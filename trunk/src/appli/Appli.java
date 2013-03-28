package appli;

import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


public class Appli {

	
	
	public static void main(String[] args) throws LineUnavailableException {
		
		final int SAMPLING_RATE = 44100;
		final int SAMPLE_SIZE = 1;
		
		SourceDataLine line;
		
		AudioFormat format = new AudioFormat(SAMPLING_RATE, SAMPLE_SIZE * 8, 1, true, true);
		
		line = (SourceDataLine) AudioSystem.getLine(new Info(SourceDataLine.class, format));
		
		line.open(format);  
		line.start();
		
		ByteBuffer cBuf = ByteBuffer.allocate(line.getBufferSize());
		
		
		line.write(cBuf.array(), 0, cBuf.position());
		
		System.out.println("start");
		while(true) {
			cBuf.clear();
			
			for(int i=0;i<line.getBufferSize() / 2;i++) {
				byte sample = (byte) (Math.random() * 1000000);
				cBuf.putShort(sample);
			}
			
		}
		
		/*
		line.drain();
		line.close();
		
		System.out.println("end");
		*/
	}

}
