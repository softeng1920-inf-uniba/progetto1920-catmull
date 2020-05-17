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
<<<<<<< HEAD
		Scacchiera.newScacchiera();
=======
		Scacchiera.nuovaScacchiera();
>>>>>>> Implementazione test classi comando, giocatore, turno, pezzo e scacchiera
		Turno.newTurno();
	}
	
	@Test
	void testGetNome() {
		assertEquals("Pedone", Scacchiera.getCella(0, 1).getPezzoCorrente().getNome());
	}
	
	@Test
	void testGetColore() {
		assertEquals(Colore.nero, Scacchiera.getCella(0, 1).getPezzoCorrente().getColore());
	}
	
	@Test
<<<<<<< HEAD
	void testGetSimboloBianco() {
		assertEquals('\u265d', Scacchiera.getCella(2, 0).getPezzoCorrente().getSimbolo());
	}
	
	@Test
	void testGetSimboloNero() {
=======
	void testGetSimbolo() {
		assertEquals('\u265d', Scacchiera.getCella(2, 0).getPezzoCorrente().getSimbolo());
>>>>>>> Implementazione test classi comando, giocatore, turno, pezzo e scacchiera
		assertEquals('\u2657', Scacchiera.getCella(2, 7).getPezzoCorrente().getSimbolo());
	}
	
	@Test
	void testToString() {
		assertEquals("Re nero \u265a", Scacchiera.getCella(4, 0).getPezzoCorrente().toString());
	}
	
	
	

}
