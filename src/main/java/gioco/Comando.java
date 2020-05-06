package gioco;

/**
 * Classe che implementa il comando
 */
//La classe Comando e' di tipo ENTITY
public class Comando {

    public final static int ARROCCO_CORTO = 1;
    public final static int ARROCCO_LUNGO = 2;

    private String nome;
    private String descrizione;

    /**
     * Metodo Costruttore
     *
     * @param nome
     * @param descrizione
     */
    Comando(String nome, String descrizione) {
	setNome(nome);
	setDescrizione(descrizione);
    }

    /**
     * Metodo che permette di modificare il nome del comando
     *
     * @param nome
     */
    void setNome(String nome) {
	this.nome = nome;
    }

    /**
     * Metodo che permette di modificare la descrizione del comando
     *
     * @param descrizione
     */
    void setDescrizione(final String descrizione) {
	this.descrizione = descrizione;
    }

    /**
     * @return nome del comando
     */
    public String getNome() {
	return nome;
    }

    /**
     * @return descrizione del comando
     */
    public String getDescrizione() {
	return descrizione;
    }

    /**
     * Metodo che permette di visualizzare i comandi
     */
    @Override
    public String toString() {
	return String.format("\u2022 " + "\033[1;37m" + nome + "\u001B[0m" + " -->    " + descrizione);
    }

    /**
     * Data la mossa, se è un arrocco ne restituisce il tipo, altrimenti viene
     * restituito -1
     *
     * @param mossa in notazione algebrica
     * @return int 1 => Arrocco corto | 2 => Arrocco lungo | -1 => Non è un arrocco
     */
    public static int isArrocco(String mossa) {

	if (mossa.matches("(0|o|O)-(0|o|O)"))
	    return ARROCCO_CORTO;
	else if (mossa.matches("(0|o|O)-(0|o|O)-(0|o|O)"))
	    return ARROCCO_LUNGO;

	return -1;

    }
}
