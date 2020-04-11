package scacchiera;

import pedine.Pezzo;

/** Classe per rappresentere le singole parti della scacchiera */
public final class Cella {
	private int x;
	private int y;
	private boolean occupato;
	private Pezzo pezzoCorrente;

	/** Costuttore */
	public Cella(final int x, final int y, final Pezzo pezzoCorrente) {
		this.x = x;
		this.y = y;
		this.pezzoCorrente = pezzoCorrente;
		if (this.pezzoCorrente != null) {
			this.occupato = true;
		}
	}

	/** Disegna il pezzo che contiene in output */
	void stampaPezzo() {
		if (pezzoCorrente != null) {
			pezzoCorrente.disegnapezzo();
		} else {
			System.out.print(" . ");
		}
	}

	// ---------Metodi di setting---------

	/** Modifica la coordinata x */
	void setX(final int newx) {
		x = newx;
	}

	/** Modifica la coordinata y */
	void setY(final int newy) {
		y = newy;
	}

	/** Modifica la variabile occupato */
	void setOccupato(final boolean occupato) {
		this.occupato = occupato;
	}

	/** Modifica il tipo di pezzo che contiene */
	public void setPezzoCorrente(final Pezzo pezzoCorrente) {
		this.pezzoCorrente = pezzoCorrente;
	}

	// --------Metodi di Get--------

	/** Restituisce la coordinata x */
	int getX() {
		return x;
	}

	/** Restituisce la coordinata y */
	int getY() {
		return y;
	}

	/** Restituisce la variabile occupato */
	boolean getOccupato() {
		return occupato;
	}

	/** Restituisce il pezzo che contiene */
	Pezzo getPezzoCorrente() {
		return pezzoCorrente;
	}

	// ------Metodi per aggiugere o togliere pezzi dalla scacchiera

	/** Aggiunge un pezzo alla cella */
	public void aggiungiPezzo(final Pezzo nuovoPezzo) {

		setPezzoCorrente(nuovoPezzo);
		setOccupato(true);

	}

	/** Rimuove il pezzo nella cella */
	public void rimuoviPezzoCorrente() {
		setOccupato(false);
		setPezzoCorrente(null);
	}
}
