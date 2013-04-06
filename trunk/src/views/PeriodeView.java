package views;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.ModelUpdate;
import model.Periode;

public class PeriodeView extends JFrame implements Observer {
	
	private static final long serialVersionUID = -1146139996955796557L;
	
	
	
	public PeriodeView() {
		super("PeriodeView");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(new BorderLayout());
		setVisible(true);
	}
	
	



	public void update(Observable o, Object arg) {
		Periode periode = (Periode) o;
		ModelUpdate up = (ModelUpdate) arg;
		
		switch(up.type) {
			case ModelUpdate.COURBE:
				
			default:
				System.out.println("erreur update, type #"+up.type+" inconnu");
		}
	}
	

}
