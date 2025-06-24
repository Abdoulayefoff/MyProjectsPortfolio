package strategieRepartition;

import java.util.List;

/**
 * Interface pour définir une stratégie de répartition des données.
 */
public interface DistributionStrategy {
    /**
     * Applique une distribution spécifique à la liste de données donnée.
     *
     * @param data la liste d'entiers à modifier.
     */
    void apply(List<Integer> data);
}
