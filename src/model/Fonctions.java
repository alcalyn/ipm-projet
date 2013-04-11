package model;

import java.lang.reflect.Method;
import java.util.ArrayList;

import appli.CoreModulations;
import appli.Modulation;

import perso.PersonalFunctions;
import perso.PersonalModulations;


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
	
	public static ArrayList<ModulationNamed> getModulations(Class<?> classe) {
		ArrayList<ModulationNamed> ret = new ArrayList<ModulationNamed>();
		
		for (Method method : classe.getMethods()) {
			if(method.getReturnType().getSimpleName().equals("Modulation")) {
				String name = plusBeau(method.getName());
				Modulation f = null;
				
				try {
					f = (Modulation) method.invoke(null, new Object[0]);
					ret.add(new ModulationNamed(f, name));
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
		return getFonctions(CoreFunctions.class);
	}
	
	public static ArrayList<FonctionNamed> getPersonalFunctions() {
		return getFonctions(PersonalFunctions.class);
	}
	
	public static ArrayList<ModulationNamed> getCoreModulations() {
		return getModulations(CoreModulations.class);
	}
	
	public static ArrayList<ModulationNamed> getPersonalModulations() {
		return getModulations(PersonalModulations.class);
	}
	
}
