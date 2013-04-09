package model;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class Fonctions {
	
	
	public static ArrayList<FonctionNamed> getFonctions(Class<?> classe) {
		ArrayList<FonctionNamed> ret = new ArrayList<FonctionNamed>();
		
		for (Method method : classe.getMethods()) {
			if(method.getReturnType().getSimpleName().equals("Fonction")) {
				String name = method.getName();
				Fonction f = null;
				
				try {
					f = (Fonction) method.invoke(null, new Object[0]);
					ret.add(new FonctionNamed(f, name));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
	
	public static ArrayList<FonctionNamed> getCoreFunctions() {
		return getFonctions(Fonctions.class);
	}
	
	
	public static Fonction sinusoide() {
		return new Fonction() {
			
			public double f(double x) {
				return Math.sin(x * Math.PI * 2);
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
