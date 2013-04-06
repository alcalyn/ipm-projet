package model;

import java.util.Observable;

public class ObservablePeriode extends Observable {
	
	public void notifyObservers(int type, Object args) {
		super.notifyObservers(new ModelUpdate(type, args));
	}

}
