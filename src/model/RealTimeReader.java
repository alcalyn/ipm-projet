package model;

import java.nio.ByteBuffer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class RealTimeReader extends Thread {
	
	/**
	 * double LATENCE doit etre compris dans [0;1[
	 */
	private static final double LATENCE = 0.040;
	
	
	private Periode periode;
	private SourceDataLine line = null;
	private ByteBuffer buf = null;
	private boolean reading = false;
	

	public RealTimeReader(Periode periode) {
		super();
		this.periode = periode;
		init();
	}
	
	private void init() {
		try {
			line = PeriodeReader.createLine();
			buf = ByteBuffer.allocate((int) (PeriodeReader.SAMPLES_RATE * 2 * LATENCE));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		double index = 0;
		int available;
		int seuil;
		
		while(reading) {
			buf.clear();
			
			for(int i = 0; i < (int) (PeriodeReader.SAMPLES_RATE * LATENCE); i++) {
				short s = periode.getAtSecond((double) (index + i) / (double) PeriodeReader.SAMPLES_RATE);
				buf.putShort(s);
			}
			
			index += PeriodeReader.SAMPLES_RATE * LATENCE;
			
			line.write(buf.array(), 0, buf.position());
			
			do {
				available = line.available();
				seuil = (int) (PeriodeReader.SAMPLES_RATE * (1 - LATENCE));
				
				try {
					Thread.sleep((long) (LATENCE * 100));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while(available < seuil);
		}
		
		line.close();
	}
	
	
	public void play() {
		if(!reading) {
			reading = true;
			start();
		}
	}
	
	public void close() {
		if(reading) {
			reading = false;
		}
	}
	
	
	
	
}
