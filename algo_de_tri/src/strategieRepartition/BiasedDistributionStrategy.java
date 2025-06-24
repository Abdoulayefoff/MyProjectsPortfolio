package strategieRepartition;

import java.util.List;
import java.util.Random;

/**
 * Stratégie pour appliquer une distribution biaisée aux données.
 * (avec des probabilités différentes pour chaque intervalle).
 */
public class BiasedDistributionStrategy implements DistributionStrategy {
    @Override
    public void apply(List<Integer> data) {
        Random random = new Random();
        
        // Pour chaque élément de la liste de données
        for (int i = 0; i < data.size(); i++) {
            // 70% de chances d'assigner une valeur entre 0 et 9
            if (random.nextDouble() < 0.7) {
                data.set(i, random.nextInt(10)); // Valeurs entre 0 et 9
            } 
            // 30% de chances d'assigner une valeur entre 0 et 99
            else {
                data.set(i, random.nextInt(100)); // Valeurs entre 0 et 99
            }
        }
    }
}
