package pedine;

import it.uniba.main.Colore;
import scacchiera.Cella;

/**
 * Classe per rappresentere il sottotipo di pezzo chiamato Pedone
 */
public final class Pedone extends Pezzo {

	private boolean primaMossa;

	/**
	 * Costruttore
	 */
	public Pedone(final Colore colore, final Cella posizioneCorrente, final String simbolo) {
		super("Pedone", colore, posizioneCorrente, simbolo);
		this.setPrimaMossa(true);
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

}
