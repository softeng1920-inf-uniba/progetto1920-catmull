package it.uniba.main;

import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
/**
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
		Menu m = new Menu();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\u265A" + "\u265B" + "  Menu principale " + "\u2655" + "\u2656" + " \n");
		System.out.println("Digitare help per visualizzare la lista dei comandi");

		while (true) {
			String nomeMenu = br.readLine();

			if (nomeMenu.equalsIgnoreCase(m.help().getNome())) {
				c.mostrareElencoComandiMenu();
			} else if (nomeMenu.equalsIgnoreCase(m.board().getNome())) {
				c.stampaScacchiera();
			} else if (nomeMenu.equalsIgnoreCase(m.quit().getNome())) {
				c.chiudiGioco();
			} else if (nomeMenu.equalsIgnoreCase(m.play().getNome())) {

				c.inizializzaPartita();
			} else {
				System.out.println("Comando non riconosciuto");

			}
		}

	}
}
