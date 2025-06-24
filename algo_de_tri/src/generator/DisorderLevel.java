package generator;

/**
 * Enumération pour différents niveaux de désordre.
 */
public enum DisorderLevel {
    NULL(0.0),    // 0% de désordre
    LOW(0.2),     // 20% de désordre
    MEDIUM(0.5),  // 50% de désordre
    HIGH(0.8),    // 80% de désordre
    MAX(1.0);     // 100% de désordre

    private final double level; // Niveau de désordre

    /**
     * Constructeur pour l'énumération.
     *
     * @param level le niveau de désordre exprimé en pourcentage.
     */
    DisorderLevel(double level) {
        this.level = level; // Initialisation du niveau de désordre
    }

    /**
     * Récupère le niveau de désordre.
     *
     * @return le niveau de désordre sous forme de valeur double.
     */
    public double getLevel() {
        return this.level; // Retourne le niveau de désordre
    }
}
