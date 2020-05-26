package gioco;

import java.util.ArrayList;

/**
 * La classe Turno serve per identificare il turno corrente e di attesa del
 * gioco, quindi definisce se sta giocando il giocatore bianco o nero.
 * <br>
 * Contiene le informazioni riguardo al turno del giocatore.
 * <br>
 * La classe Turno e' di tipo &lt;&lt;ENTITY&gt;&gt;
 */
public final class Turno {
    private static Turno instance = null;
    private Giocatore giocatoreInTurno;
    private Giocatore giocatoreInAttesa;

    private Turno() {
	giocatoreInTurno = new Giocatore(Colore.bianco);
	giocatoreInAttesa = new Giocatore(Colore.nero);
    }

    /**
     * Costruttore statico per la classe Singleton
     * 
     * @return  istanza del turno corrente
     */
    public static Turno getInstance() {
	if (instance == null) {
	    instance = new Turno();
	}
	return instance;
    }

    /**
     * Imposta il giocatore in turno e quello in attesa
     */
    public void inizializzaTurno() {
	giocatoreInTurno = new Giocatore(Colore.bianco);
	giocatoreInAttesa = new Giocatore(Colore.nero);
    }

    /**
     * Scambia il ruolo dei due giocatori
     */
    public void cambioTurno() {
	Giocatore temp = giocatoreInTurno;
	giocatoreInTurno = giocatoreInAttesa;
	giocatoreInAttesa = temp;
    }

    /**
     * Metodo che permette di ottenere il giocatore in turno
	 * @return  Giocatore corrente
	 */
    public Giocatore getGiocatoreInTurno() {
	return giocatoreInTurno;
    }
    /**
     * Metodo che permette di ottenere il giocatore in attesa
	 * @return  Giocatore in attesa
	 */
    public Giocatore getGiocatoreInAttesa() {
	return giocatoreInAttesa;
    }

    /**
	 * Modifica l'attributo di classe giocatoreInTurno
	 *
	 * @param  m Stringa indicante il giocatore che deve risultare in turno
	 */
    public void setNomeGiocatoreInTurno(final String m) {
	giocatoreInTurno.setNome(m);
    }
    /**
	 *  Modifica l'attributo di classe giocatoreInTurno
	 *
	 * @param  m Stringa indicante il giocatore che risultera' essere in attesa
	 */
    public void setNomeGiocatoreInAttesa(final String m) {
    	giocatoreInAttesa.setNome(m);
	}
    /**
	  * Fonde le due liste in cui sono conservate le mosse giocate di ogni giocatore.
	  * La fusione avviene in modo alternato.
	  * <br>
	  * Permette di avere una visione completa
	  * delle mosse giocate totali
	  *
	  * @return  lista di stringhe che permette di visualizzare le mosse effettuate e nel corretto ordine
	  */
    public ArrayList<String> getArrayStoriaMosse() {
	int i, j, k;
	int dimensione = getGiocatoreInAttesa().getNumeroMosseGiocate() + getGiocatoreInTurno().getNumeroMosseGiocate();
	ArrayList<String> mosseGiocateTotali = new ArrayList<String>(dimensione);
	if (getGiocatoreInTurno().getColore() == Colore.bianco) {
	    i = 0;
	    j = 0;
	    k = 0;
	    while (i < getGiocatoreInTurno().getNumeroMosseGiocate()
		    && j < getGiocatoreInAttesa().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, getGiocatoreInTurno().getMossaGiocata(i++));
		mosseGiocateTotali.add(k++, getGiocatoreInAttesa().getMossaGiocata(j++));
	    }
	    while (i < getGiocatoreInTurno().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, getGiocatoreInTurno().getMossaGiocata(i++));
	    }
	    while (j < getGiocatoreInAttesa().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, getGiocatoreInAttesa().getMossaGiocata(j++));
	    }
	} else {
	    i = 0;
	    j = 0;
	    k = 0;
	    while (i < getGiocatoreInAttesa().getNumeroMosseGiocate()
		    && j < getGiocatoreInTurno().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, getGiocatoreInAttesa().getMossaGiocata(i++));
		mosseGiocateTotali.add(k++, getGiocatoreInTurno().getMossaGiocata(j++));
	    }
	    while (i < getGiocatoreInAttesa().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, getGiocatoreInAttesa().getMossaGiocata(i++));
	    }
	    while (j < getGiocatoreInTurno().getNumeroMosseGiocate()) {
		mosseGiocateTotali.add(k++, getGiocatoreInTurno().getMossaGiocata(j++));
	    }
	}

	return mosseGiocateTotali;
    }

}
