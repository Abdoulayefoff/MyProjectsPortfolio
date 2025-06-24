package cp;
import modelling.Variable;
import modelling.Constraint;
import java.util.Set;
import java.util.Map;


/*
 * Cette classe définit une heuristique qui choisit la variable en fonction du nombre de contraintes auxquelles elle participe
 */
public class NbConstraintsVariableHeuristic implements VariableHeuristic {
    
    protected Set<Constraint> constraints;
    protected boolean preferable;

    public NbConstraintsVariableHeuristic(Set<Constraint> constraints, boolean preferable){
        this.constraints = constraints;
        this.preferable = preferable;
    }

    // Methode best pour choisir la meilleure variable selon le nombre de contraintes
    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        Variable bestVariable = null; // une variable pour stocker la meilleure variable sélectionnée
        int bestScore; // elle sert de référence pour choisir la meilleure variable

        // On vérifie si la préférence est pour le plus grand nombre de contraintes
        if (preferable) { 
            bestScore = -1; // le score est initialisé à une valeur très basse pour maximiser
            for (Variable variable : variables) {
                int constraintCount = 0;
                // on parcourt chaque contrainte pour voir si elle contient la variable actuelle
                for (Constraint constraint : this.constraints) {
                    if (constraint.getScope().contains(variable)) // on vérifie si la contrainte inclut la variable
                        constraintCount++;
                }
                // ensuite on met à jour la meilleure variable si le compteur actuel est supérieur au meilleur score trouvé
                if (constraintCount > bestScore) {
                    bestScore = constraintCount;
                    bestVariable = variable;
                }
            }
        } 
        else { // si la préférence est pour le plus petit nombre de contraintes
            bestScore = Integer.MAX_VALUE; // le score est initialisé à une valeur très haute pour minimiser

            for (Variable variable : variables) {
                int constraintCount = 0;
                for (Constraint constraint : this.constraints) {
                    if (constraint.getScope().contains(variable)) {
                        constraintCount++; // on incrémente le compteur si la variable est impliquée
                    }
                }
                if (constraintCount < bestScore) {
                    bestScore = constraintCount; 
                    bestVariable = variable;
                }
            }
        }
        
        return bestVariable; // on retourne la meilleure variable selon l'heuristique définie
    }


}
