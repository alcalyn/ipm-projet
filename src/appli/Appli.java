package appli;

import model.Periode;
import views.PeriodeView;


public class Appli {

	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("start");
		
		Periode periode = new Periode();
		
		PeriodeView view = new PeriodeView();
		
		periode.addObserver(view);
		
		System.out.println("end");
	}

}
