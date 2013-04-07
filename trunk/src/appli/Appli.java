package appli;

import controllers.Tool;
import model.Periode;
import model.PeriodeReader;
import views.PeriodeView;


public class Appli {

	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("start");
		
		Periode periode = new Periode();
		
		Tool.setPeriode(periode);
		
		PeriodeView view = new PeriodeView();
		
		view.observe(periode);
		
		
		
		periode.setNoise();
		periode.duree(1.0);
		view.selectTool(Tool.SNAP);
		PeriodeReader.playRealTime(periode);
		
		System.out.println("end");
	}

}
