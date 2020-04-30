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
		// "[A](x|:)?[a-h][1-8]"

		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";
		if (mossa.matches("[A][a-h][1-8]")) {// si muove senza ambiguit�
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
			if (Scacchiera.getNomePezzo(endX, endY) != "Vuota") {
				return mossaConvertita;
			}
		}

		if (mossa.matches("[A][x|:][a-h][1-8]")) { // mangia senza ambiguit�
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (Scacchiera.getNomePezzo(endX, endY) == "Vuota") {
				return mossaConvertita;
			}
		}
		if (mossa.matches("[A][1-8][x|:][a-h][1-8]")) {// mangia con ambiguit� sulle righe(numeri)
			endX = Cella.coordXinInt(mossa.charAt(3));
			endY = Cella.coordYinInt(mossa.charAt(4));
			startY = Cella.coordYinInt(mossa.charAt(1));
		}
		if (mossa.matches("[A][a-h][x|:][a-h][1-8]")) {// mangia con ambiguit� sulle colonne(lettere)
			endX = Cella.coordXinInt(mossa.charAt(3));
			endY = Cella.coordYinInt(mossa.charAt(4));
			startX = Cella.coordXinInt(mossa.charAt(1));

		}
		if (mossa.matches("[A][1-8][a-h][1-8]")) {// si muove con ambiguit� sulle righe
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			startY = Cella.coordYinInt(mossa.charAt(1));
		}
		if (mossa.matches("[A][a-h][a-h][1-8]")) {// si muove con ambiguit� sulle colonne
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			startX = Cella.coordXinInt(mossa.charAt(1));
		}
		if (startX != -1) {
			for (int i = 0; i < Scacchiera.getNumeroRighe(); i++) {
				if (Scacchiera.getNomePezzo(startX, i) == "Alfiere"
						&& Scacchiera.getCella(startX, i).getPezzoCorrente().getColore() == g.getColore()) {
					startY = i;
					break;
				}
			}

		} else if (startY != -1) {
			for (int i = 0; i < Scacchiera.getNumeroColonne(); i++) {
				if (Scacchiera.getNomePezzo(i, startY) == "Alfiere"
						&& Scacchiera.getCella(i, startY).getPezzoCorrente().getColore() == g.getColore()) {
					startX = i;
					break;
				}
			}

		}
		// aumenta x e aumenta y
		for (int i = 1; endX + i < Scacchiera.getNumeroColonne() && endY + i < Scacchiera.getNumeroRighe(); i++) {
			if (Scacchiera.getNomePezzo(endX + i, endY + i) == "Alfiere"
					&& Scacchiera.getCella(endX + i, endY + i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX + i;
				startY = endY + i;

				break;
			}
		}
		// aumenta x e diminuisce y
		for (int i = 1; endX + i < Scacchiera.getNumeroColonne() && endY - i < Scacchiera.getNumeroRighe(); i++) {
			if (Scacchiera.getNomePezzo(endX + i, endY - i) == "Alfiere"
					&& Scacchiera.getCella(endX + i, endY - i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX + i;
				startY = endY - i;

				break;
			}
		}
		// diminuisce x e aumenta y
		for (int i = 1; endX - i < Scacchiera.getNumeroColonne() && endY + i < Scacchiera.getNumeroRighe(); i++) {
			if (Scacchiera.getNomePezzo(endX - i, endY + i) == "Alfiere"
					&& Scacchiera.getCella(endX - i, endY + i).getPezzoCorrente().getColore() == g.getColore()) {
				startX = endX - i;
				startY = endY + i;

				break;
			}
		}
		// diminuisce x e diminuisce y
		for (int i = 1; endX - i < Scacchiera.getNumeroColonne() && endY - i < Scacchiera.getNumeroRighe(); i++) {
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
		System.out.println(mossaConvertita);
		return mossaConvertita;

	}

}
