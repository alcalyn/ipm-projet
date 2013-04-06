package model;

import java.nio.ByteBuffer;

public class Periode {
	
	
	private int sampling = 100000;
	private double duree;
	private short [] courbe;
	
	
	public Periode() {
		duree = (double) 1 / (double) 440;
		courbe = new short[sampling];
	}
	
	public double duree() {
		return duree;
	}

	public void duree(double duree) {
		this.duree = duree;
	}
	
	public int sampling() {
		return sampling;
	}

	public void sampling(int sampling) {
		this.sampling = sampling;
	}

	/**
	 * 
	 * @param index de l'echantillon
	 * @return
	 */
	public short get(int index) {
		return courbe[index];
	}
	
	
	/**
	 * 
	 * @param valeur dans [0;1[
	 * @return le valeur de la courbe a ce point
	 */
	public short get(double at) {
		return courbe[(int) ((at % 1) * sampling)];
	}
	
	/**
	 * 
	 * @param s temps en seconde pour lequel retourner l'echantillon
	 * @return
	 */
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
	
	
	public ByteBuffer toByteBuffer() {
		ByteBuffer buf = ByteBuffer.allocate(sampling * 2);
    	
    	for(int i=0;i<sampling;i++) {
    		short s = get(i);
    		buf.putShort(s);
    	}
    	
    	return buf;
	}
	
	public void setNoise() {
		for(int i=0;i<sampling;i++) {
			set(i, Math.random() * 2 - 1);
		}
	}
	
	public void setSin() {
		for(int i=0;i<sampling;i++) {
			double v = Math.sin(((double) i / (double) sampling) * (Math.PI * 2));
			set(i, v);
		}
	}
	
	
	
}
