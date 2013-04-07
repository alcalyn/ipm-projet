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
	
	
	private JMenu		periode;
	private JMenuItem		lire;
	private JMenuItem		stopper;
	private JMenu			change_duree;
	private JMenuItem			change_duree_sec;
	private JMenuItem			change_duree_hz;
	
	
	private JMenu		affichage;
	
	
	private JMenu		about;
	
	
	public Menu() {
		super();
		
		initFichier();
		initPeriode();
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
		
		nouveau.addActionListener(new Control(Control.NOUVEAU));
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
	
	private void initPeriode() {
		periode = new JMenu("Periode");
		lire = new JMenuItem("Lire");
		stopper = new JMenuItem("Stopper");
		change_duree = new JMenu("Modifier duree");
		change_duree_sec = new JMenuItem("Saisir un temps");
		change_duree_hz = new JMenuItem("Saisir une frequence (1/t)");
		
		lire.addActionListener(new Control(Control.PLAY));
		stopper.addActionListener(new Control(Control.STOP));
		change_duree_sec.addActionListener(new Control(Control.CHANGE_DUREE));
		change_duree_hz.addActionListener(new Control(Control.CHANGE_FREQUENCE));
		
		periode.add(lire);
		periode.add(stopper);
		periode.addSeparator();
		change_duree.add(change_duree_sec);
		change_duree.add(change_duree_hz);
		periode.add(change_duree);
		
		add(periode);
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
