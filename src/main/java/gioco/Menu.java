package gioco;

/**
 * La classe Menu implementa due menu:
 * <ul>
 * 	<li> il menu principale che compare all'inizio del gioco </li>
 *  	<li> il menu che compare durante la partita </li>
 * </ul>
 * La classe contiene le informazioni riguardo ai comandi help, moves, quit, board, captures e play.
 * <br>
 * La classe Menu e' di tipo &lt;&lt;ENTITY&gt;&gt;
 */
public final class Menu {

    private static Menu istance = null;

    private Comando help;
    private Comando moves;
    private Comando quit;
    private Comando board;
    private Comando captures;
    private Comando play;

    public static final int ARROCCO_CORTO = 1;
    public static final int ARROCCO_LUNGO = 2;

    public static final int COLONNA_PARTENZA_MOSSA_NE = 0;
    public static final int TRAVERSA_PARTENZA_MOSSA_NE = 1;

    public static final int COLONNA_DESTINAZIONE_MOSSA_NE = 3;
    public static final int TRAVERSA_DESTINAZIONE_MOSSA_NE = 4;

	 /**
	  * Costruttore per classe Singleton
	  */
    private Menu() {
	help = new Comando("Help", "    Visualizza nome e descrizione dell'elenco comandi");
	moves = new Comando("Moves", "   Visualizza la cronologia delle mosse giocate");
	quit = new Comando("Quit", "    Esci dal gioco");
	board = new Comando("Board", "   Visualizza la posizione sulla scacchiera");
	captures = new Comando("Captures", "Visualizza le catture del Bianco e del Nero");
	play = new Comando("Play", "    Inizia una nuova partita");
    }

    /**
     * Costruttore statico per classe Singleton
     *
     * @return  istanza della classe corrente
     */
    public static Menu getInstance() {
	if (istance == null) {
	    istance = new Menu();
	}
	return istance;
    }

   /**
    * Consente di chiamare il comando "help"
	  *
	  * @return  comando che permette di aiutare un utente nel cercare una voce del menu
	  */
    public Comando help() {
	return help;
    }

   /**
	  * Consente di chiamare il comando "moves"
	  *
  	* @return  comando che consente di visualizzare la cronologia delle mosse giocate
	  */
    public Comando moves() {
	return moves;
    }

   /**
	  * Consente di chiamare il comando "quit"
	  *
	  * @return  comando che consente di uscire dal gioco
	  */
    public Comando quit() {
	return quit;
    }

   /**
	  * Consente di chiamare il comando "board"
	  *
	  * @return  comando che consente di visualizzare la scacchiera del gioco
	  */
    public Comando board() {
	return board;
    }

   /**
	  * Consente di chiamare il comando "captures"
	  *
	  * @return  comando che consente di visualizzare le catture
	  */
    public Comando captures() {
	return captures;
    }

   /**
	  * Consente di chiamare il comando "play"
	  *
	  * @return  comando che consente di iniziare una nuova partita
	  */
    public Comando play() {
	return play;
    }

   /**
	  * Data la mossa, se e' un arrocco ne restituisce il tipo, altrimenti viene
	  * restituito -1
	  *
	  * @param  mossa  Stringa in notazione algebrica
	  * @return  <ul>
	  *            <li>1 se Arrocco corto;</li>
	  *            <li>2 se Arrocco lungo;</li>
	  *            <li>-1 se non e' un Arrocco</li>
	  *          </ul>
	  */
    public int isArrocco(final String mossa) {

	if (mossa.matches("(0|o|O)-(0|o|O)")) {
	    return ARROCCO_CORTO;
	} else if (mossa.matches("(0|o|O)-(0|o|O)-(0|o|O)")) {
	    return ARROCCO_LUNGO;
	}
	return -1;
    }

   /**
	  * Riconosce se il comando dato in input rientra tra quelli del menu di gioco.
	  * <br>
	  * I comandi validi nel menu di gioco sono:
	  * <ul>
	  * 	<li> help </li>
	  * 	<li> board </li>
	  * 	<li> captures </li>
	  * 	<li> moves </li>
	  * 	<li> quit </li>
	  * </ul>

	  *
	  * @param  comando Comando dato in input
	  *
	  * @return  true se il comando rientra tra quelli del menu di gioco, false
	  *          altrimenti
	  */
    public boolean isComandoValido(final String comando) {

	return (comando.equalsIgnoreCase(help().getNome()) || comando.equalsIgnoreCase(board().getNome())
		|| comando.equalsIgnoreCase(captures().getNome()) || comando.equalsIgnoreCase(moves().getNome())
		|| comando.equalsIgnoreCase(quit().getNome()));

    }

   /**
	  * Controlla attraverso un'espressione regolare se la stringa inserita
	  * dall'utente e' riconosciuta come notazione algebrica.
	  *
	  * @param  mossa Stringa rappresentante la mossa inserita
	  * 
	  * @return  true se la mossa e' riconosciuta come notazione algebrica; false altrimenti
	  */
    public boolean isNotazioneAlgebrica(final String mossa) {

	String regex = String.join("|", new String[] {"([a-h](x|:))?([a-h][1-8])( e.p.)?", // mossa del pedone
		"D([x|:])?[a-h][1-8]", // mossa della regina
		"T([a-h]|[1-8])?([x|:])?([a-h][1-8])", // mossa della torre
		"C([a-h]|[1-8])?([x|:])?([a-h][1-8])", // mossa cavallo
		"A(x|:)?[a-h][1-8]", // mossa alfiere
		"R(x|:)?[a-h][1-8]", // mossa del re
		"(0|o|O)-(0|o|O)(-(0|o|O))?" // arrocco corto o lungo
	});

	return mossa.matches(regex);
    }

}
