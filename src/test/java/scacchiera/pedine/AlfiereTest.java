package scacchiera.pedine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

class AlfiereTest {
	Alfiere a;
	Cella start;

	@BeforeEach
	void setTests() {
		Scacchiera.getInstance().inizializzaScacchiera();;
		Turno.getInstance().inizializzaTurno();

	}

	void Inizio() {
		Turno.getInstance().cambioTurno();
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(5, 0), Scacchiera.getInstance().getCella(2, 3));
		start = Scacchiera.getInstance().getCella(2, 3);
		a = (Alfiere) start.getPezzoCorrente();
	}

	void Inizio2() {
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(5, 7), Scacchiera.getInstance().getCella(2, 4));
		start = Scacchiera.getInstance().getCella(2, 4);
		a = (Alfiere) start.getPezzoCorrente();
	}

	void Inizio3() {
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(2, 7), Scacchiera.getInstance().getCella(5, 4));
		start = Scacchiera.getInstance().getCella(5, 4);
		a = (Alfiere) start.getPezzoCorrente();
	}

	void Inizio4() {
		Turno.getInstance().cambioTurno();
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(5, 0), Scacchiera.getInstance().getCella(2, 3));
	}

	void Inizio5() {
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(5, 7), Scacchiera.getInstance().getCella(2, 4));
	}

	@Test
	void testIsMossaValida_AltoDx() {
		Inizio();
		assertTrue(a.isMossaValida(start, Scacchiera.getInstance().getCella(3, 2)));

	}

	@Test
	void testIsMossaValida_AltoSx() {
		Inizio();
		assertTrue(a.isMossaValida(start, Scacchiera.getInstance().getCella(1, 2)));
	}

	@Test
	void testIsMossaValida_AltoDx2() {
		Inizio();
		// in alto a destra cella occupata stesso colore movimento di uno
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(4, 1)));
	}

	@Test
	void testIsMossaValida_BassoDx() {
		Inizio();
		// in basso a destra
		assertTrue(a.isMossaValida(start, Scacchiera.getInstance().getCella(3, 4)));

	}

	@Test
	void testIsMossaValida_BassoSx() {
		Inizio();
		// in basso a sinistra
		assertTrue(a.isMossaValida(start, Scacchiera.getInstance().getCella(1, 4)));
	}

	@Test
	void testIsMossaValida_BassoDx2() {
		Inizio();
		// in basso a destra cella occupata stesso colore
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(4, 1), Scacchiera.getInstance().getCella(4, 5));
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(4, 5)));
	}

	@Test
	void testIsMossaValida_BassoSx2() {
		Inizio();
		// in basso a sinistra cella occupata stesso colore
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(4, 1), Scacchiera.getInstance().getCella(4, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(4, 5), Scacchiera.getInstance().getCella(0, 5));
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(0, 5)));
	}

	@Test
	void testIsMossaValida_AltoSx2() {
		Inizio();
		// in alto a sinistra cella occupata stesso colore
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(0, 1)));
	}

	@Test
	void testIsMossaValida_AltoDx3() {
		Inizio();
		// in alto a destra cella occupata stesso colore
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(4, 1), Scacchiera.getInstance().getCella(5, 1));
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(5, 1)));
	}

	@Test
	void testIsMossaValida_AltoDx_Mangiata() {
		Inizio2();
		// bianco in alto a destra mangiata
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(3, 1), Scacchiera.getInstance().getCella(3, 3));
		assertTrue(a.isMossaValida(start, Scacchiera.getInstance().getCella(3, 3)));
	}

	@Test
	void testIsMossaValida_AltoDx_PercorsoOccupato() {
		Inizio2();
		// in alto a destra con bianco con percorso occupato
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(6, 0)));
	}

	@Test
	void testIsMossaValida_BassoDx_PercorsoOccupato() {
		Inizio2();
		// in basso a destra con bianco percorso occupato
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(5, 7)));
	}

	@Test
	void testIsMossaValida_AltoSx_PercorsoOccupato() {
		Inizio3();
		// in alto a sinistra con bianco con percorso occupato
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(1, 0)));
	}

	@Test
	void testIsMossaValida_BassoSx_PercorsoOccupato() {
		Inizio3();
		// in basso a sinistra con bianco percorso occupato
		assertFalse(a.isMossaValida(start, Scacchiera.getInstance().getCella(2, 7)));

	}

	@Test
	void testConvertiMossaAltoDx() {
		Inizio4();
		// in alto a destra
		assertEquals("c5 d6", Alfiere.convertiMossa("Ad6"));
	}

	@Test
	void TestConvertiMossa_AltoSx() {
		Inizio4();
		// in alto a sinistra
		assertEquals("c5 b6", Alfiere.convertiMossa("Ab6"));
	}

	@Test
	void TestConvertiMossa_BassoSx() {
		Inizio4();
		// in basso a sinistra
		assertEquals("c5 a3", Alfiere.convertiMossa("Aa3"));

	}

	@Test
	void TestConvertiMossa_BassoDx() {
		Inizio4();
		// in basso a sinistra
		assertEquals("c5 e3", Alfiere.convertiMossa("Ae3"));
	}

	@Test
	void TestConvertiMossa_Mangiata_AltoDx() {
		Inizio5();
		// mangiata con bianco in alto a destra
		assertEquals("c4 f7", Alfiere.convertiMossa("Axf7"));
	}

	@Test
	void TestConvertiMossa_BassoDx2() {
		Inizio5();
		// bianco in basso a destra cella occupata stesso colore
		assertNotEquals("c4 e2", Alfiere.convertiMossa("Ae2"));
	}

	@Test
	void TestConvertiMossa_Mangiata_BassoDx() {
		Inizio5();
		// bianco in basso a destra cella occupata stesso colore
		assertNotEquals("c4 d3", Alfiere.convertiMossa("Axd3"));
	}

}
