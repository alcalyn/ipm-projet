package views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.Control;

public class Menu extends JMenuBar {
	
	private static final long serialVersionUID = -8890344412702786756L;
	
	
	
	private JMenu		fichier;
	private JMenuItem		nouveau;
	private JMenuItem		open;
	private JMenuItem		save;
	private JMenuItem		save_as;
	
	
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
		open = new JMenuItem("Ouvrir");
		save = new JMenuItem("Enregistrer");
		save_as = new JMenuItem("Enregistrer sous...");
		
		
		open.addActionListener(new Control(Control.OPEN));
		
		save.addActionListener(new Control(Control.SAVE));
		save_as.addActionListener(new Control(Control.SAVE_AS));
		
		fichier.add(nouveau);
		fichier.add(open);
		
		fichier.addSeparator();
		
		fichier.add(save);
		fichier.add(save_as);
		
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
