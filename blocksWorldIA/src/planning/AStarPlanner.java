package planning; 
import modelling.Variable;
import java.util.*;

/*
 * Cette classe implemente l'algorithme de recherche A*
 * Elle utilise une heuristique pour pouvoir estimer le cout restant pour atteindre le goal ensuite selectionne l'action à effectuer en fonction de l'estimation
 */
public class AStarPlanner implements Planner {
    protected Map<Variable, Object> initialState; 
    protected Set<Action> actions;
    protected Goal goal; 
    protected Heuristic heuristic; 
    protected int sonde; 
    protected boolean isNodeCountActivated; 


    // Maps pour stocker le plan, les parents, les distances et les valeurs
    protected Map<Map<Variable, Object>, Action> plan = new HashMap<>();
    protected Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
    protected Map<Map<Variable, Object>, Double> distance = new HashMap<>();
    protected Map<Map<Variable, Object>, Double> value = new HashMap<>();

    // Constructeur 
    public AStarPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal, Heuristic heuristic) {
        this.initialState = initialState; 
        this.actions = actions; 
        this.goal = goal; 
        this.heuristic = heuristic;
        this.sonde = 0;
        this.isNodeCountActivated = true; 
    }
    
    // Méthode pour activer ou désactiver la sonde
    @Override
    public void activateNodeCount(boolean activate) {
        this.isNodeCountActivated = activate;
    }

    // Méthode plan() pour générer un plan
    @Override
    public List<Action> plan() {
        Set<Map<Variable, Object>> open = new HashSet<>();
        open.add(initialState); // Ajout de l'état initial à l'ensemble open
        father.put(initialState, null); // Le parent de l'état initial est null
        distance.put(initialState, 0.0); // La distance de l'état initial est 0
        value.put(initialState, (double) heuristic.estimate(initialState)); // Estimation initiale par l'heuristique

        while (!open.isEmpty()) { 
            Map<Variable, Object> instantiation = chooseMinValue(open); // Choix de l'état avec la valeur minimale
            
            if (goal.isSatisfiedBy(instantiation)) {
                return getAStarPlan(father, plan, instantiation); // on retourne le plan trouvé
            } else {
                open.remove(instantiation); 
            }
            //ON parcourt toutes les actions
            for (Action action : actions) {
                if (action.isApplicable(instantiation)) {
                    Map<Variable, Object> next = action.successor(instantiation); // État suivant après application de l'action
                    if (!distance.containsKey(next)) {
                        distance.put(next, Double.POSITIVE_INFINITY); // Initialise la distance à l'infini
                    }
                    if (distance.get(next) > distance.get(instantiation) + action.getCost()) {
                        distance.put(next, distance.get(instantiation) + action.getCost()); // Met à jour la distance
                        value.put(next, distance.get(next) + heuristic.estimate(next)); // Met à jour la valeur avec heuristique
                        father.put(next, instantiation); // on définit le parent de l'état suivant
                        plan.put(next, action); // on ajoute l'action au plan pour l'état suivant
                        open.add(next); // on ajoute l'état suivant à l'ensemble open
                        if (isNodeCountActivated) {
                            sonde++; 
                        }
                    }
                }
            }
        }
        return null; // on retourne null s'il n'y a pas de plan
    }
    
    /**
     * Méthode auxiliaire pour choisir l'état avec la valeur minimale
     * @param open represente l'ensemble des etats ouvert
     * @return L'état avec la valeur minimale
     */
    private Map<Variable, Object> chooseMinValue(Set<Map<Variable, Object>> open) {
        Map<Variable, Object> minState = null;
        double minValue = Double.MAX_VALUE;
        for (Map<Variable, Object> state : open) {
            if (value.get(state) < minValue) { // Compare avec la valeur minimale
                minValue = value.get(state);
                minState = state;
            }
        }
        return minState; // Retourne l'état avec la valeur minimale
    }

    /**
     * Méthode auxiliaire pour reconstruire le plan à partir des structures father et plan.
     * @param father La map des parents de chaque état
     * @param plan La map des actions menant à chaque état
     * @param instantiation L'état final atteignant le but
     * @return La liste des actions formant le plan
     */
    private List<Action> getAStarPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father,
                                       Map<Map<Variable, Object>, Action> plan,
                                       Map<Variable, Object> instantiation) {
        List<Action> result = new ArrayList<>();
        Map<Variable, Object> currentState = instantiation; // Commence à partir de l'état objectif
        // Retrace le chemin jusqu'à l'état initial
        while (currentState != null) {
            Action action = plan.get(currentState); // on rcupère l'action qui a mené à l'état courant
            if (action != null) {
                result.add(action); // on ajoute l'action au plan
            }
            currentState = father.get(currentState); // Se déplace vers l'état parent
        }
        Collections.reverse(result); // on inverser le plan pour le ramener dans l'ordre correct
        return result; // on retourne le plan reconstruit
    }

    //Methode getInitialState()
    @Override
    public Map<Variable, Object> getInitialState() {
        return initialState; // Retourne l'état initial
    }
    //Methode getActions()
    @Override
    public Set<Action> getActions() {
        return actions; // Retourne l'ensemble des actions
    }
    //Methode getGoal()
    @Override
    public Goal getGoal() {
        return goal; // Retourne le but
    }
    // Méthode pour récupérer le nombre de nœuds explorés
    @Override
    public int getSonde(){
        return sonde; // Retourne le nombre de nœuds explorés
    }
}