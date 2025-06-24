package datamining;
import modelling.BooleanVariable;
import java.util.Set;
import java.util.HashSet;


//Classe abstraite représentant un extracteur de règles d'association
public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {

    protected BooleanDatabase database;

    //Constructeur
    public AbstractAssociationRuleMiner(BooleanDatabase database) {
        this.database = database;
    }


    @Override
    public BooleanDatabase getDatabase() {
        return database;
    }

    // Méthode statique frequency pour obtenir la fréquence d'un ensemble d'items
    public static float frequency(Set<BooleanVariable> items, Set<Itemset> itemsets) {
        for (Itemset itemset : itemsets) {
            //si les items de l'itemset correspondent à ceux recherchés
            if (itemset.getItems().equals(items)) {
                return itemset.getFrequency(); //alors on retourne la fréquence de l'itemset
            }
        }
        throw new IllegalArgumentException("L'ensemble d'items n'est pas présent dans les itemsets.");
    }

    // Méthode statique confidence pour calculer la confiance d'une règle d'association
    public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, Set<Itemset> itemsets) {
        float freqPremise = frequency(premise, itemsets);
        // Calcul de la fréquence de l'ensemble qui combine la prémisse et la conclusion
        Set<BooleanVariable> combine = new HashSet<>(premise);
        combine.addAll(conclusion);
        float freqCombine = frequency(combine, itemsets);
        
        if (freqPremise == 0) {
            throw new IllegalArgumentException("la fréquence de la prémisse ne peut pas être nulle");
        }
        return freqCombine / freqPremise;
    }
}
