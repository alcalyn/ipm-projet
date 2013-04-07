package appli;

import controllers.Control;
import controllers.Tool;
import model.Periode;
import model.PeriodeReader;
import views.PeriodeView;


public class Appli {

	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("start");
		
		Periode periode = new Periode();
		
		Tool.setPeriode(periode);
		Control.setPeriode(periode);
		
		PeriodeView view = new PeriodeView();
		
		Control.setPeriodeView(view);
		
		view.observe(periode);
		
		
		
		periode.setNoise();
		periode.duree(1.0 / 300.0);
		view.selectTool(Tool.SNAP);
		PeriodeReader.prepare(periode);
		
		System.out.println("end");
	}

}
