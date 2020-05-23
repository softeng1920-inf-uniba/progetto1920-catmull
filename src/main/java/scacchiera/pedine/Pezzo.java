package scacchiera.pedine;

import gioco.Colore;
import scacchiera.Cella;

/**
 * La classe Pezzo di tipo astratto tale classe funge da punto di partenza per
 * poi realizzare i pezzi del gioco nello specifico. Ogni pezzo contiene i
 * seguenti attributi che ne definiscono lo stato:
 * <ul>
 * <li><b>nome</b></li>
 * <li><b>colore</b></li>
 * <li><b>simbolo</b></li>
 * <li><b>vivo</b></li>
 * <li><b>posisizioneCorrente</b></li>
 * </ul>
 * La classe Pezzo e' di tipo ENTITY.
 **/
public abstract class Pezzo {

    private String nome;
    private Colore colore;
    private char simbolo;

	/**
	 * Invoca il metodo della superclasse definendo il:
	 * <ul>
	 *   <li>nome</li>
	 *   <li>colore</li>
	 *   <li>posizioneCorrente</li>
	 *   <li>simbolo</li>
	 * </ul>
	 * 
     * @param  n  Stringa indicante il nome del pezzo
     * @param  c  Colore del pezzo
     */
	public Pezzo(final String n, final Colore c) {
	this.nome = n;
	this.colore = c;
	this.simbolo = ' ';
    }

    // --------Metodi di setting --------

    /**
	 * setSimbolo modifica lo stato del simbolo
	 *
	 * @param s Carattere indicante il simbolo del pezzo da impostare
	 */

    public void setSimbolo(final char s) {
	this.simbolo = s;
    }

    // --------Metodi di Get--------

    /**
	 * Restituisce il nome del pezzo
	 *
	 * @return  nome del pezzo
	 */
    public String getNome() {
	return nome;
    }

    /**
	 * Restituisce il colore del pezzo (bianco o nero)
	 *
	 * @return  colore del pezzo (bianco o nero)
	 */
    public Colore getColore() {
	return colore;
    }

    /**
	 * Restituisce il simbolo corrente del pezzo
	 *
	 * @return  simbolo del pezzo
	 */
    public char getSimbolo() {
	return simbolo;
    }

    /**
     * E' possibile avere il nome del pezzo, con relativo colore e simbolo
     * attraverso l'autocasting
     */
    @Override
    public String toString() {
	return String.format(nome + " " + colore + " " + simbolo);
    }

    /**
	 * Controlla se la mossa data in input attraverso cella di partenza e cella di
	 * destinazione sia valida per il pezzo corrente
	 *
	 * @param  start Cella di partenza del pezzo
	 * @param  end   Cella di destinazione del pezzo
	 * 
	 * @return  true se la mossa e' valida per il pezzo su cui il metodo e'
	 * stato chiamato, false altrimenti
	 */
    public abstract boolean isMossaValida(Cella start, Cella end);

}
