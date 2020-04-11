package pedine;
import scacchiera.Cella;


public abstract class Pezzo {
/** classe pezzo di tipo astratto; 
 * tale classe funge da punto di partenza per poi realizzare i pezzi del gioco nello specifico **/
	
	protected String nome;
	protected boolean colore;
	protected boolean vivo;
	protected Cella posizioneCorrente;

	public Pezzo(String nome,boolean colore, Cella posizioneCorrente) {
		this.nome = nome;
		this.colore = colore;
		this.posizioneCorrente = posizioneCorrente;
		vivo = true;
	}

	public abstract void disegnapezzo();
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
	 * @param colore
	 */
	
	public void setColore(boolean colore) {
		this.colore = colore;
	}

	/**
	 * setVivo imposta lo stato del pezzo
	 * se il pezzo è in campo restituisce true , altrimenti false
	 * 
	 * @param vivo
	 */
	public void setVivo (boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * setPosizioneCorrente imposta la posizione corrente del pezzo
	 * @param posizioneCorrente
	 */
	public void setPosizioneCorrente(Cella posizioneCorrente) {
		this.posizioneCorrente = posizioneCorrente;
	}

	// --------Metodi di Get--------

	/** getNome restituisce il nome del pezzo
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/** getColore restituisce il colore del pezzo, quindi se il pezzo è bianco o nero
	 * 	 
	 * @return colore
	 */
	public boolean getColore() {
		return colore;
	}
	
	/** getVivo restituisce lo stato del pezzo, quindi se vivo oppure no
	 * 
	 * 
	 * @return vivo
	 */
	public boolean getVivo() {
		return vivo;
	}

	/**
	 * getPosizioneCorrente restituisce la cella in cui si trova il pezzo
	 * @return posizioneCorrente 
	 */
	public Cella getPosizioneCorrente() {
		return posizioneCorrente;
	}


	@Override
	public String toString() {
		String colore = this.colore ? "Bianco" : "Nero";
		return String.format(nome + " " + colore);
	}
}