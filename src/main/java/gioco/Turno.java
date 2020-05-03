package gioco;

import it.uniba.main.Colore;

/**
 * Rappresenta il turno per il gioco con i metodi
 * 
 */
public class Turno {

	private static Giocatore giocatoreInTurno;
	private static Giocatore giocatoreInAttesa;

	public Turno() {
		giocatoreInTurno = new Giocatore(Colore.bianco); // true corrisponde al giocatore col colore bianco
		giocatoreInAttesa = new Giocatore(Colore.nero); // false corrisponde al giocatore col colore nero
	}

	public static final void cambioTurno() {
		Giocatore temp = giocatoreInTurno;
		giocatoreInTurno = giocatoreInAttesa;
		giocatoreInAttesa = temp;
	}

	public static Giocatore getGiocatoreInTurno() {
		return giocatoreInTurno;
	}

	public static void setGiocatoreInTurno(final Giocatore g) {
		giocatoreInTurno = g;
	}

	public static Giocatore getGiocatoreInAttesa() {
		return giocatoreInAttesa;
	}

	public static void setGiocatoreInAttesa(final Giocatore g) {
		giocatoreInAttesa = g;
	}

}
