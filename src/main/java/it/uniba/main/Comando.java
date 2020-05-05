package it.uniba.main;

/**
 * Classe che implementa il comando
 */
public class Comando {

	public final static int ARROCCO_CORTO = 1;
	public final static int ARROCCO_LUNGO = 2;

	private String nome;
	private String descrizione;

	/**
	 * Metodo Costruttore
	 * 
	 * @param nome
	 * @param descrizione
	 */
	Comando(String nome, String descrizione) {
		setNome(nome);
		setDescrizione(descrizione);
	}

	/**
	 * Metodo che permette di modificare il nome del comando
	 * 
	 * @param nome
	 */
	void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo che permette di modificare la descrizione del comando
	 * 
	 * @param descrizione
	 */
	void setDescrizione(final String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * getNome da come output il nome del comando
	 * 
	 * @return nome
	 */
	String getNome() {
		return nome;
	}

	/**
	 * getNome da come output la descrizione del comando
	 * 
	 * @return descrizione
	 */
	String getDescrizione() {
		return descrizione;
	}

	/**
	 * Metodo che permette di visualizzare i comandi
	 */
	@Override
	public String toString() {
		return String.format("\u2022 " + nome + " -->    " + descrizione);
	}

	/**
	 * Data la mossa, se è un arrocco restituisce il tipo, altrimenti -1
	 * 
	 * @param mossa
	 * @return
	 */
	public static int isArrocco(String mossa) {

		if (mossa.matches("(0|o|O)-(0|o|O)"))
			return ARROCCO_CORTO;
		else if (mossa.matches("(0|o|O)-(0|o|O)-(0|o|O)"))
			return ARROCCO_LUNGO;

		return -1;

	}
}
