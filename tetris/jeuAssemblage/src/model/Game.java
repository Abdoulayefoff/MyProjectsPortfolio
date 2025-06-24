package model;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane; 
import javax.swing.SwingWorker;
import javax.swing.Timer; 

import model.ai.AI;
import model.ai.AdvancedAI;
import model.ai.RandomAI;
import model.placementstrategy.ArbitraryStrategy;
import model.placementstrategy.RandomStrategy;
import observer.AbstractListenableModel;
import piece.Coordinates;
import piece.Piece;


// Pour hassan
public class Game extends AbstractListenableModel {
    protected Plateau board; // le plateau de jeu
    protected int score; // le score
    protected int remainingActions; // le nombre d'action restantes
    protected boolean onGoing;
    protected boolean isAIplaying;

    public Game(Plateau board) {
        this.board = board;
        this.remainingActions = GameConfig.MAX_ACTIONS;
        this.onGoing = false;
        this.isAIplaying = false;
    }

    /**
     * Methode pour démarrer le jeu. Elle utilise un nouveau plateau et reinitilise le score et le nombre d'action restante
     */
    public void start() {
        // On crée un nouveau plateau avec la largeur et la hauteur souhaitées
        //Initialise le plateau avec la configuration initial
        board.reset();
        board.initialConfigTmp();

        
        this.score = 0;
        this.remainingActions = GameConfig.MAX_ACTIONS;
        this.onGoing = true;
        this.fireChangement();
    }

    /**
     * Determine si on peut bouger une piece ou pas car le jouer a un nombre max d'actions
     * @return
     */
    public boolean canMove() {
        return (this.remainingActions > 0);
    }

    public boolean movePiece(Piece piece, Coordinates position){
        boolean success = false;
        if(canMove()) {
            success = this.board.movePiece(piece, position);
            if(success == true) 
                this.remainingActions--;
            
            this.fireChangement();
        } else {
            this.gameOver();
        }
        
        return success;
    }

    public boolean rotatePiece(Piece piece, boolean clockwise){
        boolean success = false;
        if(canMove()) {
            if(clockwise == true) { 
                success = this.board.rotateClockWisePiece(piece);
            } else {
                success = this.board.rotateAntiClockWisePiece(piece);
            }

            if(success) {
                this.remainingActions--;
            }
            
            this.fireChangement();
        } else {
            this.gameOver();
        }
        return success;
    }


   /**
     * Elle retroune le score: elle appelle le methode evaluate de plateau
     * @return
     */
    public int getScore() {
        return this.score;
    }

    
    public void calculateScore(){
    
        this.score = (board.getHeight() * board.getWidth()) - Evaluateur.calculerAireRectangle(board.getPieces());
    }

    public void gameOver() {
        calculateScore();
        this.onGoing = false;
        this.fireChangement();
    }

    public void playRandomAI() {
        playAI(new RandomAI());
    }

    public void playAdvancedAI() {
        playAI(new AdvancedAI());
    }

    /**
     * Elle fait jouer le joueur IA
     */
    public void playAI(AI ai) {
        if(!isOnGoing())
            return;
        
        this.isAIplaying = true;
        this.board.setEnabled(false);
        Game tmp = this;

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (canMove() && tmp.isOnGoing()) {
                    ai.nextMove(tmp);
                    Thread.sleep(1000);
                }
                
                tmp.isAIplaying = false;
                tmp.board.setEnabled(true);
                tmp.onGoing = false;
                tmp.gameOver();
                return null;
            }

            @Override
            protected void done() {
            }
        };

        worker.execute();
    }

    public Plateau getBoard() {
        return board;
    }

    public int getRemainingActions() {
        return remainingActions;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public boolean useRandomPlacementStrategy(boolean yes) {
        if(onGoing == true) {
            return false;
        }

        if(yes == true) {
            this.board.setPlacementStrategy(new RandomStrategy());
        } else {
            this.board.setPlacementStrategy(new ArbitraryStrategy()); 
        }

        return true;
    }

    

    



}
