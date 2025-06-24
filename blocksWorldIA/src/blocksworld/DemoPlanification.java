package blocksworld;
import modelling.Variable;
import planning.*;
import vue.Vue; 
import vue.DisplayPlanActions;
import java.util.Set;
import java.util.Map; 
import java.util.List; 


public class DemoPlanification {
    public static void main(String[] args) {

        //initialisation des actions du monde de blocs avec 4 blocs et 3 piles
        BlocksAction bwA = new BlocksAction(4, 3);
        BlocksVariable blVariables = bwA.getBw().getBlVariables(); // on recupère les variables du monde de blocs
        Set<Action> actions = bwA.getActions(); //on recupère lesactions disponibles

        // État initial 
        int[][] étatInitial = {{ 3, 1 },{ 2 },{ 0 }};
        Map<Variable, Object> état = blVariables.getVariablesState(étatInitial); // Conversion en état de variables
        

        // État final
        int[][] étatFinal = {{ 2, 1, 0 },{ 3 },{}};
        Map<Variable, Object> goalState = blVariables.getVariablesState(étatFinal); 


        //instanciation des différents planificateurs
        Goal goal = new BasicGoal(goalState);
        DFSPlanner dfs = new DFSPlanner(état, actions, goal);
        BFSPlanner bfs = new BFSPlanner(état, actions, goal);
        DijkstraPlanner djikstra = new DijkstraPlanner(état, actions, goal);

        //creation des heuristiques pour l'algorithme A*
        BlocksOnWrongPositionHeuristic blocksWrongP = new BlocksOnWrongPositionHeuristic(goalState);
        TotalDistanceHeuristicForBlocks totalDist = new TotalDistanceHeuristicForBlocks(goalState);
        AStarPlanner astarForBlocksWrongP = new AStarPlanner(état, actions, goal, blocksWrongP); // A* avec la première heuristique
        AStarPlanner astarForTotalDist = new AStarPlanner(état, actions, goal, totalDist); // A* avec la deuxième heuristique

        //debut des chronomètres et lancement des planificateurs
        long t1 = System.nanoTime(); // Chronomètre pour DFS
        List<Action> dfsplanner = dfs.plan();//exécution de la planification DFS
        long t2 = System.nanoTime(); 
        List<Action> bfsplanner = bfs.plan(); 
        long t3 = System.nanoTime(); 
        List<Action> djkplanner = djikstra.plan(); 
        long t4 = System.nanoTime(); 
        List<Action> astarplanner1 = astarForBlocksWrongP.plan(); 
        long t5 = System.nanoTime(); 
        List<Action> astarplanner2 = astarForTotalDist.plan(); 
        long t6 = System.nanoTime(); //fin du chronométrage

        //afficha des résultats pour chaque planificateur
        System.out.println("_____________Résultats des Planificateurs_______________");
        System.out.printf("Pour DFS : Nœuds visités = %d, Longueur du plan = %d, Temps de calcul = %.9f secondes%n",
                          dfs.getSonde(), dfsplanner.size(), (t2 - t1) / 1_000_000_000.0);
        System.out.println();
        System.out.printf("Pour BFS : Nœuds visités = %d, Longueur du plan = %d, Temps de calcul = %.9f secondes%n",
                          bfs.getSonde(), bfsplanner.size(), (t3 - t2) / 1_000_000_000.0);
        System.out.println();
        System.out.printf("Pour Dijkstra : Nœuds visités = %d, Longueur du plan = %d, Temps de calcul = %.9f secondes%n",
        djikstra.getSonde(), djkplanner.size(), (t4 - t3) / 1_000_000_000.0);
        System.out.println();
        System.out.println("_______________Avec Astar______________________");
        System.out.println("Astar avec le nombre de blocs mal placés :");
        System.out.printf("Nœuds visités = %d, Longueur du plan = %d, Temps de calcul = %.9f secondes%n",
        astarForBlocksWrongP.getSonde(), astarplanner1.size(), (t5 - t4) / 1_000_000_000.0);
        System.out.println();
        System.out.println("_________________________________________");
        System.out.println("Astar avec l'heuristique distance total entre les blocs :");
        System.out.printf("Nœuds visités = %d, Longueur du plan = %d, Temps de calcul = %.9f secondes%n",
        astarForTotalDist.getSonde(), astarplanner2.size(), (t6 - t5) / 1_000_000_000.0);
        System.out.println();

        // Affichage des plans
        Vue viewBfs = new Vue(blVariables, état, "BFS");
        Vue viewDfs = new Vue(blVariables, état, "DFS");
        Vue viewDijkstra = new Vue(blVariables, état, "Dijkstra");
        Vue viewAstar = new Vue(blVariables, état, "A* - Heuristique des blocs mal placés");
        Vue viewAstar2 = new Vue(blVariables, état, "A* - Heuristique distance total entre les blocs");

        // Lancement de l'affichage des actions pour chaque planificateur
        new DisplayPlanActions(viewBfs, bfsplanner, 800, 800).start(); 
        new DisplayPlanActions(viewDfs, dfsplanner, 900, 900).start(); 
        new DisplayPlanActions(viewDijkstra, djkplanner, 1000, 1000).start(); 
        new DisplayPlanActions(viewAstar, astarplanner1, 1100, 1100).start(); 
        new DisplayPlanActions(viewAstar2, astarplanner2, 1600, 1600).start(); 
    }
}