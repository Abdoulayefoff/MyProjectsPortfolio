package strategieDesordre;
import generator.DisorderLevel;
import java.util.Collections;
import java.util.List;

/**
 * Stratégie pour mélanger partiellement un pourcentage des données.
 */
public class PartialShuffleStrategy implements DisorderStrategy {
    @Override
    public void apply(List<Integer> data, DisorderLevel level) {
        // Calculer le nombre d'éléments à mélanger en fonction du niveau de désordre
        int elementsToShuffle = (int) (data.size() * level.getLevel());
        
        // Extraire une sous-liste contenant les éléments à mélanger
        List<Integer> sublist = data.subList(0, elementsToShuffle);
        
        // Mélanger aléatoirement la sous-liste
        Collections.shuffle(sublist);
    }
}
