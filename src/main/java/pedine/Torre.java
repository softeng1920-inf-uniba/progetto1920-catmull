package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;

/**
 * Realizzazione della classe Torre, estensione della classe Pezzo
 */
public final class Torre extends Pezzo {

	public Torre(final Colore colore, final Cella posizioneCorrente) {
		super("Torre", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '♜';
		} else {
			simbolo = '♖';
		}
		// TODO Auto-generated constructor stub
	}
}
