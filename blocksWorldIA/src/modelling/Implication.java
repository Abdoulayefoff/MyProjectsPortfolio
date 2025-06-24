package modelling;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Cette classe répresente une contrainte de la forme (v1 ∈ S1) -> (v2 ∈ S2)
*/
public class Implication implements Constraint{
  protected Variable v1;
  protected Variable v2;
  protected Set<Object> s1;
  protected Set<Object> s2;

  //Constructeur
  public Implication(Variable v1, Set<Object> s1, Variable v2, Set<Object> s2){
    this.v1 = v1;
    this.s1 = s1;
    this.v2 = v2;
    this.s2 = s2;
}

  // Methode getScope()
  @Override
  public Set<Variable> getScope(){
    Set<Variable> scope = new HashSet<>();
    scope.add(v1);
    scope.add(v2);
    return scope;
  }

  // Methode isSatisfiedBy()
  @Override
  public boolean isSatisfiedBy(Map<Variable, Object> instanciation){
    if (instanciation.get(this.v1) == null || instanciation.get(this.v2) == null){
      throw new IllegalArgumentException("L'affectation doit fournir une valeur pour toutes les variables de la portée");
  } // ici on verifie si v1 et v2 sont bien instancié, sinon on lève une exception
  return (!this.s1.contains(instanciation.get(this.v1)) || this.s2.contains(instanciation.get(this.v2))); // renvoie true si la valeur v1 n'est pas dans s1 ou v2 n'est pas dans s2 ce qui signifie que la contrainte est satisfaite

  }

  // Methode non demandé que j'ai ajouté pour gerer l'affichage correctement
  @Override
  public String toString(){
    return v1.getName() + "==" + s1 + "->" + v2.getName() + "==" + s2;
  }

}

      

