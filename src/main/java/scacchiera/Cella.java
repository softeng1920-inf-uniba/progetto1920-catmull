package scacchiera;

import gioco.Comando;
import scacchiera.pedine.Pezzo;

/**
 * La classe Cella contiene le informazioni riguardanti ogni singola cella della
 * scacchiera, definendola con gli attributi: x indica la coordinata delle
 * ascisse, y indica la coordinata delle ordinate, occupato indica lo stato
 * della cella e pezzoCorrente da informazioni riguardo al pezzo contenuto nella
 * cella. La classe Cella e' di tipo ENTITY
 */
public final class Cella {
    private int x;
    private int y;
    private boolean occupato;
    private Pezzo pezzoCorrente;

    /**
     * Crea una nuova cella, assegnandogli delle coordinate e settando un pezzo se
     * presente nella cella.
     * 
     * @param coordX - coordinata X per la cella
     * @param coordY - coordinata Y per la cella
     * @param pC     - Pezzo nella cella da settare
     */
    public Cella(final int coordX, final int coordY, final Pezzo pC) {
	this.x = coordX;
	this.y = coordY;
	this.pezzoCorrente = pC;
	if (pC != null) {
	    this.occupato = true;
	}
    }

    // ---------Metodi di setting---------

    /**
     * Modifica la variabile occupato
     * 
     * @param occ Valore booleano da impostare sulla cella attuale, che indica se è
     *            occupata o meno
     */
    public void setOccupato(final boolean occ) {
	this.occupato = occ;
    }

    /**
     * Modifica il tipo di pezzo che la cella contiene
     * 
     * @param pC Pezzo corrente che la cella attuale deve contenere
     */
    void setPezzoCorrente(final Pezzo pC) {
	this.pezzoCorrente = pC;
	if (pC != null) {
	    this.occupato = true;
	}
    }

    /**
     * Imposta una nuova coordinata X per la cella corrente
     * 
     * @param newx valore intero indicante la nuova coordinata sull'asse X della
     *             cella
     */
    void setX(final int newx) {
	x = newx;
    }

    /**
     * Imposta una nuova coordinata Y per la cella corrente
     * 
     * @param newy valore intero indicante la nuova coordinata sull'asse Y della
     *             cella
     */
    void setY(final int newy) {
	y = newy;
    }

    // --------Metodi di Get--------

    /**
     * Restituisce la coordinata X per la cella corrente
     * 
     * @return valore intero della coordinata X
     */
    public int getX() {
	return x;
    }

    /**
     * Restituisce la coordinata Y della cella corrente
     * 
     * @return valore della coordinata Y
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
     * Restituisce il pezzo contenuto nella cella attuale
     * 
     * @return pezzo nella cella corrente
     */
    public Pezzo getPezzoCorrente() {
	return pezzoCorrente;
    }

    // ------Metodi per aggiugere o togliere pezzi dalla scacchiera

    /**
     * Aggiunge un pezzo nella scacchiera
     * 
     * @param nuovoPezzo Pezzo da impostare all'interno della cella corrente
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
     * Preso un carattere della mossa, restituisce il corrispondente valore in
     * intero, necessario per la matrice TODO: ricontrollare se effettivamente è
     * così
     * 
     * @param coordX Carattere in minuscolo da convertire in intero
     * @return Valore necessario per la scacchiera compreso fra 0 e 7
     */
    public static int coordXinInt(final char coordX) {
	return coordX - 97;
    }

    /**
     * Preso un carattere della mossa, restituisce il corrispondente valore in
     * intero, necessario per la matrice
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
    public static int startX(final String m) {
	return Cella.coordXinInt(m.charAt(Comando.COLONNA_PARTENZA_MOSSA_NE));
    }

    /**
     * Converte la coordinata Y di partenza data in input in intero.
     *
     * @param m mossa in notazione estesa
     * @return Valore necessario per la scacchiera compreso fra 0 e 7
     */
    public static int startY(final String m) {
	return Cella.coordYinInt(m.charAt(Comando.TRAVERSA_PARTENZA_MOSSA_NE));
    }

    /**
     * Converte la coordinata X di destinazione data in input in intero.
     *
     * @param m mossa in notazione estesa
     * @return Valore necessario per la scacchiera compreso fra a e h
     */
    public static int endX(final String m) {
	return Cella.coordXinInt(m.charAt(Comando.COLONNA_DESTINAZIONE_MOSSA_NE));
    }

    /**
     * Converte la coordinata Y di destinazione data in input in intero.
     *
     * @param m mossa in notazione estesa
     * @return Valore necessario per la scacchiera compreso fra 0 e 7
     */
    public static int endY(final String m) {
	return Cella.coordYinInt(m.charAt(Comando.TRAVERSA_DESTINAZIONE_MOSSA_NE));
    }

}
