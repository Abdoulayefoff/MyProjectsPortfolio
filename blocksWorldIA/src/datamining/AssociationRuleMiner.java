package datamining;
import java.util.Set;


//Cette interface représente un extracteur de règles d'association
public interface AssociationRuleMiner {
    
    public BooleanDatabase getDatabase();
    public Set<AssociationRule> extract(float minFrequency, float minConfidence);
}
