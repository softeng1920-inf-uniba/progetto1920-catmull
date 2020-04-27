package pedine;

import java.util.ArrayList;

import giocatore.Giocatore;
import it.uniba.main.Colore;
import scacchiera.Cella;
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
		// controllo se può mangiare pezzo
		if (end.isOccupato() == true && end.getPezzoCorrente().getColore() == this.colore) {
			return false;
		}
		if (!isScacco(end, s)) {
			// MOVIMENTI LINEARI
			// sulla stessa colonna
			if (start.getX() == end.getX()) {
				if (start.getY() == end.getY() + 1)
					return true;
				if (start.getY() == end.getY() - 1)
					return true;
			}
			// sulla stessa riga
			if (start.getY() == end.getY()) {
				if (start.getX() == end.getX() + 1)
					return true;
				if (start.getX() == end.getX() - 1)
					return true;
			}
			// MOVIMENTI DIAGONALI
			if (start.getX() == end.getX() + 1 && start.getX() == end.getX() + 1)
				return true;
			if (start.getX() == end.getX() - 1 && start.getX() == end.getX() - 1)
				return true;
			if (start.getX() == end.getX() + 1 && start.getX() == end.getX() - 1)
				return true;
			if (start.getX() == end.getX() - 1 && start.getX() == end.getX() + 1)
				return true;

		}
		return false;
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String convertiMossa(String mossa, Scacchiera s, Giocatore g) {
		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";
		// mossa semplice
		if (mossa.matches("(R)[a-h][1-8]")) {
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
			if (s.getNomePezzo(endX, endY) != "Vuota")
				return mossaConvertita;
		}
		// mossa di cattura
		if (mossa.matches("(R)(x|:)[a-h][1-8]")) {
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (s.getNomePezzo(endX, endY) == "Vuota")
				return mossaConvertita;
		}
		// ricerca il re del giocatore in turno
		for (int i = 0; i < s.getNumeroColonne(); i++) {
			for (int j = 0; j < s.getNumeroRighe(); j++) {
				if (s.getNomePezzo(i, j) == "Re" && s.getCella(i, j).getPezzoCorrente().getColore() == g.getColore()) {
					startX = i;
					startY = j;
					break;
				}
			}
			if (startX != -1)
				break;
		}
		// solo se ha trovato il re ha senso convertire la mossa
		if (startX != -1 && startY != -1) {
			mossaConvertita = Cella.coordXinChar(startX) + "" + +Cella.coordYinChar(startY) + " "
					+ Cella.coordXinChar(endX) + "" + +Cella.coordYinChar(endY);
		}
		System.out.println(mossaConvertita);
		return mossaConvertita;
	}

	public boolean isScacco(Cella ReCella, Scacchiera s) {
		Re reTemp=new Re(this.colore,ReCella);
		Cella temp = new Cella(ReCella.getX(), ReCella.getY(), reTemp);
		for (int i = 0; i < s.getNumeroRighe(); i++) {
			for (int j = 0; j < s.getNumeroColonne(); j++) {
				if (s.getNomePezzo(i, j) != "Vuota" && s.getCella(i, j).getPezzoCorrente().getColore() != this.colore
						&& s.getNomePezzo(i,
								j) != "Re"
						&& s.getCella(i, j).getPezzoCorrente().isMossaValida(s.getCella(i, j), temp, s))
					return true;
			}
		}
		return false;
	}
}
