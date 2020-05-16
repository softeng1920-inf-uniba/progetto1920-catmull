package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import gioco.*;
import scacchiera.*;
import it.uniba.main.*;

import scacchiera.pedine.*;

class PedoneTest {
	Pezzo pezzoCorrente;
	Giocatore g1;
	Giocatore g2;
	Cella start;
	Cella end;
	
	@BeforeEach
	void setTests() {
		Scacchiera.nuovaScacchiera();
		Turno.newTurno();
		
	}
	
	@Test
	void testIsMossaValida() {
		System.out.println("Test isMossaValida");
		start = Scacchiera.getCella(0, 6);
		end = Scacchiera.getCella(0, 5);
		if(Scacchiera.getNomePezzo(0, 6)!= "Vuota") {
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
		
		else {
			System.out.println("Il pezzo non esiste");
		}
		
		start = Scacchiera.getCella(0, 6);
		end = Scacchiera.getCella(0, 4);
		if(Scacchiera.getNomePezzo(0, 6)!= "Vuota") {
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		} 
		
		else {
			System.out.println("Il pezzo non esiste");
		}
		
		start = Scacchiera.getCella(0, 6);
		end = Scacchiera.getCella(1, 5);
		if(Scacchiera.getNomePezzo(0, 6)!= "Vuota") {
			Scacchiera.getCella(1, 5).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
		
		else {
			System.out.println("Il pezzo non esiste");
		}
		//Test su neri
		Turno.cambioTurno();
		//mossa avanti di uno
		start = Scacchiera.getCella(0, 1);
		end = Scacchiera.getCella(0, 2);
		if(Scacchiera.getNomePezzo(0, 1)!= "Vuota") {
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
		else {
			System.out.println("Il pezzo non esiste");
		}
		
		//mossa avanti di più di due celle
		start = Scacchiera.getCella(0, 1);
		end = Scacchiera.getCella(0, 4);
		if(Scacchiera.getNomePezzo(0, 1)!= "Vuota") {
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertFalse(pezzoCorrente.isMossaValida(start, end));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		//mossa indietro di una cella
		start = Scacchiera.getCella(0, 1);
		end = Scacchiera.getCella(0, 0);
		if(Scacchiera.getNomePezzo(0, 1)!= "Vuota") {
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertFalse(pezzoCorrente.isMossaValida(start, end));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		//mossa avanti di due cella
		start = Scacchiera.getCella(3, 1);
		end = Scacchiera.getCella(3, 3);
		if(Scacchiera.getNomePezzo(3, 1)!= "Vuota") {
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		//mossa cella end occupata colore opposto
		start = Scacchiera.getCella(3, 1);
		end = Scacchiera.getCella(4, 2);
		if(Scacchiera.getNomePezzo(3, 1)!= "Vuota") {
			end.aggiungiPezzo(Scacchiera.getCella(0,  6).getPezzoCorrente());
			pezzoCorrente = start.getPezzoCorrente();
			Pedone p = (Pedone)pezzoCorrente;
			assertTrue(p.isMossaValida(start, end));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		//mossa obliqua di una cella
		start = Scacchiera.getCella(3, 1);
		end = Scacchiera.getCella(4, 2);
		if(Scacchiera.getNomePezzo(3, 1)!= "Vuota") {
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertTrue(pezzoCorrente.isMossaValida(start, end));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		//mossa avanti di due celle con cella finale occupata
		start = Scacchiera.getCella(3, 1);
		end = Scacchiera.getCella(3, 3);
		if(Scacchiera.getNomePezzo(3, 1)!= "Vuota") {
			Scacchiera.getCella(3, 3).aggiungiPezzo(Scacchiera.getCella(2,  1).getPezzoCorrente());
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertFalse(pezzoCorrente.isMossaValida(start, end));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		start = Scacchiera.getCella(3, 1);
		end = Scacchiera.getCella(3, 2);
		if(Scacchiera.getNomePezzo(3, 1)!= "Vuota") {
			Scacchiera.getCella(3, 2).aggiungiPezzo(Scacchiera.getCella(2,  1).getPezzoCorrente());
			pezzoCorrente = (Pedone)start.getPezzoCorrente();
			assertFalse(pezzoCorrente.isMossaValida(start, end));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		
		
	}
	
	@Test
	void testConvertiMossa() {
		System.out.println("Test convertiMossa");
		Turno.cambioTurno(); //cambio turno perchè test sono effettuati sulle mosse del nero
		
		//mossa su cella start = cella end
		assertEquals(Pedone.convertiMossa("a7"), "a0 a0");
		
		//mossa su cella start != cella end
		assertEquals(Pedone.convertiMossa("a6"), "a7 a6");
		
		assertEquals(Pedone.convertiMossa("a5"), "a7 a5");
		 
		//cattura pedone bianco
		start = Scacchiera.getCella(2, 1);
		pezzoCorrente = start.getPezzoCorrente();
		assertEquals("c7 b6", Pedone.convertiMossa("cxb6"));
		
		//Comando en passant in input
		Turno.cambioTurno();
		Scacchiera.getCella(2, 3).aggiungiPezzo(Scacchiera.getCella(0,  6).getPezzoCorrente());
		assertEquals("c5 d6", Pedone.convertiMossa("cxd6 e.p."));
		
		assertEquals(Pedone.convertiMossa("g3"), "g2 g3");
		assertEquals(Pedone.convertiMossa("g4"), "g2 g4");
	}
	
	@Test
	void testIsEnPassantValido(){
		System.out.println("Test isEnPassantValido");
		ArrayList<String> mosseGiocate = new ArrayList<String>();
		Scacchiera.getCella(1, 3).aggiungiPezzo(Scacchiera.getCella(1, 6).getPezzoCorrente());
		Scacchiera.getCella(1, 6).rimuoviPezzoCorrente();
		Scacchiera.getCella(0, 3).aggiungiPezzo(Scacchiera.getCella(0, 1).getPezzoCorrente());
		Scacchiera.getCella(0, 1).rimuoviPezzoCorrente();
		start = Scacchiera.getCella(1, 3);
		end = Scacchiera.getCella(0, 2);
		
		//Pezzo da catturare si è mosso di due per la prima volta
		mosseGiocate.add("b2 b4");
		mosseGiocate.add("h7 h5");
		mosseGiocate.add("b4 b5");
		mosseGiocate.add("a7 a5");
		if(Scacchiera.getNomePezzo(1, 3)!= "Vuota") {
			Pedone p = (Pedone)start.getPezzoCorrente();
			assertTrue(p.isEnPassantValido(start, end, mosseGiocate));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		//Pezzo da catturare non si è mosso di due, ma ha fatto due mosse da uno
		mosseGiocate.clear();
		mosseGiocate.add("b2 b4");
		mosseGiocate.add("a7 a6");
		mosseGiocate.add("b4 b5");
		mosseGiocate.add("a6 a5");
		if(Scacchiera.getNomePezzo(1, 3)!= "Vuota") {
			Pedone p = (Pedone)start.getPezzoCorrente();
			assertFalse(p.isEnPassantValido(start, end, mosseGiocate));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		//Pezzo da catturare si è spostato di due prima del pezzo che lo cattura
		mosseGiocate.clear();
		mosseGiocate.add("b2 b4");
		mosseGiocate.add("a7 a5");
		mosseGiocate.add("b4 b5");
		mosseGiocate.add("h7 h5");
		if(Scacchiera.getNomePezzo(1, 3)!= "Vuota") {
			Pedone p = (Pedone)start.getPezzoCorrente();
			assertFalse(p.isEnPassantValido(start, end, mosseGiocate));
		}
		else {
			fail("Il pezzo non esiste");
		}
		
		
		// da rivedere perche deve essere true
		Turno.cambioTurno();
		mosseGiocate.clear();
		mosseGiocate.add("a7 a5");
		mosseGiocate.add("h2 h4");
		mosseGiocate.add("a5 a4");
		mosseGiocate.add("b2 b4");
		Scacchiera.getCella(0, 4).aggiungiPezzo(Scacchiera.getCella(6, 1).getPezzoCorrente());
		Scacchiera.getCella(1, 4).aggiungiPezzo(Scacchiera.getCella(6, 6).getPezzoCorrente());
		start = Scacchiera.getCella(0, 4);
		
		end = Scacchiera.getCella(1, 5);
		if(Scacchiera.getNomePezzo(0, 4)!= "Vuota") {
			Pedone p = (Pedone)start.getPezzoCorrente();
			assertTrue(p.isEnPassantValido(start, end, mosseGiocate));
		}
		else { 
			fail("Il pezzo non esiste");
		}
	}
	
//	private boolean isPedone(Cella c) {
//
//		return c.isOccupato() && c.getPezzoCorrente().getNome().equals(this.nome);
//	}
	
	@Test
	void testIsPedone() {
		Pedone p = (Pedone) Scacchiera.getCella(0, 1).getPezzoCorrente();
		assertTrue(p.isPedone(Scacchiera.getCella(0, 1)));
		
	}

	
}
