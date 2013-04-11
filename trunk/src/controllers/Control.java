package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import appli.Modulation;

import model.FileManager;
import model.Fonction;
import model.Periode;
import model.PeriodeReader;
import views.About;
import views.PeriodeView;

public class Control implements ActionListener {
	
	
	public static final int
		SAVE = 7,
		SAVE_AS = 8,
		OPEN = 9,
		QUIT = 10,
		PLAY = 11,
		STOP = 12,
		NOUVEAU = 13,
		CHANGE_DUREE = 14,
		CHANGE_FREQUENCE = 15,
		SET_FONCTION = 16,
		ADD_FUNCTION = 17,
		DISPLAY_ABOUT_PROJECT = 18,
		SELECT_TOOL = 19,
		MODULER = 20,
		ADD_MODULATION = 21;
	
	
	private static Periode periode;
	private static PeriodeView periode_view;


	private int action;
	private Object [] args;
	

	public Control(int action) {
		this.action = action;
		this.args = new Object[0];
	}
	
	public Control(int action, Object ... args) {
		this.action = action;
		this.args = args;
	}


	public void actionPerformed(ActionEvent e) {
		String s;
		
		switch (action) {
			case SELECT_TOOL:
				periode_view.selectTool((Integer) args[0]);
				break;
			
			case OPEN:
				try {
					FileManager.load(periode);
				} catch (IOException e1) {
					e1.printStackTrace();
					error("Le fichier n'a pas pu être chargé");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					error("Le fichier n'a pas pu être chargé");
				}
				periode.flushCourbe();
				break;
			
			case SAVE:
				try {
					FileManager.save(periode);
				} catch (IOException e1) {
					e1.printStackTrace();
					error("Le fichier n'a pas pu être sauvegardé");
				}
				break;
			
			case SAVE_AS:
				try {
					FileManager.saveAs(periode);
				} catch (IOException e1) {
					e1.printStackTrace();
					error("Le fichier n'a pas pu être sauvegardé");
				}
				break;
			
			case QUIT:
				System.exit(0);
				break;
			
			case PLAY:
				PeriodeReader.play(periode);
				break;
			
			case STOP:
				PeriodeReader.stop();
				PeriodeReader.prepare(periode);
				break;
			
			case NOUVEAU:
				PeriodeReader.stop();
				periode.reinit();
				FileManager.newFile();
				PeriodeReader.prepare(periode);
				break;
			
			case CHANGE_DUREE:
				s = (String) JOptionPane.showInputDialog(
						periode_view,
						"Nouvelle duree de la periode en secondes :",
						"Parametre de la periode",
						JOptionPane.INFORMATION_MESSAGE,
						null,
						null,
						Double.toString(periode.duree())
				);
				
				if(s != null) {
					double d = Double.parseDouble(s);
					periode.duree(d);
				}
				
				break;
			
			case CHANGE_FREQUENCE:
				s = (String) JOptionPane.showInputDialog(
						periode_view,
						"Nouvelle duree de la periode en hertz :",
						"Parametre de la periode",
						JOptionPane.INFORMATION_MESSAGE,
						null,
						null,
						Double.toString(1.0 / periode.duree())
				);
				
				if(s != null) {
					double d = Double.parseDouble(s);
					periode.duree(1.0 / d);
				}
				
				break;
			
			case SET_FONCTION:
				periode.dessiner((Fonction) args[0]);
				break;
			
			case ADD_FUNCTION:
				JOptionPane.showMessageDialog(
						periode_view,
						"Pour ajouter une courbe, il suffit d'ajouter une fonction\ndans la classe " +
						"'PersonalFunctions' du package 'perso'.",
						"Ajouter une fonction",
						JOptionPane.INFORMATION_MESSAGE
				);
				break;
			
			case MODULER:
				periode.moduler((Modulation) args[0]);
				break;
			
			case ADD_MODULATION:
				JOptionPane.showMessageDialog(
						periode_view,
						"Pour ajouter une modulation, il suffit d'ajouter une fonction\ndans la classe " +
						"'PersonalModulations' du package 'perso'.",
						"Ajouter une modulation",
						JOptionPane.INFORMATION_MESSAGE
				);
				break;
			
			case DISPLAY_ABOUT_PROJECT:
				new About();
				break;
				
			default:
				break;
		}
	}
	
	
	public static PeriodeView getPeriode_view() {
		return periode_view;
	}

	public static void setPeriodeView(PeriodeView periode_view) {
		Control.periode_view = periode_view;
	}

	public static void setPeriode(Periode periode) {
		Control.periode = periode;
	}

	public static void error(String msg) {
		JOptionPane.showMessageDialog(
				periode_view,
				msg,
				"Oups...",
				JOptionPane.ERROR_MESSAGE
		);
	}
	
	
	public static Periode getPeriode() {
		return periode;
	}
	
	public static PeriodeView getPeriodeView() {
		return periode_view;
	}
	
}
