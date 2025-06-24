package modelling;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Création de variables normales
        Variable v1 = new Variable("v1", new HashSet<>(Arrays.asList(1, 2, 3)));
        Variable v2 = new Variable("v2", new HashSet<>(Arrays.asList(1, 2, 3)));
        Variable v3 = new Variable("v3", new HashSet<>(Arrays.asList("rouge", "vert", "bleu")));

        // Création de variables booléennes
        BooleanVariable b1 = new BooleanVariable("b1");
        BooleanVariable b2 = new BooleanVariable("b2");
        BooleanVariable b3 = new BooleanVariable("b3");
        
        // Instanciation des variables normales
        Map<Variable, Object> instanciation1 = new HashMap<>();
        instanciation1.put(v1, 2);
        instanciation1.put(v2, 3);
        instanciation1.put(v3, "rouge");

        Map<Variable, Object> instanciation2 = new HashMap<>();
        instanciation2.put(v1, 1);
        instanciation2.put(v2, 1);
        instanciation2.put(v3, "vert");

        // Instanciation des variables booléennes
        Map<Variable, Object> boolInstanciation1 = new HashMap<>();
        boolInstanciation1.put(b1, true);
        boolInstanciation1.put(b2, false);
        boolInstanciation1.put(b3, true);

        Map<Variable, Object> boolInstanciation2 = new HashMap<>();
        boolInstanciation2.put(b1, false);
        boolInstanciation2.put(b2, true);
        boolInstanciation2.put(b3, false);

        // Création de contraintes
        Constraint diffConstraint = new DifferenceConstraint(v1, v2);
        Set<Object> S1 = new HashSet<>(Arrays.asList(2, 3));
        Set<Object> S2 = new HashSet<>(Arrays.asList("rouge", "bleu"));
        Constraint implicationConstraint = new Implication(v1, S1, v3, S2);
        Set<Object> allowedValues = new HashSet<>(Arrays.asList(1, 2));
        Constraint unaryConstraint = new UnaryConstraint(v1, allowedValues);

        // Création de contraintes booléennes
        Constraint booleanImplication = new Implication(b1, new HashSet<>(Arrays.asList(true)), b2, new HashSet<>(Arrays.asList(false)));
        Constraint booleanUnary = new UnaryConstraint(b3, new HashSet<>(Arrays.asList(true)));

        // Vérification des contraintes pour les variables normales
        System.out.println("Instanciation Normale 1 : " + instanciation1);
        System.out.println("Différence (v1 ≠ v2) : " + diffConstraint.isSatisfiedBy(instanciation1)); // on doit avoir vrai
        System.out.println("Implication (v1 ∈ S1) → (v3 ∈ S2) : " + implicationConstraint.isSatisfiedBy(instanciation1)); // on doit avoir vrai
        System.out.println("Unitaire (v1 ∈ {1, 2}) : " + unaryConstraint.isSatisfiedBy(instanciation1)); // on doit avoir vrai

        System.out.println("\nInstanciation Normale 2 : " + instanciation2);
        System.out.println("Différence (v1 ≠ v2) : " + diffConstraint.isSatisfiedBy(instanciation2)); // on doit avoir faux
        System.out.println("Implication (v1 ∈ S1) → (v3 ∈ S2) : " + implicationConstraint.isSatisfiedBy(instanciation2)); // on doit avoir vrai
        System.out.println("Unitaire (v1 ∈ {1, 2}) : " + unaryConstraint.isSatisfiedBy(instanciation2)); // on doit avoir vrai

        // Vérification des contraintes pour les variables booléennes
        System.out.println("\nInstanciation Booléenne 1 : " + boolInstanciation1);
        System.out.println("Implication (b1 → b2) : " + booleanImplication.isSatisfiedBy(boolInstanciation1)); //on doit avoirfaux
        System.out.println("Unitaire (b3 = true) : " + booleanUnary.isSatisfiedBy(boolInstanciation1)); // on doit avoir vrai

        System.out.println("\nInstanciation Booléenne 2 : " + boolInstanciation2);
        System.out.println("Implication (b1 → b2) : " + booleanImplication.isSatisfiedBy(boolInstanciation2)); // on doit avoir vrai
        System.out.println("Unitaire (b3 = true) : " + booleanUnary.isSatisfiedBy(boolInstanciation2)); // on doit avoir faux
    }
}
