package blocksworld;
import modelling.BooleanVariable;
import datamining.*;
import java.util.Set;


public class DemoExtraction {

    public static void main(String[] args) {
        // Initialisation du monde des blocs avec 8 blocs et 3 piles
        BlocksExtraction bwD = new BlocksExtraction(8, 3);

        // Génération de la base de données booléenne avec 10 000 transactions
        BooleanDatabase database = bwD.generateDatabase(10000);

        float minFrequency = 2f / 3;
        float minConfidence = 95f / 100;

        // Affichage des transactions
        System.out.println("_____Les transactions qui sont dans la base de données_____ :");
        for (Set<BooleanVariable> transaction : database.getTransactions()) {
            StringBuilder itemsInTransaction = new StringBuilder();
            for (BooleanVariable item : transaction) {
                itemsInTransaction.append(item.getName()).append(", ");
            }
            System.out.println("Transaction : " + itemsInTransaction);
        }

        // Algo Apriori pour extraire des itemsets fréquents
        System.out.println("\n******************** Extraction des itemsets fréquents ********************");
        Apriori aprioriMiner = new Apriori(database);
        Set<Itemset> frequentItemsets = aprioriMiner.extract(minFrequency);

        // Affichage des itemsets fréquents
        System.out.println("\n_____Voici les itemsets fréquents (fréquence minimale = " + minFrequency + ") :_____");
        for (Itemset itemset : frequentItemsets) {
            StringBuilder itemNames = new StringBuilder();
            for (BooleanVariable item : itemset.getItems()) {
                itemNames.append(item.getName()).append(", ");
            }
            itemNames.append("Fréquence : ").append(itemset.getFrequency());
            System.out.println("Items : " + itemNames);
        }

        // Algo BruteForce pour extraire des règles d'association
        System.out.println("\n******************** Extraction des règles d'association ********************");
        BruteForceAssociationRuleMiner ruleMiner = new BruteForceAssociationRuleMiner(database);
        Set<AssociationRule> associationRules = ruleMiner.extract(minFrequency, minConfidence);

        // Affichage des règles d'association extraites
        System.out.println("\n_____Les règles d'association obtenues (confiance minimale = " + minConfidence + ") :_____");
        if (associationRules.isEmpty()) {
            System.out.println("Aucune règle d'association trouvée avec les valeurs données.");
        } else {
            for (AssociationRule rule : associationRules) {
                StringBuilder premiseNames = new StringBuilder();
                for (BooleanVariable item : rule.getPremise()) {
                    premiseNames.append(item.getName()).append(", ");
                }
                StringBuilder conclusionNames = new StringBuilder();
                for (BooleanVariable item : rule.getConclusion()) {
                    conclusionNames.append(item.getName()).append(", ");
                }
                // Affichage de la règle d'association avec fréquence et confiance
                System.out.println("Prémisse : " + premiseNames 
                        + "Conclusion : " + conclusionNames 
                        + ", Fréquence : " + rule.getFrequency() 
                        + ", Confiance : " + rule.getConfidence());
            }
        }
    }
}
