package pedine;

import scacchiera.Cella;

public class Regina extends Pezzo{

	public Regina(boolean colore, Cella posizioneCorrente) {
		super("Regina", colore, posizioneCorrente);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void disegnapezzo() {
		// TODO Auto-generated method stub
		char simbolo=getColore() ? 'Q' : 'q';
		System.out.print(" "+simbolo+" ");
	}



}
