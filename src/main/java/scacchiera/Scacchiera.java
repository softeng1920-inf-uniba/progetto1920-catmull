package scacchiera;

import java.util.ArrayList;


import pedine.Pezzo;

public class Scacchiera {

	private static final int numerocolonne = 8;
	private static final int numerorighe = 8;
	private static final int numeropezzi = 32;

	public ArrayList<Pezzo> pezzi_gioco;

	Cella[][] scacchiera;

	public final int getNumeroColonne() {
		return numerocolonne;
	}

	public final int getNumeroRighe() {
		return numerorighe;
	}

	public Cella getCella(final int x, final int y) {
		return scacchiera[x][y];
	}

	public final int getNumeroPezzi() {
		return numeropezzi;
	}

	public final void setCella(Cella nuovaCella, final int x, final int y) {
		scacchiera[x][y] = nuovaCella;
	}

	public Scacchiera() {

		pezzi_gioco = new ArrayList<Pezzo>();
		scacchiera = new Cella[numerocolonne][numerorighe];
		for (int i = 0; i < getNumeroColonne(); i++) {
			for (int j = 0; j < getNumeroRighe(); j++) {
				scacchiera[i][j] = new Cella(i, j, null);
			}
		}
	}
}
