package scacchiera;

import it.uniba.main.Colore;
import pedine.Alfiere;
import pedine.Cavallo;
import pedine.Pedone;
import pedine.Pezzo;
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

	public final void InizializzaScacchiera() {
		Pezzo pedinadamettere;

		// PEDONI NERI
		for (int i = 6, j = 0; j < getNumeroColonne(); j++) {
			pedinadamettere = new Pedone(Colore.nero, getCella(i, j));
			getCella(i, j).setPezzoCorrente(pedinadamettere);

		}
		// PEDONI BIANCHI
		for (int i = 1, j = 0; j < getNumeroColonne(); j++) {
			pedinadamettere = new Pedone(Colore.bianco, getCella(i, j));
			getCella(i, j).setPezzoCorrente(pedinadamettere);
		}
		// TORRI NERE
		pedinadamettere = new Torre(Colore.nero, getCella(7, 0));
		getCella(7, 0).setPezzoCorrente(pedinadamettere);
		pedinadamettere = new Torre(Colore.nero, getCella(7, 7));
		getCella(7, 7).setPezzoCorrente(pedinadamettere);
		// TORRI BIANCHE
		pedinadamettere = new Torre(Colore.bianco, getCella(0, 0));
		getCella(0, 0).setPezzoCorrente(pedinadamettere);
		getCella(0, 7).setPezzoCorrente(pedinadamettere);

		// ALFIERI NERI
		pedinadamettere = new Alfiere(Colore.nero, getCella(7, 2));
		getCella(7, 2).setPezzoCorrente(pedinadamettere);
		pedinadamettere = new Alfiere(Colore.nero, getCella(7, 5));
		getCella(7, 5).setPezzoCorrente(pedinadamettere);
		// ALFIERI BIANCHI
		pedinadamettere = new Alfiere(Colore.bianco, getCella(0, 2));
		getCella(0, 2).setPezzoCorrente(pedinadamettere);
		pedinadamettere = new Alfiere(Colore.bianco, getCella(0, 5));
		getCella(0, 5).setPezzoCorrente(pedinadamettere);

		// CAVALLI NERI
		pedinadamettere = new Cavallo(Colore.nero, getCella(7, 1));
		getCella(7, 1).setPezzoCorrente(pedinadamettere);
		pedinadamettere = new Cavallo(Colore.nero, getCella(7, 6));
		getCella(7, 6).setPezzoCorrente(pedinadamettere);
		// CAVALLI BIANCHI
		pedinadamettere = new Cavallo(Colore.bianco, getCella(0, 1));
		getCella(0, 1).setPezzoCorrente(pedinadamettere);
		pedinadamettere = new Cavallo(Colore.bianco, getCella(0, 6));
		getCella(0, 6).setPezzoCorrente(pedinadamettere);

		// REGINA NERA
		pedinadamettere = new Regina(Colore.nero, getCella(7, 4));
		getCella(7, 4).setPezzoCorrente(pedinadamettere);
		// REGINA BIANCA
		pedinadamettere = new Regina(Colore.bianco, getCella(7, 4));
		getCella(0, 4).setPezzoCorrente(pedinadamettere);

		// RE NERO
		pedinadamettere = new Re(Colore.nero, getCella(7, 3));
		getCella(7, 3).setPezzoCorrente(pedinadamettere);
		// RE BIANCO
		pedinadamettere = new Re(Colore.bianco, getCella(0, 3));
		getCella(0, 3).setPezzoCorrente(pedinadamettere);

	}

	public final void stampa() {
		System.out.println("      a     b     c     d     e     f    g     h");
		System.out.println("  -------------------------------------------------");
		System.out.print("8  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(0, i).stampaPezzo();
		}
		System.out.print("|\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("7  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(1, i).stampaPezzo();
		}
		System.out.print("|\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("6  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(2, i).stampaPezzo();
		}
		System.out.print("|\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("5  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(3, i).stampaPezzo();
		}
		System.out.print("|\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("4  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(4, i).stampaPezzo();
		}
		System.out.print("|\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("3  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(5, i).stampaPezzo();
		}
		System.out.print("|\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("2  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(6, i).stampaPezzo();
		}
		System.out.print("|\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("1  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(7, i).stampaPezzo();
		}
		System.out.print("|\n");
		System.out.println("  -------------------------------------------------");
	}
}
