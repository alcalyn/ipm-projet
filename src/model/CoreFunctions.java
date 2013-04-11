package model;

public class CoreFunctions {
	
	
	
	public static Fonction sinusoide() {
		return new Fonction() {
			
			public double f(double x) {
				return Math.sin(x * Math.PI * 2);
			}
		};
	}
	
	public static Fonction carree() {
		return new Fonction() {
			
			public double f(double x) {
				return x < 0.5 ? -1 : 1;
			}
		};
	}
	
	public static Fonction triangle() {
		return new Fonction() {
			
			public double f(double x) {
				if(x < 0.25) {
					return (x * 4);
				} else if(x < 0.75) {
					return (2 * (x - 0.25) * -2) + 1;
				} else {
					return ((x - 0.75) * 4) - 1;
				}
			}
		};
	}
	
	public static Fonction dentsDeScie() {
		return new Fonction() {
			
			public double f(double x) {
				return x * 2 - 1;
			}
		};
	}
	
	public static Fonction silence() {
		return new Fonction() {
			
			public double f(double x) {
				return 0;
			}
		};
	}
	
	public static Fonction bruit() {
		return new Fonction() {
			
			public double f(double x) {
				return (Math.random() * 2) - 1;
			}
		};
	}
}
