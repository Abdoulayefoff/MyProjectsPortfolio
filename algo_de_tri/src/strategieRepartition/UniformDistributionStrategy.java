package strategieRepartition;

import java.util.List;

/**
 * Stratégie pour appliquer une distribution uniforme sans modification.
 */
public class UniformDistributionStrategy implements DistributionStrategy {
    @Override
    public void apply(List<Integer> data) {
        // Aucune modification ; les données restent uniformément réparties.
    }
}
