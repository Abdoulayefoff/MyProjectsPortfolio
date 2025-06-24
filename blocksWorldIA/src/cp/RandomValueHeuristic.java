package cp;
import modelling.Variable;
import java.util.Random;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;


/**
 * Cette classe définit une heuristique qui mélange aléatoirement les valeurs d'un domaine
 */
public class RandomValueHeuristic implements ValueHeuristic {

    protected Random random;

    //Constructeur
    public RandomValueHeuristic(Random random) {
        this.random = random; 
    }

    // Methode ordering pour ordonner les valeurs
    @Override
    public List<Object> ordering(Variable variables, Set<Object> domains) {

        List<Object> list = new ArrayList<>(domains); // on cree une liste à partir du domaine
        Collections.shuffle(list, random); // la liste est mélangé en utilisant le generateur aleatoire
        return list;
    }
}
