package blocksworld;
import modelling.Variable;
import modelling.Constraint;
import modelling.DifferenceConstraint;
import modelling.Implication;
import java.util.Set;
import java.util.HashSet;


//Cette classe représente les contraintes dans le monde des blocs
public class BlocksWorld {

    private int numBlocks;
    private int numPiles;
    private BlocksVariable blVariables; 
    private Set<Constraint> contraintes; 

    //Constructeur
    public BlocksWorld(int numBlocks, int numPiles){
        this.numBlocks = numBlocks;
        this.numPiles = numPiles;
        this.blVariables = new BlocksVariable(numBlocks, numPiles);
        this.contraintes = new HashSet<>();
        buildDifferenceContraintes(); 
        buildImplicationContraintes();
    }

    //une methode pour construire des contraintes de differences entre les blocs
    public void buildDifferenceContraintes(){
        //on récupère toutes les les variables representant onb
        Set<Variable> onBVariable = blVariables.getonbVar();

        // on parcours chaque paire de variable onb pour creer une contrainte de difference
        for(Variable onb1 : onBVariable){
            for(Variable onb2: onBVariable){
                //on ajoute une contrainte de difference si les deux variables sont differentes
                if(!onb1.equals(onb2))
                    contraintes.add(new DifferenceConstraint(onb1, onb2));
            }
        }
    }

    //une méthode pour construire des contraintes d'implication
    public void buildImplicationContraintes() {
        //on parcours chaque variable onb représentant un bloc sur un autre bloc ou une pile
        for (Variable onb1 : blVariables.getonbVar()) {
            int b1Index = BlocksVariable.getIndexOfBlockOrPile(onb1);
            //première contrainte d'implication : si onb1 = b' alors fixedb = true
            for (Variable fixedb2 : blVariables.getfixedbVar()) {
                int b2Index = BlocksVariable.getIndexOfBlockOrPile(fixedb2); //on recupère l'indice du bloc pour fixedb2
                //ajout de la contrainte d'implication si les indices sont différents
                if (b1Index != b2Index) {
                    Set<Object> onb1Domaine = Set.of(b2Index); //domaine de onb1 : contient b2Index
                    Set<Object> fixedb2Domaine = Set.of(true); //domaine de fixedb2 : contient true
                    contraintes.add(new Implication(onb1, onb1Domaine, fixedb2, fixedb2Domaine));
                }
            }
            //deuxième contrainte d'implication : si onb = -(p+1), alors freep = true
            for (Variable freep : blVariables.getfreepVar()) {
                int pIndex = BlocksVariable.getIndexOfBlockOrPile(freep); 
                Set<Object> onb1Domaine2 = Set.of(-(pIndex + 1)); //domaine de onb1 : contient -(pIndex + 1)
                Set<Object> freepDomaine = Set.of(false); //domaine de freep : contient false
                contraintes.add(new Implication(onb1, onb1Domaine2, freep, freepDomaine));
            }
        }
    }

    //Les getters et setters
    public int getNumBlocks() { return numBlocks;}
    public int getNumPiles() { return numPiles;}
    public BlocksVariable getBlVariables() {return blVariables;}
    public Set<Constraint> getContraintes(){ return contraintes;}
    public void setNumBlocks(int numBlocks) { this.numBlocks = numBlocks; }
    public void setNumPiles(int numPiles) {this.numPiles = numPiles;}
    public void setBlocksVariables(BlocksVariable blVariables) { this.blVariables = blVariables;}
    public void setContraintes( Set<Constraint> contraintes) {this.contraintes = contraintes;}
}
