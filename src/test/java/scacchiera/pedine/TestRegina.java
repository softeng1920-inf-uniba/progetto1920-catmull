package scacchiera.pedine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

class TestRegina {

	Pezzo pezzoCorrente;
	Regina d;
	Cella start;
	Cella end;

	@BeforeEach
	void setTests() {
		Scacchiera.getInstance().inizializzaScacchiera();
		Turno.getInstance();

	}

	void Inizio() {
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(3, 7), Scacchiera.getInstance().getCella(3, 3));
		start = Scacchiera.getInstance().getCella(3, 3);
		d = (Regina) start.getPezzoCorrente();
	}
	

	void Inizio1() {
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(3, 7), Scacchiera.getInstance().getCella(3, 3));
	}

	@Test
	void testIsMossaValida_AltoDx() {
		Inizio();
		// in alto a destra
		assertTrue(d.isMossaValida(start, Scacchiera.getInstance().getCella(4, 2)));
	}

	@Test
	void testIsMossaValida_AltoSx() {
		Inizio();
		// in alto a sinistra
		assertTrue(d.isMossaValida(start, Scacchiera.getInstance().getCella(2, 2)));
	}

	@Test
	void testIsMossaValida_BassoDx() {
		Inizio();
		// in basso a destra
		assertTrue(d.isMossaValida(start, Scacchiera.getInstance().getCella(5, 5)));
	}

	@Test
	void testIsMossaValida_BassoSx() {
		Inizio();
		// in basso a sinistra
		assertTrue(d.isMossaValida(start, Scacchiera.getInstance().getCella(1, 5)));
	}

	@Test
	void testIsMossaValida_Dx() {
		Inizio();
		// destra
		assertTrue(d.isMossaValida(start, Scacchiera.getInstance().getCella(7, 3)));
	}

	@Test
	void testIsMossaValida_Sx() {
		Inizio();
		// sinistra
		assertTrue(d.isMossaValida(start, Scacchiera.getInstance().getCella(1, 3)));
	}

	@Test
	void testIsMossaValida_Alto() {
		Inizio();
		// in alto
		assertTrue(d.isMossaValida(start, Scacchiera.getInstance().getCella(3, 2)));
	}

	@Test
	void testIsMossaValida_Basso() {
		Inizio();
		// in basso
		assertTrue(d.isMossaValida(start, Scacchiera.getInstance().getCella(3, 5)));
	}

	@Test
	void testIsMossaValida_Alto_Occupato() {
		Inizio();
		// in alto occupato
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(3, 0)));
	}

	@Test
	void testIsMossaValida_BassoOccupato() {
		Inizio();
		// in basso occupato
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(3, 7)));
	}

	@Test
	void testIsMossaValida_Dx_Occupato() {
		Inizio();
		// a destra occupato
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(6, 1), Scacchiera.getInstance().getCella(6, 3));
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(7, 3)));
	}

	@Test
	void testIsMossaValida_Sx_Occupato() {
		Inizio();
		// a sinistra occupato
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1, 1), Scacchiera.getInstance().getCella(1, 3));
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(0, 3)));
	}

	@Test
	void testIsMossaValida_AltoSx_Occupato() {
		Inizio();
		// in alto a sinistra percorso occupato
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(6, 1), Scacchiera.getInstance().getCella(6, 3));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1, 1), Scacchiera.getInstance().getCella(1, 3));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1, 3), Scacchiera.getInstance().getCella(1, 1));
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(0, 0)));
	}

	@Test
	void testIsMossaValida_AltoDx_Occupato() {
		Inizio();
		// in alto a destra percorso occupato
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(6, 0)));
	}

	@Test
	void testIsMossaValida_BassoSx_Occupato() {
		Inizio();
		// in basso a sinistra percorso occupato
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1, 6), Scacchiera.getInstance().getCella(1, 5));
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(0, 6)));
	}

	@Test
	void testIsMossaValida_BassoDx_Occupato() {
		Inizio();
		// in basso a destra percorso occupato
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(7, 7)));
	}

	@Test
	void testIsMossaValida_Mangiata() {
		Inizio();
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(6, 1), Scacchiera.getInstance().getCella(6, 3));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1, 1), Scacchiera.getInstance().getCella(1, 3));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1, 3), Scacchiera.getInstance().getCella(1, 1));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1, 6), Scacchiera.getInstance().getCella(1, 5));
		// mangio con bianco il bianco
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(1, 5)));
	}

	@Test
	void testIsMossaValida() {
		Inizio();
		// movimento non valido
		assertFalse(d.isMossaValida(start, Scacchiera.getInstance().getCella(5, 4)));
	}


	@Test
	void testConvertiMossa_AltoDx() {
		Inizio1();
		// in alto a destra
		assertEquals("d5 e6", Regina.convertiMossa("De6"));
		}

		@Test
		void testConvertiMossa_AltoSx() {
			Inizio1();
			// in alto a sinistra
			assertEquals("d5 c6", Regina.convertiMossa("Dc6"));
		}
		
		@Test
		void testConvertiMossa_BassoSx() {
			Inizio1();
			// in basso a sinistra
			assertEquals("d5 b3", Regina.convertiMossa("Db3"));
		}
		
		@Test
		void testConvertiMossa_BassoDx() {
			Inizio1();
			// in basso a destra
			assertEquals("d5 f3", Regina.convertiMossa("Df3"));
		}

		@Test
		void testConvertiMossa_Mangiata() {
			Inizio1();
			// mangiata con bianco in alto a destra
			assertEquals("d5 f7", Regina.convertiMossa("Dxf7"));
		}
		
		@Test
		void testConvertiMossa_MangiataVuota() {
			Inizio1();
			// mangiata con cella di destinazione vuota in alto a destra
			assertEquals("a0 a0", Regina.convertiMossa("Dxe6"));
		}
		
		@Test
		void testConvertiMossa() {
			Inizio1();
			// va in una cella occupata dall'avversario senza mangiare
			assertEquals("a0 a0", Regina.convertiMossa("Df7"));
		}


}
