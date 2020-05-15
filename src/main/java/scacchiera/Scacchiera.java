package scacchiera;

import gioco.Colore;
import pedine.Alfiere;
import pedine.Cavallo;
import pedine.Pedone;
import pedine.Re;
import pedine.Regina;
import pedine.Torre;

/**
 * La classe scacchiera permette di rappresentare lo stato del gioco,
 * visualizzando i pezzi in essa. E'costituita da un numero costante di colonne
 * e di righe. La classe scacchiera e' una matrice NxM di Celle. La classe
 * Scacchiera e' di tipo ENTITY
 */
public class Scacchiera {

    public static final int SETTIMA_TRAVERSA = 6; // La prima traversa avrà valore 0, l'ottava avrà valore 7

    public static final int NUMEROCOLONNE = 8;
    public static final int NUMERORIGHE = 8;

	private static Cella[][] scacchiera = new Cella[getNumeroColonne()][getNumeroRighe()];

    /**
     * Avvalora e inizializza la matrice di celle, una per una
     */
    public Scacchiera() {

		for (int i = 0; i < getNumeroColonne(); i++) {
			for (int j = 0; j < getNumeroRighe(); j++) {
				scacchiera[i][j] = new Cella(i, j, null);
			}
		}
	}

    /**
     * Restituisce il numero di colonne della scacchiera
     * 
     * @return Valore intero indicante il numero di colonne
     */
    public static final int getNumeroColonne() {
	return NUMEROCOLONNE;
    }

    /**
     * Restituisce il numero di righe della scacchiera
     * 
     * @return Valore intero indicante il numero di righe
     */
    public static final int getNumeroRighe() {
	return NUMERORIGHE;
    }

    /**
     * Date le coordinate, ritorna il riferimento di una cella della scacchiera.
     *
     * @param x coordinata delle colonne (valore compreso fra 0 e 7)
     * @param y coordinata delle righe (valore compreso fra 0 e 7)
     * @return Riferimento di tipo Cella della scacchiera in posizione (x, y)
     */
    public static Cella getCella(final int x, final int y) {
	return scacchiera[x][y];
    }

    /**
     * Imposta il riferimento di una nuova cella in una posizione (x, y)
     * 
     * @param nuovaCella Riferimento di tipo Cella della scacchiera da impostare
     * @param x          coordinata delle colonne (valore compreso fra 0 e 7)
     * @param y          coordinata delle righe (valore compreso fra 0 e 7)
     */
    public final void setCella(final Cella nuovaCella, final int x, final int y) {
	scacchiera[x][y] = nuovaCella;
    }

    /**
     * Controlla che le coordinate in input siano valide per la scacchiera, ovvero
     * non vadano oltre i limiti.
     *
     * @param x coordinata delle colonne (valore compreso fra 0 e 7)
     * @param y coordinata delle righe (valore compreso fra 0 e 7)
     * @return true se le coordinate inserite si riferiscono ad una cella della
     *         scacchiera, false altrimenti
     */
    public static boolean isRangeValido(final int x, final int y) {
	return x < getNumeroRighe() && y < getNumeroColonne() && x >= 0 && y >= 0;
    }

    /**
     * Imposta la scacchiera allo stato iniziale, con i pezzi nelle posizioni
     * standard
     */
    public static void inizializzaScacchiera() {

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

	}

    

    /**
     * simula il movimento di un pezzo nella scacchiera
     *
     * @param c1 Cella di partenza di cui si vuole effettuare lo scambio
     * @param c2 Cella di destinazione con cui si vuole effettuare lo scambio
     */
    public static void scambiaCella(final Cella c1, final Cella c2) {
	c2.setPezzoCorrente(c1.getPezzoCorrente());
	c2.setOccupato(c1.isOccupato());
	c1.rimuoviPezzoCorrente();
    }

    /**
     * permette di simulare la mossa in cui un pezzo mangia un altro TODO:
     * migliorare javadoc
     * 
     * @param x
     * @param y
     */
    public final void mangiaPezzo(final int x, final int y) {
	getCella(x, y).rimuoviPezzoCorrente();

    }

    /**
     * Permette di avere in output il nome del pezzo. Evita errori legati a indici
     * errati e cella vuota.
     *
     * @param x coordinata delle colonne (valore compreso fra 0 e 7)
     * @param y coordinata delle righe (valore compreso fra 0 e 7)
     * @return Stringa indicante il nome del pezzo, se è presente nella cella, "Vuota" altrimenti
     */
    public static final String getNomePezzo(final int x, final int y) {
	if (isRangeValido(x, y) && getCella(x, y).isOccupato()) {
	    return getCella(x, y).getPezzoCorrente().getNome();
	}
    return "Vuota";
}
}
