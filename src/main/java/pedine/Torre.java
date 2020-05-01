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
		String cellaDestinazione = destX + "" + destY;
		Cella cellaCorrente;
		Pezzo pezzoCorrente;
		char ambiguita = mossa.charAt(1);


		// Controlla eventuale Cattura

		if (mossa.matches(regex)) {
			isMossaCattura = (mossa.charAt(mossa.length() - 3) == 'x' || mossa.charAt(mossa.length() - 3) == ':');

			// Controllo Ambiguita

			if (mossa.length() > 3 && ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {
				if (Character.isDigit(ambiguita)) {
					cellaCorrente = Scacchiera.getCella(Cella.coordXinInt(destX), Cella.coordYinInt(ambiguita));
					pezzoCorrente = cellaCorrente.getPezzoCorrente();
					if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == g.getColore())
						return destX + "" + ambiguita + " " + cellaDestinazione;

				} else {

					cellaCorrente = Scacchiera.getCella(Cella.coordXinInt(ambiguita), Cella.coordYinInt(destY));
					pezzoCorrente = cellaCorrente.getPezzoCorrente();
					if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == g.getColore())
						return ambiguita + "" + destY + " " + cellaDestinazione;

				}
			} else {

				boolean isColonnaValida = false;
				boolean isRigaValida = false;
				String posRiga = "";
				String posColonna = "";
				ArrayList<String> possibiliPosizioniColonna = checkPosTorreColonna(cellaDestinazione, g);
				ArrayList<String> possibiliPosizioniRiga = checkPosTorreRiga(cellaDestinazione, g);
				if (!possibiliPosizioniColonna.isEmpty() && !possibiliPosizioniRiga.isEmpty()) {
					int i = 0;

					while (i < possibiliPosizioniColonna.size()) {
						posColonna = possibiliPosizioniColonna.get(i);
						isColonnaValida = isMossaValida(Cella.coordXinInt(posColonna.charAt(0)),
								Cella.coordYinInt(posColonna.charAt(1)), Cella.coordXinInt(destX),
								Cella.coordYinInt(destY), g.getColore());
						if (isColonnaValida) {
							break;
						}
						i++;
					}
					i = 0;
					while (i < possibiliPosizioniRiga.size()) {
						posRiga = possibiliPosizioniRiga.get(i);
						isRigaValida = isMossaValida(Cella.coordXinInt(posRiga.charAt(0)),
								Cella.coordYinInt(posRiga.charAt(1)), Cella.coordXinInt(destX),
								Cella.coordYinInt(destY), g.getColore());
						if (isRigaValida) {
							break;
						}
						i++;
					}
					if (isColonnaValida && isRigaValida) {
						return mossaNonValida;
					} else if (isRigaValida) {
						return posRiga + " " + cellaDestinazione;
					} else
						return posColonna + " " + cellaDestinazione;
				} else if (!possibiliPosizioniColonna.isEmpty()) {
					int count = 0;
					int i = 0;
					while (i < possibiliPosizioniColonna.size()) {
						posColonna = possibiliPosizioniColonna.get(i);
						isColonnaValida = isMossaValida(Cella.coordXinInt(posColonna.charAt(0)),
								Cella.coordYinInt(posColonna.charAt(1)), Cella.coordXinInt(destX),
								Cella.coordYinInt(destY), g.getColore());
						if (isColonnaValida) {
							count++;
						}
						i++;
					}
					if (count == 1)
						return posColonna + " " + cellaDestinazione;

				} else if (!possibiliPosizioniRiga.isEmpty()) {
					int count = 0;
					int i = 0;
					while (i < possibiliPosizioniRiga.size()) {
						posRiga = possibiliPosizioniRiga.get(i);
						isRigaValida = isMossaValida(Cella.coordXinInt(posRiga.charAt(0)),
								Cella.coordYinInt(posRiga.charAt(1)), Cella.coordXinInt(destX),
								Cella.coordYinInt(destY), g.getColore());
						if (isRigaValida) {
							count++;
						}
						i++;
					}
					if (count == 1)
						return posRiga + " " + cellaDestinazione;
				}

			}
		}

		return mossaNonValida;

	}

	private static ArrayList<String> checkPosTorreRiga(String cellafinale, Giocatore g) {

		int y = Cella.coordYinInt(cellafinale.charAt(1));
		ArrayList<String> possibiliPosizioni = new ArrayList<String>();
		int numTorre = 0;

		for (int x = 0; x < Scacchiera.getNumeroRighe(); x++) {
			Cella cellaCorrente = Scacchiera.getCella(x, y);
			Pezzo pezzoCorrente = cellaCorrente.getPezzoCorrente();
			if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == g.getColore()
					&& pezzoCorrente.getNome().equals("Torre")) {
				possibiliPosizioni.add(numTorre, (char) (x + 97) + "" + cellafinale.charAt(1));
				numTorre++;
			}
		}

		return possibiliPosizioni;
	}

	private static ArrayList<String> checkPosTorreColonna(String cellafinale, Giocatore g) {

		ArrayList<String> possibiliPosizioni = new ArrayList<String>();
		int numTorre = 0;

		int x = Cella.coordXinInt(cellafinale.charAt(0));

		for (int y = 0; y < Scacchiera.getNumeroColonne(); y++) {

			Cella cellaCorrente = Scacchiera.getCella(x, y);
			Pezzo pezzoCorrente = cellaCorrente.getPezzoCorrente();
			if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == g.getColore()
					&& pezzoCorrente.getNome().equals("Torre")) {
				possibiliPosizioni.add(numTorre,
						cellafinale.charAt(0) + "" + Math.abs(y - Scacchiera.getNumeroColonne()));
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
