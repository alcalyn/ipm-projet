package model;

public class ModelUpdate {
	
	
	public static final int
		DUREE = 5,
		SAMPLING = 6,
		COURBE = 7;
	
	
	public int type;
	public Object args;
	
	
	public ModelUpdate(int type, Object args) {
		this.type = type;
		this.args = args;
	}
	
	
	
	
	
}
