package navalWar;

import java.util.ArrayList;

public class Boat {

	private int l;
	private static int nbObus = 0;
	private ArrayList<Coord> coord = new ArrayList<Coord>();
	
	public Boat(int l) {
		
		this.l = l;
	}

	//Méthodes requises
	public boolean sinkBoat() { //Vérifie si le bateau est coulé
		
		if(nbObus == this.l) //Si le nombre d'obus = à la longueur du bateau, le bat est alors coulé
			return true;
		else
			return false;
	}
	
	//Méthode d'affichage
	@Override
	public String toString()
	{
		if(this.coord.size() != 0)
			return "Bateau de longueur " + this.l + " et de coordonnées [" + this.coord.get(0).toString() + ":" + this.coord.get(this.coord.size() - 1) + "]";
		else
			return "Bateau de longueur " + this.l + " et de coordonnées (" + 0 + "," + 0 + ")";
	}
	
	//GETTERS ET SETTERS
	public int getL() { //Retourne la longueur du bateau
		return this.l;
	}

	public ArrayList<Coord> getCoord() { //Retourne l'ensemble des coordonées du bateau
		return this.coord;
	}

	public void addNbObus() {
		nbObus++;
	}

	public void addCoord(Coord c) { //Ajoute de nouvelles coordonnées à l'ensemble des coordonées du bateau
		this.coord.add(c);
	}
	
}
