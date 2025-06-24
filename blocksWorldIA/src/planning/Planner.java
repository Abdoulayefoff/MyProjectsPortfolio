package planning;
import modelling.Variable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Interface representant un planificateur
 */
public interface Planner {

    List<Action> plan();
    Map<Variable, Object> getInitialState();
    Set<Action> getActions();
    Goal getGoal();

    void activateNodeCount(boolean activate); //Methode pour activer/desactiver la sonde
    int getSonde(); // Methode pour recuperer le nombre de noeuds explorés

    
}
