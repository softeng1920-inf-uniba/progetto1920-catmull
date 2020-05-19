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

    private static Cella[][] scacchiera = new Cella[getNumeroColonne()][getNumeroRighe()];
    public static final int SETTIMA_TRAVERSA = 6; // La prima traversa avrà valore 0, l'ottava avrà valore 7
    public static final int NUMEROCOLONNE = 8;
    public static final int NUMERORIGHE = 8;

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

	int primaRiga = Cella.coordYinInt('1');
	int ottavaRiga = Cella.coordYinInt('8');

	int primaTraversa = Cella.coordXinInt('a');
	int secondaTraversa = Cella.coordXinInt('b');
	int terzaTraversa = Cella.coordXinInt('c');
	int quartaTraversa = Cella.coordXinInt('d');
	int quintaTraversa = Cella.coordXinInt('e');
	int sestaTraversa = Cella.coordXinInt('f');
	int settimaTraversa = Cella.coordXinInt('g');
	int ottavaTraversa = Cella.coordXinInt('h');

	// Inizializzazione della torre
	getCella(primaTraversa, primaRiga)
		.setPezzoCorrente(new Torre(Colore.bianco, getCella(primaTraversa, primaRiga)));
	getCella(ottavaTraversa, primaRiga)
		.setPezzoCorrente(new Torre(Colore.bianco, getCella(ottavaTraversa, primaRiga)));

	getCella(primaTraversa, ottavaRiga)
		.setPezzoCorrente(new Torre(Colore.nero, getCella(primaTraversa, ottavaRiga)));
	getCella(ottavaTraversa, ottavaRiga)
		.setPezzoCorrente(new Torre(Colore.nero, getCella(ottavaTraversa, ottavaRiga)));

	// Inizializzazione del cavallo
	getCella(secondaTraversa, primaRiga)
		.setPezzoCorrente(new Cavallo(Colore.bianco, getCella(secondaTraversa, primaRiga)));
	getCella(settimaTraversa, primaRiga)
		.setPezzoCorrente(new Cavallo(Colore.bianco, getCella(settimaTraversa, primaRiga)));

	getCella(secondaTraversa, ottavaRiga)
		.setPezzoCorrente(new Cavallo(Colore.nero, getCella(secondaTraversa, ottavaRiga)));
	getCella(settimaTraversa, ottavaRiga)
		.setPezzoCorrente(new Cavallo(Colore.nero, getCella(settimaTraversa, ottavaRiga)));

	// Inizializzazione dell'alfiere
	getCella(terzaTraversa, primaRiga)
		.setPezzoCorrente(new Alfiere(Colore.bianco, getCella(terzaTraversa, primaRiga)));
	getCella(sestaTraversa, primaRiga)
		.setPezzoCorrente(new Alfiere(Colore.bianco, getCella(sestaTraversa, primaRiga)));

	getCella(terzaTraversa, ottavaRiga)
		.setPezzoCorrente(new Alfiere(Colore.nero, getCella(terzaTraversa, ottavaRiga)));
	getCella(sestaTraversa, ottavaRiga)
		.setPezzoCorrente(new Alfiere(Colore.nero, getCella(sestaTraversa, ottavaRiga)));

	// Inizializzazione della regina
	getCella(quartaTraversa, primaRiga)
		.setPezzoCorrente(new Regina(Colore.bianco, getCella(quartaTraversa, primaRiga)));
	getCella(quartaTraversa, ottavaRiga)
		.setPezzoCorrente(new Regina(Colore.nero, getCella(quartaTraversa, ottavaRiga)));

	// Inizializzazione del re
	getCella(quintaTraversa, primaRiga)
		.setPezzoCorrente(new Re(Colore.bianco, getCella(quintaTraversa, primaRiga)));
	getCella(quintaTraversa, ottavaRiga)
		.setPezzoCorrente(new Re(Colore.nero, getCella(quintaTraversa, ottavaRiga)));

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
     * Permette di simulare la mossa in cui un pezzo mangia un altro
     * 
     * @param x Intero che rappresenta la coordinata x
     * @param y Intero che rappresenta la coordinata x
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
     * @return Stringa indicante il nome del pezzo, se è presente nella cella,
     *         "Vuota" altrimenti
     */
    public static final String getNomePezzo(final int x, final int y) {
	if (isRangeValido(x, y) && getCella(x, y).isOccupato()) {
	    return getCella(x, y).getPezzoCorrente().getNome();

	}
	return "Vuota";
    }
}
