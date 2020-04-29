package pedine;

import java.util.ArrayList;

import giocatore.Giocatore;
import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Realizzazione della classe Torre, estensione della classe Pezzo
 */
public final class Torre extends Pezzo {
	static boolean cattura;
	static final String mossaNonValida = "a0 a0";

	/** Costruttore */
	public Torre(final Colore colore, final Cella posizioneCorrente) {
		super("Torre", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265c';
		} else {
			simbolo = '\u2656';
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String ConvertiMossa(String mossa, Scacchiera s, Giocatore g) {
		String regex = "T([a-h]|[1-8])?([x|:])?([a-h][1-8])";
		char x = mossa.charAt(mossa.length() - 2);
		char y = mossa.charAt(mossa.length() - 1);
		String cellaDestinazione = x + "" + y;

		// Controlla eventuale Cattura

		if (mossa.matches(regex)) {
			if (mossa.charAt(mossa.length() - 3) == 'x' || mossa.charAt(mossa.length() - 3) == ':')
				cattura = true;
			else
				cattura = false;

			// Controllo Ambiguita

			char ambiguita = mossa.charAt(1);

			if (mossa.length() > 3 && ((ambiguita >= 'a' && ambiguita <= 'h') || Character.isDigit(ambiguita))) {
				if (Character.isDigit(ambiguita)) {
					return x + "" + ambiguita + " " + cellaDestinazione;
				} else {
					return ambiguita + "" + y + " " + cellaDestinazione;
				}
			} else {

				String posColonna = checkPosTorreColonna(cellaDestinazione, s, g);
				String posRiga = checkPosTorreRiga(cellaDestinazione, s, g);

				if (posColonna != mossaNonValida && posRiga != mossaNonValida) {
					if (posRiga.charAt(posRiga.length() - 1) == '0'
							&& posColonna.charAt(posColonna.length() - 1) == '0')
						return mossaNonValida;
					else if (posColonna != mossaNonValida && posColonna.charAt(posColonna.length() - 1) == '0') {
						return posColonna.substring(0, posColonna.length() - 2) + " " + cellaDestinazione;
					} else if (posRiga.charAt(posRiga.length() - 1) == '0')
						return posRiga.substring(0, posRiga.length() - 2) + " " + cellaDestinazione;

				} else if (posColonna != mossaNonValida) {
					return posColonna.substring(0, posColonna.length() - 2) + " " + cellaDestinazione;
				} else
					return posRiga.substring(0, posRiga.length() - 2) + " " + cellaDestinazione;

			}
		}

		return mossaNonValida;

	}

	private static String checkPosTorreRiga(String cellafinale, Scacchiera s, Giocatore g) {

		int y = Cella.coordYinInt(cellafinale.charAt(1));
		int celleOccupate = 0;

		String cellaIniziale = "";
		int numTorre = 0;

		for (int x = 0; x < s.getNumeroRighe(); x++) {
			Cella cellaCorrente = s.getCella(x, y);
			Pezzo pezzoCorrente = cellaCorrente.getPezzoCorrente();
			if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == g.getColore()
					&& pezzoCorrente.getNome().equals("Torre")) {
				numTorre++;
				cellaIniziale = (char) (x + 97) + "" + cellafinale.charAt(1);
			} else if (cellaCorrente.isOccupato())
				celleOccupate++;
		}

		if (numTorre == 1) {
			return cellaIniziale + " " + celleOccupate;
		}

		return mossaNonValida;
	}

	private static String checkPosTorreColonna(String cellafinale, Scacchiera s, Giocatore g) {
		String cellaIniziale = "";
		int numTorre = 0;
		int x = Cella.coordXinInt(cellafinale.charAt(0));
		int celleOccupate = 0;
		for (int y = 0; y < s.getNumeroColonne(); y++) {

			Cella cellaCorrente = s.getCella(x, y);
			Pezzo pezzoCorrente = cellaCorrente.getPezzoCorrente();

			if (cellaCorrente.isOccupato() && pezzoCorrente.getColore() == g.getColore()
					&& pezzoCorrente.getNome().equals("Torre")) {
				numTorre++;

				cellaIniziale = cellafinale.charAt(0) + "" + Math.abs(y - s.getNumeroColonne());
			} else if (cellaCorrente.isOccupato())
				celleOccupate++;
		}

		if (numTorre == 1) {
			return cellaIniziale + " " + celleOccupate;
		}

		return mossaNonValida;

	}

	@Override
	public boolean isMossaValida(Cella start, Cella end, Scacchiera s) {
		// TODO Auto-generated method stub
		return false;
	}
}