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

	private static final int numerocolonne = 8;
	private static final int numerorighe = 8;
	private static final int numeropezzi = 32;

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
		return numerocolonne;
	}

	public final int getNumeroRighe() {
		return numerorighe;
	}

	public final int getNumeroPezzi() {
		return numeropezzi;
	}

	public Cella getCella(final int x, final int y) {
		return scacchiera[x][y];
	}

	public final void setCella(Cella nuovaCella, final int x, final int y) {
		scacchiera[x][y] = nuovaCella;
	}

	/* metodo che inzializza la scacchiera e la prepara per una nuova partita */
	public final void inizializzaScacchiera() {

		// Inizializzazione della torre
		getCella(Cella.coordXinInt('a'), Cella.coordYinInt(1))
				.setPezzoCorrente(new Torre(Colore.bianco, getCella(Cella.coordXinInt('a'), Cella.coordYinInt(1))));
		getCella(Cella.coordXinInt('h'), Cella.coordYinInt(1))
				.setPezzoCorrente(new Torre(Colore.bianco, getCella(Cella.coordXinInt('h'), Cella.coordYinInt(1))));
		getCella(Cella.coordXinInt('a'), Cella.coordYinInt(8))
				.setPezzoCorrente(new Torre(Colore.nero, getCella(Cella.coordXinInt('a'), Cella.coordYinInt(8))));
		getCella(Cella.coordXinInt('h'), Cella.coordYinInt(8))
				.setPezzoCorrente(new Torre(Colore.nero, getCella(Cella.coordXinInt('h'), Cella.coordYinInt(8))));

		// Inizializzazione del cavallo
		getCella(Cella.coordXinInt('b'), Cella.coordYinInt(1))
				.setPezzoCorrente(new Cavallo(Colore.bianco, getCella(Cella.coordXinInt('b'), Cella.coordYinInt(1))));
		getCella(Cella.coordXinInt('g'), Cella.coordYinInt(1))
				.setPezzoCorrente(new Cavallo(Colore.bianco, getCella(Cella.coordXinInt('g'), Cella.coordYinInt(1))));
		getCella(Cella.coordXinInt('b'), Cella.coordYinInt(8))
				.setPezzoCorrente(new Cavallo(Colore.nero, getCella(Cella.coordXinInt('b'), Cella.coordYinInt(8))));
		getCella(Cella.coordXinInt('g'), Cella.coordYinInt(8))
				.setPezzoCorrente(new Cavallo(Colore.nero, getCella(Cella.coordXinInt('g'), Cella.coordYinInt(8))));

		// Inizializzazione dell'alfiere
		getCella(Cella.coordXinInt('c'), Cella.coordYinInt(1))
				.setPezzoCorrente(new Alfiere(Colore.bianco, getCella(Cella.coordXinInt('c'), Cella.coordYinInt(1))));
		getCella(Cella.coordXinInt('f'), Cella.coordYinInt(1))
				.setPezzoCorrente(new Alfiere(Colore.bianco, getCella(Cella.coordXinInt('f'), Cella.coordYinInt(1))));
		getCella(Cella.coordXinInt('c'), Cella.coordYinInt(8))
				.setPezzoCorrente(new Alfiere(Colore.nero, getCella(Cella.coordXinInt('c'), Cella.coordYinInt(8))));
		getCella(Cella.coordXinInt('f'), Cella.coordYinInt(8))
				.setPezzoCorrente(new Alfiere(Colore.nero, getCella(Cella.coordXinInt('f'), Cella.coordYinInt(8))));

		// Inizializzazione della regina

		getCella(Cella.coordXinInt('d'), Cella.coordYinInt(1))
				.setPezzoCorrente(new Regina(Colore.bianco, getCella(Cella.coordXinInt('d'), Cella.coordYinInt(1))));
		getCella(Cella.coordXinInt('d'), Cella.coordYinInt(8))
				.setPezzoCorrente(new Regina(Colore.nero, getCella(Cella.coordXinInt('d'), Cella.coordYinInt(8))));

		// Inizializzazione del re

		getCella(Cella.coordXinInt('e'), Cella.coordYinInt(1))
				.setPezzoCorrente(new Re(Colore.bianco, getCella(Cella.coordXinInt('e'), Cella.coordYinInt(1))));
		getCella(Cella.coordXinInt('e'), Cella.coordYinInt(8))
				.setPezzoCorrente(new Re(Colore.nero, getCella(Cella.coordXinInt('e'), Cella.coordYinInt(8))));

		// Inizializzazione dei pedoni
		for (int i = 0; i < getNumeroColonne(); i++) {
			getCella(i, Cella.coordYinInt(7))
					.setPezzoCorrente(new Pedone(Colore.nero, getCella(i, Cella.coordYinInt(7))));
			getCella(i, Cella.coordYinInt(2))
					.setPezzoCorrente(new Pedone(Colore.bianco, getCella(i, Cella.coordYinInt(2))));
		}
	}
	/* metodo per stampare nella console la scacchiera */
	public final void stampa() {
		System.out.println("     a    b   c    d   e    f   g   h");
		System.out.println("  --------------------------------------");
		System.out.print("8  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("| ");
			getCella(i, 0).stampaPezzo();
			System.out.print(" ");
		}
		System.out.print("|\n");
		System.out.println("  --------------------------------------");
		System.out.print("7  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("| ");
			getCella(i, 1).stampaPezzo();
			System.out.print(" ");
		}
		System.out.print("|\n");
		System.out.println("  --------------------------------------");
		System.out.print("6  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("| ");
			getCella(i, 2).stampaPezzo();
			System.out.print(" ");
		}
		System.out.print("|\n");
		System.out.println("  --------------------------------------");
		System.out.print("5  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("| ");
			getCella(i, 3).stampaPezzo();
			System.out.print(" ");
		}
		System.out.print("|\n");
		System.out.println("  --------------------------------------");
		System.out.print("4  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("| ");
			getCella(i, 4).stampaPezzo();
			System.out.print(" ");
		}
		System.out.print("|\n");
		System.out.println("  --------------------------------------");
		System.out.print("3  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("| ");
			getCella(i, 5).stampaPezzo();
			System.out.print(" ");
		}
		System.out.print("|\n");
		System.out.println("  --------------------------------------");
		System.out.print("2  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("| ");
			getCella(i, 6).stampaPezzo();
			System.out.print(" ");
		}
		System.out.print("|\n");
		System.out.println("  --------------------------------------");
		System.out.print("1  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("| ");
			getCella(i, 7).stampaPezzo();
			System.out.print(" ");
		}
		System.out.print("|\n");
		System.out.println("  --------------------------------------");
	}
}
