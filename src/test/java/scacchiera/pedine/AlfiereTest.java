package scacchiera.pedine;

import scacchiera.Cella;
import scacchiera.Scacchiera;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gioco.Turno;

class AlfiereTest {
	Pezzo pezzoCorrente;
	Alfiere a;
	Cella start;
	Cella end;

	@BeforeEach
	void setTests() {
		Scacchiera.nuovaScacchiera();
		Turno.newTurno();

	}

	@Test
	void testIsMossaValida() {
		Turno.cambioTurno();
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 0), Scacchiera.getCella(2, 3));
		start = Scacchiera.getCella(2, 3);
		a = (Alfiere) start.getPezzoCorrente();

		// in alto a destra
		assertTrue(a.isMossaValida(start, Scacchiera.getCella(3, 2)));

		// in alto a destra cella occupata stesso colore movimento di uno
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(4, 1)));
		// in alto a sinistra
		assertTrue(a.isMossaValida(start, Scacchiera.getCella(1, 2)));

		// in basso a destra
		assertTrue(a.isMossaValida(start, Scacchiera.getCella(3, 4)));

		// in basso a sinistra
		assertTrue(a.isMossaValida(start, Scacchiera.getCella(1, 4)));

		// in basso a destra cella occupata stesso colore
		Scacchiera.scambiaCella(Scacchiera.getCella(4, 1), Scacchiera.getCella(4, 5));
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(4, 5)));

		// in basso a sinistra cella occupata stesso colore
		Scacchiera.scambiaCella(Scacchiera.getCella(4, 5), Scacchiera.getCella(0, 5));
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(0, 5)));

		// in alto a sinistra cella occupata stesso colore
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(0, 1)));

		// in alto a destra cella occupata stesso colore
		Scacchiera.scambiaCella(Scacchiera.getCella(4, 1), Scacchiera.getCella(5, 1));
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(5, 1)));

		// bianco in alto a destra mangiata
		Turno.cambioTurno();

		Scacchiera.nuovaScacchiera();

		Scacchiera.scambiaCella(Scacchiera.getCella(5, 7), Scacchiera.getCella(2, 4));
		start = Scacchiera.getCella(2, 4);
		a = (Alfiere) start.getPezzoCorrente();
		Scacchiera.scambiaCella(Scacchiera.getCella(3, 1), Scacchiera.getCella(3, 3));
		assertTrue(a.isMossaValida(start, Scacchiera.getCella(3, 3)));

		// in alto a destra con bianco con percorso occupato
		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 7), Scacchiera.getCella(2, 4));
		start = Scacchiera.getCella(2, 4);
		a = (Alfiere) start.getPezzoCorrente();
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(6, 0)));

		// in basso a destra con bianco percorso occupato
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(5, 7)));
		
		// in alto a sinistra con bianco con percorso occupato
		Scacchiera.scambiaCella(Scacchiera.getCella(2, 7), Scacchiera.getCella(5, 4));
		start = Scacchiera.getCella(5, 4);
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(1, 0)));

		// in basso a sinistra con bianco percorso occupato
		assertFalse(a.isMossaValida(start, Scacchiera.getCella(2, 7)));

	}

	@Test
	void testConvertiMossa() {
		Turno.cambioTurno();
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 0), Scacchiera.getCella(2, 3));

		// in alto a destra
		assertEquals("c5 d6", Alfiere.convertiMossa("Ad6"));

		// in alto a sinistra
		assertEquals("c5 b6", Alfiere.convertiMossa("Ab6"));

		// in basso a sinistra
		assertEquals("c5 a3", Alfiere.convertiMossa("Aa3"));

		// in basso a destra
		assertEquals("c5 e3", Alfiere.convertiMossa("Ae3"));

		// mangiata con bianco in alto a destra
		Scacchiera.nuovaScacchiera();
		Turno.cambioTurno();
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 7), Scacchiera.getCella(2, 4));
		assertEquals("c4 f7", Alfiere.convertiMossa("Axf7"));

		// bianco in basso a destra cella occupata stesso colore
		assertNotEquals("c4 e2", Alfiere.convertiMossa("Ae2"));

		// bianco in basso a destra cella occupata stesso colore
		assertNotEquals("c4 d3", Alfiere.convertiMossa("Axd3"));

	}

}
