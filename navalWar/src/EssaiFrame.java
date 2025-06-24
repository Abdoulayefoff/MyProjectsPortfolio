package navalWar;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class EssaiFrame {

	public static void main(String[] args) throws Exception {
		
		
		String playerName = null;
		//Integer haut = null, larg = null;
		final int TAIL = 10;
		
		//La boîte de dialogue de départ
		//JTextField x = new JTextField(), y = new JTextField();
		JTextField player = new JTextField();
		
		JPanel panInfo = new JPanel();
	    panInfo.setLayout(new BoxLayout(panInfo, BoxLayout.PAGE_AXIS));
	    
	    panInfo.add(new JLabel("<html> <h4> Entrez votre nom </h4> </html>"));
	    player.setPreferredSize(new Dimension(100,25));
	    panInfo.add(player);
	    
	    
	    panInfo.setBorder(BorderFactory.createTitledBorder("<html> <h2> Infos sur le joueur </h2> </html>")); //sur le jeu
	    
	    JOptionPane startWlc = new JOptionPane(panInfo, JOptionPane.QUESTION_MESSAGE);
	    Integer fin = null;
	    
	    // On crée la boite de dialogue correspondante :
	 	JDialog dialog = startWlc.createDialog(null, "SMDZ-Prelude");
	 	dialog.setAlwaysOnTop(true); //On la met au dessus
	  
	 	dialog.setVisible(true);
	 	dialog.dispose();
	 		
	 	// On traite la valeur reçue :
		Object selectedValue = startWlc.getValue();
		if (selectedValue == null)
			fin = JOptionPane.CLOSED_OPTION;
	 
		if (selectedValue instanceof Integer)
			fin = ((Integer) selectedValue).intValue();

	    
	    if(fin == JOptionPane.OK_OPTION) {
	    		
	    	try
		    {
		    	//haut = Integer.parseInt(x.getText());
		    	//larg = Integer.parseInt(y.getText());
		    	playerName = player.getText();
		    }
		    catch(NumberFormatException e) //Si l'utilisateur entre autre chose que des nombres pour la hauteur et/ou la largeur des champs de bataille, on lui demande de relancer le programme.
		    { 
		    	JOptionPane.showMessageDialog(null,"<html> <h2> Vous n'avez pas entrer des dimensions correctes! Veuillez relancer </h2> </html>","SMDZ-Erreur", JOptionPane.PLAIN_MESSAGE);
		    }
	    	
				
	    	Battlefield chB1 = new Battlefield(TAIL, TAIL); //Battlefield(haut, larg);
			Battlefield chB2 = new Battlefield(TAIL, TAIL); //Battlefield(haut, larg);
			    	    
			Player p1 = new HumanPlayer(playerName, chB1);
			Player p2 = new RandomPlayer(chB2);
			    
			chB1.setPlayer(p1);
			chB2.setPlayer(p2);
			    
			// TODO Créer la frame		
			    
			BattlefieldFrame btFrame = new BattlefieldFrame(chB1, chB2);
			btFrame.setLocationRelativeTo(null);
			btFrame.setVisible(true); 

		}
	    
	    else //Si l'utilisateur appuie sur annuler ou s'il ferme la boîte de dialogue, il arrête l'exécution du programme.
		{
			JOptionPane.showMessageDialog(null,"<html> <h2> AU REVOIR! </h2> <br/> Ce fut un plaisir. </html>","SMDZ-Au revoir", JOptionPane.PLAIN_MESSAGE);
			System.exit(1);
		}
	    
	}

}
