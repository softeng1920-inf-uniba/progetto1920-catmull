package it.uniba.main;

import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 * La classe AppMain e' di tipo BOUNDARY
 * 
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
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

		Controller c = new Controller();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Menu.newMenu();
		Stampa.stampaIntro();
		Stampa.stampaMenu();

		while (true) {
			String nomeMenu = br.readLine();

			if (nomeMenu.equalsIgnoreCase(Menu.help().getNome())) {
				Stampa.mostrareElencoComandiMenu();
			} else if (nomeMenu.equalsIgnoreCase(Menu.board().getNome())) {
				Stampa.stampaScacchiera();
			} else if (nomeMenu.equalsIgnoreCase(Menu.quit().getNome())) {
				c.chiudiGioco();
			} else if (nomeMenu.equalsIgnoreCase(Menu.play().getNome())) {
				Stampa.stampaNuovaPartita();
				c.playGame();
			} else {
				Stampa.stampaComandoErrato();
			}
		}

	}
}
