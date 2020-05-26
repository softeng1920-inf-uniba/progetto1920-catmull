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
	 * Avvalora gli attributi primitivi di classe
	 * 
	 * @param  n Stringa identificativa del comando
	 * @param  desc Stringa che specifica il comando
	 */
	Comando(final String n, final String desc) {
		setNome(n);
		setDescrizione(desc);
	}

	/**
	 * Metodo che permette di modificare il <b>nome</b> del comando
	 *
	 * @param  n Stringa indicante il comando
	 */
	void setNome(final String n) {
		this.nome = n;
	}

	/**
	 * Metodo che permette di modificare la <b>descrizione</b> del comando
	 *
	 * @param  d Stringa che specifica il comando
	 */
	void setDescrizione(final String d) {
		this.descrizione = d;
	}

	/** Restituisce il nome del comando
	 * 
	 * @return  Stringa contenente il nome del comando
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che permette di visualizzare i comandi in formato testuale
	 * 
	 * @return   Stringa contenente la descrizione formattata del comando
	 */
	@Override
	public String toString() {
		return String.format("\u2022 " + "\033[1;37m" + nome + "\u001B[0m" + " -->    " + descrizione);
	}

}
