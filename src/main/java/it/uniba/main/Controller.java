package it.uniba.main;

import java.util.ArrayList;

import gioco.Colore;
import gioco.Giocatore;
import gioco.InterfacciaUtente;
import gioco.Menu;
import gioco.Turno;
import scacchiera.Cella;
import scacchiera.Scacchiera;
import scacchiera.pedine.Alfiere;
import scacchiera.pedine.Cavallo;
import scacchiera.pedine.Pedone;
import scacchiera.pedine.Pezzo;
import scacchiera.pedine.Re;
import scacchiera.pedine.Regina;
import scacchiera.pedine.Torre;

/**
 * Classe che gestisce le varie funzionalita' del gioco, permette di iniziare
 * una nuova partita o di effettuarne una. La classe Controller e' di tipo
 * CONTROL
 */
public final class Controller {
    private static Controller istance = null;
    private static final int EN_PASSANT = 3;
    private ArrayList<String> mosseConvertite = new ArrayList<String>();
    private Scacchiera scacchieraGioco;
    private InterfacciaUtente interfaccia;
    private Menu menuGioco = null;
    private Turno turnoGioco = null;

    private Controller() {
	scacchieraGioco = Scacchiera.getInstance();
	interfaccia = InterfacciaUtente.getInstance();
	menuGioco = Menu.getInstance();
	turnoGioco = Turno.getInstance();
    }

    /**
     * Costruttore privato per classe Singleton
     */
    public static Controller getInstance() {
	if (istance == null) {
	    istance = new Controller();
	}

	return istance;
    }

    /**
     * inizializzaPartita implementa la fase iniziale della partita
     */
    public void playGame() {
	boolean utenteVuoleRicominciare = false;
	do {
	    scacchieraGioco.inizializzaScacchiera();
	    turnoGioco.inizializzaTurno();
	    turnoGioco.setNomeGiocatoreInTurno(
		    interfaccia.getNomeDaTastiera(turnoGioco.getGiocatoreInTurno().getColore()));
	    turnoGioco.setNomeGiocatoreInAttesa(
		    interfaccia.getNomeDaTastiera(turnoGioco.getGiocatoreInAttesa().getColore()));
	    while (true) {
		interfaccia.stampaTurno(turnoGioco.getGiocatoreInTurno());
		String comando = interfaccia.acquisireComando();
		if (comando.equalsIgnoreCase(menuGioco.help().getNome())) {
		    interfaccia.mostrareElencoComandiGioco();
		} else if (comando.equalsIgnoreCase(menuGioco.board().getNome())) {
		    interfaccia.stampaScacchiera();
		} else if (comando.equalsIgnoreCase(menuGioco.moves().getNome())) {
		    interfaccia.stampaMosseGiocate();
		} else if (comando.equalsIgnoreCase(menuGioco.captures().getNome())) {
		    interfaccia.visualizzareCatture();
		} else if (comando.equalsIgnoreCase(menuGioco.quit().getNome())) {
		    if (interfaccia.utenteConfermaFinePartita()) {
			utenteVuoleRicominciare = false;
			break;
		    }
		} else if (comando.equalsIgnoreCase(menuGioco.play().getNome())) {
		    if (interfaccia.utenteConfermaRiavvioPartita()) {
			interfaccia.stampaNuovaPartita();
			utenteVuoleRicominciare = true;
			scacchieraGioco.inizializzaScacchiera(); // Svuoto la scacchiera
			break;
		    } else {
			continue; // Faccio ripartire il loop interno
		    }
		} else if (menuGioco.isNotazioneAlgebrica(comando)) {
		    if (mossaScacchi(comando)) {
			turnoGioco.getGiocatoreInTurno().setMosseGiocate(comando);
			turnoGioco.cambioTurno();
		    } else {
			interfaccia.stampaMossaIllegale();
		    }
		} else {
		    interfaccia.stampaComandoErrato();
		}
	    }
	} while (utenteVuoleRicominciare);
    }

