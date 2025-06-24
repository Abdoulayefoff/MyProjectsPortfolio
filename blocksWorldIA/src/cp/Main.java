package cp;
import modelling.Variable;
import modelling.Constraint;
import modelling.DifferenceConstraint;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Creation des variables
        Variable varX = new Variable("X", new HashSet<>(Arrays.asList(1, 1, 1)));
        Variable varY = new Variable("Y", new HashSet<>(Arrays.asList(3, 2, 1)));
        Variable varZ = new Variable("Z", new HashSet<>(Arrays.asList(2, 9, 7)));

        Set<Variable> variables = new HashSet<>(Arrays.asList(varX, varY, varZ));

        //on définit des contraintes de type differenceConstraint
        Set<Constraint> constraints = new HashSet<>();
        constraints.add(new DifferenceConstraint(varX, varY)); 
        constraints.add(new DifferenceConstraint(varY, varZ)); 
        constraints.add(new DifferenceConstraint(varX, varZ)); 

        System.out.println("---------------------Test des solveurs------------------------\n");
        //on teste les solveurs sans heuristiques
        System.out.println("Test de BacktrackSolver:");
        BacktrackSolver backtrackSolver = new BacktrackSolver(variables, constraints);
        Map<Variable, Object> backtrackSolution = backtrackSolver.solve();
        System.out.println(" Voici la solution de BacktrackSolver: " + backtrackSolution);

        System.out.println("\nTest de MACSolver:");
        MACSolver macSolver = new MACSolver(variables, constraints);
        Map<Variable, Object> macSolution = macSolver.solve();
        System.out.println(" Voici la solution de MACSolver: " + macSolution);

        System.out.println("--------------------------Test avec des heuristiques--------------------\n");
        // on crée des heuristiques
        VariableHeuristic nbConstraintsHeuristic = new NbConstraintsVariableHeuristic(constraints, true);
        ValueHeuristic randomValueHeuristic = new RandomValueHeuristic(new Random());
        VariableHeuristic domainSizeHeuristic = new DomainSizeVariableHeuristic(false);

        // Test avec NbConstraintsVariableHeuristic 
        System.out.println("\nTest de HeuristicMACSolver avec NbConstraintsVariableHeuristic:");
        HeuristicMACSolver heuristicMacSolver =new HeuristicMACSolver(variables, constraints,nbConstraintsHeuristic,randomValueHeuristic);
        Map<Variable, Object> heuristicMacSolution = heuristicMacSolver.solve();
        System.out.println("Voici la solution de HeuristicMACSolver (NbConstraintsVariableHeuristic): " + heuristicMacSolution);

        // Test avec DomainSizeVariableHeuristic
        System.out.println("\nTest de HeuristicMACSolver avec DomainSizeVariableHeuristic:");
        HeuristicMACSolver heuristicMacSolverDomainSize = new HeuristicMACSolver(variables, constraints,domainSizeHeuristic,randomValueHeuristic);
        Map<Variable, Object> heuristicMacSolutionDomainSize = heuristicMacSolverDomainSize.solve();
        System.out.println("Voici la solution de HeuristicMACSolver (Domain Size Heuristic) : " + heuristicMacSolutionDomainSize);

    }
}
    

