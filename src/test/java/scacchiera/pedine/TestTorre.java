package scacchiera.pedine;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gioco.Colore;
import gioco.Turno;
import scacchiera.Scacchiera;


class TestTorre {

	Torre t;

	@BeforeEach
	void setUp() {
		Scacchiera.getInstance();
		Scacchiera.getInstance().inizializzaScacchiera();
		Turno.getInstance();
		Turno.getInstance().inizializzaTurno();
		t =(Torre) Scacchiera.getInstance().getCella(7, 7).getPezzoCorrente();
	}

	@Test
	void testIsMossaValidaMangiataSeStesso() {
		assertFalse(t.isMossaValida(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(7, 7)));
	}
	@Test
	void testIsMossaValidaMovimentoNonValido() {
		assertFalse(t.isMossaValida(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(5, 7)));
	}
	@Test
	void testConvertiMossaAmbiguitaAdL() {
		/* Inizio TEST AMBIGUITA' AD L CASO IN OSSERVAZIONE :
		    -Con la x è dove voglio far spostare una delle due Torri
		    -Predispongo la scacchiera come nella rappresentazione sottostante
		    
		  Caso Iniziale				  Caso Finale 	
		  
		  3 |T| | | | | | |x|		  3 |T| | | | | | |T|
		  2 | |P|P|P|P|P|P| |		  2 | |P|P|P|P|P|P| |
		  1 | |C|A|R|D|A|C|T|		  1 | |C|A|R|D|A|C| |
		     a b c d e f g h		     a b c d e f g h
		*/
		
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 7),Scacchiera.getInstance().getCella(0, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 5),Scacchiera.getInstance().getCella(7, 6));
		assertEquals("a0 a0",Torre.convertiMossa("Th3"));
		assertEquals("h1 h3",Torre.convertiMossa("T1h3"));
		assertEquals("h1 h3",Torre.convertiMossa("Thh3"));
		assertEquals("a3 h3",Torre.convertiMossa("Tah3"));
		assertEquals("a3 h3",Torre.convertiMossa("T3h3"));
	}
	@Test
	void testConvertiMossaAmbiguitaRiga() {
	/*  Inizio TEST AMBIGUITA' TORRE RIGA
    	-Predispongo la scacchiera come nella rappresentazione sottostante
    	-Indico con x ove voglio spostare una delle due torri
  		Caso Iniziale				Caso Finale
  
  		3 |T| | |x| | | |T| 		3 |T| | |T| | | | |    
  		2 |P|P|P|P|P|P|P|P|   		2 |P|P|P|P|P|P|P|P|
  		1 | |C|A|R|D|A|C| |   		1 | |C|A|R|D|A|C| |
       	   a b c d e f g h       	   a b c d e f g h 
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 7),Scacchiera.getInstance().getCella(0, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(7, 5));
		assertEquals("a0 a0",Torre.convertiMossa("Td3"));
		assertEquals("a3 d3",Torre.convertiMossa("Tad3"));
		assertEquals("h3 d3",Torre.convertiMossa("Thd3"));
	}
	@Test
	void testConvertiMossaAmbiguitaRigaConMangiata() {
	/*  Inizio TEST AMBIGUITA' TORRE RIGA CON MANGIATA
    	-Predispongo la scacchiera come nella rappresentazione sottostante
    	-Indico con x ove voglio spostare una delle due torri
  		Caso Iniziale				Caso Finale
  
  		3 |T| | |x|p| | |T| 		3 |T| | | |p| | |T|    
  		2 |P|P|P|P|P|P|P|P|   		2 |P|P|P|P|P|P|P|P|
  		1 | |C|A|R|D|A|C| |   		1 | |C|A|R|D|A|C| |
       	   a b c d e f g h       	   a b c d e f g h 
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 7),Scacchiera.getInstance().getCella(0, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(7, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(4, 1),Scacchiera.getInstance().getCella(4, 5));
		assertEquals("a0 a0",Torre.convertiMossa("Txd3"));
		assertEquals("a0 a0",Torre.convertiMossa("Te3"));
		assertEquals("a0 a0",Torre.convertiMossa("Tae3"));
		assertEquals("a3 e3",Torre.convertiMossa("Taxe3"));
		
	}
	@Test
	void testConvertiMossaTorriStessaRigaConMangiata() {
	/*  Inizio TEST AMBIGUITA' TORRE RIGA CON MANGIATA
    	-Predispongo la scacchiera come nella rappresentazione sottostante
    	-Indico con x ove voglio spostare una delle due torri
  		Caso Iniziale				Caso Finale
  
  		3 |T| | |T| | | |p| 		3 |T| | | | | | |T|    
  		2 |P|P|P|P|P|P|P|P|   		2 |P|P|P|P|P|P|P|P|
  		1 | |C|A|R|D|A|C| |   		1 | |C|A|R|D|A|C| |
       	   a b c d e f g h       	   a b c d e f g h 
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 7),Scacchiera.getInstance().getCella(0, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(3, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 1),Scacchiera.getInstance().getCella(7, 5));
		assertEquals("a0 a0",Torre.convertiMossa("Taxh3"));
		assertEquals("a0 a0",Torre.convertiMossa("Th3"));
		assertEquals("d3 h3",Torre.convertiMossa("Txh3"));
	}
	@Test
	void testConvertiMossaAmbiguitaColonna() {
	/*  Inizio TEST AMBIGUITA' TORRE COLONNA
    	-Predispongo la scacchiera come nella rappresentazione sottostante
    	-Indico con x ove voglio spostare una delle due torri
  		Caso Iniziale				Caso Finale
  		6 |T| | | | | | | |			6 | | | | | | | | |
  		5 | | | | | | | | |			5 | | | | | | | | |  		
  		4 | | | | | | | | |			4 |T| | | | | | | |
  		3 |T| | | | | | | | 		3 |T| | | | | | | |    
  		2 |P|P|P|P|P|P|P|P|   		2 |P|P|P|P|P|P|P|P|
  		1 | |C|A|R|D|A|C| |   		1 | |C|A|R|D|A|C| |
       	   a b c d e f g h       	   a b c d e f g h 
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 7),Scacchiera.getInstance().getCella(0, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(0, 2));
		assertEquals("a0 a0", Torre.convertiMossa("Ta4"));
		assertEquals("a6 a4", Torre.convertiMossa("T6a4"));
		

	}
	@Test
	void testConvertiMossaAmbiguitaColonnaConMangiata() {
	/*  Inizio TEST AMBIGUITA' TORRE COLONNA
    	-Predispongo la scacchiera come nella rappresentazione sottostante
    	-Indico con x ove voglio spostare una delle due torri
  		Caso Iniziale				Caso Finale
  		6 |T| | | | | | | |			6 | | | | | | | | |
  		5 | | | | | | | | |			5 | | | | | | | | |  		
  		4 |p| | | | | | | |			4 |T| | | | | | | |
  		3 |T| | | | | | | | 		3 |T| | | | | | | |    
  		2 |P|P|P|P|P|P|P|P|   		2 |P|P|P|P|P|P|P|P|
  		1 | |C|A|R|D|A|C| |   		1 | |C|A|R|D|A|C| |
       	   a b c d e f g h       	   a b c d e f g h 
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 7),Scacchiera.getInstance().getCella(0, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(0, 2));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 1),Scacchiera.getInstance().getCella(0, 4));
		assertEquals("a0 a0", Torre.convertiMossa("Ta4"));
		assertEquals("a0 a0", Torre.convertiMossa("T6a4"));
		assertEquals("a6 a4", Torre.convertiMossa("T6xa4"));
	}
	@Test
	void testConvertiMossaTorriStessaColonnaConMangiata() {
	/*  Inizio TEST AMBIGUITA' TORRE COLONNA
    	-Predispongo la scacchiera come nella rappresentazione sottostante
    	-Indico con x ove voglio spostare una delle due torri
  		Caso Iniziale				Caso Finale
  		7 |p| | | | | | | |			7 |T| | | | | | | |
  		6 | | | | | | | | |			6 | | | | | | | | |
  		5 |T| | | | | | | |			5 | | | | | | | | |  		
  		4 | | | | | | | | |			4 | | | | | | | | |
  		3 |T| | | | | | | | 		3 |T| | | | | | | |    
  		2 |P|P|P|P|P|P|P|P|   		2 |P|P|P|P|P|P|P|P|
  		1 | |C|A|R|D|A|C| |   		1 | |C|A|R|D|A|C| |
       	   a b c d e f g h       	   a b c d e f g h 
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 7),Scacchiera.getInstance().getCella(0, 5));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(0, 3));
		assertEquals("a0 a0", Torre.convertiMossa("Ta4"));
		assertEquals("a0 a0", Torre.convertiMossa("T3xa7"));
		assertEquals("a0 a0", Torre.convertiMossa("T5xa7"));
		assertEquals("a5 a7", Torre.convertiMossa("Txa7"));
	}
	@Test
	void testMovimentoPezziAvversariConTurnoDifferente() {
	/*  Inizio TEST MOVIMENTO PEZZI AVVERSARI
    	-Predispongo la scacchiera come nella rappresentazione sottostante
    	-Indico con x ove voglio spostare una delle due torri
  		Caso Iniziale e Finale
  		8 |t|c|a|d|r|a|c|t|		
  		7 |p|p|p|p|p|p|p|p|		
  		6 | | | | | | | | |		
  		5 | | | | | | | | |		  		
  		4 | | | | | | | | |		
  		3 | | | | | | | | | 	    
  		2 | |P|P|P|P|P|P|P|   	
  		1 |T|C|A|D|R|A|C|T|   	
       	   a b c d e f g h       	   
	*/
		
		Turno.getInstance().cambioTurno();
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 5),Scacchiera.getInstance().getCella(0, 6));
		assertEquals("a0 a0",Torre.convertiMossa("Ta2"));
	}
	@Test
	void testMangiataFittizia() {
	/*  Inizio TEST MANGIATA SU SE STESSO:
    	-Predispongo la scacchiera come nella rappresentazione sottostante
  		Caso Iniziale e Finale
  
  		3 | | |p| | | | | |
  		2 | | |P|P|P|P|P|P|
  		1 |T|C|A|R|D|A|C|T|
       	   a b c d e f g h
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 5),Scacchiera.getInstance().getCella(0, 6));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(1, 5),Scacchiera.getInstance().getCella(1, 6));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(2, 1),Scacchiera.getInstance().getCella(2, 5));
		assertEquals("a0 a0",Torre.convertiMossa("Txc3"));
	}

	@Test
	void testSeStessoMangiata() {
		/*  Inizio TEST MANGIATA SU SE STESSO:
	    	-Predispongo la scacchiera come nella rappresentazione sottostante
	  		Caso Iniziale e Finale
	  
	  		3 | | | | | | | | |
	  		2 |P|P|P|P|P|P|P|P|
	  		1 |T|C|A|R|D|A|C|T|
	       	   a b c d e f g h
		*/
		assertEquals("a0 a0",Torre.convertiMossa("Txa1"));
	}

	@Test
	void testSpostamentoSinistra() {
	/*  Inizio TEST SPOSTAMENTO TORRE A SX
    	-Predispongo la scacchiera come nella rappresentazione sottostante
  		Caso Iniziale				Caso Intermedio				Caso Finale
  
  		3 | | | | | | | | |  		3 | | | | | | | |T|  		3 | | | |T| | | | |    
  		2 |P|P|P|P|P|P|P|P|  		2 |P|P|P|P|P|P|P|P|  		2 |P|P|P|P|P|P|P|P|
  		1 |T|C|A|R|D|A|C|T|  		1 |T|C|A|R|D|A|C| |  		1 |T|C|A|R|D|A|C| |
       	   a b c d e f g h       	   a b c d e f g h       	   a b c d e f g h
	*/
	
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(7, 5));
		assertEquals("h3 d3",Torre.convertiMossa("Td3"));
	}
	@Test
	void testSpostamentoDestra() {
		/*  Inizio TEST SPOSTAMENTO TORRE A DX
    	-Predispongo la scacchiera come nella rappresentazione sottostante
  		Caso Iniziale				Caso Intermedio				Caso Finale
  
  		3 | | | | | | | | |  		3 |T| | | | | | | |  		3 | | | |T| | | | |    
  		2 |P|P|P|P|P|P|P|P|  		2 |P|P|P|P|P|P|P|P|  		2 |P|P|P|P|P|P|P|P|
  		1 |T|C|A|R|D|A|C|T|  		1 | |C|A|R|D|A|C|T|  		1 | |C|A|R|D|A|C|T|
       	   a b c d e f g h       	   a b c d e f g h       	   a b c d e f g h
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(7, 5));
		assertEquals("h3 d3",Torre.convertiMossa("Td3"));
	}
	@Test
	void testSpostamentoSxDxConPezziAmici() {
		/*  Inizio TEST SPOSTAMENTO TORRE A SX CON PEZZI AMICI:
    	-Predispongo la scacchiera come nella rappresentazione sottostante
  		Caso Iniziale e Finale
  
  		3 | | | | | | | | |
  		2 |P|P|P|P|P|P|P|P|
  		1 |T|C|A|R|D|A|C|T|
       	   a b c d e f g h
	*/
		
		assertEquals("a0 a0",Torre.convertiMossa("Td1"));
		assertEquals("a0 a0",Torre.convertiMossa("Te1"));
	}
	@Test
	void testSpostamentoAlto() {
		/*  Inizio TEST SPOSTAMENTO VERSO L'ALTO:
    	-Predispongo la scacchiera come nella rappresentazione sottostante
  		Caso Iniziale				Caso Intermedio				Caso Finale
  		3 | | | | | | | | |  		3 | | | | | | | | |  		3 |T| | | | | | | |    
  		2 |P|P|P|P|P|P|P|P|  		2 | |P|P|P|P|P|P|P|  		2 | |P|P|P|P|P|P|P|
  		1 |T|C|A|R|D|A|C|T|  		1 |T|C|A|R|D|A|C|T|  		1 | |C|A|R|D|A|C|T|
       	   a b c d e f g h       	   a b c d e f g h       	   a b c d e f g h
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(0, 5),Scacchiera.getInstance().getCella(0, 6));
		assertEquals("a1 a3",Torre.convertiMossa("Ta3"));
	}
	@Test
	void testSpostamentoAltoConPezziAmici() {
		/*  Inizio TEST SPOSTAMENTO VERSO L'ALTO CON PEZZI DELLO STESSO COLORE
    	-Predispongo la scacchiera come nella rappresentazione sottostante
  		Caso Iniziale e Finale
  
  		3 | | | | | | | | |
  		2 |P|P|P|P|P|P|P|P|
  		1 |T|C|A|R|D|A|C|T|
       	   a b c d e f g h
	*/
		assertEquals("a0 a0",Torre.convertiMossa("Ta3"));
	}
	@Test
	void testSpostamentoBasso() {
		/*  Inizio TEST SPOSTAMENTO TORRE VERSO IL BASSO:
    	-Predispongo la scacchiera come nella rappresentazione sottostante
  		Caso Iniziale				Caso Finale			
  		4 | | | | | | | |T| 		4 | | | | | | | | |	
  		3 | | | | | | | | |  		3 | | | | | | | | |    
  		2 |P|P|P|P|P|P|P| |  		2 |P|P|P|P|P|P|P| |
  		1 |T|C|A|R|D|A|C| | 		1 |T|C|A|R|D|A|C|T|
       	   a b c d e f g h       	   a b c d e f g h
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(7, 4));
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 5),Scacchiera.getInstance().getCella(7, 6));
		assertEquals("h4 h1",Torre.convertiMossa("Th1"));
	}
	@Test
	void testSpostamentoBassoConPezziAmici() {
	/*  Inizio TEST SPOSTAMENTO TORRE VERSO IL BASSO CON PEZZI AMICI:
    	-Predispongo la scacchiera come nella rappresentazione sottostante
  		Caso Iniziale e Finale			
  		4 | | | | | | | |T| 	
  		3 | | | | | | | | |     
  		2 |P|P|P|P|P|P|P|P| 
  		1 |T|C|A|R|D|A|C| | 
       	   a b c d e f g h 
	*/
		Scacchiera.getInstance().scambiaCella(Scacchiera.getInstance().getCella(7, 7),Scacchiera.getInstance().getCella(7, 4));
		assertEquals("a0 a0",Torre.convertiMossa("Th1"));
	}
	@Test
	void testGetCoordinateArrocco() {
		assertNotEquals("h1 f1", Torre.getCoordinateArrocco(2, Colore.bianco));
		assertEquals("h1 f1", Torre.getCoordinateArrocco(1, Colore.bianco));
		assertEquals("h8 f8", Torre.getCoordinateArrocco(1, Colore.nero));
		assertEquals("a8 d8", Torre.getCoordinateArrocco(2, Colore.nero));
	}
	

}
