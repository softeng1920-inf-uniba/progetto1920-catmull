/**
 * 
 */
package it.uniba.main;

import scacchiera.Scacchiera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import gioco.Turno;

/**
 * Classe che gestisce le varie funzionalità del gioco.
 */
public class Controller {

	private Scacchiera s;
	private Turno t;
	private Menu menu;

	public Controller() {
		menu = new Menu();
		s = new Scacchiera();

	}

	final void inizializzaPartita() {

		t = new Turno();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String comando = "";
		
		s.inizializzaScacchiera();
		System.out.println("Puoi ritornare al menu principale digitando il comando 'Menu'. \n");

		while (true) {

			System.out.println("\nE' il turno di " + t.getGiocatoreInTurno().getNome() + " con le pedine di colore "
					+ t.getGiocatoreInTurno().getColore() + "");
			System.out.println("Inserisci una mossa nella notazione algebrica");
			
			try {
				comando = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (comando.equalsIgnoreCase(menu.help().getNome())) {
				// METODO STAMPA COMANDI
			} else if (comando.equalsIgnoreCase(menu.board().getNome())) {
				// METODO STAMPA SCACCHIERA
			} else if (comando.equalsIgnoreCase(menu.back().getNome())) {
				System.out.println("--- Menu principale --- \n");
				return;
			} else if (comando.equalsIgnoreCase(menu.history().getNome())) {
				stampaMosseGiocate();
			} else if (comando.equalsIgnoreCase(menu.captures().getNome())) {
				visualizzareCatture();
			}

			if (isNotazioneAlgebrica(comando)) {
				t.getGiocatoreInTurno().setMosseGiocate(comando);
				t.cambioTurno();
			} else if (!isComandoValido(comando)) {
				System.out.println("Comando non corretto. Riprova");
			}
		}

	}

	/**
	 * La seguente funzione riconosce se il comando inserito è scritto
	 * sottoforma di notazione algebrica. Il seguente comando può essere anche una
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
				|| comando.equalsIgnoreCase(menu.history().getNome())) {
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
}
