package scacchiera.pedine;

import java.util.ArrayList;

import gioco.Colore;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe che rappresenta una pedina del gioco degli scacchi ,definisce se il
 * movimento del Pedone e' valido. La classe Pedone e' di tipo noECB
 */
public final class Pedone extends Pezzo {

	/**
	 * Costruttore
	 */
	public Pedone(final Colore colore) {
		super("Pedone", colore);
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

		} // mossa obliqua NERO
		else if (start.getY() + 1 == end.getY() && (Math.abs(start.getX() - end.getX()) == 1) && end.isOccupato()
				&& end.getPezzoCorrente().getColore() != this.colore)
			return true;

		return false;
	}

	boolean isPedone(final Cella c) {
		return c.isOccupato() && c.getPezzoCorrente().getNome().equals(getNome());
	}

	public boolean isEnPassantValido(Cella start, Cella end, ArrayList<String> mosse) {

		Cella cellaPedone;
		char x = (char) (end.getX() + 97);
		char y1 = (char) (Math.abs(end.getY() - 9) + 48);
		char y2 = (char) (Math.abs(end.getY() - 7) + 48);
		String pedoneAvversarioBianco = x + "" + y2 + " " + x + "" + y1;

		cellaPedone = Scacchiera.getCella(end.getX(), start.getY()); // Prendo la colonna della destinazione, e la
		// traversa di partenza -- cellaPedoneDaCatturare
		if (isPedone(cellaPedone) && cellaPedone.getPezzoCorrente().getColore() != this.colore) { // se la cella e'
			// occupata da un
			// pedone di un colore
			// opposto
			if (this.colore == Colore.bianco) {

				String pedoneAvversarioNero = x + "" + y1 + " " + x + "" + y2; // calcolo ultima mossa del pedone da
				// catturare E.P.

				// Se la traversa di partenza + 1 e' uguale a quella di
				// destinazione, e mi sto spostando in obliquo
				// E la cella di dest. non e' occupata
				// E l'ultima mossa e' quella prevista per l'e.p.
				if (start.getY() - 1 == end.getY() && Math.abs(start.getX() - end.getX()) == 1 && !end.isOccupato()
						&& mosse.get(mosse.size() - 1).equals(pedoneAvversarioNero)) {
					return true;
			} else {
				if (start.getY() + 1 == end.getY() && Math.abs(start.getX() - end.getX()) == 1 && !end.isOccupato()
						&& mosse.get(mosse.size() - 1).equals(pedoneAvversarioBianco))
					return true;

			}
		}

		return false;
	}

	/**
	 * metodo di classe che converte il comando in input in una stringa nel formato
	 * a2 a4
	 *
	 * @param mossa - puo' essere un'avanzata (es. a2) oppure una cattura (es. axb3
	 * @return
	 */
	public static String convertiMossa(String mossa) {

		int variazione = 0;
		String mossaConvertita = "a0 a0";

		// Il formato della mossa sara'  del tipo [a-h](x|:)([a-h][1-8])
		String regexCattura = "[a-h](x|:)([a-h][1-8])( e.p.)?";
		Colore coloreGiocatoreAttuale = Turno.getGiocatoreInTurno().getColore();

		if (mossa.matches(regexCattura)) { // Mossa di cattura in diagonale

			variazione = (coloreGiocatoreAttuale == Colore.bianco) ? -49 : -47;
			if (Math.abs(mossa.charAt(0) - mossa.charAt(2)) == 1)
				mossaConvertita = String.valueOf(mossa.charAt(0)) + String.valueOf(mossa.charAt(3) + variazione) + ' '
						+ String.valueOf(mossa.charAt(2)) + String.valueOf(mossa.charAt(3));

		} else { // avanzata

			// controlla se e' possibile avanti di due o di uno

			if (coloreGiocatoreAttuale == Colore.bianco) {
				Cella c = Scacchiera.getCella(Cella.coordXinInt(mossa.charAt(0)), Cella.coordYinInt('3'));

				if (mossa.charAt(1) == '4' && // Se mi voglio spostare nella 4 traversa, devo determinare se voglio
				// avanzare di 2
						(!c.isOccupato() || !c.getPezzoCorrente().getNome().equals("Pedone")))
					variazione = -50; // Se la terza traversa non è occupata da un pedone allora vengo dalla seconda
				// traversa
				// traversa
				else
					variazione = -49;

			} else { // giocatore pedine nere
				Cella c = Scacchiera.getCella(Cella.coordXinInt(mossa.charAt(0)), 2);
				if (mossa.charAt(1) == '5' && (!c.isOccupato() || !c.getPezzoCorrente().getNome().equals("Pedone")))
					variazione = -46;
				else
					variazione = -47;
			}

			// mossa finale pedone semplice
			mossaConvertita = String.valueOf(mossa.charAt(0)) + // prima traversa
					String.valueOf(mossa.charAt(1) + variazione) + // prima colonna
					' ' + String.valueOf(mossa.charAt(0)) + // seconda traversa
					String.valueOf(mossa.charAt(1)); // seconda colonna

		}
		// controlla che nella cella di partenza ci sia un pedone

		if (Scacchiera.getNomePezzo(Cella.coordXinInt(mossaConvertita.charAt(0)),
				Cella.coordYinInt(mossaConvertita.charAt(1))) != "Pedone")
			return "a0 a0";
		return mossaConvertita;

	}
}
