package navalWar;

import java.util.ArrayList;

public class Battlefield extends AbstractListenableModel{

	private int haut, larg;
	private Area board[][];
	private Player player;
	private ArrayList<Boat> boatList = new ArrayList<Boat>();
	
	public Battlefield(int l, int col) { //On initialise le champ de bataille
		
		this.haut = l;
		this.larg = col;
		this.board = new Area[this.haut][this.larg];
		for(int i = 0 ; i < this.haut ; i++) 
		{
			for(int j = 0 ; j < this.larg ; j++) 
			{
				this.board[i][j] = new Area(this, i,j);
			}
		}
		
	}
	
	//Méthodes requises
	public void put(Boat b, int x, int y, boolean dir) { //La méthode qui illustre l'action de mettre un bateau dans le champ de bataille
		
		if( this.board[x][y].getB() == null ) {//Si la zone ne contient pas encore de bateau, on exécute les instructions ci-dessous ↓↓↓
			
			if( isValid(b, x, y, dir) )  { //Si la longueur du bateau entre dans les proportions du champ de bataille, on exécute les instructions ci-dessous ↓↓↓
				
				this.boatList.add(b); //On ajoute le bateau à la liste de bateaux du champ de bataille
					
				if(dir) //Si le bateau est à la verticale, on exécute les instructions ci-dessous ↓↓↓
				{
					for(int i = 0 ; i < b.getL() ; i++) {
						this.board[x+i][y].put(b); //On ajoute une référence au bateau sur la plage de zone sur laquelle s'étend le bateau en itérant sur les lignes
							
						b.addCoord(new Coord(x+i, y)); //On ajoute la plage de coordonées sur laquelle s'étend le bateau en itérant sur les lignes
					}
				}
				
				else //S'il est à l'horizontal, on exécute les instructions ci-dessous ↓↓↓
				{
					for(int i = 0 ; i < b.getL() ; i++) {
						this.board[x][y+i].put(b); //On ajoute une référence au bateau sur la plage de zone sur laquelle s'étend le bateau en itérant sur les colonnes
							
						b.addCoord(new Coord(x, y+i)); //On ajoute la plage de coordonées sur laquelle s'étend le bateau en itérant sur les colonnes
					}
				}
					
					//this.fireChange();
			}	
			
			else
			{
				if(dir)
					System.out.println("Le bateau ne peut être placé car la longueur du bateau dépasse la hauteur du champ de bataille. \nVeuillez réessayer");
				else
					System.out.println("Le bateau ne peut être placé car la longueur du bateau dépasse la largeur du champ de bataille. \nVeuillez réessayer");
			}
		}
		
		else
			System.out.println("Le bateau ne peut être placé car il y a déjà un bateau dans cette zone. \nVeuillez réessayer");
	}
	
	public void assault(int x, int y, Obus o) { //La méthode qui illustre l'action d'attaquer le champ de bataille
		
		if( this.board[x][y].getO() == null ) //Si la zone n'a pas encore été touchée, on l'attaque
		{	
			this.board[x][y].assault(o);
			this.fireChange();
		}
	}
	
	//Méthodes ajoutées
	public boolean isOver() { //Vérifie si tous les bateaux du champ de bataille sont coulés
		
		boolean over = true;
		
		for(int i = 0 ; i < this.boatList.size() ; i++) {
			over = over && this.boatList.get(i).sinkBoat(); //Vérifie l'état de chaque bateau du champ de bataille (s'il est coulé ou pas)
			
			if(over == false) break; //Dès qu'un bateau n'est pas coulé, on sort de la boucle
		}
		
		return over;
	}
	
	public boolean isValid(Boat b, int x, int y, boolean dir) { //Vérifie si la position de 
		
		if(dir)
		{
			if( (0 < b.getL() && b.getL() <= this.haut) && (x+b.getL() <= this.haut) )
				return true;
			else
				return false;
		}
		else
		{
			if( (0 < b.getL() && b.getL() <= this.larg) && (y+b.getL() <= this.larg) )
				return true;
			else
				return false;
		}
	}
	
	
	//Méthode d'affichage
	@Override
	public String toString() {
		
		String str = " Champ de bataille du joueur " + this.player.toString() + "\n\n";
			
		for(int i = 0 ; i < this.haut ; i++)
		{
			for(int j = 0 ; j < this.larg ; j++)
			{
				str += this.board[i][j].toString();
			}
			
			str += "\n";
		}
			
			
		return str;
	}
	
	//GETTERS ET SETTERS
	public int getHaut() {
		return this.haut;
	}
	
	public int getLarg() {
		return this.larg;
	}
	
	public Area[][] getBoard(){
		return this.board;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setHaut(int haut) {
		this.haut = haut;
	}

	public void setLarg(int larg) {
		this.larg = larg;
	}
	
	public void setBoard(Area[][] aBoard) {
		for(int i = 0 ; i < this.haut ; i++)
		{
			for(int j = 0 ; j < this.larg ; j++)
			{
				this.board[i][j] = aBoard[i][j];
			}
			
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
