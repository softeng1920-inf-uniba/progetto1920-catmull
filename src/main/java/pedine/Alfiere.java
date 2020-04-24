package pedine;

import java.util.ArrayList;

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

	@Override
	public boolean isMossaValida(Cella start, Cella end) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		// TODO Auto-generated method stub
		return false;
	}


}
