package planning;
import modelling.Variable;
import java.util.Map;

/*
 * Cette classe implement l'interface Goal
 */
public class BasicGoal implements Goal{

    public Map<Variable, Object> goal; //ici le goal represente une instanciation partielle des variables

    //Constructeur
    public BasicGoal(Map<Variable, Object> goal){
        this.goal = goal;
    }
    //Methode isSatisfiedBy() elle verifie si l'etat satisfait le goal
    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> state){
        // on parcourt chaque variable dans le goal
        for(Variable v : goal.keySet()){
            // on verifie si l'etat ne contient pas la variable ou si la valeur ne correpond pas
            if(!state.containsKey(v) || !state.get(v).equals(goal.get(v))){
                return false;
            }
        }
        return true;
     }
}
