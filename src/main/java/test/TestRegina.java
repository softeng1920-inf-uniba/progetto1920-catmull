package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;
import scacchiera.pedine.Regina;
import scacchiera.pedine.Alfiere;
import scacchiera.pedine.Pezzo;

class TestRegina {

	Pezzo pezzoCorrente;
	Regina d;
	Cella start;
	Cella end;

	@BeforeEach
	void setTests() {
		Scacchiera.nuovaScacchiera();
		Turno.newTurno();

	}

	@Test
	void testIsMossaValida() {
		Scacchiera.scambiaCella(Scacchiera.getCella(3, 7), Scacchiera.getCella(3, 3));
		start = Scacchiera.getCella(3, 3);
		d = (Regina) start.getPezzoCorrente();

		// in alto a destra
		assertTrue(d.isMossaValida(start, Scacchiera.getCella(4, 2)));

		// in alto a sinistra
		assertTrue(d.isMossaValida(start, Scacchiera.getCella(2, 2)));

		// in basso a destra
		assertTrue(d.isMossaValida(start, Scacchiera.getCella(5, 5)));

		// in basso a sinistra
		assertTrue(d.isMossaValida(start, Scacchiera.getCella(1, 5)));

		// destra
		assertTrue(d.isMossaValida(start, Scacchiera.getCella(7, 3)));

		// sinistra
		assertTrue(d.isMossaValida(start, Scacchiera.getCella(1, 3)));

		// in alto
		assertTrue(d.isMossaValida(start, Scacchiera.getCella(3, 2)));

		// in basso
		assertTrue(d.isMossaValida(start, Scacchiera.getCella(3, 5)));

		// in alto occupato
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(3, 0)));

		// in basso occupato
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(3, 7)));

		// a destra occupato
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 1), Scacchiera.getCella(6, 3));
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(7, 3)));

		// a sinistra occupato
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 1), Scacchiera.getCella(1, 3));
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(0, 3)));

		// in alto a sinistra percorso occupato
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 3), Scacchiera.getCella(1, 1));
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(0, 0)));

		// in alto a destra percorso occupato
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(6, 0)));

		// in basso a sinistra percorso occupato
		Scacchiera.scambiaCella(Scacchiera.getCella(1, 6), Scacchiera.getCella(1, 5));
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(0, 6)));

		// in basso a destra percorso occupato
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(7, 7)));

		// mangio con bianco il bianco
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(1, 5)));

		// movimento non valido
		assertFalse(d.isMossaValida(start, Scacchiera.getCella(5, 4)));

	}

	@Test
	void testConvertiMossa() {
		Scacchiera.scambiaCella(Scacchiera.getCella(3, 7), Scacchiera.getCella(3, 3));

		// in alto a destra
		assertEquals("d5 e6", Regina.convertiMossa("De6"));

		// in alto a sinistra
		assertEquals("d5 c6", Regina.convertiMossa("Dc6"));

		// in basso a sinistra
		assertEquals("d5 b3", Regina.convertiMossa("Db3"));

		// in basso a destra
		assertEquals("d5 f3", Regina.convertiMossa("Df3"));

		// mangiata con bianco in alto a destra
		assertEquals("d5 f7", Regina.convertiMossa("Dxf7"));

		//mangiata con cella di destinazione vuota in alto a destra
		assertEquals("a0 a0", Regina.convertiMossa("Dxe6"));
		
		//va in una cella occupata dall'avversario senza mangiare
		assertEquals("a0 a0", Regina.convertiMossa("Df7"));
		

		
	}
}
