package it.uniba.main;

public class Comando {

	String nome;
	String descrizione;

	Comando(String nome, String descrizione) {
		setNome(nome);
		setDescrizione(descrizione);
	}

	void setNome(String nome) {
		this.nome = nome;
	}

	void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	String getNome() {
		return nome;
	}

	String getDescrizione() {
		return descrizione;
	}
}
