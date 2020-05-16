package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import scacchiera.Cella;
class CellaTest {

	@Test
	void testStartX() {
		String s = "a2 a4";
		assertEquals(0, Cella.startX("a2 a4"));
	}

	@Test
	void testStartY() {
		String s = "a2 a4";
		assertEquals(6, Cella.startY("a2 a4"));
	}

	@Test
	void testEndX() {
		String s = "a2 a4";
		assertEquals(0, Cella.endX("a2 a4"));
	}

	@Test
	void testEndY() {
		String s = "a2 a4";
		assertEquals(4, Cella.endY("a2 a4"));
	}

}
