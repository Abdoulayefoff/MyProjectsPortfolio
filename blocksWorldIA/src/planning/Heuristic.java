package planning;
import modelling.Variable; 
import java.util.Map; 

/**
 * Interface pour les heuristiques.
 */
public interface Heuristic {
    /**
     * Estime le coût d'un plan optimal depuis un état donné jusqu'au but.
     *
     * @param state L'état à évaluer.
     * @return Une valeur de type float représentant l'estimation du coût.
     */
    float estimate(Map<Variable, Object> state);
}