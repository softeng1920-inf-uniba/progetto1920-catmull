package scacchiera.pedine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;

class CavalloTest {
	Pezzo pezzoCorrente;
	Cavallo c;
	Cella start;
	Cella end;

	@BeforeEach
	void setTests() {
		Scacchiera.nuovaScacchiera();
		Turno.newTurno();
		
	}

    @Test
    void testIsMossaValida() {
    	Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
    	Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
    	start = Scacchiera.getCella(3, 3);
    	c = (Cavallo)start.getPezzoCorrente();
    	
    	//Cella di partenza d3
    	//Cattura in c7
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(2, 1)));
        
        //Cattura in e7
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(4, 1)));
        
        //Cattura in b6
        Scacchiera.getCella(1, 2).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(1, 2)));
        
        //Cattura in b4
        Scacchiera.getCella(1, 4).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(1, 4)));
        
        //Cattura in c3
        Scacchiera.getCella(2, 5).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(2, 5)));
        
        //Cattura in e3
        Scacchiera.getCella(4, 5).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(4, 5)));
        
        //Cattura in f4
        Scacchiera.getCella(5, 4).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(5, 4)));
        
        //Cattura in f6
        Scacchiera.getCella(5, 2).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(5, 2)));
        
        Scacchiera.nuovaScacchiera();
        //mossa in e7
        Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(4, 1)));
        
        //mossa in c7
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(2, 1)));
         
        //mossa in b6
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(1, 2)));
        
        //mossa in b4
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(1, 4)));
        
        //mossa in c3
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(2, 5)));
        
        //mossa in e3
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(4, 5)));
        
        //mossa in f4
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(5, 4)));
        
        //mossa in f6
        assertTrue(c.isMossaValida(start, Scacchiera.getCella(5, 2)));
        
        Scacchiera.nuovaScacchiera();
        assertFalse(c.isMossaValida(Scacchiera.getCella(5, 5), Scacchiera.getCella(4, 1)));
        Scacchiera.getCella(5, 5).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(6, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        assertFalse(c.isMossaValida(Scacchiera.getCella(5, 5), Scacchiera.getCella(6, 3)));
         
    }

 

    @Test
    void testConvertiMossa() {
        Scacchiera.getCella(4, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(2, 1).rimuoviPezzoCorrente();
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 e7", Cavallo.convertiMossa("Ce7"));
        assertEquals("d5 c7", Cavallo.convertiMossa("Cc7"));
        assertEquals("d5 b6", Cavallo.convertiMossa("Cb6"));
        assertEquals("d5 b4", Cavallo.convertiMossa("Cb4")); 
        assertEquals("d5 c3", Cavallo.convertiMossa("Cc3"));
        assertEquals("d5 e3", Cavallo.convertiMossa("Ce3"));
        assertEquals("d5 f4", Cavallo.convertiMossa("Cf4"));
        assertEquals("d5 f6", Cavallo.convertiMossa("Cf6"));
        
        Scacchiera.nuovaScacchiera(); 
        assertNotEquals("d5 e7", Cavallo.convertiMossa("Ce7"));
        assertNotEquals("d5 e7", Cavallo.convertiMossa("Cde7"));
        assertNotEquals("d5 e7", Cavallo.convertiMossa("C5e7"));
        
         
        Scacchiera.nuovaScacchiera(); 
        Scacchiera.getCella(1, 2).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        Scacchiera.getCella(1, 4).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        Scacchiera.getCella(2, 5).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        Scacchiera.getCella(4, 5).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        Scacchiera.getCella(5, 4).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());     
        Scacchiera.getCella(5, 2).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        Scacchiera.getCella(1, 7).rimuoviPezzoCorrente();
        Scacchiera.getCella(6, 7).rimuoviPezzoCorrente();
        assertEquals("d5 e7", Cavallo.convertiMossa("Cxe7"));
        assertEquals("d5 c7", Cavallo.convertiMossa("Cxc7"));
        assertEquals("d5 b6", Cavallo.convertiMossa("Cxb6"));
        assertEquals("d5 b4", Cavallo.convertiMossa("Cxb4")); 
        assertEquals("d5 c3", Cavallo.convertiMossa("Cxc3"));
        assertEquals("d5 e3", Cavallo.convertiMossa("Cxe3"));
        assertEquals("d5 f4", Cavallo.convertiMossa("Cxf4"));
        assertEquals("d5 f6", Cavallo.convertiMossa("Cxf6"));
        
        //Bianco
        Scacchiera.nuovaScacchiera(); 
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        assertEquals("d5 c3", Cavallo.convertiMossa("C5c3")); 
        Scacchiera.getCella(4, 3).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        assertEquals("e5 f3", Cavallo.convertiMossa("Cef3"));  
        Scacchiera.getCella(3, 4).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        assertEquals("d4 f3", Cavallo.convertiMossa("Cdf3")); 
        Scacchiera.getCella(5, 4).aggiungiPezzo(Scacchiera.getCella(1, 7).getPezzoCorrente());
        assertEquals("f4 h3", Cavallo.convertiMossa("C4h3"));
        assertEquals("d4 c6", Cavallo.convertiMossa("Cdc6"));
        
        Scacchiera.nuovaScacchiera(); 
        assertNotEquals("a1 c3", Cavallo.convertiMossa("Cxc3"));
        
        //Nero
        Turno.cambioTurno(); 
        Scacchiera.nuovaScacchiera(); 
        Scacchiera.getCella(2, 2).aggiungiPezzo(Scacchiera.getCella(0, 6).getPezzoCorrente());
        Scacchiera.getCella(5, 2).aggiungiPezzo(Scacchiera.getCella(0, 6).getPezzoCorrente());
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 0).getPezzoCorrente());
        assertEquals("b8 c6", Cavallo.convertiMossa("C8xc6")); 
        Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(1, 0).getPezzoCorrente());
        assertEquals("d5 f6", Cavallo.convertiMossa("Cdxf6"));
        
        Scacchiera.nuovaScacchiera(); 
        Scacchiera.getCella(3, 4).aggiungiPezzo(Scacchiera.getCella(1, 0).getPezzoCorrente());
        assertEquals("d4 c6", Cavallo.convertiMossa("Cdc6"));
        Scacchiera.getCella(4, 2).aggiungiPezzo(Scacchiera.getCella(1, 0).getPezzoCorrente());
        Scacchiera.getCella(4, 4).aggiungiPezzo(Scacchiera.getCella(1, 0).getPezzoCorrente());
        assertEquals("e6 c5", Cavallo.convertiMossa("C6c5"));
        
        Scacchiera.nuovaScacchiera(); 
        Scacchiera.getCella(6, 4).aggiungiPezzo(Scacchiera.getCella(1, 0).getPezzoCorrente());
        assertNotEquals("g4 f6", Cavallo.convertiMossa("C4xf6"));
        assertNotEquals("d5 f6", Cavallo.convertiMossa("Cdxf6"));
    }

   

}
 