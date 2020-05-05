package pedine;

import java.util.ArrayList;

import gioco.Colore;
import scacchiera.*;


/**
 * Classe Pezzo di tipo astratto. Tale classe funge da punto di partenza per poi
 * realizzare i pezzi del gioco nello specifico
 * 
 * La classe Pezzo e' di tipo ENTITY
 **/
public abstract class Pezzo {

	protected String nome;
	protected Colore colore;
	protected boolean vivo;
	public char simbolo;
	protected Cella posizioneCorrente;

	public final static int ARROCCO_CORTO = 1;
	public final static int ARROCCO_LUNGO = 2;
	
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

	/**
	 * Metodo che permette di controllare se la mossa speciale data sia valida
	 * 
	 * @param start
	 * @param end
	 * @param s
	 * @param mosse
	 * @return boolean
	 */
	public abstract boolean isMossaSpecialeValida(Cella start, Cella end, ArrayList<String> mosse);

}