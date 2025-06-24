package datamining;
import modelling.BooleanVariable;
import java.util.Set;
import java.util.HashSet; 
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Collection; 

/*
 * Cette classe implemente l'algo Apriorie pour extraire des itemsets fréquents
 */
public class Apriori extends AbstractItemsetMiner {

    // Constructeur
    public Apriori(BooleanDatabase database) {
        super(database);
    }

    /*
     * Methode frequentSingletons, elle prend en argument une fréquence minimale
     * Return un ensemble des itemsets singletons ayant une frequence >= à la frequence donnée
     */
    public Set<Itemset> frequentSingletons(float minFrequency) {
        Set<Itemset> frequentSingletons = new HashSet<>(); // un ensemble pour stocker les itemsets fréquents
    
        // on fait un parcourt de chaque item dans la BD et on verifie sa fréquence
        for (BooleanVariable item : database.getItems()) {
            Set<BooleanVariable> singleton = Set.of(item); // on crée un singleton contenant uniquement cet item
            float itemFrequency = frequency(singleton); // ensuite on calcule la fréquence du singleton
            // Si l'item est fréquent on l'ajoute à l'ensemble des fréquents
            if (itemFrequency >= minFrequency) {
                frequentSingletons.add(new Itemset(singleton, itemFrequency));
            }
        }
        return frequentSingletons; // les itemsets singleton fréquents sont retournés
    }

    //Méthode combine, elle combine 2 ensembles d'items en respectant certaines conditions
    public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> sortedSet1, SortedSet<BooleanVariable> sortedSet2) {
        // on vérifie que les ensembles ne sont pas vides et ont la même taille
        if (sortedSet1.isEmpty() || sortedSet1.size() != sortedSet2.size()) {
            return null;
        }
    
        // on extrait les sous ensembles sans inclure le dernier elt
        SortedSet<BooleanVariable> subset1 = sortedSet1.headSet(sortedSet1.last());
        SortedSet<BooleanVariable> subset2 = sortedSet2.headSet(sortedSet2.last());
    
        // on vérifie que les sous ensembles sont égaux et que les derniers éléments sont différents
        if (subset1.equals(subset2) && !sortedSet1.last().equals(sortedSet2.last())) {
            SortedSet<BooleanVariable> newCombinedSet = new TreeSet<>(COMPARATOR); // on cree un new ensemble combiné avec un comparateur
            newCombinedSet.addAll(sortedSet1);       
            newCombinedSet.add(sortedSet2.last());  
            return newCombinedSet;
        }
        return null; // on retourne null si les conditions ne sont pas remplis
    }

    /* 
     * Methode allSubsetsFrequent, elle verifie si tous les sous ensembles d'un ensemble d'items donné sont frequents
     * Return True si tous les sous ensembles sont fréquents False sinon
     */
    public static boolean allSubsetsFrequent(Set<BooleanVariable> itemset, Collection<SortedSet<BooleanVariable>> itemsetFrequent) {
        //on vérifie si tous les sous ensembles sont fréquents
        for (BooleanVariable item : itemset) {
            SortedSet<BooleanVariable> newSubset = new TreeSet<>(COMPARATOR); //on cree un nouveau sous ensemble
            newSubset.addAll(itemset); 
            newSubset.remove(item);
            if (!itemsetFrequent.contains(newSubset)) { 
                return false; // on retourne false si un sous ensemble n'est pas frequent 
            }
        }
        return true;
    }

    //Methode extract, elle utilise les methodes précedentes pour extraire les itemsets frequents
    @Override
    public Set<Itemset> extract(float minFrequency) {

        Set<Itemset> allFrequentItemsets = new HashSet<>(); //un ensemble pour stocker tous les itemsets fréquents
        allFrequentItemsets.addAll(frequentSingletons(minFrequency)); //on ajoute les singletons frequents
        ArrayList<SortedSet<BooleanVariable>> sortFrequentSet = new ArrayList<>(); //une liste pour stocker les ensembles frequents sous forme triée

        // Pour chaque itemset singleton fréquent
        for (Itemset singletonItemset : allFrequentItemsets) { 
            SortedSet<BooleanVariable> sortedSingleton = new TreeSet<>(COMPARATOR);
            sortedSingleton.addAll(singletonItemset.getItems()); 
            sortFrequentSet.add(sortedSingleton); //on ajoute le singleton trié à la liste des ensembles fréquents
        }

        //une boucle pour générer des itemsets fréquents de taille k > 1
        while (!sortFrequentSet.isEmpty()) { //tant qu'on a des ensembles fréquents de taille k à explorer

            ArrayList<SortedSet<BooleanVariable>> nextFrequentItemsets = new ArrayList<>(); // une liste pour les ensembles fréquents de taille k+1

            for (int i = 0; i < sortFrequentSet.size(); i++) { //chaque ensemble fréquent de taille k à l'index i
                for (int j = i + 1; j < sortFrequentSet.size(); j++) { //chaque ensemble fréquent suivant à l'index j
                    SortedSet<BooleanVariable> candidateItemset = combine(sortFrequentSet.get(i), sortFrequentSet.get(j)); 

                    if (candidateItemset != null && allSubsetsFrequent(candidateItemset, sortFrequentSet)) { 
                        float candidateFrequency = frequency(candidateItemset); //on calcule la fréquence du candidat
                        if (candidateFrequency >= minFrequency) { // Si la fréquence du candidat est suffisante
                            allFrequentItemsets.add(new Itemset(candidateItemset, candidateFrequency)); 
                            nextFrequentItemsets.add(candidateItemset); //le candidat est ajouté à la liste des ensembles fréquents de taille k+1
                        }
                    }
                }
            }

            //sortFrequentSet est mise à jour pour la prochaine itération avec les ensembles fréquents de taille k+1
            sortFrequentSet = nextFrequentItemsets;
        }
        return allFrequentItemsets; // tous les itemsets frequents sont alors retournés
    }


}
