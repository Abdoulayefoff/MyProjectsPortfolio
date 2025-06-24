package sorting;

public class InsertionSort implements SortAlgorithmes {
    int compare = 0;
    int echange = 0;

    @Override
    public void sort(int[] data) {
       int taille = data.length;
       for (int i = 1; i < taille; i++) {
            int valeur = data[i];
            int indice = i - 1;
            // aranger les elts de data[0..i-1] qui sont plus grands que la valeur
            //  vers une position à droite.
            while (indice >= 0 ) {
                // on compare les valeurs
                compare++;

                if(data[indice] > valeur){

                    data[indice + 1] = data[indice];
                    indice --;
                    // on echange les cases.
                    echange++;
                }else{
                    break;
                }
            }
            // placer la valeur dans la bonne position
            data[indice + 1] = valeur;
            if (indice != i - 1) {
                echange++;
            }
       }
    }

    @Override
    public int nbComparaisonCount() {
        return compare;
    }

    @Override
    public int nbEchangeCount() {
       return echange;
    }
    
}
