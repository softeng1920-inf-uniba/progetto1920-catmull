package gioco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import pedine.Pezzo;

/**
 * La classe Giocatore serve per identificare il giocatore che sta giocando. Ci
 * possono essere solo due giocatori e, al suo interno sono presenti tutte le
 * informazioni riguardo i giocatori: nome, colore, pezzi catturati e mosse
 * giocate. Il primo giocatore e' bianco e il secondo nero: al giocatore che fa
 * la prima mossa sara' assegnato il colore bianco. La classe Giocatore e' di
 * tipo ENTITY.
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
     * @param c Colore del giocatore
     */
    public Giocatore(final Colore c) {

	setColore(c);

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
	Stampa.stampaInserireGiocatore(c);
	try {
	    while (this.nome == null || this.nome.equals("")) {
		this.nome = br.readLine();
		if (this.nome == null || this.nome.equals("")) {
		    Stampa.stampaInserireGiocatore(colore);
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

	pezziCatturati = new ArrayList<Pezzo>();
	mosseGiocate = new ArrayList<String>();
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
     * Assegna il colore al campo colore del Giocatore.
     *
     * @param c
     */
    public void setColore(final Colore c) {
	this.colore = c;
    }

    /**
     * Aggiunta la pedina p mangiata dal giocatore avversario al vettore
     * pezziCatturati
     *
     * @param p
     */
    public void addPezziCatturati(final Pezzo p) {
	pezziCatturati.add(p);
    }

    /**
     * Restituisce la lista di pezzi catturati
     * 
     * @return lista sotto forma di array dei pezzi catturati
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
    public void setMosseGiocate(final String mossa) {
	mosseGiocate.add(mossa);
    }

    /**
     * Restituisce l'i-esima mossa giocata dal giocatore.
     *
     * @param i
     * @return Stringa che contiene la mossa giocata.
     */
    public String getMossaGiocata(final int i) {
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

    /**
     * Rimuove un pezzo dalla lista dei pezzi catturati del giocatore
     */
    public void removePezzoCatturato() {
	pezziCatturati.remove(pezziCatturati.size() - 1);
    }
}
