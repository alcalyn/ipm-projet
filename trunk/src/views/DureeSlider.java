package views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.ModelUpdate;
import controllers.Control;

public class DureeSlider extends JSlider implements Observer {
	
	private static final long serialVersionUID = 3277685989835400558L;
	
	
	private static final int precision = 1000;
	private static final double range = 9;
	
	private boolean sendToModel = true;
	
	
	public DureeSlider() {
		Control.getPeriode().addObserver(this);
		
		setMinimum(- 1100);
		setMaximum(300);
		
		addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				if(sendToModel) {
					updateDuree((double) getValue() / (double) precision);
				}
				
				sendToModel = true;
			}
		});
	}
	
	
	private void updateDuree(double d) {
		double duree = Math.exp(d * range);
		Control.getPeriode().duree(duree);
	}
	
	private int dureeToInt(double duree) {
		double position = (Math.log(duree) / Math.log(Math.E)) / range;
		return (int) (position * precision);
	}
	
	

	public void update(Observable o, Object arg) {
		ModelUpdate up = (ModelUpdate) arg;
		
		switch(up.type) {
			case ModelUpdate.DUREE:
				sendToModel = false;
				setValue(dureeToInt((Double) up.arg(0)));
				break;
		}
	}

}
