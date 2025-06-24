package datamining;
import modelling.BooleanVariable;
import java.util.Set;
import java.util.HashSet;


// Cette classe implemente un algo de recherche brute force pour extraire des règles d'association
public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner {

    //Constructeur
    public BruteForceAssociationRuleMiner(BooleanDatabase database) {
        super(database); 
    }

    //Methode extract pour extraire des regles d'association
    @Override
    public Set<AssociationRule> extract(float minFrequency, float minConfidence) {

        Set<AssociationRule> associationRules = new HashSet<>(); //un ensemble pour stocker les regles d'association extraite
        Set<Itemset> frequentItemsets = (new Apriori(database)).extract(minFrequency);// Extraction des itemsets fréquents

        //on parcours chaque itemset fréquent
        for (Itemset itemset : frequentItemsets) {
            Set<Set<BooleanVariable>> candidatePremises = allCandidatePremises(itemset.getItems()); //Extraction des premises
            // on parcours chaque prémisse candidate
            for (Set<BooleanVariable> premise : candidatePremises) {
                Set<BooleanVariable> conclusion = new HashSet<>(itemset.getItems());
                conclusion.removeAll(premise); //on retire tous les éléments de la prémisse de l'itemset

                float confidenceValue = confidence(premise, conclusion, frequentItemsets);//calcul de la confiance
                if (confidenceValue >= minConfidence) {
                    //une nouvelle règle d'association est crée avec la prémisse la conclusion la fréquence et la confiance
                    associationRules.add(new AssociationRule(premise, conclusion,
                            frequency(itemset.getItems(), frequentItemsets), confidenceValue));
                }
            }
        }
        return associationRules; //on retourne l'ensemble des regles d'association 
    }

    //Méthode allCandidatePremises pour génerer tous les sous ensemples possibles d'un ensemble d'items, l'exception de l'ensemble vide et de l'ensemble lui meme
    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items) {
        
        Set<Set<BooleanVariable>> subsets = new HashSet<>(); //pour stocker les sous-ensembles
        int totalSubsets = 1 << items.size(); // nombre total de sous ensembles

        // une boucle pour générer tous les sous-ensembles
        for (int i = 1; i < totalSubsets - 1; i++) {
            Set<BooleanVariable> currentSubset = new HashSet<>();
            int j = 0; // Index pour parcourir les items

            // Boucle pour déterminer quels items inclure dans le sous-ensemble
            for (BooleanVariable item : items) {
                if ((i & (1 << j)) > 0) { // Vérifie si l'index j est inclus dans le sous-ensemble
                currentSubset.add(item);
                }
                j++;
            }
            subsets.add(currentSubset);
        }
        return subsets; //on retourne tous les sous-ensembles sans l'ensemble vide et l'ensemble lui-même
    }
}
