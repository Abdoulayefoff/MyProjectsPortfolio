package strategieDesordre;
import generator.DisorderLevel;
import java.util.Collections;
import java.util.List;

/**
 * Stratégie pour mélanger de manière localisée au sein de blocs de données.
 */
public class LocalizedShuffleStrategy implements DisorderStrategy {
    @Override
    public void apply(List<Integer> data, DisorderLevel level) {
        // Calculer la taille des blocs à mélanger en fonction du niveau de désordre
        int blockSize = (int) (data.size() * level.getLevel());
        
        // Mélanger chaque bloc de données de manière indépendante
        for (int i = 0; i < data.size(); i += blockSize) {
            // Déterminer la fin du bloc, en s'assurant qu'il ne dépasse pas la taille de la liste
            int end = Math.min(i + blockSize, data.size());
            
            // Créer un sous-ensemble de la liste de données pour chaque bloc
            List<Integer> sublist = data.subList(i, end);
            
            // Mélanger le bloc de données de manière aléatoire
            Collections.shuffle(sublist);
        }
    }
}
