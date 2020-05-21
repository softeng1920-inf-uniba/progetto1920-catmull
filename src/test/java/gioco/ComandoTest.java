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
	void testToString() {
		assertEquals("\u2022 " + "\033[1;37m" + "test" + "\u001B[0m" + " -->    " + "Effettua il test del costruttore", c.toString());
	}

}
