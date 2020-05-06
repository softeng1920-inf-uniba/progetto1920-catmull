package pedine;

import java.util.ArrayList;
import gioco.Colore;
import gioco.Comando;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi ,definisce se
 * il movimento del Re è valido.
 * La classe Re e' di tipo noECB
 */
public final class Re extends Pezzo {

    /** Costruttore */
    public Re(final Colore colore, final Cella posizioneCorrente) {
	super("Re", colore, posizioneCorrente);
	if (colore == Colore.nero) {
	    simbolo = '\u265a';
	} else {
	    simbolo = '\u2654';
	}
    }

    @Override
    public boolean isMossaValida(Cella start, Cella end) {

	// controllo se può mangiare pezzo
	if (end.isOccupato() == true && end.getPezzoCorrente().getColore() == this.colore) {
	    return false;
	}
	if (!isReSottoScacco(end)) {
	    // MOVIMENTI LINEARI
	    // sulla stessa colonna
	    if (start.getX() == end.getX()) {
		if ((start.getY() == end.getY() + 1) || (start.getY() == end.getY() - 1))
		    return true;
	    } else if (start.getY() == end.getY()) { // sulla stessa riga: + 1 a sinistra, -1 a destra
		if ((start.getX() == end.getX() + 1) || (start.getX() == end.getX() - 1))
		    return true;
	    }
	    // MOVIMENTI DIAGONALI
	    if (start.getX() == end.getX() + 1 && start.getY() == end.getY() + 1)
		return true;
	    if (start.getX() == end.getX() - 1 && start.getY() == end.getY() - 1)
		return true;
	    if (start.getX() == end.getX() + 1 && start.getY() == end.getY() - 1)
		return true;
	    if (start.getX() == end.getX() - 1 && start.getY() == end.getY() + 1)
		return true;
	}
	return false;

    }

    public boolean isArroccoValido(Cella startRe, Cella endRe, Cella startTorre, Cella endTorre,
	    ArrayList<String> storicoMosse, int tipoArrocco) {
	int sX = startRe.getX();
	int sY = startRe.getY();
	int eX = endRe.getX();
	if (!isPrimaMossaEffettuata(storicoMosse) && !isReSottoScacco(startRe)) {

	    // Itera dalla cella corrente :start fino alla cella di destinazione:end e
	    // controlla che non ci siano pezzi intermedi e che nello spostamento del re non
	    // è sotto scacco

	    if (tipoArrocco == Comando.ARROCCO_CORTO) {
		// Arrocco Corto
		for (int i = sX + 1; i < eX + 1; i++) {
		    if (isReSottoScacco(Scacchiera.getCella(i, sY)) || Scacchiera.getNomePezzo(i, sY) != "Vuota")
			return false;
		}
		return true;
	    } else {
		// Arrocco Lungo
		for (int i = sX - 1; i > eX - 1; i--) {
		    if (isReSottoScacco(Scacchiera.getCella(i, sY)) || Scacchiera.getNomePezzo(i, sY) != "Vuota")
			return false;
		}
		return true;
	    }
	} else
	    return false;

    }

    public static String getCoordinateArrocco(int tipoArrocco, Colore c) {
	if (tipoArrocco == Comando.ARROCCO_CORTO)
	    return (c == Colore.bianco) ? "e1 g1" : "e8 g8";
	else {
	    return (c == Colore.bianco) ? "e1 c1" : "e8 c8";

	}

    }

    public static String convertiMossa(String mossa) {
	int startX = -1;
	int startY = -1;
	int endX = -1;
	int endY = -1;
	String mossaConvertita = "a0 a0";
	// mossa semplice
	if (mossa.matches("R[a-h][1-8]")) {
	    endX = Cella.coordXinInt(mossa.charAt(1));
	    endY = Cella.coordYinInt(mossa.charAt(2));
	    if (Scacchiera.getNomePezzo(endX, endY) != "Vuota")
		return mossaConvertita;
	}
	// mossa di cattura
	if (mossa.matches("R(x|:)[a-h][1-8]")) {
	    endX = Cella.coordXinInt(mossa.charAt(2));
	    endY = Cella.coordYinInt(mossa.charAt(3));
	    if (Scacchiera.getNomePezzo(endX, endY) == "Vuota")
		return mossaConvertita;
	}
	// ricerca il re del giocatore in turno
	for (int i = 0; i < Scacchiera.getNumeroColonne(); i++) {
	    for (int j = 0; j < Scacchiera.getNumeroRighe(); j++) {
		if (Scacchiera.getNomePezzo(i, j) == "Re" && Scacchiera.getCella(i, j).getPezzoCorrente()
			.getColore() == Turno.getGiocatoreInTurno().getColore()) {
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
	    mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " "
		    + Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
	}
	return mossaConvertita;
    }

    /**
     *
     *
     * @param ReCella cella di destinazione del re
     * @return boolean se il re è sotto scacco o meno
     */
    public boolean isReSottoScacco(Cella ReCella) {
	Colore c = getColore();
	Re reTemp = new Re(c, ReCella);
	Cella temp = new Cella(ReCella.getX(), ReCella.getY(), reTemp);
	temp.setOccupato(true);
	for (int i = 0; i < Scacchiera.getNumeroRighe(); i++) {
	    for (int j = 0; j < Scacchiera.getNumeroColonne(); j++) {
		if (Scacchiera.getNomePezzo(i, j) != "Vuota"
			&& Scacchiera.getCella(i, j).getPezzoCorrente().getColore() != c
			&& Scacchiera.getNomePezzo(i, j) != "Re"
			&& Scacchiera.getCella(i, j).getPezzoCorrente().isMossaValida(Scacchiera.getCella(i, j), temp))
		    return true;
	    }
	}
	return false;
    }

    /**
     * Controlla se il re o la torre si sono mai spostati
     *
     * @param storicoMosse
     * @return
     */
    public static boolean isPrimaMossaEffettuata(ArrayList<String> storicoMosse) {

	for (int i = 0; i < storicoMosse.size(); i++) {
	    if (storicoMosse.get(i).matches("(h|e|a)(1|8) [a-h][1-8]"))
		return true;
	}
	return false;
    }

	/**
	 * Controlla se il re o la torre si sono mai spostati
	 *
	 * @param storicoMosse
	 * @return
	 */
	public static boolean isPrimaMossaEffettuata(ArrayList<String> storicoMosse) {

		for (int i = 0; i < storicoMosse.size(); i++) {
			if (storicoMosse.get(i).matches("(h|e|a)(1|8) [a-h][1-8]"))
				return true;
		}
		return false;
	}


}
