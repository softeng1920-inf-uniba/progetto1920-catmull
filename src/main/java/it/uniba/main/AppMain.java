package it.uniba.main;

import java.io.IOException;
import java.io.PrintStream;
import gioco.InterfacciaUtente;
import gioco.Menu;

/**
 * Classe principale del gioco Scacchi, contenente il metodo di inizio del programma.
 * <br>
 * La classe AppMain e' di tipo &lt;&lt;BOUNDARY&gt;&gt;
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
