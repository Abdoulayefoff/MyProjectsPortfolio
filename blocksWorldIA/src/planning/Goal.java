package planning;
import modelling.Variable;
import java.util.Map;


public interface Goal {
    
    /*
     * la methode verifie si l'etat donné satisfait le but
     * @param state : l'etat a verifier
     * @return true si l'etat satisfait le but, false sinon
     */
    boolean isSatisfiedBy(Map<Variable, Object> state);
}
