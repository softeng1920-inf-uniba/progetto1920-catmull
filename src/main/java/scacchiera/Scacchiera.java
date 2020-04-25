package scacchiera;

import it.uniba.main.Colore;
import pedine.Alfiere;
import pedine.Cavallo;
import pedine.Pedone;
import pedine.Re;
import pedine.Regina;
import pedine.Torre;

/**
 * Rappresentazione astratta della scacchiera
 */
public class Scacchiera {

	private static final int NUMEROCOLONNE = 8;
	private static final int NUMERORIGHE = 8;

	private Cella[][] scacchiera;

	public Scacchiera() {

		scacchiera = new Cella[getNumeroColonne()][getNumeroRighe()];
		for (int i = 0; i < getNumeroColonne(); i++) {
			for (int j = 0; j < getNumeroRighe(); j++) {
				scacchiera[i][j] = new Cella(i, j, null);
			}
		}
	}

	public final int getNumeroColonne() {
		return NUMEROCOLONNE;
	}

	public final int getNumeroRighe() {
		return NUMERORIGHE;
	}

	/**
	 * Date le coordinate, ritorno il riferimento di una cella della scacchiera.
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public Cella getCella(final int x, final int y) {
		return scacchiera[x][y];
	}

	public final void setCella(final Cella nuovaCella, final int x, final int y) {
		scacchiera[x][y] = nuovaCella;
	}

	/**
	 * contolla che le coordinate in input siano valide
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public final boolean controllaRange(int x, int y) {
		if (x < NUMERORIGHE && y < NUMEROCOLONNE && x >= 0 && y >= 0)
			return true;
		else
			return false;
	}

	/**
	 * metodo che inizializza la scacchiera e la prepara per una nuova partita
	 */
	public final void inizializzaScacchiera() {

		// Inizializzazione della torre

		getCella(Cella.coordXinInt('a'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Torre(Colore.bianco, getCella(Cella.coordXinInt('a'), Cella.coordYinInt('1'))));
		getCella(Cella.coordXinInt('h'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Torre(Colore.bianco, getCella(Cella.coordXinInt('h'), Cella.coordYinInt('1'))));

		getCella(Cella.coordXinInt('a'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Torre(Colore.nero, getCella(Cella.coordXinInt('a'), Cella.coordYinInt('8'))));
		getCella(Cella.coordXinInt('h'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Torre(Colore.nero, getCella(Cella.coordXinInt('h'), Cella.coordYinInt('8'))));

		// Inizializzazione del cavallo
		getCella(Cella.coordXinInt('b'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Cavallo(Colore.bianco, getCella(Cella.coordXinInt('b'), Cella.coordYinInt('1'))));
		getCella(Cella.coordXinInt('g'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Cavallo(Colore.bianco, getCella(Cella.coordXinInt('g'), Cella.coordYinInt('1'))));

		getCella(Cella.coordXinInt('b'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Cavallo(Colore.nero, getCella(Cella.coordXinInt('b'), Cella.coordYinInt('8'))));
		getCella(Cella.coordXinInt('g'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Cavallo(Colore.nero, getCella(Cella.coordXinInt('g'), Cella.coordYinInt('8'))));

		// Inizializzazione dell'alfiere
		getCella(Cella.coordXinInt('c'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Alfiere(Colore.bianco, getCella(Cella.coordXinInt('c'), Cella.coordYinInt('1'))));
		getCella(Cella.coordXinInt('f'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Alfiere(Colore.bianco, getCella(Cella.coordXinInt('f'), Cella.coordYinInt('1'))));

		getCella(Cella.coordXinInt('c'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Alfiere(Colore.nero, getCella(Cella.coordXinInt('c'), Cella.coordYinInt('8'))));
		getCella(Cella.coordXinInt('f'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Alfiere(Colore.nero, getCella(Cella.coordXinInt('f'), Cella.coordYinInt('8'))));

		// Inizializzazione della regina
		getCella(Cella.coordXinInt('d'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Regina(Colore.bianco, getCella(Cella.coordXinInt('d'), Cella.coordYinInt('1'))));
		getCella(Cella.coordXinInt('d'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Regina(Colore.nero, getCella(Cella.coordXinInt('d'), Cella.coordYinInt('8'))));

		// Inizializzazione del re
		getCella(Cella.coordXinInt('e'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Re(Colore.bianco, getCella(Cella.coordXinInt('e'), Cella.coordYinInt('1'))));
		getCella(Cella.coordXinInt('e'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Re(Colore.nero, getCella(Cella.coordXinInt('e'), Cella.coordYinInt('8'))));

		// Inizializzazione dei pedoni
		for (int i = 0; i < getNumeroColonne(); i++) {
			getCella(i, Cella.coordYinInt('7'))
					.setPezzoCorrente(new Pedone(Colore.nero, getCella(i, Cella.coordYinInt('7'))));
			getCella(i, Cella.coordYinInt('2'))
					.setPezzoCorrente(new Pedone(Colore.bianco, getCella(i, Cella.coordYinInt('2'))));
		}

		for (int k = 2; k < 6; k++) {
			for (int j = 0; j < getNumeroColonne(); j++) {
				scacchiera[j][k] = new Cella(j, k, null);
			}
		}

	}

	/**
	 * metodo per stampare nella console la scacchiera
	 */
	public final void stampa() {
		System.out.println("      a     b     c     d     e     f     g     h");
		for (int j = 8; j > 0; j--) {
			System.out.println("  --------------------------------------------------");
			System.out.print(j + "  ");
			for (int i = 0; i < 8; i++) {
				System.out.print("| ");
				getCella(i, Math.abs(j - 8)).stampaPezzo();
				System.out.print("  ");
			}
			System.out.print("|");
			System.out.println("  " + j);
		}
		System.out.println("  --------------------------------------------------");
		System.out.println("      a     b     c     d     e     f     g     h");
	}

	/**
	 * simula il movimento di un pezzo nella scacchiera
	 * 
	 * @param c1
	 * @param c2
	 */
	public void scambiaCella(Cella c1, Cella c2) {
		c2.setPezzoCorrente(c1.getPezzoCorrente());
		c2.setOccupato(c1.isOccupato());
		c1.setOccupato(false);
		c1.rimuoviPezzoCorrente();
	}

	/**
	 * permette di simulare la mossa in cui un pezzo mangia un altro
	 * 
	 * @param x
	 * @param y
	 */
	public final void mangiaPezzo(int x, int y) {
		getCella(x, y).rimuoviPezzoCorrente();

	}

	public final String getNomePezzo(int x, int y) {

		if (controllaRange(x, y) && getCella(x, y).isOccupato()) {

			return getCella(x, y).getPezzoCorrente().getNome();

		}

		return "Vuota";

	}
}
