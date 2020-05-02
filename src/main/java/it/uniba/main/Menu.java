package it.uniba.main;

/**
 * la classe Menu implementa il menu'
 */
public class Menu {

	private static Comando help;
	private static Comando moves;
	private static Comando quit;
	private static Comando board;
	private static Comando captures;
	private static Comando play;
	private static Comando back;

	public static void newMenu() {
		help = new Comando("Help", "");
		moves = new Comando("Moves", "Visualizza la cronologia delle mosse giocate");
		quit = new Comando("Quit", "Esci dal gioco");
		board = new Comando("Board", "Visualizza la posizione sulla scacchiera");
		captures = new Comando("Captures", "Visualizza le catture del Bianco e del Nero");
		play = new Comando("Play", "Inizia una nuova partita");
		back = new Comando("Menu", "Ritorna al menu principale");

	}

	/**
	 * help consente di chiamare il comando "help" in controller
	 * @return help
	 */
	public static Comando help() {
		return help;
	}

	/**
	 * history consente di chiamare il comando "history" in controller
	 * @return history
	 */
	public static Comando moves() {
		return moves;
	}

	/**
	 *quit consente di chiamare il comando "quit" in controller
	 * @return quit
	 */
	public static Comando quit() {
		return quit;
	}

	/**
	 * board consente di chiamare il comando "board" in controller
	 * @return board
	 */
	public static Comando board() {
		return board;
	}

	/**
	 * captures consente di chiamare il comando "captures" in controller
	 * @return captures
	 */
	public static Comando captures() {
		return captures;
	}

	/**
	 * play consente di chiamare il comando "play" in controller
	 * @return play
	 */
	public static Comando play() {
		return play;
	}

	/**
	 * back consente di chiamare il comando "back" in controller
	 * @return back
	 */
	public static Comando back() {
		return back;
	}

}
