package pedine;

import java.util.ArrayList;

import scacchiera.*;

public abstract class Pezzo {

	String nome;
	boolean colore;
	boolean vivo;
	Cella posizioneCorrente;
	ArrayList<Pezzo> pezziMangiati = new ArrayList<Pezzo>();

	public Pezzo(String nome, boolean colore, Cella posizioneCorrente) {
		this.nome = nome;
		this.colore = colore;
		this.posizioneCorrente = posizioneCorrente;
		vivo = true;
	}

	public abstract void disegnapezzo();
	// public abstract void mossevalide();

	// --------Metodi di setting --------

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setColore(boolean colore) {
		this.colore = colore;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public void setPosizioneCorrente(Cella posizioneCorrente) {
		this.posizioneCorrente = posizioneCorrente;
	}

	public void setPezziMangiati(ArrayList<Pezzo> pezziMangiati) {
		this.pezziMangiati = pezziMangiati;
	}

	// --------Metodi di Get--------

	public String getNome() {
		return nome;
	}

	public boolean getColore() {
		return colore;
	}

	public boolean getVivo() {
		return vivo;
	}

	public Cella getPosizioneCorrente() {
		return posizioneCorrente;
	}

	public ArrayList<Pezzo> getPezziMangiati() {
		return pezziMangiati;
	}

	@Override
	public String toString() {
		String colore = this.colore ? "Bianco" : "Nero";
		return String.format(nome + " " + colore);
	}

}