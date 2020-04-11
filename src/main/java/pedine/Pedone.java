package pedine;

import scacchiera.Cella;

/** Classe per rappresentere il sottotipo di pezzo chiamato Pedone */
public final class Pedone extends Pezzo {

	private boolean primaMossa;

	/** Costruttore */
	public Pedone(final boolean colore, final Cella posizioneCorrente) {
		super("Pedone", colore, posizioneCorrente);
		this.primaMossa = true;
		// TODO Auto-generated constructor stub
	}

	/** Restituisce vero se è la prima mossa, falso altrimenti */
	public boolean primaMossa() {
		return this.primaMossa;
	}

	/** Aggiorna la variabile prima mossa */
	public void primaMossa(final boolean primaMossa) {
		this.primaMossa = primaMossa;
	}

	@Override
	/** Disegna il pezzo in output */
	public void disegnapezzo() {
		// TODO Auto-generated method stub
		char simbolo;
		if (getColore()) {
			simbolo = 'P';
		} else {
			simbolo = 'p';
		}
		System.out.print(" " + simbolo + " ");
	}
}
