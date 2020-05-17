package gioco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MenuTest {
	

	@BeforeAll
	static void testNewMenu() {
		Menu.newMenu();
	}

	@Test
	void testPlay() {
		Comando c = new Comando("Play", "Inizia una nuova partita");
		assertEquals(Menu.play().getNome(), c.getNome());
		assertEquals(Menu.play().getDescrizione(), c.getDescrizione());
	}

	@Test
	void testIsArrocco() {
		assertEquals(Menu.ARROCCO_CORTO, Menu.isArrocco("0-0"));
		assertEquals(Menu.ARROCCO_CORTO, Menu.isArrocco("o-o"));
		assertEquals(Menu.ARROCCO_CORTO, Menu.isArrocco("O-O"));
		assertEquals(Menu.ARROCCO_LUNGO, Menu.isArrocco("0-0-0"));
		assertEquals(Menu.ARROCCO_LUNGO, Menu.isArrocco("o-o-o"));
		assertEquals(Menu.ARROCCO_LUNGO, Menu.isArrocco("O-O-O"));
		assertEquals(-1, Menu.isArrocco("0-0-0-0"));
	}

	@Test
	void testIsComandoValido() {
		assertTrue(Menu.isComandoValido("Moves"));
		assertTrue(Menu.isComandoValido("QUIT"));
		assertTrue(Menu.isComandoValido("captures"));
		assertTrue(Menu.isComandoValido("HELP"));
		assertTrue(Menu.isComandoValido("board"));
		assertFalse(Menu.isComandoValido("Menu"));
		
	}

	@Test
	void testIsNotazioneAlgebrica() {
		assertTrue(Menu.isNotazioneAlgebrica("a4"));
		assertTrue(Menu.isNotazioneAlgebrica("axa4"));
		assertTrue(Menu.isNotazioneAlgebrica("bxa4 e.p."));
		assertTrue(Menu.isNotazioneAlgebrica("Ca4"));
		assertTrue(Menu.isNotazioneAlgebrica("Caxa4"));
		assertTrue(Menu.isNotazioneAlgebrica("Da4"));
		assertTrue(Menu.isNotazioneAlgebrica("Dxa4"));
		assertTrue(Menu.isNotazioneAlgebrica("Aa4"));
		assertTrue(Menu.isNotazioneAlgebrica("Axa4"));
		assertTrue(Menu.isNotazioneAlgebrica("Ra4"));
		assertTrue(Menu.isNotazioneAlgebrica("Rxa4"));
		assertTrue(Menu.isNotazioneAlgebrica("Ta4"));
		assertTrue(Menu.isNotazioneAlgebrica("Txa4"));
		assertTrue(Menu.isNotazioneAlgebrica("0-0-0"));
		assertTrue(Menu.isNotazioneAlgebrica("0-0"));
		assertFalse(Menu.isNotazioneAlgebrica("a2 a4"));
		assertFalse(Menu.isNotazioneAlgebrica(""));
	}

}
