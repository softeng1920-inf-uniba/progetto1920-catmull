package scacchiera.pedine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Classe di test per la classe Cavallo. I test sono realizzati in modo tale da
 * controllare il corretto funzionamento di tutti i movimenti del cavallo. 
 * Questo pezzo può muoversi potenzialmente in otto posizioni in quanto, ogni volta
 * che viene effettuata la mossa si compiono un movimento orizzontale e verticale.
 * Partendo dalla cella con coordinate x e y, le possibili celle finali possono avere le seguanti coordinate.
 * x+1 y+2 
 * x+1 y-2 
 * x-1 y+2 
 * x-1 y-2 
 * x+2 y+1 
 * x+2 y-1 
 * x-2 y+1 
 * x-2 y-1
 * @author antoniocurci
 *
 */

class CavalloTest {
	Pezzo pezzoCorrente;
	Cavallo c;
	Cella start;
	Cella end;

	@BeforeEach
	void setTests() {
		Scacchiera.nuovaScacchiera();
		Turno.newTurno();
		Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
    	Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
    	start = Scacchiera.getCella(3, 3);
    	c = (Cavallo)start.getPezzoCorrente();
		
	}
	
	@Test
	void testisMossaValidac7() {
		assertTrue(c.isMossaValida(start, Scacchiera.getCella(2, 1)));
		
	}
	
	@Test
	void testIsMossaValidae7() {
		assertTrue(c.isMossaValida(start, Scacchiera.getCella(4, 1)));
	}
	
