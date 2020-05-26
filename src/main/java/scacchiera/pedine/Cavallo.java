package scacchiera.pedine;

import gioco.Colore;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi, definisce se il
 * movimento del Cavallo e' valido.
 * <p>
 * La classe Cavallo e' di tipo &lt;&lt;noECB&gt;&gt;.
 */
public final class Cavallo extends Pezzo {
	  /** Invoca il metodo della superclasse
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
    public Cavallo(final Colore colore) {
	super("Cavallo", colore);
	if (colore == Colore.nero) {
	    setSimbolo('\u265e');
	} else {
	    setSimbolo('\u2658');
	}
    }

    @Override
    public boolean isMossaValida(final Cella start, final Cella end) {
	if (end.isOccupato() && getColore() == end.getPezzoCorrente().getColore()) {
	    return false;
	}
	if (start.getX() == end.getX() + 2 && (start.getY() == end.getY() - 1 || start.getY() == end.getY() + 1)) {
	    return true;
	} else if (start.getX() == end.getX() - 2
		&& (start.getY() == end.getY() - 1 || start.getY() == end.getY() + 1)) {
	    return true;
	} else if (start.getX() == end.getX() + 1
		&& (start.getY() == end.getY() - 2 || start.getY() == end.getY() + 2)) {
	    return true;
	} else if (start.getX() == end.getX() - 1
		&& (start.getY() == end.getY() - 2 || start.getY() == end.getY() + 2)) {
	    return true;
	}
	return false;
    }

    /**
 	  * Converte la stringa in input in stringa leggibile dalla funzione
 	  * applicaMossa. Ad esempio: Cde4 =&gt; d2 e4
 	  *
 	  * @param  mossa Stringa rappresentante la mossa, scritta in notazione ridotta
 	  *
 	  * @return  Mossa in notazione estesa
 	  *
 	  */

