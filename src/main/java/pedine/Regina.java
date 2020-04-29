package pedine;

import java.util.ArrayList;

import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/** Classe per rappresentere il sottotipo di pezzo chiamato Regina */
public final class Regina extends Pezzo {

	/** Costruttore */
	public Regina(final Colore colore, final Cella posizioneCorrente) {
		super("Regina", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265b';
		} else {
			simbolo = '\u2655';
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMossaValida(Cella start, Cella end, Scacchiera s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		// TODO Auto-generated method stub
		return false;
	}

}
