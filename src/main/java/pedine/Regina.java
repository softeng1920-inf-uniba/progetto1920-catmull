package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;

/** Classe per rappresentere il sottotipo di pezzo chiamato Regina */
public final class Regina extends Pezzo {

	/** Costruttore */
	public Regina(final Colore colore, final Cella posizioneCorrente) {
		super("Regina", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '♛';
		} else {
			simbolo = '♕';
		}
		// TODO Auto-generated constructor stub
	}
}
