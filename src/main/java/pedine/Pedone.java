package pedine;

import java.util.ArrayList;
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
	public boolean isMossaValida(Cella start, Cella end) {

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

	public boolean isPedone(Cella c) {

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

}