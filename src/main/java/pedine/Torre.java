package pedine;

import java.util.ArrayList;

import gioco.Giocatore;
import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Realizzazione della classe Torre, estensione della classe Pezzo
 */
public final class Torre extends Pezzo {
	static boolean isMossaCattura;
	private boolean primaMossaEffettuata = false;
	static final String mossaNonValida = "a0 a0";

	/** Costruttore */
	public Torre(final Colore colore, final Cella posizioneCorrente) {
		super("Torre", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265c';
		} else {
			simbolo = '\u2656';
		}
	}

	/**
	 * Metodo che,data una stringa: mossa in notazione algebrica ridotta, la
	 * converte in notazione estesa.
	 *
	 * @param mossa
	 * @param g
	 * @return String
	 */

	public static String convertiMossa(String mossa, Giocatore g) {

		String regex = "T([a-h]|[1-8])?([x|:])?([a-h][1-8])";
		char destX = mossa.charAt(mossa.length() - 2);
		char destY = mossa.charAt(mossa.length() - 1);
		int eX = Cella.coordXinInt(destX);
		int eY = Cella.coordYinInt(destY);
		Colore colorepedineGiocatoreCorrente = g.getColore();
		ArrayList<String> possibiliPosizioniColonna = new ArrayList<String>();
		ArrayList<String> possibiliPosizioniRiga = new ArrayList<String>();
		String posRiga = "";
		String posColonna = "";
		char ambiguita = mossa.charAt(1);

		if (mossa.matches(regex)) {

			// Controlla eventuale Cattura
			isMossaCattura = (mossa.charAt(mossa.length() - 3) == 'x' || mossa.charAt(mossa.length() - 3) == ':');

			possibiliPosizioniColonna = checkPosTorreColonna(eX, colorepedineGiocatoreCorrente);
			possibiliPosizioniRiga = checkPosTorreRiga(eY, colorepedineGiocatoreCorrente);

			posRiga = posizioneValidaRiga(possibiliPosizioniRiga, eX, eY, colorepedineGiocatoreCorrente);
			posColonna = posizioneValidaColonna(possibiliPosizioniColonna, eX, eY, colorepedineGiocatoreCorrente);

			if (!posColonna.equals(mossaNonValida) && !posRiga.equals(mossaNonValida)) {
				if (mossa.length() > 3 && ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {
					if (Character.isDigit(ambiguita)) {
						if (posRiga.charAt(1) == ambiguita)
							return posRiga;
						else if (posColonna.charAt(1) == ambiguita)
							return posColonna;
					} else if (posRiga.charAt(0) == ambiguita)
						return posRiga;
					else if (posColonna.charAt(0) == ambiguita)
						return posColonna;
				}
			} else if (posColonna.equals(mossaNonValida) && posRiga.equals(mossaNonValida)) {
				if (mossa.length() > 3 && ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {
					if (Character.isDigit(ambiguita)) {
						if (isMossaValida(eX, Cella.coordYinInt(ambiguita), eX, eY, colorepedineGiocatoreCorrente))
							return destX + "" + ambiguita + " " + destX + "" + destY;

					} else if (isMossaValida(Cella.coordXinInt(ambiguita), eY, eX, eY, colorepedineGiocatoreCorrente))
						return ambiguita + "" + destY + " " + destX + "" + destY;

				}
			} else if (!posColonna.equals(mossaNonValida))
				return posColonna;
			else if (!posRiga.equals(mossaNonValida))
				return posRiga;
		}
		return mossaNonValida;
	}




	private static String posizioneValidaColonna(ArrayList<String> possibiliPosizioniColonna, int eX, int eY,
			Colore colorepedineGiocatoreCorrente) {
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
		if (count == 1)
			return posColonna;

		// Ritorna mossa non valida se nel vettore possibiliPosizioniColonna sono
		// presenti due possibili posizioni
		// di partenza della torre valide pertanto siamo nel caso di ambiguita
		return mossaNonValida;

	}

	private static String posizioneValidaRiga(ArrayList<String> possibiliPosizioniRiga, int eX, int eY,
			Colore colorepedineGiocatoreCorrente) {

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
		if (count == 1)
			return posRiga;

		// Ritorna mossa non valida se nel vettore possibiliPosizioniRiga sono presenti
		// due possibili posizioni
		// di partenza della torre valide pertanto siamo nel caso di ambiguita
		return mossaNonValida;
	}

	/**
	 * Metodo che cerca le possibili posizioni della torre di colore di valore
	 * colorepedineGiocatoreCorrente nella scacchiera in colonna esima y e riga x.
	 * Restituisce un vettore di stringhe contenente tutte le possibili posizioni
	 * occupate dalla torre nella scacchiera.
	 *
	 *
	 *
	 * @param y
	 * @param colorepedineGiocatoreCorrente
	 * @return ArrayList<String>
	 */
	private static ArrayList<String> checkPosTorreRiga(int y, Colore colorepedineGiocatoreCorrente) {

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

	/**
	 * Metodo che cerca le possibili posizioni della torre di colore di valore
	 * colorepedineGiocatoreCorrente nella scacchiera in traversa esima x e riga y.
	 * Restituisce un vettore di stringhe contenente tutte le possibili posizioni
	 * occupate dalla torre nella scacchiera.
	 *
	 *
	 *
	 * @param x
	 * @param colorepedineGiocatoreCorrente
	 * @return ArrayList<String>
	 */
	private static ArrayList<String> checkPosTorreColonna(int x, Colore colorepedineGiocatoreCorrente) {

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
	public boolean isMossaValida(Cella start, Cella end) {

		boolean isMossaValida = isMossaValida(start.getX(), start.getY(), end.getX(), end.getY(), getColore());
		if(!isPrimaMossaEffettuata() && isMossaValida)
			setPrimaMossaEffettuata(true);

		return isMossaValida;
	}

	/**
	 * Metodo che verifica il seguente scenario: date le coordinate sX e
	 * sY,indicanti la traversa e la colonna di origine del pezzo da muovere, e le
	 * coordinate eX e eY,indicanti la traversa e la colonna di arrivo del pezzo da
	 * muovere, viene effettuato un controllo sul movimento del pezzo dalle
	 * coordinate di partenza a quelle di arrivo.
	 *
	 * @param sX
	 * @param sY
	 * @param eX
	 * @param eY
	 * @param colorePezzoCorrente
	 * @return boolean
	 */
	private static boolean isMossaValida(int sX, int sY, int eX, int eY, Colore colorePezzoGiocatoreCorrente) {
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
							&& pezzoCorrente.getColore() != colorePezzoGiocatoreCorrente && isMossaCattura)
						return true;
					else if (!cellaCorrente.isOccupato() && i == eY && isMossaCattura || cellaCorrente.isOccupato())
						return false;

				}
			} else {
				// Movimento verso il basso
				for (int i = sY + 1; i <= eY; i++) {

					cellaCorrente = Scacchiera.getCella(sX, i);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();

					if (cellaCorrente.isOccupato() && i == eY
							&& pezzoCorrente.getColore() != colorePezzoGiocatoreCorrente && isMossaCattura)
						return true;
					else if (!cellaCorrente.isOccupato() && i == eY && isMossaCattura || cellaCorrente.isOccupato())
						return false;

				}
			}

			if (sX > eX) {
				// Movimento verso sx
				for (int i = sX - 1; i >= eX; i--) {

					cellaCorrente = Scacchiera.getCella(i, sY);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();

					if (cellaCorrente.isOccupato() && i == eX
							&& pezzoCorrente.getColore() != colorePezzoGiocatoreCorrente && isMossaCattura)
						return true;
					else if (!cellaCorrente.isOccupato() && i == eX && isMossaCattura || cellaCorrente.isOccupato())
						return false;

				}
			} else {
				// Movimento verso dx
				for (int i = sX + 1; i <= eX; i++) {

					cellaCorrente = Scacchiera.getCella(i, sY);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();

					if (cellaCorrente.isOccupato() && i == eX
							&& pezzoCorrente.getColore() != colorePezzoGiocatoreCorrente && isMossaCattura)
						return true;
					else if (!cellaCorrente.isOccupato() && i == eX && isMossaCattura || cellaCorrente.isOccupato())
						return false;
				}
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean isMossaSpecialeValida(Cella start, Cella end, ArrayList<String> mosse) {

		int sX = start.getX();
		int sY = start.getY();
		int eX = end.getX();
		Cella cellaCorrente = null;
		Pezzo pezzoCorrente = null;

		if (!isPrimaMossaEffettuata()) {
			if (tipoArrocco(start, end) == ARROCCO_CORTO) {

				// Arrocco corto
				for (int i = sX - 1; i >= eX; i--) {

					cellaCorrente = Scacchiera.getCella(i, sY);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();

					if (cellaCorrente.isOccupato() && !pezzoCorrente.getNome().equals("Re")
							&& pezzoCorrente.getColore() == getColore())
						return false;
				}
				return true;

			} else {
				// Arrocco lungo
				return false;
			}
		} else
			return false;
	}

	public static String convertiMossaSpeciale(int tipoArrocco, Giocatore g) {

		if (tipoArrocco == ARROCCO_CORTO)
			return (g.getColore() == Colore.bianco) ? "h1 f1" : "h8 f8";
		else {
			// arrocco lungo
			return "";
		}

	}

	private int tipoArrocco(Cella start, Cella end) {
		return start.getX() > end.getX() ? ARROCCO_CORTO : ARROCCO_LUNGO;
	}

	public boolean isPrimaMossaEffettuata() {
		return primaMossaEffettuata;
	}

	public void setPrimaMossaEffettuata(boolean primaMossaEffettuata) {
		this.primaMossaEffettuata = primaMossaEffettuata;
	}
}
