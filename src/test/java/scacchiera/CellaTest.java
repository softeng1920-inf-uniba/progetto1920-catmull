package scacchiera;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import gioco.Colore;
import scacchiera.pedine.Re;
class CellaTest {
	
	@Test
	void testCostruttore() {
		Cella c = new Cella(0, 0, new Re(Colore.bianco));
	}
	
	@Test
	void testStartX() {
		String s = "a2 a4";
		assertEquals(0, Cella.startX(s));
	}

	@Test
	void testStartY() {
		String s = "a2 a4";
		assertEquals(6, Cella.startY(s));
	}

	@Test
	void testEndX() {
		String s = "a2 a4";
		assertEquals(0, Cella.endX(s));
	}

	@Test
	void testEndY() {
		String s = "a2 a4";
		assertEquals(4, Cella.endY(s));
	}

}
