/** Classe per rappresentere il sottotipo di pezzo chiamato Cavallo */
package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;

/**
 * Rappresentazione astratta della classe cavallo
 *
 */
public final class Cavallo extends Pezzo {

	public Cavallo(final Colore colore, final Cella posizioneCorrente) {
		super("Cavallo", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '♞';
		} else {
			simbolo = '♘';
		}
	}

}
