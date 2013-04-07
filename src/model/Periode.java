package model;

import java.nio.ByteBuffer;

public class Periode extends ObservablePeriode {
	
	
	private int sampling = 6000;
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
		notifyObservers(ModelUpdate.DUREE, duree);
	}
	
	public int sampling() {
		return sampling;
	}

	public void sampling(int sampling) {
		this.sampling = sampling;
		notifyObservers(ModelUpdate.SAMPLING, sampling);
	}
	
	public short [] getCourbe() {
		return courbe;
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
		int index = (int) ((at % 1) * sampling);
		return courbe[index];
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
	 * @param value valeur dans [-1;1[ avec -1 amplitude min, et 1 amplitude max
	 */
	public void set(int index, double value) {
		double _value = value;
		if(_value < -1) _value = -1;
		if(_value > 1) _value = 1;
		courbe[index] = (short) (_value * Short.MAX_VALUE);
	}
	
	
	/**
	 * 
	 * @param position dand la courbe dans [0;1[
	 * @param value valeur dans [-1;1[ avec -1 amplitude min, et 1 amplitude max
	 */
	public void set(double at, double value) {
		double _value = value;
		if(_value < -1) _value = -1;
		if(_value > 1) _value = 1;
		
		int index = (int) ((double) sampling * at);
		
		courbe[index] = (short) (_value * Short.MAX_VALUE);
	}
	
	
	/**
	 * Notify observers from edit
	 */
	public void flushCourbe() {
		notifyObservers(ModelUpdate.COURBE);
	}
	
	
	public ByteBuffer toByteBuffer() {
		ByteBuffer buf = ByteBuffer.allocate(sampling * 2);
    	
    	for(int i=0;i<sampling;i++) {
    		short s = get(i);
    		buf.putShort(s);
    	}
    	
    	return buf;
	}
	
	
	public void setSilenceMiddle() {
		for(int i=0;i<sampling;i++) {
			set(i, 0);
		}
		
		flushCourbe();
	}
	
	public void setNoise() {
		double l1 = 0.001;
		double l2 = 0.01;
		double b = 0;
		
		for(int i=0;i<sampling;i++) {
			b += (Math.random() - 0.5) * l1 + (Math.random() - 0.5) * l2;
			set(i, b);
		}
		
		flushCourbe();
	}
	
	public void setSin() {
		for(int i=0;i<sampling;i++) {
			double v = Math.sin(((double) i / (double) sampling) * (Math.PI * 2));
			set(i, v);
		}
		
		flushCourbe();
	}
	
	
	
}
