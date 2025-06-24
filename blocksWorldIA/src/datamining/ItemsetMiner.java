package datamining;
import java.util.Set;

/**
 * Cette interface déclare les méthodes pour l'extraction d'itemsets fréquents dans une BD
 */
public interface ItemsetMiner {
    
    public BooleanDatabase getDatabase();

    public Set<Itemset> extract(float minFrequency);
}
