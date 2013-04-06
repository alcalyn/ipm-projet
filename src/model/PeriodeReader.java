package model;

import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class PeriodeReader {
	
	public static final int SAMPLES_RATE = 44100;
	
	
	private static SourceDataLine createLine() throws LineUnavailableException {
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
	
	
	
	public static void play(double seconde, Periode periode) {
		try {
			SourceDataLine line = createLine();
			
			ByteBuffer buf = ByteBuffer.allocate((int) (seconde * (SAMPLES_RATE * 2)));
			
			for(int i = 0; i < (int) (seconde * SAMPLES_RATE); i++) {
				buf.putShort(periode.getAtSecond((double) i / (double) SAMPLES_RATE));
			}
			
			line.write(buf.array(), 0, buf.position());
	    	
	    	line.drain();
	    	line.close();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void playRealTime(Periode periode) {
		// TODO
	}
	
	
	
}
