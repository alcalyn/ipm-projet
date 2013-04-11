package perso;

import model.Fonction;
import appli.Modulation;

public class PersonalModulations {
	
	
	public static Modulation maModulationPersonnelle() {
		return new Modulation() {

			@Override
			public double f(double x, Fonction fonction) {
				// Exemple : ajoute une frequence 24 fois plus elevee
				return fonction.f(x) * 0.9 + Math.sin(24 * x * Math.PI * 2) * 0.1;
			}
		};
	}
	
	
}
