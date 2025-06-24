package strategieDesordre;
import generator.DisorderLevel;
import java.util.List;

/**
 * Interface pour définir une stratégie de désordre.
 */
public interface DisorderStrategy {
    /**
     * Applique un niveau de désordre spécifié à la liste de données donnée.
     *
     * @param data la liste d'entiers à modifier.
     * @param level le niveau de désordre à appliquer.
     */
    void apply(List<Integer> data, DisorderLevel level);
}
