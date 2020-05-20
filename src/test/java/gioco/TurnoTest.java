package gioco;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnoTest {
	ArrayList<String> mosseGiocate = new ArrayList<String>();
	
	@BeforeEach
	void testTurno() {
		
		Turno.newTurno();
	}
	
	@Test
	void testSetNomeGiocatoreInTurno() {
		String nome = "Giocatore 1";
		Turno.setNomeGiocatoreInTurno(nome);
		assertEquals("Giocatore 1", Turno.getGiocatoreInTurno().getNome());
	}
	
	@Test
	void testSetNomeGiocatoreInAttesa() {
		String nome = "Giocatore 2";
		Turno.setNomeGiocatoreInAttesa(nome);
		assertEquals("Giocatore 2", Turno.getGiocatoreInAttesa().getNome());

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

	}
	
	@Test
	void testGetArrayStoriaMosseNero() {
		mosseGiocate.clear();
		Turno.getGiocatoreInTurno().setMosseGiocate("a4");
		Turno.getGiocatoreInAttesa().setMosseGiocate("b5");
		mosseGiocate.add("a4");
		mosseGiocate.add("b5");
		Turno.cambioTurno();
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseBiancoDim1() {
		Turno.getGiocatoreInTurno().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseNeroDim1() {
		Turno.getGiocatoreInTurno().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		Turno.cambioTurno();
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseAttesaDim1Bianco() {
		mosseGiocate.clear();
		Turno.getGiocatoreInAttesa().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseAttesaDim1Nero() {
		mosseGiocate.clear();
		Turno.getGiocatoreInAttesa().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		Turno.cambioTurno();
		assertEquals(Turno.getArrayStoriaMosse(), mosseGiocate);
	}
	
}
