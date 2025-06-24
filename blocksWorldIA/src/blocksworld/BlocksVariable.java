package blocksworld;
import modelling.Variable;
import modelling.BooleanVariable;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;



//Cette classe représente toutes les variables du monde des blocs
public class BlocksVariable {

    private int numBloks; 
    private int numPiles; 
    private Set<Variable> onbVar; 
    private Set<Variable> fixedbVar; 
    private Set<Variable> freepVar; 
    private Map<String, Variable> registers = new HashMap<>(); 
    private Set<Variable> allVariables = new HashSet<>(); 


    ///Constructeur
    public BlocksVariable(int numBloks, int numPiles) {
        this.numBloks = numBloks; 
        this.numPiles = numPiles;
        initializeVariables();
        
    }

    //une méthode pour initialiser toutes les variables
    public void initializeVariables(){
        buildOnbVariables(); 
        buildfixedbVar(); 
        buildfreepVar(); 
        allVariables.addAll(onbVar);
        allVariables.addAll(fixedbVar);
        allVariables.addAll(freepVar);
    }
    
    //une méthode pour creer toutes les variables onb
    public void buildOnbVariables() {
        onbVar = new HashSet<>();
        Set<Object> domaine = new HashSet<Object>();
        for (int i = -numPiles; i < numBloks; i++) { // on ajoute des valeurs pour le domaine
            domaine.add(i);
        }
        //création des variables Onb
        for(int i = 0; i < numBloks; i++) {
            Set<Object> domaineI = new HashSet<Object>(domaine); // Copie du domaine
            domaineI.remove(i); // le bloc actuel est exclut   
            Variable onbVariable = new Variable("on_" + Integer.toString(i), domaineI);
            onbVar.add(onbVariable); 
            registers.put(onbVariable.getName(), onbVariable); // on enregistre dans le registre registers
        }
    }

    //une méthode pour creer toutes les variables Fixebd
    public void buildfixedbVar() {
        fixedbVar = new HashSet<>();
        //création des variables Fixedb
        for(int i = 0; i < numBloks; i++) {
            Variable fixedVariable = new BooleanVariable("fixed_" + Integer.toString(i)); 
            fixedbVar.add(fixedVariable); 
            registers.put(fixedVariable.getName(), fixedVariable);
        }
    }


    //une méthode pour creer toutes les variables Freep
    public void buildfreepVar() {
        freepVar = new HashSet<>();
        //création des variables Freep
        for(int i = 0; i < numPiles; i++) {
            Variable freepVariable = new BooleanVariable("free_" + Integer.toString(i)); 
            freepVar.add(freepVariable);
            registers.put(freepVariable.getName(), freepVariable); 
        }
    }


    //une méthode statique permettant d'obtenir l'indice d'un bloc ou d'une pile à partir d'une variable
    public static int getIndexOfBlockOrPile(Variable v) {
        String variableName = v.getName(); 
        int underscoreIndex = variableName.lastIndexOf('_'); // Trouve l'index du dernier '_'
        return Integer.parseInt(variableName.substring(underscoreIndex + 1)); // Extrait la partie après le dernier '_' et convertit en entier
    }
    

    /*
     * une méthode pour obtenir l'eetat des variables en fonction d'un état donné
     * prends en paramètre un state répresentant l'état des piles et des blocs
     * et return une map associant chaque variable à sa valeur dans cet état
     */
    public Map<Variable, Object> getVariablesState(int[][] state) {
        Map<Variable, Object> map = new HashMap<>();

        // on parcours les piles (lignes) dans le tableau d'état
        for (int i = 0; i < state.length; i++) {
            int nbBlocks = state[i].length; // nbre de blocs dans la pile i
            Variable free = registers.get("free_" + i); // on recupère la variable "free" pour cette pile depuis le registre
            map.put(free, nbBlocks == 0); // si la pile est vide, on indique qu'elle est libre (free = true)

            // on parcours les blocs dans la pile i
            for (int j = 0; j < nbBlocks; j++) {
                int blockId = state[i][j]; // ID du bloc dans la pile à la position j
                Variable on = registers.get("on_" + blockId); 
                Variable fixed = registers.get("fixed_" + blockId); 

                // on verifie si c'est le premier bloc dans la pile
                if (j == 0) { 
                    map.put(on, -i - 1); // le 1er bloc est "sur" la pile elle-même (indiqué par une valeur négative)
                    map.put(fixed, nbBlocks > 1); // il est fixé si la pile contient plus d'un bloc, sinon non fixé
                } else { 
                    map.put(on, state[i][j - 1]); // le bloc est "sur" le bloc juste en dessous
                    map.put(fixed, j < nbBlocks - 1); // il est fixé s'il n'est pas le dernier bloc de la pile
                }
            }
        }
        return map;
    }

    // Constructeurs de noms de variables basés sur un indice de bloc ou de pile
    public String generateOnBlockName(int blockIndex) { return "on_" + blockIndex; }
    public String generateFixedBlockName(int blockIndex) { return "fixed_" + blockIndex; }
    public String generateFreePileName(int pileIndex) { return "free_" + pileIndex; }
    
    //pour recuperer une variable par son index
    public Variable getOnbVariableByIndexe(int blockIndex) { return registers.get(generateOnBlockName(blockIndex)); }
    public Variable getFixedbVariableByIndexe(int blockIndex) { return registers.get(generateFixedBlockName(blockIndex)); }
    public Variable getFreepVariableByIndexe(int pileIndex) { return registers.get(generateFreePileName(pileIndex)); }

    // Getters
    public int getNumBlocks() { return numBloks; }
    public int getNumPiles() { return numPiles; }
    public Set<Variable> getonbVar() { return onbVar; }
    public Set<Variable> getfixedbVar() { return fixedbVar; }
    public Set<Variable> getfreepVar() { return freepVar; }
    public Set<Variable> getallVariables() { return allVariables; }
}
