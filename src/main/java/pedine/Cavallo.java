package pedine;

import scacchiera.Cella;

public class Cavallo extends Pezzo
{

	public Cavallo(boolean colore, Cella posizioneCorrente) 
	{
		super("Cavallo", colore, posizioneCorrente);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void disegnapezzo() 
	{
		// TODO Auto-generated method stub
		 char simbolo=getColore() ? 'N' : 'n';
		 System.out.print(" "+simbolo+" ");
	}
}