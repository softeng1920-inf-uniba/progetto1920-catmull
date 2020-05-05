/**
 *
 */
package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import gioco.Giocatore;
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
 * Classe che gestisce le varie funzionalita' del gioco
 */
public class Controller {

    private Menu menu;
    private static ArrayList<String> mosseConvertite;

    public Controller() {
	mosseConvertite = new ArrayList<String>();
	menu = new Menu();
	new Scacchiera();

    }

    /**
     * inizializzaPartita implementa la fase iniziale della partita
     */
    final void playGame() {

	boolean utenteVuoleRicominciare = false;
	do {
	    Scacchiera.inizializzaScacchiera();

	    System.out.println("\n\u2022" + " Digita 'Menu' per tornare al menu principale.");
	    System.out.println("\u2022" + " Digita 'Help' per visualizzare l'elenco dei comandi.");
	    new Turno();

	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String comando = "";

	    while (true) {

		System.out.println("\nE' il turno di " + Turno.getGiocatoreInTurno().getNome()
			+ " con le pedine di colore " + Turno.getGiocatoreInTurno().getColore() + ".");
		System.out.println(
			"-> Inserisci una mossa nella notazione algebrica (es. e4, exd3, exd3 e.p.); altrimenti digita una voce del menu.");

		try {
		    comando = br.readLine();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		System.out.println("\n");

		if (comando.equalsIgnoreCase(menu.help().getNome())) {
		    mostrareElencoComandiGioco();
		} else if (comando.equalsIgnoreCase(menu.board().getNome())) {
		    Scacchiera.stampa();
		} else if (comando.equalsIgnoreCase(menu.back().getNome())) {
		    System.out.println("\u265A" + "\u265B" + "  Menu principale " + "\u2655" + "\u2656" + " \n");
		    System.out.println("Digitare help per visualizzare la lista dei comandi");
		    return;
		} else if (comando.equalsIgnoreCase(menu.moves().getNome())) {
		    stampaMosseGiocate();
		} else if (comando.equalsIgnoreCase(menu.captures().getNome())) {
		    visualizzareCatture();
		} else if (comando.equalsIgnoreCase(menu.quit().getNome())) {
		    chiudiGioco();
		} else if (comando.equalsIgnoreCase(menu.play().getNome())) {
		    if (utenteConfermaRiavvioPartita()) {
			utenteVuoleRicominciare = true;
			new Scacchiera(); // Svuoto la scacchiera
			break;
		    } else
			continue; // Faccio ripartire il loop interno
		}

		if (isNotazioneAlgebrica(comando)) {

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

			} else {
			    System.out.println("Mossa illegale.");
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

			    } else
				System.out.println("Mossa illegale.");

			} else {
			    System.out.println("Mossa illegale.");
			}

		    }

		} else if (!isComandoValido(comando)) // Se il comando inserito non è una mossa, nè un comando di
						      // gioco...
		    System.out.println("Comando non corretto. Riprova!");

	    } // Fine loop di gioco
	} while (utenteVuoleRicominciare);
    }

    /**
     *
     * @param cellaPartenza
     * @param cellaDestinazione
     * @param scacchiera
     * @param mosseEffettuate
     * @return -1 se mossa illegale, 0 se mossa valida, 1 se en passant valido, 2 se
     *         arrocco
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
     * metodo che serve a modificare il comando a seconda del pezzo da muovere
     *
     *
     *
     * @param mossa in notazione algebrica
     * @return String
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

    }

    /**
     *
     * @param comandiArr Array di comandi già  convertiti
     * @return
     */
    public static boolean isMossaInRangeValido(String comando) {

	if (!isCoordinateValide(Cella.startX(comando), Cella.startY(comando), Cella.endX(comando), Cella.endY(comando)))
	    return false; // La mossa inserita non e' valida, va oltre i limiti della scacchiera

	return true;
    }

    public final void applicaMossa(Cella cellaPartenza, Cella cellaDestinazione, int tipoMossa) {

	Pezzo pezzoInCellaDestinazione = cellaDestinazione.getPezzoCorrente();
	Cella cellaAdiacenteEp = Scacchiera.getCella(cellaDestinazione.getX(), cellaPartenza.getY());
	Giocatore giocatoreAttivo = Turno.getGiocatoreInTurno();
	switch (tipoMossa) {

	case 0: // Caso Mossa Normale(Spostamento,Cattura) di un pezzo
	    if (cellaDestinazione.isOccupato()) {
		giocatoreAttivo.addPezziCatturati(pezzoInCellaDestinazione);
		cellaDestinazione.rimuoviPezzoCorrente();
	    }
	    break;
	case 1:// Caso mossa speciale ep
	    giocatoreAttivo.addPezziCatturati(cellaAdiacenteEp.getPezzoCorrente());
	    cellaAdiacenteEp.rimuoviPezzoCorrente();
	    break;
	default:

	}

	Scacchiera.scambiaCella(cellaPartenza, cellaDestinazione);

    }

    /**
     * Restituisce se le coordinate (necessariamente in interi, presi dalla stringa
     * principale del comando) sono valide. Non posso passare le celle perchÃ¨
     * altrimenti se il comando non fosse valido (es. b0 b1), il sistema
     * controllando b0 prendera' la cella -1 e andra'  in eccezione)
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    private static boolean isCoordinateValide(int startX, int startY, int endX, int endY) {
	// Controllo che il comando convertito sia valido per la scacchiera
	return Scacchiera.isRangeValido(startX, startY) && Scacchiera.isRangeValido(endX, endY);
    }

    private boolean utenteConfermaRiavvioPartita() {

	String comando = "";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	System.out.println(
		"Sei sicuro di voler iniziare una nuova partita? (Digita 'y' per confermare, 'n' altrimenti)\n");
	while (true) {
	    try {
		comando = br.readLine();
		switch (comando) {
		case "y":
		    return true;
		case "n":
		    return false;
		default:
		    System.out.println("Il comando inserito non e' valido. Riprova \n");
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

    }

    /**
     * La seguente funzione riconosce se il comando inserito e' un comando scritto
     * sottoforma di notazione algebrica. Il seguente comando puo' essere anche una
     * mossa non valida
     *
     * @param comando
     *
     * @return boolean
     */
    private boolean isComandoValido(final String comando) {

	if (comando.equalsIgnoreCase(menu.help().getNome()) || comando.equalsIgnoreCase(menu.back().getNome())
		|| comando.equalsIgnoreCase(menu.board().getNome())
		|| comando.equalsIgnoreCase(menu.captures().getNome())
		|| comando.equalsIgnoreCase(menu.moves().getNome())
		|| comando.equalsIgnoreCase(menu.quit().getNome())) {
	    return true;
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

	return mosseGiocateTotali;
    }

    /**
     * Stampa a video l'elenco delle mosse giocate del giocatore.
     */
    public void stampaMosseGiocate() {
	String mossa = null;
	int counter = 1;
	int dimensione = Turno.getGiocatoreInAttesa().getNumeroMosseGiocate()
		+ Turno.getGiocatoreInTurno().getNumeroMosseGiocate();
	if (dimensione == 0) {
	    System.out.println("Non e' stata giocata alcuna mossa");
	} else {
	    System.out.println("Storia delle mosse giocate");
	    for (int i = 0; i < dimensione; i++) {
		if (i == dimensione - 1) {
		    mossa = counter + ". " + fusioneListe().get(i);
		    System.out.println(mossa);
		} else {
		    mossa = counter + ". " + fusioneListe().get(i) + " " + fusioneListe().get(i + 1);
		    System.out.println(mossa);
		}
		i++;
		counter++;
	    }
	}
    }

    /**
     * Metodo che permette la visualizzazione dell 'elenco comandi del menu
     * principale: Quit play board
     */
    public void mostrareElencoComandiMenu() {
	System.out.println(menu.quit().toString());
	System.out.println(menu.play().toString());
	System.out.println(menu.board().toString());
    }

    /**
     * Metodo che permette la visualizzazione dell' elenco comandi del menu di
     * gioco: Quit board captures moves back play
     */
    public void mostrareElencoComandiGioco() {
	System.out.println(menu.back().toString());
	System.out.println(menu.play().toString());
	System.out.println(menu.board().toString());
	System.out.println(menu.captures().toString());
	System.out.println(menu.moves().toString());
	System.out.println(menu.quit().toString());
    }

    /**
     * Funzione che consente di chiudere il gioco e lasciare il controllo al sistema
     * operativo
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
     * Restituisce la lista delle mosse convertite in notazione comprensibile da
     * applicaMossa.
     */
    public void addMosseConvertite(String mossa) {
	mosseConvertite.add(mossa);
    }

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

	return false; // L'arrocco è stato chiamato con pezzi non compatibili / celle vuote

    }

}
