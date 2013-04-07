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
	private JMenuItem		quit;
	
	
	private JMenu		son;
	private JMenuItem		lire;
	private JMenuItem		stopper;
	
	
	private JMenu		affichage;
	
	
	private JMenu		about;
	
	
	public Menu() {
		super();
		
		initFichier();
		initSon();
		initAffichage();
		initAbout();
	}
	
	
	
	private void initFichier() {
		fichier = new JMenu("Fichier");
		nouveau = new JMenuItem("Nouveau");
		open = new JMenuItem("Ouvrir");
		save = new JMenuItem("Enregistrer");
		save_as = new JMenuItem("Enregistrer sous...");
		quit = new JMenuItem("Quitter");
		
		open.addActionListener(new Control(Control.OPEN));
		save.addActionListener(new Control(Control.SAVE));
		save_as.addActionListener(new Control(Control.SAVE_AS));
		quit.addActionListener(new Control(Control.QUIT));
		
		fichier.add(nouveau);
		fichier.add(open);
		
		fichier.addSeparator();
		
		fichier.add(save);
		fichier.add(save_as);
		
		fichier.addSeparator();
		
		fichier.add(quit);
		
		add(fichier);
	}
	
	private void initSon() {
		son = new JMenu("Son");
		lire = new JMenuItem("Lire");
		stopper = new JMenuItem("Stopper");
		
		lire.addActionListener(new Control(Control.PLAY));
		stopper.addActionListener(new Control(Control.STOP));
		
		son.add(lire);
		son.add(stopper);
		
		add(son);
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
