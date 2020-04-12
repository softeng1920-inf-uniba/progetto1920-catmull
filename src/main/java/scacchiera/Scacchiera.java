package scacchiera;

import pedine.Pedone;
import pedine.Re;
import pedine.Alfiere;
import pedine.Regina;
import pedine.Torre;
import pedine.Cavallo;

import it.uniba.main.Colore;
import it.uniba.main.Costanti;

/**
 * Rappresentazione astratta della scacchiera
 */
public class Scacchiera {

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
		return Costanti.N_COLONNE;
	}

	public final int getNumeroRighe() {
		return Costanti.N_RIGHE;
	}

	public final int getNumeroPezzi() {
		return Costanti.N_PEZZI;
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

	public final void inizializzaScacchiera() {

		// Inizializzazione della torre
		getCella(Cella.coordXinInt('a'), Cella.coordYinInt(1)).setPezzoCorrente(
				new Torre(Colore.bianco, getCella(Cella.coordXinInt('a'), Cella.coordYinInt(1)), "\u2656"));
		getCella(Cella.coordXinInt('h'), Cella.coordYinInt(1)).setPezzoCorrente(
				new Torre(Colore.bianco, getCella(Cella.coordXinInt('h'), Cella.coordYinInt(1)), "\u2656"));

		getCella(Cella.coordXinInt('a'), Cella.coordYinInt(8)).setPezzoCorrente(
				new Torre(Colore.nero, getCella(Cella.coordXinInt('a'), Cella.coordYinInt(8)), "\u265C"));
		getCella(Cella.coordXinInt('h'), Cella.coordYinInt(8)).setPezzoCorrente(
				new Torre(Colore.nero, getCella(Cella.coordXinInt('h'), Cella.coordYinInt(8)), "\u265C"));

		// Inizializzazione del cavallo
		getCella(Cella.coordXinInt('b'), Cella.coordYinInt(1)).setPezzoCorrente(
				new Cavallo(Colore.bianco, getCella(Cella.coordXinInt('b'), Cella.coordYinInt(1)), "\u2658"));
		getCella(Cella.coordXinInt('g'), Cella.coordYinInt(1)).setPezzoCorrente(
				new Cavallo(Colore.bianco, getCella(Cella.coordXinInt('g'), Cella.coordYinInt(1)), "\u2658"));

		getCella(Cella.coordXinInt('b'), Cella.coordYinInt(8)).setPezzoCorrente(
				new Cavallo(Colore.nero, getCella(Cella.coordXinInt('b'), Cella.coordYinInt(8)), "\u265e"));
		getCella(Cella.coordXinInt('g'), Cella.coordYinInt(8)).setPezzoCorrente(
				new Cavallo(Colore.nero, getCella(Cella.coordXinInt('g'), Cella.coordYinInt(8)), "\u265e"));

		// Inizializzazione dell'alfiere
		getCella(Cella.coordXinInt('c'), Cella.coordYinInt(1)).setPezzoCorrente(
				new Alfiere(Colore.bianco, getCella(Cella.coordXinInt('c'), Cella.coordYinInt(1)), "\u2657"));
		getCella(Cella.coordXinInt('f'), Cella.coordYinInt(1)).setPezzoCorrente(
				new Alfiere(Colore.bianco, getCella(Cella.coordXinInt('f'), Cella.coordYinInt(1)), "\u2657"));

		getCella(Cella.coordXinInt('c'), Cella.coordYinInt(8)).setPezzoCorrente(
				new Alfiere(Colore.nero, getCella(Cella.coordXinInt('c'), Cella.coordYinInt(8)), "\u265D"));
		getCella(Cella.coordXinInt('f'), Cella.coordYinInt(8)).setPezzoCorrente(
				new Alfiere(Colore.nero, getCella(Cella.coordXinInt('f'), Cella.coordYinInt(8)), "\u265D"));

		// Inizializzazione della regina
		getCella(Cella.coordXinInt('d'), Cella.coordYinInt(1)).setPezzoCorrente(
				new Regina(Colore.bianco, getCella(Cella.coordXinInt('d'), Cella.coordYinInt(1)), "\u2655"));
		getCella(Cella.coordXinInt('d'), Cella.coordYinInt(8)).setPezzoCorrente(
				new Regina(Colore.nero, getCella(Cella.coordXinInt('d'), Cella.coordYinInt(8)), "\u265B"));

		// Inizializzazione del re
		getCella(Cella.coordXinInt('e'), Cella.coordYinInt(1)).setPezzoCorrente(
				new Re(Colore.bianco, getCella(Cella.coordXinInt('e'), Cella.coordYinInt(1)), "\u2654"));
		getCella(Cella.coordXinInt('e'), Cella.coordYinInt(8)).setPezzoCorrente(
				new Re(Colore.nero, getCella(Cella.coordXinInt('e'), Cella.coordYinInt(8)), "\u265A"));

		// Inizializzazione dei pedoni
		for (int i = 0; i < getNumeroColonne(); i++) {
			getCella(i, Cella.coordYinInt(7))
					.setPezzoCorrente(new Pedone(Colore.nero, getCella(i, Cella.coordYinInt(7)), "\u265F"));
			getCella(i, Cella.coordYinInt(2))
					.setPezzoCorrente(new Pedone(Colore.bianco, getCella(i, Cella.coordYinInt(2)), "\u2659"));
		}

	}

}
