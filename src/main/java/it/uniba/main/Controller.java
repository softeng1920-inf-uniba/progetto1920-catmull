/**
 * 
 */
package it.uniba.main;

import scacchiera.Scacchiera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
				// METODO TORNA AL MENU PRINCIPALE
			} else if (comando.equalsIgnoreCase(menu.history().getNome())) {
				System.out.println("--- Menu principale --- \n");
				return;
			} else if (comando.equalsIgnoreCase(menu.captures().getNome())) {
				visualizzareCatture();
			}

			if (isNotazioneAlgebrica(comando)) {
				t.cambioTurno();
			} else if (!isComandoValido(comando)) {
				System.out.println("Comando non corretto. Riprova");
			}
		}

	}

	/**
	 * La seguente funzione riconosce se il comando inserito è un comando scritto
	 * sottoforma di notazione algebrica Il seguente comando può essere anche una
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
}
