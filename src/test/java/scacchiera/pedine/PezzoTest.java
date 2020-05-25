package scacchiera.pedine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import scacchiera.Scacchiera;
import org.junit.jupiter.api.Test;
import gioco.*;

class PezzoTest {
	Pezzo p;
	
	@BeforeEach
	void setTests() {
		Scacchiera.getInstance().inizializzaScacchiera();
		Turno.getInstance();
	}
	
	@Test
	void testGetNome() {
		assertEquals("Pedone", Scacchiera.getInstance().getCella(0, 1).getPezzoCorrente().getNome());
	}
	
	@Test
	void testGetColore() {
		assertEquals(Colore.nero, Scacchiera.getInstance().getCella(0, 1).getPezzoCorrente().getColore());
	}
	
	@Test
	void testGetSimboloBianco() {
		assertEquals('\u265d', Scacchiera.getInstance().getCella(2, 0).getPezzoCorrente().getSimbolo());
	}
	
	@Test
	void testGetSimboloNero() {
		assertEquals('\u2657', Scacchiera.getInstance().getCella(2, 7).getPezzoCorrente().getSimbolo());
	}
	
	@Test
	void testToString() {
		assertEquals("Re nero \u265a", Scacchiera.getInstance().getCella(4, 0).getPezzoCorrente().toString());
	}
	
	
	

}
