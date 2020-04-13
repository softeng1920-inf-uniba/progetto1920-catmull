package it.uniba.main;

public class Menu {

	private Comando help;
	private Comando history;
	private Comando quit;
	private Comando board;
	private Comando captures;
	private Comando play;
	private Comando exit;

	Menu() {
		help = new Comando("Help", "");
		history = new Comando("History", "Visualizza la cronologia delle mosse giocate");
		quit = new Comando("Quit", "Esci dal gioco");
		board = new Comando("Board", "Visualizza la posizione sulla scacchiera");
		captures = new Comando("Captures", "Visualizza le catture del Bianco e del Nero");
		play = new Comando("Play", "Inizia una nuova partita");
		exit = new Comando("Menu","Ritorna al menu principale");
	}

	public Comando help() {
		return help;
	}

	public Comando history() {
		return history;
	}

	public Comando quit() {
		return quit;
	}

	public Comando board() {
		return board;
	}

	public Comando captures() {
		return captures;
	}

	public Comando play() {
		return play;
	}
	
	public Comando exit() {
		return exit;
	}
	
}
