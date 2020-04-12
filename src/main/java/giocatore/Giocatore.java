package giocatore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import it.uniba.main.Colore;
import pedine.Pezzo;

/**
 * La classe Giocatore serve per identificare il giocatore che sta giocando. Ci
 * possono essere solo due giocatori. Al suo interno sono presenti tutte le
 * informazioni riguardo i giocatori: nome, colore, pezzi morti e mosse giocate.
 * Il primo è bianco e il secondo nero: al giocatore che fa la prima mossa sarà
 * assegnato il colore bianco.
 *
 */
public class Giocatore {

	private String nome;
	private Colore colore;
	private ArrayList<Pezzo> pezziMorti;
	private ArrayList<String> mosseGiocate;

	/**
	 * Costruttore della classe Giocatore che assegna al campo colore il nome del
	 * colore dei pezzi. Il giocatore che gioca il primo turno è bianco, quello
	 * successivo è nero. Nel vettore "pezziMorti" ci saranno conservati tutti i
	 * pezzi morti del giocatore. Nel vettore "mosseGiocate" ci sarà la storia delle
	 * mosse giocate dal giocatore.
	 * 
	 * @param colore
	 */
	public Giocatore(final Colore colore) {

		setColore(colore);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Inserire il nome del giocatore con le pedine (" + colore + ")");
		String nome;

		try {

			nome = br.readLine();
			setNome(nome);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// pezziMorti = new ArrayList<Pezzo>();
		// mosseGiocate = new ArrayList<String>();

	}

	/**
	 * Assegna il nome al campo nome del Giocatore. S
	 * 
	 * @param nome
	 */
	public void setNome(final String nome) {
		this.nome = nome;
	}

	/**
	 * Assegna il colore al campo colore del Giocatore.
	 * 
	 * @param colore
	 */
	public void setColore(final Colore colore) {
		this.colore = colore;
	}

	/**
	 * Restituisce il nome del giocatore.
	 * 
	 * @return nome del giocatore.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce il colore dei pezzi del giocatore.
	 * 
	 * @return
	 */
	public Colore getColore() {
		return colore;
	}

}
