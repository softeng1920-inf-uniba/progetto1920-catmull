package pedine;

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
    
    protected String nome; // TODO: aggiungere commenti
    protected Colore colore;
    protected boolean vivo;
    public char simbolo;
    protected Cella posizioneCorrente;

    /**
     * TODO: aggiungere commenti
     * @param name
     * @param colore
     * @param posizioneCorrente
     */
    public Pezzo(String name, Colore colore, Cella posizioneCorrente) {
	this.nome = name;
	this.colore = colore;
	this.posizioneCorrente = posizioneCorrente;
	this.simbolo = ' ';
	vivo = true;
    }

    // --------Metodi di setting --------

    /**
     * setNome imposta il nome del pezzo
     * TODO: Migliorare javadoc
     * @param nome
     */
    void setNome(String n) {
	this.nome = n;
    }

    /**
     * setColore imposta il colore del pezzo
     *TODO: Migliorare javadoc
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
     *TODO: Migliorare javadoc
     * @param simbolo
     */

    public void setSimbolo(final char simbolo) {
	this.simbolo = simbolo;
    }

    /**
     * setPosizioneCorrente imposta la posizione corrente del pezzo
     *TODO: Migliorare javadoc
     * @param posizioneCorrente
     */
    public void setPosizioneCorrente(Cella pC) {
	this.posizioneCorrente = pC;
    }

    // --------Metodi di Get--------

    /**
     * getNome restituisce il nome del pezzo
     *TODO: Migliorare javadoc
     * @return nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * getSimbolo restituisce il simbolo corrente del pezzo
     *TODO: Migliorare javadoc
     * @return simbolo
     */
    public char getSimbolo() {
	return simbolo;
    }

    /**
     * getColore restituisce il colore del pezzo, quindi se il pezzo e' bianco o
     * nero
     *TODO: Migliorare javadoc
     * @return colore
     */
    public Colore getColore() {
	return colore;
    }

    /**
     * getVivo restituisce lo stato del pezzo, quindi se vivo oppure no
     *TODO: Migliorare javadoc
     *
     * @return vivo
     */
    public boolean isVivo() {
	return vivo;
    }

    /**
     * getPosizioneCorrente restituisce la cella in cui si trova il pezzo
     *TODO: Migliorare javadoc
     * @return posizioneCorrente
     */
    public Cella getPosizioneCorrente() {
	return posizioneCorrente;
    }

    @Override
    public String toString() {
	return String.format(nome + " " + colore + " " + simbolo);
    }

    /**
     * Metodo che permette di controllare se la mossa data sia valida
     *TODO: Migliorare javadoc
     * @param start
     * @param end
     * @return boolean
     */
    public abstract boolean isMossaValida(Cella start, Cella end);

}