package pedine;

import java.util.ArrayList;
import gioco.Colore;
import gioco.Comando;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi ,definisce se il
 * movimento della Torre è valido. La classe Torre e' di tipo noECB
 */
public final class Torre extends Pezzo {
    private static boolean isMossaCattura;
    static final String MOSSA_NON_VALIDA = "a0 a0";

    /** Costruttore */
    public Torre(final Colore colore, final Cella posizioneCorrente) {
	super("Torre", colore, posizioneCorrente);
	if (colore == Colore.nero) {
	    setSimbolo('\u265c');
	} else {
	    setSimbolo('\u2656');

	}
    }

    /**
     * Data una stringa: mossa in notazione algebrica ridotta, la converte in
     * notazione estesa. TODO: Migliorare javadoc
     *
     * @param mossa
     * @return String
     */
    public static String convertiMossa(String mossa) {

	String regex = "T([a-h]|[1-8])?([x|:])?([a-h][1-8])";
	char destX = mossa.charAt(mossa.length() - 2);
	char destY = mossa.charAt(mossa.length() - 1);
	int eX = Cella.coordXinInt(destX);
	int eY = Cella.coordYinInt(destY);
	Colore colorepedineGiocatoreCorrente = Turno.getGiocatoreInTurno().getColore();
	ArrayList<String> possibiliPosizioniColonna;
	ArrayList<String> possibiliPosizioniRiga;
	String posRiga = "";
	String posColonna = "";
	char ambiguita = mossa.charAt(1);

	if (mossa.matches(regex)) {
	    final int posizioneCarattereCattura = 3;

	    // Controlla eventuale Cattura
	    isMossaCattura = (mossa.charAt(mossa.length() - posizioneCarattereCattura) == 'x'
		    || mossa.charAt(mossa.length() - posizioneCarattereCattura) == ':');

	    possibiliPosizioniColonna = new ArrayList<String>(checkPosTorreColonna(eX, colorepedineGiocatoreCorrente));
	    possibiliPosizioniRiga = new ArrayList<String>(checkPosTorreRiga(eY, colorepedineGiocatoreCorrente));

	    posRiga = posizioneValidaRiga(possibiliPosizioniRiga, eX, eY, colorepedineGiocatoreCorrente);
	    posColonna = posizioneValidaColonna(possibiliPosizioniColonna, eX, eY, colorepedineGiocatoreCorrente);

	    if (!posColonna.equals(MOSSA_NON_VALIDA) && !posRiga.equals(MOSSA_NON_VALIDA)
		    && mossa.length() > posizioneCarattereCattura
		    && ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {

		if (Character.isDigit(ambiguita)) {
		    if (posRiga.charAt(1) == ambiguita) {
			return posRiga;
		    } else if (posColonna.charAt(1) == ambiguita) {
			return posColonna;
		    }
		} else if (posRiga.charAt(0) == ambiguita) {
		    return posRiga;
		} else if (posColonna.charAt(0) == ambiguita) {
		    return posColonna;
		}
	    } else if (posColonna.equals(MOSSA_NON_VALIDA) && posRiga.equals(MOSSA_NON_VALIDA)
		    && mossa.length() > posizioneCarattereCattura
		    && ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {
		if (Character.isDigit(ambiguita)) {
		    if (isMossaValida(eX, Cella.coordYinInt(ambiguita), eX, eY, colorepedineGiocatoreCorrente)) {
			return destX + "" + ambiguita + " " + destX + "" + destY;
		    }

		} else if (isMossaValida(Cella.coordXinInt(ambiguita), eY, eX, eY, colorepedineGiocatoreCorrente)) {
		    return ambiguita + "" + destY + " " + destX + "" + destY;
		}

	    } else if (!posColonna.equals(MOSSA_NON_VALIDA)) {
		if (mossa.length() > posizioneCarattereCattura
			&& ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {
		    if (posColonna.charAt(1) == ambiguita || posColonna.charAt(0) == ambiguita) {
			return posColonna;
		    }
		} else {
		    return posColonna;
		}
	    } else if (!posRiga.equals(MOSSA_NON_VALIDA)) {
		if (mossa.length() > posizioneCarattereCattura
			&& ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {
		    if (posRiga.charAt(1) == ambiguita || posRiga.charAt(0) == ambiguita) {
			return posRiga;
		    }
		} else {
		    return posRiga;

		}
	    }
	}
	return MOSSA_NON_VALIDA;

    }

    private static String posizioneValidaColonna(final ArrayList<String> possibiliPosizioniColonna, final int eX,
	    final int eY, final Colore colorepedineGiocatoreCorrente) {
	String posColonna = "";
	String temp;
	int sX = 0;
	int sY = 0;

	int count = 0;
	int i = 0;

	while (i < possibiliPosizioniColonna.size()) {
	    temp = possibiliPosizioniColonna.get(i);
	    sX = Cella.coordXinInt(temp.charAt(0));
	    sY = Cella.coordYinInt(temp.charAt(1));
	    if (isMossaValida(sX, sY, eX, eY, colorepedineGiocatoreCorrente)) {
		count++;
		posColonna = temp + " " + Cella.coordXinChar(eX) + "" + Cella.coordYinChar(eY);
	    }
	    i++;
	}
	if (count == 1) {
	    return posColonna;
	}
	// Ritorna mossa non valida se nel vettore possibiliPosizioniColonna sono
	// presenti due possibili posizioni
	// di partenza della torre valide pertanto siamo nel caso di ambiguita
	return MOSSA_NON_VALIDA;

    }

    private static String posizioneValidaRiga(final ArrayList<String> possibiliPosizioniRiga, final int eX,
	    final int eY, final Colore colorepedineGiocatoreCorrente) {

	String posRiga = "";
	String temp;
	int sX = 0;
	int sY = 0;
	int count = 0;
	int i = 0;
	while (i < possibiliPosizioniRiga.size()) {
	    temp = possibiliPosizioniRiga.get(i);
	    sX = Cella.coordXinInt(temp.charAt(0));
	    sY = Cella.coordYinInt(temp.charAt(1));

	    if (isMossaValida(sX, sY, eX, eY, colorepedineGiocatoreCorrente)) {
		count++;
		posRiga = temp + " " + Cella.coordXinChar(eX) + "" + Cella.coordYinChar(eY);
	    }
	    i++;
	}
	if (count == 1) {
	    return posRiga;
	}

	// Ritorna mossa non valida se nel vettore possibiliPosizioniRiga sono presenti
	// due possibili posizioni
	// di partenza della torre valide pertanto siamo nel caso di ambiguita
	return MOSSA_NON_VALIDA;
    }

    private static ArrayList<String> checkPosTorreRiga(final int y, final Colore colorepedineGiocatoreCorrente) {

	// int y = Cella.coordYinInt(destY);
	ArrayList<String> possibiliPosizioni = new ArrayList<String>();
	int numTorre = 0;

	for (int x = 0; x < Scacchiera.getNumeroRighe(); x++) {
	    Cella cellaCorrente = Scacchiera.getCella(x, y);
	    Pezzo pezzoCorrente = cellaCorrente.getPezzoCorrente();
	    if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == colorepedineGiocatoreCorrente
		    && pezzoCorrente.getNome().equals("Torre")) {
		possibiliPosizioni.add(numTorre, Cella.coordXinChar(x) + "" + Cella.coordYinChar(y));
		numTorre++;
	    }
	}

	return possibiliPosizioni;
    }

    private static ArrayList<String> checkPosTorreColonna(final int x, final Colore colorepedineGiocatoreCorrente) {

	ArrayList<String> possibiliPosizioni = new ArrayList<String>();
	int numTorre = 0;

	// int x = Cella.coordXinInt(destX);

	for (int y = 0; y < Scacchiera.getNumeroColonne(); y++) {

	    Cella cellaCorrente = Scacchiera.getCella(x, y);
	    Pezzo pezzoCorrente = cellaCorrente.getPezzoCorrente();
	    if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == colorepedineGiocatoreCorrente
		    && pezzoCorrente.getNome().equals("Torre")) {
		possibiliPosizioni.add(numTorre, Cella.coordXinChar(x) + "" + Cella.coordYinChar(y));
		numTorre++;
	    }
	}

	return possibiliPosizioni;

    }

    @Override
    public boolean isMossaValida(final Cella start, final Cella end) {
	return isMossaValida(start.getX(), start.getY(), end.getX(), end.getY(), getColore());
    }

    private static boolean isMossaValida(final int sX, final int sY, final int eX, final int eY,
	    final Colore colorePezzoGiocatoreCorrente) {
	Cella cellaCorrente = Scacchiera.getCella(sX, sY);
	Pezzo pezzoCorrente = cellaCorrente.getPezzoCorrente();

	if ((sX != eX || sY != eY) && cellaCorrente.isOccupato()
		&& pezzoCorrente.getColore() == colorePezzoGiocatoreCorrente) {
	    if (sY > eY) {
		// Movimento verso l'alto

		for (int i = sY - 1; i >= eY; i--) {

		    cellaCorrente = Scacchiera.getCella(sX, i);
		    pezzoCorrente = cellaCorrente.getPezzoCorrente();

		    if (cellaCorrente.isOccupato() && i == eY
			    && pezzoCorrente.getColore() != colorePezzoGiocatoreCorrente && isMossaCattura) {
			return true;
		    } else if (!cellaCorrente.isOccupato() && i == eY && isMossaCattura || cellaCorrente.isOccupato()) {
			return false;
		    }
		}
	    } else {
		// Movimento verso il basso
		for (int i = sY + 1; i <= eY; i++) {

		    cellaCorrente = Scacchiera.getCella(sX, i);
		    pezzoCorrente = cellaCorrente.getPezzoCorrente();

		    if (cellaCorrente.isOccupato() && i == eY
			    && pezzoCorrente.getColore() != colorePezzoGiocatoreCorrente && isMossaCattura) {
			return true;
		    } else if (!cellaCorrente.isOccupato() && i == eY && isMossaCattura || cellaCorrente.isOccupato()) {
			return false;
		    }
		}
	    }

	    if (sX > eX) {
		// Movimento verso sx
		for (int i = sX - 1; i >= eX; i--) {
		    cellaCorrente = Scacchiera.getCella(i, sY);
		    pezzoCorrente = cellaCorrente.getPezzoCorrente();

		    if (cellaCorrente.isOccupato() && i == eX
			    && pezzoCorrente.getColore() != colorePezzoGiocatoreCorrente && isMossaCattura) {
			return true;
		    } else if (!cellaCorrente.isOccupato() && i == eX && isMossaCattura || cellaCorrente.isOccupato()) {
			return false;
		    }
		}
	    } else {
		// Movimento verso dx
		for (int i = sX + 1; i <= eX; i++) {
		    cellaCorrente = Scacchiera.getCella(i, sY);
		    pezzoCorrente = cellaCorrente.getPezzoCorrente();

		    if (cellaCorrente.isOccupato() && i == eX
			    && pezzoCorrente.getColore() != colorePezzoGiocatoreCorrente && isMossaCattura) {
			return true;
		    } else if (!cellaCorrente.isOccupato() && i == eX && isMossaCattura || cellaCorrente.isOccupato()) {
			return false;
		    }
		}
	    }
	    return true;
	}
	return false;
    }

    /**
     * Restituisce una stringa nel formato [a|h][1|8] [f|d][1|8], che indica la
     * mossa da effettuare per la torre in base al colore e alla tipologia di
     * arrocco.
     * 
     * @param tipoArrocco 0 - corto | 1 - lungo
     * @param c           colore del giocatore in turno
     * @return mossa da effettuare
     */
    public static String getCoordinateArrocco(final int tipoArrocco, final Colore c) {
	String comando;
	if (tipoArrocco == Comando.ARROCCO_CORTO) {
	    if (c == Colore.bianco) {
		comando = "h1 f1";
	    } else {
		comando = "h8 f8";
	    }
	} else {
	    // arrocco lungo
	    if (c == Colore.bianco) {
		comando = "a1 d1";
	    } else {
		comando = "a8 d8";
	    }
	}

	return comando;
    }

}
