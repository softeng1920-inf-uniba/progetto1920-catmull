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
 * Classe che gestisce le varie funzionalita' del gioco, permette di iniziare
 * una nuova partita o di effettuarne una. La classe Controller e' di tipo
 * CONTROL
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
		    if (utenteConfermaFinePartita()) {
			chiudiGioco();
		    }
		} else if (comando.equalsIgnoreCase(Menu.play().getNome())) {
		    if (utenteConfermaRiavvioPartita()) {
			Stampa.stampaNuovaPartita();
			utenteVuoleRicominciare = true;
			new Scacchiera(); // Svuoto la scacchiera
			break;
		    } else {
			continue; // Faccio ripartire il loop interno
		    }
		}

		if (Comando.isNotazioneAlgebrica(comando)) {

		    int tipoArrocco = Comando.isArrocco(comando);
		    if (tipoArrocco != -1) { // Se è un arrocco
			Colore coloreGiocatoreAttivo = Turno.getGiocatoreInTurno().getColore();

			String mossaTorre = Torre.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);
			String mossaRe = Re.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);

			// Non ho bisogno di controllare se i comandi convertiti saranno validi, perchè
			// sono stati gia stabiliti dalle regole del gioco
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

			} else {
			    Stampa.stampaMossaIllegale();
			}
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

			    } else {
				Stampa.stampaMossaIllegale();
			    }
			} else {
			    Stampa.stampaMossaIllegale();
			}
		    }
		} else if (!Comando.isComandoValido(comando)) {
		    // Se il comando inserito non è una mossa, nè un comando di gioco...
		    Stampa.stampaComandoErrato();
		}
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
    private int getTipoMossa(final Cella cellaPartenza, final Cella cellaDestinazione,
	    final ArrayList<String> mosseEffettuate) {

	int tipoMossa = -1;
	Pezzo pezzoCorrente = cellaPartenza.getPezzoCorrente();
	if (pezzoCorrente == null) {
	    // Se ho inserito una mossa il cui pezzo di partenza non esiste, ritorno mossa
	    // illegale
	    return tipoMossa;
	} else if (pezzoCorrente.isMossaValida(cellaPartenza, cellaDestinazione)) {
	    if (isReProtetto(cellaPartenza, cellaDestinazione, 0)) {
		tipoMossa = 0;

	    }
	} else if (pezzoCorrente.getNome().equals("Pedone")) {
	    Pedone p = (Pedone) pezzoCorrente;
	    // Controllo se l'en Passant e'consentito
	    if (p.isEnPassantValido(cellaPartenza, cellaDestinazione, mosseEffettuate)) {
		tipoMossa = 1;
	    }
	}
	return tipoMossa;
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
     * Viene richiesto all'utente una conferma se vuole uscire dal gioco
     *
     * @return true se l'utente vuole ricominciare la partita, false altrimenti.
     */
    private boolean utenteConfermaFinePartita() {
	String comando = "";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Stampa.stampaConfermaFinePartita();
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
    private String convertiNotazioneRidottaInEstesa(final String mossa) {

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
	default: // pedone
	    return Pedone.convertiMossa(mossa);
	}
    }

    /**
     * Controlla se la mossa inserita in input va oltre i limiti della scacchiera
     * (la traversa e' minore di 1 ecc.)
     *
     * @param comando Stringa nel formato : colonna e traversa di partenza seguite
     *                da uno spazio, ed infine colonna e traversa di destinazione
     * @return false se la mossa inserita non e' valida, va oltre i limiti della
     *         scacchiera, true altrimenti
     */
    public static boolean isMossaInRangeValido(final String comando) {
	return isCoordinateValide(Cella.startX(comando), Cella.startY(comando), Cella.endX(comando),
		Cella.endY(comando)); // TODO: ricontrollare se logica è corretta
    }

    /**
     * Dopo che sono stati effettuati i vari controlli (se la mossa inserita e'
     * valida, se e' consentita ecc.), viene effettuato in base alla tipologia della
     * mossa, il necessario per proseguire con il corretto funzionamento del gioco.
     *
     * L'applicazione della mossa non è univoca per ogni comando inserito (vedi
     * e.p.)
     *
     * @param cellaPartenza
     * @param cellaDestinazione
     * @param tipoMossa         Tipologia della mossa: 0: Mossa normale (avanzata,
     *                          cattura) di un pezzo 1: Mossa speciale (en passant)
     *                          del pedone
     */
    private void applicaMossa(final Cella cellaPartenza, final Cella cellaDestinazione, final int tipoMossa) {

	Pezzo pezzoInCellaDestinazione = cellaDestinazione.getPezzoCorrente();

	Giocatore giocatoreAttivo = Turno.getGiocatoreInTurno();
	switch (tipoMossa) {
	case 0:
	    if (cellaDestinazione.isOccupato()) {
		giocatoreAttivo.addPezziCatturati(pezzoInCellaDestinazione);
		cellaDestinazione.rimuoviPezzoCorrente();
	    }
	    break;
	case 1:
	    Cella cellaAdiacenteEp = Scacchiera.getCella(cellaDestinazione.getX(), cellaPartenza.getY());
	    giocatoreAttivo.addPezziCatturati(cellaAdiacenteEp.getPezzoCorrente());
	    cellaAdiacenteEp.rimuoviPezzoCorrente();
	    break;
	default:
	}
	Scacchiera.scambiaCella(cellaPartenza, cellaDestinazione);

    }

    /**
     * Restituisce un valore booleano che indica se le coordinate che rappresentano
     * gli indici della matrice scacchiera (necessariamente di tipo intero)sono
     * valide per la scacchiera.
     *
     * @param startX intero compreso fra 0 e 7
     * @param startY intero compreso fra 0 e 7
     * @param endX   intero compreso fra 0 e 7
     * @param endY   intero compreso fra 0 e 7
     * @return vero se i quattro parametri sono compresi fra 0 e 7, falso altrimenti
     */
    private static boolean isCoordinateValide(final int startX, final int startY, final int endX, final int endY) {
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
    public void addMosseConvertite(final String mossa) {
	mosseConvertite.add(mossa);
    }

    /**
     * Data la mossa del re e quella della torre, vengono effettuati tutti i
     * controlli che validano se la mossa e' consentita o meno.
     *
     * @param mossaRe
     * @param mossaTorre
     * @param tipoArrocco
     * @return true se l'arrocco può essere effettuato, false se l'arrocco e' stato
     *         chiamato con pezzi non compatibili / celle vuote L'arrocco e' valido
     *         se sono verificate tutte le seguenti condizioni: - Il giocatore non
     *         ha ancora mosso ne' il re ne' la torre coinvolta nell'arrocco; - Non
     *         ci devono essere pezzi (amici o avversari) fra il re e la torre
     *         usata; - Ne' la casa di partenza del re, ne' la casa che esso deve
     *         attraversare ne' quella di arrivo devono essere minacciate da un
     *         pezzo avversario.
     */
    public static boolean isArroccoValido(final String mossaRe, final String mossaTorre, final int tipoArrocco) {

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
		&& presuntoReGiocatoreAttuale.getColore() == coloreGiocatoreAttivo
		// e nella cella della presunta torre c'è la torre del colore del giocatore in
		// turno..
		&& cellaPartenzaTorre.isOccupato() && presuntaTorreGiocatoreAttuale.getNome() == "Torre"
		&& presuntaTorreGiocatoreAttuale.getColore() == coloreGiocatoreAttivo) {
	    Re re = (Re) presuntoReGiocatoreAttuale;

	    return re.isArroccoValido(cellaPartenzaRe, cellaDestinazioneRe, cellaPartenzaTorre, cellaDestinazioneTorre,
		    getMosseConvertite(), tipoArrocco);
	}

	return false;

    }

    /**
     * TODO: Aggiungere javadoc!
     * 
     * @param partenza
     * @param destinazione
     * @param tipo         //TODO tipomossa??
     * @return
     */
    public boolean isReProtetto(final Cella partenza, final Cella destinazione, final int tipo) {
	Cella cellaRe = Re.findRe();
	Re reDaProteggere = (Re) cellaRe.getPezzoCorrente();
	boolean isReProtetto = false;

	if (partenza.getPezzoCorrente().getNome().equals("Re")) {
	    return true; // Il re è protetto
	}

	applicaMossa(partenza, destinazione, tipo);
	if (!reDaProteggere.isReSottoScacco(cellaRe)) {
	    isReProtetto = true;
	}

	applicaMossa(destinazione, partenza, tipo);

	Cella temp = new Cella(destinazione.getX(), destinazione.getY(), destinazione.getPezzoCorrente());
	if (temp.isOccupato()) {
	    Scacchiera.getCella(temp.getX(), temp.getY()).aggiungiPezzo(temp.getPezzoCorrente());
	    Turno.getGiocatoreInTurno().removePezzoCatturato();
	}

	return isReProtetto;
    }

}
