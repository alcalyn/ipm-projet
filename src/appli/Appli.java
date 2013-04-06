package appli;

import model.Periode;
import model.PeriodeReader;
import model.RealTimeReader;


public class Appli {

	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("start");
		
		Periode p = new Periode();
		p.duree(1.0/800.0);
		p.setNoise();
		RealTimeReader r = PeriodeReader.playRealTime(p);
		System.out.println("play");
		r.play();
		
		Thread.sleep(500);
		
		p.setSin();
		
		Thread.sleep(5000);
		r.close();
		
		System.out.println("end");
	}

}
