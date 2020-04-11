package pedine;

import scacchiera.Cella;

public class Torre extends Pezzo {

	public Torre(boolean colore, Cella posizioneCorrente) {
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
