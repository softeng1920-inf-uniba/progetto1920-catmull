package gioco;

/**
 * La classe Comando definisce la struttura dei comandi mostrati nel menu del
 * gioco attraverso gli attributi nome e descrizione. La classe Comando e' di
 * tipo ENTITY.
 */

public class Comando {

	private String nome;
	private String descrizione;

	/**
	 * Metodo Costruttore
	 *
	 * @param n
	 * @param desc
	 */
	Comando(final String n, final String desc) {
		setNome(n);
		setDescrizione(desc);
	}

	/**
	 * Metodo che permette di modificare il nome del comando
	 *
	 * @param n
	 */
	void setNome(final String n) {
		this.nome = n;
	}

	/**
	 * Metodo che permette di modificare la descrizione del comando
	 *
	 * @param d
	 */
	void setDescrizione(final String d) {
		this.descrizione = d;
	}

	/**
	 * @return nome del comando
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che permette di visualizzare i comandi
	 */
	@Override
	public String toString() {
		return String.format("\u2022 " + "\033[1;37m" + nome + "\u001B[0m" + " -->    " + descrizione);
	}

}
