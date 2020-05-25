package gioco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.main.Controller;
import scacchiera.Scacchiera;

class ControllerTest {

	@BeforeEach
	void setTests() {
		Controller.getInstance();
		Turno.getInstance().inizializzaTurno();
	}

	@Test
	void testMossaScacchi() {
		assertEquals(true, Controller.getInstance().mossaScacchi("a4"));
	}

	@Test
	void testMossaScacchiArrocco_Corto_NonValido() {
		assertEquals(false, Controller.getInstance().mossaScacchi("0-0"));
	}

	@Test
	void testMossaScacchiArrocco_Lungo_NonValido() {
		assertEquals(false, Controller.getInstance().mossaScacchi("0-0-0"));
	}

	@Test
	void testMossaScacchiArrocco_Lungo_Valido() {
		// bianco
		Scacchiera.getInstance().inizializzaScacchiera();
		if (Controller.getInstance().mossaScacchi("b4")) {
			if (Controller.getInstance().mossaScacchi("c4")) {
				if (Controller.getInstance().mossaScacchi("d4")) {
					if (Controller.getInstance().mossaScacchi("Cc3")) {
						if (Controller.getInstance().mossaScacchi("Ab2")) {
							if (Controller.getInstance().mossaScacchi("Dd3")) {
								assertEquals(true, Controller.getInstance().mossaScacchi("0-0-0"));
							}
						}
					}
				}
			}
		}
	}
	
	@Test
	void testMossaScacchiIsArroccoValidoNonValido() {
		assertEquals(false, Controller.getInstance().isArroccoValido("f1 g1","h1 f1", 2));
	}
	
	@Test
	void testMossaScacchiTorre() {
		Scacchiera.getInstance().inizializzaScacchiera();
		if(Controller.getInstance().mossaScacchi("h4"))
		{
			assertEquals(true, Controller.getInstance().mossaScacchi("Th3"));
		}
	}
	
	@Test
	void testMossaScacchiRe() {
		Scacchiera.getInstance().inizializzaScacchiera();
		if(Controller.getInstance().mossaScacchi("e4"))
		{
			assertEquals(true, Controller.getInstance().mossaScacchi("Re2"));
		}
	}
	
	
	

	@Test
	void testMossaScacchiArrocco_Corto_Valido() {
		// nero
		Scacchiera.getInstance().inizializzaScacchiera();
		Turno.getInstance().cambioTurno();
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(5, 1), Scacchiera.getInstance().getCella(5, 3));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(6, 1), Scacchiera.getInstance().getCella(6, 3));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(6, 0), Scacchiera.getInstance().getCella(7, 2));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(5, 0), Scacchiera.getInstance().getCella(6, 1));
		assertEquals(true, Controller.getInstance().mossaScacchi("0-0"));
	}

	@Test
	void testMossaScacchi_EnPassant() {
		// bianco
		Scacchiera.getInstance().inizializzaScacchiera();
		if (Controller.getInstance().mossaScacchi("a4")) {
			Turno.getInstance().cambioTurno();
			if (Controller.getInstance().mossaScacchi("h5")) {
				Turno.getInstance().cambioTurno();
				if (Controller.getInstance().mossaScacchi("a5")) {
					Turno.getInstance().cambioTurno();
					if (Controller.getInstance().mossaScacchi("b5")) {
						Turno.getInstance().cambioTurno();
						assertEquals(true, Controller.getInstance().mossaScacchi("axb6 e.p."));
					}
				}
			}
		}
	}

	@Test
	void testMossaScacchi_EnPassantNonValido() {
		// bianco
		Scacchiera.getInstance().inizializzaScacchiera();
		if (Controller.getInstance().mossaScacchi("b4")) {
			Turno.getInstance().cambioTurno();
			if (Controller.getInstance().mossaScacchi("a5")) {
				Turno.getInstance().cambioTurno();
				if (Controller.getInstance().mossaScacchi("b5")) {
					Turno.getInstance().cambioTurno();
					if (Controller.getInstance().mossaScacchi("h5")) {
						Turno.getInstance().cambioTurno();
						assertEquals(false, Controller.getInstance().mossaScacchi("axb6"));
					}
				}
			}
		}
	}

	@Test
	void testMossaScacchi_MossaNonValida() {
		// bianco
		Scacchiera.getInstance().inizializzaScacchiera();
		assertEquals(false, Controller.getInstance().mossaScacchi("b5"));
	}

	@Test
	void testMossaScacchi_Mangiata() {
		// pedone nero mangia pedone bianco
		Scacchiera.getInstance().inizializzaScacchiera();
		if (Controller.getInstance().mossaScacchi("b4")) {
			Turno.getInstance().cambioTurno();
			if (Controller.getInstance().mossaScacchi("a5")) {
				Turno.getInstance().cambioTurno();
				assertEquals(true, Controller.getInstance().mossaScacchi("bxa5"));
			}
		}
	}

}
