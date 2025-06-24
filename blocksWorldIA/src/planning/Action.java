package planning;
import modelling.Variable;
import java.util.Map;


/*Cette interface represente une action */
public interface Action {

    /*
     * Cette methode verifie si une action donnée peut etre executé dans un état
     * @param state : represente l'etat actuel du systeme
     * @return true si l'action est applicable a cet etat, false sinon
     */
    boolean isApplicable(Map<Variable, Object> state);

    /*
     * Cette methode genère un nouvel état resultant de l'application de l'action à un état actuel donné
     * @param state : répresente l'état actuel du systeme
     * @return l'etat resultant apres application de l'action
     */
     Map<Variable, Object> successor(Map<Variable, Object> state);

     /*
      * Cette methode renvoi le cout associé à l'application d'une action
      */
      int getCost();
}
