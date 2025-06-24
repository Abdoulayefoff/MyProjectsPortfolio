package planning;
import java.util.*; 
import modelling.Variable;


// Cette classe utilise l'algo Djikstra pour generer des plans optimaux
public class DijkstraPlanner implements Planner {

    protected Map<Variable, Object> initialState;
    protected Set<Action> actions;
    protected Goal goal;
    protected int sonde; 
    protected boolean isNodeCountActivated;
    
    //Constructeur
    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.sonde = 0; 
        this.isNodeCountActivated = true;
        
    }

    // Méthode pour activer ou désactiver la sonde
    @Override
    public void activateNodeCount(boolean activate) {
        this.isNodeCountActivated = activate;
    }

    //Methode Plan
    @Override
    public List<Action> plan() {
        //On cree une map pour associer un etat a son father
        Map<Map<Variable, Object>, Map<Variable, Object>> father = 
        new HashMap<Map<Variable, Object>, Map<Variable, Object>>();
        father.put(this.initialState, null); // l'etat initial du père est null

        // On cree une map pour associer chaque etat à l'action qui permet de l'obtenir
        Map<Map<Variable, Object>, Action> plan = new HashMap<Map<Variable, Object>, Action>();

        // On cree une map ppour stoker la distance de chaque etat à l'etat initial
        Map<Map<Variable, Object>, Double> distance = new HashMap<Map<Variable, Object>, Double>();
        distance.put(initialState, 0.0); // set distance of initial state to 0
        
        // Goals
        Set<Map<Variable, Object>> goals = new HashSet<Map<Variable, Object>>();

        // Open states: etats visités
        Set<Map<Variable, Object>> open = new HashSet<Map<Variable, Object>>();
        open.add(initialState);
        while(!open.isEmpty()) {
            Map<Variable, Object> instantiation = getMinDistanceNode(open, distance);
            open.remove(instantiation);
            if(this.getGoal().isSatisfiedBy(instantiation)) {
                goals.add(instantiation);
            }
            for(Action action: this.actions) {
                if(action.isApplicable(instantiation)) {
                    Map<Variable, Object> next = action.successor(instantiation);
                    if(!distance.containsKey(next)) {
                        distance.put(next, Double.POSITIVE_INFINITY);
                    }
                    if(distance.get(next) > (distance.get(instantiation) + action.getCost())){
                        distance.put(next, distance.get(instantiation) + action.getCost());
                        father.put(next, instantiation);
                        plan.put(next, action);
                        open.add(next);
                        if (isNodeCountActivated) {
                            sonde++; // Incrémente le compteur si la sonde est activée
                        }
                    }
                }
            }
        }

        if(goals.isEmpty()) {
            return null;
        } else {
            return getDijkstraPlan(father, plan, goals, distance);
        }   
    }

    //Methode auxilliaire    
    public List<Action> getDijkstraPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father,
                                      Map<Map<Variable, Object>, Action> plan,
                                      Set<Map<Variable, Object>> goals,
                                      Map<Map<Variable, Object>, Double> distance) {
    List<Action> dijPlan = new ArrayList<>(); // Utilisation d'une liste pour le plan
    Map<Variable, Object> goal = getMinDistanceNode(goals, distance);

    // Remonter jusqu'à l'état initial en ajoutant les actions dans l'ordre inverse
    while (goal != null && father.get(goal) != null) {
        Action action = plan.get(goal);
        if (action != null) {
            dijPlan.add(action); // on ajoute l'action à la liste
        }
        goal = father.get(goal); // Se déplace vers l'état parent
    }
    // Inverser la liste pour obtenir le bon ordre
    Collections.reverse(dijPlan);

    return dijPlan; // on retourne le plan reconstruit
    }

   /**
    * Methode pour avoir la distance minimale
    * @param nodes noeud a chercher
    * @param distance une map pour la distance des noeuds
    * @return la distance minimale
    */
    public Map<Variable, Object> getMinDistanceNode(Set<Map<Variable, Object>> nodes, Map<Map<Variable, Object>,
    Double> distance) {
        Map<Variable, Object> min = null;
        for(Map<Variable, Object> element: nodes) {
            if(min == null || distance.get(min) > distance.get(element)) {
                min = element;
            }
        }
        return min;
    }

    //Methode getInitialState()
    @Override
    public Map<Variable, Object> getInitialState() {
        return this.initialState;
    }
    //Methode getActions()
    @Override
    public Set<Action> getActions() {
        return this.actions;
    }
    //Methodes getGoal()
    @Override
    public Goal getGoal() {
        return this.goal;
    }
    // Méthode pour récupérer le nombre de nœuds explorés
    @Override
    public int getSonde(){
        return sonde; 
    }


    
    
}