package it.uniba.main;

import java.util.ArrayList;

import gioco.Colore;
import gioco.Giocatore;
import gioco.InterfacciaUtente;
import gioco.Menu;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;
import scacchiera.pedine.Alfiere;
import scacchiera.pedine.Cavallo;
import scacchiera.pedine.Pedone;
import scacchiera.pedine.Pezzo;
import scacchiera.pedine.Re;
import scacchiera.pedine.Regina;
import scacchiera.pedine.Torre;

/**
 * Classe che gestisce le varie funzionalita'� del gioco, permette di iniziare
 * una nuova partita o di effettuarne una. La classe Controller e' di tipo
 * CONTROL
 */
public final class Controller {
	private static ArrayList<String> mosseConvertite = new ArrayList<String>();

	private Controller() {
	}

	/**
	 * Costruttore privato per classe Singleton
	 */
	public static void newController() {
		Scacchiera.newScacchiera();
	}

	/**
	 * inizializzaPartita implementa la fase iniziale della partita
	 */
	static void playGame() {
		boolean utenteVuoleRicominciare = false;
		do {
			Turno.newTurno();
			Turno.setNomeGiocatoreInAttesa(
					InterfacciaUtente.getNomeDaTastiera(Turno.getGiocatoreInTurno().getColore()));
			Turno.setNomeGiocatoreInTurno(
					InterfacciaUtente.getNomeDaTastiera(Turno.getGiocatoreInAttesa().getColore()));
			while (true) {
				InterfacciaUtente.stampaTurno(Turno.getGiocatoreInTurno());
				String comando = InterfacciaUtente.acquisireComando();
				if (comando != null) {
					if (comando.equalsIgnoreCase(Menu.help().getNome())) {
						InterfacciaUtente.mostrareElencoComandiGioco();
					} else if (comando.equalsIgnoreCase(Menu.board().getNome())) {
						InterfacciaUtente.stampaScacchiera();
					} else if (comando.equalsIgnoreCase(Menu.moves().getNome())) {
						InterfacciaUtente.stampaMosseGiocate();
					} else if (comando.equalsIgnoreCase(Menu.captures().getNome())) {
						InterfacciaUtente.visualizzareCatture();
					} else if (comando.equalsIgnoreCase(Menu.quit().getNome())) {
						if (InterfacciaUtente.utenteConfermaFinePartita()) {
							utenteVuoleRicominciare = false;
							break;
						}

					} else if (comando.equalsIgnoreCase(Menu.play().getNome())) {
						if (InterfacciaUtente.utenteConfermaRiavvioPartita()) {
							InterfacciaUtente.stampaNuovaPartita();
							utenteVuoleRicominciare = true;
							Scacchiera.newScacchiera(); // Svuoto la scacchiera
							break;

						} else {
							continue; // Faccio ripartire il loop interno
						}
					}
				} else {
					break;
				}

				if (Menu.isNotazioneAlgebrica(comando)) {
					int tipoArrocco = Menu.isArrocco(comando);
					if (tipoArrocco != -1) { // Se è un arrocco
						Colore coloreGiocatoreAttivo = Turno.getGiocatoreInTurno().getColore();

						String mossaTorre = Torre.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);
						String mossaRe = Re.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);

						// Non ho bisogno di controllare se i comandi convertiti saranno validi, perchè
						// sono stati gia' stabiliti dalle regole del gioco

						// Controllo se l'arrocco è possibile
						if (isArroccoValido(mossaRe, mossaTorre, tipoArrocco)) {
							// Applico arrocco

							Cella cellaPartenzaRe = Scacchiera.getCella(Cella.startX(mossaRe), Cella.startY(mossaRe));
							Cella cellaDestinazioneRe = Scacchiera.getCella(Cella.endX(mossaRe), Cella.endY(mossaRe));

							Scacchiera.scambiaCella(cellaPartenzaRe, cellaDestinazioneRe);

							Cella cellaPartenzaTorre = Scacchiera.getCella(Cella.startX(mossaTorre),
									Cella.startY(mossaTorre));
							Cella cellaDestinazioneTorre = Scacchiera.getCella(Cella.endX(mossaTorre),
									Cella.endY(mossaTorre));

							Scacchiera.scambiaCella(cellaPartenzaTorre, cellaDestinazioneTorre);

							addMosseConvertite(comando);
							Turno.getGiocatoreInTurno().setMosseGiocate(comando);
							Turno.cambioTurno();

						} else {
							InterfacciaUtente.stampaMossaIllegale();

						}
					} else { // La mossa inserita è un'avanzata, una cattura o un en passant
						String mossaInCoordinate = convertiNotazioneRidottaInEstesa(comando);

						if (isMossaInRangeValido(mossaInCoordinate)) {
							int tipoMossa = 0;
							Cella cellaPartenza = Scacchiera.getCella(Cella.startX(mossaInCoordinate),
									Cella.startY(mossaInCoordinate));
							Cella cellaDestinazione = Scacchiera.getCella(Cella.endX(mossaInCoordinate),
									Cella.endY(mossaInCoordinate));
							tipoMossa = getTipoMossa(cellaPartenza, cellaDestinazione, mosseConvertite);

							if (tipoMossa != -1) {

								applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
								addMosseConvertite(mossaInCoordinate);
								Turno.getGiocatoreInTurno().setMosseGiocate(comando);
								Turno.cambioTurno();

							} else {
								InterfacciaUtente.stampaMossaIllegale();
							}
						} else {
							InterfacciaUtente.stampaMossaIllegale();
						}
					}
				} else if (!Menu.isComandoValido(comando)) {
					// Se il comando inserito non è una mossa, nè un comando
					// di gioco...
					InterfacciaUtente.stampaComandoErrato();
				}
			} // Fine loop di gioco
		} while (utenteVuoleRicominciare);
	}

	/**
	 * Data la cella di partenza e di destinazione del pezzo, effettua i controlli
	 * necessari se per il pezzo è consentita la mossa.
	 *
	 * @param cellaPartenza
	 * @param cellaDestinazione
	 * @param mosseEffettuate
	 * @return -1 se mossa illegale, 0 se mossa valida, 1 se en passant valido
	 */

	private static int getTipoMossa(final Cella cellaPartenza,
			final Cella cellaDestinazione,
			final ArrayList<String> mosseEffettuate) {

		int tipoMossa = -1;
		Pezzo pezzoCorrente = cellaPartenza.getPezzoCorrente();
		if (pezzoCorrente == null) {
			// Se ho inserito una mossa il cui pezzo di partenza non esiste, ritorno mossa
			// illegale
			return tipoMossa;
		} else if (pezzoCorrente.isMossaValida(cellaPartenza, cellaDestinazione)) {
			if (isReProtetto(cellaPartenza, cellaDestinazione, 0)) {
				tipoMossa = 0;

			}
		} else if (pezzoCorrente.getNome().equals("Pedone")) {

			Pedone p = (Pedone) pezzoCorrente;
			// Controllo se l'en Passant e'consentito
			if (p.isEnPassantValido(cellaPartenza, cellaDestinazione, mosseEffettuate)) {
				tipoMossa = 1;
			}
		}
		return tipoMossa;
	}

	/**
	 * Trasforma il comando a seconda del pezzo da muovere
	 *
	 * @param mossa in notazione algebrica
	 * @return String comando in colonna e traversa di partenza seguite da uno
	 *         spazio, ed infine colonna e traversa di destinazione
	 */
	private static String convertiNotazioneRidottaInEstesa(final String mossa) {

		switch (mossa.charAt(0)) {
		case 'T': // Torre
			return Torre.convertiMossa(mossa);
		case 'A': // Alfiere
			return Alfiere.convertiMossa(mossa);
		case 'R': // Re
			return Re.convertiMossa(mossa);
		case 'D': // Donna
			return Regina.convertiMossa(mossa);
		case 'C': // Cavallo
			return Cavallo.convertiMossa(mossa);
		default:
			// pedone
			return Pedone.convertiMossa(mossa);
		}
	}

	/**
	 * Restituisce un valore booleano che indica se le coordinate che rappresentano
	 * gli indici della matrice scacchiera (necessariamente di tipo intero)sono
	 * valide per la scacchiera.
	 *
	 * @param startX intero compreso fra 0 e 7
	 * @param startY intero compreso fra 0 e 7
	 * @param endX   intero compreso fra 0 e 7
	 * @param endY   intero compreso fra 0 e 7
	 * @return vero se i quattro parametri sono compresi fra 0 e 7, falso altrimenti
	 */
	public static boolean isMossaInRangeValido(final String comando) {
		return Scacchiera.isRangeValido(Cella.startX(comando), Cella.startY(comando))
				&& Scacchiera.isRangeValido(Cella.endX(comando), Cella.endY(comando));
	}

	/**
	 * Dopo che sono stati effettuati i vari controlli (se la mossa inserita è
	 * valida, se è consentita ecc.), viene effettuato in base alla tipologia della
	 * mossa, il necessario per proseguire con il corretto funzionamento del gioco.
	 *
	 * L'applicazione della mossa non è univoca per ogni comando inserito (vedi
	 * e.p.)
	 *
	 * @param cellaPartenza
	 * @param cellaDestinazione
	 * @param tipoMossa         Tipologia della mossa: 0: Mossa normale (avanzata,
	 *                          cattura) di un pezzo 1: Mossa speciale (en passant)
	 *                          del pedone
	 */
	private static void applicaMossa(final Cella cellaPartenza, final Cella cellaDestinazione, final int tipoMossa) {

		Pezzo pezzoInCellaDestinazione = cellaDestinazione.getPezzoCorrente();

		Giocatore giocatoreAttivo = Turno.getGiocatoreInTurno();
		switch (tipoMossa) {
		case 0:
			if (cellaDestinazione.isOccupato()) {
				giocatoreAttivo.addPezziCatturati(pezzoInCellaDestinazione);
				cellaDestinazione.rimuoviPezzoCorrente();
			}
			break;
		case 1:
			Cella cellaAdiacenteEp = Scacchiera.getCella(cellaDestinazione.getX(), cellaPartenza.getY());
			giocatoreAttivo.addPezziCatturati(cellaAdiacenteEp.getPezzoCorrente());
			cellaAdiacenteEp.rimuoviPezzoCorrente();
			break;
		default:
		}
		Scacchiera.scambiaCella(cellaPartenza, cellaDestinazione);

	}

	/**
	 * Restituisce la lista delle mosse convertite in notazione comprensibile da
	 * applicaMossa.
	 *
	 * @return mosseConverite
	 */
	public static ArrayList<String> getMosseConvertite() {
		return mosseConvertite;
	}

	/**
	 * Aggiunge la mossa effettuata fra quelle convertite
	 */
	public static void addMosseConvertite(final String mossa) {
		mosseConvertite.add(mossa);
	}

	/**
	 * Data la mossa del re e quella della torre, vengono effettuati tutti i
	 * controlli che validano se la mossa e' consentita o meno.
	 *
	 * @param mossaRe
	 * @param mossaTorre
	 * @param tipoArrocco
	 * @return true se l'arrocco può essere effettuato, false se l'arrocco e' stato
	 *         chiamato con pezzi non compatibili / celle vuote L'arrocco e' valido
	 *         se sono verificate tutte le seguenti condizioni: - Il giocatore non
	 *         ha ancora mosso ne' il re ne' la torre coinvolta nell'arrocco; - Non
	 *         ci devono essere pezzi (amici o avversari) fra il re e la torre
	 *         usata; - Ne' la casa di partenza del re, ne' la casa che esso deve
	 *         attraversare ne' quella di arrivo devono essere minacciate da un
	 *         pezzo avversario.
	 */
	public static boolean isArroccoValido(final String mossaRe, final String mossaTorre, final int tipoArrocco) {

		Cella cellaPartenzaRe = Scacchiera.getCella(Cella.startX(mossaRe), Cella.startY(mossaRe));
		Cella cellaDestinazioneRe = Scacchiera.getCella(Cella.endX(mossaRe), Cella.endY(mossaRe));

		Pezzo presuntoReGiocatoreAttuale = cellaPartenzaRe.getPezzoCorrente();

		Cella cellaPartenzaTorre = Scacchiera.getCella(Cella.startX(mossaTorre), Cella.startY(mossaTorre));
		Cella cellaDestinazioneTorre = Scacchiera.getCella(Cella.endX(mossaTorre), Cella.endY(mossaTorre));

		Pezzo presuntaTorreGiocatoreAttuale = cellaPartenzaTorre.getPezzoCorrente();

		Colore coloreGiocatoreAttivo = Turno.getGiocatoreInTurno().getColore();
		// Se nella cella di partenza del presunto re c'è il re del colore del
		// giocatore
		// in turno..
		if (cellaPartenzaRe.isOccupato() && presuntoReGiocatoreAttuale.getNome() == "Re"
				&& presuntoReGiocatoreAttuale.getColore() == coloreGiocatoreAttivo
				// e nella cella della presunta torre c'è la torre del colore del giocatore in
				// turno..
				&& cellaPartenzaTorre.isOccupato() && presuntaTorreGiocatoreAttuale.getNome() == "Torre"
				&& presuntaTorreGiocatoreAttuale.getColore() == coloreGiocatoreAttivo) {
			Re re = (Re) presuntoReGiocatoreAttuale;

			return re.isArroccoValido(cellaPartenzaRe, cellaDestinazioneRe, cellaPartenzaTorre, cellaDestinazioneTorre,
					getMosseConvertite(), tipoArrocco);
		}

		return false;

	}

	/**
	 * Controlla se il Re non e' sotto scacco nel caso in cui un pezzo del suo
	 * stesso colore si muove in un'altra cella. Viene applicata la mossa
	 * temporaneamente per effettuare i controlli attraverso la funzione
	 * isReSottoScacco: in caso positivo viene restituito un booleano con valore
	 * false, altrimenti e' restituito un booleano con valore true. In entrambi i
	 * casi viene ripristinata la situazione immediatamente precedente alla
	 * applicazione della mossa.
	 *
	 * @param partenza
	 * @param destinazione
	 * @param tipoMossa
	 * @return isReProtetto: falso se il Re è sotto scacco, vero altrimenti.
	 */
	public static boolean isReProtetto(final Cella partenza, final Cella destinazione, final int tipoMossa) {

		Cella cellaRe = Re.findRe();
		Re reDaProteggere = (Re) cellaRe.getPezzoCorrente();
		boolean isReProtetto = false;
		if (partenza.getPezzoCorrente().getNome().equals("Re")) {
			isReProtetto = true;
			return isReProtetto;
		}
		applicaMossa(partenza, destinazione, tipoMossa);
		if (!reDaProteggere.isReSottoScacco(cellaRe)) {
			isReProtetto = true;
		}
		applicaMossa(destinazione, partenza, tipoMossa);

		Cella temp = new Cella(destinazione.getX(), destinazione.getY(), destinazione.getPezzoCorrente());
		if (temp.isOccupato()) {
			Scacchiera.getCella(temp.getX(), temp.getY()).aggiungiPezzo(temp.getPezzoCorrente());
			Turno.getGiocatoreInTurno().removePezzoCatturato();
		}

		return isReProtetto;
	}
}
