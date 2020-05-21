package gioco;

/**
 * La classe Menu implementa i menu: il menu principale che compare all'inizio
 * del gioco e il menu che compare durante la partita. La classe contiene le
 * informazioni riguardo ai comandi: help, moves, quit, board, captures e play.
 * La classe Menu e' di tipo Entity.
 */

public final class Menu {

	private static Comando help;
	private static Comando moves;
	private static Comando quit;
	private static Comando board;
	private static Comando captures;
	private static Comando play;

	public static final int ARROCCO_CORTO = 1;
	public static final int ARROCCO_LUNGO = 2;

	public static final int COLONNA_PARTENZA_MOSSA_NE = 0;
	public static final int TRAVERSA_PARTENZA_MOSSA_NE = 1;

	public static final int COLONNA_DESTINAZIONE_MOSSA_NE = 3;
	public static final int TRAVERSA_DESTINAZIONE_MOSSA_NE = 4;
	private Menu() {
	}

	/**
	 * Costruttore statico per classe Singleton
	 */
	public static void newMenu() {
		help = new Comando("Help", "Visualizza nome e descrizione dell'elenco comandi");
		moves = new Comando("Moves", "Visualizza la cronologia delle mosse giocate");
		quit = new Comando("Quit", "Esci dal gioco");
		board = new Comando("Board", "Visualizza la scacchiera");
		captures = new Comando("Captures", "Visualizza le catture del Bianco e del Nero");
		play = new Comando("Play", "Inizia una nuova partita");
	}

	/**
	 * help consente di chiamare il comando "help" in controller
	 *
	 * @return help
	 */
	public static Comando help() {
		return help;
	}

	/**
	 * history consente di chiamare il comando "history" in controller
	 *
	 * @return history
	 */
	public static Comando moves() {
		return moves;
	}

	/**
	 * quit consente di chiamare il comando "quit" in controller
	 *
	 * @return quit
	 */
	public static Comando quit() {
		return quit;
	}

	/**
	 * board consente di chiamare il comando "board" in controller
	 *
	 * @return board
	 */
	public static Comando board() {
		return board;
	}

	/**
	 * captures consente di chiamare il comando "captures" in controller
	 *
	 * @return captures
	 */
	public static Comando captures() {
		return captures;
	}

	/**
	 * play consente di chiamare il comando "play" in controller
	 *
	 * @return play
	 */
	public static Comando play() {
		return play;
	}

	/**
	 * Data la mossa, se e' un arrocco ne restituisce il tipo, altrimenti viene
	 * restituito -1
	 *
	 * @param mossa in notazione algebrica
	 * @return int 1 => Arrocco corto | 2 => Arrocco lungo | -1 => Non e' un arrocco
	 */
	public static int isArrocco(final String mossa) {

		if (mossa.matches("(0|o|O)-(0|o|O)")) {
			return ARROCCO_CORTO;
		} else if (mossa.matches("(0|o|O)-(0|o|O)-(0|o|O)")) {
			return ARROCCO_LUNGO;
		}
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

		return (comando.equalsIgnoreCase(Menu.help().getNome())
				|| comando.equalsIgnoreCase(Menu.board().getNome())
				|| comando.equalsIgnoreCase(Menu.captures().getNome())
				|| comando.equalsIgnoreCase(Menu.moves().getNome())
				|| comando.equalsIgnoreCase(Menu.quit().getNome()));

	}

	/**
	 * Controlla, attraverso un'espressione regolare, se la stringa inserita
	 * dall'utente e' riconosciuta come notazione algebrica.
	 *
	 * @param mossa
	 * @return boolean
	 */
	public static boolean isNotazioneAlgebrica(final String mossa) {
		String regex = String.join("|", new String[] {
				"([a-h](x|:))?([a-h][1-8])( e.p.)?", // mossa del pedone
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
