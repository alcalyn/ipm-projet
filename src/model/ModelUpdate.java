package model;


public class ModelUpdate {
	
	
	public static final int
		DUREE = 5,
		SAMPLING = 6,
		COURBE = 7;
	
	
	public int type;
	public Object [] args;
	
	public ModelUpdate(int type, Object [] args) {
		init(type, args);
	}
	
	public ModelUpdate(int type, Object arg) {
		init(type, new Object [] {arg});
	}
	
	public ModelUpdate(int type) {
		init(type, new Object [] {});
	}
	
	private void init(int type, Object [] args) {
		this.type = type;
		this.args = args;
	}
	
	public Object arg(int index) {
		return args[index];
	}
	
	
	
	
}
