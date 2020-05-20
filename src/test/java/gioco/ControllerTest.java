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
<<<<<<< HEAD
	void testIsArroccoValidoBianco() {
		assertFalse(Controller.isArroccoValido("e1 g1", "h1 f1", Menu.ARROCCO_CORTO));
	}

	@Test
=======
	void testIsMossaInRangeValidoCorretta() {
		assertTrue(Controller.isMossaInRangeValido("a2 a4"));
	}

	@Test
	void testIsMossaInRangeValidoNonCorretta() {
		assertFalse(Controller.isMossaInRangeValido("a0 a9"));
	}

	@Test
	void testIsArroccoValidoBianco() {
		assertFalse(Controller.isArroccoValido("e1 g1", "h1 f1", Menu.ARROCCO_CORTO));
	}

	@Test
>>>>>>> Miglioramento classi PedoneTest, TurnoTest e GiocatoreTest
	void testIsArroccoValidoNero() {
		Turno.cambioTurno();
		assertFalse(Controller.isArroccoValido("e1 g1", "h1 f1", Menu.ARROCCO_CORTO));

	}

	@Test
	void testIsReProtettoCasoMossaRe() {
<<<<<<< HEAD
		Scacchiera.newScacchiera();
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 7), Scacchiera.getCella(4, 6)));
=======
		Scacchiera.nuovaScacchiera();
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 7), Scacchiera.getCella(4, 6), 0));
>>>>>>> Miglioramento classi PedoneTest, TurnoTest e GiocatoreTest
	}
	
	@Test
	void testIsReProtettoCellaFinaleOccupata() {
<<<<<<< HEAD
		Scacchiera.newScacchiera();
=======
		Scacchiera.nuovaScacchiera();
>>>>>>> Miglioramento classi PedoneTest, TurnoTest e GiocatoreTest
		Scacchiera.scambiaCella(Scacchiera.getCella(4, 6), Scacchiera.getCella(4, 4));
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 1), Scacchiera.getCella(5, 3));
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 4), Scacchiera.getCella(5, 3)));

	}

}
