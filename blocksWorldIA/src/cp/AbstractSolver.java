package cp;
import modelling.Variable;
import modelling.Constraint;
import java.util.Map;
import java.util.Set;

/*
 * Une classe abstraite qui fournit les attributs et méthodes pour un solver.
 */

public abstract class AbstractSolver implements Solver {
    protected Set<Variable> var;
    protected Set<Constraint> contraintes;

    // Constructeur
    public AbstractSolver(Set<Variable> var, Set<Constraint> contraintes){
        this.var = var;
        this.contraintes = contraintes;
    }

    /*
     * Méthode isConsistent, elle vérifie la consistence d'une instanciation partielle des variables par rapport aux contraintes
     * Return true si toutes les instanciations satisfaitent les contraintes
     */

    public boolean isConsistent(Map<Variable, Object> insPartielle){
        // On fait un parcourt de toutes les contraintes du probleme
        for(Constraint constraint : contraintes){
            // Ensuite on verifie si les variables qu'elle concerce sont incluse dans l'instanciation partielle
            if(insPartielle.keySet().containsAll(constraint.getScope())){ 
                if(!constraint.isSatisfiedBy(insPartielle)){ // On verifie si la contrainte est satisfaite
                    return false; // Si t'elle n'est pas le cas on return false
                }
            }
        }
        return true; // On retourne true si l'affectation est consistante c-à-d si aucune contrainte n'est violée
    }
}
