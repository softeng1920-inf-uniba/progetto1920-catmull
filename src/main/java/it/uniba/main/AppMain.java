package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

		Controller c = new Controller();
		Menu m = new Menu();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String nomeMenu = br.readLine();
		
				if (nomeMenu.equals(m.help().getNome())) {
					//METODO STAMPA COMANDI
				} else if (nomeMenu.equals(m.board().getNome())) {
					//METODO STAMPA SCACCHIERA
				} else if (nomeMenu.equals(m.quit().getNome())) {
					//METODO CHIUDI IL GIOCO
				} else if (nomeMenu.equals(m.play().getNome())) {
					//METODO INIZIA PARTITA
				} else {
					System.out.println("Comando non riconosciuto");
				}
			}


	}
}
