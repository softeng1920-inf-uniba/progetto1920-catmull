package pedine;

import java.util.ArrayList;

import gioco.Giocatore;
import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/** Classe per rappresentere il sottotipo di pezzo chiamato Re */
public final class Re extends Pezzo {

	boolean primaMossaEffettuata = false;

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

		boolean isMossaValida = false;
		// controllo se può mangiare pezzo
		if (end.isOccupato() == true && end.getPezzoCorrente().getColore() == this.colore) {
			isMossaValida = false;
		}
		if (!isReSottoScacco(end)) {
			// MOVIMENTI LINEARI
			// sulla stessa colonna
			if (start.getX() == end.getX()) {
				if (start.getY() == end.getY() + 1)
					isMossaValida = true;
				if (start.getY() == end.getY() - 1)
					isMossaValida = true;			}
			// sulla stessa riga
			if (start.getY() == end.getY()) {
				if (start.getX() == end.getX() + 1)
					isMossaValida = true;
				if (start.getX() == end.getX() - 1)
					isMossaValida = true;
				}
			// MOVIMENTI DIAGONALI
			if (start.getX() == end.getX() + 1 && start.getX() == end.getX() + 1)
				isMossaValida = true;
			if (start.getX() == end.getX() - 1 && start.getX() == end.getX() - 1)
				isMossaValida = true;
			if (start.getX() == end.getX() + 1 && start.getX() == end.getX() - 1)
				isMossaValida = true;
			if (start.getX() == end.getX() - 1 && start.getX() == end.getX() + 1)
				isMossaValida = true;
			}
		
		if(isMossaValida && ! isPrimaMossaEffettuata())
			setPrimaMossaEffettuata(true);
		
		return isMossaValida;
	}

	@Override
	public boolean isMossaSpecialeValida(Cella start, Cella end, ArrayList<String> mosse) {

		int sX = start.getX();
		int sY = start.getY();
		int eX = end.getX();
		Cella cellaCorrente = null;
		Pezzo pezzoCorrente = null;

		if (!isPrimaMossaEffettuata() && !isReSottoScacco(start)) {

			if (tipoArrocco(start, end) == ARROCCO_CORTO) {

				// Itera dalla cella corrente :start fino alla cella di destinazione:end e
				// controlla che non ci siano pezzi intermedi e che nello spostamento del re non
				// è sotto scacco

				// Arrocco Corto
				for (int i = sX + 1; i < eX; i++) {
					cellaCorrente = Scacchiera.getCella(i, sY);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();
					if (cellaCorrente.isOccupato() && !pezzoCorrente.getNome().equals("Torre")
							&& pezzoCorrente.getColore() == getColore() && !isReSottoScacco(cellaCorrente))
						return false;
				}

				return true;

			} else {
				// Arrocco Lungo
			}
		} else
			return false;

		return false;
	}

	public static String convertiMossa(String mossa, Giocatore g) {
		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		String mossaConvertita = "a0 a0";
		// mossa semplice
		if (mossa.matches("(R)[a-h][1-8]")) {
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
			if (Scacchiera.getNomePezzo(endX, endY) != "Vuota")
				return mossaConvertita;
		}
		// mossa di cattura
		if (mossa.matches("(R)(x|:)[a-h][1-8]")) {
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (Scacchiera.getNomePezzo(endX, endY) == "Vuota")
				return mossaConvertita;
		}
		// ricerca il re del giocatore in turno
		for (int i = 0; i < Scacchiera.getNumeroColonne(); i++) {
			for (int j = 0; j < Scacchiera.getNumeroRighe(); j++) {
				if (Scacchiera.getNomePezzo(i, j) == "Re"
						&& Scacchiera.getCella(i, j).getPezzoCorrente().getColore() == g.getColore()) {
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

	public static String convertiMossaSpeciale(int tipoArrocco, Giocatore giocatoreInTurno) {

		if (tipoArrocco == ARROCCO_CORTO)
			return (giocatoreInTurno.getColore() == Colore.bianco) ? "e1 g1" : "e8 g8";
		else {
			// arrocco lungo
			return "";
		}

	}

	private int tipoArrocco(Cella start, Cella end) {
		return start.getX() < end.getX() ? ARROCCO_CORTO : ARROCCO_LUNGO;
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

	public boolean isPrimaMossaEffettuata() {
		return primaMossaEffettuata;
	}

	public void setPrimaMossaEffettuata(boolean primaMossaEffettuata) {
		this.primaMossaEffettuata = primaMossaEffettuata;
	}

}
