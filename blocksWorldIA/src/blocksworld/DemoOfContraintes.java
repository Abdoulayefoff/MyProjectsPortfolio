package blocksworld;
import modelling.Variable;
import modelling.Constraint;
import java.util.Map;

public class DemoOfContraintes {
    public static void main(String[] args) {
        //on initialise le monde des blocs avec 10 blocs et 3 piles
        BlocksWorld blocksworld = new BlocksWorld(10, 3);

        //creation des contraintes pour les configurations croissantes et régulières
        BlocksIncreasingConfigConstraint increasingConstraint = new BlocksIncreasingConfigConstraint(blocksworld);
        BlocksRegularConfigConstraint regularConstraint = new BlocksRegularConfigConstraint(blocksworld);

        //recuperation des variables des blocs
        BlocksVariable blVariables = blocksworld.getBlVariables();

        System.out.println("_______________Démonstration des contraintes______________");

        //declaration des états
        Map<Variable, Object> etat1, etat2, etat3, etat4, etat5;

        //quelques configuration pour tester les différents cas de contraintes
        int[][] config1 = {{0, 1, 2}, {3, 4}, {5, 6, 7, 8, 9}};  // Respecte uniquement les contraintes de base
        int[][] config2 = {{0, 3}, {1, 2, 4, 5}, {6, 7, 8, 9}};  // Respecte les contraintes régulières
        int[][] config3 = {{0}, {1, 3, 5, 7}, {2, 4, 6, 8, 9}};  // Respecte les contraintes croissantes
        int[][] config4 = {{0, 1, 2}, {3, 4}, {5, 6, 7, 8, 9}};  // Ne respecte pas les contraintes régulières
        int[][] config5 = {{5, 2, 3}, {0, 4, 6}, {1, 7, 8, 9}};  // Ne respecte pas les contraintes croissantes

        // Conversion des configurations en états pour les variables
        etat1 = blVariables.getVariablesState(config1);
        etat2 = blVariables.getVariablesState(config2);
        etat3 = blVariables.getVariablesState(config3);
        etat4 = blVariables.getVariablesState(config4);
        etat5 = blVariables.getVariablesState(config5);

        // Test des contraintes pour chaque état
        testConstraints(etat1, config1, "État 1 (toutes les contraintes sont respectées)", blocksworld, regularConstraint, increasingConstraint);
        testConstraints(etat2, config2, "État 2 (ne respecte pas les contraintes régulières)", blocksworld, regularConstraint, increasingConstraint);
        testConstraints(etat3, config3, "État 3 (respecte contraintes croissantes)", blocksworld, regularConstraint, increasingConstraint);
        testConstraints(etat4, config4, "État 4 (respecte les contraintes regulières)", blocksworld, regularConstraint, increasingConstraint);
        testConstraints(etat5, config5, "État 5 (ne respecte ni les contraintes croissantes ni regulieres)", blocksworld, regularConstraint, increasingConstraint);
    }

    //methode pour tester les contraintes pour un état donné
    public static void testConstraints(Map<Variable, Object> state, int[][] config, String stateName, 
                                       BlocksWorld blocksworld, BlocksRegularConfigConstraint regularConstraint, 
                                       BlocksIncreasingConfigConstraint increasingConstraint) {
        // initialisation de la variable de vérification
        boolean allSatisfied;
        System.out.println("\n" + stateName + " :");
        printConfiguration(config);

        // Vérification des contraintes de base
        allSatisfied = true;
        for (Constraint constraint : blocksworld.getContraintes()) {
            if (!constraint.isSatisfiedBy(state)) {
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "Les contraintes de base sont satisfaites." : 
                                          "Les contraintes de base ne sont pas satisfaites.");

        // Vérification des contraintes de régularité
        allSatisfied = true;
        for (Constraint constraint : regularConstraint.getContraintes()) {
            if (!constraint.isSatisfiedBy(state)) {
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "Les contraintes de régularité sont satisfaites." : 
                                          "Les contraintes de régularité ne sont pas satisfaites.");

        // Vérification des contraintes de croissance
        allSatisfied = true;
        for (Constraint constraint : increasingConstraint.getContraintes()) {
            if (!constraint.isSatisfiedBy(state)) {
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "Les contraintes de croissance sont satisfaites." : 
                                          "Les contraintes de croissance ne sont pas satisfaites.");
    }

    //methode pour afficher les configs
    public static void printConfiguration(int[][] config) {
        for (int i = 0; i < config.length; i++) {
            System.out.print("Pile " + (i + 1) + " : ");
            for (int block : config[i]) {
                System.out.print(block + " ");
            }
            System.out.println();
        }
    }
}
