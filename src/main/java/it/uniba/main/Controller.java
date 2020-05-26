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
 * una nuova partita o di effettuarne una.
 * <br>
 * La classe Controller e' di tipo &lt;&lt;CONTROL&gt;&gt;
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
     *
     * @return  istanza della classe corrente
     */

    public static Controller getInstance() {
	if (istance == null) {
	    istance = new Controller();
	}
	return istance;
    }

   /**
	  * Crea la partita e inizializza il gioco
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
	  * restituisce true se la mossa e' valida ed e' stata applicata altrimenti
	  * restituisce false
	  *
	  * @param  mossaDaGiocare Stringa rappresentante la mossa inserita dal giocatore
	  *
	  * @return  true se la mossa e' valida; false altrimenti
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
	  * Data la notazione algebrica inserita dall'utente, l'algoritmo restituisce:
	  *  <ul>
	  *    <li>0 se e' una mossa semplice;</li>
	  *    <li>1 se e' una mossa di cattura;</li>
	  *    <li>2 se e' un arrocco;</li>
	  *    <li>se e' di sicuro un en passant.</li>
	  *  </ul>
	  *
	  * @param  mossaInInput Stringa rappresentante la mossa in notazione algebrica
	  *
	  * @return  intero rappresentante il tipo di mossa
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
	  * Trasforma il comando inserito dall'utente,a seconda del pezzo da muovere,
	  * in notazione estesa
	  *
	  * @param  mossa Stringa in notazione algebrica
	  * @return  comando in notazione estesa
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
	  * Dopo che sono stati effettuati i vari controlli,
	  * a seconda della tipologia della mossa effettua
	  * una cattura particolare nel caso di En Passant, una cattura o un
	  * avanzamento nel caso di mossa normale
	  *
	  * @param  cellaPartenza Cella contenente il pezzo da spostare
	  * @param  cellaDestinazione Cella in cui si desidera spostare il pezzo contenuto in cellaPartenza
	  * @param  tipoMossa int indicante la tipologia della mossa:
	  * 			         <ul>
	  * 				       <li>0: Mossa normale (avanzata,cattura) di un pezzo</li>
	  * 				       <li>1: Mossa speciale (en passant) del pedone</li>
	  * 			         </ul>
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

   /** Restituisce una lista di tutte le mosse convertite
	  * @return  Lista delle mosse convertite in notazione comprensibile da
	  * applicaMossa
	  */
    public ArrayList<String> getMosseConvertite() {
	return mosseConvertite;
    }

   /**
	  * Aggiunge la mossa effettuata fra quelle convertite
	  *
	  * @param  mossa Stringa da aggiungere all'attributo di classe mosseConvertite
	  */
    public void addMosseConvertite(final String mossa) {
	mosseConvertite.add(mossa);
    }

   /**
	  * Data la mossa del Re e quella della torre, vengono effettuati tutti i
	  * controlli che verificano se la mossa sia consentita o meno.
	  * <p>
	  * L'arrocco e' valido se sono verificate tutte le seguenti condizioni:
	  *        <ul>
	  *          <li>il giocatore non ha ancora mosso ne' il Re ne' la torre coinvolta nell'arrocco;</li>
	  *          <li> non ci devono essere pezzi (amici o avversari) fra il Re e la torre usata;</li>
	  *          <li>ne' la casa di partenza del Re, ne' la casa che esso deve</li>
	  *          <li>attraversare, ne' quella di arrivo devono essere attaccabili da un
	  *           pezzo avversario</li>
	  *        </ul>
	  *
	  * @param  mossaRe Stringa indicante la mossa del Re
	  * @param  mossaTorre Stringa indicante la mossa della torre
	  * @param  tipoArrocco Intero indicante il tipo di arrocco
	  *
	  * @return  true se l'arrocco puo' essere effettuato, false altrimenti
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
	  * Controlla se il Re non sia sotto scacco nel caso in cui un pezzo del suo
	  * stesso colore si muova in un'altra cella.
	  * <br>
	  * Viene applicata la mossa temporaneamente per effettuarne i controlli attraverso la funzione
	  * isReSottoScacco: in caso positivo viene restituito un booleano con valore
	  * false, altrimenti e' restituito un booleano con valore true.
	  * <br>
	  * In entrambi i casi viene ripristinata la situazione immediatamente precedente alla
	  * applicazione della mossa.
	  *
	  * @param  partenza Cella da cui si muove il pezzo
	  * @param  destinazione Cella in cui si desidera far giungere il pezzo
	  *
	  * @return  false se il Re e' sotto scacco, true altrimenti.
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
