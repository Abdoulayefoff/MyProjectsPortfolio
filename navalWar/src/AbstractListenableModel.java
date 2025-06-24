package navalWar;

import java.util.ArrayList;

public class AbstractListenableModel {
	
	ArrayList<ModelListener> followers;
	
	public AbstractListenableModel() {
		
		this.followers = new ArrayList<>();
		
	}
	
	public void addModelListener(ModelListener listener) {
		this.followers.add(listener);
	}
	
	public void removeModelLidtener(ModelListener listener) {
		this.followers.remove(listener);
	}
	
	public void fireChange() {
		
		for(ModelListener a : this.followers) {
			a.modelUpdated(this);
		}
		
		
	}

}
