
/**
 *
 */
package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import giocatore.Giocatore;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che gestisce le varie funzionalita' del gioco
 */
public class Controller {

	private Scacchiera s;
	private Turno t;
	private Menu menu;

	public Controller() {
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

	final void inizializzaPartita() {
		System.out.println("Benvenuto nel gioco degli scacchi.");
		System.out.println("\n\u2022" + " Digita 'Menu' per tornare al menu principale.");
		System.out.println("\u2022" + " Digita 'Help' per visualizzare l'elenco dei comandi.");
		t = new Turno();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String comando = "";

		s.inizializzaScacchiera();

		while (true) {

			System.out.println("\nE' il turno di " + t.getGiocatoreInTurno().getNome() + " con le pedine di colore "
					+ t.getGiocatoreInTurno().getColore() + ".");

			System.out.println(
					"-> Inserisci una mossa nella notazione algebrica (es. e1 e4); altrimenti digita una voce del menu.");

			try {
				comando = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (comando.equalsIgnoreCase(menu.help().getNome())) {
				mostrareElencoComandiGioco();
			} else if (comando.equalsIgnoreCase(menu.board().getNome())) {
				s.stampa();
			} else if (comando.equalsIgnoreCase(menu.back().getNome())) {
				System.out.println("\u265A" + "\u265B" + "  Menu principale " + "\u2655" + "\u2656" + " \n");
				System.out.println("Digitare help per visualizzare la lista dei comandi");
				return;
			} else if (comando.equalsIgnoreCase(menu.history().getNome())) {
				stampaMosseGiocate();
			} else if (comando.equalsIgnoreCase(menu.captures().getNome())) {
				visualizzareCatture();
			} else if (comando.equalsIgnoreCase(menu.quit().getNome())) {
				chiudiGioco();
			} else if (comando.equalsIgnoreCase(menu.play().getNome())) {
				inizializzaPartita();
			}

			if (isNotazioneAlgebrica(comando)) {

				if (isAvanzataPedone(comando)) {
					comando = convertiAvanzataPedone(t.getGiocatoreInTurno(), comando);
				} else if (isCatturaPedone(comando)) {
					comando = convertiCatturaPedone(t.getGiocatoreInTurno(), comando);
				}

				if (applicaMossa(comando)) {
					t.getGiocatoreInTurno().setMosseGiocate(comando);
					t.cambioTurno();
				}
			} else if (!isComandoValido(comando)) {

				System.out.println("Comando non corretto. Riprova!");

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
				|| comando.equalsIgnoreCase(menu.history().getNome())
				|| comando.equalsIgnoreCase(menu.quit().getNome())) {
			return true;
		}
		return false;
	}

	/**
	 * Funzione che converte la notazione algebrica per l'avanzata del pedone nella
	 * sintassi di coordinate (partenza) - (arrivo)
	 * 
	 * @param Giocatore g - Necessario per determinare il colore del pezzo
	 * @param String    mossa - Mossa in input che rispetti la regex [a-h][1-8]
	 * 
	 * @return String mossaConvertita - Mossa convertita in regex [a-h][1-8]\\
	 *         [a-h][1-8]
	 */
	private String convertiAvanzataPedone(Giocatore g, String mossa) {

		int variazione = 0;
		String mossaConvertita = "";

		if (g.getColore() == Colore.bianco) {

			if ((int) mossa.charAt(1) == '4') { // traversa
				variazione = -50;
			} else {
				variazione = -49;
			}
		} else { // giocatore è pedine nere
			if ((int) mossa.charAt(1) == '5') {
				variazione = -46;
			} else
				variazione = -47;

		}

		mossaConvertita = String.valueOf(mossa.charAt(0)) + // 1° traversa
				String.valueOf(mossa.charAt(1) + variazione) + // 1° colonna
				' ' + String.valueOf(mossa.charAt(0)) + // 2° traversa
				String.valueOf(mossa.charAt(1)); // 2° colonna

		return mossaConvertita;

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
		});

		return mossa.matches(regex);
	}

	/**
	 * Funzione che ritorna un valore booleano, in base al fatto che una mossa sia
	 * di cattura di un pedone oppure no
	 * 
	 * @param String mossa
	 * @return boolean Se la mossa è di una cattura di un pedone (da parte di un
	 *         altro pedone) o meno
	 */
	private boolean isCatturaPedone(String mossa) {

		// Il formato della mossa sarà del tipo [a-h](x|:)([a-h][1-8])
		String regex = "[a-h](x|:)([a-h][1-8])( e.p.)?";
		return mossa.matches(regex); // Se è una mossa di cattura

	}

	/**
	 * Funzione che converte la notazione algebrica per la cattura di un pedone
	 * nella sintassi con coordinate (partenza) - (arrivo)
	 * 
	 * @param Giocatore g - Necessario per determinare il colore del pezzo
	 * @param String    mossa - Mossa in input che rispetti la regex di cattura
	 *                  pedone (vedi isCatturaPedone)
	 * 
	 * @return String mossaConvertita - Mossa convertita in regex [a-h][1-8]\\
	 *         [a-h][1-8]
	 */
	private String convertiCatturaPedone(Giocatore g, String mossa) {

		int variazione;
		String mossaConvertita = "";

		if (g.getColore() == Colore.bianco) {
			variazione = -49;
		} else
			variazione = -47;
		
		mossaConvertita = String.valueOf(mossa.charAt(0)) + String.valueOf(mossa.charAt(3) + variazione) + ' '
				+ String.valueOf(mossa.charAt(2)) + String.valueOf(mossa.charAt(3));
		
		if(mossa.length()>=4) {
			mossaConvertita = mossaConvertita + mossa.substring(4);
		}

		return mossaConvertita;

	}

	/**
	 * Un'avanzata del pedone è una mossa che rispetta l'espressione regolare
	 * [a-h][1-8]
	 * 
	 * @param String mossa
	 * @return boolean (true | false)
	 */
	private boolean isAvanzataPedone(String mossa) {

		String regex = "[a-h][1-8]";
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
		int dimensione = t.getGiocatoreInAttesa().getNumeroMosseGiocate()
				+ t.getGiocatoreInTurno().getNumeroMosseGiocate();
		ArrayList<String> mosseGiocateTotali = new ArrayList<String>(dimensione);
		int i = 0;
		int j = 0;
		int k = 0;
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
		return mosseGiocateTotali;
	}

	/**
	 * Stampa a video l'elenco delle mosse giocate del giocatore.
	 */
	public void stampaMosseGiocate() {
		System.out.println("Storia delle mosse giocate");
		int dimensione = t.getGiocatoreInAttesa().getNumeroMosseGiocate()
				+ t.getGiocatoreInTurno().getNumeroMosseGiocate();
		for (int i = 0; i < dimensione; i++) {
			if ((i % 2) == 0) {
				System.out.println("Bianco " + "[" + fusioneListe().get(i) + "]");
			} else {
				System.out.println("Nero   " + "[" + fusioneListe().get(i) + "]");
			}
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
		System.out.println(menu.board().toString());
		System.out.println(menu.captures().toString());
		System.out.println(menu.history().toString());
		System.out.println(menu.quit().toString());

	}

	void stampaScacchiera() {
		s.stampa();
	}
	
	private boolean isMossaEnPassant(String mossa){
			return mossa.length()>=4 && (mossa.substring(6) == "e.p.");
	}
	
	/**
	 * Applica la mossa data in input tramite stringa.
	 * 
	 * @param comando
	 * @return booleano true se la mossa è applicabile, false altrimenti
	 */
	public final boolean applicaMossa(String comando) {
		if ((s.getCella(Cella.coordXinInt(comando.charAt(0)), Cella.coordYinInt(comando.charAt(1))).isOccupato())) {
			if (t.getGiocatoreInTurno().getColore() == Colore.bianco
					&& s.getCella(Cella.coordXinInt(comando.charAt(0)), Cella.coordYinInt(comando.charAt(1)))
							.getPezzoCorrente().getColore() == Colore.bianco) {
				if (s.getCella(Cella.coordXinInt(comando.charAt(0)), Cella.coordYinInt(comando.charAt(1)))
						.getPezzoCorrente().isMossaValidaBianco(comando, s)) {
					if (s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)))
							.isOccupato()) {
						mangiaPezzo(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)));
					}
					s.scambiaCella(comando);
					return true;
				} else if (s.getCella(Cella.coordXinInt(comando.charAt(0)), Cella.coordYinInt(comando.charAt(1)))
						.getPezzoCorrente().isEnPassant(comando, s) && isMossaEnPassant(comando)) {
					String c = String.valueOf(comando.charAt(3)) + String.valueOf(comando.charAt(1) - 46) + ' '
							+ String.valueOf(comando.charAt(3)) + String.valueOf(comando.charAt(1));
					if (c.equals(t.getGiocatoreInAttesa()
							.getMossaGiocata(t.getGiocatoreInAttesa().getNumeroMosseGiocate() - 1))) {
						s.scambiaCella(comando);
						mangiaPezzo(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(1)));
						return true;
					} else {
						System.out.println("Mossa illegale");
						return false;
					}
				} else {
					System.out.println("Mossa illegale");
					return false;
				}
			} else if (t.getGiocatoreInTurno().getColore() == Colore.nero
					&& s.getCella(Cella.coordXinInt(comando.charAt(0)), Cella.coordYinInt(comando.charAt(1)))
							.getPezzoCorrente().getColore() == Colore.nero) {
				if (s.getCella(Cella.coordXinInt(comando.charAt(0)), Cella.coordYinInt(comando.charAt(1)))
						.getPezzoCorrente().isMossaValidaNero(comando, s)) {
					if (s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)))
							.isOccupato()) {
						mangiaPezzo(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)));
					}
					s.scambiaCella(comando);
					return true;
					// b4 a3
					// a2 a4
				} else if (s.getCella(Cella.coordXinInt(comando.charAt(0)), Cella.coordYinInt(comando.charAt(1)))
						.getPezzoCorrente().isEnPassant(comando, s) && isMossaEnPassant(comando)) {
					String c = String.valueOf(comando.charAt(3)) + String.valueOf(comando.charAt(1) - 50) + ' '
							+ String.valueOf(comando.charAt(3)) + String.valueOf(comando.charAt(1));
					if (c.equals(t.getGiocatoreInAttesa()
							.getMossaGiocata(t.getGiocatoreInAttesa().getNumeroMosseGiocate() - 1))) {
						s.scambiaCella(comando);
						mangiaPezzo(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(1)));
						return true;
					} else {
						System.out.println("Mossa illegale");
						return false;
					}

				} else {
					System.out.println("Mossa illegale");
					return false;
				}
			} else {
				System.out.println("Mossa illegale");
				return false;
			}
		} else {
			System.out.println("Mossa illegale");
			return false;
		}
	}

	/**
	 * Mangia il pezzo avversario. Aggiunge il pezzo mangiato alla lista dei pezzi
	 * mangiati del giocatore in attesa.
	 * 
	 * @param x
	 * @param y
	 */
	public final void mangiaPezzo(int x, int y) {
		t.getGiocatoreInAttesa().addPezziCatturati(s.getCella(x, y).getPezzoCorrente());
		s.getCella(x, y).rimuoviPezzoCorrente();

	}
}
