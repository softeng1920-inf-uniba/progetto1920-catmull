package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;

/**
 * Classe Pezzo di tipo astratto Tale classe funge da punto di partenza per poi
 * realizzare i pezzi del gioco nello specifico
 **/
public abstract class Pezzo {

	protected String nome;
	protected Colore colore;
	protected boolean vivo;
	public char simbolo;
	protected Cella posizioneCorrente;

	public Pezzo(String n, Colore c, Cella pC) {
		this.nome = n;
		this.colore = c;
		this.posizioneCorrente = pC;
		this.simbolo = ' ';
		vivo = true;
	}

	/**
	 * disegnapezzo consente di rappresentare il pezzo nella scacchiera
	 */
	public void disegnapezzo() {
		System.out.print(this.simbolo + "  ");
	}
	// public abstract void mossevalide();

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

	public void setSimbolo(char s) {
		this.simbolo = s;
	}

	/**
	 * setPosizioneCorrente imposta la posizione corrente del pezzo
	 *
	 * @param posizioneCorrente
	 */
	public void setPosizioneCorrente(Cella pC) {
		this.posizioneCorrente = pC;
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
	 * getColore restituisce il colore del pezzo, quindi se il pezzo � bianco o
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
	 * getPosizioneCorrente restituisce la cella in cui si trova il pezzo
	 *
	 * @return posizioneCorrente
	 */
	public Cella getPosizioneCorrente() {
		return posizioneCorrente;
	}

	/**
	 * toString consente di rappresentare un oggetto come una stringa
	 */
	@Override
	public String toString() {
		return String.format(nome + " " + colore);
	}
}
