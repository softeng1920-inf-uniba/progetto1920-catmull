package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;

/** Classe per rappresentere il sottotipo di pezzo chiamato Re */
public final class Re extends Pezzo {

	/** Costruttore */
	public Re(final Colore colore, final Cella posizioneCorrente) {
		super("Re", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265a';
		} else {
			simbolo = '\u2654';
		}
		// TODO Auto-generated constructor stub
	}

}
