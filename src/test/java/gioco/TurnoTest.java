package gioco;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TurnoTest {

	@BeforeAll
	static void testTurno() {
		
		Turno.newTurno();
	}
	
	@Test
	void testSetGiocatoreInTurno() {
		Giocatore g = new Giocatore(Colore.bianco);
		Turno.setGiocatoreInTurno(g);
		assertEquals(Turno.getGiocatoreInTurno().getColore(), Colore.bianco);
	}
	
	@Test
	void testSetGiocatoreInAttesa() {
		Giocatore g = new Giocatore(Colore.nero);
		Turno.setGiocatoreInAttesa(g);
		assertEquals(Turno.getGiocatoreInAttesa().getColore(), Colore.nero);
	}

	@Test
	void testGetGiocatoreInTurno() {
		assertEquals(Turno.getGiocatoreInTurno().getColore(), Colore.bianco);
	}
	
	@Test
	void testGetGiocatoreInAttesa() {
		assertEquals(Turno.getGiocatoreInAttesa().getColore(), Colore.nero);
	}
	
	@Test
	void testGetArrayStoriaMosse() {
		ArrayList<String> mosseGiocate = new ArrayList<String>();
		Turno.getGiocatoreInTurno().setMosseGiocate("a4");
		Turno.getGiocatoreInAttesa().setMosseGiocate("b5");
		mosseGiocate.add("a4");
		mosseGiocate.add("b5");
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
		Turno.cambioTurno();
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
		
		mosseGiocate.clear();
		Turno.newTurno();
		Turno.getGiocatoreInTurno().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
		Turno.cambioTurno();
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
		
		mosseGiocate.clear();
		Turno.newTurno();
		Turno.getGiocatoreInAttesa().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
		Turno.cambioTurno();
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
	}
}
