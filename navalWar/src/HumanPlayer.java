package navalWar;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player{

	private String name;
	private Battlefield chB;
	private ArrayList<Coord> oldCoordPut = new ArrayList<Coord>();
	private ArrayList<Coord> oldCoordAssault = new ArrayList<Coord>();
	private Scanner sc = new Scanner(System.in);
	
	public HumanPlayer(String n, Battlefield chB) {
		
		this.name = n;
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
	public void put(Boat b) { 
		
		int x, y;
		Coord c;
		
		do
		{	
			System.out.println("Veuillez entrer les coordonnées de la zone de départ du bateau: ");
			System.out.println("Le x: ");
			x = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Le y: ");
			y = sc.nextInt();
			
			c = new Coord(x,y);
			
			if( !this.contain(this.oldCoordPut, c) )
				System.out.println(" Vous avez déjà posé un bateau dans la zone " + c.toString() + " ! Essayez-en une autre!");
		
		} while( !this.contain(this.oldCoordPut, c) );
		
		this.oldCoordPut.remove(c);
		
		//dir = true lorsque le bateau est à la verticale 
		//dir = false lorsque le bateau est à l'horizontal
		this.chB.put(b, x, y, this.chooseDir());
		
	}
	
	@Override
	public void aim(Battlefield chB, Obus o) { //Vise la zone de coordonées x,y du champ de bataille de l'adversaire
		
		do //Tant que le joueur attaque son propre camp, on lui demande de réessayer
		{	
			int x, y;
			Coord c;
			
			do
			{	
				System.out.println("Veuillez entrer les coordonnées de la zone à viser: ");
				System.out.println("Le x: ");
				x = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Le y: ");
				y = sc.nextInt();
				
				c = new Coord(x,y);
				
				if( !this.contain(this.oldCoordAssault, c) )
					System.out.println(" Vous avez déjà visé la zone " + c.toString() + " ! Essayez-en une autre!");
			
			} while( !this.contain(this.oldCoordAssault, c) );
			
			this.oldCoordAssault.remove(c);
			
			chB.assault(x, y, o); //La méthode attaque du champ de bataille gère l'attaque
			
			if(chB == this.chB)
				System.out.println(" Vous ne pouvez pas vous auto-attaquer! Attaquez le champ adverse!");
		
		} while (chB == this.chB) ;
	}
	
	//@Override
	public void putPanel(Boat b, int x, int y, boolean dir) { 
		
		//x et y représente la position dans le tableau
		
		//dir = true lorsque le bateau est à la verticale 
		//dir = false lorsque le bateau est à l'horizontal
		this.chB.put(b, x, y, dir);
		
	}
	
	//@Override
	public void aimPanel(Battlefield chB, int x, int y, Obus o) { //Vise la zone de coordonées x,y du champ de bataille de l'adversaire
		
		chB.assault(x, y, o); //La méthode attaque du champ de bataille gère l'attaque
	}
	
	//Méthode ajoutée
	public boolean contain(ArrayList<Coord> listC, Coord c) {
		
		boolean isIn = false;
		
		for(int i = 0 ; i < listC.size() ; i++)
		{
			if( (c.getX() == listC.get(i).getX()) && (c.getY() == listC.get(i).getY()) )
			{
				isIn = true;
				break;
			}
		}
		
		return isIn;
	}
	
	@Override
	public boolean chooseDir() {
		
		int x;
		do
		{	
			System.out.println("Veuillez choisir la direction du bateau: ");
			System.out.println("Entrez 0 pour l'horizontal et 1 pour la verticale!");
			x = sc.nextInt();
			sc.nextLine();
			
			if( x!=0 && x!=1  )
				System.out.println(" Vous avez saisit une mauvaise information! Veuillez saisir 0 ou 1!");
		
		} while( x!=0 && x!=1 );
		
		if( x==0 )
			return false; //Si l'utilisateur saisit 0, il place le bateau à l'horizontal
		else
			return true; //Si l'utilisateur saisit 1, il place le bateau à la verticale
	}
	
	//Méthode d'affichage
	@Override
	public String toString() { //Retourne le nom du joueur
		
		if(this.name == null)
			return "Humain Inconnu";
		else
			return "Humain " + this.name.substring(0, 1).toUpperCase() + this.name.substring(1) ;
	}
	
	//GETTERS ET SETTERS
	public String getName() {
		return this.name;
	}
	
}
