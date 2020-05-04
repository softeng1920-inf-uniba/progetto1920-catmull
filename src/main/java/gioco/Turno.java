package gioco;

import java.util.ArrayList;

import it.uniba.main.Colore;

/**
 * Rappresenta il turno per il gioco con i metodi
 * 
 * La classe turno è di tipo ENTITY
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

	/**
	 * Fonde le due liste in cui sono conservate le mosse giocate di ogni giocatore.
	 * La fusione avviene in modo alternato. Permette di avere una visione completa
	 * delle mosse giocate totali.
	 *
	 * @return ArrayList di stringhe.
	 */
	public ArrayList<String> fusioneListe() {
		int i, j, k;
		int dimensione = getGiocatoreInAttesa().getNumeroMosseGiocate() + getGiocatoreInTurno().getNumeroMosseGiocate();
		ArrayList<String> mosseGiocateTotali = new ArrayList<String>(dimensione);
		if (getGiocatoreInTurno().getColore() == Colore.bianco) {
			i = 0;
			j = 0;
			k = 0;
			while (i < getGiocatoreInTurno().getNumeroMosseGiocate()
					&& j < getGiocatoreInAttesa().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, getGiocatoreInTurno().getMossaGiocata(i++));
				mosseGiocateTotali.add(k++, getGiocatoreInAttesa().getMossaGiocata(j++));
			}
			while (i < getGiocatoreInTurno().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, getGiocatoreInTurno().getMossaGiocata(i++));
			}
			while (j < getGiocatoreInAttesa().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, getGiocatoreInAttesa().getMossaGiocata(j++));
			}
		} else {
			i = 0;
			j = 0;
			k = 0;
			while (i < getGiocatoreInAttesa().getNumeroMosseGiocate()
					&& j < getGiocatoreInTurno().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, getGiocatoreInAttesa().getMossaGiocata(i++));
				mosseGiocateTotali.add(k++, getGiocatoreInTurno().getMossaGiocata(j++));
			}
			while (i < getGiocatoreInAttesa().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, getGiocatoreInAttesa().getMossaGiocata(i++));
			}
			while (j < getGiocatoreInTurno().getNumeroMosseGiocate()) {
				mosseGiocateTotali.add(k++, getGiocatoreInTurno().getMossaGiocata(j++));
			}

		}

		return mosseGiocateTotali;
	}

}
