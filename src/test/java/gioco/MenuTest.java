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
<<<<<<< HEAD
	}

	@Test
	void testIsArrocco_00() {
		assertEquals(Menu.ARROCCO_CORTO, Menu.isArrocco("0-0"));
	}
	
	@Test
	void testIsArrocco_oo() {
		assertEquals(Menu.ARROCCO_CORTO, Menu.isArrocco("o-o"));
	}
	
	@Test
	void testIsArrocco_OO() {
		assertEquals(Menu.ARROCCO_CORTO, Menu.isArrocco("O-O"));
	}
	
	@Test
	void testIsArrocco_000() {
		assertEquals(Menu.ARROCCO_LUNGO, Menu.isArrocco("0-0-0"));
	}
	
	@Test
	void testIsArrocco_ooo() {
		assertEquals(Menu.ARROCCO_LUNGO, Menu.isArrocco("o-o-o"));
	}
	
	@Test
	void testIsArrocco_OOO() {
		assertEquals(Menu.ARROCCO_LUNGO, Menu.isArrocco("O-O-O"));
	}
	
	@Test
	void testIsArroccoNonValido() {
=======
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
>>>>>>> Implementazione test classi comando, giocatore, turno, pezzo e scacchiera
		assertEquals(-1, Menu.isArrocco("0-0-0-0"));
	}

	@Test
<<<<<<< HEAD
	void testIsComandoValidoMoves() {
		assertTrue(Menu.isComandoValido("Moves"));
	}
	
	@Test
	void testIsComandoValidoQuit() {
		assertTrue(Menu.isComandoValido("QUIT"));
	}
	
	@Test
	void testIsComandoValidoCaptures() {
		assertTrue(Menu.isComandoValido("captures"));
	}
	
	@Test
	void testIsComandoValidoHelp() {
		assertTrue(Menu.isComandoValido("HELP"));
	}
	
	@Test
	void testIsComandoValidoBoard() {
		assertTrue(Menu.isComandoValido("board"));
	}
	
	@Test
	void testIsComandoValidoMenu() {
		assertFalse(Menu.isComandoValido("Menu"));
	}
	
	@Test
	void testIsNotazioneAlgebricaAlfiereMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Aa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaAlfiereCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Axa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaTorreMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Ta4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaTorreCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Txa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaCavalloMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Ca4"));	
	}
	
	@Test
	void testIsNotazioneAlgebricaCavalloCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Caxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Ra4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Rxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReginaMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Da4"));
		
	}
	
	@Test
	void testIsNotazioneAlgebricaReginaCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Dxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("a4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("axa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneEP() {
		assertTrue(Menu.isNotazioneAlgebrica("bxa4 e.p."));
	}
	
	@Test
	void testIsNotazioneAlgebricaArroccoCorto() {
		assertTrue(Menu.isNotazioneAlgebrica("0-0"));
	}
	
	@Test
	void testIsNotazioneAlgebricaArroccoLungo() {
		assertTrue(Menu.isNotazioneAlgebrica("0-0-0"));
	}
	
	@Test
	void testIsNotazioneAlgebricaComandoVuoto() {
		assertFalse(Menu.isNotazioneAlgebrica(""));
	}

	@Test
	void testIsNotazioneAlgebricaErrata() {
		assertFalse(Menu.isNotazioneAlgebrica("a2 a4"));
=======
	void testIsComandoValido() {
		assertTrue(Menu.isComandoValido("Moves"));
		assertTrue(Menu.isComandoValido("QUIT"));
		assertTrue(Menu.isComandoValido("captures"));
		assertTrue(Menu.isComandoValido("HELP"));
		assertTrue(Menu.isComandoValido("board"));
		assertFalse(Menu.isComandoValido("Menu"));
		
	}
	
	@Test
	void testIsNotazioneAlgebricaAlfiereMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Aa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaAlfiereCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Axa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaTorreMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Ta4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaTorreCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Txa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaCavalloMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Ca4"));	
	}
	
	@Test
	void testIsNotazioneAlgebricaCavalloCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Caxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Ra4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Rxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaReginaMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("Da4"));
		
	}
	
	@Test
	void testIsNotazioneAlgebricaReginaCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("Dxa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneMossa() {
		assertTrue(Menu.isNotazioneAlgebrica("a4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneCattura() {
		assertTrue(Menu.isNotazioneAlgebrica("axa4"));
	}
	
	@Test
	void testIsNotazioneAlgebricaPedoneEP() {
		assertTrue(Menu.isNotazioneAlgebrica("bxa4 e.p."));
	}
	
	@Test
	void testIsNotazioneAlgebricaArroccoCorto() {
		assertTrue(Menu.isNotazioneAlgebrica("0-0"));
	}
	
	@Test
	void testIsNotazioneAlgebricaArroccoLungo() {
		assertTrue(Menu.isNotazioneAlgebrica("0-0-0"));
	}
	
	@Test
	void testIsNotazioneAlgebricaComandoVuoto() {
		assertFalse(Menu.isNotazioneAlgebrica(""));
>>>>>>> Implementazione test classi comando, giocatore, turno, pezzo e scacchiera
	}

	@Test
	void testIsNotazioneAlgebricaErrata() {
		assertFalse(Menu.isNotazioneAlgebrica("a2 a4"));
	}

}
