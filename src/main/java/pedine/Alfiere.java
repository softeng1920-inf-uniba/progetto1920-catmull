package pedine;

import java.util.ArrayList;

import giocatore.Giocatore;
import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Rappresentazione dell'astrazione del pezzo alfiere
 */
public final class Alfiere extends Pezzo {

	public Alfiere(final Colore colore, final Cella posizioneCorrente) {
		super("Alfiere", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265d';
		} else {
			simbolo = '\u2657';
		}
	}

	@Override
	public boolean isMossaValida(Cella start, Cella end, Scacchiera s) {
		// aumenta x e aumenta y = aumenta x diminuisce y
		int j;
		if (end.getX() - start.getX() == end.getY() - start.getY() && end.getX() - start.getX() > 0) {
			j = start.getY() + 1;
			for (int i = start.getX() + 1; end.getX() > i && end.getY() > j; i++) {
				if (s.getCella(i, j).isOccupato())
					return false;
				j++;
			}
		}
		// diminuisce x e aumenta y
		else if (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())
				&& end.getX() - start.getX() < 0 && end.getY() - start.getY() > 0) {
			j = start.getY() + 1;
			for (int i = start.getX() - 1; end.getX() < i && end.getY() > j; i--) {
				if (s.getCella(i, j).isOccupato())
					return false;
				j++;

			}
		}
		// diminuisce x e diminuisce y
		else if (end.getX() - start.getX() == end.getY() - start.getY() && end.getX() - start.getX() < 0) {
			j = start.getY() - 1;
			for (int i = start.getX() - 1; end.getX() < i && end.getY() < j; i--) {
				if (s.getCella(i, j).isOccupato())
					return false;
				j--;
			}
		}

		// aumenta x e diminuisce y
		else if (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())
				&& end.getX() - start.getX() > 0 && end.getY() - start.getY() < 0) {
			j = start.getY() - 1;
			for (int i = start.getX() + 1; end.getX() > i && end.getY() < j; i++) {
				if (s.getCella(i, j).isOccupato())
					return false;
				j--;
			}
		} else
			return false;
		// controllo se può mangiare pezzo
		if (end.isOccupato() == true && end.getPezzoCorrente().getColore() == this.colore) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String ConvertiMossa(String mossa, Scacchiera s, Giocatore g) {
		// "[A](x|:)?[a-h][1-8]"

		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";

		if (mossa.matches("[A][a-h][1-8]")) {// si muove senza ambiguità
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
			if (s.getNomePezzo(endX, endY) != "Vuota") {
				return mossaConvertita;
			}
		}

		if (mossa.matches("[A][x|:][a-h][1-8]")) { // mangia senza ambiguità
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (s.getNomePezzo(endX, endY) == "Vuota") {
				return mossaConvertita;
			}
		}

		if (startX != -1) {
			for (int i = 0; i < s.getNumeroRighe(); i++) {
				if (s.getNomePezzo(startX, i) == "Alfiere"
						&& s.getCella(startX, i).getPezzoCorrente().getColore() == g.getColore()) {
					startY = i;
					break;
				}
			}

		} else if (startY != -1) {
			for (int i = 0; i < s.getNumeroColonne(); i++) {
				if (s.getNomePezzo(i, startY) == "Alfiere"
						&& s.getCella(i, startY).getPezzoCorrente().getColore() == g.getColore()) {
					startX = i;
					break;
				}
			}

		}
		// aumenta x e aumenta y
		for (int i = 1; endX + i < s.getNumeroColonne() && endY + i < s.getNumeroRighe(); i++) {
			if (s.getNomePezzo(endX + i, endY + i) == "Alfiere"
					&& s.getCella(endX + i, endY + i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX + i;
				startY = endY + i;

				break;
			}
		}
		// aumenta x e diminuisce y
		for (int i = 1; endX + i < s.getNumeroColonne() && endY - i < s.getNumeroRighe(); i++) {
			if (s.getNomePezzo(endX + i, endY - i) == "Alfiere"
					&& s.getCella(endX + i, endY - i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX + i;
				startY = endY - i;

				break;
			}
		}
		// diminuisce x e aumenta y
		for (int i = 1; endX - i < s.getNumeroColonne() && endY + i < s.getNumeroRighe(); i++) {
			if (s.getNomePezzo(endX - i, endY + i) == "Alfiere"
					&& s.getCella(endX - i, endY + i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX - i;
				startY = endY + i;

				break;
			}
		}
		// diminuisce x e diminuisce y
		for (int i = 1; endX - i < s.getNumeroColonne() && endY - i < s.getNumeroRighe(); i++) {
			if (s.getNomePezzo(endX - i, endY - i) == "Alfiere"
					&& s.getCella(endX - i, endY - i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX - i;
				startY = endY - i;

				break;
			}
		}

		if (s.controllaRange(startX, startY) && s.getCella(startX, startY).isOccupato()) {

			mossaConvertita = Cella.coordXinChar(startX) + "" + +Cella.coordYinChar(startY) + " "
					+ Cella.coordXinChar(endX) + "" + +Cella.coordYinChar(endY);
		}
		return mossaConvertita;

	}

}
