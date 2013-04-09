package perso;

import model.Fonction;


/**
 * 
 * @author Julien
 * 
 * Classe personnelle qui permet d'ajouter des fonctions de periode.
 * Elles apparaitront dans l'interface graphique, onglet Fonctions.
 * En cliquant dessus, la période sera recalculée en utilisant la fonction de l'utilisateur.
 * 
 * La fonction doit avoir comme signature :
 * 	
 * 		public static Fonction fonctionName();
 * 
 * 
 * Elle retourne un objet implementant l'interface Fonction, c'est à dire
 * un object implementant la fonction double f(double);
 * 
 * En entrée : un double entre 0 et 1,
 * En sortie : un double entre -1 et 1;
 * 
 * La période allant de l'abscisse 0 à 1,
 * et son ordonnée allant de -1 à 1.
 *
 */
public class PersonalFunctions {
	
	/**
	 * 
	 * @return Fonction objet
	 */
	public static Fonction maFonctionPersonnelle() {
		return new Fonction() {
			
			public double f(double x) {
				return Math.sin(x * Math.PI * 2 * 4) * 0.2 + Math.sin(x * Math.PI * 2 * 6) * 0.8;
			}
		};
	}
	
	
	
}
