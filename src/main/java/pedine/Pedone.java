package pedine;

import it.uniba.main.Colore;
import scacchiera.Scacchiera;
import scacchiera.Cella;

/**
 * Classe per rappresentere il sottotipo di pezzo chiamato Pedone
 */
public final class Pedone extends Pezzo {

	private boolean primaMossa;

	/**
	 * Costruttore
	 */
	public Pedone(final Colore colore, final Cella posizioneCorrente) {
		super("Pedone", colore, posizioneCorrente);
		this.setPrimaMossa(true);
		if (colore == Colore.nero) {
			simbolo = '\u265f';
		} else {
			simbolo = '\u2659';
		}
	}

	/**
	 * Restituisce vero se e' la prima mossa, falso altrimenti
	 */
	public boolean isPrimaMossa() {
		return this.primaMossa;
	}

	/**
	 * Aggiorna la variabile prima mossa
	 */
	public void setPrimaMossa(final boolean primaMossa) {
		this.primaMossa = primaMossa;
	}

	public final boolean isMossaValidaNero(String comando, Scacchiera s) {
		if ((Cella.coordXinInt(comando.charAt(0)) == Cella.coordXinInt(comando.charAt(3)))) { // movimento verticale
			if (Cella.coordYinInt(comando.charAt(1)) + 2 == Cella.coordYinInt(comando.charAt(4)) && isPrimaMossa()) {
				if (!(s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)))
						.isOccupato())) {
					setPrimaMossa(false);
					return true;

				} else
					return false;
			}

			else if (Cella.coordYinInt(comando.charAt(1)) + 1 == Cella.coordYinInt(comando.charAt(4))) {
				if (!(s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)))
						.isOccupato())) {
					setPrimaMossa(false);
					return true;

				} else
					return false;
			} else
				return false;
		}
		// a1 b3
		// 01234
		else // movimento obliquo
		{
			if ((Cella.coordXinInt(comando.charAt(0)) + 1 == Cella.coordXinInt(comando.charAt(3))
					|| Cella.coordXinInt(comando.charAt(0)) - 1 == Cella.coordXinInt(comando.charAt(3)))
					&& Cella.coordYinInt(comando.charAt(1)) + 1 == Cella.coordYinInt(comando.charAt(4))) {
				if (s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4))).isOccupato()
						&& s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)))
								.getPezzoCorrente().getColore() != getColore())
				// in codice getCella(Cella.coordXinInt(comando[1]+1),
				// Cella.coordYinInt(comando[2]+1))
				{
					setPrimaMossa(false);
					return true;

				} else
					return false;
			} else
				return false;
		}
	}

	public final boolean isMossaValidaBianco(String comando, Scacchiera s) {
		if ((Cella.coordXinInt(comando.charAt(0)) == Cella.coordXinInt(comando.charAt(3)))) // movimento verticale
		{
			if (Cella.coordYinInt(comando.charAt(1)) - 2 == Cella.coordYinInt(comando.charAt(4)) && isPrimaMossa()) {
				if (!(s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)))
						.isOccupato())) {
					setPrimaMossa(false);
					return true;

				} else
					return false;
			}

			else if (Cella.coordYinInt(comando.charAt(1)) - 1 == Cella.coordYinInt(comando.charAt(4))) {
				if (!(s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)))
						.isOccupato())) {
					setPrimaMossa(false);
					return true;

				} else
					return false;
			} else
				return false;
		}
		// a1 b3
		// 01234
		else // movimento obliquo
		{
			if ((Cella.coordXinInt(comando.charAt(0)) - 1 == Cella.coordXinInt(comando.charAt(3))
					|| Cella.coordXinInt(comando.charAt(0)) + 1 == Cella.coordXinInt(comando.charAt(3)))
					&& Cella.coordYinInt(comando.charAt(1)) - 1 == Cella.coordYinInt(comando.charAt(4))) {
				if (s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4))).isOccupato()
						&& s.getCella(Cella.coordXinInt(comando.charAt(3)), Cella.coordYinInt(comando.charAt(4)))
								.getPezzoCorrente().getColore() != getColore())
				// in codice getCella(Cella.coordXinInt(comando[1]+1),
				// Cella.coordYinInt(comando[2]+1))
				{
					setPrimaMossa(false);
					return true;

				} else
					return false;
			} else
				return false;
		}
	}

}
