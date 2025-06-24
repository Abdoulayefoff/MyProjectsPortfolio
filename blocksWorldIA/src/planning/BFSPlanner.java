package planning;
import modelling.Variable;
import java.util.*;

//Cette classe utilise l'algo de recherche en largeur pour generer des plans d'actions
public class BFSPlanner implements Planner {
    
    public Map<Variable, Object> initialState;
    public Set<Action> actions; 
    public Goal goal; 
    public int sonde; 
    public boolean isNodeCoundActivated; 

    //Constructeur
    public BFSPlanner(Map<Variable, Object> initialState,Set<Action> actions, Goal goal){
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.sonde = 0; //Initialise le compteur
        this.isNodeCoundActivated = true; //Par defaut, la sonde est desactivé
    }

    // Methode pour activer ou desactiver la sonde
    @Override
    public void activateNodeCount(boolean activate){
        this.isNodeCoundActivated = activate;
    }

    //Methode plan
    @Override
    public List<Action> plan(){
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>(); // Map pour stoker les parents
        Map<Map<Variable, Object>, Action> plan = new HashMap<>(); // Map pour stoker les actions
        Set<Map<Variable, Object>> closed = new HashSet<>(); // Ensemble des etats déja visités
        Queue<Map<Variable, Object>> open = new LinkedList<>(); // File d'attente pour les etats explorés

        // On ajoute l'etat initial a la file d'attente et a la map des parents
        open.add(this.initialState);
        father.put(this.initialState, null); // Le parent de l'etat initial est null

        // Verifie si l'etat initial satisfait le goal
        if(this.goal.isSatisfiedBy(this.initialState)){
            return new ArrayList<Action>(); // Retourne une liste vide si l'objectif est deja atteint
        }

        // Boucle tant qu'il y a des etats a explorer
        while(!open.isEmpty()){
            Map<Variable, Object> instantiation = open.remove(); // Recupere et supprime l'etat en tete de la file d'attente. instanciation = currentState
            
            closed.add(instantiation); // on ajoute l'etat à l'ensemble des etats fermés

            // on explore toutes les actions possibles
            for (Action action : actions){
                if(action.isApplicable(instantiation)){
                    Map<Variable, Object> next = action.successor(instantiation);
                    //Verifie si l'etat suivant n'a pas deja ete visité et n'est pas dans la file d'attente
                    if(!closed.contains(next) && !open.contains(next)){
                        father.put(next, instantiation); // on enregistre l'etat courant comme parent de l'etat suivant
                        plan.put(next, action); // on enregistre l'action ayant mené à l'etat suivant
                        if(isNodeCoundActivated){
                            sonde++;
                        }
                        if(goal.isSatisfiedBy(next)){
                            return auxBfsPlan(father, plan, next);
                        }
                        else{
                            open.add(next); // on ajoute l'etat suivant a la file d'attente pour exploration
                        }
                    }
                }
            }

        }
        return null; //on retourne null si aucun plan n'est trouvé
    }


    /**
     * Methode auxiliaire pour reconstruire le plan à partir des structures father et plan
     * 
     * @param father la map des parents de chaque etat
     * @param plan la map des actions menant à chaque etat
     * @param goalState l'etat qui satisfait l'objectif
     * @return la liste des actions formant le plan
     */

    public List<Action> auxBfsPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father, Map<Map<Variable, Object>, Action> plan, Map<Variable, Object> goalState){
        List<Action> res = new ArrayList<>(); //liste pour stoker le plan
        Map<Variable, Object> currentState = goalState;
        while(currentState != null){
            Action action = plan.get(currentState);
            if(action != null){
                res.add(action); // Ajoute l'action au plan
            }
            currentState = father.get(currentState);
        }
        Collections.reverse(res); // on inverse le plan pour le ramener dans l'ordre correct
        return res; // Retourne le plan reconstruit
    }

    //Methode getInitialState()
    @Override
    public Map<Variable, Object> getInitialState(){
        return initialState; // Retourne l'etat initial
    }

    //Methode getActions()
    @Override
    public Set<Action> getActions(){
        return actions; // Retourne l'ensemble des actions;
    }

    //Methode getGoal()
    @Override
    public Goal getGoal(){
        return goal; // Retourne le but
    }
    
    // Methode pour recuperer le nombre de noeuds explorés
    @Override
    public int getSonde(){
        return sonde; 
    }

}