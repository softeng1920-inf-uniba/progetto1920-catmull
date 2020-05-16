package gioco;

/**
 * La classe Comando definisce la struttura dei comandi mostrati nel menu del
 * gioco attraverso gli attributi nome e descrizione. La classe Comando e' di
 * tipo ENTITY.
 */

public class Comando {

	// NE sta per notazione estesa
    public static final int COLONNA_PARTENZA_MOSSA_NE = 0;
    public static final int TRAVERSA_PARTENZA_MOSSA_NE = 1;

    public static final int COLONNA_DESTINAZIONE_MOSSA_NE = 3;
    public static final int TRAVERSA_DESTINAZIONE_MOSSA_NE = 4;

    private String nome;
    private String descrizione;

    /**
     * Metodo Costruttore
     *
     * @param n
     * @param desc
     */
    Comando(final String n, final String desc) {
	setNome(n);
	setDescrizione(desc);
    }

    /**
     * Metodo che permette di modificare il nome del comando
     *
     * @param n
     */
    void setNome(final String n) {
	this.nome = n;
    }

    /**
     * Metodo che permette di modificare la descrizione del comando
     *
     * @param d
     */
    void setDescrizione(final String d) {
	this.descrizione = d;
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

}
