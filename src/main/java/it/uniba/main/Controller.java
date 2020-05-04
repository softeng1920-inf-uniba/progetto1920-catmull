package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import gioco.*;
import pedine.*;
import scacchiera.*;

/**
 * Classe che gestisce le varie funzionalita' del gioco
 * 
 * La classe Controller e' di tipo CONTROL
 */
public class Controller {

	private Turno t;
	private ArrayList<String> mosseConvertite;

	public Controller() {
		mosseConvertite = new ArrayList<String>();
		new Scacchiera();

	}

	/**
	 * inizializzaPartita implementa la fase iniziale della partita
	 */
	final void playGame() {

		boolean utenteVuoleRicominciare = false;
		Menu.newMenu();
		do {
			
			Scacchiera.inizializzaScacchiera();

			t = new Turno();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String comando = "";

			while (true) {

				Stampa.stampaTurno(t.getGiocatoreInTurno());
				try {
					comando = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (comando.equalsIgnoreCase(Menu.help().getNome())) {
					Stampa.mostrareElencoComandiGioco();
				} else if (comando.equalsIgnoreCase(Menu.board().getNome())) {
					Stampa.stampaScacchiera();
				} else if (comando.equalsIgnoreCase(Menu.moves().getNome())) {
					Stampa.stampaMosseGiocate(t);
				} else if (comando.equalsIgnoreCase(Menu.captures().getNome())) {
					Stampa.visualizzareCatture(t);
				} else if (comando.equalsIgnoreCase(Menu.quit().getNome())) {
					chiudiGioco();
				} else if (comando.equalsIgnoreCase(Menu.play().getNome())) {
					if (utenteConfermaRiavvioPartita()) {
						Stampa.stampaNuovaPartita();
						utenteVuoleRicominciare = true;
						new Scacchiera(); // Svuoto la scacchiera
						break;
					} else
						continue; // Faccio ripartire il loop interno
				}

				if (isNotazioneAlgebrica(comando)) {

					// ritorna un array di comandi
					ArrayList<String> notazioneEstesa = convertiNotazioneRidottaInEstesa(comando);

					boolean isArrayComandiValidi = controllaArrayComandi(notazioneEstesa, mosseConvertite);
					if (isArrayComandiValidi) {

						int tipoMossa = 0;
						for (int i = 0; i < notazioneEstesa.size(); i++) {

							Cella cellaPartenza = Scacchiera.getCella(startX(notazioneEstesa.get(i)),
									startY(notazioneEstesa.get(i)));
							Cella cellaDestinazione = Scacchiera.getCella(endX(notazioneEstesa.get(i)),
									endY(notazioneEstesa.get(i)));
							tipoMossa = getTipoMossa(cellaPartenza, cellaDestinazione, mosseConvertite);
							applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
							addMosseConvertite(notazioneEstesa.get(i));

						}
						t.getGiocatoreInTurno().setMosseGiocate(comando);
						t.cambioTurno();

					} else {
						Stampa.stampaMossaIllegale();
					}
				} else if (!isComandoValido(comando)) {

					Stampa.stampaComandoErrato();

				}
			}

		} while (utenteVuoleRicominciare);

	}

	/**
	 *
	 * @param cellaPartenza
	 * @param cellaDestinazione
	 * @param scacchiera
	 * @param mosseEffettuate
	 * @return -1 se mossa illegale, 0 se mossa valida, 1 se en passant valido, 2 se
	 *         arrocco
	 */
	private int getTipoMossa(Cella cellaPartenza, Cella cellaDestinazione, ArrayList<String> mosseEffettuate) {

		Pezzo pezzoCorrente = cellaPartenza.getPezzoCorrente();
		if (pezzoCorrente == null) // Se ho inserito una mossa il cui pezzo di partenza non esiste, ritorno mossa
									// illegale
			return -1;
		if (pezzoCorrente.isMossaValida(cellaPartenza, cellaDestinazione))
			return 0;
		else if (pezzoCorrente.getNome().equals("Pedone")
				&& pezzoCorrente.isMossaSpecialeValida(cellaPartenza, cellaDestinazione, mosseEffettuate)) // Controllo
																											// se l'en
																											// Passant
																											// è
																											// consentito
			return 1;

		return -1;

	}

	/**
	 *
	 * @param comandiArr Array di comandi già convertiti
	 * @return
	 */
	private boolean controllaArrayComandi(ArrayList<String> comandiArr, ArrayList<String> storicoMosse) {

		int i = 0;
		while (i < comandiArr.size()) {
			String comando = comandiArr.get(i);

			if (isCoordinateValide(startX(comando), startY(comando), endX(comando), endY(comando))) {
				Cella cellaPartenza = Scacchiera.getCella(startX(comando), startY(comando));
				Cella cellaDestinazione = Scacchiera.getCella(endX(comando), endY(comando));
				if (getTipoMossa(cellaPartenza, cellaDestinazione, storicoMosse) == -1) // La mossa è valida ma non
																						// consentita
					return false;
			} else // La mossa inserita non è valida, va oltre i limiti della scacchiera
				return false;
			i += 1;
		}

		return true;
	}

	/**
	 * Restituisce se le coordinate (necessariamente in interi, presi dalla stringa
	 * principale del comando) sono valide. Non posso passare le celle perchè
	 * altrimenti se il comando non fosse valido (es. b0 b1), il sistema
	 * controllando b0 prenderà la cella -1 e andrà in eccezione)
	 *
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @return
	 */
	private boolean isCoordinateValide(int startX, int startY, int endX, int endY) {
		// Controllo che il comando convertito sia valido per la scacchiera
		return Scacchiera.isRangeValido(startX, startY) && Scacchiera.isRangeValido(endX, endY);
	}

	private boolean utenteConfermaRiavvioPartita() {

		String comando = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stampa.stampaConfermaNuovaPartita();
		while (true) {
			try {
				comando = br.readLine();
				switch (comando) {
				case "y":
					return true;
				case "n":
					return false;
				default:
					Stampa.stampaComandoErrato();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * La seguente funzione riconosce se il comando inserito e' un comando scritto
	 * sottoforma di notazione algebrica. Il seguente comando puo' essere anche una
	 * mossa non valida
	 *
	 * @param comando
	 *
	 * @return boolean
	 */
	private boolean isComandoValido(final String comando) {

		if (comando.equalsIgnoreCase(Menu.help().getNome())
				|| comando.equalsIgnoreCase(Menu.board().getNome())
				|| comando.equalsIgnoreCase(Menu.captures().getNome())
				|| comando.equalsIgnoreCase(Menu.moves().getNome())
				|| comando.equalsIgnoreCase(Menu.quit().getNome())) {
			return true;
		}
		return false;
	}

	/**
	 * Controlla, attraverso un'espressione regolare, se la stringa inserita
	 * dall'utente è riconosciuta come notazione algebrica.
	 *
	 * @param mossa
	 * @return boolean
	 */
	private boolean isNotazioneAlgebrica(final String mossa) {
		String regex = String.join("|", new String[] { 
				"([a-h](x|:))?([a-h][1-8])( e.p.)?", // mossa del pedone
				"D([x|:])?[a-h][1-8]", // mossa della regina
				"T([a-h]|[1-8])?([x|:])?([a-h][1-8])", // mossa della torre
				"C([a-h]|[1-8])?([x|:])?([a-h][1-8])", // mossa cavallo
				"A(x|:)?[a-h][1-8]", // mossa alfiere
				"R(x|:)?[a-h][1-8]" // mossa del re
		});

		return mossa.matches(regex);
	}

	
	
	/**
	 * Funzione che consente di chiudere il gioco e lasciare il controllo al sistema
	 * operativo
	 *
	 */
	public void chiudiGioco() {
		System.exit(0);
	}

	public final void applicaMossa(Cella cellaPartenza, Cella cellaDestinazione, int tipoMossa) {

		Pezzo pezzoInCellaDestinazione = cellaDestinazione.getPezzoCorrente();
		Cella cellaAdiacenteEp = Scacchiera.getCella(cellaDestinazione.getX(), cellaPartenza.getY());
		Giocatore giocatoreAttivo = t.getGiocatoreInTurno();
		switch (tipoMossa) {
		case 0: // Caso Mossa Normale(Spostamento,Cattura) di un pezzo
			if (cellaDestinazione.isOccupato()) {
				giocatoreAttivo.addPezziCatturati(pezzoInCellaDestinazione);
				cellaDestinazione.rimuoviPezzoCorrente();
			}
			Scacchiera.scambiaCella(cellaPartenza, cellaDestinazione);
			break;
		case 1:// Caso mossa speciale ep
			giocatoreAttivo.addPezziCatturati(cellaAdiacenteEp.getPezzoCorrente());
			cellaAdiacenteEp.rimuoviPezzoCorrente();
			Scacchiera.scambiaCella(cellaPartenza, cellaDestinazione);
			break;

		}

	}

	/**
	 * Restituisce la lista delle mosse convertite in notazione comprensibile da
	 * applicaMossa.
	 *
	 * @return mosseConverite
	 */
	public ArrayList<String> getMosseConvertite() {
		return mosseConvertite;
	}

	/**
	 * Restituisce la lista delle mosse convertite in notazione comprensibile da
	 * applicaMossa.
	 */
	public void addMosseConvertite(String mossa) {
		mosseConvertite.add(mossa);
	}

	/**
	 * metodo che serve a modificare il comando a seconda del pezzo da muovere
	 *
	 *
	 *
	 * @param mossa in notazione algebrica
	 * @return String
	 */
	private final ArrayList<String> convertiNotazioneRidottaInEstesa(String mossa) {

		ArrayList<String> comandi = new ArrayList<String>();

		switch (mossa.charAt(0)) {
		case 'T': // Torre
			comandi.add(Torre.convertiMossa(mossa, t.getGiocatoreInTurno()));
			break;
		case 'A': // Alfiere
			comandi.add(Alfiere.convertiMossa(mossa, t.getGiocatoreInTurno()));
			break;
		case 'R': // Re
			comandi.add(Re.convertiMossa(mossa, t.getGiocatoreInTurno()));
			break;
		case 'D': // Donna
			comandi.add(Regina.convertiMossa(mossa, t.getGiocatoreInTurno()));
			break;
		case 'C': // Cavallo
			comandi.add(Cavallo.convertiMossa(mossa, t.getGiocatoreInTurno()));
			break;
		case '0':

			break;
		default:
			// pedone
			comandi.add(Pedone.convertiMossa(mossa, t.getGiocatoreInTurno()));
		}

		return comandi;
	}

	/**
	 * Converte la coordinata X di partenza data in input in intero.
	 *
	 * @param m
	 * @return
	 */
	private static int startX(String m) {
		return Cella.coordXinInt(m.charAt(0));
	}

	/**
	 * Converte la coordinata Y di partenza data in input in intero.
	 *
	 * @param m
	 * @return
	 */
	private static int startY(String m) {
		return Cella.coordYinInt(m.charAt(1));
	}

	/**
	 * Converte la coordinata X di partenza data in input in intero.
	 *
	 * @param m
	 * @return
	 */
	private static int endX(String m) {
		return Cella.coordXinInt(m.charAt(3));
	}

	/**
	 * Converte la coordinata Y di partenza data in input in intero.
	 *
	 * @param m
	 * @return
	 */
	private static int endY(String m) {
		return Cella.coordYinInt(m.charAt(4));
	}

}
