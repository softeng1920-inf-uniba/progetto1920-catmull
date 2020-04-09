package scacchiera;

import pedine.Pezzo;

public class Cella {
	int x;
	int y;
	boolean occupato;
	Pezzo pezzoCorrente;
	
	
	public Cella(int x,int y,Pezzo pezzoCorrente) {
		this.x=x;
		this.y=y;
		this.pezzoCorrente=pezzoCorrente;
		if(this.pezzoCorrente!=null)
			this.occupato=true;
	}

	void stampapezzo() {
		if(pezzoCorrente!=null)
			pezzoCorrente.disegnapezzo();
		else
			System.out.print(" . ");
	}
	
	//---------Metodi di setting---------

	void setX(int x) {
		this.x=x;
	}
	void setY(int y) {
		this.y=y;
	}
	void setOccupato(boolean occupato) {
		this.occupato=occupato;
	}
	public void setPezzoCorrente(Pezzo pezzoCorrente) {
		this.pezzoCorrente=pezzoCorrente;
	}
    
	//--------Metodi di Get--------
	
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	boolean getOccupato() {
		return occupato;
	}
	Pezzo getPezzoCorrente() {
		return pezzoCorrente;
	}

	//------Metodi per aggiugere o togliere pezzi dalla scacchiera
	
	public void aggiugi_pezzo(Pezzo nuovoPezzo){
        pezzoCorrente=nuovoPezzo;
        occupato = true;
    }

    public void rimuoviPezzoCorrente(){
        occupato = false;
        pezzoCorrente = null;
    }


}
