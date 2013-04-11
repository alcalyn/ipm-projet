package model;


public class CoreModulations {
	
	
	
	public static Modulation attenuer() {
		return new Modulation() {

			@Override
			public double f(double x, Fonction fonction) {
				return fonction.f(x) * 0.9;
			}
		};
	}
	
	
	public static Modulation ajouterDuBruit() {
		return new Modulation() {

			@Override
			public double f(double x, Fonction fonction) {
				return fonction.f(x) + (Math.random() * 0.2 - 0.1);
			}
		};
	}
	
}
