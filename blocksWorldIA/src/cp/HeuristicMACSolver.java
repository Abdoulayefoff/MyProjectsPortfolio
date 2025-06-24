package cp;
import modelling.Variable;
import modelling.Constraint;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

/*
 * Cette classe resout des problemes de csp en utilisant des heuristiques
 */
public class HeuristicMACSolver extends AbstractSolver {
    
    protected ValueHeuristic valueHeuristic;
    protected VariableHeuristic variableHeuristic;

    //Constructeur
    public HeuristicMACSolver(Set<Variable> variables, Set<Constraint> constraints, VariableHeuristic variableHeuristic,ValueHeuristic valueHeuristic){
        super(variables, constraints);
        this.variableHeuristic = variableHeuristic;
        this.valueHeuristic = valueHeuristic;
        
    }

    
    // Methode solve pour resoudre le csp, elle utilise l'algo MAC
    @Override
    public Map<Variable, Object> solve() {
        Map<Variable, Set<Object>> domains = new HashMap<>(); // une map pour stocker les domaines des variables
        for (Variable variable : var) {
            domains.put(variable, variable.getDomain());
        }
        // Appel de la méthode mac pour trouver une solution
        return this.mac(new HashMap<>(), new LinkedList<>(var), domains);
    }

    //Méthode mac, elle utilise l'algo de baktracking avec arc consistency
    public Map<Variable, Object> mac(Map<Variable, Object> partialInstantiation, LinkedList<Variable> varNoInstancie, Map<Variable, Set<Object>> domains) {
        // Si toutes les variables sont instanciées, retourne l'affectation partielle
        if (varNoInstancie.isEmpty())
            return partialInstantiation;

        //application de la cohérence d'arc sur les domaines
        ArcConsistency arcConsistency = new ArcConsistency(contraintes);
        if (!arcConsistency.ac1(domains))
            return null; // on retourne null si les domaines sont incohérents
    

        // on choisit la meilleure variable en utilisant l'heuristique
        Variable variable = variableHeuristic.best(new HashSet<>(varNoInstancie), domains);
        varNoInstancie.remove(variable); // la variable selectionné est retiré de la liste des non instanciées

        // on fait un parcours de chaque valeur dans le domaine de la variable choisie, en suivant l'ordre de l'heuristique de valeur
        Set<Object> domainValues = domains.get(variable);
        for (Object value : valueHeuristic.ordering(variable, domainValues)) {
            Map<Variable, Object> newInstantiation = new HashMap<>(partialInstantiation);
            newInstantiation.put(variable, value);

            // création d'une copie temporaire des domaines et réduction du domaine de la variable
            Map<Variable, Set<Object>> updatedDomains = new HashMap<>(domains);
            Set<Object> reducedDomain = new HashSet<>();
            reducedDomain.add(value);
            updatedDomains.put(variable, reducedDomain);

            // une vérification de la cohérence de la nouvelle instantiation partielle
            if (isConsistent(newInstantiation)) {
                //un appel récursif pour continuer la recherche avec les nouveaux domaines mis à jour
                Map<Variable, Object> result = mac(newInstantiation, varNoInstancie, updatedDomains);
                if (result != null) {
                    return result; // return result si une solution est trouvé
                }
            }
        }
        // Si aucune solution n'est trouvée on remet la variable dans la liste des non instanciées
        varNoInstancie.addFirst(variable);
        return null; //return null if no solution
    }

}
