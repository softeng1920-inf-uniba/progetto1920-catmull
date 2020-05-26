package gioco;

import java.util.ArrayList;

import scacchiera.pedine.Pezzo;

/**
 * La classe Giocatore serve per identificare il giocatore che sta giocando
 * <p>
 * Ci possono essere solo due giocatori e, al suo interno sono presenti tutte le
 * informazioni riguardo i giocatori: nome, colore, pezzi catturati e mosse
 * giocate.
 * <p>
 * Il primo giocatore e' bianco e il secondo nero: al giocatore che fa la prima
 * mossa sara' assegnato il colore bianco.
 * <p>
 * La classe Giocatore e' di tipo &lt;&lt;ENTITY&gt;&gt;.
 */

public final class Giocatore {

    private String nome;
    private Colore colore;
    private ArrayList<Pezzo> pezziCatturati;
    private ArrayList<String> mosseGiocate;

    /**
     * Costruttore della classe Giocatore. <br>
     * Nell'attributo pezziCatturati vengono conservati tutti i pezzi del giocatore
     * corrente catturati dal giocatore avversario.
     * <p>
     * Nell'attributo mosseGiocate viene memorizzato lo storico delle mosse
     * effettuate dal giocatore corrente
     * <p>
     *
     * @param c Colore da assegnare al giocatore (bianco o nero)
     */
    public Giocatore(final Colore c) {

		setColore(c);
		pezziCatturati = new ArrayList<Pezzo>();
		mosseGiocate = new ArrayList<String>();
	}

    /**
     * Assegna il nome al campo nome del Giocatore
     *
     * @param n Stringa indicante il nome del giocatore
     */
    public void setNome(final String n) {
	this.nome = n;
    }

    /**
     * Assegna il colore al campo colore del Giocatore.
     *
     * @param c Colore indicante il colore assegnato al giocatore(bianco o nero)
     */
    public void setColore(final Colore c) {
	this.colore = c;
    }

    /**
     * Restituisce il nome del giocatore.
     *
     * @return nome del giocatore
     */
    public String getNome() {
	return nome;
    }

    /**
     * Restituisce il colore dei pezzi del giocatore.
     *
     * @return colore assegnato al giocatore
     */
    public Colore getColore() {
	return colore;
    }

    /**
     * Aggiunge la pedina catturata dal giocatore avversario all'attributo di classe
     * pezziCatturati
     *
     * @param p Pezzo catturato dal giocatore avversario
     */
    public void addPezziCatturati(final Pezzo p) {
	pezziCatturati.add(p);
    }

    /**
     * Funzione che restituisce le pedine catturate dal giocatore avversario
     *
     * @return Pezzi catturati dal giocatore avversario
     *
     */
    public ArrayList<Pezzo> getPezziCatturati() {
	return pezziCatturati;
    }

    /**
     * Restituisce un valore booleano che individua se sono stati catturati dei
     * pezzi dal giocatore avversario.
     *
     * @return true se il numero di pezzi catturati e' 0, false altrimenti
     */
    public boolean isEmptyPezziCatturati() {
	return pezziCatturati.isEmpty();
    }

    /**
     * Aggiunge la mossa corrente alla lista mosseGiocate.
     *
     * @param mossa Stringa da inserire nell'attributo primitivo di classe
     *              mosseGiocate
     */
    public void setMosseGiocate(final String mossa) {
	mosseGiocate.add(mossa);
    }

    /**
     * Restituisce l'i-esima mossa giocata dal giocatore.
     *
     * @param i intero indicante la mossa
     * @return i-esima mossa giocata dal giocatore
     */
    public String getMossaGiocata(final int i) {
	return mosseGiocate.get(i);
    }

    /**
     * Restituisce il numero delle mosse giocate dal giocatore
     *
     * @return Dimensione della lista delle mosse giocate
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

