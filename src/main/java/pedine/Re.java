package pedine;

import scacchiera.Cella;

public class Re extends Pezzo{

	public Re(boolean colore, Cella posizioneCorrente) {
		super("Re", colore, posizioneCorrente);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void disegnapezzo() {
		// TODO Auto-generated method stub
		char simbolo=getColore() ? 'K' : 'k';
		System.out.print(" "+simbolo+" ");
	}



}


