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
	private static final int EN_PASSANT = 3;
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
			Turno.setNomeGiocatoreInTurno(InterfacciaUtente
					.getNomeDaTastiera(Turno.getGiocatoreInTurno().getColore()));
			Turno.setNomeGiocatoreInAttesa(
					InterfacciaUtente.getNomeDaTastiera(Turno.getGiocatoreInAttesa().getColore()));
			while (true) {
				InterfacciaUtente.stampaTurno(Turno.getGiocatoreInTurno());
				String comando = InterfacciaUtente.acquisireComando();
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
				} else if (Menu.isNotazioneAlgebrica(comando)) {
					if (mossaScacchi(comando)) {
						Turno.getGiocatoreInTurno().setMosseGiocate(comando);
						Turno.cambioTurno();
					} else {
						InterfacciaUtente.stampaMossaIllegale();
					}
				} else {
					InterfacciaUtente.stampaComandoErrato();
				}
			}
		} while (utenteVuoleRicominciare);
	}

	/**
	 * Dopo i dovuti controlli permette di giocare la mossa inserita dal giocatore,
	 * e restituisce true se la mossa � valida ed � stata applicata altrimenti
	 * restituisce false
	 *
	 * @param mossaDaGiocare
	 * @return
	 */
	public static boolean mossaScacchi(final String mossaDaGiocare) {
		int tipoMossa = getTipoMossa(mossaDaGiocare);
		if (tipoMossa == 2) {
			int tipoArrocco = Menu.isArrocco(mossaDaGiocare);
			Colore coloreGiocatoreAttivo = Turno.getGiocatoreInTurno().getColore();
			String mossaTorre = Torre.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);
			String mossaRe = Re.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);
			// Non ho bisogno di controllare se i comandi convertiti saranno
			// validi, perche' sono stati gia' stabiliti dalle regole del gioco
			// Controllo se l'arrocco e' possibile
			if (isArroccoValido(mossaRe, mossaTorre, tipoArrocco)) {
				// Applico arrocco Re
				Cella cellaPartenza = Scacchiera.getCella(Cella.startX(mossaRe), Cella.startY(mossaRe));
				Cella cellaDestinazione = Scacchiera.getCella(Cella.endX(mossaRe), Cella.endY(mossaRe));
				applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
				// Applico arrocco Torre
				cellaPartenza = Scacchiera.getCella(Cella.startX(mossaTorre), Cella.startY(mossaTorre));
				cellaDestinazione = Scacchiera.getCella(Cella.endX(mossaTorre), Cella.endY(mossaTorre));
				applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
				addMosseConvertite(mossaDaGiocare);
			} else {
				return false;
			}
		} else { // operazioni valide sia per una mossa normale che per un en passant
			String mossaEstesa = convertiMossaInEstesa(mossaDaGiocare);
			if (Scacchiera.isRangeValido(Cella.startX(mossaEstesa), Cella.startY(mossaEstesa))
					&& Scacchiera.isRangeValido(Cella.endX(mossaEstesa), Cella.endY(mossaEstesa))) {
				Cella cellaPartenza = Scacchiera
						.getCella(Cella.startX(mossaEstesa), Cella.startY(mossaEstesa));
				Cella cellaDestinazione = Scacchiera
						.getCella(Cella.endX(mossaEstesa), Cella.endY(mossaEstesa));
				if (new Pedone(Turno.getGiocatoreInTurno().getColore())
						.isEnPassantValido(cellaPartenza, cellaDestinazione, mosseConvertite)) {
						tipoMossa = EN_PASSANT;
						applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
						addMosseConvertite(mossaEstesa);
				} else {
					// se puo' essere un en passant
					if (isMossaValidaGlobale(mossaEstesa, tipoMossa) && tipoMossa != EN_PASSANT) {
						applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
						addMosseConvertite(mossaEstesa);
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return true;

	}

	/**
	 * Data la notazione algebrica inserita dall'utente, l'algoritmo restituisce 0
	 * se � una mossa semplice, 1 se � una mossa di cattura, 2 se � un arrocco e 3
	 * se � di sicuro un en passant
	 * @param mossaInInput
	 * @return
	 */
	private static int getTipoMossa(final String mossaInInput) {
		int tipoMossa = 0;
		if (mossaInInput.matches("(D|R|T|A|C)?([a-h]|[1-8])?(x|:)[a-h][1-8]")) {
			tipoMossa = 1;
		} else if (mossaInInput.matches("(0|o|O)-(0|o|O)(-(0|o|O))?")) {
			tipoMossa = 2;
		} else if (mossaInInput.matches("([a-h](x|:))([a-h][1-8])( e.p.)")) {
			tipoMossa = EN_PASSANT;
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
	private static String convertiMossaInEstesa(final String mossa) {

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
	private static boolean isMossaValidaGlobale(final String comando, final int tipoMossa) {
		Cella cellaPartenza = Scacchiera.getCella(Cella.startX(comando), Cella.startY(comando));
		Cella cellaDestinazione = Scacchiera.getCella(Cella.endX(comando), Cella.endY(comando));
		if (cellaPartenza.isOccupato()
				&& cellaPartenza.getPezzoCorrente().isMossaValida(cellaPartenza, cellaDestinazione)) {
			if (isReProtetto(cellaPartenza, cellaDestinazione)) {
				return true;
			}
		}
		return false;
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
	private static void applicaMossa(final Cella cellaPartenza,
	final Cella cellaDestinazione, final int tipoMossa) {
		Pezzo pezzoInCellaDestinazione = cellaDestinazione.getPezzoCorrente();
		Giocatore giocatoreAttivo = Turno.getGiocatoreInTurno();
		switch (tipoMossa) {
		case 1:
			if (cellaDestinazione.isOccupato()) {
				giocatoreAttivo.addPezziCatturati(pezzoInCellaDestinazione);
				cellaDestinazione.rimuoviPezzoCorrente();
			}
			break;
		case EN_PASSANT:
			Cella cellaAdiacenteEp = Scacchiera.getCella(cellaDestinazione.getX(), cellaPartenza.getY());
			giocatoreAttivo.addPezziCatturati(cellaAdiacenteEp.getPezzoCorrente());
			cellaAdiacenteEp.rimuoviPezzoCorrente();
			break;
		default:
		}
		Scacchiera.scambiaCella(cellaPartenza, cellaDestinazione);
	}

	/**
	 * <<<<<<< Updated upstream ======= Lascia il controllo al sistema operativo
	 *
	 */
	public static void chiudiGioco() {
		System.exit(0);
	}

	/**
	 * >>>>>>> Stashed changes Restituisce la lista delle mosse convertite in
	 * notazione comprensibile da applicaMossa.
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
		// Se nella cella di partenza del presunto re c'e' il re del colore del
		// giocatore
		// in turno..
		if (cellaPartenzaRe.isOccupato() && presuntoReGiocatoreAttuale.getNome() == "Re"
				&& presuntoReGiocatoreAttuale.getColore() == coloreGiocatoreAttivo
				// e nella cella della presunta torre c'e' la torre del colore del giocatore in
				// turno..
				&& cellaPartenzaTorre.isOccupato() && presuntaTorreGiocatoreAttuale.getNome() == "Torre"
				&& presuntaTorreGiocatoreAttuale.getColore() == coloreGiocatoreAttivo) {
			Re re = (Re) presuntoReGiocatoreAttuale;
			return re.isArroccoValido(cellaPartenzaRe, cellaDestinazioneRe, cellaPartenzaTorre,
					cellaDestinazioneTorre, getMosseConvertite(), tipoArrocco);
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
	public static boolean isReProtetto(final Cella partenza, final Cella destinazione) {

		Cella cellaRe = Re.findRe();
		Re reDaProteggere = (Re) cellaRe.getPezzoCorrente();
		Cella temp = new Cella(destinazione.getX(), destinazione.getY(), destinazione.getPezzoCorrente());
		boolean isReProtetto = false;
		if (partenza.getPezzoCorrente().getNome().equals("Re")) {
			isReProtetto = true;
			return isReProtetto;
		}
		Scacchiera.scambiaCella(partenza, destinazione);
		if (!reDaProteggere.isReSottoScacco(cellaRe)) {
			isReProtetto = true;
		}
		Scacchiera.scambiaCella(destinazione, partenza);
		if (temp.isOccupato()) {
			Scacchiera.getCella(temp.getX(), temp.getY()).aggiungiPezzo(temp.getPezzoCorrente());
		}
		return isReProtetto;
	}
}
