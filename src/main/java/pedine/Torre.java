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
		Cella cellaCorrente;

		Pezzo pezzoCorrente;
		char ambiguita = mossa.charAt(1);

		if (mossa.matches(regex)) {

			// Controlla eventuale Cattura
			isMossaCattura = (mossa.charAt(mossa.length() - 3) == 'x' || mossa.charAt(mossa.length() - 3) == ':');

			// Controllo Ambiguita
			if (mossa.length() > 3 && ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {

				if (Character.isDigit(ambiguita)) {
					cellaCorrente = Scacchiera.getCella(eX, Cella.coordYinInt(ambiguita));
					pezzoCorrente = cellaCorrente.getPezzoCorrente();
					if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == colorepedineGiocatoreCorrente)
						return destX + "" + ambiguita + " " + destX + "" + destY;
				} else {

					cellaCorrente = Scacchiera.getCella(Cella.coordXinInt(ambiguita), eY);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();
					if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == colorepedineGiocatoreCorrente)
						return ambiguita + "" + destY + " " + destX + "" + destY;
				}
			} else {
				possibiliPosizioniColonna = checkPosTorreColonna(eX, colorepedineGiocatoreCorrente);
				possibiliPosizioniRiga = checkPosTorreRiga(eY, colorepedineGiocatoreCorrente);

				posRiga = posizioneValidaRiga(possibiliPosizioniRiga, eX, eY, colorepedineGiocatoreCorrente);
				posColonna = posizioneValidaColonna(possibiliPosizioniColonna, eX, eY, colorepedineGiocatoreCorrente);
				if (!posColonna.equals(mossaNonValida) && !posRiga.equals(mossaNonValida)) {
					return mossaNonValida;
				} else if (!posColonna.equals(mossaNonValida))
					return posColonna;
				else if (!posRiga.equals(mossaNonValida))
					return posRiga;
			}
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
		// presente due possibili posizioni
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

		// Ritorna mossa non valida se nel vettore possibiliPosizioniRiga sono presente
		// due possibili posizioni
		// di partenza della torre valide pertanto siamo nel caso di ambiguita
		return mossaNonValida;
	}

	/**
	 * Metodo che cerca le possibili posizioni della torre nella scacchiera
	 * 
	 * conoscendo la cella di destinazione data dall'utente. restituisce un vettore
	 * di stringhe contenente tutte le possibili posizioni
	 * 
	 * 
	 * @param cellafinale
	 * @param g
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
		return isMossaValida(start.getX(), start.getY(), end.getX(), end.getY(), getColore());
	}

	private static boolean isMossaValida(int sX, int sY, int eX, int eY, Colore colorePezzoCorrente) {
		Cella cellaCorrente;
		Pezzo pezzoCorrente;

		if (sX != eX || sY != eY) {
			if (sY > eY) {
				// Movimento verso l'alto

				for (int i = sY - 1; i >= eY; i--) {

					cellaCorrente = Scacchiera.getCella(sX, i);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();
					if (cellaCorrente.isOccupato()) {
						if (i == eY && pezzoCorrente.getColore() != colorePezzoCorrente && isMossaCattura)
							return true;
						else
							return false;
					}
				}
			} else {
				// Movimento verso il basso
				for (int i = sY + 1; i <= eY; i++) {

					cellaCorrente = Scacchiera.getCella(sX, i);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();

					if (cellaCorrente.isOccupato()) {
						if (i == eY && pezzoCorrente.getColore() != colorePezzoCorrente && isMossaCattura)
							return true;
						else
							return false;
					}
				}
			}

			if (sX > eX) {
				// Movimento verso sx
				for (int i = sX - 1; i >= eX; i--) {

					cellaCorrente = Scacchiera.getCella(i, sY);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();

					if (cellaCorrente.isOccupato()) {
						if (i == eX && pezzoCorrente.getColore() != colorePezzoCorrente && isMossaCattura)
							return true;
						else
							return false;
					}
				}
			} else {
				// Movimento verso dx
				for (int i = sX + 1; i <= eX; i++) {

					cellaCorrente = Scacchiera.getCella(i, sY);
					pezzoCorrente = cellaCorrente.getPezzoCorrente();

					if (cellaCorrente.isOccupato()) {
						if (i == eX && pezzoCorrente.getColore() != colorePezzoCorrente && isMossaCattura)
							return true;
						else
							return false;
					}
				}
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean isMossaSpecialeValida(Cella start, Cella end, ArrayList<String> mosse) {
		return false;
	}
}
