package pedine;

import it.uniba.main.Colore;
import scacchiera.*;

/**
 * Classe per rappresentere il sottotipo di pezzo chiamato Pedone
 */
public final class Pedone extends Pezzo {

	private boolean primaMossa;

	/**
	 * Costruttore
	 */
	public Pedone(final Colore colore, final Cella posizioneCorrente) {
		super("Pedone", colore, posizioneCorrente);
		this.setPrimaMossa(true);
		if (colore == Colore.nero) {
			simbolo = '\u265f';
		} else {
			simbolo = '\u2659';
		}
	}

	/**
  * Restituisce vero se è la prima mossa, falso altrimenti
  */
	public boolean primaMossa() {
		return this.primaMossa;
	}

	/**
	 * Aggiorna la variabile prima mossa
	 */
	public void setPrimaMossa(final boolean primaMossa) {
		this.primaMossa = primaMossa;
	}
	
	public boolean isMossaValida(String mossa, Scacchiera s) {
		return true;
	}	
}
