package pedine;

import java.util.ArrayList;

import gioco.Giocatore;
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
	public boolean isMossaValida(Cella start, Cella end) {
		// aumenta x e aumenta y = aumenta x diminuisce y
		int j;
		if (end.getX() - start.getX() == end.getY() - start.getY() && end.getX() - start.getX() > 0) {
			j = start.getY() + 1;
			for (int i = start.getX() + 1; end.getX() > i && end.getY() > j; i++) {
				if (Scacchiera.getCella(i, j).isOccupato())
					return false;
				j++;
			}
		}
		// diminuisce x e aumenta y
		else if (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())
				&& end.getX() - start.getX() < 0 && end.getY() - start.getY() > 0) {
			j = start.getY() + 1;
			for (int i = start.getX() - 1; end.getX() < i && end.getY() > j; i--) {
				if (Scacchiera.getCella(i, j).isOccupato())
					return false;
				j++;

			}
		}
		// diminuisce x e diminuisce y
		else if (end.getX() - start.getX() == end.getY() - start.getY() && end.getX() - start.getX() < 0) {
			j = start.getY() - 1;
			for (int i = start.getX() - 1; end.getX() < i && end.getY() < j; i--) {
				if (Scacchiera.getCella(i, j).isOccupato())
					return false;
				j--;
			}
		}

		// aumenta x e diminuisce y
		else if (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())
				&& end.getX() - start.getX() > 0 && end.getY() - start.getY() < 0) {
			j = start.getY() - 1;
			for (int i = start.getX() + 1; end.getX() > i && end.getY() < j; i++) {
				if (Scacchiera.getCella(i, j).isOccupato())
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
	public boolean isMossaSpecialeValida(Cella start, Cella end, ArrayList<String> mosse) {
		return false;
	}

	public static String convertiMossa(String mossa, Giocatore g) {
		// "A(x|:)?[a-h][1-8]"

		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";
		if (mossa.matches("A[a-h][1-8]")) {// si muove senza ambiguit�
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
			if (Scacchiera.getNomePezzo(endX, endY) != "Vuota") {
				return mossaConvertita;
			}
		}

		if (mossa.matches("A[x|:][a-h][1-8]")) { // mangia senza ambiguit�
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (Scacchiera.getNomePezzo(endX, endY) == "Vuota") {
				return mossaConvertita;
			}
		}
    
		// aumenta x e aumenta y
		for (int i = 1; Scacchiera.isRangeValido(endX + i, endY + i); i++) {
			if (Scacchiera.getNomePezzo(endX + i, endY + i) == "Alfiere"
					&& Scacchiera.getCella(endX + i, endY + i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX + i;
				startY = endY + i;

				break;
			}
		}
		// aumenta x e diminuisce y
		for (int i = 1; Scacchiera.isRangeValido(endX + i, endY - i); i++) {
			if (Scacchiera.getNomePezzo(endX + i, endY - i) == "Alfiere"
					&& Scacchiera.getCella(endX + i, endY - i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX + i;
				startY = endY - i;

				break;
			}
		}
		// diminuisce x e aumenta y
		for (int i = 1; Scacchiera.isRangeValido(endX - i, endY + i); i++) {
			if (Scacchiera.getNomePezzo(endX - i, endY + i) == "Alfiere"
					&& Scacchiera.getCella(endX - i, endY + i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX - i;
				startY = endY + i;

				break;
			}
		}
		// diminuisce x e diminuisce y
		for (int i = 1; Scacchiera.isRangeValido(endX - i, endY - i); i++) {
			if (Scacchiera.getNomePezzo(endX - i, endY - i) == "Alfiere"
					&& Scacchiera.getCella(endX - i, endY - i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX - i;
				startY = endY - i;

				break;
			}
		}

		if (Scacchiera.isRangeValido(startX, startY) && Scacchiera.getCella(startX, startY).isOccupato()) {

			mossaConvertita = Cella.coordXinChar(startX) + "" + +Cella.coordYinChar(startY) + " "
					+ Cella.coordXinChar(endX) + "" + +Cella.coordYinChar(endY);
		}
		return mossaConvertita;

	}

}
