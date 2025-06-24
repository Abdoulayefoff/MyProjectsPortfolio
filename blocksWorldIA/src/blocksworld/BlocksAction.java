package blocksworld;
import modelling.Variable;
import planning.Action;
import planning.BasicAction;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

//Cette classe permet de generer toutes les actions dans le monde des blocs
public class BlocksAction {
    
    private Set<Action> actions;
    private BlocksWorld bw; 

    //Constructeur
    public BlocksAction(int numBlocks, int numPiles){
        this.bw = new BlocksWorld(numBlocks, numPiles); 
        this.actions = new HashSet<>(); 
        buildActionToMoveB1FromB2ToB3(); 
        buildActionToMoveB1FromB2ToP(); 
        buildActionToMoveB1FromPToB2(); 
        buildActionToMoveBFromP1ToP2(); 
    }

    //une methode pour déplacer un bloc b1 de b2 à b3
    public void buildActionToMoveB1FromB2ToB3(){
        int numBlocks = bw.getNumBlocks();
        //une bloucle sur chaque bloc b1
        for(int b1=0; b1 < numBlocks; b1++){
            //boucle sur chaque bloc b2, qui est different de b1
            for(int b2=0; b2 < numBlocks; b2++){
                if(b1 == b2)
                    continue; //ignore si bi et b2 sont identiques(on ne veux pas deplacer un bloc sur lui meme0)

                //boucle sur chaque bloc b3 qui est differents de b1 et b2
                for(int b3=0; b3 < numBlocks; b3++){
                    if(b3 == b1 || b3 == b2)
                        continue; //ignore si b3 est identique a b1 ou b2
                    //on recupere la variable representant la position actuelle de b1
                    Variable blockB1 = bw.getBlVariables().getOnbVariableByIndexe(b1);
                    Variable fixedB1 = bw.getBlVariables().getFixedbVariableByIndexe(b1);//recupere la variable indiquant si b1 est fixé
                    Variable fixedB2 = bw.getBlVariables().getFixedbVariableByIndexe(b2);
                    Variable fixedB3 = bw.getBlVariables().getFixedbVariableByIndexe(b3);

                    //creation des preconditions pour l'action 
                    Map<Variable, Object> precondition = new HashMap<>();
                    precondition.put(blockB1, b2);//b1 doit etre sur b2 avant l'action
                    precondition.put(fixedB1,false);
                    precondition.put(fixedB3, false); 

                    //creation des effets de l'action
                    Map<Variable, Object> effects = new HashMap<>();
                    effects.put(blockB1, b3);
                    effects.put(fixedB2, false);
                    effects.put(fixedB3, true);

                    //ajoute l'action a la liste des actions
                    actions.add(new BasicAction(precondition, effects, 1));
                }
            }
        }
    }

    //une methode pour déplacer un bloc b1 de b2 à une pile p
    public void buildActionToMoveB1FromB2ToP() {
        int numBlocks = bw.getNumBlocks();
        int numPiles = bw.getNumPiles();
        // boucle sur chaque bloc b1
        for (int b1 = 0; b1 < numBlocks; b1++) {
            // boucle sur chaque bloc b2, qui est différent de b1
            for (int b2 = 0; b2 < numBlocks; b2++) {
                if (b1 == b2)
                    continue; // Ignore si b1 et b2 sont identiques 
        
                // boucle sur chaque pile p
                for (int p = 0; p < numPiles; p++) {
                    // Récupère la variable représentant la position actuelle de b1
                    Variable blockB1 = bw.getBlVariables().getOnbVariableByIndexe(b1);
                    Variable fixedB1 = bw.getBlVariables().getFixedbVariableByIndexe(b1);
                    Variable freeP = bw.getBlVariables().getFreepVariableByIndexe(p);
                    Variable fixedB2 = bw.getBlVariables().getFixedbVariableByIndexe(b2);
        
                    //création des préconditions pour l'action
                    Map<Variable, Object> precondition = new HashMap<>();
                    precondition.put(blockB1, b2); 
                    precondition.put(fixedB1, false); 
                    precondition.put(freeP, true); 
        
                    //création des effets de l'action 
                    Map<Variable, Object> effects = new HashMap<>();
                    effects.put(blockB1, -p - 1); // b1 doit être déplacé à la pile p après l'action (p est transformé en un indice négatif)
                    effects.put(fixedB2, false); 
                    effects.put(freeP, false); 
        
                    actions.add(new BasicAction(precondition, effects, 1));
                }
            }
        }
    }

