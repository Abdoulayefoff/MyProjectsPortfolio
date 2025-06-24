package vue; 
import planning.Action; 
import java.util.List;  


public class DisplayPlanActions extends Thread {  
    
    private Vue vue; //pour afficher le plan sur l'interface graphique.
    private List<Action> actionPlan; //liste des actions du plan à afficher.
    private int xPosition; 
    private int yPosition; 

    // Constructeur 
    public DisplayPlanActions(Vue vue, List<Action> actionPlan, int xPosition, int yPosition) {
        this.vue = vue;
        this.actionPlan = actionPlan; 
        this.xPosition = xPosition; 
        this.yPosition = yPosition;  
    }

    //la méthode run() contient le code qui sera exécuté lorsque le thread démarrera
    @Override
    public void run() {
        //on appelle la methode displayPlan de Vue en luis passant la liste des actions et les positions
        vue.displayPlan(actionPlan, xPosition, yPosition); 
    }
}