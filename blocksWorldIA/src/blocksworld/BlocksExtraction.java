package blocksworld;
import bwgeneratordemo.*;
import modelling.Variable;
import modelling.BooleanVariable;
import datamining.BooleanDatabase;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.List;


//Cette classe instancie toutes les variables boolennees pour le datamining
public class BlocksExtraction {

    private Set<BooleanVariable> items = new HashSet<>();
    private Map<String, BooleanVariable> booleanVariableRegistry = new HashMap<>();//un registre pour les variables booléennes
    private BlocksVariable bVariables;

    //Constructeur
    public BlocksExtraction(int numBlocks, int numPiles) {
        bVariables = new BlocksVariable(numBlocks, numPiles);
        buildOnBlock();
        buildOnTable();
    }
    
    //une methode pour construire les variables on_i_j pour indiquer si un bloc i est sur un bloc j
    public void buildOnBlock() {
        //parcourt toutes les variables "on_i"
        for (Variable on_i : bVariables.getonbVar()) {
          //parcourt toutes les variables "fixed_j"
            for (Variable fixed_j : bVariables.getfixedbVar()) {
                int i = BlocksVariable.getIndexOfBlockOrPile(on_i); 
                int j = BlocksVariable.getIndexOfBlockOrPile(fixed_j); 
                if (i == j) // Ignore les cas où i et j sont identiques
                    continue;
                String variableName = "on_" + i + "_" + j;//on génère le nom de la variable "on_i_j"
                BooleanVariable on_i_j = new BooleanVariable(variableName);
                // on ajoute la variable et ses dépendances à l'ensemble
                items.add(on_i_j);
                items.add((BooleanVariable) fixed_j);
                
                booleanVariableRegistry.put(variableName, on_i_j);
                booleanVariableRegistry.put("fixed_" + j, (BooleanVariable) fixed_j);
            }
        }
    }


    //une methode pour construire les onTable pour indiquer si un bloc i est directement sur une pile p
    public void buildOnTable() {
        //on parcours toutes les variables "on_i"
        for (Variable on_i : bVariables.getonbVar()) {
          //parcours de toutes les variables "free_p"
            for (Variable free_j : bVariables.getfreepVar()) {
                int i = BlocksVariable.getIndexOfBlockOrPile(on_i); 
                int j = BlocksVariable.getIndexOfBlockOrPile(free_j); 
                String variableName = "onTable_" + i + "_" + j;
                BooleanVariable onTable_i_j = new BooleanVariable(variableName);
                items.add(onTable_i_j);
                items.add((BooleanVariable) free_j);

                booleanVariableRegistry.put(variableName, onTable_i_j);
                booleanVariableRegistry.put("free_" + j, (BooleanVariable) free_j);
            }
        }
    }


    //Cette methode retourne les variables representant une relation valide dans la transaction
    public Set<BooleanVariable> extractTransactionVariables(List<List<Integer>> transaction) {
        int numPiles = transaction.size(); //nbre de piles dans la transaction
        Set<BooleanVariable> var = new HashSet<>();
        //parcourt de toutes les piles
        for (int p = 0; p < numPiles; p++) { 
            if (transaction.get(p).isEmpty()) {
                String freeVarName = "free_" + p; // Variable "free_p"
                BooleanVariable freep = booleanVariableRegistry.get(freeVarName);
                if (freep == null) { //si la variable n'existe pas encore dans le registre
                    freep = new BooleanVariable(freeVarName);
                    booleanVariableRegistry.put(freeVarName, freep); 
                }
                var.add(freep); 
            }
            int numBlocks = transaction.get(p).size(); //nb de blocs dans la pile
            //on parcours chaque bloc
            for (int b = 0; b < numBlocks; b++) { 
                int j = transaction.get(p).get(b); //identifiant du bloc
                if (b == 0) { //si le bloc est en bas de la pile
                    String onTableVarName = "onTable_" + j + "_" + Integer.toString(p);
                    BooleanVariable onTable = booleanVariableRegistry.get(onTableVarName);
                    if (onTable == null) { //si la variable n'existe pas encore dans le registre
                        onTable = new BooleanVariable(onTableVarName);
                        booleanVariableRegistry.put(onTableVarName, onTable);
                    }
                    var.add(onTable);
                }
                if (b < numBlocks - 1) { //si ce n'est pas le dernier bloc
                    int i = transaction.get(p).get(b + 1); //identifiant du bloc suivant
                    String onVarName = "on_" + i + "_" + j;
                    String fixedVarName = "fixed_" + j;

                    BooleanVariable on_ij = booleanVariableRegistry.get(onVarName);
                    if (on_ij == null) { 
                        on_ij = new BooleanVariable(onVarName);
                        booleanVariableRegistry.put(onVarName, on_ij);
                    }

                    BooleanVariable fixed = booleanVariableRegistry.get(fixedVarName);
                    if (fixed == null) { // Si la variable "fixed" n'existe pas encore dans le registre
                        fixed = new BooleanVariable(fixedVarName);
                        booleanVariableRegistry.put(fixedVarName, fixed);
                    }

                    var.add(on_ij);
                    var.add(fixed);
                }
            }
        }
        return var;
    }

    //une methode qui construit une BD
    public BooleanDatabase generateDatabase(int transactionsNumb) {
        BooleanDatabase database = new BooleanDatabase(items); //on instancie la base
        //on parcours chaque transaction
        for (int i = 0; i < transactionsNumb; i++) {
            List<List<Integer>> transaction = Demo.getState(new Random()); //on genère une transaction aléatoire
            Set<BooleanVariable> instance = extractTransactionVariables(transaction); //on recupère les variables valides
            database.add(instance); 
        }
        return database;
    }


}