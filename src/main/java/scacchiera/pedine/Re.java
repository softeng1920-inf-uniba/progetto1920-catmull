package scacchiera.pedine;

import java.util.ArrayList;

import gioco.Colore;
import gioco.Menu;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi, definisce se il
 * movimento del Re e' valido.
 * <br>
 * La classe Re e' di tipo &lt;&lt;noECB&gt;&gt;
 *
 */
public final class Re extends Pezzo {

	   /**
		  * Invoca il metodo della superclasse
		  * <p>
		  * Vengono settati i valori dei vari parametri della classe, di cui:
		  * <ul>
		  *   <li>nome</li>
		  *   <li>colore</li>
		  *   <li>posizioneCorrente</li>
		  *   <li>simbolo</li>
		  * </ul>
		  *
		  * @param  colore Colore indicante l'aspetto visivo: bianco o nero
		  */
    public Re(final Colore colore) {
	super("Re", colore);
	if (colore == Colore.nero) {
	    setSimbolo('\u265a');
	} else {
	    setSimbolo('\u2654');
	}
    }

    @Override
    public boolean isMossaValida(final Cella start, final Cella end) {

	// controllo se puo' mangiare pezzo
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

    /**Identifica se le coordinate passate in input di Re e torre costituiscono un tipo di arrocco applicabile
     * <p>
     * L'arrocco e' valido se sono verificate tutte le seguenti condizioni:
     * <ul>
	   *        <li>il giocatore non ha ancora mosso ne' il Re ne' la torre coinvolta nell'arrocco</li>
	   *        <li>non ci devono essere pezzi (amici o avversari) fra il Re e la torre usata</li>
	   *        <li>ne' la casa di partenza del Re, ne' la casa che esso deve
	   *           attraversare, ne' quella di arrivo devono essere attaccabili da un
	   *           pezzo avversario</li>
	   * </ul>
     *
     * @param  startRe Cella indicante la posizione iniziale del Re sulla scacchiera
     * @param  endRe Cella indicante la posizione finale del Re sulla scacchiera
     * @param  startTorre Cella indicante la posizione iniziale della torre sulla scacchiera
     * @param  endTorre Cella indicante la posizione finale della torre sulla scacchiera
     * @param  storicoMosse Lista di stringhe che rappresenta l'insieme di tutte le mosse
     * @param  tipoArrocco Intero indicante la tipologia di arrocco
     *
     * @return  true se tutte le condizioni sono verificate, false altrimenti
     */
    public boolean isArroccoValido(final Cella startRe, final Cella endRe, final Cella startTorre, final Cella endTorre,
	    final ArrayList<String> storicoMosse, final int tipoArrocco) {
	int sX = startRe.getX();
	int sY = startRe.getY();
	int eX = endRe.getX();
	if (!isPrimaMossaEffettuata(storicoMosse, tipoArrocco) && !isReSottoScacco(startRe)) {

	    // Itera dalla cella corrente :start fino alla cella di destinazione:end e
	    // controlla che non ci siano pezzi intermedi e che nello spostamento del re non
	    // Ã¨ sotto scacco

	    if (tipoArrocco == Menu.ARROCCO_CORTO) {
		// Arrocco Corto
		for (int i = sX + 1; i < eX + 1; i++) {
		    if (isReSottoScacco(Scacchiera.getInstance().getCella(i, sY))
			    || Scacchiera.getInstance().getNomePezzo(i, sY) != "Vuota") {
			return false;
		    }
		}
		return true;
	    } else {
		// Arrocco Lungo
		for (int i = sX - 1; i > eX - 1; i--) {
		    if (isReSottoScacco(Scacchiera.getInstance().getCella(i, sY))
			    || Scacchiera.getInstance().getNomePezzo(i, sY) != "Vuota") {
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
 	  * @param  tipoArrocco Intero indicante la tipologia di arrocco
 	  * @param  c Colore del giocatore in turno
 	  *
 	  * @return  Mossa da effettuare in notazione estesa
 	  */
    public static String getCoordinateArrocco(final int tipoArrocco, final Colore c) {

	String comando;
	if (tipoArrocco == Menu.ARROCCO_CORTO) {
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
 	  * applicaMossa. Ad esempio: Re4 =&gt; e3 e4
 	  *
 	  * @param  mossa Stringa indicante la mossa in notazione algebrica
 	  * @return  mossa in notazione estesa
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
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) != "Vuota") {
		return mossaConvertita;
	    }
	}
	// mossa di cattura
	if (mossa.matches("R(x|:)[a-h][1-8]")) {
	    final int colonnaDestinazioneCattura = 2;
	    final int traversaDestinazioneCattura = 3;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneCattura));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneCattura));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) == "Vuota") {
		return mossaConvertita;
	    }
	}
	// ricerca il re del giocatore in turno
	for (int i = 0; i < Scacchiera.getInstance().getNumeroColonne(); i++) {
	    for (int j = 0; j < Scacchiera.getInstance().getNumeroRighe(); j++) {
		if (Scacchiera.getInstance().getNomePezzo(i, j) == "Re" && Scacchiera.getInstance().getCella(i, j)
			.getPezzoCorrente().getColore() == Turno.getInstance().getGiocatoreInTurno().getColore()) {
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
	if ((startX != -1 && startY != -1)) {
	    mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " "
		    + Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
	}
	return mossaConvertita;
    }

    /**
	 * Controlla se il re e' sotto scacco
	 *
	 * @param  reCella Cella di destinazione del Re
	 * @return  true se il re e' sotto scacco, false altrimenti
	 */
    public boolean isReSottoScacco(final Cella reCella) {
	Colore c = getColore();
	Re reTemp = new Re(c);
	Cella temp = new Cella(reCella.getX(), reCella.getY(), reTemp);
	temp.setOccupato(true);
	for (int i = 0; i < Scacchiera.getInstance().getNumeroRighe(); i++) {
	    for (int j = 0; j < Scacchiera.getInstance().getNumeroColonne(); j++) {
		if (Scacchiera.getInstance().getNomePezzo(i, j) != "Vuota"
			&& Scacchiera.getInstance().getCella(i, j).getPezzoCorrente().getColore() != c
			&& Scacchiera.getInstance().getNomePezzo(i, j) != "Re"
			&& Scacchiera.getInstance().getCella(i, j).getPezzoCorrente()
				.isMossaValida(Scacchiera.getInstance().getCella(i, j), temp)) {
		    return true;
		}
	    }
	}
	return false;
    }

    /**
 	  * Controlla se il re o la torre si sono mai spostati
 	  *
 	  * @param  storicoMosse Lista di Stringhe indicanti gli spostamenti dei pezzi
 	  * @param  tipoArrocco Intero indicante la tipologia di arrocco (corto o lungo)
 	  *
 	  * @return  true se la prima mossa del re o della torre e' stata effettuata, false altrimenti
 	  */
    public static boolean isPrimaMossaEffettuata(final ArrayList<String> storicoMosse, final int tipoArrocco) {
	final short numGiocatori = 2;
	for (int i = 0; i < storicoMosse.size(); i++) {
	    if ((tipoArrocco == Menu.ARROCCO_CORTO && storicoMosse.get(i).matches("(h|e)(1|8) [a-h][1-8]"))
		    || (tipoArrocco == Menu.ARROCCO_LUNGO && storicoMosse.get(i).matches("(e|a)(1|8) [a-h][1-8]"))) {
		if (((i % numGiocatori) == 0 && Turno.getInstance().getGiocatoreInTurno().getColore() == Colore.bianco)
			|| ((i % numGiocatori) != 0
				&& Turno.getInstance().getGiocatoreInTurno().getColore() == Colore.nero)) {
		    return true;
		}
	    }
	}
	return false;
    }

    /**
 	  * Cerca il Re dello stesso colore del giocatore in turno nella scacchiera
 	  *
 	  * @return Cella che contiene il Re
 	  */
    public static Cella findRe() {
	int startX = -1;
	int startY = -1;
	// ricerca il re del giocatore in turno
	for (int i = 0; i < Scacchiera.getInstance().getNumeroColonne(); i++) {
	    for (int j = 0; j < Scacchiera.getInstance().getNumeroRighe(); j++) {
		if (Scacchiera.getInstance().getNomePezzo(i, j) == "Re" && Scacchiera.getInstance().getCella(i, j)
			.getPezzoCorrente().getColore() == Turno.getInstance().getGiocatoreInTurno().getColore()) {
		    startX = i;
		    startY = j;
		    break;
		}
	    }
	    if (startX != -1) {
		break;
	    }
	}
	return Scacchiera.getInstance().getCella(startX, startY);
    }

}
