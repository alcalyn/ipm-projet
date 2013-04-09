package views;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.FonctionNamed;
import model.Fonctions;
import model.Res;
import controllers.Control;

public class TabbedTools extends JPanel {
	
	private static final long serialVersionUID = 2505664005430624227L;
	
	
	private static final Dimension dim_tab = new Dimension(750, 120);
	private static final Dimension dim_button = new Dimension(40, 40);
	private static final Dimension dim_icon = new Dimension(32, 32);
	
	
	private JTabbedPane tabs;
	
	private JPanel periode;
	private JPanel fonctions;
	
	
	public TabbedTools() {
		super();
		
		tabs = new JTabbedPane();
		
		initPeriode();
		initFonctions();
		
		add(tabs);
	}
	
	
	private void initPeriode() {
		periode = createPanel();
		
		JPanel fichier = createGroup("Fichier");
		fichier.add(createButton("Nouvelle periode", Control.NOUVEAU, "newFile.png"));
		fichier.add(createButton("Ouvrir une periode", Control.OPEN, "open.png"));
		fichier.add(createButton("Enregistrer", Control.SAVE, "save.png"));
		periode.add(fichier);
		
		JPanel lecture = createGroup("Lecture");
		lecture.add(createButton("Lire", Control.PLAY, "play.png"));
		lecture.add(createButton("Arreter", Control.STOP, "pause.png"));
		periode.add(lecture);
		
		JPanel outils = createGroup("Outils");
		outils.add(createButton("Serpent", Control.SELECT_TOOL_SNAP, "periodSnakeIcon.png"));
		outils.add(createButton("Plume", Control.SELECT_TOOL_WRITE, "plume.png"));
		periode.add(outils);
		
		tabs.addTab("Periode", null, periode, "Parametres de la periode");
	}
	
	private void initFonctions() {
		fonctions = createPanel();
		
		JPanel core_functions = createGroup("Fonctions standard");
		ArrayList<FonctionNamed> fs = Fonctions.getCoreFunctions();
		for (FonctionNamed f : fs) {
			JButton button = createButton(f.name(), -1, null);
			button.addActionListener(new Control(Control.SET_FONCTION, f.fonction()));
			button.setIcon(f.createIcon(dim_icon.width, dim_icon.height));
			core_functions.add(button);
		}
		fonctions.add(core_functions);
		
		JPanel perso_functions = createGroup("Fonctions personnelles");
		fs = Fonctions.getPersonalFunctions();
		for (FonctionNamed f : fs) {
			JButton button = createButton(f.name(), -1, null);
			button.addActionListener(new Control(Control.SET_FONCTION, f.fonction()));
			button.setIcon(f.createIcon(dim_icon.width, dim_icon.height));
			perso_functions.add(button);
		}
		perso_functions.add(createButton("Ajouter une fonction", Control.ADD_FUNCTION, "add.png"));
		fonctions.add(perso_functions);
		
		tabs.addTab("Fonctions", null, fonctions, "Dessiner une fonction");
	}
	
	
	
	
	
	private JPanel createPanel() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(dim_tab);
		
		return panel;
	}
	
	private JPanel createGroup(String title) {
		JPanel panel = new JPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder(title));
		
		return panel;
	}
	
	
	private JButton createButton(String text, int control, String res) {
		JButton b = new JButton();
		
		if(res != null) {
			b.setIcon(Res.getIcon(res, dim_icon));
		}
		
		b.setToolTipText(text);
		b.setPreferredSize(dim_button);
		if(control >= 0) b.addActionListener(new Control(control));
		return b;
	}
	

}
