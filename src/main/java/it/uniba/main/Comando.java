package it.uniba.main;

public class Comando {

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

	String getNome() {
		return nome;
	}

	String getDescrizione() {
		return descrizione;
	}

	/**
	 * Metodo che permette di visualizzare i comandi
	 */
	@Override
	public String toString() {
		return String.format(nome + " " + descrizione);
	}
}
