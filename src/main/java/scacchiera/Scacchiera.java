package scacchiera;

import gioco.Colore;
import scacchiera.pedine.Alfiere;
import scacchiera.pedine.Cavallo;
import scacchiera.pedine.Pedone;
import scacchiera.pedine.Re;
import scacchiera.pedine.Regina;
import scacchiera.pedine.Torre;

/**
 * La classe scacchiera permette di rappresentare lo stato del gioco,
 * visualizzando i pezzi in essa. E'costituita da un numero costante di colonne
 * e di righe. La classe scacchiera è una matrice NxM di Celle. La classe
 * Scacchiera e' di tipo ENTITY
 */
public class Scacchiera {

	private static final int NUMEROCOLONNE = 8;
	private static final int NUMERORIGHE = 8;

	private static Cella[][] scacchiera = new Cella[getNumeroColonne()][getNumeroRighe()];

	private Scacchiera() {
	}

	/**
	 * costruttore statico per la scacchiera
	 */
	public static void nuovaScacchiera() {
		scacchiera = new Cella[getNumeroColonne()][getNumeroRighe()];
		for (int i = 0; i < getNumeroColonne(); i++) {
			for (int j = 0; j < getNumeroRighe(); j++) {
				scacchiera[i][j] = new Cella(i, j, null);
			}
		}
		inizializzaScacchiera();
	}
	public static final int getNumeroColonne() {
		return NUMEROCOLONNE;
	}

	public static final int getNumeroRighe() {
		return NUMERORIGHE;
	}

	/**
	 * Date le coordinate, ritorno il riferimento di una cella della scacchiera.
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static Cella getCella(final int x, final int y) {
		return scacchiera[x][y];
	}

	public static void setCella(final Cella nuovaCella, final int x, final int y) {
		scacchiera[x][y] = nuovaCella;
	}

	/**
	 * contolla che le coordinate in input siano valide
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isRangeValido(int x, int y) {
		return x < getNumeroRighe() && y < getNumeroColonne() && x >= 0 && y >= 0;
	}

	/**
	 * metodo che inizializza la scacchiera e la prepara per una nuova partita
	 */
	private static void inizializzaScacchiera() {
		// Inizializzazione della torre

		getCella(Cella.coordXinInt('a'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Torre(Colore.bianco));
		getCella(Cella.coordXinInt('h'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Torre(Colore.bianco));

		getCella(Cella.coordXinInt('a'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Torre(Colore.nero));
		getCella(Cella.coordXinInt('h'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Torre(Colore.nero));

		// Inizializzazione del cavallo
		getCella(Cella.coordXinInt('b'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Cavallo(Colore.bianco));
		getCella(Cella.coordXinInt('g'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Cavallo(Colore.bianco));

		getCella(Cella.coordXinInt('b'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Cavallo(Colore.nero));
		getCella(Cella.coordXinInt('g'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Cavallo(Colore.nero));

		// Inizializzazione dell'alfiere
		getCella(Cella.coordXinInt('c'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Alfiere(Colore.bianco));
		getCella(Cella.coordXinInt('f'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Alfiere(Colore.bianco));

		getCella(Cella.coordXinInt('c'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Alfiere(Colore.nero));
		getCella(Cella.coordXinInt('f'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Alfiere(Colore.nero));

		// Inizializzazione della regina
		getCella(Cella.coordXinInt('d'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Regina(Colore.bianco));
		getCella(Cella.coordXinInt('d'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Regina(Colore.nero));

		// Inizializzazione del re
		getCella(Cella.coordXinInt('e'), Cella.coordYinInt('1'))
				.setPezzoCorrente(new Re(Colore.bianco));
		getCella(Cella.coordXinInt('e'), Cella.coordYinInt('8'))
				.setPezzoCorrente(new Re(Colore.nero));

		// Inizializzazione dei pedoni
		for (int i = 0; i < getNumeroColonne(); i++) {
			getCella(i, Cella.coordYinInt('7'))
					.setPezzoCorrente(new Pedone(Colore.nero));
			getCella(i, Cella.coordYinInt('2'))
					.setPezzoCorrente(new Pedone(Colore.bianco));
		}

	}

	/**
	 * simula il movimento di un pezzo nella scacchiera
	 *
	 * @param c1
	 * @param c2
	 */
	public static void scambiaCella(Cella c1, Cella c2) {
		c2.setPezzoCorrente(c1.getPezzoCorrente());
		c2.setOccupato(c1.isOccupato());
		c1.rimuoviPezzoCorrente();
	}

	/**
	 * permette di simulare la mossa in cui un pezzo mangia un altro
	 *
	 * @param x
	 * @param y
	 */
	public static void mangiaPezzo(int x, int y) {
		getCella(x, y).rimuoviPezzoCorrente();

	}

	/**
	 * Permette di avere in output il nome del pezzo. Evita errori legati a indici
	 * errati e cella vuota.
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static final String getNomePezzo(int x, int y) {
		if (isRangeValido(x, y) && getCella(x, y).isOccupato()) {
			return getCella(x, y).getPezzoCorrente().getNome();
		}
		return "Vuota";
	}
}
