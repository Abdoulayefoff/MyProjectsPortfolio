
package planning;
import modelling.Variable;
import java.util.*; 

public class Main {
    public static void main(String[] args) {
        // On crée des domaines pour les variables
        Set<Object> domainX = new HashSet<>(Arrays.asList("start", "intermediate", "goal"));
        Set<Object> domainY = new HashSet<>(Arrays.asList(0, 1, 2));

        // On crée les variables
        Variable x = new Variable("x", domainX);
        Variable y = new Variable("y", domainY);
        System.out.println("Nom de la variable x : " + x.getName());
        System.out.println("Domaine de la variable x : " + x.getDomain());

        // Objectif
        Goal goal = new BasicGoal(Map.of(x, "goal"));

        // État initial
        Map<Variable, Object> initialState = new HashMap<>();
        initialState.put(x, "start");
        initialState.put(y, 0);

        // Créer des actions avec différentes transitions
        Set<Action> actions = new HashSet<>();
        Action action1 = new BasicAction(Map.of(x, "start"), Map.of(x, "intermediate", y, 1), 1);
        Action action2 = new BasicAction(Map.of(x, "intermediate", y, 1), Map.of(x, "goal"), 1);
        Action action3 = new BasicAction(Map.of(y, 0), Map.of(y, 1), 1); // Incrémente y
        Action action4 = new BasicAction(Map.of(y, 1), Map.of(y, 2), 1); // Incrémente y à 2
        actions.add(action1);
        actions.add(action2);
        actions.add(action3);
        actions.add(action4);

        // les méthodes de BasicAction
        System.out.println("Action 1 applicable à l'état initial ? : " + action1.isApplicable(initialState));
        Map<Variable, Object> nextState1 = action1.successor(initialState);
        System.out.println("État après application de l'Action 1 : " + nextState1);

        //On initialiser les planificateurs
        Planner bfsPlanner = new BFSPlanner(initialState, actions, goal);
        Planner dfsPlanner = new DFSPlanner(initialState, actions, goal);
        Planner dijkstraPlanner = new DijkstraPlanner(initialState, actions, goal);
        Planner aStarPlanner = new AStarPlanner(initialState, actions, goal, state -> 0); // Utilisation d'une heuristique simple

        // On active le compteur de nœuds
        bfsPlanner.activateNodeCount(true);
        dfsPlanner.activateNodeCount(true);
        dijkstraPlanner.activateNodeCount(true);
        aStarPlanner.activateNodeCount(true);

        //On exécute chaque planificateur
        System.out.println("\nRésultats des plans :");
        List<Action> bfsPlan = bfsPlanner.plan();
        List<Action> dfsPlan = dfsPlanner.plan();
        List<Action> dijkstraPlan = dijkstraPlanner.plan();
        List<Action> aStarPlan = aStarPlanner.plan();

        // On affiche les résultats des plans
        System.out.println("Plan BFS : " + bfsPlan);
        System.out.println("Plan DFS : " + dfsPlan);
        System.out.println("Plan Dijkstra : " + dijkstraPlan);
        System.out.println("Plan A* : " + aStarPlan);

        // On affiche le nombre de nœuds explorés
        System.out.println("\nNombre de nœuds explorés :");
        System.out.println("BFS : " + bfsPlanner.getSonde());
        System.out.println("DFS : " + dfsPlanner.getSonde());
        System.out.println("Dijkstra : " + dijkstraPlanner.getSonde());
        System.out.println("A* : " + aStarPlanner.getSonde());
    }
}
