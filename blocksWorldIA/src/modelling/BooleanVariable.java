package modelling;
import java.util.Set;

/*
 * cette classe represente une variable booleenne elle herite de la classe variable
 */
public class BooleanVariable extends Variable {

    // Constructeur
    public BooleanVariable(String name){
        super(name, Set.of(true,false)); //on initialise le domaine directement
    }

    @Override
    public boolean equals(Object x){
        return super.equals(x); 
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
    
}
