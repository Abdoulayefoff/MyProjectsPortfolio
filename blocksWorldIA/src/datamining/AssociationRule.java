package datamining;
import modelling.BooleanVariable;
import java.util.Set;

// Classe représente une règle d'association entre les items
public class AssociationRule {
    
    protected Set<BooleanVariable> premise;
    protected Set<BooleanVariable> conclusion;
    protected float frequency;
    protected float confidence;

    // Constructeur
    public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, float frequency, float confidence) {
        this.premise = premise;
        this.conclusion = conclusion;
        this.frequency = frequency;
        this.confidence = confidence;
    }

    
    public Set<BooleanVariable> getPremise() {
        return premise;
    }

    public Set<BooleanVariable> getConclusion() {
        return conclusion;
    }

    public float getFrequency() {
        return frequency;
    }

    public float getConfidence() {
        return confidence;
    }

    //Methode toString
    @Override
    public String toString() {
        return "Association_Rule [Premise: " + premise +
            ", Conclusion: " + conclusion +
            ", Frequency: " + frequency +
            ", Confidence: " + confidence + "]";
    }


}
