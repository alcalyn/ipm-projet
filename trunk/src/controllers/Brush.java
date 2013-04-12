package controllers;

public class Brush {
	
	
	private Object [] args;
	
	
	public Brush(Object ... args) {
		this.args = args;
	}
	
	
	public int getParametersCount() {
		return args.length;
	}
	
	
	public Object get(int n) {
		return args[n];
	}
	
	public void set(int n, Object value) {
		args[n] = value;
	}
	
	
	public int getInt(int n) {
		return (Integer) get(n);
	}
	
	public double getDouble(int n) {
		return (Double) get(n);
	}
	
	public boolean getBoolean(int n) {
		return (Boolean) get(n);
	}
	
	
}
