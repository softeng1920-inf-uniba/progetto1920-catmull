package it.uniba.main;

public class Menu {

	private Comando help;
	private Comando history;
	private Comando quit;
	private Comando board;
	private Comando captures;
	private Comando play;

	Menu() {
		help = new Comando("help", "");
		history = new Comando("history", "Visualizza la cronologia delle mosse giocate");
		quit = new Comando("quit", "Esci dal gioco");
		board = new Comando("board", "Visualizza la posizione sulla scacchiera");
		captures = new Comando("captures", "Visualizza le catture del Bianco e del Nero");
		play = new Comando("play", "Inizia una nuova partita");
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
}
