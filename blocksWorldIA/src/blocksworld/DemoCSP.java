package blocksworld;
import modelling.*;
import cp.*;
import vue.Vue;
import java.util.Map;
import java.util.Set;
import java.util.Random;

public class DemoCSP {
        public static void main(String[] args) {

                System.out.println("__________________Démonstration CSP______________________");

                //initialisation du monde des blocs avec 7 blocs et 3 piles
                BlocksWorld mondeDesBlocs = new BlocksWorld(7, 3);
                Set<Variable> toutesLesVariables = mondeDesBlocs.getBlVariables().getallVariables();


                // Partie 1 : Contraintes régulières
                System.out.println("__________________Contraintes régulières__________________");

                // Initialisation des contraintes régulières
                BlocksRegularConfigConstraint contraintesRegulieres = new BlocksRegularConfigConstraint(mondeDesBlocs);
                Set<Constraint> ensembleContraintesRegulieres = contraintesRegulieres.getContraintes();

                // Initialisation des solveurs pour les contraintes régulières
                Solver solveurBacktrackRegulier = new BacktrackSolver(toutesLesVariables, ensembleContraintesRegulieres);
                Solver solveurMACRegulier = new MACSolver(toutesLesVariables, ensembleContraintesRegulieres);
                VariableHeuristic heuristiqueVariableRegulier = new DomainSizeVariableHeuristic(true);
                ValueHeuristic heuristiqueValeurRegulier = new RandomValueHeuristic(new Random());
                Solver solveurHeuristiqueMACRegulier = new HeuristicMACSolver(
                        toutesLesVariables, ensembleContraintesRegulieres, heuristiqueVariableRegulier, heuristiqueValeurRegulier
                );

                // Résolution et mesure du temps pour les solveurs
                long debutBacktrackRegulier = System.currentTimeMillis();
                Map<Variable, Object> solutionBacktrackRegulier = solveurBacktrackRegulier.solve();
                long finBacktrackRegulier = System.currentTimeMillis();

                long debutMACRegulier = System.currentTimeMillis();
                Map<Variable, Object> solutionMACRegulier = solveurMACRegulier.solve();
                long finMACRegulier = System.currentTimeMillis();

                long debutHeuristiqueMACRegulier = System.currentTimeMillis();
                Map<Variable, Object> solutionHeuristiqueMACRegulier = solveurHeuristiqueMACRegulier.solve();
                long finHeuristiqueMACRegulier = System.currentTimeMillis();

                // Affichage des temps d'exécution pour les solveurs 
                System.out.println("Temps du solveur Backtrack avec contraintes régulières : " +
                        (finBacktrackRegulier - debutBacktrackRegulier) / 1000.0 + " s");
                System.out.println("Temps du solveur MAC avec contraintes régulières : " +
                        (finMACRegulier - debutMACRegulier) / 1000.0 + " s");
                System.out.println("Temps du solveur Heuristic MAC avec contraintes régulières : " +
                        (finHeuristiqueMACRegulier - debutHeuristiqueMACRegulier) / 1000.0 + " s");

                // Affichage graphique des résultats
                Vue vueBacktrackRegulier = new Vue(mondeDesBlocs.getBlVariables(), solutionBacktrackRegulier, "Contraintes Régulières pour Backtrack");
                Vue vueMACRegulier = new Vue(mondeDesBlocs.getBlVariables(), solutionMACRegulier, "Contraintes Régulières pour MAC");
                Vue vueHeuristiqueMACRegulier = new Vue(mondeDesBlocs.getBlVariables(), solutionHeuristiqueMACRegulier, "Contraintes Régulières pour Heuristic MAC");
                vueBacktrackRegulier.display(100, 100);
                vueMACRegulier.display(600, 0);
                vueHeuristiqueMACRegulier.display(1200, 0);


                // Partie 2 : Contraintes croissantes
                System.out.println("________________________Contraintes croissantes_____________________");

                // Initialisation des contraintes croissantes
                BlocksIncreasingConfigConstraint contraintesCroissantes = new BlocksIncreasingConfigConstraint(mondeDesBlocs);
                Set<Constraint> ensembleContraintesCroissantes = contraintesCroissantes.getContraintes();

                // Solveurs pour les contraintes croissantes
                Solver solveurBacktrackCroissant = new BacktrackSolver(toutesLesVariables, ensembleContraintesCroissantes);
                Solver solveurMACCroissant = new MACSolver(toutesLesVariables, ensembleContraintesCroissantes);
                Solver solveurHeuristiqueMACCroissant = new HeuristicMACSolver(
                        toutesLesVariables, ensembleContraintesCroissantes, heuristiqueVariableRegulier, heuristiqueValeurRegulier
                );

                // Résolution et mesure du temps
                long debutBacktrackCroissant = System.currentTimeMillis();
                Map<Variable, Object> solutionBacktrackCroissant = solveurBacktrackCroissant.solve();
                long finBacktrackCroissant = System.currentTimeMillis();

                long debutMACCroissant = System.currentTimeMillis();
                Map<Variable, Object> solutionMACCroissant = solveurMACCroissant.solve();
                long finMACCroissant = System.currentTimeMillis();

                long debutHeuristiqueMACCroissant = System.currentTimeMillis();
                Map<Variable, Object> solutionHeuristiqueMACCroissant = solveurHeuristiqueMACCroissant.solve();
                long finHeuristiqueMACCroissant = System.currentTimeMillis();

                // Affichage des temps d'exécution (en secondes)
                System.out.println("Temps du solveur Backtrack avec contraintes croissantes : " +
                        (finBacktrackCroissant - debutBacktrackCroissant) / 1000.0 + " s");
                System.out.println("Temps du solveur MAC avec contraintes croissantes : " +
                        (finMACCroissant - debutMACCroissant) / 1000.0 + " s");
                System.out.println("Temps du solveur Heuristic MAC avec contraintes croissantes : " +
                        (finHeuristiqueMACCroissant - debutHeuristiqueMACCroissant) / 1000.0 + " s");

                // Affichage graphique des résultats
                Vue vueBacktrackCroissant = new Vue(mondeDesBlocs.getBlVariables(), solutionBacktrackCroissant, "Contraintes Croissantes pour Backtrack");
                Vue vueMACCroissant = new Vue(mondeDesBlocs.getBlVariables(), solutionMACCroissant, "Contraintes Croissantes pour MAC");
                Vue vueHeuristiqueMACCroissant = new Vue(mondeDesBlocs.getBlVariables(), solutionHeuristiqueMACCroissant, "Contraintes Croissantes pour Heuristic MAC");
                vueBacktrackCroissant.display(100, 800);
                vueMACCroissant.display(600, 800);
                vueHeuristiqueMACCroissant.display(1200, 800);
        }
}

