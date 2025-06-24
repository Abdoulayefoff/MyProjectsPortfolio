package modelling;
import java.util.Set;

/*
 * Cette classe permet de representer une var avec un name et un domain
 */

 public class Variable {
 
    protected String name;
    protected Set<Object> domain;
    

    // Constructeur
    public Variable(String name, Set<Object> domain){
        this.name = name;
        this.domain = domain;
    }

    
    public String getName(){
        return this.name;
    }

    public Set<Object> getDomain(){
        return this.domain;
    }

    /**
     * Methode equals(Object)
     * elle verifie si 2 variables sont égales, c-à-d si elles ont le meme nom
     */
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true; // ici on verifie si les deux réf pointent vers le meme object en memoire et si c'est le cas, elles sont consideres comme egales

        if(!(o instanceof Variable))
            return false; // ici on verifie si x est une instance de la classe variable.Si ce n'est pas le cas la methone renvoie false

        Variable var = (Variable) o; // ici o est casté en variable, et on compare les noms des deux variables. On envoie true si les noms sont identiques, fals sinon
        return this.name.equals(var.name);
    }

    // Methode hashCode()
    @Override
    public int hashCode(){
        int prime = 31;
        return prime + (name == null ? 0 : name.hashCode());
    }
    
    // Methode non demandé que j'ai ajouté pour gerer l'affichage correctement
    @Override
    public String toString() {
        return "Variable{name='" + name + "', domain=" + domain + "}";
    }


 }