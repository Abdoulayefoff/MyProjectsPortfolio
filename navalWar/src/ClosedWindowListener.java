package navalWar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class ClosedWindowListener extends WindowAdapter{
	
	private BattlefieldFrame base;
	
	public ClosedWindowListener(BattlefieldFrame base) {
		this.base = base;
	}

	@Override
    public void windowClosing(WindowEvent e) {
      if (JOptionPane.showConfirmDialog(this.base, "Désirez-vous quitter l'application ?") == JOptionPane.YES_OPTION) System.exit(0); 
    }

}
