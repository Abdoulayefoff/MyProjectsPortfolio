package generator;
import strategieDesordre.*;
import strategieRepartition.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale pour générer des données non triées avec des stratégies de désordre et de répartition configurables.
 */
public class DataGenerator {
    private DisorderStrategy disorderStrategy; // Stratégie de désordre
    private DistributionStrategy distributionStrategy; // Stratégie de répartition

    /**
     * Constructeur pour initialiser les stratégies de désordre et de répartition.
     *
     * @param disorderStrategy la stratégie pour appliquer le désordre aux données.
     * @param distributionStrategy la stratégie pour appliquer la répartition aux données.
     */
    public DataGenerator(DisorderStrategy disorderStrategy, DistributionStrategy distributionStrategy) {
        this.disorderStrategy = disorderStrategy; // Initialisation de la stratégie de désordre
        this.distributionStrategy = distributionStrategy; // Initialisation de la stratégie de répartition
    }

    /**
     * Définit la stratégie pour appliquer le désordre aux données.
     *
     * @param disorderStrategy la nouvelle stratégie de désordre.
     */
    public void setDisorderStrategy(DisorderStrategy disorderStrategy) {
        this.disorderStrategy = disorderStrategy; // Mise à jour de la stratégie de désordre
    }

    /**
     * Définit la stratégie pour répartir les données.
     *
     * @param distributionStrategy la nouvelle stratégie de répartition.
     */
    public void setDistributionStrategy(DistributionStrategy distributionStrategy) {
        this.distributionStrategy = distributionStrategy; // Mise à jour de la stratégie de répartition
    }

    /**
     * Génère une liste d'entiers non triée et applique les stratégies de désordre et de répartition.
     *
     * @param size la taille de la liste à générer.
     * @param level le niveau de désordre à appliquer.
     * @return une liste d'entiers avec le désordre et la répartition appliqués.
     */
    public List<Integer> generate(int size, DisorderLevel level) {
        // Créer une liste d'entiers de 1 à "size"
        List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            data.add(i);
        }

        // Mélanger la liste pour la rendre non triée
        java.util.Collections.shuffle(data);

        // Appliquer la stratégie de désordre (si un désordre supplémentaire doit être appliqué)
        disorderStrategy.apply(data, level);

        // Appliquer la stratégie de répartition
        distributionStrategy.apply(data);

        return data; // Retourner la liste de données modifiée
    }
}
