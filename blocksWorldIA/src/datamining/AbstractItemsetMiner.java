package datamining; 
import modelling.BooleanVariable;
import java.util.Set; 
import java.util.Comparator; 


// Cette classe implemente l'interface ItemsetMiner
public abstract class AbstractItemsetMiner implements ItemsetMiner { 
    protected BooleanDatabase database; 

    // Attribut statique pour comparer les items
    public static final Comparator<BooleanVariable> COMPARATOR = (var1, var2) -> var1.getName().compareTo(var2.getName()); 

    // Constructeur
    public AbstractItemsetMiner(BooleanDatabase database) {
        this.database = database;
    }

    @Override
    public BooleanDatabase getDatabase() {
        return this.database; 
    }

    // Méthode pour calculer la fréquence d'un ensemble d'items dans la base
    public float frequency(Set<BooleanVariable> items) {
        float count = 0;
        for (Set<BooleanVariable> transaction : database.getTransactions()) { 
            if (transaction.containsAll(items)) { // on vérifie si la transaction contient tous les items
                count++;
            }
        }
        // on retourne la fréquence qui est le (nombre de transactions contenant les items) / (nombre total de transactions)
        return count / database.getTransactions().size(); 
    }
}
