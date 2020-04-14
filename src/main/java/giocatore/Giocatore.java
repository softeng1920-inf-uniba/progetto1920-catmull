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
 * Il primo � bianco e il secondo nero: al giocatore che fa la prima mossa sar�
 * assegnato il colore bianco.
 *
 */
public class Giocatore {

	private String nome;
	private Colore colore;
	ArrayList<Pezzo> pedineMangiate;
	ArrayList<String> mosseGiocate;

	/**
	 * Costruttore della classe Giocatore che assegna al campo colore il nome del
	 * colore dei pezzi. Il giocatore che gioca il primo turno � bianco, quello
	 * successivo � nero. Nel vettore "pezziMorti" ci saranno conservati tutti i
	 * pezzi morti del giocatore. Nel vettore "mosseGiocate" ci sar� la storia delle
	 * mosse giocate dal giocatore.
	 * 
	 * @param colore
	 */
	public Giocatore(Colore colore) {

		setColore(colore);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Inserire il nome del giocatore con le pedine (" + colore + ")");
		String nome = "";

		try {
			nome = br.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

		setNome(nome);

		pedineMangiate = new ArrayList<Pezzo>();
		mosseGiocate = new ArrayList<String>();

	}

	/**
	 * Assegna il nome al campo nome del Giocatore.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Assegna il colore al campo colore del Giocatore.
	 * 
	 * @param colore
	 */
	public void setColore(Colore colore) {
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

	/**
	 * Aggiunta la pedina p mangiata dal giocatore avversarsio al vettore pedine
	 * mangiate
	 * 
	 * @param p
	 */
	public void aggiungiPedinaMangiata(Pezzo p) {
		pedineMangiate.add(p);
	}

	/**
	 * Funzione che restituisce le pedine mangiate dal giocatore avversario
	 *
	 * @return pedineMangiate
	 *
	 */

	public ArrayList<Pezzo> getPedineMangiate() {
		return pedineMangiate;
	}

	public void stampaPedineMangiate() {

		System.out.println(nome.toUpperCase() + " ecco le pedine che il tuo avversario ti ha mangiato:");
		for (Pezzo pezzoMangiato : getPedineMangiate()) {
			System.out.println(pezzoMangiato);
		}
	}

	public boolean isVuotoPedineMangiate() {
		return pedineMangiate.isEmpty();
	}
}
