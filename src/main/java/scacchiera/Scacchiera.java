package scacchiera;
import java.util.ArrayList;
import pedine.*;

public class Scacchiera {

	int numerocolonne=8;
	int numerorighe=8;
	int numeropezzi=32;
	public ArrayList<Pezzo> pezzidelgioco;
	Cella[][] scacchiera;


	public int getNumeroColonne() {
		return numerocolonne;
	}
	public int getNumeroRighe() {
		return numerorighe;
	}
	public Cella getCella(int x,int y) {
		return scacchiera[x][y];
	}
	public Scacchiera(){

		pezzidelgioco = new ArrayList<Pezzo>();
		scacchiera = new Cella[numerocolonne][numerorighe];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				scacchiera[i][j]=new Cella(i,j,null);
			}
		}
	}




}
