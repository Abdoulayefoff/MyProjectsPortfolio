package strategieDesordre;
import generator.DisorderLevel;
import java.util.Collections;
import java.util.List;

/**
 * Stratégie pour introduire des inversions entre des paires d'éléments aléatoires.
 */
public class InversionStrategy implements DisorderStrategy {
    @Override
    public void apply(List<Integer> data, DisorderLevel level) {
        // Calculer le nombre d'inversions à effectuer en fonction du niveau de désordre
        int inversions = (int) (data.size() * level.getLevel());
        
        // Effectuer les inversions sur des paires aléatoires
        for (int i = 0; i < inversions; i++) {
            // Sélectionner deux indices aléatoires dans la liste
            int index1 = (int) (Math.random() * data.size());
            int index2 = (int) (Math.random() * data.size());
            
            // Échanger les éléments aux indices sélectionnés
            Collections.swap(data, index1, index2);
        }
    }
}
