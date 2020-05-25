package scacchiera.pedine;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

class PedoneTest {
	Pezzo pezzoCorrente;
	Cella start;
	Cella end;

	@BeforeEach
	void setTests() {
		Scacchiera.getInstance().inizializzaScacchiera();
		Turno.getInstance();

	}

	@Test
	void testIsMossaValidaUnaCellaBianco() {
		start = Scacchiera.getInstance().getCella(0, 6);
		end = Scacchiera.getInstance().getCella(0, 5);
		if (Scacchiera.getInstance().getNomePezzo(0, 6) != "Vuota") {
			pezzoCorrente = start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testIsMossaValidaDueCelleBianco() {
		start = Scacchiera.getInstance().getCella(0, 6);
		end = Scacchiera.getInstance().getCella(0, 4);
		if (Scacchiera.getInstance().getNomePezzo(0, 6) != "Vuota") {
			pezzoCorrente = start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testisMossaValidaFinalePienaBianco() {
		start = Scacchiera.getInstance().getCella(0, 6);
		end = Scacchiera.getInstance().getCella(1, 5);
		if (Scacchiera.getInstance().getNomePezzo(0, 6) != "Vuota") {
			Scacchiera.getInstance().getCella(1, 5).aggiungiPezzo(Scacchiera.getInstance().getCella(0, 1).getPezzoCorrente());
			pezzoCorrente = start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testisMossaValidaUnaCellaNero() {
		Turno.getInstance().cambioTurno();
		// mossa avanti di uno
		start = Scacchiera.getInstance().getCella(0, 1);
		end = Scacchiera.getInstance().getCella(0, 2);
		if (Scacchiera.getInstance().getNomePezzo(0, 1) != "Vuota") {
			pezzoCorrente = start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testIsMossaValidaMossaNonvalida() {
		start = Scacchiera.getInstance().getCella(0, 1);
		end = Scacchiera.getInstance().getCella(0, 4);
		if (Scacchiera.getInstance().getNomePezzo(0, 1) != "Vuota") {
			pezzoCorrente = start.getPezzoCorrente();
			assertFalse(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testIsMossaValidaInDietro() {
		start = Scacchiera.getInstance().getCella(0, 1);
		end = Scacchiera.getInstance().getCella(0, 0);
		if (Scacchiera.getInstance().getNomePezzo(0, 1) != "Vuota") {
			pezzoCorrente = start.getPezzoCorrente();
			assertFalse(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testIsMossaValidaDueCelle() {
		// mossa avanti di due cella
		start = Scacchiera.getInstance().getCella(3, 1);
		end = Scacchiera.getInstance().getCella(3, 3);
		if (Scacchiera.getInstance().getNomePezzo(3, 1) != "Vuota") {
			pezzoCorrente = start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testIsMossaValidaObliquoNonValida() {
		start = Scacchiera.getInstance().getCella(3, 1);
		end = Scacchiera.getInstance().getCella(4, 2);
		if (Scacchiera.getInstance().getNomePezzo(3, 1) != "Vuota") {
			pezzoCorrente = start.getPezzoCorrente();
			assertFalse(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testIsMossaValidaObliquoValida() {
		start = Scacchiera.getInstance().getCella(3, 1);
		end = Scacchiera.getInstance().getCella(4, 2);
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1,  6), Scacchiera.getInstance().getCella(4,  2));
		if (Scacchiera.getInstance().getNomePezzo(3, 1) != "Vuota") {
			pezzoCorrente = start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
	}

	@Test
	void testIsMossaValidaDueFinaleOccupata() {
		// mossa avanti di due celle con cella finale occupata
		start = Scacchiera.getInstance().getCella(3, 1);
		end = Scacchiera.getInstance().getCella(3, 3);
		if (Scacchiera.getInstance().getNomePezzo(3, 1) != "Vuota") {
			Scacchiera.getInstance().getCella(3, 3).aggiungiPezzo(Scacchiera.getInstance().getCella(2, 1).getPezzoCorrente());
			pezzoCorrente = start.getPezzoCorrente();
		}
	}

	@Test
	void testConvertiMossaSuCellaCorrente() {
		// mossa su cella start = cella end
		Turno.getInstance().cambioTurno();
		assertEquals(Pedone.convertiMossa("a7"), "a0 a0");
	}

	@Test
	void testConvertiMossaUnoAvanti() {
		// mossa su cella start = cella end
		Turno.getInstance().cambioTurno();
		assertEquals(Pedone.convertiMossa("a6"), "a7 a6");
	}

	@Test
	void testConvertiMossaDueAvanti() {
		// mossa su cella start = cella end
		assertEquals(Pedone.convertiMossa("a5"), "a7 a5");
	}

	@Test
	void testConvertiMossaCatturaPedone() {
		Turno.getInstance().cambioTurno();
		start = Scacchiera.getInstance().getCella(2, 1);
		pezzoCorrente = start.getPezzoCorrente();
		assertEquals("c7 b6", Pedone.convertiMossa("cxb6"));
	}

	@Test
	void testConvertiMossaEnPassant() {
		Scacchiera.getInstance().getCella(2, 3).aggiungiPezzo(Scacchiera.getInstance().getCella(0, 6).getPezzoCorrente());
		assertEquals("c5 d6", Pedone.convertiMossa("cxd6 e.p."));
	}

	@Test
	void testConvertiMossaUnaCellaBianco() {
		Turno.getInstance().cambioTurno();
		assertEquals(Pedone.convertiMossa("g3"), "g2 g3");
	}

	@Test
	void testConvertiMossaDueCelleBianco() {
		assertEquals(Pedone.convertiMossa("g4"), "g2 g4");
	}

	@Test
	void testIsEnPassantValido() {
		ArrayList<String> mosseGiocate = new ArrayList<String>();
		Scacchiera.getInstance().getCella(1, 3).aggiungiPezzo(Scacchiera.getInstance().getCella(1, 6).getPezzoCorrente());
		Scacchiera.getInstance().getCella(1, 6).rimuoviPezzoCorrente();
		Scacchiera.getInstance().getCella(0, 3).aggiungiPezzo(Scacchiera.getInstance().getCella(0, 1).getPezzoCorrente());
		Scacchiera.getInstance().getCella(0, 1).rimuoviPezzoCorrente();
		start = Scacchiera.getInstance().getCella(1, 3);
		end = Scacchiera.getInstance().getCella(0, 2);

		mosseGiocate.add("b2 b4");
		mosseGiocate.add("h7 h5");
		mosseGiocate.add("b4 b5");
		mosseGiocate.add("a7 a5");
		if (Scacchiera.getInstance().getNomePezzo(1, 3) != "Vuota") {
			Pedone p = (Pedone) start.getPezzoCorrente();
			assertTrue(p.isEnPassantValido(start, end, mosseGiocate));
		}
	}

	@Test
	void testIsEnPassantValidoSenzaMossaDiDue() {
		ArrayList<String> mosseGiocate = new ArrayList<String>();
		// Pezzo da catturare non si è mosso di due, ma ha fatto due mosse da uno
		Scacchiera.getInstance().getCella(1, 3).aggiungiPezzo(Scacchiera.getInstance().getCella(1, 6).getPezzoCorrente());
		Scacchiera.getInstance().getCella(1, 6).rimuoviPezzoCorrente();
		Scacchiera.getInstance().getCella(0, 3).aggiungiPezzo(Scacchiera.getInstance().getCella(0, 1).getPezzoCorrente());
		Scacchiera.getInstance().getCella(0, 1).rimuoviPezzoCorrente();
		start = Scacchiera.getInstance().getCella(1, 3);
		end = Scacchiera.getInstance().getCella(0, 2);
		mosseGiocate.clear();
		mosseGiocate.add("b2 b4");
		mosseGiocate.add("a7 a6");
		mosseGiocate.add("b4 b5");
		mosseGiocate.add("a6 a5");
		if (Scacchiera.getInstance().getNomePezzo(1, 3) != "Vuota") {
			Pedone p = (Pedone) start.getPezzoCorrente();
			assertFalse(p.isEnPassantValido(start, end, mosseGiocate));
		}
	}

	@Test
	void testIsEnPassantValidoCatturaNonValida() {
		ArrayList<String> mosseGiocate = new ArrayList<String>();
		// Pezzo da catturare si è spostato di due prima del pezzo che lo cattura
		Scacchiera.getInstance().getCella(1, 3).aggiungiPezzo(Scacchiera.getInstance().getCella(1, 6).getPezzoCorrente());
		Scacchiera.getInstance().getCella(1, 6).rimuoviPezzoCorrente();
		Scacchiera.getInstance().getCella(0, 3).aggiungiPezzo(Scacchiera.getInstance().getCella(0, 1).getPezzoCorrente());
		Scacchiera.getInstance().getCella(0, 1).rimuoviPezzoCorrente();
		start = Scacchiera.getInstance().getCella(1, 3);
		end = Scacchiera.getInstance().getCella(0, 2);
		mosseGiocate.clear();
		mosseGiocate.add("b2 b4");
		mosseGiocate.add("a7 a5");
		mosseGiocate.add("b4 b5");
		mosseGiocate.add("h7 h5");
		if (Scacchiera.getInstance().getNomePezzo(1, 3) != "Vuota") {
			Pedone p = (Pedone) start.getPezzoCorrente();
			assertFalse(p.isEnPassantValido(start, end, mosseGiocate));
		}
	}

	@Test
	void testIsEnPassantValidoBianco() {
		ArrayList<String> mosseGiocate = new ArrayList<String>();
		Turno.getInstance().cambioTurno();
		mosseGiocate.clear();
		mosseGiocate.add("a7 a5");
		mosseGiocate.add("h2 h4");
		mosseGiocate.add("a5 a4");
		mosseGiocate.add("b2 b4");
		Scacchiera.getInstance().getCella(0, 4).aggiungiPezzo(Scacchiera.getInstance().getCella(6, 1).getPezzoCorrente());
		Scacchiera.getInstance().getCella(1, 4).aggiungiPezzo(Scacchiera.getInstance().getCella(6, 6).getPezzoCorrente());
		start = Scacchiera.getInstance().getCella(0, 4);
		end = Scacchiera.getInstance().getCella(1, 5);
		if (Scacchiera.getInstance().getNomePezzo(0, 4) != "Vuota") {
			Pedone p = (Pedone) start.getPezzoCorrente();
			assertTrue(p.isEnPassantValido(start, end, mosseGiocate));
		}
	}

}
