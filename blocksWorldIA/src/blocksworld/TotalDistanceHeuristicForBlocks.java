package blocksworld;
import modelling.Variable;
import planning.Heuristic; 
import java.util.Map; 



//cette classe implemente une heuristique qui évalue la distance totale que chaque bloc doit parcourir pour atteindre sa position finale
public class TotalDistanceHeuristicForBlocks implements Heuristic {

    private Map<Variable, Object> goalState;

    //constructeur
    public TotalDistanceHeuristicForBlocks(Map<Variable, Object> goalState) {
        this.goalState = goalState;
    }

    //methode estimate, estime la distance totale que chaque bloc doit parcourir pour atteindre l'état final à partir de l'état actuel
    @Override
    public float estimate(Map<Variable, Object> étatActuel) {
        int distanceTotale = 0; 

        // On parcourt chaque variable (bloc) dans l'état actuel
        for (Variable variable : étatActuel.keySet()) {
            // on verifie si la variable représente un bloc en position "on"
            if (variable.getName().contains("on")) {
                int positionActuelle = (int) étatActuel.get(variable); // on recupère la position actuelle du bloc
                int positionFinale = (int) goalState.get(variable);// on recupère la position finale du bloc dans l'état final
                // Calcule la distance absolue entre la position actuelle et la position finale
                distanceTotale += Math.abs(positionActuelle - positionFinale);
            }
        }

        // on retourne enfin la distance totale que tous les blocs doivent parcourir
        return distanceTotale;
    }
}