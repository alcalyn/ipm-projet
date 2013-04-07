package views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
	
	private static final long serialVersionUID = -8890344412702786756L;
	
	
	
	private JMenu		fichier;
	private JMenuItem		nouveau;
	
	
	private JMenu		affichage;
	
	
	private JMenu		about;
	
	
	public Menu() {
		super();
		
		initFichier();
		initAffichage();
		initAbout();
	}
	
	
	
	private void initFichier() {
		fichier = new JMenu("Fichier");
		nouveau = new JMenuItem("Nouveau");
		
		fichier.add(nouveau);
		
		fichier.addSeparator();
		
		add(fichier);
	}
	
	private void initAffichage() {
		affichage = new JMenu("Affichage");
		
		add(affichage);
	}
	
	private void initAbout() {
		about = new JMenu("?");
		
		add(about);
	}
	
	
	
	
	

}
