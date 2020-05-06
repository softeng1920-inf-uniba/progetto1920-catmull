package gioco;

import scacchiera.*;
import pedine.*;

/**
 * La classe Stampa contiene i messaggi stampati a video e i metodi di stampa
 * dell'intero gioco. Contiene anche informazioni riguardo ai colori utilizzati
 * nelle stampe.
 * La classe Stampa e' di tipo BOUNDARY.
 */

public class Stampa {
    // colore carattere e font
    private static final String CYAN_BOLD = "\033[1;96m";
    private static final String WHITE_BOLD_BRIGHT = "\033[1;97m";
    private static final String CYAN_UNDERLINED = "\033[4;96m";
    private static final String WHITE_UNDERLINED_BRIGHT = "\033[4;97m";
    private static final String CYAN = "\u001B[36m";

    // sfondo cella
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m"; // grigio
    private static final String ANSI_WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // bianco

    // reset sfondo e carattere a default
    private static final String ANSI_RESET = "\u001B[0m";

    // colore Intro
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     * Stampa a video il simbolo del pezzo in input
     * 
     * @param p
     */
    private static void disegnaPezzo(Pezzo p) {
	System.out.print("\033[1;30m" + p.getSimbolo());
    }

    /**
     * Stampa a video il pezzo nella cella corrente
     * 
     * @param cellaPezzo
     */
    private static void stampaPezzo(Cella cellaPezzo) {
	if (cellaPezzo.getPezzoCorrente() != null) {
	    System.out.print(" ");
	    disegnaPezzo(cellaPezzo.getPezzoCorrente());
	} else {
	    System.out.print("  ");
	}

    }

    /**
     * Stampa a video la scacchiera con i pezzi all'interno
     */
    public static final void stampaScacchiera() {
	boolean cost;
	System.out.println("      a   b    c    d    e    f    g    h");
	for (int j = 8; j > 0; j--) {
	    if (j % 2 == 0) {
		cost = false;
	    } else {
		cost = true;
	    }
	    System.out.print("   ");
	    for (int i = 0; i < 8; i++) {
		if (cost) {
		    if (i % 2 == 0) {
			System.out.print(ANSI_CYAN_BACKGROUND + " ");
		    } else {
			System.out.print(ANSI_WHITE_BACKGROUND_BRIGHT + " ");
		    }
		} else {
		    if (i % 2 == 0) {
			System.out.print(ANSI_WHITE_BACKGROUND_BRIGHT + " ");
		    } else {
			System.out.print(ANSI_CYAN_BACKGROUND + " ");
		    }
		}
		System.out.print("    ");
		System.out.print(ANSI_RESET);
	    }
	    System.out.println();
	    System.out.print(j + "  ");

	    for (int i = 0; i < 8; i++) {
		if (cost) {
		    if (i % 2 == 0) {
			System.out.print(ANSI_CYAN_BACKGROUND + " ");
		    } else {
			System.out.print(ANSI_WHITE_BACKGROUND_BRIGHT + " ");
		    }
		} else {
		    if (i % 2 == 0) {
			System.out.print(ANSI_WHITE_BACKGROUND_BRIGHT + " ");
		    } else {
			System.out.print(ANSI_CYAN_BACKGROUND + " ");
		    }
		}
		stampaPezzo(Scacchiera.getCella(i, Math.abs(j - 8)));
		System.out.print("  ");
		System.out.print(ANSI_RESET);
	    }
	    System.out.print(ANSI_RESET);
	    System.out.println("  " + j);
	    System.out.print("   ");
	    for (int i = 0; i < 8; i++) {
		if (cost) {
		    if (i % 2 == 0) {
			System.out.print(ANSI_CYAN_BACKGROUND + " ");
		    } else {
			System.out.print(ANSI_WHITE_BACKGROUND_BRIGHT + " ");
		    }
		} else {
		    if (i % 2 == 0) {
			System.out.print(ANSI_WHITE_BACKGROUND_BRIGHT + " ");
		    } else {
			System.out.print(ANSI_CYAN_BACKGROUND + " ");
		    }
		}
		System.out.print("    ");
		System.out.print(ANSI_RESET);
	    }

	    System.out.println();

	}
	System.out.println("      a   b    c    d    e    f    g    h");
    }

