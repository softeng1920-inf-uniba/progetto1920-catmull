package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Realizzazione della classe Torre, estensione della classe Pezzo
 */
public final class Torre extends Pezzo {

	/** Costruttore */
	public Torre(final Colore colore, final Cella posizioneCorrente) {
		super("Torre", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265c';
		} else {
			simbolo = '\u2656';
		}
		// TODO Auto-generated constructor stub
	}

	public boolean isMossaValidaNero(String mossa, Scacchiera s) {
		return false;
	}

	public boolean isMossaValidaBianco(String mossa, Scacchiera s) {
		return false;
	}

	public final boolean isEnPassant(String comando, Scacchiera s) {
		return false;
	}

}
