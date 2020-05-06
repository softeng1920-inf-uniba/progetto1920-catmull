package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import gioco.Colore;
import gioco.Comando;
import gioco.Giocatore;
import gioco.Menu;
import gioco.Stampa;
import gioco.Turno;
import pedine.Alfiere;
import pedine.Cavallo;
import pedine.Pedone;
import pedine.Pezzo;
import pedine.Re;
import pedine.Regina;
import pedine.Torre;
import scacchiera.Cella;
import scacchiera.Scacchiera;
/**
 * Classe che gestisce le varie funzionalita' del gioco, permette di iniziare 
 * una nuova partita o di effettuarne una.
 * La classe Controller e' di tipo CONTROL
 */
public class Controller {
    private static ArrayList<String> mosseConvertite;

    public Controller() {
	mosseConvertite = new ArrayList<String>();
	new Scacchiera();
    }

    /**
     * inizializzaPartita implementa la fase iniziale della partita
     */
    final void playGame() {

	boolean utenteVuoleRicominciare = false;
	Menu.newMenu();

	do {

	    Scacchiera.inizializzaScacchiera();

	    new Turno();

	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String comando = "";

	    while (true) {

		Stampa.stampaTurno(Turno.getGiocatoreInTurno());
		try {
		    comando = br.readLine();
		} catch (IOException e) {
		    e.printStackTrace();
		}

		if (comando.equalsIgnoreCase(Menu.help().getNome())) {
		    Stampa.mostrareElencoComandiGioco();
		} else if (comando.equalsIgnoreCase(Menu.board().getNome())) {
		    Stampa.stampaScacchiera();
		} else if (comando.equalsIgnoreCase(Menu.moves().getNome())) {
		    Stampa.stampaMosseGiocate();
		} else if (comando.equalsIgnoreCase(Menu.captures().getNome())) {
		    Stampa.visualizzareCatture();
		} else if (comando.equalsIgnoreCase(Menu.quit().getNome())) {
		    chiudiGioco();
		} else if (comando.equalsIgnoreCase(Menu.play().getNome())) {
		    if (utenteConfermaRiavvioPartita()) {
			Stampa.stampaNuovaPartita();
			utenteVuoleRicominciare = true;
			new Scacchiera(); // Svuoto la scacchiera
			break;
		    } else
			continue; // Faccio ripartire il loop interno
		}

		if (Comando.isNotazioneAlgebrica(comando)) {

		    int tipoArrocco;
		    if ((tipoArrocco = Comando.isArrocco(comando)) != -1) { // Se è un arrocco
			Colore coloreGiocatoreAttivo = Turno.getGiocatoreInTurno().getColore();

			String mossaTorre = Torre.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);
			String mossaRe = Re.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);

			// Non ho bisogno di controllare se i comandi convertiti saranno validi, perchè
			// sono stati già stabiliti dalle regole del gioco

			// Controllo se l'arrocco è possibile
			if (isArroccoValido(mossaRe, mossaTorre, tipoArrocco)) {
			    // Applico arrocco

			    Cella cellaPartenzaRe = Scacchiera.getCella(Cella.startX(mossaRe), Cella.startY(mossaRe));
			    Cella cellaDestinazioneRe = Scacchiera.getCella(Cella.endX(mossaRe), Cella.endY(mossaRe));

			    Scacchiera.scambiaCella(cellaPartenzaRe, cellaDestinazioneRe);

			    Cella cellaPartenzaTorre = Scacchiera.getCella(Cella.startX(mossaTorre),
				    Cella.startY(mossaTorre));
			    Cella cellaDestinazioneTorre = Scacchiera.getCella(Cella.endX(mossaTorre),
				    Cella.endY(mossaTorre));

			    Scacchiera.scambiaCella(cellaPartenzaTorre, cellaDestinazioneTorre);

			    addMosseConvertite(comando);
			    Turno.getGiocatoreInTurno().setMosseGiocate(comando);
			    Turno.cambioTurno();

			} else
			    Stampa.stampaMossaIllegale();
		    } else { // La mossa inserita è un'avanzata, una cattura o un en passant
			String mossaInCoordinate = convertiNotazioneRidottaInEstesa(comando);

			if (isMossaInRangeValido(mossaInCoordinate)) {
			    int tipoMossa = 0;
			    Cella cellaPartenza = Scacchiera.getCella(Cella.startX(mossaInCoordinate),
				    Cella.startY(mossaInCoordinate));
			    Cella cellaDestinazione = Scacchiera.getCella(Cella.endX(mossaInCoordinate),
				    Cella.endY(mossaInCoordinate));
			    tipoMossa = getTipoMossa(cellaPartenza, cellaDestinazione, mosseConvertite);

			    if (tipoMossa != -1) {

				applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
				addMosseConvertite(mossaInCoordinate);
				Turno.getGiocatoreInTurno().setMosseGiocate(comando);
				Turno.cambioTurno();

			    } else
				Stampa.stampaMossaIllegale();

			} else
			    Stampa.stampaMossaIllegale();
		    }
		} else if (!Comando.isComandoValido(comando)) // Se il comando inserito non è una mossa, nè un comando di
		    // gioco...
		    Stampa.stampaComandoErrato();

	    } // Fine loop di gioco
	} while (utenteVuoleRicominciare);
    }

    /**
     * Data la cella di partenza e di destinazione del pezzo, effettua i controlli
     * necessari se per il pezzo è consentita la mossa.
     *
     * @param cellaPartenza
     * @param cellaDestinazione
     * @param mosseEffettuate
     * @return -1 se mossa illegale, 0 se mossa valida, 1 se en passant valido
     */
    private int getTipoMossa(Cella cellaPartenza, Cella cellaDestinazione, ArrayList<String> mosseEffettuate) {

	Pezzo pezzoCorrente = cellaPartenza.getPezzoCorrente();
	if (pezzoCorrente == null)
	    // Se ho inserito una mossa il cui pezzo di partenza non esiste, ritorno mossa
	    // illegale
	    return -1;

	if (pezzoCorrente.isMossaValida(cellaPartenza, cellaDestinazione))
	    return 0;
	else if (pezzoCorrente.getNome().equals("Pedone")) {
	    Pedone p = (Pedone) pezzoCorrente;
	    // Controllo se l'en Passant e'consentito
	    return (p.isEnPassantValido(cellaPartenza, cellaDestinazione, mosseEffettuate) ? 1 : -1);
	}

	return -1;
    }

    /**
     * Viene richiesto all'utente una conferma se vuole riavviare la partita
     *
     * @return true se l'utente vuole ricominciare la partita, false altrimenti.
     */
    private boolean utenteConfermaRiavvioPartita() {

	String comando = "";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Stampa.stampaConfermaNuovaPartita();
	while (true) {
	    try {
		comando = br.readLine();
		switch (comando) {
		case "y":
		    return true;
		case "n":
		    return false;
		default:
		    Stampa.stampaComandoErrato();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

    }

    /**
     * Trasforma il comando a seconda del pezzo da muovere
     *
     * @param mossa in notazione algebrica
     * @return String comando in colonna e traversa di partenza seguite da uno
     *         spazio, ed infine colonna e traversa di destinazione
     */
    private final String convertiNotazioneRidottaInEstesa(String mossa) {

	switch (mossa.charAt(0)) {
	case 'T': // Torre
	    return Torre.convertiMossa(mossa);
	case 'A': // Alfiere
	    return Alfiere.convertiMossa(mossa);
	case 'R': // Re
	    return Re.convertiMossa(mossa);
	case 'D': // Donna
	    return Regina.convertiMossa(mossa);
	case 'C': // Cavallo
	    return Cavallo.convertiMossa(mossa);
	default:
	    // pedone
	    return Pedone.convertiMossa(mossa);
	}
	return false;
    }

    /**
     * Controlla, attraverso un'espressione regolare, se la stringa inserita
     * dall'utente Ã¨ riconosciuta come notazione algebrica.
     *
     * @param mossa
     * @return boolean
     */
    private boolean isNotazioneAlgebrica(final String mossa) {
	String regex = String.join("|", new String[] { "([a-h](x|:))?([a-h][1-8])( e.p.)?", // mossa del pedone
		"D([x|:])?[a-h][1-8]", // mossa della regina
		"T([a-h]|[1-8])?([x|:])?([a-h][1-8])", // mossa della torre
		"C([a-h]|[1-8])?([x|:])?([a-h][1-8])", // mossa cavallo
		"A(x|:)?[a-h][1-8]", // mossa alfiere
		"R(x|:)?[a-h][1-8]", // mossa del re
		"(0|o|O)-(0|o|O)(-(0|o|O))?" // arrocco corto o lungo
	});

	return mossa.matches(regex);
    }

    /**
     * Mostra le catture di entrambi i giocatori
     */
    private void visualizzareCatture() {

	Giocatore giocatoreAttivo = Turno.getGiocatoreInTurno();
	Giocatore giocatoreAttesa = Turno.getGiocatoreInAttesa();
	if (!giocatoreAttivo.isEmptyPezziCatturati() || !giocatoreAttesa.isEmptyPezziCatturati()) {
	    if (!giocatoreAttivo.isEmptyPezziCatturati()) // Se il giocatore attivo ha catturato dei pezzi, li stampo
		giocatoreAttivo.stampaPezziCatturati();

	    if (!giocatoreAttesa.isEmptyPezziCatturati()) // Se il giocatore in attesa ha catturato dei pezzi, li stampo
		giocatoreAttesa.stampaPezziCatturati();
	} else
	    System.out.println("Non ci sono pezzi catturati da entrambi i giocatori.");
    }

    /**
     * Fonde le due liste in cui sono conservate le mosse giocate di ogni giocatore.
     * La fusione avviene in modo alternato. Permette di avere una visione completa
     * delle mosse giocate totali.
     *
     * @return ArrayList di stringhe.
     */
    private ArrayList<String> fusioneListe() {
	int i, j, k;
	int dimensione = Turno.getGiocatoreInAttesa().getNumeroMosseGiocate()
		+ Turno.getGiocatoreInTurno().getNumeroMosseGiocate();
	ArrayList<String> mosseGiocateTotali = new ArrayList<String>(dimensione);
	if (Turno.getGiocatoreInTurno().getColore() == Colore.bianco) {
	    i = 0;
	    j = 0;
	    k = 0;
	    while (i < Turno.getGiocatoreInTurno().getNumeroMosseGiocate()
		    && j < Turno.getGiocatoreInAttesa().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, Turno.getGiocatoreInTurno().getMossaGiocata(i++));
		mosseGiocateTotali.add(k++, Turno.getGiocatoreInAttesa().getMossaGiocata(j++));
	    }
	    while (i < Turno.getGiocatoreInTurno().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, Turno.getGiocatoreInTurno().getMossaGiocata(i++));
	    }
	    while (j < Turno.getGiocatoreInAttesa().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, Turno.getGiocatoreInAttesa().getMossaGiocata(j++));
	    }
	} else {
	    i = 0;
	    j = 0;
	    k = 0;
	    while (i < Turno.getGiocatoreInAttesa().getNumeroMosseGiocate()
		    && j < Turno.getGiocatoreInTurno().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, Turno.getGiocatoreInAttesa().getMossaGiocata(i++));
		mosseGiocateTotali.add(k++, Turno.getGiocatoreInTurno().getMossaGiocata(j++));
	    }
	    while (i < Turno.getGiocatoreInAttesa().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, Turno.getGiocatoreInAttesa().getMossaGiocata(i++));
	    }
	    while (j < Turno.getGiocatoreInTurno().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, Turno.getGiocatoreInTurno().getMossaGiocata(j++));
	    }

    }

    /**
     * Controlla se la mossa inserita in input va oltre i limiti della scacchiera
     * (la traversa è minore di 1 ecc.)
     *
     * @param comando Stringa nel formato : colonna e traversa di partenza seguite
     *                da uno spazio, ed infine colonna e traversa di destinazione
     * @return false se la mossa inserita non e' valida, va oltre i limiti della
     *         scacchiera, true altrimenti
     */
    public static boolean isMossaInRangeValido(String comando) {
	if (!isCoordinateValide(Cella.startX(comando), Cella.startY(comando), Cella.endX(comando), Cella.endY(comando)))
	    return false;

	return true;
    }

    /**
     * Dopo che sono stati effettuati i vari controlli (se la mossa inserita è valida, se è consentita ecc.), viene effettuato
     * in base alla tipologia della mossa, il necessario per proseguire con il corretto funzionamento del gioco.
     *
     * L'applicazione della mossa non è univoca per ogni comando inserito (vedi e.p.)
     *
     * @param cellaPartenza
     * @param cellaDestinazione
     * @param tipoMossa Tipologia della mossa:
     * 		0: Mossa normale (avanzata, cattura) di un pezzo
     * 		1: Mossa speciale (en passant) del pedone
     */
    private final void applicaMossa(Cella cellaPartenza, Cella cellaDestinazione, int tipoMossa) {

	Pezzo pezzoInCellaDestinazione = cellaDestinazione.getPezzoCorrente();
	Cella cellaAdiacenteEp = Scacchiera.getCella(cellaDestinazione.getX(), cellaPartenza.getY());
	Giocatore giocatoreAttivo = Turno.getGiocatoreInTurno();
	switch (tipoMossa) {

	case 0:
	    if (cellaDestinazione.isOccupato()) {
		giocatoreAttivo.addPezziCatturati(pezzoInCellaDestinazione);
		cellaDestinazione.rimuoviPezzoCorrente();
	    }
	    break;
	case 1:
	    giocatoreAttivo.addPezziCatturati(cellaAdiacenteEp.getPezzoCorrente());
	    cellaAdiacenteEp.rimuoviPezzoCorrente();
	    break;
	default:
	}

	Scacchiera.scambiaCella(cellaPartenza, cellaDestinazione);

    }

    /**
     * Restituisce un valore booleano che indica se le coordinate che rappresentano gli indici della matrice scacchiera
     * (necessariamente di tipo intero)sono valide per la scacchiera.
     *
     * @param startX 	intero compreso fra 0 e 7
     * @param startY 	intero compreso fra 0 e 7
     * @param endX 	intero compreso fra 0 e 7
     * @param endY 	intero compreso fra 0 e 7
     * @return	vero se i quattro parametri sono compresi fra 0 e 7, falso altrimenti
     */
    private static boolean isCoordinateValide(int startX, int startY, int endX, int endY) {
	return Scacchiera.isRangeValido(startX, startY) && Scacchiera.isRangeValido(endX, endY);
    }

    /**
     * Lascia il controllo al sistema operativo
     *
     */
    public void chiudiGioco() {
	System.exit(0);
    }

    /**
     * Restituisce la lista delle mosse convertite in notazione comprensibile da
     * applicaMossa.
     *
     * @return mosseConverite
     */
    public static ArrayList<String> getMosseConvertite() {
	return mosseConvertite;
    }

    /**
     * Aggiunge la mossa effettuata fra quelle convertite
     */
    public void addMosseConvertite(String mossa) {
	mosseConvertite.add(mossa);
    }

    /**
     * Data la mossa del re e quella della torre, vengono effettuati tutti i controlli che validano se la mossa è consentita o meno.
     *
     * @param mossaRe
     * @param mossaTorre
     * @param tipoArrocco
     * @return true se l'arrocco può essere effettuato, false se l'arrocco e' stato chiamato con pezzi non compatibili / celle vuote
     * 		L'arrocco e' valido se sono verificate tutte le seguenti condizioni:
     * 		- Il giocatore non ha ancora mosso ne' il re ne' la torre coinvolta nell'arrocco;
     *		- Non ci devono essere pezzi (amici o avversari) fra il re e la torre usata;
     * 		- Ne' la casa di partenza del re, ne' la casa che esso deve attraversare ne' quella di arrivo devono essere minacciate da un pezzo avversario.
     */
    public static boolean isArroccoValido(String mossaRe, String mossaTorre, int tipoArrocco) {

	Cella cellaPartenzaRe = Scacchiera.getCella(Cella.startX(mossaRe), Cella.startY(mossaRe));
	Cella cellaDestinazioneRe = Scacchiera.getCella(Cella.endX(mossaRe), Cella.endY(mossaRe));

	Pezzo presuntoReGiocatoreAttuale = cellaPartenzaRe.getPezzoCorrente();

	Cella cellaPartenzaTorre = Scacchiera.getCella(Cella.startX(mossaTorre), Cella.startY(mossaTorre));
	Cella cellaDestinazioneTorre = Scacchiera.getCella(Cella.endX(mossaTorre), Cella.endY(mossaTorre));

	Pezzo presuntaTorreGiocatoreAttuale = cellaPartenzaTorre.getPezzoCorrente();

	Colore coloreGiocatoreAttivo = Turno.getGiocatoreInTurno().getColore();
	// Se nella cella di partenza del presunto re c'è il re del colore del giocatore
	// in turno..
	if (cellaPartenzaRe.isOccupato() && presuntoReGiocatoreAttuale.getNome() == "Re"
		&& presuntoReGiocatoreAttuale.getColore() == coloreGiocatoreAttivo &&
		// e nella cella della presunta torre c'è la torre del colore del giocatore in
		// turno..
		cellaPartenzaTorre.isOccupato() && presuntaTorreGiocatoreAttuale.getNome() == "Torre"
		&& presuntaTorreGiocatoreAttuale.getColore() == coloreGiocatoreAttivo) {
	    Re re = (Re) presuntoReGiocatoreAttuale;

	    return re.isArroccoValido(cellaPartenzaRe, cellaDestinazioneRe, cellaPartenzaTorre, cellaDestinazioneTorre,
		    getMosseConvertite(), tipoArrocco);
	}

	return false;

    }

	return false; // L'arrocco è stato chiamato con pezzi non compatibili / celle vuote

    }

}
