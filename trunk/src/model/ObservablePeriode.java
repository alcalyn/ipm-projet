package model;

import java.util.Observable;

public class ObservablePeriode extends Observable {
	
	public void notifyObservers(int type, Object [] args) {
		super.setChanged();
		super.notifyObservers(new ModelUpdate(type, args));
	}
	
	public void notifyObservers(int type, Object arg) {
		super.setChanged();
		super.notifyObservers(new ModelUpdate(type, arg));
	}
	
	public void notifyObservers(int type) {
		super.setChanged();
		super.notifyObservers(new ModelUpdate(type));
	}

}
