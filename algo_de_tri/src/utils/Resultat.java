package utils;
public class Resultat {
    private String algorthmeNom;
    private int comparaisonCount;
    private int echangeCount;
    private long executionTime;

    public Resultat(String algorthmeNom,int comparaisonCount,int echangeCount,long executionTime){
        this.algorthmeNom = algorthmeNom;
        this.comparaisonCount = comparaisonCount;
        this.echangeCount = echangeCount;
        this.executionTime = executionTime;
    }

    public String getAlgorthmeNom() {
        return algorthmeNom;
    }

    public int getComparaisonCount() {
        return comparaisonCount;
    }

    public int getEchangeCount() {
        return echangeCount;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return String.format("Algorithme: %s\nComparaisons: %d\nEchanges: %d\nTemps d'executions: %d ms\n",
                        algorthmeNom,comparaisonCount,echangeCount,executionTime);
    }

    
}
