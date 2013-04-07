package model;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class PeriodeReader {
	
	public static final int SAMPLES_RATE = 44100;
	
	private static RealTimeReader piste = null;
	
	
	public static SourceDataLine createLine() throws LineUnavailableException {
		SourceDataLine line;
		
		AudioFormat format = new AudioFormat(SAMPLES_RATE, 16, 1, true, true);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Line matching " + info + " is not supported.");
			throw new LineUnavailableException();
		}
		
		line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(format);
		line.start();
		
		return line;
	}
	
	
	
	public static RealTimeReader playRealTime(Periode periode, boolean play) {
		RealTimeReader rtr = new RealTimeReader(periode);
		if(play) rtr.play();
		return rtr;
	}
	
	
	public static void prepare(Periode periode) {
		stop();
		piste = playRealTime(periode, false);
	}
	
	public static void play(Periode periode) {
		if(piste == null) {
			prepare(periode);
		}
		
		piste.play();
	}
	
	public static void stop() {
		if(piste != null) {
			piste.close();
			piste = null;
		}
	}
	
	
}
