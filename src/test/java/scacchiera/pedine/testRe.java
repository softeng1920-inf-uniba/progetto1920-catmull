package scacchiera.pedine;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gioco.Colore;
import gioco.Menu;
import gioco.Turno;
import it.uniba.main.Controller;
import scacchiera.Cella;
import scacchiera.Scacchiera;

class ReTest {

    private static final int primaColonna = 0;
    private static final int terzaColonna = 2;
    private static final int quartaColonna = 3;
    private static final int sestaColonna = 5;
    private static final int settimaColonna = 6;
    private static final int ottavaColonna = 7;

    private static final int primaTraversa = 7;
    private static final int secondaTraversa = 6;
    private static final int sestaTraversa = 2;
    private static final int settimaTraversa = 1;
    private static final int ottavaTraversa = 0;

    Re reBianco = new Re(Colore.bianco);
    Re reNero = new Re(Colore.nero);

    // Cella cellaGenerica;
    Scacchiera s;

    // Re bianco

    static final int coordXinizialeReBianco = 4; // 5° colonna
    static final int coordYinizialeReBianco = 7; // 1° traversa
    static Cella posizioneInizialeReBianco;

    // Re nero

    static final int coordXinizialeReNero = 4; // 5° colonna
    static final int coordYinizialeReNero = 0; // 8° traversa

    static Cella posizioneInizialeReNero;

    // Torri bianche

    final static int coordXinizialeTorreSxBianco = 0; // 1° colonna
    final static int coordYinizialeTorreSxBianco = 7; // 1° traversa
    static Cella posizioneInizialeTorreSxBianco;


    final static int coordXinizialeTorreDxBianco = 7; // 8° colonna
    final static int coordYinizialeTorreDxBianco = 7; // 1° traversa
    static Cella posizioneInizialeTorreDxBianco;

    // Torri nere

    final static int coordXinizialeTorreDxNero = 7; // 8° colonna
    final static int coordYinizialeTorreDxNero = 0; // 8° traversa
    static Cella posizioneInizialeTorreDxNera;

    final static int coordXinizialeTorreSxNero = 0; // 1° colonna
    final static int coordYinizialeTorreSxNero = 0; // 8° traversa
    static Cella posizioneInizialeTorreSxNera;


    ArrayList<String> storicoMosse;

    @BeforeEach
    void setUp() throws Exception {
	Scacchiera.getInstance().inizializzaScacchiera();
	posizioneInizialeTorreDxBianco = Scacchiera.getInstance().getCella(coordXinizialeTorreDxBianco, coordYinizialeTorreDxBianco);
	posizioneInizialeTorreSxBianco = Scacchiera.getInstance().getCella(coordXinizialeTorreSxBianco, coordYinizialeTorreSxBianco);

	posizioneInizialeTorreDxNera = Scacchiera.getInstance().getCella(coordXinizialeTorreDxNero, coordYinizialeTorreDxNero);
	posizioneInizialeTorreSxNera  = Scacchiera.getInstance().getCella(coordXinizialeTorreSxNero, coordYinizialeTorreSxNero);

	posizioneInizialeReBianco 	= Scacchiera.getInstance().getCella(coordXinizialeReBianco, coordYinizialeReBianco);
	posizioneInizialeReNero 	= Scacchiera.getInstance().getCella(coordXinizialeReNero, coordYinizialeReNero);
	Turno.getInstance().inizializzaTurno();
	storicoMosse = new ArrayList<String>();

    }

    /**
     * Scacchiera originale
     *
     *	8 |tNs|cNs|aNs|dN | rN|aNd|cNd|tNd|
     *	7 | pN| pN| pN| pN| pN| pN| pN| pN|
     *	6 |   |   |   |   |   |   |   |   |
     *	5 |   |   |   |   |   |   |   |   |
     *	4 |   |   |   |   |   |   |   |   |
     *	3 |   |   |   |   |   |   |   |   |
     *	2 | pB| pB| pB| pB| pB| pB| pB| pB|
     *	1 |tBs|cBs|aBs|dB | rB|aBd|cBd|tBd|
     *	    a   b   c   d   e   f   g   h
     */

    @Test
    final void testIsReBiancoInCellaInizialeDopoInizializzazioneScacchiera() {
	// Controllo se dopo l'inizializzazione della scacchiera, il re bianco è nella sua cella iniziale.
	assertTrue(posizioneInizialeReBianco.getPezzoCorrente() instanceof Re);
	assertEquals(posizioneInizialeReBianco.getPezzoCorrente().getColore(), Colore.bianco);
	assertEquals(posizioneInizialeReBianco.getPezzoCorrente().getNome(), "Re");
    }

