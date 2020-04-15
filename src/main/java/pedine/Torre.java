package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;

/**
 * Realizzazione della classe Torre, estensione della classe Pezzo
 */
public final class Torre extends Pezzo {

<<<<<<< HEAD
	/** Costruttore */
=======
>>>>>>> 8d0cc484439433782260394a1b83626179e0ca53
	public Torre(final Colore colore, final Cella posizioneCorrente) {
		super("Torre", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265c';
		} else {
			simbolo = '\u2656';
		}
		// TODO Auto-generated constructor stub
	}
}
