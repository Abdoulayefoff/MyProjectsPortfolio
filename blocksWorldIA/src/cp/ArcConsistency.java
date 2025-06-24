package cp;
import modelling.Variable;
import modelling.Constraint;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

/*
 * Cette classe permet de gerer la cohérence des arcs dans un probleme de csp
 */
public class ArcConsistency {

    protected Set<Constraint> binaryConstraints;
    protected Set<Constraint> unaryConstraints;  

    // Constructeur qui initialise les ensembles de contraintes unaires et binaires
    public ArcConsistency(Set<Constraint> setConstraints) {
        unaryConstraints = new HashSet<>(); // on initialise l'ensemble des contraintes unaires.
        binaryConstraints = new HashSet<>(); 

        // On parcourt chaque contrainte dans l'ensemble donné
        for (Constraint constraint : setConstraints) { 
            Set<Variable> scope = constraint.getScope(); // On récupère le domaine (scope) des variables concernées par la contrainte
            switch (scope.size()) { 
                case 1 -> unaryConstraints.add(constraint); // Si la contrainte est unaire, elle est ajoutée aux contraintes unaires
                case 2 -> binaryConstraints.add(constraint); 
                default -> throw new IllegalArgumentException("Les contraintes doivent être unaires ou binaires");
            }
        }
    }

    /*
     * Méthode enforceNodeConsistency, elle garantit que les valeurs des domaines des vairables respectent les contraintes unaires
     */
    public boolean enforceNodeConsistency(Map<Variable, Set<Object>> domains) {
        // on parcours de chaque variable dans la map des domaines
        for (Variable v : domains.keySet()){
            Set<Object> domainValue = new HashSet<>(domains.get(v)); 
            Set<Object> valueToDelete = new HashSet<>(); 
        
            // on parcours chaque valeur dans le domaine de la variable v
            for(Object value: domainValue){
                // on parcours chaque contrainte unaire
                for(Constraint constraint : unaryConstraints){
                    if(constraint.getScope().contains(v)){// on verifie si la contrainte concerne la variable v
                        Map<Variable, Object> currentState = new HashMap<>();
                        currentState.put(v, value);
                        // on verifie si la contrainte est satisfaite pour cet état
                        if(!constraint.isSatisfiedBy(currentState))
                            valueToDelete.add(value); 
                    }
                }
            }
            domainValue.removeAll(valueToDelete);  // on supprime des valeurs non valides
        
            // on remet à jour le domaine de la variable v avec les nouvelles valeurs (après suppression)
            domains.put(v, domainValue);
        }
        return true;// on retourne true si aucun domaine n'est vide (ce qui signifie que toutes les contraintes sont respectées)
    }
    
    
    /*
     * Méthode revise, elle garantit que pour chaque valeur d'une variable, il existe au moins une valeur compatible dans l'autre variable
     * Return un bool indiquant si des valeurs on été supprimées ou pas
     */
    public boolean revise(Variable v1, Set<Object> d1, Variable v2, Set<Object> d2) {
        // un indicateur pour savoir si des valeurs ont été supprimées du domaine de v1
        boolean delete = false;
    
        // on cree une copie modifiable du domaine d1 pour pouvoir apporter des modifications sans affecter l'original
        Set<Object> modifiableD1 = new HashSet<>(d1);
    
        // Parcourt chaque valeur du domaine de v1
        for (Object value1 : new HashSet<>(modifiableD1)) {  // On crée une nouvelle copie modifiable
            boolean viable = false;
            for (Object value2 : d2) {
                boolean allSatisfied = true;
                // on parcourt toutes les contraintes binaires qui concernent v1 et v2
                for (Constraint constraint : binaryConstraints) {
                    // on verifie si la contrainte concerne à la fois v1 et v2
                    if (constraint.getScope().contains(v1) && constraint.getScope().contains(v2)) {
                        Map<Variable, Object> state = new HashMap<>();
                        state.put(v1, value1);
                        state.put(v2, value2);
                        if (!constraint.isSatisfiedBy(state)) {
                            allSatisfied = false; 
                            break;  // On arrête la vérification pour cette paire de valeurs
                        }
                    }
                }
                if (allSatisfied) {
                    viable = true;
                    break; 
                }
            }
            // Si aucune valeur de v2 ne rend value1 viable, on supprime value1 du domaine de v1
            if (!viable) {
                modifiableD1.remove(value1);  // Suppression de la valeur non viable
                delete = true;  // on marque que des suppressions ont eu lieu
            }
        }
        // Mise à jour du domaine d1 avec les valeurs restantes dans modifiableD1
        d1.clear(); 
        d1.addAll(modifiableD1);
        return delete;// Retourne true si des valeurs ont été supprimées du domaine de v1
    }
    
    
    
    /*
     * Methode ac1, elle implement l'algo ac1
     */
    public boolean ac1(Map<Variable, Set<Object>> domains) {
        // On verifie la consistance des noeuds, return false si un domaine est vide
        if (!enforceNodeConsistency(domains)) {
            return false;
        }
    
        boolean change; // pour indiquer si un changement a eu lieu
        //Boucle jusqu'a ce qu'il n'y ait plus de changement
        do {
            change = false;  
            // on parcourt tous les couples de variables
            for (Variable v1 : domains.keySet()) {
                for (Variable v2 : domains.keySet()) {
                    if (!v1.equals(v2)) {
                        if (revise(v1, domains.get(v1), v2, domains.get(v2))) {
                            change = true;  // Si le domaine a été modifié
                        }
                    }
                }
            }
        } while (change);  // on répète tant qu'il y'a des changements
    
        //On verifie si un domaine a été vidé apres l'algo de revise
        for (Variable x : domains.keySet()) {
            if (domains.get(x).isEmpty()) {
                return false;  // si au moins un domaine est vide
            }
        }
    
        return true;  // vrai si tous les domaines sont encore valides
    }
    
    
}
