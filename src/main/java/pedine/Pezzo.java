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

    private String nome; // TODO: aggiungere commenti
    private Colore colore;
    private boolean vivo;
    private char simbolo;
    private Cella posizioneCorrente;

    /**
     * TODO: aggiungere commenti
     * 
     * @param n
     * @param c
     * @param pC
     */
    public Pezzo(final String n, final Colore c, final Cella pC) {
	this.nome = n;
	this.colore = c;
	this.posizioneCorrente = pC;
	this.simbolo = ' ';
	vivo = true;
    }

    // --------Metodi di setting --------

    /**
     * setNome imposta il nome del pezzo TODO: Migliorare javadoc
     * 
     * @param nome
     */
    void setNome(final String n) {
	this.nome = n;
    }

    /**
     * setColore imposta il colore del pezzo TODO: Migliorare javadoc
     * 
     * @param colore
     */

    public void setColore(final Colore c) {
	this.colore = c;
    }

    /**
     * setVivo riporta lo stato del pezzo
     */
    public void setVivo(final boolean v) {
	this.vivo = v;
    }

    /**
     * setSimbolo modifica lo stato del simbolo TODO: Migliorare javadoc
     * 
     * @param s
     */

    public void setSimbolo(final char s) {
	this.simbolo = s;
    }

    /**
     * setPosizioneCorrente imposta la posizione corrente del pezzo TODO: Migliorare
     * javadoc
     * 
     * @param posizioneCorrente
     */
    public void setPosizioneCorrente(final Cella pC) {
	this.posizioneCorrente = pC;
    }

    // --------Metodi di Get--------

    /**
     * getNome restituisce il nome del pezzo TODO: Migliorare javadoc
     * 
     * @return nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * getColore restituisce il colore del pezzo, quindi se il pezzo e' bianco o
     * nero TODO: Migliorare javadoc
     * 
     * @return colore
     */
    public Colore getColore() {
	return colore;
    }

    /**
     * getSimbolo restituisce il simbolo corrente del pezzo TODO: Migliorare javadoc
     * 
     * @return simbolo
     */
    public char getSimbolo() {
	return simbolo;
    }

    /**
     * getVivo restituisce lo stato del pezzo, quindi se vivo oppure no TODO:
     * Migliorare javadoc
     *
     * @return vivo
     */
    public boolean isVivo() {
	return vivo;
    }

    /**
     * getPosizioneCorrente restituisce la cella in cui si trova il pezzo TODO:
     * Migliorare javadoc
     * 
     * @return posizioneCorrente
     */
    public Cella getPosizioneCorrente() {
	return posizioneCorrente;
    }

    /**
     * E' possibile avere il nome del pezzo, con relativo colore e simbolo attraverso l'autocasting
     */
    @Override
    public String toString() {
	return String.format(nome + " " + colore + " " + simbolo);
    }

    /**
     * Metodo che permette di controllare se la mossa data sia valida TODO:
     * Migliorare javadoc
     * 
     * @param start
     * @param end
     * @return boolean
     */
    public abstract boolean isMossaValida(Cella start, Cella end);

}
