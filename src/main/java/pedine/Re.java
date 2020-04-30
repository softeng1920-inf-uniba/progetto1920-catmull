package pedine;

import java.util.ArrayList;

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
	}

	@Override
	public boolean isMossaValida(Cella start, Cella end) {
		// TODO Se la mossa è valida dobbiamo impostare il primaMossaEffettuata a falso

		return false;
	}

	@Override
	public boolean isMossaSpecialeValida(Cella start, Cella end, ArrayList<String> mosse) {
			return false;
	}

}
