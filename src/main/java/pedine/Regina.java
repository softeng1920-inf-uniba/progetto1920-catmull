package pedine;

import scacchiera.Cella;

public class Regina extends Pezzo {

	public Regina(boolean colore, Cella posizioneCorrente) {
		super("Regina", colore, posizioneCorrente);
		// TODO Auto-generated constructor stub
	}

	@Override
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
