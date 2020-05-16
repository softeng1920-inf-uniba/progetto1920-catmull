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
    protected String nome;
    protected Colore colore;
    public char simbolo;

    /**
     * @param n  Nome del pezzo
     * @param c  Colore del pezzo
     * @param pC Cella iniziale del pezzo
     */
	public Pezzo(String name, Colore colore) {
		this.nome = name;
		this.colore = colore;
	this.simbolo = ' ';
    }

    // --------Metodi di setting --------

    /**
     * setNome imposta il nome del pezzo
     *
     * @param n Stringa indicante il nome del pezzo da impostare (es. Torre, Cavallo
     *          ecc.)
     */
    void setNome(final String n) {
	this.nome = n;
    }

    /**
     * setColore imposta il colore del pezzo
     *
     * @param c Parametro di tipo Colore del pezzo da impostare
     */

    public void setColore(final Colore c) {
	this.colore = c;
    }

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
     * getNome restituisce il nome del pezzo
     *
     * @return nome Stringa indicante il nome del pezzo
     */
    public String getNome() {
	return nome;
    }

    /**
     * getColore restituisce il colore del pezzo (bianco o nero)
     *
     * @return colore Colore del pezzo (bianco o nero)
     */
    public Colore getColore() {
	return colore;
    }

    /**
     * Restituisce il simbolo corrente del pezzo
     *
     * @return simbolo Carattere indicante il simbolo del pezzo
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
     * @param start Cella di partenza del pezzo
     * @param end   Cella di destinazione del pezzo
     * @return boolean true se la mossa è valida per il pezzo su cui il metodo è
     *         stato chiamato, false altrimenti
     */
    public abstract boolean isMossaValida(Cella start, Cella end);

}
