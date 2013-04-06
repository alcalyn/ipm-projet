package appli;

import model.Periode;
import model.PeriodeReader;
import views.PeriodeView;


public class Appli {

	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("start");
		
		Periode periode = new Periode();
		
		PeriodeView view = new PeriodeView();
		
		view.observe(periode);
		
		periode.setSilenceMiddle();
		periode.duree(1.0 / 500.0);
		PeriodeReader.playRealTime(periode);
		
		System.out.println("end");
	}

}
