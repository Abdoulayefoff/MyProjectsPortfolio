package navalWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class AreaPanel extends JPanel implements MouseListener{
	
	Battlefield chB;
	Area a;
	Player p;
	
	public AreaPanel(Battlefield chB, Area a, Player p) {
		
		super();
		this.chB = chB;
		this.a = a;
		this.p = p;
		
		this.setSize(30, 30);
		
		this.setBorder( BorderFactory.createLineBorder(Color.RED) );
		
		this.addMouseListener(this);
		
	}

	
	//Méthodes appelées lors du clic de souris	
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		if( this.chB.getPlayer() == this.p ) 
		{
			if(this.a.getB() == null)
			{
			
			}
		}
		
		else
		{
			if( this.a.getO() == null)
			{
				Graphics g = null;
				
				this.p.aim(this.chB, new Obus());
				
				
				if(this.a.getB() == null)
				{
					//paint en vert
					g.setColor(Color.GREEN);
					g.drawOval(15, 15, 20, 20);
					g.setColor(Color.GREEN);
					g.fillOval(15, 15, 20, 20);
				}
			
				else
				{
					//paint en rouge
					g.setColor(Color.RED);
					g.drawOval(15, 15, 20, 20);
					g.setColor(Color.RED);
					g.drawOval(15, 15, 20, 20);
				}
				this.repaint();
			}
		}
		
	}

}
