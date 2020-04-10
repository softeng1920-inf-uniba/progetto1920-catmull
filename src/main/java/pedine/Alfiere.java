package pedine;

import scacchiera.Cella;

public class Alfiere extends Pezzo
{

	public Alfiere(boolean colore, Cella posizioneCorrente) 
	{
		super("Alfiere", colore, posizioneCorrente);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void disegnapezzo() 
	{
		// TODO Auto-generated method stub
		 char simbolo=getColore() ? 'B' : 'b';
		 System.out.print(" "+simbolo+" ");
	}
}
