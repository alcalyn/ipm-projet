package controllers;

public class Brush {
	
	
	private String [] args;
	
	
	public Brush(String ... args) {
		this.args = args;
	}
	
	
	public int getParametersCount() {
		return args.length;
	}
	
	
	public String get(int n) {
		return args[n];
	}
	
	public void set(int n, String value) {
		args[n] = value;
	}
	
	
	public int getInt(int n) {
		if(get(n).length() == 0) return 0;
		return Integer.parseInt(get(n));
	}
	
	public double getDouble(int n) {
		if(get(n).length() == 0) return 0.0;
		return Double.parseDouble(get(n));
	}
	
	public boolean getBoolean(int n) {
		if(get(n).length() == 0) return false;
		return Boolean.parseBoolean(get(n));
	}
	
	
}
