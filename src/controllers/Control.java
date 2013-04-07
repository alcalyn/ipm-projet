package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import views.PeriodeView;

import model.FileManager;
import model.Periode;
import model.PeriodeReader;

public class Control implements ActionListener {
	
	
	public static final int
		SELECT_TOOL_SNAP = 5,
		SELECT_TOOL_WRITE = 6,
		SAVE = 7,
		SAVE_AS = 8,
		OPEN = 9,
		QUIT = 10,
		PLAY = 11,
		STOP = 12,
		NOUVEAU = 13,
		CHANGE_DUREE = 14,
		CHANGE_FREQUENCE = 15;
	
	
	private static Periode periode;
	private static PeriodeView periode_view;


	private int action;
	

	public Control(int action) {
		this.action = action;
	}


	public void actionPerformed(ActionEvent e) {
		String s;
		
		switch (action) {
			case SELECT_TOOL_SNAP:
				periode_view.selectTool(Tool.SNAP);
				break;
				
			case SELECT_TOOL_WRITE:
				periode_view.selectTool(Tool.WRITE);
				break;
			
			case OPEN:
				try {
					FileManager.load(periode);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				periode.flushCourbe();
				break;
			
			case SAVE:
				try {
					FileManager.save(periode);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			
			case SAVE_AS:
				try {
					FileManager.saveAs(periode);
				} catch (IOException e1) {
					e1.printStackTrace();
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
				
			default:
				break;
		}
	}
	
	
	public static Periode getPeriode() {
		return periode;
	}


	public static void setPeriode(Periode periode) {
		Control.periode = periode;
	}
	
	public static PeriodeView getPeriodeView() {
		return periode_view;
	}


	public static void setPeriodeView(PeriodeView periode_view) {
		Control.periode_view = periode_view;
	}
	
}
