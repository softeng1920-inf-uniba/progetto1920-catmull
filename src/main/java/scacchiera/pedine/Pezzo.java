package scacchiera.pedine;

import gioco.Colore;
import scacchiera.Cella;

/**
 * La classe Pezzo di tipo astratto tale classe funge da punto di partenza per poi
 * realizzare i pezzi del gioco nello specifico. Ogni pezzo contiene i seguenti attributi
 * che ne definiscono lo stato:
 * <ul>
 * 	<li><b>nome</b></li>
 *  <li><b>colore</b></li>
 *  <li><b>simbolo</b></li>
 *  <li><b>vivo</b></li>
 *  <li><b>posisizioneCorrente</b></li>
 * </ul>
 * La classe Pezzo e' di tipo ENTITY.
 **/
public abstract class Pezzo {
    protected String nome;
    protected Colore colore;
    protected boolean vivo;
    public char simbolo;

    public Pezzo(String name, Colore colore) {
	this.nome = name;
	this.colore = colore;
	this.simbolo = ' ';
	vivo = true;
    }

    // --------Metodi di setting --------

    /**
     * setNome imposta il nome del pezzo
     *
     * @param nome
     */
    void setNome(String n) {
	this.nome = n;
    }

    /**
     * setColore imposta il colore del pezzo
     *
     * @param colore
     */

    public void setColore(Colore c) {
	this.colore = c;
    }

    /**
     * setVivo riporta lo stato del pezzo
     */
    public void setVivo(boolean v) {
	this.vivo = v;
    }

    /**
     * setSimbolo modifica lo stato del simbolo
     *
     * @param simbolo
     */

    public void setSimbolo(final char simbolo) {
	this.simbolo = simbolo;
    }


    // --------Metodi di Get--------

    /**
     * getNome restituisce il nome del pezzo
     *
     * @return nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * getSimbolo restituisce il simbolo corrente del pezzo
     *
     * @return simbolo
     */
    public char getSimbolo() {
	return simbolo;
    }

    /**
     * getColore restituisce il colore del pezzo, quindi se il pezzo e' bianco o
     * nero
     *
     * @return colore
     */
    public Colore getColore() {
	return colore;
    }

    /**
     * getVivo restituisce lo stato del pezzo, quindi se vivo oppure no
     *
     *
     * @return vivo
     */
    public boolean isVivo() {
	return vivo;
    }


    /**
     * toString consente di rappresentare un oggetto come una stringa
     */
    @Override
    public String toString() {
	return String.format(nome + " " + colore + " " + simbolo);
    }

    /**
     * Metodo che permette di controllare se la mossa data sia valida
     *
     * @param start
     * @param end
     * @return boolean
     */
    public abstract boolean isMossaValida(Cella start, Cella end);

}