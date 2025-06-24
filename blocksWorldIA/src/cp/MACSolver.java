package cp;
import modelling.Constraint;
import modelling.Variable;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;


/*
 * Cette classe implémente un solveur en utilisant l'algo de MAC (Maintien de la cohérence des arcs)
 */
public class MACSolver extends AbstractSolver {
    
    protected ArcConsistency arcConsistence;

    //Constructeur
    public MACSolver(Set<Variable> var, Set<Constraint> constraints){
        super(var, constraints);
        this.arcConsistence = new ArcConsistency(constraints); // On initialise la consistance d'arc avec les contraintes
    }

    //Méthode solve pour resoudre le csp avec l'algo MAC
    @Override
    public Map<Variable, Object> solve(){
        Map<Variable, Set<Object>> domains = new HashMap<>(); //On cree une map pour stocker le domaines des variables
        // On remple la map avec les variables et leurs domaines
        for(Variable v : var){
            domains.put(v, v.getDomain());
        }
        // Appelle a la methode recursive recursiveMac pour resoudre le probleme
        return recursiveMac(new HashMap<>(), new LinkedList<>(var), domains);
    }

    // Methode recursiveMac pour implementer l'algo de MAC
    public Map<Variable, Object> recursiveMac(Map<Variable, Object> partialInstaciation, LinkedList<Variable> varNoInstancie, Map<Variable, Set<Object>> domains){
        // si toutes les variables sont instanciées, on retourne l'affectation partielle
        if(varNoInstancie.isEmpty())
            return partialInstaciation;

        // On applique la consistance d'arc pour verifier la validité des domaines
        if(!arcConsistence.ac1(domains))
            return null; // si les domaines ne sont pas cohérents pas de solution
        
        Variable v = varNoInstancie.poll(); // 1ere variable non instanciée
        // On parcours chaque valeur dans le domaine de v 
        for(Object valeur : domains.get(v)){
            Map<Variable, Object> currentState = new HashMap<>(partialInstaciation);
            currentState.put(v, valeur); // On affecte la valeur a la variable
            if(isConsistent(currentState)){
                // on fait un appel recursif à la methode avec un nouvel etat
                Map<Variable, Object> newState = recursiveMac(currentState, varNoInstancie, domains);
                if(newState != null)
                    return newState; // On retourne le nouvel etat si une solution est trouvée
            }
        }
        varNoInstancie.add(v); // On remet la variable non instanciée dans la liste
        return null; // si aucune solution n'est trouvé
    }
}
