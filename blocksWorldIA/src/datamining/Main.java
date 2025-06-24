package datamining;
import modelling.BooleanVariable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {

        float minFrequency = 0.3f;
        float minConfidence = 0.2f; 
        // Pour gerer la saisie des arguments directement dans le terminal 
        if(args.length >=2){
            try{
                minFrequency = Float.parseFloat(args[0]);
                minConfidence = Float.parseFloat(args[1]);
            }catch(NumberFormatException e){
                System.out.println("Les arguments fournis ne sont pas valides. Les valeurs par défaut sont utilisées");
            }
        }
        System.out.println("La fréquence minimale saisie : " +minFrequency);
        System.out.println("La Confiance minimale saisie : " + minConfidence);
        //on cree un ensemble d'items
        Set<BooleanVariable> items = new HashSet<>();
        items.add(new BooleanVariable("Pain"));
        items.add(new BooleanVariable("Beurre"));
        items.add(new BooleanVariable("Confiture"));
        items.add(new BooleanVariable("Lait"));
        items.add(new BooleanVariable("Café"));
        items.add(new BooleanVariable("Thé"));
        items.add(new BooleanVariable("Chocolat"));

        //on cree la BD
        BooleanDatabase database = new BooleanDatabase(items);
        
        //on cree des transactions
        Set<BooleanVariable> transaction1 = new HashSet<>(Arrays.asList(new BooleanVariable("Lait"), new BooleanVariable("Beurre")));
        Set<BooleanVariable> transaction2 = new HashSet<>(Arrays.asList(new BooleanVariable("Pain"), new BooleanVariable("Beurre"), new BooleanVariable("Confiture")));
        Set<BooleanVariable> transaction3 = new HashSet<>(Arrays.asList(new BooleanVariable("Café"), new BooleanVariable("Pain"), new BooleanVariable("Lait")));
        Set<BooleanVariable> transaction4 = new HashSet<>(Arrays.asList(new BooleanVariable("Pain"), new BooleanVariable("Chocolat"), new BooleanVariable("Confiture")));
        Set<BooleanVariable> transaction5 = new HashSet<>(Arrays.asList(new BooleanVariable("Lait"), new BooleanVariable("Confiture")));
        Set<BooleanVariable> transaction6 = new HashSet<>(Arrays.asList(new BooleanVariable("Café"), new BooleanVariable("Lait")));

        //on ajoute les transactions dans la base
        database.add(transaction1);
        database.add(transaction2);
        database.add(transaction3);
        database.add(transaction4);
        database.add(transaction5);
        database.add(transaction6);
        
        //Affichage des transactions avec les items
        System.out.println("_____Les transactions qui sont dans la base de données_____ :");
        for (Set<BooleanVariable> transaction : database.getTransactions()) {
            String itemsInTransaction = ""; //une chaine pour stocker les items
            for (BooleanVariable item : transaction) {
                itemsInTransaction += item.getName() + ", ";
            }
            System.out.println("Transaction : " + itemsInTransaction);
        }

        //Algo Apriori pour extraire des itemsets fréquents
        Apriori aprioriMiner = new Apriori(database);
        //
        Set<Itemset> frequentItemsets = aprioriMiner.extract(minFrequency);

        //affichage des itemsets fréquents 
        System.out.println("\n _____Voici les itemsets fréquents (la fréquence minimale est = " + minFrequency + ") :_____");
        for (Itemset itemset : frequentItemsets) {
            String itemNames = ""; //une chaine pour stocker le nom des items
            for (BooleanVariable item : itemset.getItems()) {
                itemNames += item.getName() + ", ";
            }
            itemNames += "Fréquence : " + itemset.getFrequency();
            System.out.println("Items : " + itemNames);
        }

        //algo brute force pour extraire des regles d'associations
        BruteForceAssociationRuleMiner ruleMiner = new BruteForceAssociationRuleMiner(database);
        //float minConfidence = 0.2f; 
        Set<AssociationRule> associationRules = ruleMiner.extract(minFrequency, minConfidence);

        //Affichage des règles d'association extraites
        System.out.println("\n _____Les règles d'association obtenues (min confiance = " + minConfidence + ") :______");
        if(associationRules.isEmpty()){
            System.out.println("Malheureusement aucune regle d'association n'a été trouvée avec les valeurs données");
        }else{
        for (AssociationRule rule : associationRules) {
            String premiseNames = "";
            for (BooleanVariable item : rule.getPremise()) {
                premiseNames += item.getName() + ", "; 
            }
            String conclusionNames = ""; 
            for (BooleanVariable item : rule.getConclusion()) {
                conclusionNames += item.getName() + ", ";
            }
            //on affiche la règle d'association avec sa fréquence et sa confiance
            System.out.println("Prémisse : " + premiseNames + "Conclusion : " + conclusionNames + ", Fréquence : " + rule.getFrequency() +", Confiance : " + rule.getConfidence());
            }
        }
    }
}
