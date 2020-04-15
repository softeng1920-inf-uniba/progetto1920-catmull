package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

		Controller c = new Controller();
		Menu m = new Menu();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


  		System.out.println("Benvenuto nel gioco degli scacchi. \n");
    	System.out.println("--- Menu principale --- \n");

			while (true) {
				String nomeMenu = br.readLine();
		
				if (nomeMenu.equalsIgnoreCase(m.help().getNome())) {
					//METODO STAMPA COMANDI
				} else if (nomeMenu.equalsIgnoreCase(m.board().getNome())) {
					//METODO STAMPA SCACCHIERA
				} else if (nomeMenu.equalsIgnoreCase(m.quit().getNome())) {
					//METODO CHIUDI IL GIOCO
				} else if (nomeMenu.equalsIgnoreCase(m.play().getNome())) {
					c.inizializzaPartita();
				} else {
					System.out.println("Comando non riconosciuto");
				}
			}
    
	}
}
