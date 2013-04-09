package model;

import java.lang.reflect.Method;
import java.util.ArrayList;

import perso.PersonalFunctions;


public class Fonctions {
	
	
	public static ArrayList<FonctionNamed> getFonctions(Class<?> classe) {
		ArrayList<FonctionNamed> ret = new ArrayList<FonctionNamed>();
		
		for (Method method : classe.getMethods()) {
			if(method.getReturnType().getSimpleName().equals("Fonction")) {
				String name = plusBeau(method.getName());
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
	
	private static String plusBeau(String s) {
		String ret = new String();
		
		ret += Character.toUpperCase(s.charAt(0));
		
		for(int i=1;i<s.length();i++) {
			char c = s.charAt(i);
			char next = 0;
			if((i + 1) < s.length()) {
				next = s.charAt(i+1);
			}
			
			if(c == '_' && next != 0) {
				ret += ' ';
				continue;
			}
			
			ret += Character.toLowerCase(c);
			
			
			if(next != 0 && Character.isUpperCase(next)) {
				ret += ' ';
				continue;
			}
			
			if(!Character.isDigit(c) && Character.isDigit(next)) {
				ret += ' ';
				continue;
			}
		}
		
		return ret;
	}
	
	public static ArrayList<FonctionNamed> getCoreFunctions() {
		return getFonctions(Fonctions.class);
	}
	
	public static ArrayList<FonctionNamed> getPersonalFunctions() {
		return getFonctions(PersonalFunctions.class);
	}
	
	
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
