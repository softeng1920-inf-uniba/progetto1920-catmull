package pedine;

import java.util.ArrayList;

import giocatore.Giocatore;
import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/** Classe per rappresentere il sottotipo di pezzo chiamato Regina */
public final class Regina extends Pezzo {

	/** Costruttore */
	public Regina(final Colore colore, final Cella posizioneCorrente) {
		super("Regina", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265b';
		} else {
			simbolo = '\u2655';
		}
		// TODO Auto-generated constructor stub
	}

	/**
	 * Controlla se la mossa della regina è valida
	 */
	@Override
	public boolean isMossaValida(Cella start, Cella end, Scacchiera s) {
		// MOVIMENTI LINEARI
		// stessa x, aumenta y
		if (end.getX() == start.getX() && end.getY() > start.getY()) {
			for (int j = start.getY() + 1; end.getY() > j; j++) {
				if (s.getCella(end.getX(), j).isOccupato()) {
					return false;
				}
			}
		}
		// stessa x, diminuisce y
		if (end.getX() == start.getX() && end.getY() < start.getY()) {
			for (int j = start.getY() - 1; end.getY() < j; j--) {
				if (s.getCella(end.getX(), j).isOccupato()) {
					return false;
				}
			}
		}
		// aumenta x, stessa y
		if (end.getY() == start.getY() && end.getX() > start.getX()) {
			for (int j = start.getX() + 1; end.getX() > j; j++) {
				if (s.getCella(j, end.getY()).isOccupato()) {
					return false;
				}
			}
		}
		// diminuisce x, stessa y
		if (end.getY() == start.getY() && end.getX() < start.getX()) {
			for (int j = start.getX() - 1; end.getX() < j; j--) {
				if (s.getCella(j, end.getY()).isOccupato()) {
					return false;
				}
			}
		}

		// MOVIMENTI DIAGONALI (ALFIERE)
		if (end.getX() > start.getX() && end.getY() > start.getY()) {
			for (int i = start.getX() + 1; end.getX() > i; i++) {
				for (int j = start.getY() + 1; end.getY() > j; j++) {
					if (s.getCella(i, j).isOccupato()) {
						return false;
					}
				}
			}
		}
		// diminuisce x e aumenta y
		if (end.getX() < start.getX() && end.getY() > start.getY()) {
			for (int i = start.getX() - 1; end.getX() < i; i--) {
				for (int j = start.getY() + 1; end.getY() > j; j++) {
					if (s.getCella(i, j).isOccupato()) {
						return false;
					}
				}
			}
		}
		// diminuisce x e diminuisce y
		if (end.getX() < start.getX() && end.getY() < start.getY()) {
			for (int i = start.getX() - 1; end.getX() < i; i--) {
				for (int j = start.getY() - 1; end.getY() < j; j--) {
					if (s.getCella(i, j).isOccupato()) {
						return false;
					}
				}
			}
		}

		// aumenta x e diminuisce y
		if (end.getX() > start.getX() && end.getY() > start.getY()) {
			for (int i = start.getX() + 1; end.getX() > i; i++) {
				for (int j = start.getY() - 1; end.getY() < j; j--) {
					if (s.getCella(i, j).isOccupato()) {
						return false;
					}
				}
			}
		}
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

	/**
	 * Converte la mossa in input nell stringa con le coordinate della cella
	 * iniziale e le coordinate della cella finale
	 *
	 * @param mossa
	 * @param s
	 * @param g
	 * @return
	 */
	public static String convertiMossa(String mossa, Scacchiera s, Giocatore g) {
		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";
		// mossa semplice
		if (mossa.matches("(D)[a-h][1-8]")) {
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
			if (s.getNomePezzo(endX, endY) != "Vuota")
				return mossaConvertita;
		}
		// mossa di cattura
		if (mossa.matches("(D)(x|:)[a-h][1-8]")) {
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (s.getNomePezzo(endX, endY) == "Vuota")
				return mossaConvertita;
		}
		// ricerca la regina del giocatore in turno
		for (int i = 0; i < s.getNumeroColonne(); i++) {
			for (int j = 0; j < s.getNumeroRighe(); j++) {
				if (s.getNomePezzo(i, j) == "Regina"
						&& s.getCella(i, j).getPezzoCorrente().getColore() == g.getColore()) {
					startX = i;
					startY = j;
					break;
				}
			}
			if (startX != -1)
				break;
		}
		// solo se ha trovato la regina ha senso convertire la mossa
		if (startX != -1 && startY != -1) {
			mossaConvertita = Cella.coordXinChar(startX) + "" + +Cella.coordYinChar(startY) + " "
					+ Cella.coordXinChar(endX) + "" + +Cella.coordYinChar(endY);
		}
		return mossaConvertita;
	}

}
