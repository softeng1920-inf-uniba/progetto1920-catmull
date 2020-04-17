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

	public boolean isMossaValidaNero(String mossa, Scacchiera s) {
		return false; // non posso muovere altri pedoni, cioè opposti
	}

	public boolean isMossaValidaBianco(String mossa, Scacchiera s) {
		return false;
	}

	@Override
	public final boolean isEnPassant(String comando, Scacchiera s) {
		return false;
	}

}
