package pedine;

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

	public boolean isMossaValidaNero(String mossa, Scacchiera s) {
		return false; // non posso muovere altri pedoni, cioè opposti
	}

	public boolean isMossaValidaBianco(String mossa, Scacchiera s) {
		return false;
	}
	
	@Override
	public final boolean isEnPassant(String comando, Scacchiera s) {
		return false;
	}
}
