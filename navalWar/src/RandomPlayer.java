package navalWar;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements Player{

	private Battlefield chB;
	private Random rand = new Random();
	private ArrayList<Coord> oldCoordPut = new ArrayList<Coord>();
	private ArrayList<Coord> oldCoordAssault = new ArrayList<Coord>();
	
	public RandomPlayer(Battlefield chB) {
		
		this.chB = chB;
		for(int i = 0 ; i < this.chB.getHaut() ; i++) 
		{
			for(int j = 0 ; j < this.chB.getLarg() ; j++) 
			{
				this.oldCoordPut.add(new Coord(i,j));
				this.oldCoordAssault.add(new Coord(i,j));
			}
		}
	}
	
	//Méthodes requises
	@Override
	public boolean chooseDir() {
		
		return rand.nextBoolean() ;
	}
	
	@Override
	public void put(Boat b) { 
		
		//Il choisi au hasard l'indice d'une coordonnée pas encore tirée
		int x = rand.nextInt(this.oldCoordPut.size());
		
		Coord c = this.oldCoordPut.get(x);
		
		this.oldCoordPut.remove(c); //On retire c de l'ensemble des coordonées pas encore tirées
		
		//dir = true lorsque le bateau est à la verticale 
		//dir = false lorsque le bateau est à l'horizontal
		this.chB.put(b, c.getX(), c.getY(), this.chooseDir());

	}
	
	@Override
	public void aim(Battlefield chB, Obus o) { //Vise la zone de coordonées x,y du champ de bataille de l'adversaire
		
		do //Tant que le joueur attaque son propre camp, on lui demande de réessayer
		{
			//Il choisi au hasard l'indice d'une coordonnée pas encore visée
			int x = rand.nextInt(this.oldCoordAssault.size());
					
			Coord c = this.oldCoordAssault.get(x);
					
			this.oldCoordAssault.remove(c); //On retire c de l'ensemble des coordonées pas encore visées
					
			chB.assault(c.getX(), c.getY(), o); //La méthode attaque du champ de bataille gère l'attaque
			
			if(chB == this.chB)
				System.out.println(" Vous ne pouvez pas vous auto-attaquer! Attaquez le champ adverse!");
		
		} while (chB == this.chB) ;
		
	}
	
	//Méthode d'affichage
	@Override
	public String toString() { //Retourne le nom du joueur
		return "Random " + this.hashCode() ;
	}
	
}
