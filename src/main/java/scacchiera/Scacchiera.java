package scacchiera;

import java.util.ArrayList;
import pedine.*;

/**
 * Rappresentazione astratta della scacchiera
 */
public class Scacchiera {

	private static final int numerocolonne = 8;
	private static final int numerorighe = 8;
	private static final int numeropezzi = 32;

	ArrayList<Pezzo> pezzi_gioco; // TODO: inserire metodi di accesso

	private Cella[][] scacchiera;

	public Scacchiera() {

		pezzi_gioco = new ArrayList<Pezzo>(getNumeroPezzi());
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

}
