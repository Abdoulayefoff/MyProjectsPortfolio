package navalWar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BattlefieldPanel extends JPanel implements ModelListener{ 
	
	Battlefield chBmodel;
	
	public BattlefieldPanel(Battlefield chB) {
		
		super();
		this.chBmodel = chB;
		this.chBmodel.addModelListener(this);
		
		
		
		JPanel base = new JPanel();
		base.setLayout(new BorderLayout());
		
		JPanel middle = new JPanel();
		middle.setPreferredSize( new Dimension((50*this.chBmodel.getLarg()) , (50*this.chBmodel.getHaut())) );
		middle.setLayout(new GridLayout(this.chBmodel.getHaut(), this.chBmodel.getLarg()));
		
		for(int i = 0 ; i < this.chBmodel.getHaut() ; i++)
		{
			for(int j = 0 ; j < this.chBmodel.getLarg() ; j++) 
			{
				middle.add(new AreaPanel(this.chBmodel, this.chBmodel.getBoard()[i][j], this.chBmodel.getPlayer()));
			}
		}
		
		//middle.repaint();
		middle.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
		
		this.add(middle);
		
		//this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		
		this.setBackground(Color.WHITE);
		

	}
	
	@Override
	public void modelUpdated(Object source) {
		this.repaint();
	}

}
