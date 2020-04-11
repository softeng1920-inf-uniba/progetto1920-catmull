package pedine;

import scacchiera.Cella;

/** Classe per rappresentere il sottotipo di pezzo chiamato Re */
public final class Re extends Pezzo {

	/** Costruttore */
	public Re(final boolean colore, final Cella posizioneCorrente) {
		super("Re", colore, posizioneCorrente);
		// TODO Auto-generated constructor stub
	}

	@Override
	/** Disegna il pezzo in output */
	public void disegnapezzo() {
		// TODO Auto-generated method stub
		char simbolo;
		if (getColore()) {
			simbolo = 'K';
		} else {
			simbolo = 'k';
		}
		System.out.print(" " + simbolo + " ");
	}
}