    /**
     * Dopo i dovuti controlli permette di giocare la mossa inserita dal giocatore,
     * e restituisce true se la mossa e' valida ed e' stata applicata altrimenti
     * restituisce false
     *
     * @param mossaDaGiocare
     * @return
     */
    public boolean mossaScacchi(final String mossaDaGiocare) {
	int tipoMossa = getTipoMossa(mossaDaGiocare);
	if (tipoMossa == 2) {
	    int tipoArrocco = menuGioco.isArrocco(mossaDaGiocare);
	    Colore coloreGiocatoreAttivo = turnoGioco.getGiocatoreInTurno().getColore();
	    String mossaTorre = Torre.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);
	    String mossaRe = Re.getCoordinateArrocco(tipoArrocco, coloreGiocatoreAttivo);
	    // Non ho bisogno di controllare se i comandi convertiti saranno
	    // validi, perche' sono stati gia' stabiliti dalle regole del gioco
	    // Controllo se l'arrocco e' possibile
	    if (isArroccoValido(mossaRe, mossaTorre, tipoArrocco)) {
		// Applico arrocco Re
		Cella cellaPartenza = scacchieraGioco.getCella(Cella.startX(mossaRe), Cella.startY(mossaRe));
		Cella cellaDestinazione = scacchieraGioco.getCella(Cella.endX(mossaRe), Cella.endY(mossaRe));
		applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
		// Applico arrocco Torre
		cellaPartenza = scacchieraGioco.getCella(Cella.startX(mossaTorre), Cella.startY(mossaTorre));
		cellaDestinazione = scacchieraGioco.getCella(Cella.endX(mossaTorre), Cella.endY(mossaTorre));
		applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
		addMosseConvertite(mossaDaGiocare);
	    } else {
		return false;
	    }
	} else { // operazioni valide sia per una mossa normale che per un en passant
	    String mossaEstesa = convertiMossaInEstesa(mossaDaGiocare);
	    if (scacchieraGioco.isRangeValido(Cella.startX(mossaEstesa), Cella.startY(mossaEstesa))
		    && scacchieraGioco.isRangeValido(Cella.endX(mossaEstesa), Cella.endY(mossaEstesa))) {
		Cella cellaPartenza = scacchieraGioco.getCella(Cella.startX(mossaEstesa), Cella.startY(mossaEstesa));
		Cella cellaDestinazione = scacchieraGioco.getCella(Cella.endX(mossaEstesa), Cella.endY(mossaEstesa));
		if (new Pedone(turnoGioco.getGiocatoreInTurno().getColore()).isEnPassantValido(cellaPartenza,
			cellaDestinazione, mosseConvertite)) {
		    tipoMossa = EN_PASSANT;
		    applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
		    addMosseConvertite(mossaEstesa);
		} else {
		    // se puo' essere un en passant
		    if (isMossaValidaGlobale(mossaEstesa, tipoMossa) && tipoMossa != EN_PASSANT) {
			applicaMossa(cellaPartenza, cellaDestinazione, tipoMossa);
			addMosseConvertite(mossaEstesa);
		    } else {
			return false;
		    }
		}
	    } else {
		return false;
	    }
	}
	return true;

    }

    /**
     * Data la notazione algebrica inserita dall'utente, l'algoritmo restituisce 0
     * se � una mossa semplice, 1 se � una mossa di cattura, 2 se � un arrocco e 3
     * se � di sicuro un en passant
     *
     * @param mossaInInput
     * @return
     */
    private int getTipoMossa(final String mossaInInput) {
	int tipoMossa = 0;
	if (mossaInInput.matches("(D|R|T|A|C)?([a-h]|[1-8])?(x|:)[a-h][1-8]")) {
	    tipoMossa = 1;
	} else if (mossaInInput.matches("(0|o|O)-(0|o|O)(-(0|o|O))?")) {
	    tipoMossa = 2;
	} else if (mossaInInput.matches("([a-h](x|:))([a-h][1-8])( e.p.)")) {
	    tipoMossa = EN_PASSANT;
	}

	return tipoMossa;
    }

    /**
     * Trasforma il comando a seconda del pezzo da muovere
     *
     * @param mossa in notazione algebrica
     * @return String comando in colonna e traversa di partenza seguite da uno
     *         spazio, ed infine colonna e traversa di destinazione
     */
    private String convertiMossaInEstesa(final String mossa) {

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
    private boolean isMossaValidaGlobale(final String comando, final int tipoMossa) {
	Cella cellaPartenza = scacchieraGioco.getCella(Cella.startX(comando), Cella.startY(comando));
	Cella cellaDestinazione = scacchieraGioco.getCella(Cella.endX(comando), Cella.endY(comando));
	if (cellaPartenza.isOccupato()
		&& cellaPartenza.getPezzoCorrente().isMossaValida(cellaPartenza, cellaDestinazione)) {
	    if (isReProtetto(cellaPartenza, cellaDestinazione)) {
		return true;
	    }

	}
	return false;
    }

    /**
     * Dopo che sono stati effettuati i vari controlli (se la mossa inserita è
     * valida, se è consentita ecc.), viene effettuato in base alla tipologia della
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
	Giocatore giocatoreAttivo = turnoGioco.getGiocatoreInTurno();
	switch (tipoMossa) {
	case 1:
	    if (cellaDestinazione.isOccupato()) {
		giocatoreAttivo.addPezziCatturati(pezzoInCellaDestinazione);
		cellaDestinazione.rimuoviPezzoCorrente();
	    }
	    break;
	case EN_PASSANT:
	    Cella cellaAdiacenteEp = scacchieraGioco.getCella(cellaDestinazione.getX(), cellaPartenza.getY());
	    giocatoreAttivo.addPezziCatturati(cellaAdiacenteEp.getPezzoCorrente());
	    cellaAdiacenteEp.rimuoviPezzoCorrente();
	    break;
	default:
	}
	scacchieraGioco.scambiaCella(cellaPartenza, cellaDestinazione);
    }

    /**
     * Restituisce la lista delle mosse convertite in notazione comprensibile da
     * applicaMossa.
     *
     * @return mosseConverite
     */
    public ArrayList<String> getMosseConvertite() {
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
    public boolean isArroccoValido(final String mossaRe, final String mossaTorre, final int tipoArrocco) {

	Cella cellaPartenzaRe = scacchieraGioco.getCella(Cella.startX(mossaRe), Cella.startY(mossaRe));
	Cella cellaDestinazioneRe = scacchieraGioco.getCella(Cella.endX(mossaRe), Cella.endY(mossaRe));

	Pezzo presuntoReGiocatoreAttuale = cellaPartenzaRe.getPezzoCorrente();

	Cella cellaPartenzaTorre = scacchieraGioco.getCella(Cella.startX(mossaTorre), Cella.startY(mossaTorre));
	Cella cellaDestinazioneTorre = scacchieraGioco.getCella(Cella.endX(mossaTorre), Cella.endY(mossaTorre));

	Pezzo presuntaTorreGiocatoreAttuale = cellaPartenzaTorre.getPezzoCorrente();

	Colore coloreGiocatoreAttivo = turnoGioco.getGiocatoreInTurno().getColore();
	// Se nella cella di partenza del presunto re c'e' il re del colore del
	// giocatore
	// in turno..
	if (cellaPartenzaRe.isOccupato() && presuntoReGiocatoreAttuale.getNome() == "Re"
		&& presuntoReGiocatoreAttuale.getColore() == coloreGiocatoreAttivo
		// e nella cella della presunta torre c'e' la torre del colore del giocatore in
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
     * Controlla se il Re non e' sotto scacco nel caso in cui un pezzo del suo
     * stesso colore si muove in un'altra cella. Viene applicata la mossa
     * temporaneamente per effettuare i controlli attraverso la funzione
     * isReSottoScacco: in caso positivo viene restituito un booleano con valore
     * false, altrimenti e' restituito un booleano con valore true. In entrambi i
     * casi viene ripristinata la situazione immediatamente precedente alla
     * applicazione della mossa.
     *
     * @param partenza
     * @param destinazione
     * @param tipoMossa
     * @return isReProtetto: falso se il Re è sotto scacco, vero altrimenti.
     */
    public boolean isReProtetto(final Cella partenza, final Cella destinazione) {

	Cella cellaRe = Re.findRe();
	Re reDaProteggere = (Re) cellaRe.getPezzoCorrente();
	Cella temp = new Cella(destinazione.getX(), destinazione.getY(), destinazione.getPezzoCorrente());
	boolean isReProtetto = false;
	if (partenza.getPezzoCorrente().getNome().equals("Re")) {
	    isReProtetto = true;
	    return isReProtetto;
	}
	scacchieraGioco.scambiaCella(partenza, destinazione);
	if (!reDaProteggere.isReSottoScacco(cellaRe)) {
	    isReProtetto = true;
	}
	scacchieraGioco.scambiaCella(destinazione, partenza);
	if (temp.isOccupato()) {
	    scacchieraGioco.getCella(temp.getX(), temp.getY()).aggiungiPezzo(temp.getPezzoCorrente());

	}
	return isReProtetto;
    }
}
