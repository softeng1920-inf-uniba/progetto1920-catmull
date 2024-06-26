package scacchiera.pedine;

import gioco.Colore;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi, definisce se il
 * movimento dell'Alfiere e' valido.
 * <br>
 * La classe Alfiere e' di tipo &lt;&lt;noECB&gt;&gt;
 */
public final class Alfiere extends Pezzo {
	/**
	  * Invoca il metodo della superclasse.
	  * <br>
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
    public Alfiere(final Colore colore) {
	super("Alfiere", colore);
	if (colore == Colore.nero) {
	    setSimbolo('\u265d');
	} else {
	    setSimbolo('\u2657');
	}
    }

    @Override
    public boolean isMossaValida(final Cella start, final Cella end) {

	int deltaX = end.getX() - start.getX();
	int deltaY = end.getY() - start.getY();
	// aumenta x e aumenta y = aumenta x diminuisce y
	int j;
	if (deltaX == deltaY && deltaX > 0) {
	    j = start.getY() + 1;
	    for (int i = start.getX() + 1; end.getX() > i && end.getY() > j; i++) {
		if (Scacchiera.getInstance().getCella(i, j).isOccupato()) {
		    return false;
		}
		j++;
	    }
	} else if (Math.abs(deltaX) == Math.abs(deltaY) && deltaX < 0 && end.getY() - start.getY() > 0) { // diminuisce
	    // x e aumenta
	    // y
	    j = start.getY() + 1;
	    for (int i = start.getX() - 1; end.getX() < i && end.getY() > j; i--) {
		if (Scacchiera.getInstance().getCella(i, j).isOccupato()) {
		    return false;
		}
		j++;

	    }
	} else if (deltaX == deltaY && deltaX < 0) { // diminuisce
	    // x e
	    // diminuisce
	    // y
	    j = start.getY() - 1;
	    for (int i = start.getX() - 1; end.getX() < i && end.getY() < j; i--) {
		if (Scacchiera.getInstance().getCella(i, j).isOccupato()) {
		    return false;
		}
		j--;
	    }
	} else if (Math.abs(deltaX) == Math.abs(deltaY) && deltaX > 0 && deltaY < 0) { // aumenta x e diminuisce y
	    j = start.getY() - 1;
	    for (int i = start.getX() + 1; end.getX() > i && end.getY() < j; i++) {
		if (Scacchiera.getInstance().getCella(i, j).isOccupato()) {
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
     * Converte la stringa in input in stringa leggibile dalla funzione
     * applicaMossa. Ad esempio: Ae4 =&gt; d2 e4s
     *
     * @param  mossa Stringa rappresentante la mossa in notazione algebrica
	   * @return  mossa in notazione estesa
     */
    public static String convertiMossa(final String mossa) {
	// "[A](x|:)?[a-h][1-8]"

	int startX = -1;
	int startY = -1;
	int endX = -1;
	int endY = -1;
	String mossaConvertita = "a0 a0";
	if (mossa.matches("A[a-h][1-8]")) { // si muove senza ambiguita'
	    final int colonnaDestinazione = 1;
	    final int traversaDestinazione = 2;

	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazione));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazione));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) != "Vuota") {
		return mossaConvertita;
	    }
	}

	if (mossa.matches("A[x|:][a-h][1-8]")) { // mangia senza ambiguita'
	    final int colonnaDestinazioneConCattura = 2;
	    final int traversaDestinazioneConCattura = 3;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneConCattura));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneConCattura));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) == "Vuota") {
		return mossaConvertita;
	    }
	}
	Colore coloreGiocatoreAttuale = Turno.getInstance().getGiocatoreInTurno().getColore();
	// aumenta x e aumenta y
	for (int i = 1; Scacchiera.getInstance().isRangeValido(endX + i, endY + i); i++) {
	    if (Scacchiera.getInstance().getNomePezzo(endX + i, endY + i) == "Alfiere" && Scacchiera.getInstance()
		    .getCella(endX + i, endY + i).getPezzoCorrente().getColore() == coloreGiocatoreAttuale) {
		startX = endX + i;
		startY = endY + i;

		break;
	    }
	}
	// aumenta x e diminuisce y
	for (int i = 1; Scacchiera.getInstance().isRangeValido(endX + i, endY - i); i++) {
	    if (Scacchiera.getInstance().getNomePezzo(endX + i, endY - i) == "Alfiere" && Scacchiera.getInstance()
		    .getCella(endX + i, endY - i).getPezzoCorrente().getColore() == coloreGiocatoreAttuale) {
		startX = endX + i;
		startY = endY - i;

		break;
	    }
	}
	// diminuisce x e aumenta y
	for (int i = 1; Scacchiera.getInstance().isRangeValido(endX - i, endY + i); i++) {
	    if (Scacchiera.getInstance().getNomePezzo(endX - i, endY + i) == "Alfiere" && Scacchiera.getInstance()
		    .getCella(endX - i, endY + i).getPezzoCorrente().getColore() == coloreGiocatoreAttuale) {
		startX = endX - i;
		startY = endY + i;

		break;
	    }
	}
	// diminuisce x e diminuisce y
	for (int i = 1; Scacchiera.getInstance().isRangeValido(endX - i, endY - i); i++) {
	    if (Scacchiera.getInstance().getNomePezzo(endX - i, endY - i) == "Alfiere"
		    && Scacchiera.getInstance().getCella(endX - i, endY - i).getPezzoCorrente().getColore() == Turno
			    .getInstance().getGiocatoreInTurno().getColore()) {
		startX = endX - i;
		startY = endY - i;

		break;
	    }
	}

	if (Scacchiera.getInstance().isRangeValido(startX, startY)
		&& Scacchiera.getInstance().getCella(startX, startY).isOccupato()) {

			mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " "
					+ Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
		}

		return mossaConvertita;

	}

}
