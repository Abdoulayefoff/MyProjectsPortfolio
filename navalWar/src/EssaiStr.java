package navalWar;

import java.util.Scanner;

public class EssaiStr {

	public static void main(String[] args) {
		
		//String name;
		Scanner sc = new Scanner(System.in);
		
		Battlefield chB = new Battlefield(10,10);
		Battlefield chB2 = new Battlefield(10,10);
		
	
		
		Player p = new RandomPlayer(chB); //new HumanPlayer(name, chB);
		Player p2 = new RandomPlayer(chB2);
		
		chB.setPlayer(p);
		chB2.setPlayer(p2);
		
		Boat b1p1 = new Boat(3);
		Boat b2p1 = new Boat(7);
		Boat b3p1 = new Boat(5);
		
		Boat b1p2 = new Boat(3);
		Boat b2p2 = new Boat(7);
		Boat b3p2 = new Boat(5);
		
		System.out.println(chB.toString());
		System.out.println(chB2.toString());
		
		p.put(b1p1);
		p.put(b2p1);
		p.put(b3p1);
		
		p2.put(b1p2);
		p2.put(b2p2);
		p2.put(b3p2);
		
		
		p.aim(chB2, new Obus());
		
		System.out.println(chB2.toString());
		
		p2.aim(chB, new Obus());
		
		System.out.println(chB.toString());
		
		p.aim(chB2, new Obus());
		
		System.out.println(chB2.toString());

		p2.aim(chB, new Obus());
		
		System.out.println(chB.toString());
		
		p.aim(chB2, new Obus());
		
		System.out.println(chB2.toString());
		
		p2.aim(chB, new Obus());
		
		System.out.println(chB.toString());
		
		
		if(chB.isOver() && !chB2.isOver()) 
		{
			System.out.println("Le vainqueur est le joueur " + chB2.getPlayer().toString());		
		}
		
		else if(!chB.isOver() && chB2.isOver()) 
		{
			System.out.println("Le vainqueur est le joueur " + chB.getPlayer().toString());
		}
		
		else 
		{
			System.out.println("Match nul! Il n'y a pas de vaiqueur.");
		}
		
		System.out.println(chB.toString());
		System.out.println(chB2.toString());
		
		sc.close();
	}

}
