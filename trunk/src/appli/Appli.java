package appli;

import javax.sound.sampled.LineUnavailableException;

import model.Periode;
import model.PeriodeReader;


public class Appli {

	
	
	public static void main(String[] args) throws LineUnavailableException {
		
		System.out.println("start");
		
		Periode p = new Periode();
		p.duree(1.0/500.0);
		p.setSin();
		PeriodeReader.play(360.0, p);
		
		System.out.println("end");
		
	}

}
