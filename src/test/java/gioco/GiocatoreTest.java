package gioco;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scacchiera.Scacchiera;
import scacchiera.pedine.Pezzo;

class GiocatoreTest {

	Giocatore g1;
	
	@BeforeEach
	void setTurno() {
		Turno.newTurno();
	}
	
	
	@Test
	void testGetNome() {
		g1 = new Giocatore(Colore.bianco);
		g1.setNome("Giocatore1");
		assertEquals("Giocatore1", g1.getNome());
	}

	@Test
	void testGetColore() {
		g1 = new Giocatore(Colore.bianco);
		g1.setNome("Giocatore1");
		assertEquals(Colore.bianco, g1.getColore());
	}

	@Test
	void testAddPezziCatturati() {
		Scacchiera.newScacchiera();
		g1 = Turno.getGiocatoreInTurno();
		g1.addPezziCatturati(Scacchiera.getCella(0, 6).getPezzoCorrente());
		assertNotNull(g1.getPezziCatturati());
	}

	@Test
	void testGetPezziCatturati() {
		Scacchiera.newScacchiera();

		g1 = Turno.getGiocatoreInTurno();
		g1.addPezziCatturati(Scacchiera.getCella(0, 6).getPezzoCorrente());
		ArrayList<Pezzo> pezziCatturati = new ArrayList<Pezzo>();
		pezziCatturati.add(Scacchiera.getCella(0, 6).getPezzoCorrente());
		assertEquals(pezziCatturati, g1.getPezziCatturati());
	}

	@Test
	void testIsEmptyPezziCatturati() {
		g1 = Turno.getGiocatoreInTurno();
		assertTrue(g1.isEmptyPezziCatturati());
	}

	@Test
	void testRemovePezzoCatturato() {
		Scacchiera.newScacchiera();
		g1 = Turno.getGiocatoreInTurno();
		g1.addPezziCatturati(Scacchiera.getCella(0, 6).getPezzoCorrente());
		g1.removePezzoCatturato();
		assertTrue(g1.isEmptyPezziCatturati());
	}

}
