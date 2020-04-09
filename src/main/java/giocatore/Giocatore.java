package giocatore;
import pedine.*;
import java.util.*;

public class Giocatore {

	String nome;
	boolean colore;
	public ArrayList<Pezzo> pezziMorti;
	public ArrayList<String> mosseGiocate;

	public Giocatore(String nome, boolean colore) {

		this.colore = colore;
		this.nome = nome;
		pezziMorti = new ArrayList<Pezzo>();
		mosseGiocate = new ArrayList<String>();

	}

	public Giocatore(boolean colore) {

		this.colore = colore;

		try (Scanner scanner = new Scanner(System.in)) {

			String nomecolore = colore ? "Bianche" : "Nere";
			System.out.println("Inserire il nome del giocatore con le pedine ("+nomecolore+")");
			this.nome = scanner.nextLine();

		}

		pezziMorti = new ArrayList<Pezzo>();
		mosseGiocate = new ArrayList<String>();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setColore(boolean colore) {
		this.colore = colore;
	}

	public String getNome() {
		return nome;
	}

	public boolean getColore() {
		return colore;
	}

	public ArrayList<Pezzo> getPezziMorti() {
		return pezziMorti;
	}

	public ArrayList<String> getMosseGiocate(){
		return mosseGiocate;
	}

	public void stampaPezziMorti() {
		System.out.println(pezziMorti.toString());
	}

	public void stampaMosseGiocate() {
		System.out.println(mosseGiocate);
	}

}
