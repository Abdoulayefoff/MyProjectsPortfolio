package navalWar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class BattlefieldFrame extends JFrame {

	private Battlefield chBmodel1;
	private Battlefield chBmodel2;
	
	public BattlefieldFrame(Battlefield chB1, Battlefield chB2) { 
		
		super("SMDZ-Jeu de Bataille Navale");
		
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.chBmodel1 = chB1;
		this.chBmodel2 = chB2;
		
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		
	//Le panel de gauche gérant l'affichage des joueurs
		
		JPanel leftSide = new JPanel();
		leftSide.add(Box.createRigidArea(new Dimension(10,10)));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.PAGE_AXIS));
		leftSide.add(new JLabel("Joueur 1"));
		leftSide.add(new JLabel(this.chBmodel1.getPlayer().toString()));
		leftSide.add(Box.createVerticalStrut(20));
		leftSide.add(new JLabel("Joueur 2"));
		leftSide.add(new JLabel(this.chBmodel2.getPlayer().toString()));
	
	//Le panel de droite gérant l'affichage des champs de bataille et le déroulement du jeu	
		
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		
		JLabel gameRules = new JLabel();
		gameRules.setText( "<html> <h4> Chacun des deux joueurs dispose d’une flotte (ensemble de navires) positionnée sur une <br/>"
				+ "grille (représentant une portion de mer en 2 dimensions). Les joueurs tirent chacun à leur <br/>"
				+ "tour sur une position du camp adverse. Si un bateau adverse est impacté, le tir apparaît <br/>"
				+ "d’une façon spécifique (rouge dans l’illustration) par rapport à un tir raté (vert dans <br/>"
				+ "l’illustration). Le gagnant est le premier joueur parvenant à couler l’ensemble de la flotte adverse. </h4> </html>" );
		
		JPanel head = new JPanel(); //Gère l'affichage du texte placé au dessus.
		head.add(new JLabel(gameRules.getText()));
		head.setBorder(null);
		
		rightSide.add(head, BorderLayout.NORTH);
		
		//Ajout des champs de bataille à l'interface graphique -- Vues
		
		BattlefieldPanel chB1Panel = new BattlefieldPanel(this.chBmodel1); //Faire passer en param, un chB
		BattlefieldPanel chB2Panel = new BattlefieldPanel(this.chBmodel2);
		
		
		JSplitPane middle = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chB1Panel, chB2Panel); //Gère l'affichage des champs de bataille
		middle.setResizeWeight( 0.5 );
		
		rightSide.add(middle, BorderLayout.CENTER);
		
	//Le panel regroupant le panel de gauche et celui de droite	
		
		JSplitPane base = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSide, rightSide); //Constitue l'élément au centre du contentPane
		base.setResizeWeight( 0.1 );
		base.setBorder(BorderFactory.createTitledBorder("<html> <h4> Mer </h4> </html>"));
		
		cp.add(base, BorderLayout.CENTER);
		
		ClosedWindowListener cwl = new ClosedWindowListener(this);
		this.addWindowListener(cwl);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
	}
	
}
