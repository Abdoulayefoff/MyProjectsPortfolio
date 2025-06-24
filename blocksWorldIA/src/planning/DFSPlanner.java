package planning;
import java.util.*;
import modelling.Variable;


/**
 * Cette classe genère un plan d'action pour atteindre un but à partir d'un état initial
 * Elle utilise une strategie de récherche en profondeur
 */
public class DFSPlanner implements Planner {
    public Map<Variable, Object> initialState;
    public Set<Action> actions;
    public Goal goal;
    public int sonde;
    public boolean isNodeCountActivated;

    //Constructeur
    public DFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal){
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.sonde = 0; // Initialise le compteur
        this.isNodeCountActivated = true; // Par défaut, la sonde est désactivée
    }

    //Methode activateNodeCount()
    @Override
    public void activateNodeCount(boolean activate) {
        this.isNodeCountActivated = activate;
    }

    //Methode plan(), elle genère un plan d'actions qui permettre d'atteindre le but à partir de l'état initial
    @Override
    public List<Action> plan(){
        Stack<Action> plan = new Stack<>(); // on utilise une pile pour stoker le plan
        Set<Map<Variable,Object>> visitedStates = new HashSet<>();
        // Appel de la methode auxiliaire recursive auxPlanDfs
        List<Action> res = auxPlanDfs(initialState,plan,visitedStates);
        
        if(res != null){
            return res; // on retourne le plan si une solution est trouvé
        }
        return null; // sinon on retourne null s'il n'y a pas de solution
    }
 
     /*
      *Methode recursive pour effectuer la recherche en profondeur dfs
      *@param currentState l'etat actuel du probleme
      *@param plan liste des actions en cours de construction
      * @param visitedState ensemble des etats deja visité
      *@return une liste d'action formant un plan si une solution est trouvée sinon null
      */
      public List<Action> auxPlanDfs(Map<Variable, Object> currentState, Stack<Action> plan, Set<Map<Variable, Object>> visitedStates) {
        if (goal.isSatisfiedBy(currentState)) {
            return new ArrayList<>(plan); // On vérifie si l'état actuel satisfait le but
        }
    
        // On parcourt toutes les actions disponibles
        for (Action action : actions) {
            if (action.isApplicable(currentState)) {
                Map<Variable, Object> nextState = action.successor(currentState);
                if (!visitedStates.contains(nextState)) {
                    if (isNodeCountActivated) {
                        sonde++; // Incrémente le compteur à chaque nouvel état visité
                    }
                    plan.push(action);
                    visitedStates.add(nextState);
    
                    // Appel récursif à la méthode avec le nouvel état
                    List<Action> subPlan = auxPlanDfs(nextState, plan, visitedStates);
                    if (subPlan != null) {
                        return subPlan;
                    } else {
                        plan.pop();
                    }
                }
            }
        }
        // On retourne null si aucune solution n'est trouvée
        return null;
    }
    

     //Methode getInitialState()
    @Override
    public Map<Variable, Object> getInitialState(){
        return initialState; 
    }

    //Methode getActions()
    @Override
    public Set<Action> getActions(){
        return actions;
    }

    //Methode getGoal()
    @Override
    public Goal getGoal(){
        return goal; // Retourne le but
    }
    
    //Methode getSonde()
    @Override
    public int getSonde(){
        return sonde;
    }
}
