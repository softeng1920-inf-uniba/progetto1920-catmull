package it.uniba.main;

import gioco.*;
import scacchiera.*;
import pedine.*;

public class Stampa {

	public Stampa() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Stampa a video il simbolo del pezzo in input
	 * 
	 * @param p
	 */
	private static void disegnaPezzo(Pezzo p) {
		System.out.print(p.getSimbolo());
	}

	/**
	 * Stampa a video il pezzo nella cella corrente
	 * 
	 * @param pezzo
	 */
	private static void stampaPezzo(Cella pezzo) {
		if (pezzo.getPezzoCorrente() != null) {
			System.out.print(" ");
			disegnaPezzo(pezzo.getPezzoCorrente());
		} else {
			System.out.print("  ");
		}

	}

	/**
	 * stampa nella console la scacchiera
	 */
	public static final void stampaScacchiera() {
		System.out.println("      a     b     c     d     e     f     g     h");
		for (int j = 8; j > 0; j--) {
			System.out.println("  --------------------------------------------------");
			System.out.print(j + "  ");
			for (int i = 0; i < 8; i++) {
				System.out.print("| ");
				stampaPezzo(Scacchiera.getCella(i, Math.abs(j - 8)));
				System.out.print("  ");
			}
			System.out.print("|");
			System.out.println("  " + j);
		}
		System.out.println("  --------------------------------------------------");
		System.out.println("      a     b     c     d     e     f     g     h");
	}

	/**
	 * stampa a video il messaggio di introduzione del gioco
	 */
	public static void stampaIntroPlay() {
		System.out.println("Benvenuto nel gioco degli scacchi");
		System.out.println("\n\u2022" + " Digita 'Menu' per tornare al menu principale.");
		System.out.println("\u2022" + " Digita 'Help' per visualizzare l'elenco dei comandi.");
	}

	/**
	 * stampa a video il giocatore in turno
	 * @param g
	 */
	public static void stampaTurno(Giocatore g) {
		System.out.println("\nE' il turno di " + g.getNome() + " con le pedine di colore " + g.getColore() + ".");

		System.out.println(
				"-> Inserisci una mossa nella notazione algebrica (es. e4, Cxd3, exd3 e.p.); altrimenti digita una voce del menu.");
	}

	/**
	 * stampa a video i comandi
	 */
	public static void stampaComandi() {
		System.out.println();
		System.out.println("\u265A" + "\u265B" + "  Menu principale " + "\u2655" + "\u2656" + " \n");
		System.out.println("Digitare help per visualizzare la lista dei comandi");
		return;
	}

	/**
	 * stampa a video il messaggio illegale
	 */
	public static void stampaMossaIllegale() {
		System.out.println("Mossa Illegale");
	}

	/**
	 * stampa a video il messaggio comando errato
	 */
	public static void stampaComandoErrato() {
		System.out.println("Comando errato. Riprova!");
	}

	/**
	 * stampa a video il messaggio per iniziare una nuova partita
	 */
	public static void stampaNuovaPartita() {
		System.out.println(
				"Sei sicuro di voler iniziare una nuova partita? \n (Digita 'y' per confermare, 'n' altrimenti)");
	}
	
	/**
	 * stampa a video i pezzi catturati per ogni giocatore
	 * @param g
	 */
	private static void stampaPezziCatturati(Giocatore g) {

		System.out.println("Pezzi catturati dal giocatore " + g.getNome().toUpperCase() + ":");
		for (Pezzo pezzoMangiato : g.getPezziCatturati()) {
			System.out.println(pezzoMangiato);
		}
	}

	/**
	 * Mostra le catture di entrambi i giocatori
	 */
	public static void visualizzareCatture(Turno t) {

		Giocatore giocatoreAttivo = t.getGiocatoreInTurno();
		Giocatore giocatoreAttesa = t.getGiocatoreInAttesa();
		if (!giocatoreAttivo.isEmptyPezziCatturati() || !giocatoreAttesa.isEmptyPezziCatturati()) {
			if (!giocatoreAttivo.isEmptyPezziCatturati()) // Se il giocatore attivo ha catturato dei pezzi, li stampo
				stampaPezziCatturati(giocatoreAttivo);

			if (!giocatoreAttesa.isEmptyPezziCatturati()) // Se il giocatore in attesa ha catturato dei pezzi, li stampo
				stampaPezziCatturati(giocatoreAttesa);
		} else
			System.out.println("Non ci sono pezzi catturati da entrambi i giocatori.");
	}

	/**
	 * Stampa a video l'elenco delle mosse giocate da ogni giocatore
	 */
	public static void stampaMosseGiocate(Turno t) {
		String mossa = null;
		int counter = 1;
		int dimensione = t.getGiocatoreInAttesa().getNumeroMosseGiocate()
				+ t.getGiocatoreInTurno().getNumeroMosseGiocate();
		if (dimensione == 0) {
			System.out.println("Non e' stata giocata alcuna mossa");
		} else {
			System.out.println("Storia delle mosse giocate");
			for (int i = 0; i < dimensione; i++) {
				if (i == dimensione - 1) {
					mossa = counter + ". " + t.fusioneListe().get(i);
					System.out.println(mossa);
				} else {
					mossa = counter + ". " + t.fusioneListe().get(i) + " " + t.fusioneListe().get(i + 1);
					System.out.println(mossa);
				}
				i++;
				counter++;
			}
		}
	}

	/**
	 * Permette la visualizzazione dell' elenco dei comandi del menu principale
	 */
	public static void mostrareElencoComandiMenu() {
		System.out.println(Menu.quit().toString());
		System.out.println(Menu.play().toString());
		System.out.println(Menu.board().toString());
	}

	/**
	 * Permette la visualizzazione dell' elenco dei comandi del menu di gioco
	 */
	public static void mostrareElencoComandiGioco() {
		System.out.println(Menu.back().toString());
		System.out.println(Menu.play().toString());
		System.out.println(Menu.board().toString());
		System.out.println(Menu.captures().toString());
		System.out.println(Menu.moves().toString());
		System.out.println(Menu.quit().toString());
	}

	/**
	 * stampa a video il messaggio per l'inserimento del nome del giocatore
	 * @param c
	 */
	public static void stampaInserireGiocatore(Colore c) {
		System.out.println("\nInserisci il nome del giocatore con le pedine di colore " + c + " \u2193");
	}
	
	


}
