package scacchiera.pedine;

import gioco.Colore;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi ,definisce se il
 * movimento della Regina � valido. La classe Regina e' di tipo noECB
 */
public final class Regina extends Pezzo {

    /** Costruttore */
    public Regina(final Colore colore) {
	super("Regina", colore);
	if (colore == Colore.nero) {
	    setSimbolo('\u265b');
	} else {
	    setSimbolo('\u2655');
	}
    }

    @Override
    public boolean isMossaValida(final Cella start, final Cella end) {

	int deltaX = end.getX() - start.getX();
	int deltaY = end.getY() - start.getY();
	int j;
	// MOVIMENTI LINEARI
	// stessa x, aumenta y
	if (end.getX() == start.getX() && end.getY() > start.getY()) {
	    for (int i = start.getY() + 1; end.getY() > i; i++) {
		if (Scacchiera.getInstance().getCella(end.getX(), i).isOccupato()) {

		    return false;
		}
	    }
	} else if (end.getX() == start.getX() && end.getY() < start.getY()) {
	    // stessa x, diminuisce y
	    for (int i = start.getY() - 1; end.getY() < i; i--) {
		if (Scacchiera.getInstance().getCella(end.getX(), i).isOccupato()) {
		    return false;
		}
	    }
	} else if (end.getY() == start.getY() && end.getX() > start.getX()) {
	    // aumenta x, stessa y
	    for (int i = start.getX() + 1; end.getX() > i; i++) {
		if (Scacchiera.getInstance().getCella(i, end.getY()).isOccupato()) {
		    return false;
		}
	    }
	} else if (end.getY() == start.getY() && end.getX() < start.getX()) {
	    // diminuisce x, stessa y
	    for (int i = start.getX() - 1; end.getX() < i; i--) {
		if (Scacchiera.getInstance().getCella(i, end.getY()).isOccupato()) {
		    return false;
		}
	    }
	} else if (deltaX == deltaY && deltaX > 0) {
	    // MOVIMENTI DIAGONALI (ALFIERE)
	    // aumenta x e aumenta y
	    j = start.getY() + 1;
	    for (int i = start.getX() + 1; end.getX() > i && end.getY() > j; i++) {
		if (Scacchiera.getInstance().getCella(i, j).isOccupato()) {
		    return false;
		}
		j++;
	    }
	} else if (Math.abs(deltaX) == Math.abs(deltaY) && deltaX < 0 && deltaY > 0) {
	    // diminuisce x e aumenta y
	    j = start.getY() + 1;
	    for (int i = start.getX() - 1; end.getX() < i && end.getY() > j; i--) {
		if (Scacchiera.getInstance().getCella(i, j).isOccupato()) {
		    return false;
		}
		// diminuisce x e aumenta y
		else if (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())
				&& end.getX() - start.getX() < 0 && end.getY() - start.getY() > 0) {
			j = start.getY() + 1;
			for (int i = start.getX() - 1; end.getX() < i && end.getY() > j; i--) {
				if (Scacchiera.getCella(i, j).isOccupato()) {
					return false;
				}
				j++;
	    }
	} else if (deltaX == deltaY && deltaX < 0) {
	    // diminuisce x e diminuisce y
	    j = start.getY() - 1;
	    for (int i = start.getX() - 1; end.getX() < i && end.getY() < j; i--) {
		if (Scacchiera.getInstance().getCella(i, j).isOccupato()) {
		    return false;
		}
		j--;
	    }
	} else if (Math.abs(deltaX) == Math.abs(end.getY() - start.getY()) && deltaX > 0 && deltaY < 0) {
	    // aumenta x e diminuisce y
	    j = start.getY() - 1;
	    for (int i = start.getX() + 1; end.getX() > i && end.getY() < j; i++) {
		if (Scacchiera.getInstance().getCella(i, j).isOccupato()) {
		    return false;
		}

		// aumenta x e diminuisce y
		else if (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())
				&& end.getX() - start.getX() > 0 && end.getY() - start.getY() < 0) {
			j = start.getY() - 1;
			for (int i = start.getX() + 1; end.getX() > i && end.getY() < j; i++) {
				if (Scacchiera.getCella(i, j).isOccupato()) {
					return false;
				}
				j--;
			}
		} else {
			return false;
		}
		j--;
	    }
	} else if (Math.abs(deltaX) == Math.abs(end.getY() - start.getY()) && deltaX > 0 && deltaY < 0) {
	    // aumenta x e diminuisce y
	    j = start.getY() - 1;
	    for (int i = start.getX() + 1; end.getX() > i && end.getY() < j; i++) {
		if (Scacchiera.getCella(i, j).isOccupato()) {
		    return false;
		}
		j--;
	    }
	} else {
	    return false;
	}
	// controllo se puo' mangiare pezzo
	if (end.isOccupato() && end.getPezzoCorrente().getColore() == getColore()) {
	    return false;
	}
	return true;
    }

