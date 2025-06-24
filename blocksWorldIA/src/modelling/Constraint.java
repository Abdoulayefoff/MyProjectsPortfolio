package modelling;
import java.util.Map;
import java.util.Set;

/*
 * Interface représentant une contrainte sur un ensemble de variable
 */
public interface Constraint {

    // Methode getScope(), retournant l'ensemble des variables sur lesquelles porte la contrainte
    Set<Variable> getScope();

    /**
     * Methode issatisfiedBy, on a les variables comme cléfs et leurs affectation respectives comme valeurs
     * retourne un booleen selon que la contrainte est satisfaite ou non par l'instanciation donnée
     */
    boolean isSatisfiedBy(Map<Variable, Object> instanciation);
    
}
