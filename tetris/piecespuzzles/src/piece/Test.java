package piece;

import java.util.Map;

public class Test {
    
    public static void main(String[] args) {
       
        boolean clockwise = true; // change this to test clockwise or anticw
        Piece[] tab = {new PieceL(5,9), new PieceR(5,9), new PieceT(5,9)};
        for(int i=0; i<3; i++) {
            System.out.println("=============================");
            Piece piece = tab[i];
            
            for(int j=0; j<=4; j++) {
                System.out.println(piece);
                if(clockwise)
                    piece.rotateClockWise();
                else
                    piece.rotateAntiClockWise();
            }
        }




        
        
    }
}