/** Classe per rappresentere il sottotipo di pezzo chiamato Cavallo */
package pedine;

import scacchiera.Cella;

/** Rappresentazione astratta della classe cavallo
 *
 */
public final class Cavallo extends Pezzo {

	public Cavallo(final boolean colore, final Cella posizioneCorrente) {
		super("Cavallo", colore, posizioneCorrente);
	}

	@Override
	public void disegnapezzo() {
		// TODO Auto-generated method stub
		char simbolo;
		if (getColore()) {
			simbolo = 'N';
		} else {
			simbolo = 'n';
		}
		System.out.print(" " + simbolo + " ");
	}
}
