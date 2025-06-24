package cp;
import modelling.Variable;
import java.util.Set;
import java.util.List;


/*
 * Interface représentant une heuristique de valeur pour un domaine de variables
 */
public interface ValueHeuristic {
    
    public List<Object> ordering(Variable variable, Set<Object> domaine);
}
