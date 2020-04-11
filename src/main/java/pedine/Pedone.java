package pedine;

import scacchiera.Cella;

public class Pedone extends Pezzo {

	private boolean primaMossa;

	public Pedone(boolean colore, Cella posizioneCorrente) {
		super("Pedone", colore, posizioneCorrente);
		this.primaMossa = true;
		// TODO Auto-generated constructor stub
	}

	public boolean primaMossa() {
		return this.primaMossa;
	}

	public void primaMossa(boolean primaMossa) {
		this.primaMossa = primaMossa;
	}

	@Override
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
