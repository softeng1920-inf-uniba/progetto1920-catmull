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
		Controller controller = Controller.getInstance();
		InterfacciaUtente interfaccia = InterfacciaUtente.getInstance();
		boolean play = false;
		Menu menuGioco = Menu.getInstance();

		interfaccia.stampaIntro();
		interfaccia.stampaMenu();
		while (!play) {
			String nomeMenu = interfaccia.acquisireComando();
			if (nomeMenu != null) {
				if (nomeMenu.equalsIgnoreCase(menuGioco.help().getNome())) {
					interfaccia.mostrareElencoComandiMenu();
				} else if (nomeMenu.equalsIgnoreCase(menuGioco.board().getNome())) {
					interfaccia.stampaScacchiera();
				} else if (nomeMenu.equalsIgnoreCase(menuGioco.quit().getNome())) {
					if (interfaccia.utenteConfermaFinePartita()) {
						break;
					} else {
						interfaccia.stampaMenu();
					}
				} else if (nomeMenu.equalsIgnoreCase(menuGioco.play().getNome())) {
					interfaccia.stampaNuovaPartita();
					controller.playGame();
					play = true;
				} else {
					interfaccia.stampaComandoErrato();
				}
			} else {
				break;
			}
		}

	}
}
