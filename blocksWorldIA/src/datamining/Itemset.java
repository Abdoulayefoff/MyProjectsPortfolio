package datamining;
import modelling.BooleanVariable; 
import java.util.Set;

/**
 * Cette classe représente un ensemble d'items avec une fréquence associée dans une base de données.
 */
public class Itemset {
    
    protected Set<BooleanVariable> items;
    protected float frequency;

    //Constructeur
    public Itemset(Set<BooleanVariable> items, float frequency) {
        if (frequency < 0.0 || frequency > 1.0) {
            throw new IllegalArgumentException("La fréquence doit être comprise entre 0,0 et 1,0.");
        }
        this.items = items;
        this.frequency = frequency;
    }

    public Set<BooleanVariable> getItems() {
        return items;
    }

    public float getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "Itemset { " + "items = " + items + ", frequence = " + frequency + " }";
    }

}
