package model.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.Game;
import model.GameConfig; 
import model.Plateau;
import piece.Coordinates;
import piece.Piece;

public class RandomAI implements AI{
    List<Integer> excludeX = new ArrayList<>();
    List<Integer> excludeY = new ArrayList<>();

    public RandomAI() {

    }

     @Override
    public void nextMove(Game game) {
        Random random = new Random(); 
        
    e
        int actionAleatoire = random.nextInt(3); // 0 pour déplacer, 1 pour rotation dans le sens des aiguilles d'une montre, 2 pour rotation dans le sens inverse des aiguilles d'une montre
    
        switch (actionAleatoire) {
            case 0:
                randomRotate(game, true);
                break;
            case 1:
                randomRotate(game, false);
                
            case 2:
                randomMove(game);
                break;
        }
        
        
    }

    public void randomMove(Game game) {
        Plateau plateau = game.getBoard();
        List<Piece> pieces = plateau.getPieces();
        
    
        if (pieces.isEmpty()) {
            return; // Si notre liste de pièces est vide, il n'y a rien à faire, alors retournez.
        }
    
        Random random = new Random(); 
    
        // Sélectionnez une pièce au hasard dans notre liste
        Piece pieceAleatoire = pieces.get(random.nextInt(pieces.size()));

    
        // Générez une position aléatoire
        Coordinates positionAleatoire = new Coordinates(
                generateRandomIntWithExclusionsAndMax(excludeX, plateau.getWidth()),
                generateRandomIntWithExclusionsAndMax(excludeY, plateau.getHeight())
        );

        
    
        while (!game.movePiece(pieceAleatoire, positionAleatoire)) {
            if(excludeX.size() == game.getBoard().getHeight() || excludeY.size() == game.getBoard().getWidth()){
                game.gameOver();
            }
            positionAleatoire = new Coordinates(
                    generateRandomIntWithExclusionsAndMax(excludeX, plateau.getWidth()),
                    generateRandomIntWithExclusionsAndMax(excludeY, plateau.getHeight())
            );
            excludeX.add(positionAleatoire.getX());
            excludeY.add(positionAleatoire.getY());
        
        }
    }

    public void randomRotate(Game game, boolean clockwise) {
        Plateau plateau = game.getBoard();
        List<Piece> pieces = plateau.getPieces();
        int attempt = 100;
    
        if (pieces.isEmpty()) {
            return; // Si notre liste de pièces est vide, il n'y a rien à faire, alors retournez.
        }
    
        Random random = new Random(); 
    
        // Sélectionnez une pièce au hasard dans notre liste
        Piece pieceAleatoire = pieces.get(random.nextInt(pieces.size()));

    
        while (!game.rotatePiece(pieceAleatoire, clockwise)) {
           if(attempt <= 0) {
            break;
           }
           attempt --;
        }
        randomMove(game);
    }

    // Function to generate a random integer excluding specified values
 
    public static int generateRandomIntWithExclusionsAndMax(List<Integer> exclude, int maxValue) {
        Random random = new Random(); 

       
        int randomInt = IntStream.generate(() -> random.nextInt(maxValue + 1))
                .filter(value -> !exclude.contains(value))
                .findFirst()
                .orElseThrow();

        return randomInt;
    }
    
}