package views.gui;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import model.Game;
import observer.ListeningModel;

import java.awt.*;


public class MainWindow extends JFrame implements ListeningModel {
    private static final long serialVersionUID = 7376825297884956163L;
    Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWith = (tailleEcran.width*2/3)+150;
    int screenheight = (tailleEcran.height*2/3)+100;

    private final Rendu zoneRendu;
	private  BoardView grid;
    private Game game; 


    public MainWindow(Game game) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
        
            e.printStackTrace();
        }

        this.game = game;
		JPanel contentPane= (JPanel)this.getContentPane();

		this.zoneRendu = new Rendu(game);
		this.grid = new BoardView(game, game.getBoard());
        this.grid.setLayout(null);
        
		contentPane.add(createPage(),BorderLayout.CENTER);


		this.setSize(screenWith,screenheight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);
		
	}

	private JPanel createPage(){
		JPanel page = new JPanel(new BorderLayout(50, 30));
		page.setPreferredSize(new Dimension(300,200));
		this.zoneRendu.rendu.add(this.grid,BorderLayout.CENTER);
        
		page.add(this.zoneRendu.getRenduPanel(),BorderLayout.CENTER);
		//creation de panel vide pour rejoudre le probleme des margin
		JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(1,0));
		page.add(south,BorderLayout.SOUTH);
		return page;
	}

    @Override
    public void modelUpdtae(Object source) {
        return;
    }

}