    //une methode pour déplacer un bloc b1 d'une pile p à b2
    public void buildActionToMoveB1FromPToB2() {
        int numBlocks = bw.getNumBlocks();
        int numPiles = bw.getNumPiles();

        // boucle sur chaque bloc b1
        for (int b1 = 0; b1 < numBlocks; b1++) {
            // boucle sur chaque bloc b2, qui est différent de b1
            for (int b2 = 0; b2 < numBlocks; b2++) {
                if (b1 == b2) continue; // Ignore si b1 et b2 sont identiques
                // boucle sur chaque pile p
                for (int p = 0; p < numPiles; p++) {
                    // Récupère la variable représentant la position actuelle de b1
                    Variable BlockB1 = bw.getBlVariables().getOnbVariableByIndexe(b1);
                    Variable fixedB1 = bw.getBlVariables().getFixedbVariableByIndexe(b1);
                    Variable freeP = bw.getBlVariables().getFreepVariableByIndexe(p);
                    Variable fixedB2 = bw.getBlVariables().getFixedbVariableByIndexe(b2);
        
                    //création des préconditions pour l'action 
                    Map<Variable, Object> precondition = new HashMap<>();
                    precondition.put(BlockB1, -p - 1); // b1 doit être sur la pile p avant l'action 
                    precondition.put(fixedB1, false); 
                    precondition.put(fixedB2, false); 
        
                    //création des effets de l'action 
                    Map<Variable, Object> effects = new HashMap<>();
                    effects.put(BlockB1, b2); 
                    effects.put(freeP, true); 
                    effects.put(fixedB2, true);
        
                    actions.add(new BasicAction(precondition, effects, 1));
                }
            }
        }
    }

    //une methode pour déplacer un bloc b d'une pile p1 à une pile p2
    public void buildActionToMoveBFromP1ToP2() {
        int numBlocks = bw.getNumBlocks();
        int numPiles = bw.getNumPiles();
        // boucle sur chaque bloc b
        for (int b = 0; b < numBlocks; b++) {
            // boucle sur chaque pile p1 (source)
            for (int p1 = 0; p1 < numPiles; p1++) {
                // boucle sur chaque pile p2 (destination), mais on ignore si p1 et p2 sont identiques
                for (int p2 = 0; p2 < numPiles; p2++) {
                    if (p1 == p2) continue; // Ignore si p1 et p2 sont la même pile
        
                    // Récupère la variable représentant la position actuelle du bloc b
                    Variable block = bw.getBlVariables().getOnbVariableByIndexe(b);
                    Variable fixedB = bw.getBlVariables().getFixedbVariableByIndexe(b);
                    Variable freeP2 = bw.getBlVariables().getFreepVariableByIndexe(p2);
                    Variable freeP1 = bw.getBlVariables().getFreepVariableByIndexe(p1);
        
                    //création des préconditions pour l'action 
                    Map<Variable, Object> precondition = new HashMap<>();
                    precondition.put(block, -p1 - 1); // Le bloc b doit être sur la pile p1 avant l'action
                    precondition.put(fixedB, false); 
                    precondition.put(freeP2, true);
        
                    //création des effets de l'action
                    Map<Variable, Object> effects = new HashMap<>();
                    effects.put(block, -p2 - 1); 
                    effects.put(freeP1, true); 
                    effects.put(freeP2, false); 
        
                    actions.add(new BasicAction(precondition, effects, 1));
                }
            }
        }
    }

    //Getters 
    public Set<Action> getActions() {return actions;}
    public BlocksWorld getBw() { return bw;}
    
}
