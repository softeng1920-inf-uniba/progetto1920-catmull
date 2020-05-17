package scacchiera.pedine;

import java.util.ArrayList;

import gioco.Colore;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi ,definisce se il
 * movimento del Pedone � valido. La classe Pedone e' di tipo noECB
 */
public final class Pedone extends Pezzo {
	

    /**
     * Costruttore
     */
    public Pedone(final Colore colore) {
	super("Pedone", colore);
	if (colore == Colore.nero) {
	    setSimbolo('\u265f');
	} else {
	    setSimbolo('\u2659');
	}
    }

    @Override
    public boolean isMossaValida(final Cella start, final Cella end) {

	if (getColore() == Colore.bianco) {
	    // movimento semplice
	    if (start.getX() == end.getX() && !end.isOccupato()) {
		if (((start.getY() - 1) == end.getY())) { // avanti di una cella
		    return true;
		} else if (((start.getY() - 2) == end.getY()) && start.getY() == Scacchiera.SETTIMA_TRAVERSA // Se il
		// pedone
		// parte
		// dalla
		// settima
		// traversa
			&& !(Scacchiera.getInstance().getCella(end.getX(), (start.getY() - 1)).isOccupato())) {
		    return true;
		}
	}

	/**
	 * Restituisce vero se e' la prima mossa, falso altrimenti
	 */
	@Override
	public boolean isMossaValida(Cella start, Cella end) {

		if (this.colore == Colore.bianco) {
			// movimento semplice
			if (start.getX() == end.getX() && !end.isOccupato()) {
				if (((start.getY() - 1) == end.getY())) // avanti di una cella
					return true;
				else if (((start.getY() - 2) == end.getY()) && start.getY() == 6
						&& !(Scacchiera.getCella(end.getX(), (start.getY() - 1)).isOccupato())) // 6 indica la riga di
					// partenza del
					// pedone
					return true;
				// movimento obliquo
			} else if ((start.getY() - 1 == end.getY()) && (Math.abs(start.getX() - end.getX()) == 1)
					&& end.isOccupato() && end.getPezzoCorrente().getColore() != this.colore)
				return true;

		} // mosse NERO semplice
		else if (start.getX() == end.getX() && !end.isOccupato()) {
			if (((start.getY() + 1) == end.getY()) && !end.isOccupato()) // avanti di una cella
				return true;
			else if (((start.getY() + 2) == end.getY()) && start.getY() == 1
					&& !(Scacchiera.getCella(end.getX(), (start.getY() + 1)).isOccupato())) // avanti di due
				return true;

		// movimento obliquo
	    } else if ((start.getY() - 1 == end.getY()) && (Math.abs(start.getX() - end.getX()) == 1)
		    && end.isOccupato() && end.getPezzoCorrente().getColore() != getColore()) {
		return true;
	    }

	} else if (start.getX() == end.getX() && !end.isOccupato()) { // mosse NERO semplice
	    if (((start.getY() + 1) == end.getY()) && !end.isOccupato()) { // avanti di una cella
		return true;
	    } else if (((start.getY() + 2) == end.getY()) && start.getY() == 1
		    && !(Scacchiera.getInstance().getCella(end.getX(), (start.getY() + 1)).isOccupato())) { // avanti di
													    // due
		return true;
	    }
	} else if (start.getY() + 1 == end.getY() && (Math.abs(start.getX() - end.getX()) == 1) && end.isOccupato()
		&& end.getPezzoCorrente().getColore() != getColore()) { // mossa obliqua NERO
	    return true;
	}

	boolean isPedone(final Cella c) {
		return c.isOccupato() && c.getPezzoCorrente().getNome().equals(getNome());
	}

    /**
     * Determina se la cattura en passant e' effettuabile o meno
     *
     * @param start Cella di partenza della mossa del pedone
     * @param end   Cella di destinazione della mossa del pedone
     * @param mosse Array di tutte le mosse effettuate, necessario per controllare
     *              l'ultima mossa effettuata e' un avanzata di due case
     * @return true se la cattura e' effettuabile in en passant, false altrimenti
     */
    public boolean isEnPassantValido(final Cella start, final Cella end, final ArrayList<String> mosse) {
	Cella cellaPedone;
	final int aMinuscolaAscii = 97;
	final int carattere0Ascii = 48;
	final int offset1Ascii = 7;
	final int offset2Ascii = 9;
	char x = (char) (end.getX() + aMinuscolaAscii);
	char y1 = (char) (Math.abs(end.getY() - offset2Ascii) + carattere0Ascii);
	char y2 = (char) (Math.abs(end.getY() - offset1Ascii) + carattere0Ascii);
	String pedoneAvversarioBianco = x + "" + y2 + " " + x + "" + y1;
	// Prendo la colonna della destinazione, e la traversa di partenza
	cellaPedone = Scacchiera.getInstance().getCella(end.getX(), start.getY());
	// se la cella e' occupata da un pedone di un colore opposto
	if (isPedone(cellaPedone) && cellaPedone.getPezzoCorrente().getColore() != getColore()) {
	    if (getColore() == Colore.bianco) {
		String pedoneAvversarioNero = x + "" + y1 + " " + x + "" + y2; // calcolo ultima mossa del pedone da
		// catturare E.P.
		// Se la traversa di partenza + 1 e' uguale a quella di
		// destinazione, e mi sto spostando in obliquo
		// E la cella di dest. non e' occupata
		// E l'ultima mossa e' quella prevista per l'e.p.
		if (start.getY() - 1 == end.getY() && Math.abs(start.getX() - end.getX()) == 1 && !end.isOccupato()
			&& mosse.get(mosse.size() - 1).equals(pedoneAvversarioNero)) {
		    return true;
		}
	    } else { // Pedone di colore nero
		if (start.getY() + 1 == end.getY() && Math.abs(start.getX() - end.getX()) == 1 && !end.isOccupato()
			&& mosse.get(mosse.size() - 1).equals(pedoneAvversarioBianco)) {
		    return true;
		}

		return false;
	}
	return false;
    }

    /**
     * metodo di classe che converte il comando in input in una stringa nel formato
     * a2 a4
     *
     * @param mossa puo' essere un'avanzata (es. a2) oppure una cattura (es. axb3
     * @return mossa nel formato esteso
     */
    public static String convertiMossa(final String mossa) {
	int variazione = 0;
	final int offsetMenoDueAscii = 46;
	final int offsetMenoUnoAscii = 47;
	final int offsetUnoAscii = 49;
	final int offsetDueAscii = 50;
	String mossaConvertita = "a0 a0";
	// Il formato della mossa sara' del tipo [a-h](x|:)([a-h][1-8])
	String regexCattura = "[a-h](x|:)([a-h][1-8])( e.p.)?";
	Colore coloreGiocatoreAttuale = Turno.getInstance().getGiocatoreInTurno().getColore();
	if (mossa.matches(regexCattura)) { // Mossa di cattura in diagonale
	    final int colonnaPartenzaCattura = 0;
	    final int colonnaDestinazioneCattura = 2;
	    final int traversaDestinazioneCattura = 3;
	    variazione = -offsetMenoUnoAscii;
	    if (coloreGiocatoreAttuale == Colore.bianco) {
		variazione = -offsetUnoAscii;
	    }
	    if (Math.abs(mossa.charAt(colonnaPartenzaCattura) - mossa.charAt(colonnaDestinazioneCattura)) == 1) {
		mossaConvertita = String.valueOf(mossa.charAt(colonnaPartenzaCattura))
			+ String.valueOf(mossa.charAt(traversaDestinazioneCattura) + variazione) + ' '
			+ String.valueOf(mossa.charAt(colonnaDestinazioneCattura))
			+ String.valueOf(mossa.charAt(traversaDestinazioneCattura));
	    }
	} else { // avanzata
	    final int colonnaDestinazioneAvanzata = 0;
	    final int traversaDestinazioneAvanzata = 1;
	    // controlla se e' possibile avanti di due o di uno
	    if (coloreGiocatoreAttuale == Colore.bianco) {
		int colonnaDestinazione = Cella.coordXinInt(mossa.charAt(colonnaDestinazioneAvanzata));
		int terzaTraversa = Cella.coordYinInt('3');
		Cella c = Scacchiera.getInstance().getCella(colonnaDestinazione, terzaTraversa);
		if (mossa.charAt(traversaDestinazioneAvanzata) == '4'
			// Se mi voglio spostare nella 4 traversa, devo determinare se voglio
			// avanzare di 2
			&& (!c.isOccupato() || !c.getPezzoCorrente().getNome().equals("Pedone"))) {
		    variazione = -offsetDueAscii; // Se la terza traversa non e' occupata da un pedone allora vengo
		    // dalla seconda
		    // traversa
		} else {
		    variazione = -offsetUnoAscii;
		}
	    } else { // giocatore pedine nere
		Cella c = Scacchiera.getInstance()
			.getCella(Cella.coordXinInt(mossa.charAt(colonnaDestinazioneAvanzata)), 2);
		variazione = -offsetMenoUnoAscii;
		if (mossa.charAt(1) == '5' && (!c.isOccupato() || !c.getPezzoCorrente().getNome().equals("Pedone"))) {
		    variazione = -offsetMenoDueAscii;
		}
	    }
	    // mossa finale pedone semplice
	    mossaConvertita = String.valueOf(mossa.charAt(colonnaDestinazioneAvanzata)) + // prima traversa
		    String.valueOf(mossa.charAt(traversaDestinazioneAvanzata) + variazione) + // prima colonna
		    ' ' + String.valueOf(mossa.charAt(colonnaDestinazioneAvanzata)) + // seconda traversa
		    String.valueOf(mossa.charAt(traversaDestinazioneAvanzata)); // seconda colonna
	}
	// controlla che nella cella di partenza ci sia un pedone
	if (Scacchiera.getInstance().getNomePezzo(Cella.coordXinInt(mossaConvertita.charAt(0)),
		Cella.coordYinInt(mossaConvertita.charAt(1))) != "Pedone") {
	    return "a0 a0";
	}
}
