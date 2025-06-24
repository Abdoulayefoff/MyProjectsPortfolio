package planning;
import modelling.Variable;
import java.util.HashMap;
import java.util.Map;

/* 
 * Cette classe répresente une action basique avec une precondition et un effet
*/
public class BasicAction implements Action{

    protected Map<Variable, Object> precondition; 
    protected Map<Variable, Object> effect; 
    protected int cost;

    //Constructeur
    public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effect, int cost){
        this.precondition = precondition; 
        this.effect = effect;
        this.cost = cost;
    }

    //Methose isApplicable() pour savoir si une action est applicable ou pas
    @Override
    public boolean isApplicable(Map<Variable, Object> state){
        for(Variable v : precondition.keySet()){
            // On parcout les variables de la precondition
            // Si la variable n'est pas presente ou la valeur ne correspond pas, on retourne false
            if(!state.containsKey(v) || !state.get(v).equals(precondition.get(v))){
                return false; // l'action n'est pas applicable
            }
        }
        return true; // Action applicable
    }

    //Methode successor()
    @Override
    public Map<Variable, Object> successor(Map<Variable, Object> state){
        // On cree d'abord une copie de l'etat actuel
        Map<Variable, Object> cpState = new HashMap<>(state);
        for(Variable v : effect.keySet()){ 
            cpState.put(v,effect.get(v));
        } // On parcourt les variables de l'effet et on met a jour l'etat avec l'effet
        return cpState; // le nouvel état est retourné
    }

    //Methode getCost()
    @Override
    public int getCost(){
        return cost;
    }
    //Methode rajouté pour gerer l'affichage
    @Override
    public String toString() {
        return "BasicAction{" +
           "precondition=" + precondition +
           ", effect=" + effect +
           ", cost=" + cost +
           '}';
    }


    
}
