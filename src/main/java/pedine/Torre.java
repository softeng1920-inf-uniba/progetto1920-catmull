package pedine;

import scacchiera.Cella;

/**
 * Realizzazione della classe Torre, estensione della classe Pezzo
 */
public final class Torre extends Pezzo {

	public Torre(final boolean colore, final Cella posizioneCorrente) {
		super("Regina", colore, posizioneCorrente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void disegnapezzo() {
		// TODO Auto-generated method stub
		char simbolo;
		if (getColore()) {
			simbolo = 'R';
		} else {
			simbolo = 'r';
		}
		System.out.print(" " + simbolo + " ");
	}
}
