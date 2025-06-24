package blocksworld; 
import modelling.Variable;
import planning.Heuristic;
import java.util.Map; 



//Cette classe implemente une heuristique estimant le nombre de blocs qui ne sont pas dans la position correcte
public class BlocksOnWrongPositionHeuristic implements Heuristic {
    private Map<Variable, Object> goalState;

    //Constructeur
    public BlocksOnWrongPositionHeuristic(Map<Variable, Object> goalState) {
        this.goalState = goalState; 
    }

    //methode estime pour estimer le coût pour atteindre l'état final à partir de l'état actuel
    @Override
    public float estimate(Map<Variable, Object> state) {
        int incorrectBlocks = 0;

        //on parcours de toutes les variables de l'état actuel
        for (Variable var : state.keySet()) {
            //ensuite on verifie si la variable représente un bloc en position "on" et si elle est mal placée
            if (var.getName().contains("on") && !state.get(var).equals(goalState.get(var))) {
                // on incremente le compteur si le bloc est mal placé
                incorrectBlocks++;
            }
        }

        // enfin on retourne le nombre total de blocs mal placés
        return incorrectBlocks;
    }
}