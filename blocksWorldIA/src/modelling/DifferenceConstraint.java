package modelling;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Cette classe represente une contrainte qui stipule que deux variables doivent avoir des valeurs differentes
 */
public class DifferenceConstraint implements Constraint {

    protected Variable v1; 
    protected Variable v2;

    //Constructeur
    public DifferenceConstraint(Variable v1, Variable v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    // Methode getScope(), elle retourne les variables qui portent la contrainte
    @Override
    public Set<Variable> getScope(){
        Set<Variable> scope = new HashSet<>();
        scope.add(v1);
        scope.add(v2);
        return scope;
    }
    
    // Methode isSatisfiedBy(), elle determine si la contrainte est satisfaite par l'instanciation donnée
    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> instanciation){
        if (instanciation.get(this.v1) == null || instanciation.get(this.v2) == null){
            throw new IllegalArgumentException("L'affectation doit fournir une valeur pour toutes les variables de la portée");
        } // ici on verifie si v1 et v2 sont bien instancié, sinon on lève une exception

        return !(instanciation.get(this.v1).equals(instanciation.get(this.v2))); // ici on recupere les valeurs assignées a v1 et v2 et verifie si elles sont differentes. On renvoi true si elles sont differentes car elles satisfaitent la contrainte
}
}
