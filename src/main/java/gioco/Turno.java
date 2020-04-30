package gioco;

import it.uniba.main.Colore;

/**
 * Rappresenta il turno per il gioco con i metodi
 * 
 */
public class Turno {

	private Giocatore giocatoreInTurno;
	private Giocatore giocatoreInAttesa;

	public Turno() {
		giocatoreInTurno = new Giocatore(Colore.bianco); // true corrisponde al giocatore col colore bianco
		giocatoreInAttesa = new Giocatore(Colore.nero); // false corrisponde al giocatore col colore nero
	}

	public final void cambioTurno() {
		Giocatore temp = giocatoreInTurno;
		giocatoreInTurno = giocatoreInAttesa;
		giocatoreInAttesa = temp;
	}

	public final Giocatore getGiocatoreInTurno() {
		return giocatoreInTurno;
	}

	public final void setGiocatoreInTurno(final Giocatore g) {
		this.giocatoreInTurno = g;
	}

	public final Giocatore getGiocatoreInAttesa() {
		return giocatoreInAttesa;
	}

	public final void setGiocatoreInAttesa(final Giocatore g) {
		this.giocatoreInAttesa = g;
	}

}
