package navalWar;

public class BattlefieldView implements ModelListener{
	
	Battlefield model;
	String name;
	
	public BattlefieldView(String name, Battlefield chB) {
		
		this.name = name;
		this.model = chB;
		this.model.setBoard(chB.getBoard());
		
		this.model.addModelListener(this);
		
	}

	@Override
	public void modelUpdated(Object source) {

		System.out.println(this.name + " : \n" + this.model.toString());
	}

}
