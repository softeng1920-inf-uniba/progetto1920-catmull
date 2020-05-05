package gioco;

/**
 * Classe che implementa il comando
 *
 * La classe Comando e' di tipo ENTITY
 */
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

	/**
	 * getNome da come output il nome del comando
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * getNome da come output la descrizione del comando
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Metodo che permette di visualizzare i comandi
	 */
	@Override
	public String toString() {
		return String.format("\u2022 " + "\033[1;37m" + nome + "\u001B[0m" + " -->    " + descrizione);
	}
}
