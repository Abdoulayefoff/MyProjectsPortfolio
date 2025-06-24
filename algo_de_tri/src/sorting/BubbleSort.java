package sorting;

public class BubbleSort implements SortAlgorithmes{
    int compare = 0;
    int echange = 0;
    
    
    @Override
    public void sort(int[] data) {
        int taille = data.length;

        for (int i = 0; i < taille -1; i++) {
            for (int j = 0; j < taille - i - 1; j++) {
                // compte 
                compare++;
                if (data[j] > data[j+1]) {
                    // on echage les valeurs à la position j avec la position j +1
                    echange++;
                    int tmp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = tmp;
                }
            }
        }
    }
    @Override
    public int nbEchangeCount() {
        
        return echange;
    }
        
    @Override
    public int nbComparaisonCount() {
       return compare;
    }
}
