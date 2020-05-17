package gioco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.uniba.main.Controller;
import scacchiera.Scacchiera;

class ControllerTest {

	@BeforeAll
	static void setTests() {
		Controller.newController();
		Turno.newTurno();

	}

	@Test
	void testIsMossaInRangeValido() {
		assertTrue(Controller.isMossaInRangeValido("a2 a4"));

		assertFalse(Controller.isMossaInRangeValido("a0 a9"));

	}

	@Test
	void testIsArroccoValido() {
		assertFalse(Controller.isArroccoValido("e1 g1", "h1 f1", Menu.ARROCCO_CORTO));
		Turno.cambioTurno();
		assertFalse(Controller.isArroccoValido("e1 g1", "h1 f1", Menu.ARROCCO_CORTO));

	}

	@Test
	void testIsReProtetto() {
		Turno.cambioTurno();
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 6), Scacchiera.getCella(4, 5), 0));
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 7), Scacchiera.getCella(4, 5), 0));
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 7), Scacchiera.getCella(4, 6), 0));

		Scacchiera.nuovaScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(4, 6), Scacchiera.getCella(4, 4));

		Scacchiera.scambiaCella(Scacchiera.getCella(5, 1), Scacchiera.getCella(5, 3));
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 4), Scacchiera.getCella(5, 3), 0));

	}

}
