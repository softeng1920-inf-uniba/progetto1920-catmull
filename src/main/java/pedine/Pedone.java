package pedine;

import java.util.ArrayList;

import giocatore.Giocatore;
import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe per rappresentere il sottotipo di pezzo chiamato Pedone
 */
public final class Pedone extends Pezzo {

	/**
	 * Costruttore
	 */
	public Pedone(final Colore colore, final Cella posizioneCorrente) {
		super("Pedone", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265f';
		} else {
			simbolo = '\u2659';
		}
	}

	/**
	 * Restituisce vero se e' la prima mossa, falso altrimenti
	 */
	@Override
	public boolean isMossaValida(Cella start, Cella end, Scacchiera s) {

		if (this.colore == Colore.bianco) {
			// movimento semplice
			if (start.getX() == end.getX() && !end.isOccupato()) {
				if (((start.getY() - 1) == end.getY())) // avanti di una cella
					return true;
				else if (((start.getY() - 2) == end.getY()) && start.getY() == 6) // 6 indica la riga di partenza del
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
			else if (((start.getY() + 2) == end.getY()) && start.getY() == 1) // avanti di due
				return true;

		} // mossa obliqua NERO
		else if (start.getY() + 1 == end.getY() && (Math.abs(start.getX() - end.getX()) == 1) && end.isOccupato()
				&& end.getPezzoCorrente().getColore() != this.colore)
			return true;

		return false;
	}

	private boolean isPedone(Cella c) {

		return c.isOccupato() && c.getPezzoCorrente().getNome().equals(this.nome);
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		Cella pedone;
		char x = (char) (end.getX() + 97);
		char y1 = (char) (Math.abs(end.getY() - 9) + 48);
		char y2 = (char) (Math.abs(end.getY() - 7) + 48);
		String pedoneAvversarioBianco = x + "" + y2 + " " + x + "" + y1;
		String pedoneAvversarioNero = x + "" + y1 + " " + x + "" + y2;

		pedone = s.getCella(end.getX(), start.getY());
		if (isPedone(pedone) && pedone.getPezzoCorrente().getColore() != this.colore) {
			if (this.colore == Colore.bianco) {

				if ((start.getY() - 1 == end.getY()) && (Math.abs(start.getX() - end.getX()) == 1) && !end.isOccupato()
						&& mosse.get(mosse.size() - 1).equals(pedoneAvversarioNero))
					return true;
			} else if ((start.getY() + 1 == end.getY()) && (Math.abs(start.getX() - end.getX()) == 1)
					&& !end.isOccupato() && mosse.get(mosse.size() - 1).equals(pedoneAvversarioBianco))
				return true;

		}

		return false;
	}

	// metodo di classe che converte il comando in input in una stringa con
	// coordinate cella partenza, spazio e coordinate cella di arrivo
	public static String ConvertiMossa(String mossa, Scacchiera s, Giocatore g) {
		int variazione = 0;
		String mossaConvertita = "";
		// Il formato della mossa sarà del tipo [a-h](x|:)([a-h][1-8])
		String regex = "[a-h](x|:)([a-h][1-8])( e.p.)?";

		if (mossa.matches(regex)) { // mossa in diagonale di cattura
			if (g.getColore() == Colore.bianco) {
				variazione = -49;
			} else
				variazione = -47;

			mossaConvertita = String.valueOf(mossa.charAt(0)) + String.valueOf(mossa.charAt(3) + variazione) + ' '
					+ String.valueOf(mossa.charAt(2)) + String.valueOf(mossa.charAt(3));
		} else { // mosse semplici
			if (g.getColore() == Colore.bianco) {
				// contolla se è una possibile avanti di due o di uno
				if (mossa.charAt(1) == '4' && (!s.getCella(Cella.coordXinInt(mossa.charAt(0)), 5).isOccupato() || !s
						.getCella(Cella.coordXinInt(mossa.charAt(0)), 5).getPezzoCorrente().getNome().equals("Pedone"))) // traversa
					variazione = -50;
				else
					variazione = -49;

			} else { // giocatore pedine nere
				// contolla se è una possibile avanti di due o di uno
				if (mossa.charAt(1) == '5' && (!s.getCella(Cella.coordXinInt(mossa.charAt(0)), 2).isOccupato() || !s
						.getCella(Cella.coordXinInt(mossa.charAt(0)), 2).getPezzoCorrente().getNome().equals("Pedone")))
					variazione = -46;
				else
					variazione = -47;
			}
			// mossa finale pedone semplice
			mossaConvertita = String.valueOf(mossa.charAt(0)) + // 1° traversa
					String.valueOf(mossa.charAt(1) + variazione) + // 1° colonna
					' ' + String.valueOf(mossa.charAt(0)) + // 2° traversa
					String.valueOf(mossa.charAt(1)); // 2° colonna
		}
		return mossaConvertita;
	}
}