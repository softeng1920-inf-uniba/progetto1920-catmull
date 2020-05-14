package pedine;

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
    public Regina(final Colore colore, final Cella posizioneCorrente) {
	super("Regina", colore, posizioneCorrente);
	if (colore == Colore.nero) {
	    setSimbolo('\u265b');
	} else {
	    setSimbolo('\u2655');
	}
    }

    @Override
    public boolean isMossaValida(final Cella start, final Cella end) {
	int j;
	// MOVIMENTI LINEARI
	// stessa x, aumenta y
	if (end.getX() == start.getX() && end.getY() > start.getY()) {
	    for (int i = start.getY() + 1; end.getY() > i; i++) {
		if (Scacchiera.getCella(end.getX(), i).isOccupato()) {
		    return false;
		}
	    }
	} else if (end.getX() == start.getX() && end.getY() < start.getY()) { // stessa x, diminuisce y
	    for (int i = start.getY() - 1; end.getY() < i; i--) {
		if (Scacchiera.getCella(end.getX(), i).isOccupato()) {
		    return false;
		}
	    }
	} else if (end.getY() == start.getY() && end.getX() > start.getX()) { // aumenta x, stessa y
	    for (int i = start.getX() + 1; end.getX() > i; i++) {
		if (Scacchiera.getCella(i, end.getY()).isOccupato()) {
		    return false;
		}
	    }
	} else if (end.getY() == start.getY() && end.getX() < start.getX()) { // diminuisce x, stessa y
	    for (int i = start.getX() - 1; end.getX() < i; i--) {
		if (Scacchiera.getCella(i, end.getY()).isOccupato()) {
		    return false;
		}
	    }
	} else if (end.getX() - start.getX() == end.getY() - start.getY() && end.getX() - start.getX() > 0) {
	    // MOVIMENTI DIAGONALI (ALFIERE)
	    // aumenta x e aumenta y
	    j = start.getY() + 1;
	    for (int i = start.getX() + 1; end.getX() > i && end.getY() > j; i++) {
		if (Scacchiera.getCella(i, j).isOccupato()) {
		    return false;
		}
		j++;
	    }
	} else if (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())
		&& end.getX() - start.getX() < 0 && end.getY() - start.getY() > 0) { // diminuisce x e aumenta y
	    j = start.getY() + 1;
	    for (int i = start.getX() - 1; end.getX() < i && end.getY() > j; i--) {
		if (Scacchiera.getCella(i, j).isOccupato()) {
		    return false;
		}
		j++;

	    }
	} else if (end.getX() - start.getX() == end.getY() - start.getY() && end.getX() - start.getX() < 0) { // diminuisce
													      // x e
													      // diminuisce
													      // y
	    j = start.getY() - 1;
	    for (int i = start.getX() - 1; end.getX() < i && end.getY() < j; i--) {
		if (Scacchiera.getCella(i, j).isOccupato()) {
		    return false;
		}
		j--;
	    }
	} else if (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())
		&& end.getX() - start.getX() > 0 && end.getY() - start.getY() < 0) { // aumenta x e diminuisce y
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
     * iniziale e le coordinate della cella finale TODO: Migliorare javadoc
     * 
     * @param mossa
     * @param s
     * @param g
     * @return
     */
    public static String convertiMossa(final String mossa) {
	int startX = -1;
	int startY = -1;
	int endX = -1;
	int endY = -1;
	String mossaConvertita = "a0 a0";
	Colore coloreGiocatoreAttuale = Turno.getGiocatoreInTurno().getColore();
	// mossa semplice
	if (mossa.matches("D[a-h][1-8]")) {
	    final int colonnaDestinazioneAvanzata = 1;
	    final int traversaDestinazioneAvanzata = 2;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneAvanzata));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneAvanzata));
	    if (Scacchiera.getNomePezzo(endX, endY) != "Vuota") {
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
