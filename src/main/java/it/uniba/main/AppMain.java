package it.uniba.main;

import java.io.IOException;
import java.io.PrintStream;

import gioco.InterfacciaUtente;
import gioco.Menu;

/**
 * Classe principale del gioco Scacchi, contenente il metodo di inizio del programma.
 * <br>
 * La classe AppMain e' di tipo BOUNDARY
 */
public final class AppMain {

	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {
	}

	/**
	 * Questa e' la classe di lancio del software
	 *
	 * @param  args String indicating The command-line arguments.
	 *
	 * @throws  IOException se la stringa letta da Buffer e' vuota
	 */
	public static void main(final String[] args) throws IOException {

		System.setOut(new PrintStream(System.out, false, "UTF-8"));
		Controller.newController();
		boolean play = false;
		Menu.newMenu();
		InterfacciaUtente.stampaIntro();
		InterfacciaUtente.stampaMenu();
		while (!play) {
			String nomeMenu = InterfacciaUtente.acquisireComando();
			if (nomeMenu != null) {
				if (nomeMenu.equalsIgnoreCase(Menu.help().getNome())) {
					InterfacciaUtente.mostrareElencoComandiMenu();
				} else if (nomeMenu.equalsIgnoreCase(Menu.board().getNome())) {
					InterfacciaUtente.stampaScacchiera();
				} else if (nomeMenu.equalsIgnoreCase(Menu.quit().getNome())) {
					if (InterfacciaUtente.utenteConfermaFinePartita()) {
						break;
					} else {
						InterfacciaUtente.stampaMenu();
					}
				} else if (nomeMenu.equalsIgnoreCase(Menu.play().getNome())) {
					InterfacciaUtente.stampaNuovaPartita();
					Controller.playGame();
					play = true;
				} else {
					InterfacciaUtente.stampaComandoErrato();
				}
			} else {
				break;
			}
		}

	}
}
