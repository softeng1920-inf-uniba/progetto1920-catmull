package pedine;

import java.util.ArrayList;

import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

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
