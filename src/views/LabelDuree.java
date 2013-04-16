package views;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.ModelUpdate;
import controllers.Control;

public class LabelDuree extends JLabel implements Observer {
	
	private static final long serialVersionUID = 8583240637603815263L;
	

	public LabelDuree() {
		setPreferredSize(new Dimension(90, 20));
		setHorizontalAlignment(SwingConstants.RIGHT);
		Control.getPeriode().addObserver(this);
	}
	
	
	private void updateDuree(double d) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		if(d > 1) {
			setText(df.format(d)+" sec");
		} else {
			setText(df.format(1.0 / d)+" Hz");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		ModelUpdate up = (ModelUpdate) arg;
		
		switch(up.type) {
			case ModelUpdate.DUREE:
				updateDuree((Double) up.arg(0));
				break;
		}
	}

}
