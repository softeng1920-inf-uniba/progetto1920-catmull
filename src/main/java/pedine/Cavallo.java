/** Classe per rappresentere il sottotipo di pezzo chiamato Cavallo */
package pedine;

import java.util.ArrayList;

import giocatore.Giocatore;
import it.uniba.main.Colore;
import scacchiera.Cella;
import scacchiera.Scacchiera;

/**
 * Rappresentazione astratta della classe cavallo
 *
 */
public final class Cavallo extends Pezzo {

	public Cavallo(final Colore colore, final Cella posizioneCorrente) {
		super("Cavallo", colore, posizioneCorrente);
		if (colore == Colore.nero) {
			simbolo = '\u265e';
		} else {
			simbolo = '\u2658';
		}
	}
	
	@Override
	public boolean isMossaValida(Cella start, Cella end, Scacchiera s) {
		if (end.isOccupato()) {
			if (this.colore != end.getPezzoCorrente().getColore()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public boolean isMossaSpeciale(Cella start, Cella end, Scacchiera s, ArrayList<String> mosse) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Converte la stringa in input in stringa leggibile dalla funzione applicaMossa.
	 * Ad esempio: Cde4 ==> d2 e4
	 * @param mossa
	 * @param s
	 * @param g
	 * @return
	 */
	public static String ConvertiMossa(String mossa, Scacchiera s, Giocatore g) {
		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		int ambiguita = 0;
		//stringa standard da restituire in caso di mossa non valida per i controlli nella classe controller
		String mossaConvertita = "a0 a0"; 	
		
		//esempio: Cdxe4
		if (mossa.matches("[C][a-h][x|:]([a-h][1-8])")) {
			startX = Cella.coordXinInt(mossa.charAt(1));
			endX = Cella.coordXinInt(mossa.charAt(3));
			endY = Cella.coordYinInt(mossa.charAt(4));
			if (s.getNomePezzo(endX, endY) == "Vuota") {
				return mossaConvertita;
			}
		}
		//esempio: Cxe4
		if (mossa.matches("[C][x|:]([a-h][1-8])")) {
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (s.getNomePezzo(endX, endY) == "Vuota") {
				return mossaConvertita;
			}
		}
		//esempio: C6xe4
		if (mossa.matches("[C][1-8][x|:]([a-h][1-8])")) {
			startY= Cella.coordYinInt(mossa.charAt(1));
			endX = Cella.coordXinInt(mossa.charAt(3));
			endY = Cella.coordYinInt(mossa.charAt(4));
			if (s.getNomePezzo(endX, endY) == "Vuota") {
				return mossaConvertita;
			}
		} 
		//esempio: Cde4
		if (mossa.matches("[C][a-h]([a-h][1-8])")) {
			startX = Cella.coordXinInt(mossa.charAt(1));
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (s.getNomePezzo(endX, endY) != "Vuota") {
				return mossaConvertita;
			}
		}
		//esempio: C3e4 
		if (mossa.matches("[C][1-8]([a-h][1-8])")) {
			startY = Cella.coordYinInt(mossa.charAt(1));
			endX = Cella.coordXinInt(mossa.charAt(2));
			endY = Cella.coordYinInt(mossa.charAt(3));
			if (s.getNomePezzo(endX, endY) != "Vuota") {
				return mossaConvertita;
			}
		}
		//esempio: Ce4 
		if (mossa.matches("[C]([a-h][1-8])")) {
			endX = Cella.coordXinInt(mossa.charAt(1));
			endY = Cella.coordYinInt(mossa.charAt(2));
			if (s.getNomePezzo(endX, endY) != "Vuota") {
				return mossaConvertita;
			}
		}
		
		//ricaviamo X o Y a seconda di quella diversa da -1
		if(startX != -1) { //startX data in input
			if(Math.abs(startX - endX) == 2) {
				if(s.getNomePezzo(startX, endY - 1) == "Cavallo" && 
						g.getColore() == s.getCella(startX, endY - 1).getPezzoCorrente().getColore()) {
					startY = endY - 1;
				}
				if(s.getNomePezzo(startX, endY + 1) == "Cavallo" &&
						g.getColore() == s.getCella(startX, endY + 1).getPezzoCorrente().getColore()) {
					startY = endY + 1;
				}
			}
				if(Math.abs(startX - endX) == 1) {
					if(s.getNomePezzo(startX, endY - 2) == "Cavallo" &&
							g.getColore() == s.getCella(startX, endY - 2).getPezzoCorrente().getColore()) {
						startY = endY - 2;
					}
					if(s.getNomePezzo(startX, endY + 2) == "Cavallo" &&
							g.getColore() == s.getCella(startX, endY + 2).getPezzoCorrente().getColore()) {
						startY = endY + 2;
					}
				}
		} else if (startY != -1) { //startY data in input
			if (Math.abs(startY - endY) == 2) {
				if(s.getNomePezzo(endX - 1, startY) == "Cavallo" &&
						g.getColore() == s.getCella(endX - 1, startY).getPezzoCorrente().getColore()) {
					startX = endX - 1;
				}
				if(s.getNomePezzo(endX + 1, startY) == "Cavallo" &&
						g.getColore() == s.getCella(endX + 1, startY).getPezzoCorrente().getColore()) {
					startX = endX + 1;
				}
			}
			if (Math.abs(startY - endY) == 1) {
				if(s.getNomePezzo(endX - 2, startY) == "Cavallo" &&
						g.getColore() == s.getCella(endX - 2, startY).getPezzoCorrente().getColore()) {
					startX = endX - 2;
				}
				if(s.getNomePezzo(endX + 2, startY) == "Cavallo" &&
						g.getColore() == s.getCella(endX + 2, startY).getPezzoCorrente().getColore()) {
					startX = endX + 2;
				}
			}
		} else { // startX e startY uguali entrambe a -1, quindi entrambe non date in input
			if (s.getNomePezzo(endX - 2, endY - 1) == "Cavallo" &&
					g.getColore() == s.getCella(endX - 2, endY - 1).getPezzoCorrente().getColore()) {
				startX = endX - 2;
				startY = endY - 1;
				ambiguita++;
			}
			if (s.getNomePezzo(endX - 2, endY + 1) == "Cavallo" &&
					g.getColore() == s.getCella(endX - 2, endY + 1).getPezzoCorrente().getColore()) {
				startX = endX - 2;
				startY = endY + 1;
				ambiguita++;
			}
			if (s.getNomePezzo(endX + 2, endY + 1) == "Cavallo" &&
					g.getColore() == s.getCella(endX + 2, endY + 1).getPezzoCorrente().getColore()) {
				startX = endX + 2;
				startY = endY + 1;
				ambiguita++;
			}
			if (s.getNomePezzo(endX + 2, endY - 1) == "Cavallo" &&
					g.getColore() == s.getCella(endX + 2, endY - 1).getPezzoCorrente().getColore()) {
				startX = endX + 2;
				startY = endY - 1;
				ambiguita++;
			}
			if (s.getNomePezzo(endX + 1, endY - 2) == "Cavallo" &&
					g.getColore() == s.getCella(endX + 1, endY - 2).getPezzoCorrente().getColore()) {
				startX = endX + 1;
				startY = endY - 2;
				ambiguita++;
			}
			if (s.getNomePezzo(endX + 1, endY + 2) == "Cavallo" &&
					g.getColore() == s.getCella(endX + 1, endY + 2).getPezzoCorrente().getColore()) {
				startX = endX + 1;
				startY = endY + 2;
				ambiguita++;
			}
			if (s.getNomePezzo(endX - 1, endY + 2) == "Cavallo" &&
					g.getColore() == s.getCella(endX - 1, endY + 2).getPezzoCorrente().getColore()) {
				startX = endX - 1;
				startY = endY + 2;
				ambiguita++;
			}
			if (s.getNomePezzo(endX - 1, endY - 2) == "Cavallo" &&
					g.getColore() == s.getCella(endX - 1, endY - 2).getPezzoCorrente().getColore()) {
				startX = endX - 1;
				startY = endY - 2;
				ambiguita++;
			}
		}
		
		if (s.controllaRange(startX, startY) && s.getCella(startX, startY).isOccupato() &&
				ambiguita <= 1) {
				mossaConvertita = Cella.coordXinChar(startX) + "" +
						+ Cella.coordYinChar(startY) + " "
						+ Cella.coordXinChar(endX) + "" +
						+ Cella.coordYinChar(endY);
			}
		return mossaConvertita;
	}

}