    /**
     * Converte la mossa in input nell stringa con le coordinate della cella
     * iniziale e le coordinate della cella finale
     *
     * @param mossa
     * @return
     */
    public static String convertiMossa(final String mossa) {
	int startX = -1;
	int startY = -1;
	int endX = -1;
	int endY = -1;
	String mossaConvertita = "a0 a0";
	Colore coloreGiocatoreAttuale = Turno.getInstance().getGiocatoreInTurno().getColore();
	// mossa semplice
	if (mossa.matches("D[a-h][1-8]")) {
	    final int colonnaDestinazioneAvanzata = 1;
	    final int traversaDestinazioneAvanzata = 2;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneAvanzata));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneAvanzata));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) != "Vuota") {
		return mossaConvertita;
	    }
	}
	// mossa di cattura
	if (mossa.matches("D(x|:)[a-h][1-8]")) {
	    final int colonnaDestinazioneCattura = 2;
	    final int traversaDestinazioneCattura = 3;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneCattura));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneCattura));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) == "Vuota") {
		return mossaConvertita;
	    }
	}
	// ricerca la regina del giocatore in turno
	for (int i = 0; i < Scacchiera.getInstance().getNumeroColonne(); i++) {
	    for (int j = 0; j < Scacchiera.getInstance().getNumeroRighe(); j++) {
		if (Scacchiera.getInstance().getNomePezzo(i, j) == "Regina" && Scacchiera.getInstance().getCella(i, j)
			.getPezzoCorrente().getColore() == coloreGiocatoreAttuale) {
		    startX = i;
		    startY = j;
		    break;
		}
		// mossa di cattura
		if (mossa.matches("D(x|:)[a-h][1-8]")) {
			final int colonnaDestinazioneCattura = 2;
			final int traversaDestinazioneCattura = 3;
			endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneCattura));
			endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneCattura));
			if (Scacchiera.getNomePezzo(endX, endY) == "Vuota") {
				return mossaConvertita;
			}
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
			if (startX != -1) {
				break;
			}
		}
		// solo se ha trovato la regina ha senso convertire la mossa
		if (startX != -1 && startY != -1) {
			mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " "
					+ Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
		}
		return mossaConvertita;
	    }
	}
	// mossa di cattura
	if (mossa.matches("D(x|:)[a-h][1-8]")) {
	    final int colonnaDestinazioneCattura = 2;
	    final int traversaDestinazioneCattura = 3;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneCattura));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneCattura));
	    if (Scacchiera.getNomePezzo(endX, endY) == "Vuota") {
		return mossaConvertita;
	    }
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
	    if (startX != -1) {
		break;
	    }
	}
	// solo se ha trovato la regina ha senso convertire la mossa
	if (startX != -1 && startY != -1) {
	    mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " "
		    + Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
	}
	return mossaConvertita;
    }

}