package datamining;
import modelling.BooleanVariable;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * Cette classe représente une base de données transactionnelle contenant des items booléens
 */
public class BooleanDatabase {
    
    protected Set<BooleanVariable> items;
    protected List<Set<BooleanVariable>> transactions;

    //Constructeur
    public BooleanDatabase(Set<BooleanVariable> items) {
        this.items = new HashSet<>(items);
        this.transactions = new ArrayList<Set<BooleanVariable>>();
    }

    // Méthode add pour ajouter une transaction à la BD
    public void add(Set<BooleanVariable> transaction) {
        transactions.add(new HashSet<>(transaction));
    }

    public Set<BooleanVariable> getItems() {
        return items;
    }

    
    public List<Set<BooleanVariable>> getTransactions() {
        return transactions;
    }
}
