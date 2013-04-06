package model;

import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Periode {
	
	
	private static final int SAMPLES = 44100;
	
	private double duree; // en seconde
	private short [] courbe;
	
	
	public Periode() {
		duree = (double) 1 / (double) 440;
		courbe = new short[SAMPLES];
	}
	
	public double duree() {
		return duree;
	}

	public void duree(double duree) {
		this.duree = duree;
	}
	

	/**
	 * 
	 * @param valeur dans [0;1[
	 * @return le valeur de la courbe a ce point
	 */
	public short get(double at) {
		return courbe[(int) ((at % 1) * SAMPLES)];
	}
	
	
	public short getAtSecond(double s) {
		return get(s / duree);
	}
	
	/**
	 * 
	 * @param index de la courbe
	 * @param value valeur dans [0;1[ avec 0 amplitude min, et 1 amplitude max
	 */
	public void set(int index, double value) {
		courbe[index] = (short) (value * Short.MAX_VALUE);
	}
	
	
	public void setNoise() {
		for(int i=0;i<SAMPLES;i++) {
			set(i, Math.random());
		}
	}
	
	public void setSin() {
		for(int i=0;i<SAMPLES;i++) {
			double v = Math.sin(((double) i / (double) SAMPLES) * (Math.PI * 2));
			set(i, v);
		}
	}
	
	
	public void play(int sec) throws LineUnavailableException {
		final int SAMPLES_RATE = 44100;
		
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
		
	    ByteBuffer cBuf = ByteBuffer.allocate(SAMPLES * 2);
    	
    	for(int i=0;i<44100;i++) {
    		short s = getAtSecond((double) i / (double) 44100);
    		cBuf.putShort(s);
    	}
    	
    	for(int i=0;i<sec;i++) line.write(cBuf.array(), 0, SAMPLES * 2);
    	
    	line.drain();
    	line.close();
	}
	
	
}
