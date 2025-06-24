package vue; 
import modelling.Variable;
import planning.Action; 
import blocksworld.BlocksVariable; 
import java.awt.Dimension; 
import java.util.Map; 
import java.util.List; 
import javax.swing.JFrame; 
import bwmodel.BWState; 
import bwmodel.BWStateBuilder; 
import bwui.BWComponent; 
import bwui.BWIntegerGUI; 


public class Vue {
  private Map<Variable, Object> state; 
  private String titre; 
  private BlocksVariable blVariables; 
  

  public Vue(BlocksVariable blVariables, Map<Variable, Object> state, String titre) {
    this.blVariables = blVariables; 
    this.state = state; 
    this.titre = titre; 
  }

  public Vue(BlocksVariable bVariable, Map<Variable, Object> state) {
    this(bVariable, state, "Vue"); 
  }


  //methode pour creer un état du monde des blocs
  public BWState<Integer> makedBwState() {
    int numBlocks = blVariables.getonbVar().size(); // on recupère le nombre de blocs
    BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(numBlocks); 
    //pour chaque variable représentant un bloc
    for (Variable variable : blVariables.getonbVar()) {
      int index = BlocksVariable.getIndexOfBlockOrPile(variable);
      int pos = (int) state.get(variable); //on recupère la position de ce bloc dans l'état actuel
      //si la position est valide (>= 0)
      if (pos >= 0)
        builder.setOn(index, pos); //on définit la position du bloc dans l'état construit
    }
    BWState<Integer> state = builder.getState(); //on obtient l'état construit
    return state; // et on retourne l'état du monde de blocs
  }

  //une méthode pour afficher un état donné
  public void display(int posX, int posY) {
    int numBlocks = blVariables.getonbVar().size(); //le nombre de blocs
    BWState<Integer> state = makedBwState(); //état actuel du monde de blocs
    BWIntegerGUI gui = new BWIntegerGUI(numBlocks); //on cree une interface graphique p
    JFrame frame = new JFrame(titre);
    frame.setLocation(posX, posY); 
    frame.setPreferredSize(new Dimension(1000, 1000)); 
    frame.add(gui.getComponent(state)); //on ajoute le composant graphique à la fenêtre
    frame.pack(); 
    frame.setVisible(true); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  
  //une methode pour afficher l'execution d'un plan
  public void displayPlan(List<Action> actions, int posX, int posY) {
    int numBlocks = blVariables.getonbVar().size(); 
    BWIntegerGUI gui = new BWIntegerGUI(numBlocks); 
    JFrame frame = new JFrame(this.titre); 
    frame.setLocation(posX, posY); 
    frame.setPreferredSize(new Dimension(1000, 1000)); 
    BWState<Integer> bwState = makedBwState(); 
    BWComponent<Integer> component = gui.getComponent(bwState); 
    frame.add(component); 
    frame.pack(); 
    frame.setVisible(true); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Exécution du plan affichant chaque action avec un délai
    for (Action action : actions) {
      try {
        Thread.sleep(1000); //pause d'une seconde entre les actions
      } catch (InterruptedException ie) {
        ie.printStackTrace(); //affiche une erreur si l'interruption se produit
      }
      state = action.successor(state); 
      component.setState(makedBwState()); 
    }
  }
}