    public static String convertiMossa(final String mossa) {
	int startX = -1;
	int startY = -1;
	int endX = -1;
	int endY = -1;
	int ambiguita = 0;
	// stringa standard da restituire in caso di mossa non valida per i controlli
	// nella classe controller
	String mossaConvertita = "a0 a0";
	Colore coloreGiocatoreAttivo = Turno.getInstance().getGiocatoreInTurno().getColore();
	// esempio: Cdxe4 - cattura con ambiguita' su colonna
	if (mossa.matches("C[a-h][x|:]([a-h][1-8])")) {
	    final int colonnaPartenzaCattura = 1;
	    final int colonnaDestinazioneCattura = 3;
	    final int traversaDestinazioneCattura = 4;
	    startX = Cella.coordXinInt(mossa.charAt(colonnaPartenzaCattura));
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneCattura));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneCattura));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) == "Vuota") {
		return mossaConvertita;
	    }
	}
	// esempio: Cxe4 - cattura cavallo normale
	if (mossa.matches("C[x|:]([a-h][1-8])")) {
	    final int colonnaDestinazioneCattura = 2;
	    final int traversaDestinazioneCattura = 3;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneCattura));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneCattura));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) == "Vuota") {
		return mossaConvertita;
	    }
	}
	// esempio: C6xe4 - cattura con ambiguita' su traversa
	if (mossa.matches("C[1-8][x|:]([a-h][1-8])")) {
	    final int traversaPartenzaCattura = 1;
	    final int colonnaDestinazioneCattura = 3;
	    final int traversaDestinazioneCattura = 4;
	    startY = Cella.coordYinInt(mossa.charAt(traversaPartenzaCattura));
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneCattura));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneCattura));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) == "Vuota") {
		return mossaConvertita;
	    }
	}
	// esempio: Cde4 - Avanzata cavallo con ambiguit� su colonna
	if (mossa.matches("C[a-h]([a-h][1-8])")) {
	    final int colonnaPartenzaAvanzata = 1;
	    final int colonnaDestinazioneAvanzata = 2;
	    final int traversaDestinazioneAvanzata = 3;
	    startX = Cella.coordXinInt(mossa.charAt(colonnaPartenzaAvanzata));
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneAvanzata));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneAvanzata));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) != "Vuota") {
		return mossaConvertita;
	    }
	}
	// esempio: C3e4 - Avanzata cavallo con ambiguit� su traversa
	if (mossa.matches("C[1-8]([a-h][1-8])")) {
	    final int traversaPartenzaAvanzata = 1;
	    final int colonnaDestinazioneAvanzata = 2;
	    final int traversaDestinazioneAvanzata = 3;
	    startY = Cella.coordYinInt(mossa.charAt(traversaPartenzaAvanzata));
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneAvanzata));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneAvanzata));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) != "Vuota") {
		return mossaConvertita;
	    }
	}
	// esempio: Ce4 - Avanzata cavallo normale
	if (mossa.matches("C([a-h][1-8])")) {
	    final int colonnaDestinazioneAvanzata = 1;
	    final int traversaDestinazioneAvanzata = 2;
	    endX = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneAvanzata));
	    endY = Cella.coordYinInt(mossa.charAt(traversaDestinazioneAvanzata));
	    if (Scacchiera.getInstance().getNomePezzo(endX, endY) != "Vuota") {
		return mossaConvertita;
	    }
	}
	// ricaviamo X o Y a seconda di quella diversa da -1
	if (startX != -1) { // startX data in input
	    if (Math.abs(startX - endX) == 2) {
		if (Scacchiera.getInstance().getNomePezzo(startX, endY - 1) == "Cavallo"
			&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(startX, endY - 1)
				.getPezzoCorrente().getColore()) {
		    startY = endY - 1;
		    ambiguita++;
		}
		if (Scacchiera.getInstance().getNomePezzo(startX, endY + 1) == "Cavallo"
			&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(startX, endY + 1)
				.getPezzoCorrente().getColore()) {
		    startY = endY + 1;
		    ambiguita++;
		}
	    }
	    if (Math.abs(startX - endX) == 1) {
		if (Scacchiera.getInstance().getNomePezzo(startX, endY - 2) == "Cavallo"
			&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(startX, endY - 2)
				.getPezzoCorrente().getColore()) {
		    startY = endY - 2;
		    ambiguita++;

		}
		if (Scacchiera.getInstance().getNomePezzo(startX, endY + 2) == "Cavallo"
			&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(startX, endY + 2)
				.getPezzoCorrente().getColore()) {
		    startY = endY + 2;
		    ambiguita++;
		}
	    }
	} else if (startY != -1) { // startY data in input
	    if (Math.abs(startY - endY) == 2) {
		if (Scacchiera.getInstance().getNomePezzo(endX - 1, startY) == "Cavallo"
			&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX - 1, startY)
				.getPezzoCorrente().getColore()) {
		    startX = endX - 1;
		    ambiguita++;

		}
		if (Scacchiera.getInstance().getNomePezzo(endX + 1, startY) == "Cavallo"
			&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX + 1, startY)
				.getPezzoCorrente().getColore()) {
		    startX = endX + 1;
		    ambiguita++;
		}
	    }
	    if (Math.abs(startY - endY) == 1) {
		if (Scacchiera.getInstance().getNomePezzo(endX - 2, startY) == "Cavallo"
			&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX - 2, startY)
				.getPezzoCorrente().getColore()) {
		    startX = endX - 2;
		    ambiguita++;
		}
		if (Scacchiera.getInstance().getNomePezzo(endX + 2, startY) == "Cavallo"
			&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX + 2, startY)
				.getPezzoCorrente().getColore()) {
		    startX = endX + 2;
		    ambiguita++;
		}
	    }
	} else { // startX e startY uguali entrambe a -1, quindi entrambe non date in input
	    mossaConvertita = convertiMossaNonAmbigua(endX, endY, mossa);
	}
	if (Scacchiera.getInstance().isRangeValido(startX, startY)
		&& Scacchiera.getInstance().getCella(startX, startY).isOccupato() && ambiguita <= 1) {
	    mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " "
		    + Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
	}
	return mossaConvertita;
    }

    /**
 	  * Converte la mossa in caso di espressione regolare senza ambiguita'
 	  *
 	  * @param  endX intero indicante la coordinata delle colonne(compreso tra 0 e 7)
 	  * @param  endY intero indicante la coordinata delle righe(compreso tra 0 e 7)
 	  * @param  mossa Stringa indicante la mossa da convertire
 	  * @return mossa convertita
 	  */
    private static String convertiMossaNonAmbigua(final int endX, final int endY, final String mossa) {

	String mossaConvertita = "a0 a0";
	Colore coloreGiocatoreAttivo = Turno.getInstance().getGiocatoreInTurno().getColore();
	int startX = -1;
	int startY = -1;
	int ambiguita = 0;
	if (Scacchiera.getInstance().getNomePezzo(endX - 2, endY - 1) == "Cavallo"
		&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX - 2, endY - 1).getPezzoCorrente()
			.getColore()) {
	    startX = endX - 2;
	    startY = endY - 1;
	    ambiguita++;
	}

	if (Scacchiera.getInstance().getNomePezzo(endX - 2, endY + 1) == "Cavallo"
		&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX - 2, endY + 1).getPezzoCorrente()
			.getColore()) {
	    startX = endX - 2;
	    startY = endY + 1;
	    ambiguita++;
	}

	if (Scacchiera.getInstance().getNomePezzo(endX + 2, endY + 1) == "Cavallo"
		&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX + 2, endY + 1).getPezzoCorrente()
			.getColore()) {
	    startX = endX + 2;
	    startY = endY + 1;
	    ambiguita++;
	}

	if (Scacchiera.getInstance().getNomePezzo(endX + 2, endY - 1) == "Cavallo"
		&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX + 2, endY - 1).getPezzoCorrente()
			.getColore()) {
	    startX = endX + 2;
	    startY = endY - 1;
	    ambiguita++;
	}

	if (Scacchiera.getInstance().getNomePezzo(endX + 1, endY - 2) == "Cavallo"
		&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX + 1, endY - 2).getPezzoCorrente()
			.getColore()) {
	    startX = endX + 1;
	    startY = endY - 2;
	    ambiguita++;
	}

	if (Scacchiera.getInstance().getNomePezzo(endX + 1, endY + 2) == "Cavallo"
		&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX + 1, endY + 2).getPezzoCorrente()
			.getColore()) {
	    startX = endX + 1;
	    startY = endY + 2;
	    ambiguita++;
	}

	if (Scacchiera.getInstance().getNomePezzo(endX - 1, endY + 2) == "Cavallo"
		&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX - 1, endY + 2).getPezzoCorrente()
			.getColore()) {
	    startX = endX - 1;
	    startY = endY + 2;
	    ambiguita++;
	}

	if (Scacchiera.getInstance().getNomePezzo(endX - 1, endY - 2) == "Cavallo"
		&& coloreGiocatoreAttivo == Scacchiera.getInstance().getCella(endX - 1, endY - 2).getPezzoCorrente()
			.getColore()) {
	    startX = endX - 1;
	    startY = endY - 2;
	    ambiguita++;
	}

	if (Scacchiera.getInstance().isRangeValido(startX, startY)
		&& Scacchiera.getInstance().getCella(startX, startY).isOccupato() && ambiguita <= 1) {
	    mossaConvertita = Cella.coordXinChar(startX) + "" + Cella.coordYinChar(startY) + " "
		    + Cella.coordXinChar(endX) + "" + Cella.coordYinChar(endY);
	}
	return mossaConvertita;
    }
}
