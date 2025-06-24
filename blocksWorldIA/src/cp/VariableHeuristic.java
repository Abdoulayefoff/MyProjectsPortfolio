package cp;
import modelling.Variable;
import java.util.Set;
import java.util.Map;


/*Interface représentant une heuristique pour selectionner la meilleure variable */
public interface VariableHeuristic {

    public Variable best(Set<Variable> variable, Map<Variable, Set<Object>> domains);
}
