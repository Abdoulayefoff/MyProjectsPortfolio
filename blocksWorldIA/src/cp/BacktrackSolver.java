package cp;
import modelling.Variable;
import modelling.Constraint;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * Cette classe implemente l'algo de backtracking
 */

public class BacktrackSolver extends AbstractSolver{
    
    // Constructeur
    public BacktrackSolver(Set<Variable> var, Set<Constraint> contraintes){
        super(var,contraintes);
    }

    /*
     * Méthode solve, elle utilise l'algo de bactracking pour résoudre un probleme de csp
     * Return une solution si elle existe ou null s'il n'y en pas
     */
    @Override
    public Map<Variable, Object> solve(){
        // On lance le solveur avec une affectation vide et toutes les variables non instanciées(LinkedList)
        return backTracking(new HashMap<>(), new LinkedList<>(var)); 
    }

    // Methode recursive pour implementer l'algo de backtracking
    public Map<Variable, Object> backTracking(Map<Variable, Object> partialInstanciation, LinkedList<Variable> varNoInstancie) {
        if (varNoInstancie.isEmpty())
            return partialInstanciation; // On retourne l'instanciation partielle si toutes les variables sont instancié
        // On récupere et on retire la 1ère variable non instanciée de la liste
        Variable currentVar = varNoInstancie.poll();

        // On parcours toutes les valeurs possibles du domaine de la variable courante
        for (Object value : currentVar.getDomain()) {
            // On cree une nouvelle affectation partielle en ajoutant value et currentVar à l'affectation actuelle
            Map<Variable, Object> newPartialInstanciation = new HashMap<>(partialInstanciation);
            newPartialInstanciation.put(currentVar, value);
            // On verifie si la nouvelle affectation est consistante avec les contraintes
            if (isConsistent(newPartialInstanciation)) {
                Map<Variable, Object> solution = backTracking(newPartialInstanciation, varNoInstancie); // Si l'affectation est consistante on continue la recherche récursive avec cette affectation
                if (solution != null) {
                    return solution; // Si on a une solution on la retourne
                }
            }
        }
        // Si on a aucune solution qui est trouvé pour cette variable, on la remet dans liste des variables non instanciées
        varNoInstancie.add(currentVar);
        return null; // On retourne null si aucune affectation valide n'a été trouvé
    }
}
