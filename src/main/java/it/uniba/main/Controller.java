/**
 *
 */
package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import gioco.Turno;
import pedine.Pedone;
import pedine.Regina;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che gestisce le varie funzionalita' del gioco
 */
public class Controller {

	private Scacchiera s;
	private Turno t;
	private Menu menu;
	private ArrayList<String> mosseConvertite;

	public Controller() {
		mosseConvertite = new ArrayList<String>();
		menu = new Menu();
		s = new Scacchiera();
	}

	/**
	 * Funzione che consente di chiudere il gioco e lasciare il controllo al sistema
	 * operativo
	 *
	 */
	final void chiudiGioco() {
		System.exit(0);
	}

	/**
	 * inizializzaPartita implementa la fase iniziale della partita
	 */
	final void inizializzaPartita() {

		clearConsole();
		System.out.println("Benvenuto nel gioco degli scacchi.");
		System.out.println("\n\u2022" + " Digita 'Menu' per tornare al menu principale.");
		System.out.println("\u2022" + " Digita 'Help' per visualizzare l'elenco dei comandi.");
		t = new Turno();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String comando = "";
		String comandoNonConvertito = "";

		s.inizializzaScacchiera();

		while (true) {

			System.out.println("\nE' il turno di " + t.getGiocatoreInTurno().getNome() + " con le pedine di colore "
					+ t.getGiocatoreInTurno().getColore() + ".");

			System.out.println(
					"-> Inserisci una mossa nella notazione algebrica (es. e4, exd3, exd3 e.p.); altrimenti digita una voce del menu.");

			try {
				comando = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("\n");

			if (comando.equalsIgnoreCase(menu.help().getNome())) {
				mostrareElencoComandiGioco();
			} else if (comando.equalsIgnoreCase(menu.board().getNome())) {
				s.stampa();
			} else if (comando.equalsIgnoreCase(menu.back().getNome())) {
				System.out.println("\u265A" + "\u265B" + "  Menu principale " + "\u2655" + "\u2656" + " \n");
				System.out.println("Digitare help per visualizzare la lista dei comandi");
				return;
			} else if (comando.equalsIgnoreCase(menu.moves().getNome())) {
				stampaMosseGiocate();
			} else if (comando.equalsIgnoreCase(menu.captures().getNome())) {
				visualizzareCatture();
			} else if (comando.equalsIgnoreCase(menu.quit().getNome())) {
				chiudiGioco();
			} else if (comando.equalsIgnoreCase(menu.play().getNome())) {
				if( utenteConfermaRiavvioPartita() ) {
					inizializzaPartita();
				} else continue;
			}

			if (isNotazioneAlgebrica(comando)) {
				comandoNonConvertito = comando;
				comando = ConvertiMossa(comando);

				if (s.controllaRange(Cella.coordXinInt(comando.charAt(0)), Cella.coordYinInt(comando.charAt(1))) && (s
						.controllaRange(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4))))) {
					if (applicaMossa(startX(comando), startY(comando), endX(comando), endY(comando), s,
							mosseConvertite)) {
						addMosseConvertite(comando);
						t.getGiocatoreInTurno().setMosseGiocate(comandoNonConvertito);
						t.cambioTurno();
					} else {
						System.out.println("Mossa illegale.");
					}

				} else {
					System.out.println("Mossa illegale.");
				}
			} else if (!isComandoValido(comando)) {

				System.out.println("Comando non corretto. Riprova!");

			}
		}

	}

	private boolean utenteConfermaRiavvioPartita() {

		String comando = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Sei sicuro di voler iniziare una nuova partita? (Digita 'y' per confermare, 'n' altrimenti)\n");

		while( true ) {
			try {
				comando = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch(comando) {
				case "y":
					return true;
				case "n":
					return false;
				default:
					System.out.println("Il comando inserito non e' valido. Riprova \n");

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

		if (comando.equalsIgnoreCase(menu.help().getNome()) || comando.equalsIgnoreCase(menu.back().getNome())
				|| comando.equalsIgnoreCase(menu.board().getNome())
				|| comando.equalsIgnoreCase(menu.captures().getNome())
				|| comando.equalsIgnoreCase(menu.moves().getNome())
				|| comando.equalsIgnoreCase(menu.quit().getNome())) {
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

		// Espressione regolare completa
		/*
		 *
		 * [TACRD][a-h][1-8]| [TACRD][a-h]x[a-h][1-8]| [TACRD][a-h][1-8]x[a-h][1-8]|
		 * [TACRD][a-h][1-8][a-h][1-8]| [TACRD][a-h][a-h][1-8]| [TACRD]x[a-h][1-8]|
		 * [a-h]x[a-h][1-8]=(A+T+D+C)| [a-h]x[a-h][1-8]|
		 * [a-h][1-8]x[a-h][1-8]=(B+R+Q+N)| [a-h][1-8]x[a-h][1-8]|
		 * [a-h][1-8][a-h][1-8]=(B+R+Q+N)| [a-h][1-8][a-h][1-8]| [a-h][1-8]=(A+T+D+C)|
		 * [a-h][1-8]| [ATDCR][1-8]x[a-h][1-8]| [ATDCR][1-8][a-h][1-8]
		 */

		/*
		 * Obiettivi: - Riconoscere mossa pedone //[a-h][1-8]\ [a-h][1-8] - Riconoscere
		 * mossa non pedone //(T|A|C|R|D)?(1-8)?[a-h]([a-h])?[1-8]\ [a-h][1-8] -
		 * Riconoscere cattura [a-h](x|:)([a-h][1-8])
		 */
		// String regex_cattura_pezzo = "[a-h](x|:)([a-h][1-8])";

		String regex = String.join("|", new String[] { "[a-h][1-8]", // mossa del pedone
				"[a-h](x|:)([a-h][1-8])( e.p.)?", // cattura del pedone, con possibilità dell'en passant
				"(D)(x|:)?[a-h][1-8]", // mossa della regina
		});

		return mossa.matches(regex);
	}

	/**
	 * Mostra le catture di entrambi i giocatori
	 */
	void visualizzareCatture() {

		if (!t.getGiocatoreInTurno().isEmptyPezziCatturati()) {
			t.getGiocatoreInTurno().stampaPezziCatturati();
		}
		if (!t.getGiocatoreInAttesa().isEmptyPezziCatturati()) {
			t.getGiocatoreInAttesa().stampaPezziCatturati();
		}
	}

	/**
	 * Fonde le due liste in cui sono conservate le mosse giocate di ogni giocatore.
	 * La fusione avviene in modo alternato. Permette di avere una visione completa
	 * delle mosse giocate totali.
	 *
	 * @return ArrayList di stringhe.
	 */
	private ArrayList<String> fusioneListe() {
		int i, j, k;
		int dimensione = t.getGiocatoreInAttesa().getNumeroMosseGiocate()
				+ t.getGiocatoreInTurno().getNumeroMosseGiocate();
		ArrayList<String> mosseGiocateTotali = new ArrayList<String>(dimensione);
		if (t.getGiocatoreInTurno().getColore() == Colore.bianco) {
			i = 0;
			j = 0;
			k = 0;
			while (i < t.getGiocatoreInTurno().getNumeroMosseGiocate()
					&& j < t.getGiocatoreInAttesa().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, t.getGiocatoreInTurno().getMossaGiocata(i++));
				mosseGiocateTotali.add(k++, t.getGiocatoreInAttesa().getMossaGiocata(j++));
			}
			while (i < t.getGiocatoreInTurno().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, t.getGiocatoreInTurno().getMossaGiocata(i++));
			}
			while (j < t.getGiocatoreInAttesa().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, t.getGiocatoreInAttesa().getMossaGiocata(j++));
			}
		} else {
			i = 0;
			j = 0;
			k = 0;
			while (i < t.getGiocatoreInAttesa().getNumeroMosseGiocate()
					&& j < t.getGiocatoreInTurno().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, t.getGiocatoreInAttesa().getMossaGiocata(i++));
				mosseGiocateTotali.add(k++, t.getGiocatoreInTurno().getMossaGiocata(j++));
			}
			while (i < t.getGiocatoreInAttesa().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, t.getGiocatoreInAttesa().getMossaGiocata(i++));
			}
			while (j < t.getGiocatoreInTurno().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, t.getGiocatoreInTurno().getMossaGiocata(j++));
			}

		}

		return mosseGiocateTotali;
	}

	/**
	 * Stampa a video l'elenco delle mosse giocate del giocatore.
	 */
	public void stampaMosseGiocate() {
		String mossa = null;
		int counter = 1;
		System.out.println("Storia delle mosse giocate");
		int dimensione = t.getGiocatoreInAttesa().getNumeroMosseGiocate()
				+ t.getGiocatoreInTurno().getNumeroMosseGiocate();
		for (int i = 0; i < dimensione; i++) {
			if (i == dimensione - 1) {
				mossa = counter + ". " + fusioneListe().get(i);
				System.out.println(mossa);
			} else {
				mossa = counter + ". " + fusioneListe().get(i) + " " + fusioneListe().get(i + 1);
				System.out.println(mossa);
			}
			i++;
			counter++;
		}
	}

	/**
	 * Metodo che permette la visualizzazione dell 'elenco comandi del menu
	 * principale: Quit play board
	 */

	public void mostrareElencoComandiMenu() {
		System.out.println(menu.quit().toString());
		System.out.println(menu.play().toString());
		System.out.println(menu.board().toString());
	}

	/**
	 * Metodo che permette la visualizzazione dell' elenco comandi del menu di
	 * gioco: Quit board captures history back
	 */
	public void mostrareElencoComandiGioco() {
		System.out.println(menu.back().toString());
		System.out.println(menu.play().toString());
		System.out.println(menu.board().toString());
		System.out.println(menu.captures().toString());
		System.out.println(menu.moves().toString());
		System.out.println(menu.quit().toString());

	}

	void stampaScacchiera() {
		s.stampa();
	}

	/**
	 * Applica la mossa data in input tramite stringa.
	 *
	 * @param comando
	 * @return booleano true se la mossa è applicabile, false altrimenti
	 */
	public final boolean applicaMossa(int startX, int startY, int endX, int endY, Scacchiera s,
			ArrayList<String> mosse) {
		if ((s.getCella(startX, startY).isOccupato())) {
			if (s.getCella(startX, startY).getPezzoCorrente().isMossaValida(s.getCella(startX, startY),
					s.getCella(endX, endY), s)) {
				if (s.getCella(endX, endY).isOccupato()) {
					t.getGiocatoreInTurno().addPezziCatturati(s.getCella(endX, endY).getPezzoCorrente());
					s.mangiaPezzo(endX, endY);
				}
				s.scambiaCella(s.getCella(startX, startY), s.getCella(endX, endY));
				return true;
			} else if (s.getCella(startX, startY).getPezzoCorrente().isMossaSpeciale(s.getCella(startX, startY),
					s.getCella(endX, endY), s, mosse)) {
				t.getGiocatoreInTurno().addPezziCatturati(s.getCella(endX, startY).getPezzoCorrente());
				s.mangiaPezzo(endX, startY);
				s.scambiaCella(s.getCella(startX, startY), s.getCella(endX, endY));
				return true;
			}

		}

		return false;
	}

	public ArrayList<String> getMosseConvertite() {
		return mosseConvertite;
	}

	public void addMosseConvertite(String mossa) {
		mosseConvertite.add(mossa);
	}

	/**
	 * metodo che serve a modificare il comando a seconda del pezzo da muovere
	 *
	 * @param mossa
	 * @return String
	 */
	private final String ConvertiMossa(String mossa) {
		if (mossa.charAt(0) >= 'a') {
			return Pedone.ConvertiMossa(mossa, s, t.getGiocatoreInTurno());
		} else {
			if (mossa.charAt(0) == 'D')
				return Regina.convertiMossa(mossa, s, t.getGiocatoreInTurno());
			return mossa;
		}
	}

	private static int startX(String m) {
		return Cella.coordXinInt(m.charAt(0));
	}

	private static int startY(String m) {
		return Cella.coordYinInt(m.charAt(1));
	}

	private static int endX(String m) {
		return Cella.coordXinInt(m.charAt(3));
	}

	private static int endY(String m) {
		return Cella.coordYinInt(m.charAt(4));
	}

	public final static void clearConsole()	{
		for (int i = 0; i < 100; ++i)
			System.out.println();
	}
}
