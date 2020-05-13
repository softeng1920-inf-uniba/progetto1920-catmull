package gioco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pedine.Pezzo;

/**
 * La classe Giocatore serve per identificare il giocatore che sta giocando. Ci
 * possono essere solo due giocatori e, al suo interno sono presenti tutte le
 * informazioni riguardo i giocatori: nome, colore, pezzi catturati e mosse
 * giocate. Il primo giocatore e' bianco e il secondo nero: al giocatore che fa
 * la prima mossa sara' assegnato il colore bianco. 
 * La classe Giocatore e' di tipo ENTITY.
 */

public class Giocatore {

    private String nome;
    private Colore colore;
    private ArrayList<Pezzo> pezziCatturati;
    private ArrayList<String> mosseGiocate;

    /**
     * Costruttore della classe Giocatore
     *
     * Nel vettore "pezziCatturati" verranno conservati tutti i pezzi del giocatore
     * attuale catturati dal giocatore avversario.
     *
     * Nel vettore "mosseGiocate" ci sara' la storia delle mosse giocate dal
     * giocatore.
     *
     *
     * @param colore
     */
    public Giocatore(Colore colore) {

	setColore(colore);

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Stampa.stampaInserireGiocatore(colore);

	String nome = "";

	try {
	    while (nome.equals("")) {
		nome = br.readLine();
		if (nome.equals("")) {
		    Stampa.stampaInserireGiocatore(colore);
		}
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}

	setNome(nome);

	pezziCatturati = new ArrayList<Pezzo>();
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
     * @return nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * Restituisce il colore dei pezzi del giocatore.
     *
     * @return colore
     */
    public Colore getColore() {
	return colore;
    }

    /**
     * Aggiunta la pedina p mangiata dal giocatore avversario al vettore
     * pezziCatturati
     *
     * @param p
     */
    public void addPezziCatturati(Pezzo p) {
	pezziCatturati.add(p);
    }

    /**
     * Funzione che restituisce le pedine catturate dal giocatore avversario
     *
     * @return pezziCatturati
     *
     */

    public ArrayList<Pezzo> getPezziCatturati() {
	return pezziCatturati;
    }

    /**
     * Restituisce un valore booleano che individua se sono stati catturati dei
     * pezzi dal giocatore avversario.
     *
     * @return boolean
     */
    public boolean isEmptyPezziCatturati() {
	return pezziCatturati.isEmpty();
    }

    /**
     * Aggiunge la mossa corrente alla lista mosseGiocate.
     *
     * @param mossa
     */
    public void setMosseGiocate(String mossa) {
	mosseGiocate.add(mossa);
    }

    /**
     * Restituisce la storia delle mosse giocate dal giocatore.
     *
     * @return lista delle mosse giocate.
     */
    public ArrayList<String> getMosseGiocate() {
	return mosseGiocate;
    }

    /**
     * Restituisce l'i-esima mossa giocata dal giocatore.
     *
     * @param i
     * @return Stringa che contiene la mossa giocata.
     */
    public String getMossaGiocata(int i) {
	return mosseGiocate.get(i);
    }

    /**
     * Restituisce il numero delle mosse giocate dal giocatore
     *
     * @return dimensione della lista delle mosse giocate
     */
    public int getNumeroMosseGiocate() {
	return mosseGiocate.size();
    }
    
    public void removePezzoCatturato() {
    	pezziCatturati.remove(pezziCatturati.size()-1);
    }
}
