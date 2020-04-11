package gioco;

import giocatore.*;

public class Turno {

	Giocatore giocatoreInTurno;
	Giocatore giocatoreInAttesa;

	public Turno() {
		giocatoreInTurno = new Giocatore(true); // true corrisponde al giocatore col colore bianco
		giocatoreInAttesa = new Giocatore(false); // false corrisponde al giocatore col colore nero
	}

	public void cambioTurno() {
		Giocatore temp = giocatoreInTurno;
		giocatoreInTurno = giocatoreInAttesa;
		giocatoreInAttesa = temp;
	}

	public Giocatore getGiocatoreInTurno() {
		return giocatoreInTurno;
	}

}
