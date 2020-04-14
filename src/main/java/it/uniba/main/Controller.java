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

	public Controller() {

		s = new Scacchiera();

	}

	/**
	 * funzione che consente di chiudere il gioco e lasciare il controllo al sistema operativo
	 */
	final void chiudiGioco()
	{
		System.exit(0);
	}
	
	final void inizializzaPartita() {

		t = new Turno();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String comando = "";

		s.inizializzaScacchiera();

		System.out.println("Puoi ritornare al menu principale digitando il comando 'exit'. \n");

		while (true) {

			System.out.println("E' il turno di " + t.getGiocatoreInTurno().getNome() + " con le pedine di colore "
					+ t.getGiocatoreInTurno().getColore() + "\n");
			System.out.println("Inserisci una mossa nella notazione algebrica\n");

			try {
				comando = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch (comando) {
			case "exit":
				return;
			case "board":
				System.out.println("Questa è una scacchiera");

				break;
			default:
				if (isNotazioneAlgebrica(comando)) {
					// se è una mossa consentita...
					System.out.println("OK");
					t.cambioTurno();
					// Altrimenti stampa mossa illegale
				} else if (!isComandoValido(comando)) {
					System.out.println("Comando non corretto. Riprova \n");
					break;
				}

			}

		}

	}

	/**
	 * La seguente funzione riconosce se il comando inserito è un comando scritto
	 * sottoforma di notazione algebrica Il seguente comando può essere anche una
	 * mossa non valida
	 * 
	 * @param comando
	 * @return
	 */
	private boolean isComandoValido(final String comando) {
		for (String s : Costanti.getComandi()) {
			if (s.equals(comando)) {
				return true;
			}
		}
		return false;
	}

	private boolean isNotazioneAlgebrica(final String mossa) {

		String regex = "[a-h][1-8]\\ [a-h][1-8]";
		return mossa.matches(regex);
	}
}