    /**
     * stampa introduttiva del gioco
     */
    public static void stampaIntro() {
	System.out.println(CYAN + "\n _/|" + ANSI_RESET + "    #" + CYAN + "#" + ANSI_RESET + "#" + CYAN + "#"
		+ ANSI_RESET + "#" + CYAN + "   #" + ANSI_RESET + "#" + CYAN + "#" + ANSI_RESET + "#" + CYAN + "#"
		+ ANSI_RESET + "     #" + CYAN + "     #" + ANSI_RESET + "#" + CYAN + "#" + ANSI_RESET + "#" + CYAN
		+ "#" + ANSI_RESET + "   #" + CYAN + "#" + ANSI_RESET + "#" + CYAN + "#" + ANSI_RESET + "#" + CYAN
		+ " #" + CYAN + "     #" + CYAN + " #" + ANSI_RESET + "#" + CYAN + "#" + CYAN + "    |\\_");
	System.out.println(CYAN + "// o\\" + CYAN + "   #" + ANSI_RESET + "      #" + CYAN + "        #" + CYAN + "   #"
		+ ANSI_RESET + "  #" + CYAN + "       #" + ANSI_RESET + "      #" + ANSI_RESET + "     #" + CYAN + "  #"
		+ CYAN + "    /o \\\\");
	System.out.println(CYAN + "|| ._)" + ANSI_RESET + "  #" + CYAN + "#" + ANSI_RESET + "#" + CYAN + "#"
		+ ANSI_RESET + "#" + CYAN + "  #" + ANSI_RESET + "       #" + ANSI_RESET + "     #" + CYAN + " #"
		+ ANSI_RESET + "       #" + CYAN + "      #" + ANSI_RESET + "#" + CYAN + "#" + ANSI_RESET + "#" + CYAN
		+ "#" + ANSI_RESET + "#" + CYAN + "#" + ANSI_RESET + "  #" + CYAN + "   (_. ||");
	System.out.println(CYAN + "//__\\" + CYAN + "       #" + ANSI_RESET + "  #" + CYAN + "       #" + ANSI_RESET
		+ " #" + CYAN + "#" + ANSI_RESET + "#" + CYAN + " #" + ANSI_RESET + " #" + CYAN + "       #"
		+ ANSI_RESET + "      #" + ANSI_RESET + "     #" + CYAN + "  #" + CYAN + "    /__\\\\");
	System.out.println(CYAN + ")___(" + ANSI_RESET + "   #" + CYAN + "#" + ANSI_RESET + "#" + CYAN + "#"
		+ ANSI_RESET + "#" + CYAN + "   #" + ANSI_RESET + "#" + CYAN + "#" + ANSI_RESET + "#" + CYAN + "#"
		+ ANSI_RESET + "  #" + ANSI_RESET + "     #" + CYAN + "  #" + ANSI_RESET + "#" + CYAN + "#" + ANSI_RESET
		+ "#" + CYAN + "#" + ANSI_RESET + "   #" + CYAN + "#" + ANSI_RESET + "#" + CYAN + "#" + ANSI_RESET + "#"
		+ CYAN + " #" + CYAN + "     #" + CYAN + " #" + ANSI_RESET + "#" + CYAN + "#" + CYAN + "   )___(");
	System.out.print(ANSI_RESET);
    }

    /**
     * Stampa a video il giocatore in turno
     * 
     * @param giocatoreAttivo
     */
    public static void stampaTurno(Giocatore giocatoreAttivo) {
	if (giocatoreAttivo.getColore() == Colore.bianco) {
	    System.out.println("\nE' il turno di " + WHITE_BOLD_BRIGHT + WHITE_UNDERLINED_BRIGHT
		    + giocatoreAttivo.getNome() + ANSI_RESET + " con le pedine di colore " + WHITE_BOLD_BRIGHT
		    + WHITE_UNDERLINED_BRIGHT + giocatoreAttivo.getColore() + ANSI_RESET + ".");
	} else {
	    System.out.println("\nE' il turno di " + CYAN_BOLD + CYAN_UNDERLINED + giocatoreAttivo.getNome()
		    + ANSI_RESET + " con le pedine di colore " + CYAN_BOLD + CYAN_UNDERLINED
		    + giocatoreAttivo.getColore() + ANSI_RESET + ".");
	}

	System.out.println(
		"-> Inserisci una mossa nella notazione algebrica (es. e4, Cxd3, exd3 e.p.), altrimenti digita una voce del menu.");
    }

    /**
     * Stampa a video i comandi principali del menu
     */
    public static void stampaMenu() {
	System.out.println();
	System.out.println("\u265A" + "\u265B" + WHITE_BOLD_BRIGHT + "  MENU PRINCIPALE " + ANSI_RESET + "\u2655"
		+ "\u2656" + " \n");
	mostrareElencoComandiMenu();
    }

    /**
     * Stampa a video il messaggio di mossa illegale
     */
    public static void stampaMossaIllegale() {
	System.out.println(WHITE_BOLD_BRIGHT + "Mossa Illegale!" + ANSI_RESET);
    }

