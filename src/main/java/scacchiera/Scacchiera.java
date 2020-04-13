package scacchiera;

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

	public final void stampa() {
		System.out.println("      a     b     c     d     e     f    g     h");
		System.out.println("  -------------------------------------------------");
		System.out.print("8  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("  |");
			getCella(0, i).stampaPezzo();
		}
		System.out.print("  |\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("7  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(1, i).stampaPezzo();
		}
		System.out.print("  |\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("6  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(2, i).stampaPezzo();
		}
		System.out.print("  |\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("5  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(2, i).stampaPezzo();
		}
		System.out.print("  |\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("4  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(3, i).stampaPezzo();
		}
		System.out.print("  |\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("3  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(4, i).stampaPezzo();
		}
		System.out.print("  |\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("2  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(5, i).stampaPezzo();
		}
		System.out.print("  |\n");
		System.out.println("  -------------------------------------------------");
		System.out.print("1  ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  ");
			getCella(6, i).stampaPezzo();
		}
		System.out.print("  |\n");
		System.out.println("  -------------------------------------------------");
	}
}
