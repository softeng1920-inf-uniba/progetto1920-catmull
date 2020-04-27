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
		// TODO Auto-generated method stub
		// movimento in 4 direzioni
		/*if (((Math.abs(start.getY() - end.getY()) >= 1)) && (Math.abs(start.getX() - end.getX()) >= 1)
				&& end.isOccupato() && end.getPezzoCorrente().getColore() == null)
			return true;
		if (((Math.abs(start.getY() - end.getY()) <= 1)) && (Math.abs(start.getX() - end.getX()) <= 1)
				&& end.isOccupato() && end.getPezzoCorrente().getColore() == null)
			return true;
		if (((Math.abs(start.getY() - end.getY()) <= 1)) && (Math.abs(start.getX() - end.getX()) >= 1)
				&& end.isOccupato() && end.getPezzoCorrente().getColore() == null)
			return true;
		if (((Math.abs(start.getY() - end.getY()) >= 1)) && (Math.abs(start.getX() - end.getX()) >= 1)
				&& end.isOccupato() && end.getPezzoCorrente().getColore() == null)*/
			return true;
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String ConvertiMossa(String mossa, Scacchiera s, Giocatore g) {
		// "[A]([a-h]|[1-8])(x|:)[a-h][1-8]"
		// "[A]([a-h]|[1-8])[a-h][1-8]"
		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";
		int ambiguita = 0;

		if (mossa.matches("[A][a-h][1-8]")) {// si muove senza ambiguità
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
		}

		if (mossa.matches("[A][x|:][a-h][1-8]")) { // mangia senza ambiguità
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
		}
		if (mossa.matches("[A][1-8][x|:][a-h][1-8]")) {// mangia con ambiguità sulle righe(numeri)
			endX = Cella.coordXinInt(mossa.charAt(3));
			endY = Cella.coordYinInt(mossa.charAt(4));
			startY = Cella.coordYinInt(mossa.charAt(1));
		}
		if (mossa.matches("[A][a-h][x|:][a-h][1-8]")) {// mangia con ambiguità sulle colonne(lettere)
			endX = Cella.coordXinInt(mossa.charAt(3));
			endY = Cella.coordYinInt(mossa.charAt(4));
			startX = Cella.coordXinInt(mossa.charAt(1));

		}
		if (mossa.matches("[A][1-8][a-h][1-8]")) {// si muove con ambiguità sulle righe
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			startY = Cella.coordYinInt(mossa.charAt(1));
		}
		if (mossa.matches("[A][a-h][a-h][1-8]")) {// si muove con ambiguità sulle colonne
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			startX = Cella.coordXinInt(mossa.charAt(1));
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

		} else {
			// aumenta x e aumenta y
			for (int i = 1; endX + i < s.getNumeroColonne() && endY + i < s.getNumeroRighe(); i++) {
				if (s.getNomePezzo(endX + i, endY + i) == "Alfiere"
						&& s.getCella(endX + i, endY + i).getPezzoCorrente().getColore() == g.getColore()) {
					startX = endX + i;
					startY = endY + i;
					ambiguita++;
					break;
				}
			}
			// aumenta x e diminuisce y
			for (int i = 1; endX + i < s.getNumeroColonne() && endY - i < s.getNumeroRighe(); i++) {
				if (s.getNomePezzo(endX + i, endY - i) == "Alfiere"
						&& s.getCella(endX + i, endY - i).getPezzoCorrente().getColore() == g.getColore()) {
					startX = endX + i;
					startY = endY - i;
					ambiguita++;
					break;
				}
			}
			// diminuisce x e aumenta y
			for (int i = 1; endX - i < s.getNumeroColonne() && endY + i < s.getNumeroRighe(); i++) {
				if (s.getNomePezzo(endX - i, endY + i) == "Alfiere"
						&& s.getCella(endX - i, endY + i).getPezzoCorrente().getColore() == g.getColore()) {
					startX = endX - i;
					startY = endY + i;
					ambiguita++;
					break;
				}
			}
			// diminuisce x e diminuisce y
			for (int i = 1; endX - i < s.getNumeroColonne() && endY - i < s.getNumeroRighe(); i++) {
				if (s.getNomePezzo(endX - i, endY - i) == "Alfiere"
						&& s.getCella(endX - i, endY - i).getPezzoCorrente().getColore() == g.getColore()) {
					startX = endX - i;
					startY = endY - i;
					ambiguita++;
					break;
				}
			}
		}
		if (s.controllaRange(startX, startY) && s.getCella(startX, startY).isOccupato() && ambiguita <= 1) {

			mossaConvertita = Cella.coordXinChar(startX) + "" + +Cella.coordYinChar(startY) + " "
					+ Cella.coordXinChar(endX) + "" + +Cella.coordYinChar(endY);
		}
		System.out.println(mossaConvertita);
		return mossaConvertita;

	}

}
