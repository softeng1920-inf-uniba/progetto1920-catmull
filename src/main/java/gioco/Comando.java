package gioco;

/**
<<<<<<< Updated upstream
 * La classe Comando definisce la struttura dei comandi mostrati nel menu del
 * gioco attraverso gli attributi nome e descrizione.
 * La classe Comando e' di tipo ENTITY.
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
	 * @return nome del comando
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return descrizione del comando
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
	 * Data la mossa, se è un arrocco ne restituisce il tipo, altrimenti viene
	 * restituito -1
	 *
	 * @param mossa in notazione algebrica
	 * @return int 1 => Arrocco corto | 2 => Arrocco lungo | -1 => Non è un arrocco
	 */
	public static int isArrocco(String mossa) {

		if (mossa.matches("(0|o|O)-(0|o|O)"))
			return ARROCCO_CORTO;
		else if (mossa.matches("(0|o|O)-(0|o|O)-(0|o|O)"))
			return ARROCCO_LUNGO;

		return -1;

	}

	/**
	 * Riconosce se il comando dato in input rientra tra quelli del menu di gioco
	 *
	 * @param comando
	 *
	 * @return true se il comando rientra tra quelli del menu di gioco, false
	 *         altrimenti
	 *
	 *         I comandi validi nel menu di gioco sono: - help - board - captures -
	 *         moves - quit
	 */
	public static boolean isComandoValido(final String comando) {

		return (comando.equalsIgnoreCase(Menu.help().getNome()) || comando.equalsIgnoreCase(Menu.board().getNome())
				|| comando.equalsIgnoreCase(Menu.captures().getNome())
				|| comando.equalsIgnoreCase(Menu.moves().getNome()) || comando.equalsIgnoreCase(Menu.quit().getNome()));

	}

	/**
	 * Controlla, attraverso un'espressione regolare, se la stringa inserita
	 * dall'utente e' riconosciuta come notazione algebrica.
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
				"R(x|:)?[a-h][1-8]", // mossa del re
				"(0|o|O)-(0|o|O)(-(0|o|O))?" // arrocco corto o lungo
		});

		return mossa.matches(regex);
	}

}
