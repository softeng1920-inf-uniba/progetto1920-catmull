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
 * e di righe. La classe scacchiera � una matrice NxM di Celle. La classe
 * Scacchiera e' di tipo ENTITY
 */
public final class Scacchiera {

    private static Scacchiera istance = null;
    private Cella[][] scacchiera;
    public static final int SETTIMA_TRAVERSA = 6; // La prima traversa avrà valore 0, l'ottava avrà
						  // valore 7
    public static final int NUMEROCOLONNE = 8;
    public static final int NUMERORIGHE = 8;

    /**
     * Avvalora e inizializza la matrice di celle, una per una
     */
    private Scacchiera() {
	scacchiera = new Cella[getNumeroColonne()][getNumeroRighe()];
	for (int i = 0; i < getNumeroColonne(); i++) {
	    for (int j = 0; j < getNumeroRighe(); j++) {
		scacchiera[i][j] = new Cella(i, j, null);
	    }
	}
    }

    /**
     * Costruttore statico per classe Singleton
     */
    public static Scacchiera getInstance() {
	if (istance == null) {
	    istance = new Scacchiera();
	}
	
	return istance;
    }

    /**
     * Restituisce il numero di colonne della scacchiera
     *
     * @return Valore intero indicante il numero di colonne
     */
    public int getNumeroColonne() {
	return NUMEROCOLONNE;
    }

    /**
     * Restituisce il numero di righe della scacchiera
     *
     * @return Valore intero indicante il numero di righe
     */
    public int getNumeroRighe() {
	return NUMERORIGHE;
    }

    /**
     * Date le coordinate, ritorna il riferimento di una cella della scacchiera.
     *
     * @param x coordinata delle colonne (valore compreso fra 0 e 7)
     * @param y coordinata delle righe (valore compreso fra 0 e 7)
     * @return Riferimento di tipo Cella della scacchiera in posizione (x, y)
     */
    public Cella getCella(final int x, final int y) {
	return scacchiera[x][y];
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
    public boolean isRangeValido(final int x, final int y) {
	return x < getNumeroRighe() && y < getNumeroColonne() && x >= 0 && y >= 0;
    }

    /**
     * Imposta la scacchiera allo stato iniziale, con i pezzi nelle posizioni
     * standard
     */
    public void inizializzaScacchiera() {

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

	// permette di svuotare tutte le celle
	svuotaScacchiera();

	// Inizializzazione della torre
	getCella(primaTraversa, primaRiga).setPezzoCorrente(new Torre(Colore.bianco));
	getCella(ottavaTraversa, primaRiga).setPezzoCorrente(new Torre(Colore.bianco));

	getCella(primaTraversa, ottavaRiga).setPezzoCorrente(new Torre(Colore.nero));
	getCella(ottavaTraversa, ottavaRiga).setPezzoCorrente(new Torre(Colore.nero));

	// Inizializzazione del cavallo
	getCella(secondaTraversa, primaRiga).setPezzoCorrente(new Cavallo(Colore.bianco));
	getCella(settimaTraversa, primaRiga).setPezzoCorrente(new Cavallo(Colore.bianco));

	getCella(secondaTraversa, ottavaRiga).setPezzoCorrente(new Cavallo(Colore.nero));
	getCella(settimaTraversa, ottavaRiga).setPezzoCorrente(new Cavallo(Colore.nero));

	// Inizializzazione dell'alfiere
	getCella(terzaTraversa, primaRiga).setPezzoCorrente(new Alfiere(Colore.bianco));
	getCella(sestaTraversa, primaRiga).setPezzoCorrente(new Alfiere(Colore.bianco));

	getCella(terzaTraversa, ottavaRiga).setPezzoCorrente(new Alfiere(Colore.nero));
	getCella(sestaTraversa, ottavaRiga).setPezzoCorrente(new Alfiere(Colore.nero));

	// Inizializzazione della regina
	getCella(quartaTraversa, primaRiga).setPezzoCorrente(new Regina(Colore.bianco));
	getCella(quartaTraversa, ottavaRiga).setPezzoCorrente(new Regina(Colore.nero));

	// Inizializzazione del re
	getCella(quintaTraversa, primaRiga).setPezzoCorrente(new Re(Colore.bianco));
	getCella(quintaTraversa, ottavaRiga).setPezzoCorrente(new Re(Colore.nero));

	// Inizializzazione dei pedoni
	for (int i = 0; i < getNumeroColonne(); i++) {
	    getCella(i, Cella.coordYinInt('7')).setPezzoCorrente(new Pedone(Colore.nero));
	    getCella(i, Cella.coordYinInt('2')).setPezzoCorrente(new Pedone(Colore.bianco));
	}
    }


    private void svuotaScacchiera() {
	for (int i = 0; i < getNumeroColonne(); i++) {
	    for (int j = 0; j < getNumeroRighe(); j++) {
		if (scacchiera[i][j].isOccupato()) {
		    scacchiera[i][j].rimuoviPezzoCorrente();
		}
	    }
	}
    }

    /**
     * simula il movimento di un pezzo nella scacchiera
     *
     * @param c1 Cella di partenza di cui si vuole effettuare lo scambio
     * @param c2 Cella di destinazione con cui si vuole effettuare lo scambio
     */
    public void scambiaCella(final Cella c1, final Cella c2) {
	c2.setPezzoCorrente(c1.getPezzoCorrente());
	c2.setOccupato(c1.isOccupato());
	c1.rimuoviPezzoCorrente();
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
    public String getNomePezzo(final int x, final int y) {
	if (isRangeValido(x, y) && getCella(x, y).isOccupato()) {
	    return getCella(x, y).getPezzoCorrente().getNome();

	}
	return "Vuota";
    }
}
