package pedine;

import java.util.ArrayList;

import gioco.Colore;
import gioco.Comando;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi ,definisce se il
 * movimento del Re è valido. La classe Re e' di tipo noECB
 */
public final class Re extends Pezzo {

    /** Costruttore */
    public Re(final Colore colore, final Cella posizioneCorrente) {
	super("Re", colore, posizioneCorrente);
	if (colore == Colore.nero) {
	    setSimbolo('\u265a');
	} else {
	    setSimbolo('\u2654');
	}
    }

    @Override
    public boolean isMossaValida(final Cella start, final Cella end) {

	// controllo se può mangiare pezzo
	if (end.isOccupato()) {
	    if (end.getPezzoCorrente().getColore() == getColore()) {
		return false;
	    }
	}
	if (!isReSottoScacco(end)) {
	    // MOVIMENTI LINEARI
	    // sulla stessa colonna
	    if (start.getX() == end.getX()) {
		if ((start.getY() == end.getY() + 1) || (start.getY() == end.getY() - 1)) {
		    return true;
		}
	    } else if (start.getY() == end.getY()) { // sulla stessa riga: + 1 a sinistra, -1 a destra
		if ((start.getX() == end.getX() + 1) || (start.getX() == end.getX() - 1)) {
		    return true;
		}
	    }
	    // MOVIMENTI DIAGONALI
	    if (start.getX() == end.getX() + 1 && start.getY() == end.getY() + 1) {
		return true;
	    }
	    if (start.getX() == end.getX() - 1 && start.getY() == end.getY() - 1) {
		return true;
	    }
	    if (start.getX() == end.getX() + 1 && start.getY() == end.getY() - 1) {
		return true;
	    }
	    if (start.getX() == end.getX() - 1 && start.getY() == end.getY() + 1) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Restituisce un valore booleano che indica se è possibile effettuare l'arrocco
     * con i pezzi nelle celle passate in input.
     * 
     * @param startRe      Cella di partenza del re di cui si vuole controllare se è
     *                     possibile effettuare l'arrocco
     * @param endRe        Cella di destinazione del re di cui si vuole controllare
     *                     se è possibile effettuare l'arrocco
     * @param startTorre   Cella di partenza della torre di cui si vuole controllare
     *                     se è possibile effettuare l'arrocco
     * @param endTorre     Cella di destinazione della torre di cui si vuole
     *                     controllare se è possibile effettuare l'arrocco
     * @param storicoMosse Lista di stringhe che rappresenta l'insieme di tutte le
     *                     mosse
     * @param tipoArrocco  0 - corto | 1 - lungo
     * @return true se l'arrocco è effettuabile, false altrimenti.
     */
    public boolean isArroccoValido(final Cella startRe, final Cella endRe, final Cella startTorre, final Cella endTorre,
	    final ArrayList<String> storicoMosse, final int tipoArrocco) {
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
		    if (isReSottoScacco(Scacchiera.getCella(i, sY)) || Scacchiera.getNomePezzo(i, sY) != "Vuota") {
			return false;
		    }
		}
		return true;
	    } else {
		// Arrocco Lungo
		for (int i = sX - 1; i > eX - 1; i--) {
		    if (isReSottoScacco(Scacchiera.getCella(i, sY)) || Scacchiera.getNomePezzo(i, sY) != "Vuota") {
			return false;
		    }
		}
		return true;
	    }
	} else {
	    return false;
	}

    }

    /**
     * Restituisce una stringa nel formato e[1|8] [c|g][1|8], che indica la mossa da
     * effettuare per il re in base al colore e alla tipologia di arrocco.
     * 
     * @param tipoArrocco 0 - corto | 1 - lungo
     * @param c           colore del giocatore in turno
     * @return mossa da effettuare
     */
    public static String getCoordinateArrocco(final int tipoArrocco, final Colore c) {

	String comando;
	if (tipoArrocco == Comando.ARROCCO_CORTO) {
	    if (c == Colore.bianco) {
		comando = "e1 g1";
	    } else {
		comando = "e8 g8";
	    }
	} else {
	    // arrocco lungo
	    if (c == Colore.bianco) {
		comando = "e1 c1";
	    } else {
		comando = "e8 c8";
	    }
	}

	return comando;

    }

    /**
     * Converte la stringa in input in stringa leggibile dalla funzione
     * applicaMossa. Ad esempio: Re4 ==> e3 e4
     * 
     * @param mossa in notazione algebrica
     * @return mossa in output in notazione estesa
     */
    public static String convertiMossa(final String mossa) {
	int startX = -1;
	int startY = -1;
	int endX = -1;
	int endY = -1;
	String mossaConvertita = "a0 a0";
	// mossa semplice
	if (mossa.matches("R[a-h][1-8]")) {
	    final int colonnaDestinazioneAvanzata = 1;
	    final int traversaDestinazioneAvanzata = 2;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneAvanzata));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneAvanzata));
	    if (Scacchiera.getNomePezzo(endX, endY) != "Vuota") {
		return mossaConvertita;
	    }
	}
	// mossa di cattura
	if (mossa.matches("R(x|:)[a-h][1-8]")) {
	    final int colonnaDestinazioneCattura = 2;
	    final int traversaDestinazioneCattura = 3;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneCattura));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneCattura));
	    if (Scacchiera.getNomePezzo(endX, endY) == "Vuota") {
		return mossaConvertita;
	    }
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
	    if (startX != -1) {
		break;
	    }
	}
	// solo se ha trovato il re ha senso convertire la mossa
	if (startX != -1 && startY != -1) {
	    mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " "
		    + Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
	}
	return mossaConvertita;
    }

    /**
     * Controlla se il re è sotto scacco
     * 
     * @param reCella cella di destinazione del re
     * @return boolean true se il re è sotto scacco, false altrimenti
     */
    public boolean isReSottoScacco(final Cella reCella) {
	Colore c = getColore();
	Re reTemp = new Re(c, reCella);
	Cella temp = new Cella(reCella.getX(), reCella.getY(), reTemp);
	temp.setOccupato(true);
	for (int i = 0; i < Scacchiera.getNumeroRighe(); i++) {
	    for (int j = 0; j < Scacchiera.getNumeroColonne(); j++) {
		if (Scacchiera.getNomePezzo(i, j) != "Vuota"
			&& Scacchiera.getCella(i, j).getPezzoCorrente().getColore() != c
			&& Scacchiera.getNomePezzo(i, j) != "Re" && Scacchiera.getCella(i, j).getPezzoCorrente()
				.isMossaValida(Scacchiera.getCella(i, j), temp)) {
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Controlla se il re o la torre si sono mai spostati
     *
     * @param storicoMosse
     * @return true se la prima mossa del re è stata effettuata, false altrimenti.
     */
    public static boolean isPrimaMossaEffettuata(final ArrayList<String> storicoMosse) {

	for (int i = 0; i < storicoMosse.size(); i++) {
	    if (storicoMosse.get(i).matches("(h|e|a)(1|8) [a-h][1-8]")) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Cerca il Re nella scacchiera dello stesso colore del giocatore in turno.
     * 
     * @return Cella che contiene il Re
     */
    public static Cella findRe() {
	int startX = -1;
	int startY = -1;
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
	    if (startX != -1) {
		break;
	    }
	}
	return Scacchiera.getCella(startX, startY);
    }

}
