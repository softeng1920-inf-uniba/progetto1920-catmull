package scacchiera;

import pedine.Pezzo;

/**
 * La classe Cella contiene le informazioni riguardanti ogni singola cella della
 * scacchiera, definendola con gli attributi: x indica la coordinata delle
 * ascisse, y indica la coordinata delle ordinate, occupato indica lo stato
 * della cella e pezzoCorrente da informazioni riguardo al pezzo contenuto nella
 * cella. La classe Cella e' di tipo ENTITY
 */
public final class Cella {
    private int x;	// TODO: Aggiungere javadoc o commenti
    private int y;
    private boolean occupato; // TODO: lo stato occupato si puo' ottenere dalla cella, se contiene un pezzo o
			      // meno. Si potrebbe pensare di eliminare questa variabile
    private Pezzo pezzoCorrente;

    /**
     * TODO: Migliorare javadoc
     * @param x
     * @param y
     * @param pezzoCorrente
     */
    public Cella(final int x, final int y, final Pezzo pezzoCorrente) {
	this.x = x;
	this.y = y;
	this.pezzoCorrente = pezzoCorrente;
	if (pezzoCorrente != null) {
	    this.occupato = true;
	}
    }

    // ---------Metodi di setting---------

    /**
     * Modifica la variabile occupato
     * TODO: Migliorare javadoc
     * @param occupato
     */
    public void setOccupato(final boolean occupato) {
	this.occupato = occupato;
    }

    /**
     * Modifica il tipo di pezzo che la cella contiene
     * TODO: Migliorare javadoc
     * @param pezzoCorrente
     */
    void setPezzoCorrente(final Pezzo pezzoCorrente) {
	this.pezzoCorrente = pezzoCorrente;
	if (pezzoCorrente != null) {
	    this.occupato = true;
	}
    }

    /**
     * Modifica la coordinata X
     * TODO: Migliorare javadoc
     * @param newx
     */
    void setX(final int newx) {
	x = newx;
    }

    /**
     * Modifica la coordinata Y
     * TODO: Migliorare javadoc
     * @param newy
     */
    void setY(final int newy) {
	y = newy;
    }

    // --------Metodi di Get--------

    /**
     * Restituisce la coordinata X
     * @return valore della coordinata X per la cella corrente
     */
    public int getX() {
	return x;
    }

    /**
     * Restituisce la coordinata Y
     * @return valore della coordinata Y per la cella corrente
     */
    public int getY() {
	return y;
    }

    /**
     * Restituisce un valore booleano che indica se la cella è occupata da qualche
     * pezzo o meno.
     * 
     * @return true se la cella è occupata, false se vuota.
     */
    public boolean isOccupato() {
	return occupato;
    }

    /**
     * Restituisce il pezzo contenuto nella cella
     * @return pezzo nella cella
     */
    public Pezzo getPezzoCorrente() {
	return pezzoCorrente;
    }

    // ------Metodi per aggiugere o togliere pezzi dalla scacchiera

    /**
     * Aggiunge un pezzo nella scacchiera
     * @param nuovoPezzo
     */
    public void aggiungiPezzo(final Pezzo nuovoPezzo) {

	setPezzoCorrente(nuovoPezzo);
	setOccupato(true);

    }

    /**
     * Rimuove il pezzo dalla cella corrente
     */
    public void rimuoviPezzoCorrente() {
	setOccupato(false);
	setPezzoCorrente(null);
    }

    /**
     * @param coordX Carattere in minuscolo da convertire in intero
     * @return Valore necessario per la scacchiera compreso fra 0 e 7
     */
    public static int coordXinInt(final char coordX) {
	return coordX - 97;
    }

    /**
     * Restituisce il corrispondente valore della scacchiera
     *
     * @param coordY Compreso fra 1 e 8
     * @return Coordinata convertita in intero, compreso tra 0 e 7
     */
    public static int coordYinInt(final char coordY) {

	return Math.abs((coordY - 49) - 7);
    }

    /**
     * Trasforma un valore da 0 a 7 (indici della matrice) in valori in termini di
     * traversa della scacchiera (carattere tra a e h)
     * 
     * @param coordX intero da convertire in carattere
     * @return Valore necessario per la scacchiera compreso fra a e h
     */

    public static char coordXinChar(final int coordX) {
	return (char) (coordX + 97);
    }

    /**
     * Trasforma un valore da 0 a 7 (indici della matrice) in valori in termini di
     * righe della scacchiera (carattere tra 1 e 8)
     * 
     * @param coordX intero da convertire in carattere
     * @return Valore necessario per la scacchiera compreso fra 1 e 8
     */
    public static char coordYinChar(final int coordY) {
	return (char) (Math.abs((coordY - 8)) + 48);
    }

    /**
     * Converte la coordinata X di partenza data in input in intero.
     *
     * @param m mossa in notazione estesa
     * @return Valore necessario per la scacchiera compreso fra a e h
     */
    public static int startX(String m) {
	return Cella.coordXinInt(m.charAt(0));
    }

    /**
     * Converte la coordinata Y di partenza data in input in intero.
     *
     * @param m mossa in notazione estesa
     * @return Valore necessario per la scacchiera compreso fra 0 e 7
     */
    public static int startY(String m) {
	return Cella.coordYinInt(m.charAt(1));
    }

    /**
     * Converte la coordinata X di destinazione data in input in intero.
     *
     * @param m mossa in notazione estesa
     * @return Valore necessario per la scacchiera compreso fra a e h
     */
    public static int endX(String m) {
	return Cella.coordXinInt(m.charAt(3));
    }

    /**
     * Converte la coordinata Y di destinazione data in input in intero.
     *
     * @param m mossa in notazione estesa
     * @return Valore necessario per la scacchiera compreso fra 0 e 7
     */
    public static int endY(String m) {
	return Cella.coordYinInt(m.charAt(4));
    }

}
