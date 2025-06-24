package navalWar;

public class Coord {

	private int x, y;
	
	public Coord(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	//Méthode d'affichage
	@Override
	public String toString() {
		
		return "(" + this.x + "," + this.y + ")";
	}
	
	//GETTERS ET SETTERS
	public int getX() {
		
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
}
