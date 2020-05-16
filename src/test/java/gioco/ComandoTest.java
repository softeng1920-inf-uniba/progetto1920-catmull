package gioco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


class ComandoTest {
	static Comando c;
	
	@BeforeAll
	static void TestComando() {
		c = new Comando("test", "Effettua il test del costruttore");
	}
	
	@Test
	void testGetNome() {
		assertEquals("test", c.getNome());
	}

	@Test
	void testGetDescrizione() {
		assertEquals("Effettua il test del costruttore", c.getDescrizione());
	}

	@Test
	void testToString() {
		assertEquals("\u2022 " + "\033[1;37m" + c.getNome() + "\u001B[0m" + " -->    " + c.getDescrizione(), c.toString());
	}

}
