package pedine;

import java.util.ArrayList;

import it.uniba.main.Colore;
import scacchiera.Cella;
import giocatore.Giocatore;
import scacchiera.Scacchiera;

/** Classe per rappresentere il sottotipo di pezzo chiamato Re */
public final class Re extends Pezzo {

	/** Costruttore */
	public Re(final Colore colore, final Cella posizioneCorrente) {
		super("Re", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265a';
		} else {
			simbolo = '\u2654';
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMossaValida(Cella start, Cella end, Scacchiera s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static String convertiMossa(String mossa, Scacchiera s, Giocatore g) {
		int startX = 4;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";
		if (mossa.matches("0-0")) { //arrocco corto 
			endX = 6;
			if (g.getColore() == Colore.bianco && s.getNomePezzo(4, 7) == "Re") {
				if(s.getNomePezzo(7, 7) == "Torre") {
					startY = 7;
					endY = 7;
				}
			}
			else if (g.getColore() == Colore.nero && s.getNomePezzo(4, 0) == "Re") {
				if(s.getNomePezzo(0, 0) == "Torre") {
					startY = 0;
					endY = 0;
				}
			}
		}
		
		else if (mossa.matches("0-0-0")) { //arrocco lungo
			endX = 1;
			if (g.getColore() == Colore.bianco && s.getNomePezzo(4, 7) == "Re") {
				if(s.getNomePezzo(0, 7) == "Torre") {
					startY = 7;
					endY = 7;
				}
			}
			else if (g.getColore() == Colore.nero && s.getNomePezzo(4, 0) == "Re") {
				if(s.getNomePezzo(0, 7) == "Torre") {
					startY = 0;
					endY = 0;
				}
			}
		}
		
		mossaConvertita = Cella.coordXinChar(startX) + "" +
					+ Cella.coordYinChar(startY) + " " 
					+ Cella.coordXinChar(endX) + "" +
					+ Cella.coordYinChar(endY);
		
		System.out.println(mossaConvertita);
		return mossaConvertita;
	}
}
