package pedine;

import scacchiera.Cella;

/**
 * Rappresentazione dell'astrazione del pezzo alfiere
 */
public final class Alfiere extends Pezzo {

	public Alfiere(final boolean colore, final Cella posizioneCorrente) {
		super("Alfiere", colore, posizioneCorrente);

	}

	@Override
	public void disegnapezzo() {

		char simbolo;
		if (getColore()) {
			simbolo = 'B';
		} else {
			simbolo = 'b';
		}
		System.out.print(" " + simbolo + " ");
	}
}
