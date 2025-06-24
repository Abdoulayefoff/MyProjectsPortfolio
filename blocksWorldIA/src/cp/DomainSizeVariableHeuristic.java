package cp;
import modelling.Variable;
import java.util.Set;
import java.util.Map;

/*
 * Cette classe choisit une variable à assigner en fonction de la taille de son domaine
 */
public class DomainSizeVariableHeuristic implements VariableHeuristic {
    
    protected boolean preferable;
    
    // Constructeur
    public DomainSizeVariableHeuristic(boolean preferable){
        this.preferable = preferable;
    }

    // Methode best
    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        Variable bestVariable = null; // une variable pour stocker la meilleure variable trouvée
        int bestDomainSize = preferable ? Integer.MIN_VALUE : Integer.MAX_VALUE; // on initialise la taille de domaine de référence
    
        // on fait un parcours de chaque variable pour déterminer celle avec le domaine le plus grand ou le plus petit
        for (Variable variable : variables) {
            int domainSize = domains.get(variable).size(); // taille du domaine pour la variable actuelle
    
            // ensuite on met à jour la meilleure variable selon la préférence définie
            if ((preferable && domainSize > bestDomainSize) || 
                (!preferable && domainSize < bestDomainSize)) {
                bestDomainSize = domainSize; // on met à jour la taille
                bestVariable = variable; // la variable actuelle est définit comme meilleure
            }
        }
    
        return bestVariable; // on retourne la meilleure variable selon l'heuristique
    }
}
