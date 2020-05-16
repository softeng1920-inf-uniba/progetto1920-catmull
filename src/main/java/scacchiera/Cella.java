package scacchiera;

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
	private boolean occupato; // TODO: lo stato occupato si puo' ottenere dalla cella, se contiene un pezzo o
	// meno. Si potrebbe pensare di eliminare questa variabile
	private Pezzo pezzoCorrente;

	/** Costuttore */
	public Cella(final int x, final int y, final Pezzo pezzoCorrente) {
		this.x = x;
		this.y = y;
		this.pezzoCorrente = pezzoCorrente;
	}

	// ---------Metodi di setting---------

	/** Modifica la variabile occupato */
	public void setOccupato(final boolean occupato) {
		this.occupato = occupato;
	}

	/** Modifica il tipo di pezzo che contiene */
	void setPezzoCorrente(final Pezzo pezzoCorrente) {
		this.pezzoCorrente = pezzoCorrente;
		if (pezzoCorrente != null) {
			this.occupato = true;
		}
	}

//	/** Modifica la coordinata x */
//	void setX(final int newx) {
//		x = newx;
//	}
//
//	/** Modifica la coordinata y */
//	void setY(final int newy) {
//		y = newy;
//	}

	// --------Metodi di Get--------

	/** Restituisce la coordinata x */
	public int getX() {
		return x;
	}

	/** Restituisce la coordinata y */
	public int getY() {
		return y;
	}

	/** Restituisce la variabile occupato */
	public boolean isOccupato() {
		return occupato;
	}

	/** Restituisce il pezzo che contiene */
	public Pezzo getPezzoCorrente() {
		return pezzoCorrente;
	}

	// ------Metodi per aggiugere o togliere pezzi dalla scacchiera

	/** Aggiunge un pezzo alla cella */
	public void aggiungiPezzo(final Pezzo nuovoPezzo) {

		setPezzoCorrente(nuovoPezzo);
		setOccupato(true);

	}

	/** Rimuove il pezzo nella cella */
	public void rimuoviPezzoCorrente() {
		setOccupato(false);
		setPezzoCorrente(null);
	}

	/**
	 * @param char coordX Carattere in minuscolo da convertire in intero
	 * @return int Valore necessario per la scacchiera compreso fra 0 e 7
	 */
	public static int coordXinInt(final char coordX) {
		return coordX - 97;
	}

	/**
	 * Restituisce il corrispondente valore della scacchiera
	 *
	 * @param int coordY Compreso fra 1 e 8
	 * @return int Coordinata convertita in intero, compreso tra 0 e 7
	 */
	public static int coordYinInt(final char coordY) {

		return Math.abs((coordY - 49) - 7);
	}

	/**
	 * @param char coordX intero da convertire in carattere
	 * @return char Valore necessario per la scacchiera compreso fra a e h
	 */

	public static char coordXinChar(final int coordX) {

		return (char) (coordX + 97);

	}

	/**
	 *
	 * @param char coordY intero da convertire in carattere
	 *
	 * @return char Valore necessario per la scacchiera compreso fra 1 e 8
	 *
	 */

	public static char coordYinChar(final int coordY) {

		return (char) (Math.abs((coordY - 8)) + 48);

	}

	/**
	 * Converte la coordinata X di partenza data in input in intero.
	 *
	 * @param m
	 * @return
	 */
	public static int startX(String m) {
		return Cella.coordXinInt(m.charAt(0));
	}

	/**
	 * Converte la coordinata Y di partenza data in input in intero.
	 *
	 * @param m
	 * @return
	 */
	public static int startY(String m) {
		return Cella.coordYinInt(m.charAt(1));
	}

	/**
	 * Converte la coordinata X di partenza data in input in intero.
	 *
	 * @param m
	 * @return
	 */
	public static int endX(String m) {
		return Cella.coordXinInt(m.charAt(3));
	}

	/**
	 * Converte la coordinata Y di partenza data in input in intero.
	 *
	 * @param m
	 * @return
	 */
	public static int endY(String m) {
		return Cella.coordYinInt(m.charAt(4));
	}

}
