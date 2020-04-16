package pedine;

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
	
	public boolean isMossaValidaNero(String mossa, Scacchiera s) {
		return false;// non posso muovere altri pedoni, cioè opposti
	}	
	
	public boolean isMossaValidaBianco(String mossa, Scacchiera s) {
		return false;
	}
}
