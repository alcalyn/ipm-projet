package model;

import java.io.Serializable;

public class Periode extends ObservablePeriode implements Serializable {
	
	private static final long serialVersionUID = 8648757381097933473L;
	
	
	
	private int sampling = 4096;
	private double duree;
	private short [] courbe;
	
	
	public Periode() {
		courbe = new short[sampling];
		reinit();
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
		double continu = (double) ((double) at % 1.0) * (double) sampling;
		short sample;
		
		int discret0 = (int) Math.floor(continu);
		int discret1 = (int) Math.ceil(continu);
		
		if(discret0 == discret1) discret1++;
		
		double coef0 = discret1 - continu;
		double coef1 = continu - discret0;
		double p0 = courbe[discret0] * coef0;
		double p1 = courbe[discret1 % sampling] * coef1;
		sample = (short) ((p0 + p1) / 2);
		
		return sample;
	}
	
	
	
	/**
	 * 
	 * @param valeur dans [0;1[
	 * @return le valeur de la courbe a ce point entre [-1;1[
	 */
	public double getDouble(double at) {
		double continu = (double) ((double) at % 1.0) * (double) sampling;
		short sample;
		
		int discret0 = (int) Math.floor(continu);
		int discret1 = (int) Math.ceil(continu);
		
		if(discret0 == discret1) discret1++;
		
		double coef0 = discret1 - continu;
		double coef1 = continu - discret0;
		double p0 = courbe[discret0] * coef0;
		double p1 = courbe[discret1 % sampling] * coef1;
		sample = (short) ((p0 + p1) / 2);
		
		return sample;
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
		
		short sample = (short) (_value * Short.MAX_VALUE);
		
		courbe[index] = sample;
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
	
	
	public void dessiner(Fonction fonction) {
		for(int i=0;i<sampling;i++) {
			double at = (double) i / sampling;
			set(at, fonction.f(at));
		}
		
		flushCourbe();
	}
	
	
	public void setSilenceMiddle() {
		dessiner(new Fonction() {
			
			public double f(double x) {
				return 0;
			}
		});
	}
	
	public void setNoise() {
		double l1 = 0.003;
		double l2 = 0.03;
		double b = 0;
		
		for(int i=0;i<sampling;i++) {
			b += (Math.random() - 0.5) * l1 + (Math.random() - 0.5) * l2;
			set(i, b);
		}
		
		flushCourbe();
	}
	
	public void setSin() {
		dessiner(new Fonction() {
			
			public double f(double x) {
				return Math.sin(x * (Math.PI * 2));
			}
		});
	}
	
	
	public void reinit() {
		setSilenceMiddle();
		duree = 1.0 / 440.0;
		flushCourbe();
	}
	
	public void load(Periode p) {
		this.sampling = p.sampling;
		this.duree = p.duree;
		this.courbe = p.courbe;
	}
	
	
	
}