    @Test
    final void testIsReNeroInCellaInizialeDopoInizializzazioneScacchiera() {
	assertTrue(posizioneInizialeReNero.getPezzoCorrente() instanceof Re);
	assertEquals(posizioneInizialeReNero.getPezzoCorrente().getColore(), Colore.nero);
	assertEquals(posizioneInizialeReNero.getPezzoCorrente().getNome(), "Re");
    }

    /* ========================================== */
    // 		Test isMossaValida		  //
    /* ========================================== */

    // Mossa illegale su autocattura
    @Test
    final void testIsMossaIllegaleAutocattura() {
	Cella posizioneAttualeRe = Re.findRe();
	assertFalse(reBianco.isMossaValida(posizioneAttualeRe, posizioneAttualeRe));
    }

    // Controllo se può mangiare in avanti
    @Test
    final void testIsAvanzataValidaAvanti() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB| pB|pN | pB| pB| pB|
   	 *	1 |tBs|aBs|cBs|dB |rB |cBd|aBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale rimuovo il pedone bianco in e2, rimpiazzandolo con uno nero
	 */

	Cella cellaReBianco = Scacchiera.getInstance().getCella(coordXinizialeReBianco, coordYinizialeReBianco);

	Cella cellaPedoneNero = Scacchiera.getInstance().getCella(coordXinizialeReBianco, coordYinizialeReBianco - 1);
	cellaPedoneNero.rimuoviPezzoCorrente();
	cellaPedoneNero.aggiungiPezzo(new Pedone(Colore.nero));
	assertTrue(((Re) cellaReBianco.getPezzoCorrente()).isMossaValida(cellaReBianco, cellaPedoneNero));


    }
    // Controllo se può mangiare indietro
    @Test
    final void testIsCatturaValidaIndietro() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB| pB|rB | pB| pB| pB|
   	 *	1 |tBs|aBs|cBs|dB |pN |cBd|aBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale:
   	 *  - Rimuovo il pedone bianco da e2
   	 *  - Rimuovo il re da e1
   	 *  - Aggiungo il re bianco in e2
   	 *  - Aggiungo un pedone nero in e1
   	 *
	 */


	Cella cellaReBianco = Scacchiera.getInstance().getCella(coordXinizialeReBianco, coordYinizialeReBianco - 1);
	cellaReBianco.rimuoviPezzoCorrente(); // In precedenza c'era un pedone
	cellaReBianco.aggiungiPezzo(new Re(Colore.bianco));

	Cella cellaPedoneNero = Scacchiera.getInstance().getCella(coordXinizialeReBianco, coordYinizialeReBianco);
	cellaPedoneNero.rimuoviPezzoCorrente();
	cellaPedoneNero.aggiungiPezzo(new Pedone(Colore.nero));
	Re rB = (Re) cellaReBianco.getPezzoCorrente();

	assertTrue(rB.isMossaValida(cellaReBianco, cellaPedoneNero));

    }

    // Controllo se può mangiare a destra
    @Test
    final void testIsCatturaValidaDestra() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB| pB|pB | pB| pB| pB|
   	 *	1 |tBs|aBs|cBs| dB|rB |pN |aBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale:
   	 *  - Rimuovo il pedone bianco da f1
   	 *  - Aggiungo un pedone nero in f1
   	 *
	 */


	Cella cellaPedoneNero = Scacchiera.getInstance().getCella(coordXinizialeReNero + 1, coordYinizialeReBianco);
	cellaPedoneNero.rimuoviPezzoCorrente();
	cellaPedoneNero.aggiungiPezzo(new Pedone(Colore.nero));
	Re rB = (Re) Scacchiera.getInstance().getCella(coordXinizialeReNero, coordYinizialeReBianco).getPezzoCorrente();

	assertTrue(rB.isMossaValida(posizioneInizialeReBianco, cellaPedoneNero));

    }

    // Controllo se può mangiare a sinistra
    @Test
    final void testIsCatturaValidaSx() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB| pB|pB | pB| pB| pB|
   	 *	1 |tBs|aBs|cBs| pN|rB |cBd|aBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale:
   	 *  - Rimuovo il pedone bianco da d1
   	 *  - Aggiungo un pedone nero in d1
   	 *
	 */




	Cella cellaPedoneNero = Scacchiera.getInstance().getCella(coordXinizialeReNero - 1, coordYinizialeReBianco);
	cellaPedoneNero.rimuoviPezzoCorrente();
	cellaPedoneNero.aggiungiPezzo(new Pedone(Colore.nero));
	Re rB = (Re) Scacchiera.getInstance().getCella(coordXinizialeReNero, coordYinizialeReBianco).getPezzoCorrente();

	assertTrue(rB.isMossaValida(posizioneInizialeReBianco, cellaPedoneNero));

    }

    // Controllo se può mangiare nella diagonale in alto a dx
    @Test
    final void testIsCatturaValidaNE() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB| pB|pB | pN| pB| pB|
   	 *	1 |tBs|aBs|cBs| dB|rB |cBd|aBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale:
   	 *  - Rimuovo il pedone bianco da f2
   	 *  - Aggiungo un pedone nero in f2
   	 *
	 */

	Cella cellaPedoneNero = Scacchiera.getInstance().getCella(coordXinizialeReNero + 1, coordYinizialeReBianco - 1);
	cellaPedoneNero.rimuoviPezzoCorrente();
	cellaPedoneNero.aggiungiPezzo(new Pedone(Colore.nero));
	Re rB = (Re) Scacchiera.getInstance().getCella(coordXinizialeReNero, coordYinizialeReBianco).getPezzoCorrente();

	assertTrue(rB.isMossaValida(posizioneInizialeReBianco, cellaPedoneNero));

       }

    // Controllo se può mangiare nella diagonale in alto a sx
    @Test
    final void testIsCatturaValidaNW() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB| pN|pB | pB| pB| pB|
   	 *	1 |tBs|aBs|cBs| dB|rB |cBd|aBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale:
   	 *  - Rimuovo il pedone bianco da d2
   	 *  - Aggiungo un pedone nero in d2
   	 *
	 */

	Cella cellaPedoneNero = Scacchiera.getInstance().getCella(coordXinizialeReNero - 1, coordYinizialeReBianco - 1);
	cellaPedoneNero.rimuoviPezzoCorrente();
	cellaPedoneNero.aggiungiPezzo(new Pedone(Colore.nero));
	Re rB = (Re) Scacchiera.getInstance().getCella(coordXinizialeReNero, coordYinizialeReBianco).getPezzoCorrente();

	assertTrue(rB.isMossaValida(posizioneInizialeReBianco, cellaPedoneNero));

       }

    // Controllo se può mangiare nella diagonale in basso a dx
    @Test
    final void testIsCatturaValidaSE() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB| rB| pB| pB| pB| pB|
   	 *	1 |tBs|aBs|cBs| dB| pN|cBd|aBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale:
   	 *  - Rimuovo il pedone bianco da d2
   	 *  - Aggiungo il re bianco in d2
   	 *  - Rimuovo il pezzo in e1
   	 *  - Aggiungo un pedone avversario
	 */


	Cella cellaReBianco = Scacchiera.getInstance().getCella(coordXinizialeReNero - 1, coordYinizialeReBianco - 1);
	cellaReBianco.rimuoviPezzoCorrente();
	cellaReBianco.aggiungiPezzo(new Re(Colore.bianco));

	Cella cellaPedoneNero = Scacchiera.getInstance().getCella(coordXinizialeReNero, coordYinizialeReBianco);
	cellaPedoneNero.rimuoviPezzoCorrente();
	cellaPedoneNero.aggiungiPezzo(new Pedone(Colore.nero));
	Re rB = (Re) Scacchiera.getInstance().getCella(coordXinizialeReNero - 1, coordYinizialeReBianco - 1).getPezzoCorrente();

	assertTrue(rB.isMossaValida(cellaReBianco, cellaPedoneNero));

       }
    // Controllo se può mangiare nella diagonale in basso a sx
    @Test
    final void testIsCatturaValidaSW() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB|   | rB| pB| pB| pB|
   	 *	1 |tBs|aBs|cBs| pN|   |cBd|aBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale:
   	 *  - Rimuovo il pedone bianco da d2
   	 *  - Aggiungo il re bianco in d2
   	 *  - Rimuovo il pezzo in e1
   	 *  - Aggiungo un pedone avversario
	 */


   	Pezzo rB = new Re(Colore.bianco);
   	Cella cellaReBianco = Scacchiera.getInstance().getCella(coordXinizialeReNero, coordYinizialeReBianco - 1);
   	cellaReBianco.rimuoviPezzoCorrente();
   	cellaReBianco.aggiungiPezzo(rB);

   	Pezzo pN = new Pedone(Colore.nero);
   	Cella cellaPedoneNero = Scacchiera.getInstance().getCella(coordXinizialeReNero - 1, coordYinizialeReBianco);
   	cellaPedoneNero.rimuoviPezzoCorrente();
   	cellaPedoneNero.aggiungiPezzo(pN);
   	assertTrue(rB.isMossaValida(cellaReBianco, cellaPedoneNero));

       }

    @Test
    final void testIsMossaValidaAvanzataReSottoScacco() {

	// Sposto il pedone bianco in e4, per lasciare le case libere
	Controller.getInstance().mossaScacchi("e4");

	Cella cella_c8 = Scacchiera.getInstance().getCella(terzaColonna, ottavaTraversa);
	Cella cella_a6 = Scacchiera.getInstance().getCella(primaColonna, sestaTraversa);
	Scacchiera.getInstance().scambiaCella(cella_c8, cella_a6);

   	assertFalse(((Re) posizioneInizialeReBianco.getPezzoCorrente())
   		.isMossaValida(posizioneInizialeReBianco, Scacchiera.getInstance().getCella(coordXinizialeReBianco, coordYinizialeReBianco - 1)));

    }

    /* ========================================== */
    // 		Test isArroccoValido 		  //
    /* ========================================== */

    // La funzione isArroccoValido parte dal presupposto di avere delle mosse
    // corrette in input,
    // ovvero le 4 mosse istituite dalle regole del gioco.
    @Test
    final void testIsArroccoLungoValidoReBiancoScacchieraInizializzata() {
   	// Caso di test base, in cui non è stata ancora effettuata alcuna mossa,
	// la scacchiera è allo stato iniziale

	final int secondaColonna = 1;
	final int primaTraversa = 7;
	Cella cellaCorrettaDestinazioneReBiancoArroccoLungo = Scacchiera.getInstance().getCella(secondaColonna, primaTraversa);

	final int terzaColonna = 2;
	Cella cellaCorrettaDestinazioneTorreBiancoArroccoLungo = Scacchiera.getInstance().getCella(terzaColonna, primaTraversa);
	assertFalse(
		( new Re(Colore.bianco) ).isArroccoValido(posizioneInizialeReBianco,
		cellaCorrettaDestinazioneReBiancoArroccoLungo,
		posizioneInizialeTorreSxBianco,
		cellaCorrettaDestinazioneTorreBiancoArroccoLungo, storicoMosse, Menu.ARROCCO_LUNGO));

    }

    @Test
    final void testIsArroccoCortoValidoReBiancoScacchieraInizializzata() {

	final int settimaColonna = 6;
	final int primaTraversa = 7;
	Cella cellaCorrettaDestinazioneReBiancoArroccoCorto = Scacchiera.getInstance().getCella(settimaColonna, primaTraversa);

	final int sestaColonna = 5;
	Cella cellaCorrettaDestinazioneTorreBiancoArroccoCorto = Scacchiera.getInstance().getCella(sestaColonna, primaTraversa);
	assertFalse(((Re) (posizioneInizialeReBianco.getPezzoCorrente())).isArroccoValido(posizioneInizialeReBianco,
		cellaCorrettaDestinazioneReBiancoArroccoCorto,
		posizioneInizialeTorreDxBianco,
		cellaCorrettaDestinazioneTorreBiancoArroccoCorto, storicoMosse, Menu.ARROCCO_CORTO));

    }

    @Test
    final void testIsArroccoCortoValidoPrimaMossaEffettuata() {

	//Test con re mosso
	// Supponendo che le altre condizioni siano valide (re attraversa case non sotto scacco),
	// Non ci sono pezzi tra la casa di arrivo e quella di destinazione

	/**
	 * 	Scacchiera originale
	 *
	 *	8 |tNs|cNs|aNs|dN | rN|aNd|cNd|tNd|
	 *	7 | pN| pN| pN|   |   | pN| pN| pN|
	 *	6 |   |   |   | pN| pN|   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |pB |   |   |   |
	 *	3 |   |   |   |   |   |   |   |   |
	 *	2 | pB| pB| pB| pB|   | pB| pB| pB|
	 *	1 |tBs|cBs|aBs|dB | rB|   |   |tBd|
	 *	    a   b   c   d   e   f   g   h
	 */


	Controller.getInstance().mossaScacchi("e4");
	storicoMosse.add("e2 e4");

	storicoMosse.add("a0 a0"); // Aggiungo una mossa fittizia fatta dal nero per non alterare l'algoritmo

	Controller.getInstance().mossaScacchi("Re2");

	storicoMosse.add("e1 e2");
	storicoMosse.add("a0 a0"); // Aggiungo una mossa fittizia fatta dal nero per non alterare l'algoritmo
	Controller.getInstance().mossaScacchi("Re1");
	storicoMosse.add("e2 e1");
	storicoMosse.add("a0 a0");


	// Rimuovo l'alfiere in f1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco + 1, coordYinizialeReBianco).rimuoviPezzoCorrente();

	// Rimuovo il cavallo in g1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco + 2, coordYinizialeReBianco).rimuoviPezzoCorrente();

	Cella cellaCorrettaDestinazioneReBiancoArroccoCorto = Scacchiera.getInstance().getCella(settimaColonna, primaTraversa);
	Cella cellaCorrettaDestinazioneTorreBiancaArroccoCorto = Scacchiera.getInstance().getCella(sestaColonna, primaTraversa);
	assertFalse(
		( (Re) posizioneInizialeReBianco.getPezzoCorrente() ).isArroccoValido(
			posizioneInizialeReBianco,
			cellaCorrettaDestinazioneReBiancoArroccoCorto,
			posizioneInizialeTorreDxBianco,
			cellaCorrettaDestinazioneTorreBiancaArroccoCorto,
			storicoMosse,
			Menu.ARROCCO_CORTO)
		);

    }


    @Test
    final void testIsArroccoCortoValidoPrimaMossaEffettuataNero() {

	//Test con re mosso
	// Supponendo che le altre condizioni siano valide (re attraversa case non sotto scacco),
	// Non ci sono pezzi tra la casa di arrivo e quella di destinazione

	/**
	 *
	 *	8 |tNs|cNs|aNs|dN | rN|   |   |tNd|
	 *	7 | pN| pN| pN| pN| pN| pN| pN| pN|
	 *	6 |   |   |   |   |   |   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |   |   |   |   |
	 *	3 |   |   |   |   |   |   |   |   |
	 *	2 | pB| pB| pB| pB|   | pB| pB| pB|
	 *	1 |tBs|cBs|aBs|dB | rB|   |   |tBd|
	 *	    a   b   c   d   e   f   g   h
	 */

	// Simulo che gli utenti abbiano effettuato delle mosse,
	// inserendo nella lista delle mosse quelle effettuate sia da bianco che da nero

	storicoMosse.add("e2 e4"); // Bianco
	storicoMosse.add("e8 e7"); // Simulo che il re nero si sia mosso
	storicoMosse.add("d2 d3");
	storicoMosse.add("e7 e8"); // Simulo che il re nero si sia mosso

	// Rimuovo l'alfiere in f8
	Scacchiera.getInstance().getCella(coordXinizialeReNero + 1, coordYinizialeReNero).rimuoviPezzoCorrente();

	// Rimuovo il cavallo in g8
	Scacchiera.getInstance().getCella(coordXinizialeReNero + 2, coordYinizialeReNero).rimuoviPezzoCorrente();

	Cella cellaCorrettaDestinazioneReNeroArroccoCorto = Scacchiera.getInstance().getCella(settimaColonna, ottavaTraversa);
	Cella cellaCorrettaDestinazioneTorreNeraDxArroccoCorto = Scacchiera.getInstance().getCella(sestaColonna, ottavaTraversa);

	Turno.getInstance().cambioTurno(); // Imposto il turno al giocatore nero
	assertFalse(
		( (Re) posizioneInizialeReNero.getPezzoCorrente() ).isArroccoValido(
			posizioneInizialeReNero,
			cellaCorrettaDestinazioneReNeroArroccoCorto,
			posizioneInizialeTorreDxNera,
			cellaCorrettaDestinazioneTorreNeraDxArroccoCorto,
			storicoMosse,
			Menu.ARROCCO_CORTO)
		);

    }

    @Test
    final void testIsArroccoCortoValidoCaseAttraversaReSottoScacco() {
	//Test con re sotto scacco dall'alfiere nero
	// Supponendo che le altre condizioni siano valide (re attraversa case non sotto scacco),
	// Non ci sono pezzi tra la casa di arrivo e quella di destinazione

	/**
	 * 	Scacchiera originale
	 *
	 *	8 |tNs|cNs|   |dN | rN|aNd|cNd|tNd|
	 *	7 | pN| pN| pN|pN | pN| pN| pN| pN|
	 *	6 |aNs|   |   |   |   |   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |pB |   |   |   |
	 *	3 |   |   |   |   |   |   |   |   |
	 *	2 | pB| pB| pB| pB|   | pB| pB| pB|
	 *	1 |tBs|cBs|aBs|dB | rB|   |   |tBd|
	 *	    a   b   c   d   e   f   g   h
	 */

	// Sposto il pedone bianco in e4, per lasciare le case libere
	Controller.getInstance().mossaScacchi("e4");
	storicoMosse.add("e2 e4");

	Cella cella_c8 = Scacchiera.getInstance().getCella(terzaColonna, ottavaTraversa);
	Cella cella_a6 = Scacchiera.getInstance().getCella(primaColonna, sestaTraversa);
	Scacchiera.getInstance().scambiaCella(cella_c8, cella_a6);

	// Rimuovo l'alfiere in f1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco + 1, coordYinizialeReBianco).rimuoviPezzoCorrente();

	// Rimuovo il cavallo in g1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco + 2, coordYinizialeReBianco).rimuoviPezzoCorrente();

	Cella cellaCorrettaDestinazioneReBiancoArroccoCorto = Scacchiera.getInstance().getCella(settimaColonna, primaTraversa);
	Cella cellaCorrettaDestinazioneTorreBiancaArroccoCorto = Scacchiera.getInstance().getCella(sestaColonna, primaTraversa);
	// L'arrocco corto non sarà possibile effettuarlo poichè la casa f1 è sotto tiro dall'alfiere
	assertFalse(((Re) posizioneInizialeReBianco.getPezzoCorrente()).isArroccoValido(posizioneInizialeReBianco,
		cellaCorrettaDestinazioneReBiancoArroccoCorto, posizioneInizialeTorreDxNera,
		cellaCorrettaDestinazioneTorreBiancaArroccoCorto, storicoMosse, Menu.ARROCCO_CORTO));

    }

    // Test con re sotto scacco
    @Test
    final void testIsArroccoCortoValidoReSottoScacco() {

	/**
	 *
	 *
	 *	8 |tNs|cNs|aNs|dN | rN|aNd|cNd|tNd|
	 *	7 | pN| pN| pN| pB| pN| pN| pN| pN|
	 *	6 |   |   |   |   |   |   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |   |   |   |   |
	 *	3 |   |   |   |   |   |   |   |   |
	 *	2 | pB| pB| pB| pN|pB | pB| pB| pB|
	 *	1 |tBs|cBs|aBs|dB | rB|   |   |tBd|
	 *	    a   b   c   d   e   f   g   h
	 */

	Cella cella_temp = new Cella(coordXinizialeReNero - 1, coordYinizialeReNero + 1,
		Scacchiera.getInstance().getCella(coordXinizialeReNero - 1, coordYinizialeReNero + 1).getPezzoCorrente());

	Cella cella_d2 = Scacchiera.getInstance().getCella(coordXinizialeReBianco - 1, coordYinizialeReBianco - 1);
	Cella cella_d7 = Scacchiera.getInstance().getCella(coordXinizialeReNero - 1, coordYinizialeReNero + 1);

	cella_d7.rimuoviPezzoCorrente();
	cella_d7.aggiungiPezzo(cella_d2.getPezzoCorrente());

	cella_d2.rimuoviPezzoCorrente();
	cella_d2.aggiungiPezzo(cella_temp.getPezzoCorrente());

   	// Rimuovo l'alfiere in f1
   	Scacchiera.getInstance().getCella(coordXinizialeReBianco + 1, coordYinizialeReBianco).rimuoviPezzoCorrente();

   	// Rimuovo il cavallo in g1
   	Scacchiera.getInstance().getCella(coordXinizialeReBianco + 2, coordYinizialeReBianco).rimuoviPezzoCorrente();

   	Cella cellaCorrettaDestinazioneReBiancoArroccoCorto = Scacchiera.getInstance().getCella(settimaColonna, primaTraversa);
	Cella cellaCorrettaDestinazioneTorreBiancaArroccoCorto = Scacchiera.getInstance().getCella(sestaColonna, primaTraversa);
	// L'arrocco corto non sarà possibile effettuarlo poichè la casa f1 è sotto tiro dall'alfiere
	assertFalse(((Re) posizioneInizialeReBianco.getPezzoCorrente()).isArroccoValido(posizioneInizialeReBianco,
		cellaCorrettaDestinazioneReBiancoArroccoCorto, posizioneInizialeTorreDxBianco,
		cellaCorrettaDestinazioneTorreBiancaArroccoCorto, storicoMosse, Menu.ARROCCO_CORTO));

    }

    // Test con arrocco possibile
    @Test
    final void testIsArroccoCortoValido() {

	/**
	 *	8 |tNs|cNs|aNs|dN | rN|aNd|cNd|tNd|
	 *	7 | pN| pN| pN| pN| pN| pN| pN| pN|
	 *	6 |   |   |   |   |   |   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |   |   |   |   |
	 *	3 |   |   |   |   |   |   |   |   |
	 *	2 | pB| pB| pB| pB|pB | pB| pB| pB|
	 *	1 |tBs|cBs|aBs|dB | rB|   |   |tBd|
	 *	    a   b   c   d   e   f   g   h
	 */


	// Rimuovo l'alfiere in f1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco + 1, coordYinizialeReBianco).rimuoviPezzoCorrente();

	// Rimuovo l'il cavallo in g1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco + 2, coordYinizialeReBianco).rimuoviPezzoCorrente();

	Cella cellaCorrettaDestinazioneReBiancoArroccoCorto = Scacchiera.getInstance().getCella(settimaColonna, primaTraversa);
	Cella cellaCorrettaDestinazioneTorreBiancaArroccoCorto = Scacchiera.getInstance().getCella(sestaColonna, primaTraversa);

	assertTrue(((Re) posizioneInizialeReBianco.getPezzoCorrente()).isArroccoValido(posizioneInizialeReBianco,
		cellaCorrettaDestinazioneReBiancoArroccoCorto, posizioneInizialeTorreDxBianco,
		cellaCorrettaDestinazioneTorreBiancaArroccoCorto, storicoMosse, Menu.ARROCCO_CORTO));

    }

    // Test con arrocco possibile
    @Test
    final void testIsArroccoLungoValido() {

	/**
	 *	8 |tNs|cNs|aNs|dN | rN|aNd|cNd|tNd|
	 *	7 | pN| pN| pN| pN| pN| pN| pN| pN|
	 *	6 |   |   |   |   |   |   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |   |   |   |   |
	 *	3 |   |   |   |   |   |   |   |   |
	 *	2 | pB| pB| pB| pB|pB | pB| pB| pB|
	 *	1 |tBs|   |   |   | rB|aBd|cBd|tBd|
	 *	    a   b   c   d   e   f   g   h
	 */


	// Rimuovo la donna in d1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco - 1, coordYinizialeReBianco).rimuoviPezzoCorrente();

	// Rimuovo l'alfiere in c1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco - 2, coordYinizialeReBianco).rimuoviPezzoCorrente();

	// Rimuovo il cavallo in b1
	Scacchiera.getInstance().getCella(coordXinizialeReBianco - 3, coordYinizialeReBianco).rimuoviPezzoCorrente();

	Cella cellaCorrettaDestinazioneReBiancoArroccoLungo = Scacchiera.getInstance().getCella(terzaColonna, primaTraversa);
	Cella cellaCorrettaDestinazioneTorreBiancaArroccoLungo = Scacchiera.getInstance().getCella(quartaColonna, primaTraversa);

	assertTrue(((Re) posizioneInizialeReBianco.getPezzoCorrente()).isArroccoValido(posizioneInizialeReBianco,
		cellaCorrettaDestinazioneReBiancoArroccoLungo, posizioneInizialeTorreSxBianco,
		cellaCorrettaDestinazioneTorreBiancaArroccoLungo, storicoMosse, Menu.ARROCCO_LUNGO));

    }

    /* ========================================== */
    // 		Test getCoordinateArrocco	  //
    /* ========================================== */

    @Test
    final void testGetCoordinateArroccoCortoBianco() {
	assertEquals(Re.getCoordinateArrocco(Menu.ARROCCO_CORTO, Colore.bianco), "e1 g1");
    }

    @Test
    final void testGetCoordinateArroccoLungoBianco() {
	assertEquals(Re.getCoordinateArrocco(Menu.ARROCCO_LUNGO, Colore.bianco), "e1 c1");
    }

    @Test
    final void testGetCoordinateArroccoCortoNero() {
	assertEquals(Re.getCoordinateArrocco(Menu.ARROCCO_CORTO, Colore.nero), "e8 g8");
    }

    @Test
    final void testGetCoordinateArroccoLungoNero() {
	assertEquals(Re.getCoordinateArrocco(Menu.ARROCCO_LUNGO, Colore.nero), "e8 c8");
    }

    /* ========================================== */
    // 		Test convertiMossa		  //
    /* ========================================== */

    @Test
    final void testConvertiMossaAvanzataBiancoStessaCasaRe() {
	assertEquals(Re.convertiMossa("Re1"), "a0 a0"); // Stessa mossa del re
    }

    @Test
    final void testConvertiMossaCatturaBiancoStessaCasaRe() {
	assertEquals(Re.convertiMossa("Rxe1"), "e1 e1");
    }

    @Test
    final void testConvertiMossaAvanzataBiancoCasaOccupataPedoneStessoColore() {
	assertEquals(Re.convertiMossa("Re2"), "a0 a0"); // Avanzata su casa occupata da pedone stesso colore
    }

    @Test
    final void testConvertiMossaCatturaCasaOccupataPedoneStessoColore() {
	assertEquals(Re.convertiMossa("Rxe2"), "e1 e2"); // Cattura su casa occupata da pedone stesso colore
    }

    @Test
    final void testConvertiMossaAvanzataBiancoCasaLibera() {

	Scacchiera.getInstance().getCella(ottavaColonna, secondaTraversa).rimuoviPezzoCorrente();

	Cella cella_h1 = Scacchiera.getInstance().getCella(ottavaColonna, primaTraversa);
   	Scacchiera.getInstance().scambiaCella(posizioneInizialeReBianco, cella_h1);
   	//Turno.getInstance().cambioTurno();
   	//if(Turno.getInstance().getGiocatoreInTurno().getColore() != Colore.bianco)
	assertEquals(Re.convertiMossa("Rh2"), "h1 h2");
    }

    @Test
    final void testConvertiMossaCatturaCasaVuota() {
	//Rimuovo il pedone in e2 per far avanzare correttamente il re
	Cella cella_e2 = Scacchiera.getInstance().getCella(coordXinizialeReNero, coordYinizialeReBianco - 1);
	cella_e2.rimuoviPezzoCorrente();
	assertEquals(Re.convertiMossa("Rxe2"), "a0 a0"); // Cattura su casa vuota
    }



    /* ========================================== */
    // 		Test isReSottoScacco		  //
    /* ========================================== */


    @Test
    final void testIsReSottoScaccoScacchieraOriginale() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	8 |tNs|cNs|aNs|dN | rN|aNd|cNd|tNd|
	 *	7 | pN| pN| pN| pN| pN| pN| pN| pN|
	 *	6 |   |   |   |   |   |   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |   |   |   |   |
   	 *	3 |   |   |   |   |   |   |   |   |
   	 *	2 | pB| pB| pB| pB|   | pB| pB| pB|
   	 *	1 |tBs|cBs|aBs|dB | rB|aBd|cBd|tBd|
   	 *  	    a   b   c   d   e   f   g   h
   	 *
   	 *  Rispetto all'originale ho rimosso solo il pedone bianco in e2
	 */

   	Cella cella_e2 = Scacchiera.getInstance().getCella(coordXinizialeReBianco, coordYinizialeReBianco - 1);
   	cella_e2.rimuoviPezzoCorrente();

   	// Tento di far avanzare il re in e2. In quella casa, il re non è sotto scacco
   	assertFalse(((Re) posizioneInizialeReBianco.getPezzoCorrente()).isReSottoScacco(cella_e2));

    }

    @Test
    final void testIsReSottoScaccoAlfiereMinaccia() {
	/*
	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
	 *
	 *	8 |tNs|cNs|   |dN | rN|aNd|cNd|tNd|
	 *	7 | pN| pN| pN| pN| pN| pN| pN| pN|
	 *	6 |aNs|   |   |   |   |   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |   |   |   |   |
	 *	3 |   |   |   |   |   |   |   |   |
	 *	2 | pB| pB| pB| pB|   | pB| pB| pB|
	 *	1 |tBs|cBs|aBs|dB | rB|aBd|cBd|tBd|
	 *  	    a   b   c   d   e   f   g   h
	 *
	 *  Rispetto all'originale ho rimosso solo il pedone bianco in e2
	 */

	Cella cella_c8 = Scacchiera.getInstance().getCella(terzaColonna, ottavaTraversa);
	Cella cella_a6 = Scacchiera.getInstance().getCella(primaColonna, sestaTraversa);
	Scacchiera.getInstance().scambiaCella(cella_c8, cella_a6);

	Cella cella_e2 = Scacchiera.getInstance().getCella(coordXinizialeReBianco, coordYinizialeReBianco - 1);
	cella_e2.rimuoviPezzoCorrente();

	// Tento di far avanzare il re in e2. In quella casa, il re è sotto scacco, poichè minacciato dall'alfiere in a6
	assertTrue(((Re) posizioneInizialeReBianco.getPezzoCorrente()).isReSottoScacco(cella_e2));
    }

    /* ========================================== */
    // 			Test findRe		  //
    /* ========================================== */

    @Test
    final void testFindReLimiteScacchiera() {
	/*
   	 *  Nel seguente caso di test imposto la scacchiera come nella seguente figura.
   	 *
	 *
	 *	8 |tNs|cNs|   |dN | rN|aNd|cNd|tNd|
	 *	7 | pN| pN| pN| pN| pN| pN| pN| pN|
	 *	6 |aNs|   |   |   |   |   |   |   |
	 *	5 |   |   |   |   |   |   |   |   |
	 *	4 |   |   |   |   |   |   |   |   |
	 *	3 |   |   |   |   |   |   |   |   |
	 *	2 | pB| pB| pB| pB| pB| pB| pB| pB|
	 *	1 |tBs|cBs|aBs|dB |tBd|aBd|cBd| rB|
	 *  	    a   b   c   d   e   f   g   h
	 *
   	 *  Rispetto all'originale scambio solo la cella iniziale del re con h1
   	 */

   	Cella cella_h1 = Scacchiera.getInstance().getCella(ottavaColonna, primaTraversa);
   	Scacchiera.getInstance().scambiaCella(posizioneInizialeReBianco, cella_h1);
   	assertEquals(cella_h1, Re.findRe());

    }
}
