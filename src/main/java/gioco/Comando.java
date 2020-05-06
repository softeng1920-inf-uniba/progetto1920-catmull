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

	/**
	 * La seguente funzione riconosce se il comando inserito e' un comando scritto
	 * sottoforma di notazione algebrica. Il seguente comando puo' essere anche una
	 * mossa non valida
	 *
	 * @param comando
	 *
	 * @return boolean
	 */
	public static boolean isComandoValido(final String comando) {

		if (comando.equalsIgnoreCase(Menu.help().getNome()) || comando.equalsIgnoreCase(Menu.board().getNome())
				|| comando.equalsIgnoreCase(Menu.captures().getNome())
				|| comando.equalsIgnoreCase(Menu.moves().getNome())
				|| comando.equalsIgnoreCase(Menu.quit().getNome())) {
			return true;
		}
		return false;
	}

	/**
	 * Controlla, attraverso un'espressione regolare, se la stringa inserita
	 * dall'utente è riconosciuta come notazione algebrica.
	 *
	 * @param mossa
	 * @return boolean
	 */
	public static boolean isNotazioneAlgebrica(final String mossa) {
		String regex = String.join("|", new String[] { "([a-h](x|:))?([a-h][1-8])( e.p.)?", // mossa del pedone
				"D([x|:])?[a-h][1-8]", // mossa della regina
				"T([a-h]|[1-8])?([x|:])?([a-h][1-8])", // mossa della torre
				"C([a-h]|[1-8])?([x|:])?([a-h][1-8])", // mossa cavallo
				"A(x|:)?[a-h][1-8]", // mossa alfiere
				"R(x|:)?[a-h][1-8]" // mossa del re
		});

		return mossa.matches(regex);
	}
}
