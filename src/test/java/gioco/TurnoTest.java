package gioco;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnoTest {
	ArrayList<String> mosseGiocate = new ArrayList<String>();
	
	@BeforeEach
	void testTurno() {
		
		Turno.getInstance().inizializzaTurno();
	}
	
	@Test
	void testSetNomeGiocatoreInTurno() {
		String nome = "Giocatore 1";
		Turno.getInstance().setNomeGiocatoreInTurno(nome);
		assertEquals("Giocatore 1", Turno.getInstance().getGiocatoreInTurno().getNome());
	}
	
	@Test
	void testSetNomeGiocatoreInAttesa() {
		String nome = "Giocatore 2";
		Turno.getInstance().setNomeGiocatoreInAttesa(nome);
		assertEquals("Giocatore 2", Turno.getInstance().getGiocatoreInAttesa().getNome());
	}

	@Test
	void testGetGiocatoreInTurno() {
		assertEquals(Turno.getInstance().getGiocatoreInTurno().getColore(), Colore.bianco);
	}
	
	@Test
	void testGetGiocatoreInAttesa() {
		assertEquals(Turno.getInstance().getGiocatoreInAttesa().getColore(), Colore.nero);
	}
	
	@Test
	void testGetArrayStoriaMosseNero() {
		mosseGiocate.clear();
		Turno.getInstance().getGiocatoreInTurno().setMosseGiocate("a4");
		Turno.getInstance().getGiocatoreInAttesa().setMosseGiocate("b5");
		mosseGiocate.add("a4");
		mosseGiocate.add("b5");
		Turno.getInstance().cambioTurno();
		assertEquals(Turno.getInstance().getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseBianco() {
		mosseGiocate.clear();
		Turno.getInstance().getGiocatoreInTurno().setMosseGiocate("a4");
		Turno.getInstance().getGiocatoreInAttesa().setMosseGiocate("b5");
		mosseGiocate.add("a4");
		mosseGiocate.add("b5");
		assertEquals(Turno.getInstance().getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseBiancoDim1() {
		Turno.getInstance().getGiocatoreInTurno().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		assertEquals(Turno.getInstance().getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseNeroDim1() {
		Turno.getInstance().getGiocatoreInTurno().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		Turno.getInstance().cambioTurno();
		assertEquals(Turno.getInstance().getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseAttesaDim1Bianco() {
		mosseGiocate.clear();
		Turno.getInstance().getGiocatoreInAttesa().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		assertEquals(Turno.getInstance().getArrayStoriaMosse(), mosseGiocate);
	}
	
	@Test
	void testGetArrayStoriaMosseAttesaDim1Nero() {
		mosseGiocate.clear();
		Turno.getInstance().getGiocatoreInAttesa().setMosseGiocate("a4");
		mosseGiocate.add("a4");
		Turno.getInstance().cambioTurno();
		assertEquals(Turno.getInstance().getArrayStoriaMosse(), mosseGiocate);
	}
	
}
