/**
 *
 */
package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import gioco.Turno;
import scacchiera.Scacchiera;

/**
 * Classe che gestisce le varie funzionalita'  del gioco.
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
<<<<<<< HEAD
	 * Funzione che consente di chiudere il gioco e lasciare il controllo al sistema operativo
=======
	 * funzione che consente di chiudere il gioco e lasciare il controllo al sistema
	 * operativo
>>>>>>> Miglioramento interfaccia grafica
	 */
	final void chiudiGioco() {
		System.exit(0);
	}

	final void inizializzaPartita() {

		System.out.println("\n\u2022" + " Digita 'Menu' per tornare al menu principale.");
		System.out.println("\u2022" + " Digita 'Help' per visualizzare l'elenco dei comandi.");
		t = new Turno();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String comando = "";

		s.inizializzaScacchiera();

		while (true) {

			System.out.println("\nE' il turno di " + t.getGiocatoreInTurno().getNome() + " con le pedine di colore "
					+ t.getGiocatoreInTurno().getColore() + ".");
<<<<<<< HEAD
			System.out.println("Inserisci una mossa nella notazione algebrica:");
=======
			System.out.println(
					"-> Inserisci una mossa nella notazione algebrica (es. e1 e4); altrimenti digita una voce del menu.");
>>>>>>> Miglioramento interfaccia grafica

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
				System.out.println("\u265A" + "\u265B" + " Menu principale " + "\u2655" + "\u2656" + " \n");
				return;
			} else if (comando.equalsIgnoreCase(menu.history().getNome())) {

				stampaMosseGiocate();

			} else if (comando.equalsIgnoreCase(menu.captures().getNome())) {

			} else if (comando.equalsIgnoreCase(menu.quit().getNome())) {
				chiudiGioco();
			}

			if (isNotazioneAlgebrica(comando)) {
				t.getGiocatoreInTurno().setMosseGiocate(comando);
				t.cambioTurno();
			} else if (!isComandoValido(comando)) {

				System.out.println("Comando non corretto. Riprova!");

			}
		}

	}

	/**
	 * La seguente funzione riconosce se il comando inserito e' un comando scritto
	 * sottoforma di notazione algebrica.Il seguente comando puo' essere anche una
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

	private boolean isNotazioneAlgebrica(final String mossa) {

		String regex = "[a-h][1-8]\\ [a-h][1-8]";
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
	 * La fusione avviene in modo alternato.
	 * Permette di avere una visione completa delle mosse giocate totali.
	 * @return ArrayList di stringhe.
	 */
	private ArrayList<String> fusioneListe() {
		int dimensione = t.getGiocatoreInAttesa().getNumeroMosseGiocate() + t.getGiocatoreInTurno().getNumeroMosseGiocate();
		ArrayList<String> mosseGiocateTotali = new ArrayList<String>(dimensione);
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < t.getGiocatoreInTurno().getNumeroMosseGiocate() && j < t.getGiocatoreInAttesa().getNumeroMosseGiocate()) {
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
		int dimensione = t.getGiocatoreInAttesa().getNumeroMosseGiocate() + t.getGiocatoreInTurno().getNumeroMosseGiocate();
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
	 * Metodo che permette la visualizzazione dell 'elenco comandi del menu di
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
}
