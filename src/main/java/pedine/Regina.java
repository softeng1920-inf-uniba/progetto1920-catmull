package pedine;

import scacchiera.Cella;

/** Classe per rappresentere il sottotipo di pezzo chiamato Regina */
public final class Regina extends Pezzo {

	/** Costruttore */
	public Regina(final boolean colore, final Cella posizioneCorrente) {
		super("Regina", colore, posizioneCorrente);
		// TODO Auto-generated constructor stub
	}

	@Override
	/** Disegna il pezzo in output */
	public void disegnapezzo() {
		// TODO Auto-generated method stub
		char simbolo;
		if (getColore()) {
			simbolo = 'Q';
		} else {
			simbolo = 'q';
		}
		System.out.print(" " + simbolo + " ");
	}
}
