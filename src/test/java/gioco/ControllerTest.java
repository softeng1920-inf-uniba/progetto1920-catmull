package gioco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.main.Controller;
import scacchiera.Scacchiera;

class ControllerTest {

	@BeforeEach
	void setTests() {
		Controller.newController();
		Turno.newTurno();
	}

	@Test
	void testIsArroccoValidoBianco() {
		assertFalse(Controller.isArroccoValido("e1 g1", "h1 f1", Menu.ARROCCO_CORTO));
	}

	@Test
	void testIsArroccoValidoNero() {
		Turno.cambioTurno();
		assertFalse(Controller.isArroccoValido("e1 g1", "h1 f1", Menu.ARROCCO_CORTO));

	}

	@Test
	void testIsReProtettoCasoMossaRe() {
		Scacchiera.newScacchiera();
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 7), Scacchiera.getCella(4, 6)));
	}

	@Test
	void testIsReProtettoCellaFinaleOccupata() {
		Scacchiera.newScacchiera();
		Scacchiera.scambiaCella(Scacchiera.getCella(4, 6), Scacchiera.getCella(4, 4));
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 1), Scacchiera.getCella(5, 3));
		assertTrue(Controller.isReProtetto(Scacchiera.getCella(4, 4), Scacchiera.getCella(5, 3)));

	}

	@Test
	void testMossaScacchi() {
		assertEquals(true, Controller.mossaScacchi("a4"));
	}

	@Test
	void testMossaScacchiArrocco_Corto_NonValido() {
		assertEquals(false, Controller.mossaScacchi("0-0"));
	}

	@Test
	void testMossaScacchiArrocco_Lungo_NonValido() {
		assertEquals(false, Controller.mossaScacchi("0-0-0"));
	}

	@Test
	void testMossaScacchiArrocco_Lungo_Valido() {
		// bianco
		Scacchiera.newScacchiera();
		if (Controller.mossaScacchi("b4")) {
			if (Controller.mossaScacchi("c4")) {
				if (Controller.mossaScacchi("d4")) {
					if (Controller.mossaScacchi("Cc3")) {
						if (Controller.mossaScacchi("Ab2")) {
							if (Controller.mossaScacchi("Dd3")) {
								assertEquals(true, Controller.mossaScacchi("0-0-0"));
							}
						}
					}
				}
			}
		}
	}
	
	@Test
	void testMossaScacchiTorre() {
		Scacchiera.newScacchiera();
		if(Controller.mossaScacchi("h4"))
		{
			assertEquals(true, Controller.mossaScacchi("Th3"));
		}
	}
	
	@Test
	void testMossaScacchiRe() {
		Scacchiera.newScacchiera();
		if(Controller.mossaScacchi("e4"))
		{
			assertEquals(true, Controller.mossaScacchi("Re2"));
		}
	}
	
	
	

	@Test
	void testMossaScacchiArrocco_Corto_Valido() {
		// nero
		Scacchiera.newScacchiera();
		Turno.cambioTurno();
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 1), Scacchiera.getCella(5, 3));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 1), Scacchiera.getCella(6, 3));
		Scacchiera.scambiaCella(Scacchiera.getCella(6, 0), Scacchiera.getCella(7, 2));
		Scacchiera.scambiaCella(Scacchiera.getCella(5, 0), Scacchiera.getCella(6, 1));
		assertEquals(true, Controller.mossaScacchi("0-0"));
	}

	@Test
	void testMossaScacchi_EnPassant() {
		// bianco
		Scacchiera.newScacchiera();
		if (Controller.mossaScacchi("a4")) {
			Turno.cambioTurno();
			if (Controller.mossaScacchi("h5")) {
				Turno.cambioTurno();
				if (Controller.mossaScacchi("a5")) {
					Turno.cambioTurno();
					if (Controller.mossaScacchi("b5")) {
						Turno.cambioTurno();
						assertEquals(true, Controller.mossaScacchi("axb6 e.p."));
					}
				}
			}
		}
	}

	@Test
	void testMossaScacchi_EnPassantNonValido() {
		// bianco
		Scacchiera.newScacchiera();
		if (Controller.mossaScacchi("b4")) {
			Turno.cambioTurno();
			if (Controller.mossaScacchi("a5")) {
				Turno.cambioTurno();
				if (Controller.mossaScacchi("b5")) {
					Turno.cambioTurno();
					if (Controller.mossaScacchi("h5")) {
						Turno.cambioTurno();
						assertEquals(false, Controller.mossaScacchi("axb6"));
					}
				}
			}
		}
	}

	@Test
	void testMossaScacchi_MossaNonValida() {
		// bianco
		Scacchiera.newScacchiera();
		assertEquals(false, Controller.mossaScacchi("b5"));
	}

	@Test
	void testMossaScacchi_Mangiata() {
		// pedone nero mangia pedone bianco
		Scacchiera.newScacchiera();
		if (Controller.mossaScacchi("b4")) {
			Turno.cambioTurno();
			if (Controller.mossaScacchi("a5")) {
				Turno.cambioTurno();
				assertEquals(true, Controller.mossaScacchi("bxa5"));
			}
		}
	}

}
