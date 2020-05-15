package scacchiera.pedine;
import gioco.Colore;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi ,definisce se il
 * movimento della Regina è valido. La classe Regina e' di tipo noECB
 */

public final class Regina extends Pezzo {

	/** Costruttore */
	public Regina(final Colore colore) {
		super("Regina", colore);
		if (colore == Colore.nero) {
			simbolo = '\u265b';
		} else {
			simbolo = '\u2655';
		}
	}

	/**
	 * Controlla se la mossa della regina e' valida
	 */
	@Override
	public boolean isMossaValida(Cella start, Cella end) {
		int j;
		// MOVIMENTI LINEARI
		// stessa x, aumenta y
		if (end.getX() == start.getX() && end.getY() > start.getY()) {
			for (int i = start.getY() + 1; end.getY() > i; i++) {
				if (Scacchiera.getCella(end.getX(), i).isOccupato()) {
					return false;
				}
			}
		}
		// stessa x, diminuisce y
		else if (end.getX() == start.getX() && end.getY() < start.getY()) {
			for (int i = start.getY() - 1; end.getY() < i; i--) {
				if (Scacchiera.getCella(end.getX(), i).isOccupato()) {
					return false;
				}
			}
		}
		// aumenta x, stessa y
		else if (end.getY() == start.getY() && end.getX() > start.getX()) {
			for (int i = start.getX() + 1; end.getX() > i; i++) {
				if (Scacchiera.getCella(i, end.getY()).isOccupato()) {
					return false;
				}
			}
		}
		// diminuisce x, stessa y
		else if (end.getY() == start.getY() && end.getX() < start.getX()) {
			for (int i = start.getX() - 1; end.getX() < i; i--) {
				if (Scacchiera.getCella(i, end.getY()).isOccupato()) {
					return false;
				}
			}
		}

		// MOVIMENTI DIAGONALI (ALFIERE)
		// aumenta x e aumenta y
		else if (end.getX() - start.getX() == end.getY() - start.getY() && end.getX() - start.getX() > 0) {
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
		// controllo se puo' mangiare pezzo
		if (end.isOccupato() == true && end.getPezzoCorrente().getColore() == this.colore) {
			return false;
		}
		return true;
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
	public static String convertiMossa(String mossa) {
		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";
		Colore coloreGiocatoreAttuale = Turno.getGiocatoreInTurno().getColore();
		// mossa semplice
		if (mossa.matches("D[a-h][1-8]")) {
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
			if (Scacchiera.getNomePezzo(endX, endY) != "Vuota")
				return mossaConvertita;
		}
		// mossa di cattura
		if (mossa.matches("D(x|:)[a-h][1-8]")) {
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (Scacchiera.getNomePezzo(endX, endY) == "Vuota")
				return mossaConvertita;
		}
		// ricerca la regina del giocatore in turno
		for (int i = 0; i < Scacchiera.getNumeroColonne(); i++) {
			for (int j = 0; j < Scacchiera.getNumeroRighe(); j++) {
				if (Scacchiera.getNomePezzo(i, j) == "Regina"
						&& Scacchiera.getCella(i, j).getPezzoCorrente().getColore() == coloreGiocatoreAttuale) {
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
			mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " " + Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
		}
		return mossaConvertita;
	}

}
