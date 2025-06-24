package strategieRepartition;

import java.util.Collections;
import java.util.List;

/**
 * Stratégie pour répartir les données en clusters et mélanger à l'intérieur de chaque cluster.
 */
public class ClusteredDistributionStrategy implements DistributionStrategy {
    @Override
    public void apply(List<Integer> data) {
        int clusterSize = 3; // Exemple : taille des clusters
        // Parcourir la liste par étapes égales à la taille du cluster
        for (int i = 0; i < data.size(); i += clusterSize) {
            // Calculer la fin du cluster, en s'assurant qu'il ne dépasse pas la taille de la liste
            int end = Math.min(i + clusterSize, data.size());
            
            // Extraire un sous-ensemble correspondant à un cluster
            List<Integer> sublist = data.subList(i, end);
            
            // Mélanger aléatoirement les éléments à l'intérieur du cluster
            Collections.shuffle(sublist);
        }
    }
}