	@Test
	void testIsMossaValidab6() {
		Scacchiera.getCella(1, 2).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(1, 2)));
	}
	
	@Test
	void testIsMossaValidab4() {
		Scacchiera.getCella(1, 4).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(1, 4)));
	}
	
	@Test
	void testIsMossaValidac3() {
		Scacchiera.getCella(2, 5).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(2, 5)));
	}
	
	@Test
	void testIsMossaValidae3() {
		Scacchiera.getCella(4, 5).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(4, 5)));
	}
	
	@Test
	void testIsMossaValidaf4() {
		Scacchiera.getCella(5, 4).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(5, 4)));
	}
	
	@Test
	void testIsMossaValidaf6() {
		Scacchiera.getCella(5, 2).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(5, 2)));
	}
	
	@Test
	void testIsMossaValidaEndOccupato() {
		Scacchiera.nuovaScacchiera();
		assertFalse(c.isMossaValida(Scacchiera.getCella(5, 5), Scacchiera.getCella(4, 1)));
		Scacchiera.getCella(5, 5).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(6, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        assertFalse(c.isMossaValida(Scacchiera.getCella(5, 5), Scacchiera.getCella(6, 3)));
	}
	
	@Test
	void testConvertiMossae7() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 e7", Cavallo.convertiMossa("Ce7"));
	}
	
	@Test
	void testConvertiMossac7() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 c7", Cavallo.convertiMossa("Cc7"));
	}
	
	@Test
	void testConvertiMossab6() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 b6", Cavallo.convertiMossa("Cb6"));
	}
	
	@Test
	void testConvertiMossab4() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 b4", Cavallo.convertiMossa("Cb4"));
	}
	
	@Test
	void testConvertiMossac3() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 c3", Cavallo.convertiMossa("Cc3"));
	}
	
	@Test
	void testConvertiMossae3() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 e3", Cavallo.convertiMossa("Ce3"));
	}
	
	@Test
	void testConvertiMossaf4() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 f4", Cavallo.convertiMossa("Cf4"));
	}
	
	@Test
	void testConvertiMossaf6() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 f6", Cavallo.convertiMossa("Cf6"));
	}
	
	/*
	 * Ho spostato il cavallo bianco in d5
	 * partendo da d5 posso catturare il pedone nero in e7 
	 */
	@Test
	void testConvertiMossaCattura() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(3, 3));
		assertEquals("d5 e7", Cavallo.convertiMossa("Cxe7"));
		
	}
	
	/*
	 * Ho spostato il cavallo bianco in d5
	 * partendo da d5 posso catturare il pedone nero in e7 
	 */
	@Test
	void testConvertiMossaFull() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(3, 3));
		assertEquals("a0 a0", Cavallo.convertiMossa("Ce7"));
		
	}
	
	/*
	 * Mossa di cattura in e4, ma la cella e4 è vuota e quindi non può essere effettuata
	 */
	@Test
	void testConvertiMossaCatturaFinaleVuota() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(3, 3));
		assertEquals("a0 a0", Cavallo.convertiMossa("Cxe4"));
		
	}
	
	/*
	 * Avendo ambiguità nelle celle d5 e f5 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza
	 */
	@Test
	void testConvertiMossaCatturaAmbiguitaTrav() { 
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(3, 3));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(5, 3));
		assertEquals("f5 e7", Cavallo.convertiMossa("Cfxe7"));
	}
	
	/*
	 * Avendo ambiguità nelle celle d5 e f5 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza. La cella in cui catturare è però vuota.
	 */
	@Test
	void testConvertiMossaCatturaAmbiguitaTravEmpty() { 
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(3, 3));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(5, 3));
		Scacchiera.getCella(4,  1).rimuoviPezzoCorrente();
		assertEquals("a0 a0", Cavallo.convertiMossa("Cfxe7"));
	}
	
	/*
	 * Avendo ambiguità nelle celle d5 e d3 c'è bisogno di specificare il cavallo
	 * da muovere indicando la traversa di partenza
	 */
	@Test
	void testConvertiMossaCatturaAmbiguitaColonne() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(3, 3));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(3, 5));
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 1), Scacchiera.getCella(5, 4));
		assertEquals("d5 f4", Cavallo.convertiMossa("C5xf4"));
	}
	
	/*
	 * Avendo ambiguità nelle celle d5 e d3 c'è bisogno di specificare il cavallo
	 * da muovere indicando la traversa di partenza. La cella in cui catturare è però vuota.
	 */
	@Test
	void testConvertiMossaCatturaAmbiguitaColonneEmpty() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(3, 3));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(3, 5));
		assertEquals("a0 a0", Cavallo.convertiMossa("C5xf4"));
	}
	
	/*
	 * Avendo ambiguità nelle celle b4 e b6 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza
	 */
	@Test
	void testConvertiMossaAmbiguitaTrav() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(1, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(1, 4));
		assertEquals("b4 d5", Cavallo.convertiMossa("C4d5"));
	}
	
	/*
	 * Avendo ambiguità nelle celle b6 e b2 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza
	 */
	@Test
	void testConvertiMossaAmbiguitaTrav2() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(1, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(1, 6));
		assertEquals("b2 c4", Cavallo.convertiMossa("C2c4"));
	}
	
	/*
	 * Avendo ambiguità nelle celle b6 e b2 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza
	 */
	@Test
	void testConvertiMossaAmbiguitaTrav3() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(1, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(1, 6));
		assertEquals("b6 a4", Cavallo.convertiMossa("C6a4"));
	}
	
	
	/*
	 * Avendo ambiguità nelle celle b4 e b6 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza. La cella in cui catturare è però piena.
	 */
	@Test
	void testConvertiMossaAmbiguitaTravFull() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(1, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(1, 4));
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 1), Scacchiera.getCella(3, 3));
		assertEquals("a0 a0", Cavallo.convertiMossa("C4d5"));
	}
	
	/*
	 * Avendo ambiguità nelle celle c4 e c6 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza.
	 */
	@Test
	void testConvertiMossaAmbiguitaTrav4() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(2, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(2, 4));
		assertEquals("c4 a5", Cavallo.convertiMossa("C4a5"));
	}
	
	/*
	 * Avendo ambiguità nelle celle d6 e b6 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza
	 */
	@Test
	void testConvertiMossaAmbiguitaColonna() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(1, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(3, 2));
		assertEquals("b6 c4", Cavallo.convertiMossa("Cbc4"));
	}

	/*
	 * Avendo ambiguità nelle celle b4 e b6 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza
	 */
	@Test
	void testConvertiMossaAmbiguitaColonnaL() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(1, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(3, 2));
		assertEquals("b6 d5", Cavallo.convertiMossa("Cbd5"));
	}
	
	/*
	 * Avendo ambiguità nelle celle b4 e f4 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza
	 */
	@Test
	void testConvertiMossaAmbiguitaColonna_I() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(1, 4));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(5, 4));
		assertEquals("b4 d5", Cavallo.convertiMossa("Cbd5"));
	}
	
	/*
	 * Avendo ambiguità nelle celle b4 e b6 c'è bisogno di specificare il cavallo
	 * da muovere indicando la colonna di partenza. La cella in cui catturare è però piena.
	 */
	@Test
	void testConvertiMossaAmbiguitaColonnaFull() {
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 7), Scacchiera.getCella(1, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 7), Scacchiera.getCella(3, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 1), Scacchiera.getCella(2, 4));
		assertEquals("a0 a0", Cavallo.convertiMossa("Cbc4"));
	}
}