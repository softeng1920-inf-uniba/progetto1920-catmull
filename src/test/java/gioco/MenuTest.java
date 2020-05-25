package gioco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MenuTest {
	

	@BeforeAll
	static void testNewMenu() {
		Menu.getInstance();
	}

	@Test
	void testPlay() {
		Comando c = new Comando("Play", "Inizia una nuova partita");
		assertEquals(Menu.getInstance().play().getNome(), c.getNome());
	}

	@Test
	void testIsArrocco_00() {
		assertEquals(Menu.ARROCCO_CORTO, Menu.getInstance().isArrocco("0-0"));
	}
	
	@Test
	void testIsArrocco_oo() {
		assertEquals(Menu.ARROCCO_CORTO, Menu.getInstance().isArrocco("o-o"));
	}
	
	@Test
	void testIsArrocco_OO() {
		assertEquals(Menu.ARROCCO_CORTO, Menu.getInstance().isArrocco("O-O"));
	}
	
	@Test
	void testIsArrocco_000() {
		assertEquals(Menu.ARROCCO_LUNGO, Menu.getInstance().isArrocco("0-0-0"));
	}
	
	@Test
	void testIsArrocco_ooo() {
		assertEquals(Menu.ARROCCO_LUNGO, Menu.getInstance().isArrocco("o-o-o"));
	}
	
	@Test
	void testIsArrocco_OOO() {
		assertEquals(Menu.ARROCCO_LUNGO, Menu.getInstance().isArrocco("O-O-O"));
	}
	
	@Test
	void testIsArroccoNonValido() {
		assertEquals(-1, Menu.getInstance().isArrocco("0-0-0-0"));
	}

	@Test
	void testIsComandoValidoMoves() {
		assertTrue(Menu.getInstance().isComandoValido("Moves"));
	}
	
	@Test
	void testIsComandoValidoQuit() {
		assertTrue(Menu.getInstance().isComandoValido("QUIT"));
	}
	
	@Test
	void testIsComandoValidoCaptures() {
		assertTrue(Menu.getInstance().isComandoValido("captures"));
	}
	
	@Test
	void testIsComandoValidoHelp() {
		assertTrue(Menu.getInstance().isComandoValido("HELP"));
	}
	
	@Test
	void testIsComandoValidoBoard() {
		assertTrue(Menu.getInstance().isComandoValido("board"));
	}
	
	@Test
	void testIsComandoValidoMenu() {
		assertFalse(Menu.getInstance().isComandoValido("Menu"));
	}
	
	@Test
	void testIsNotazioneAlgebricaAlfiereMossa() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Aa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaAlfiereCattura() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Axa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaTorreMossa() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Ta4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaTorreCattura() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Txa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaCavalloMossa() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Ca4"));	
	}
	
	@Test
	void testIsNotazioneAlgebricaCavalloCattura() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Caxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReMossa() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Ra4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReCattura() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Rxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReginaMossa() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Da4"));
		
	}
	
	@Test
	void testIsNotazioneAlgebricaReginaCattura() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("Dxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneMossa() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("a4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneCattura() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("axa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneEP() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("bxa4 e.p."));
	}
	
	@Test
	void testIsNotazioneAlgebricaArroccoCorto() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("0-0"));
	}
	
	@Test
	void testIsNotazioneAlgebricaArroccoLungo() {
		assertTrue(Menu.getInstance().isNotazioneAlgebrica("0-0-0"));
	}
	
	@Test
	void testIsNotazioneAlgebricaComandoVuoto() {
		assertFalse(Menu.getInstance().isNotazioneAlgebrica(""));
	}

	@Test
	void testIsNotazioneAlgebricaErrata() {
		assertFalse(Menu.getInstance().isNotazioneAlgebrica("a2 a4"));
	}


}
