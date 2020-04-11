package pedine;

import scacchiera.Cella;
import it.uniba.main.Colore;

/**
 * Classe Pezzo di tipo astratto Tale classe funge da punto di partenza per poi
 * realizzare i pezzi del gioco nello specifico
 **/
public abstract class Pezzo {

	protected String nome;
	protected  Colore colore;
	protected boolean vivo;
	public String simbolo;
	protected Cella posizioneCorrente;

	public Pezzo(String nome, Colore colore, Cella posizioneCorrente, String simbolo) {
		this.nome = nome;
		this.colore = colore;
		this.posizioneCorrente = posizioneCorrente;
		this.simbolo = simbolo;
		vivo = true;
	}

	public void disegnapezzo() {
		System.out.print("   " + this.simbolo + "   ");
	}
	// public abstract void mossevalide();

	// --------Metodi di setting --------

	/**
	 * setNome imposta il nome del pezzo
	 * 
	 * @param nome
	 */
	void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * setColore imposta il colore del pezzo
	 * 
	 * @param colore
	 */

	public void setColore(Colore colore) {
		this.colore = colore;
	}

	/**
	 * 
	 */
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * setSimbolo modifica lo stato del simbolo
	 * 
	 * @param simbolo
	 */

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * setPosizioneCorrente imposta la posizione corrente del pezzo
	 * 
	 * @param posizioneCorrente
	 */
	public void setPosizioneCorrente(Cella posizioneCorrente) {
		this.posizioneCorrente = posizioneCorrente;
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
	public String getSimbolo() {
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
	public boolean getVivo() {
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

	@Override
	public String toString() {
		return String.format(nome + " " + colore);
	}
}
