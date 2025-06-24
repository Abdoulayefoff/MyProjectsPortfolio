package blocksworld;
import modelling.Variable;
import modelling.Constraint;
import modelling.UnaryConstraint;
import java.util.Set;
import java.util.HashSet;

//Cette classe cree des contraintes de croissance
public class BlocksIncreasingConfigConstraint {
    private Set<Constraint> contraintes;
    private BlocksWorld bw;

    //Constructeur
    public BlocksIncreasingConfigConstraint(BlocksWorld bw){
        this.bw = bw;
        this.contraintes = new HashSet<>();
        this.contraintes.addAll(bw.getContraintes());
        buildIncreasingContraintes();
    }

    //une methode pour creer des contraines de croissance
    public void buildIncreasingContraintes(){
        Set<Variable> onbVariables = bw.getBlVariables().getonbVar(); // on récupère les variables on du monde des blocs
        //on parcours chaque variable on pour definir des contraintes de domaine croissant
        for(Variable variableOnI : onbVariables){
            int indexI = BlocksVariable.getIndexOfBlockOrPile(variableOnI);
            Set<Object> domainI = new HashSet<>();

            //on remplit le domaine de variableOnI avec les valeurs inférieures à i et les indices négatifs de piles
            for(int pileIndex = -bw.getNumPiles(); pileIndex < indexI; pileIndex++){
                domainI.add(pileIndex); //ajoute chaque valeur possible au domaine
            }
            //on ajoute une contrainte unitaire limitant les valeurs de variableOnI au domaine défini
            contraintes.add(new UnaryConstraint(variableOnI, domainI));
        }
    }
    
    //Getters
    public BlocksWorld getBw() {return bw;}
    public Set<Constraint> getContraintes(){ return contraintes;}

}