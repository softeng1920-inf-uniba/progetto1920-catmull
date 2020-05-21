package it.uniba.main;

import java.io.IOException;
import java.io.PrintStream;
import gioco.InterfacciaUtente;
import gioco.Menu;

/**
 * La classe AppMain e' di tipo BOUNDARY The main class for the project. It must
 * be customized to meet the project assignment specifications.
 *
 * <b>DO NOT RENAME</b>
 */
public final class AppMain {

	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {
	}

	/**
	 * * This is the main entry of the application.
	 *
	 * @param args The command-line arguments.
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void main(final String[] args) throws IOException {

		System.setOut(new PrintStream(System.out, false, "UTF-8"));
		Controller.newController();
		boolean play = false;
		Menu.newMenu();
		InterfacciaUtente.stampaIntro();
		InterfacciaUtente.stampaMenu();
		InterfacciaUtente.stampaInserireComando();
		while (!play) {
			String comando = InterfacciaUtente.acquisireComando();
			if (comando != null) {
				if (comando.equalsIgnoreCase(Menu.help().getNome())) {
					InterfacciaUtente.mostrareElencoComandiMenu();
					InterfacciaUtente.stampaInserireComando();
				} else if (comando.equalsIgnoreCase(Menu.board().getNome())) {
					InterfacciaUtente.stampaScacchiera();
					InterfacciaUtente.stampaInserireComando();
				} else if (comando.equalsIgnoreCase(Menu.quit().getNome())) {
					if (InterfacciaUtente.utenteConfermaFinePartita()) {
						break;
					} else {
						InterfacciaUtente.stampaMenu();
					}
				} else if (comando.equalsIgnoreCase(Menu.play().getNome())) {
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
