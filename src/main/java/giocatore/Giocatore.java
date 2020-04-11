package giocatore;

import java.util.ArrayList;
import java.util.Scanner;

import pedine.Pezzo;

/**
 * La classe Giocatore serve per identificare il giocatore che sta giocando.
 * Ci possono essere solo due giocatori.
 * Al suo interno sono presenti tutte le informazioni riguardo i giocatori: nome, colore, pezzi morti e mosse giocate.
 * Il primo è bianco e il secondo nero: al giocatore che fa la prima mossa sarà assegnato il colore bianco.
 *
 */
public class Giocatore {

	private String nome;
	private boolean colore;
	private ArrayList<Pezzo> pezziMorti;
	private ArrayList<String> mosseGiocate;

	/**
	 * Costruttore della classe Giocatore che assegna al campo colore il nome del colore dei pezzi.
	 * Il giocatore che gioca il primo turno è bianco, quello successivo è nero.
	 * Nel vettore "pezziMorti" ci saranno conservati tutti i pezzi morti del giocatore.
	 * Nel vettore "mosseGiocate" ci sarà la storia delle mosse giocate dal giocatore.
	 * @param colore
	 */
	public Giocatore(boolean colore) {

		this.colore = colore;
		try (Scanner scanner = new Scanner(System.in)) {
			String nomecolore;
			if (colore) {
				nomecolore = "Bianche";
			} else {
					nomecolore = "Nere";
			}
			System.out.println("Inserire il nome del giocatore con le pedine (" + nomecolore + ")");
			this.nome = scanner.nextLine();

		}

		pezziMorti = new ArrayList<Pezzo>();
		mosseGiocate = new ArrayList<String>();
	}
	/**
	 * Assegna il nome al campo nome del Giocatore.
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Assegna il colore al campo colore del Giocatore.
	 * @param colore
	 */
	public void setColore(boolean colore) {
		this.colore = colore;
	}
	/**
	 * Restituisce il nome del giocatore.
	 * @return nome del giocatore.
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Restituisce il colore dei pezzi del giocatore.
	 * @return
	 */
	public boolean getColore() {
		return colore;
	}
	/**
	 * Restituisce i pezzi morti del giocatore.
	 * @return vettore dei pezzi morti.
	 */
	public ArrayList<Pezzo> getPezziMorti() {
		return pezziMorti;
	}
	/**
	 * Restituisce la storia delle mosse giocate dal giocatore.
	 * @return vettore delle mosse giocate.
	 */
	public ArrayList<String> getMosseGiocate() {
		return mosseGiocate;
	}
	/**
	 * Stampa a video l'elenco dei pezzi morti del giocatore.
	 */
	public void stampaPezziMorti() {
		System.out.println(pezziMorti.toString());
	}
	/**
	 * Stampa a video l'elenco delle mosse giocate del giocatore.
	 */
	public void stampaMosseGiocate() {
		System.out.println(mosseGiocate);
	}
	
}
