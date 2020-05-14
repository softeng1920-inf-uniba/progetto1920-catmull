package gioco;

import java.util.ArrayList;

/**
 * La classe turno serve per identificare il turno corrente e di attesa del
 * gioco, quindi definisce se sta giocando il giocatore bianco o nero; Contiene
 * le informazioni riguardo al turno del giocatore. La classe Turno e' di tipo
 * ENTITY
 */

public class Turno {
	private static Giocatore giocatoreInTurno = new Giocatore(Colore.bianco);
	private static Giocatore giocatoreInAttesa = new Giocatore(Colore.nero);;

	public Turno() {
		
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

	/**
	 * Fonde le due liste in cui sono conservate le mosse giocate di ogni giocatore.
	 * La fusione avviene in modo alternato. Permette di avere una visione completa
	 * delle mosse giocate totali.
	 *
	 * @return ArrayList di stringhe.
	 */
	public static ArrayList<String> fusioneListe() {
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
