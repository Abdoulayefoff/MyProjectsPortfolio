package blocksworld;
import modelling.Variable;
import modelling.Constraint;
import modelling.Implication;
import java.util.Set;
import java.util.HashSet;

//Cette classe represente une contrainte de régularité entre les blocs
public class BlocksRegularConfigConstraint {

    private BlocksWorld bw;
    private Set<Constraint> contraintes;

    //Constructeur
    public BlocksRegularConfigConstraint(BlocksWorld bw){
        this.bw = bw;
        this.contraintes = new HashSet<>();
        this.contraintes.addAll(bw.getContraintes()); //ajout de toutes les contraintes deja presentes dans BlocksWord
        buildRegularyContraintes();
    }

    // Méthode pour construire des contraintes de régularisation
    public void buildRegularyContraintes() {
        Set<Variable> onVariables = bw.getBlVariables().getonbVar(); //on récupère les variables "on" du monde des blocs
        // on parcours chaque variable "on" pour appliquer les contraintes de régularisation
        for (Variable variableOnI : onVariables) {
            for (Variable variableOnJ : onVariables) {
                int i = BlocksVariable.getIndexOfBlockOrPile(variableOnI); 
                int j = BlocksVariable.getIndexOfBlockOrPile(variableOnJ);

                int regularizationValue = j - (i - j); 
                if (i != j) { // on verifie que i et j sont différents pour éviter les auto-contraintes
                    Set<Object> domainI = new HashSet<>(); //domaine pour la variable variableOnI
                    domainI.add(j); 

                    Set<Object> domainJ = new HashSet<>();
                    for (int l = 1; l <= bw.getNumPiles(); l++) {
                        domainJ.add(-l); // on ajoute d'une valeur négative pour représenter que variableOnJ peut être libre
                    }
                    // on ajpute une condition pour regularizationValue : s'il est dans les bornes, on l'ajoute au domaine de variableOnJ
                    if (regularizationValue >= 0 && regularizationValue < bw.getNumBlocks())
                        domainJ.add(regularizationValue);
                    contraintes.add(new Implication(variableOnI, domainI, variableOnJ, domainJ));
                    
                }
            }
        }
    }

    //Getters
    public BlocksWorld getBw() {return bw;}
    public Set<Constraint> getContraintes(){ return contraintes;}

    
}
