package modelling;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Represente une contrainte unitaire sur une variable: V ∈ S
 */
public class UnaryConstraint implements Constraint{

    protected Variable var;
    protected Set<Object> s;

    //Constructeur
    public UnaryConstraint(Variable var, Set<Object> s){
        this.var = var;
        this.s = s;
    }
    
    //Methode getScope()
    @Override
    public Set<Variable> getScope(){
        Set<Variable> scope = new HashSet<>();
        scope.add(var);
        return scope;
    }

    //Methode isSatisfiedBy()
    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> instanciation){
        if(instanciation.get(this.var) == null){
            throw new IllegalArgumentException("L'affectation doit fournir une valeur pour la variable");
        }
        return this.s.contains(instanciation.get(var)); // Verifie si la valeur est dans le sous-ensemble
    }

    // Methode non demandé que j'ai ajouté pour gerer l'affichage correctement
    @Override
    public String toString(){
        return var.getName() + "dans" + s;
    }
}