    /**
     * Stampa a video il messaggio che indica che il comando inserito e' errato
     */
    public static void stampaComandoErrato() {
	System.out.println(WHITE_BOLD_BRIGHT + "Comando errato. Riprova!" + ANSI_RESET);
    }

    /**
     * Sampa a video il messaggio di conferma per iniziare una nuova partita
     */
    public static void stampaConfermaNuovaPartita() {
	System.out.println();
	System.out.println(
		"Sei sicuro di voler iniziare una nuova partita? \n(Digita 'y' per confermare, 'n' altrimenti)");

    }

    /**
     * Stampa a video il messaggio di nuova partita
     */
    public static void stampaNuovaPartita() {
	System.out.println("\n" + WHITE_BOLD_BRIGHT + "~ Nuova partita ~" + ANSI_RESET);
    }

    /**
     * Stampa a video i pezzi catturati per ogni giocatore
     * 
     * @param giocatoreInteressato
     */
    private static void stampaPezziCatturati(Giocatore giocatoreInteressato) {

	System.out.println("\nPezzi catturati dal giocatore " + WHITE_BOLD_BRIGHT
		+ giocatoreInteressato.getNome().toUpperCase() + ANSI_RESET + ":");
	for (Pezzo pezzoMangiato : giocatoreInteressato.getPezziCatturati())
	    System.out.println(pezzoMangiato);

    }

    /**
     * Mostra le catture effettuate da entrambe i giocatori
     */
    public static void visualizzareCatture() {

	Giocatore giocatoreAttivo = Turno.getGiocatoreInTurno();
	Giocatore giocatoreAttesa = Turno.getGiocatoreInAttesa();
	if (!giocatoreAttivo.isEmptyPezziCatturati() || !giocatoreAttesa.isEmptyPezziCatturati()) {
	    if (!giocatoreAttivo.isEmptyPezziCatturati()) // Se il giocatore attivo ha catturato dei pezzi, li stampo
		stampaPezziCatturati(giocatoreAttivo);

	    if (!giocatoreAttesa.isEmptyPezziCatturati()) // Se il giocatore in attesa ha catturato dei pezzi, li stampo
		stampaPezziCatturati(giocatoreAttesa);
	} else
	    System.out.println("\nNon ci sono pezzi catturati da entrambi i giocatori.");
    }

    /**
     * Stampa a video l'elenco delle mosse giocate da ogni giocatore
     */
    public static void stampaMosseGiocate() {
	String mossa = null;
	int counter = 1;
	int dimensione = Turno.getGiocatoreInAttesa().getNumeroMosseGiocate()
		+ Turno.getGiocatoreInTurno().getNumeroMosseGiocate();
	if (dimensione == 0) {
	    System.out.println("\nNon e' stata giocata alcuna mossa.");
	} else {
	    System.out.println("\nStoria delle mosse giocate: ");
	    for (int i = 0; i < dimensione; i++) {
		if (i == dimensione - 1) {
		    mossa = counter + ". " + Turno.fusioneListe().get(i);
		    System.out.println(mossa);
		} else {
		    mossa = counter + ". " + Turno.fusioneListe().get(i) + " " + Turno.fusioneListe().get(i + 1);
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
	System.out.println(Menu.play().toString());
	System.out.println(Menu.quit().toString());
	System.out.println(Menu.board().toString());
	System.out.println(Menu.help().toString());
	System.out.println();
    }

    /**
     * Permette la visualizzazione dell' elenco dei comandi del menu di gioco
     */
    public static void mostrareElencoComandiGioco() {
	System.out.println();
	System.out.println(Menu.play().toString());
	System.out.println(Menu.quit().toString());
	System.out.println(Menu.board().toString());
	System.out.println(Menu.captures().toString());
	System.out.println(Menu.moves().toString());
	System.out.println(Menu.help().toString());
	System.out.println();
    }

    /**
     * Stampa a video il messaggio per l'inserimento del nome del giocatore
     * 
     * @param c
     */
    public static void stampaInserireGiocatore(Colore c) {
	if (c == Colore.bianco) {
	    System.out.println("\nInserisci il nome del giocatore con le pedine di colore " + WHITE_BOLD_BRIGHT
		    + WHITE_UNDERLINED_BRIGHT + c + ANSI_RESET + " \u2193");
	} else {
	    System.out.println("\nInserisci il nome del giocatore con le pedine di colore " + CYAN_UNDERLINED
		    + CYAN_BOLD + c + ANSI_RESET + " \u2193");
	}

    }

}
