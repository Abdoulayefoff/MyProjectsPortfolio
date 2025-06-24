package navalWar;

public class Area {

	private String s = " ";
	private Battlefield chB;
	private Coord coord;
	private Obus o;
	private Boat b;
	
	public Area(Battlefield chB, int x, int y) {
		
		this.chB = chB;
		this.coord = new Coord(x,y);
		this.o = null;
		this.b = null;
	}
	
	public void put(Boat b) {
		
		this.b = b;
	}
	
	public void assault(Obus o) {
		
		this.o = o;
		if(this.b != null)
		{
			this.b.addNbObus();
			this.s = "X";
		}
		else
		{
			this.s = "!";
		}
	}
	
	//Méthode d'affichage
	@Override
	public String toString() {
		
		String str = " " + this.s + " |";
		
		return str;
	}
	
	//GETTERS ET SETTERS
	public String getS() {
		return this.s;
	}
	
	public Battlefield getChB() {
		return this.chB;
	}

	public Coord getCoord() {
		return this.coord;
	}
	public Obus getO() {
		return this.o;
	}

	public Boat getB() {
		return this.b;
	}
	
	public void setS(String s) {
		
		this.s = s;
	}
	
}
