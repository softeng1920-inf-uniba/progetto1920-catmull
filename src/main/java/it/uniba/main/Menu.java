package it.uniba.main;

/**
 * la classe Menu implementa il menu'
 */
public class Menu {

	private Comando help;
	private Comando moves;
	private Comando quit;
	private Comando board;
	private Comando captures;
	private Comando play;
	private Comando back;

	Menu() {
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
	public Comando help() {
		return help;
	}

	/**
	 * history consente di chiamare il comando "history" in controller
	 * @return history
	 */
	public Comando moves() {
		return moves;
	}

	/**
	 *quit consente di chiamare il comando "quit" in controller
	 * @return quit
	 */
	public Comando quit() {
		return quit;
	}

	/**
	 * board consente di chiamare il comando "board" in controller
	 * @return board
	 */
	public Comando board() {
		return board;
	}

	/**
	 * captures consente di chiamare il comando "captures" in controller
	 * @return captures
	 */
	public Comando captures() {
		return captures;
	}

	/**
	 * play consente di chiamare il comando "play" in controller
	 * @return play
	 */
	public Comando play() {
		return play;
	}

	/**
	 * back consente di chiamare il comando "back" in controller
	 * @return back
	 */
	public Comando back() {
		return back;
	}

}
