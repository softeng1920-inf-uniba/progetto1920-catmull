package scacchiera;

import gioco.Menu;
import scacchiera.pedine.Pezzo;

/**
 * La classe Cella contiene le informazioni riguardanti ogni singola cella della
 * scacchiera, definendola con gli attributi:
 * <ul>
 * 	<li> x indica l'ascissa della coordinata </li>
 * 	<li> y invece l'ordinata </li>
 * 	<li> occupato indica lo stato della cella</li>
 * 	<li> pezzoCorrente da informazioni riguardo al pezzo contenuto nella cella </li>
 * </ul>
 * La classe Cella e' di tipo ENTITY
 */
public final class Cella {
	private int x;
	private int y;
	private boolean occupato;
	private Pezzo pezzoCorrente;

	/**
	 * Crea una nuova cella, assegnandogli delle coordinate e settando un pezzo (se
	 * presente) nella cella.
	 *
	 * @param  coordX Intero che identifica la coordinata X per la cella
	 * @param  coordY Intero che identifica la coordinata Y per la cella
	 * @param  pC Pezzo all'interno della cella
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
	 * Modifica la variabile <b>occupato</b>
	 *
	 * @param  occ boolean che identifica se la cella deve essere impostata ad occupata o meno
	 *
	 */
	public void setOccupato(final boolean occ) {
		this.occupato = occ;
	}

	/**
	 * Modifica il tipo di pezzo che la cella contiene
	 *
	 * @param  pC Pezzo corrente che la cella attuale deve contenere
	 */
	void setPezzoCorrente(final Pezzo pC) {
		this.pezzoCorrente = pC;
		if (pC != null) {
			this.occupato = true;
		}
	}

	// --------Metodi di Get--------

	/**
	 * Restituisce la coordinata X della cella corrente
	 *
	 * @return  Valore intero della coordinata X
	 */
	public int getX() {
		return x;
	}

	/**
	 * Restituisce la coordinata Y della cella corrente
	 *
	 * @return  Valore della coordinata Y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Restituisce un valore booleano che indica se la cella e' occupata da qualche
	 * pezzo o meno
	 *
	 * @return  true se la cella e' occupata, false altrimenti
	 */
	public boolean isOccupato() {
		return occupato;
	}

	/**
	 * Restituisce il pezzo contenuto nella cella attuale
	 *
	 * @return  Pezzo nella cella corrente
	 */
	public Pezzo getPezzoCorrente() {
		return pezzoCorrente;
	}

	// ------Metodi per aggiugere o togliere pezzi dalla scacchiera

	/**
	 * Aggiunge un pezzo nella scacchiera
	 *
	 * @param  nuovoPezzo Pezzo da aggiungere/sovrascrivere alla cella
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
     * Converte l'attributo coordX in intero restituendo
     * il corrispondente valore
     *
     * @param  coordX Carattere in minuscolo da convertire in intero
     *
     * @return  Ascissa convertita in intero compresa fra 0 e 7
     */
    public static int coordXinInt(final char coordX) {
	final int aMinuscolaAscii = 97;
	return coordX - aMinuscolaAscii;
    }

    /**
     * Converte l'attributo coordY in intero restituendo
     * il corrispondente valore
     *
     * @param  coordY Carattere in minuscolo da convertire in intero
     *
     * @return  Ordinata convertita in intero compresa tra 0 e 7
     */
    public static int coordYinInt(final char coordY) {
	final int offsetUnoAscii = 49;
	final int offsetMenoUnoAscii = 7;
	return Math.abs((coordY - offsetUnoAscii) - offsetMenoUnoAscii);
    }

    /**
     * Trasforma un valore da 0 a 7 (indici della matrice) in valori in termini di
     * traversa della scacchiera (carattere tra a e h)
     *
     * @param  coordX Intero da convertire in carattere
     *
     * @return  Valore necessario per la scacchiera compreso fra a e h
     */

    public static char coordXinChar(final int coordX) {
	final int aMinuscolaAscii = 97;
	return (char) (coordX + aMinuscolaAscii);
    }

    /**
     * Trasforma un valore da 0 a 7 (indici della matrice) in valori in termini di
     * righe della scacchiera (carattere tra 1 e 8)
     *
     * @param  coordY Intero da convertire in carattere
     * @return  Valore necessario per la scacchiera compreso fra 1 e 8
     */
    public static char coordYinChar(final int coordY) {
	final int carattere0Ascii = 48;
	return (char) (Math.abs((coordY - Scacchiera.getNumeroColonne())) + carattere0Ascii);
    }

    /**
     * Converte la coordinata X di partenza data in input in intero.
     *
     * @param  m Stringa indicante la mossa in notazione estesa
     * @return  Valore necessario per la scacchiera compreso fra a e h
     */
    public static int startX(final String m) {
		return Cella.coordXinInt(m.charAt(Menu.COLONNA_PARTENZA_MOSSA_NE));
    }

    /**
     * Converte la coordinata Y di partenza data in input in intero.
     *
     * @param  m Stringa indicante la mossa in notazione estesa
     * @return  Valore necessario per la scacchiera compreso fra 0 e 7
     */
    public static int startY(final String m) {
		return Cella.coordYinInt(m.charAt(Menu.TRAVERSA_PARTENZA_MOSSA_NE));
    }

    /**
     * Converte la coordinata X di destinazione data in input in intero.
     *
     * @param  m Stringa indicante la mossa in notazione estesa
     * @return  Valore necessario per la scacchiera compreso fra a e h
     */
    public static int endX(final String m) {
		return Cella.coordXinInt(m.charAt(Menu.COLONNA_DESTINAZIONE_MOSSA_NE));
    }

    /**
     * Converte la coordinata Y di destinazione data in input in intero.
     *
     * @param  m Stringa indicante la mossa in notazione estesa
     * @return  Valore necessario per la scacchiera compreso fra 0 e 7
     */
    public static int endY(final String m) {
		return Cella.coordYinInt(m.charAt(Menu.TRAVERSA_DESTINAZIONE_MOSSA_NE));
    }

}

