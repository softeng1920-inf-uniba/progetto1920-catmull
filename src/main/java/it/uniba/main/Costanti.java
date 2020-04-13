/**
 * 
 */
package it.uniba.main;

/** Contenitore di tutte le costanti utilizzati nel gioco */
public final class Costanti {

	/**
	 * 
	 */
	private Costanti() {
		// TODO Auto-generated constructor stub
	}

	protected static final String[] COMANDI = {"play", "quit", "exit", "board" };

	public static final int ASCII_A_MINUSCOLA = 97;

	public static String[] getComandi() {
		return COMANDI;
	}
}
