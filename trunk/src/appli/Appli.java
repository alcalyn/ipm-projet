package appli;

import javax.sound.sampled.LineUnavailableException;

import model.Periode;


public class Appli {

	
	
	public static void main(String[] args) throws LineUnavailableException {
		
		System.out.println("start");
		
		Periode p = new Periode();
		p.duree(1.0/990.0);
		p.setSin();
		p.play(1);
		
		System.out.println("end");
		
	}

}
