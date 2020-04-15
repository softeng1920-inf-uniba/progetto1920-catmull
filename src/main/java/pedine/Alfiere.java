package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Rappresentazione dell'astrazione del pezzo alfiere
 */
public final class Alfiere extends Pezzo {

	public Alfiere(final Colore colore, final Cella posizioneCorrente) {
		super("Alfiere", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265d';
		} else {
			simbolo = '\u2657';
		}
	}
	
	public boolean isMossaValida(String mossa, Scacchiera s) {
		return true;
	}	
}
