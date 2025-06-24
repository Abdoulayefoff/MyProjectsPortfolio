/*package Main;
import generator.*;
import strategieDesordre.*;
import strategieRepartition.*;


import java.util.List;

/**
 * Main class to demonstrate the functionality of the data generator.
 
public class Main {
    public static void main(String[] args) {
        // Create a generator with inversion strategy and uniform distribution
        DataGenerator generator = new DataGenerator(
            new InversionStrategy(), new UniformDistributionStrategy());

        // Generate data with a low disorder level
        List<Integer> data1 = generator.generate(10, DisorderLevel.LOW);
        System.out.println("Inversion with uniform distribution: " + data1);

        // Create a generator with inversion strategy and clustered distribution
        generator = new DataGenerator(
            new InversionStrategy(), new ClusteredDistributionStrategy());

        // Generate data with a high disorder level
        List<Integer> data2 = generator.generate(10, DisorderLevel.HIGH);
        System.out.println("Inversion with clustered distribution: " + data2);

        // Create a generator with inversion strategy and biased distribution
        generator = new DataGenerator(
            new InversionStrategy(), new BiasedDistributionStrategy());

        // Generate data with a low disorder level
        List<Integer> data3 = generator.generate(10, DisorderLevel.LOW);
        System.out.println("Inversion with biased distribution: " + data3);
    }
}*/

/*package Main;

import generator.*;
import sorting.*;
import strategieDesordre.*;
import strategieRepartition.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe principale pour démontrer la fonctionnalité du générateur de données.
 
public class Main {
    public static void main(String[] args) {
        // Créer un générateur avec la stratégie d'inversion et la répartition uniforme
        DataGenerator generator = new DataGenerator(
            new InversionStrategy(), new UniformDistributionStrategy());

        // Générer des données avec un faible niveau de désordre
        List<Integer> data1 = generator.generate(10, DisorderLevel.LOW);
        System.out.println("Données générées (Inversion avec répartition uniforme) : " + data1);

        // Trier les données générées en utilisant l'InsertionSort
        int[] array1 = data1.stream().mapToInt(Integer::intValue).toArray();
        InsertionSort sorter = new InsertionSort();
        sorter.sort(array1);
        System.out.println("Données triées : " + 
            java.util.Arrays.stream(array1).boxed().collect(Collectors.toList()));
        System.out.println("Comparaisons : " + sorter.nbComparaisonCount());
        System.out.println("Échanges : " + sorter.nbEchangeCount());

        // Créer un générateur avec la stratégie d'inversion et la répartition groupée
        generator = new DataGenerator(
            new InversionStrategy(), new ClusteredDistributionStrategy());

        // Générer des données avec un niveau de désordre élevé
        List<Integer> data2 = generator.generate(10, DisorderLevel.HIGH);
        System.out.println("Données générées (Inversion avec répartition groupée) : " + data2);

        // Trier les données générées en utilisant l'InsertionSort
        int[] array2 = data2.stream().mapToInt(Integer::intValue).toArray();
        sorter = new InsertionSort();
        sorter.sort(array2);
        System.out.println("Données triées : " + 
            java.util.Arrays.stream(array2).boxed().collect(Collectors.toList()));
        System.out.println("Comparaisons : " + sorter.nbComparaisonCount());
        System.out.println("Échanges : " + sorter.nbEchangeCount());

        // Créer un générateur avec la stratégie d'inversion et la répartition biaisée
        generator = new DataGenerator(
            new InversionStrategy(), new BiasedDistributionStrategy());

        // Générer des données avec un faible niveau de désordre
        List<Integer> data3 = generator.generate(10, DisorderLevel.LOW);
        System.out.println("Données générées (Inversion avec répartition biaisée) : " + data3);

        // Trier les données générées en utilisant l'InsertionSort
        int[] array3 = data3.stream().mapToInt(Integer::intValue).toArray();
        sorter = new InsertionSort();
        sorter.sort(array3);
        System.out.println("Données triées : " + 
            java.util.Arrays.stream(array3).boxed().collect(Collectors.toList()));
        System.out.println("Comparaisons : " + sorter.nbComparaisonCount());
        System.out.println("Échanges : " + sorter.nbEchangeCount());
    }
}*/


package Main;

import generator.*;
import sorting.*;
import strategieDesordre.*;
import strategieRepartition.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe principale pour démontrer les différentes stratégies de génération de données.
 */
public class Main {
    public static void main(String[] args) {
        // Générer des données avec la stratégie PartialShuffle et répartition uniforme
        DataGenerator generator = new DataGenerator(
            new PartialShuffleStrategy(), new UniformDistributionStrategy());

        // Générer des données avec un niveau de désordre faible
        List<Integer> data1 = generator.generate(10, DisorderLevel.LOW);
        System.out.println("Données générées (Partial Shuffle avec répartition uniforme) : " + data1);

        // Trier les données générées avec InsertionSort
        int[] array1 = data1.stream().mapToInt(Integer::intValue).toArray();
        InsertionSort sorter = new InsertionSort();
        sorter.sort(array1);
        System.out.println("Données triées : " + 
            java.util.Arrays.stream(array1).boxed().collect(Collectors.toList()));
        System.out.println("Comparaisons : " + sorter.nbComparaisonCount());
        System.out.println("Échanges : " + sorter.nbEchangeCount());

        // Générer des données avec la stratégie PartialShuffle et répartition biaisée
        generator = new DataGenerator(
            new PartialShuffleStrategy(), new BiasedDistributionStrategy());

        // Générer des données avec un niveau de désordre élevé
        List<Integer> data2 = generator.generate(10, DisorderLevel.HIGH);
        System.out.println("Données générées (Partial Shuffle avec répartition biaisée) : " + data2);

        // Trier les données générées avec InsertionSort
        int[] array2 = data2.stream().mapToInt(Integer::intValue).toArray();
        sorter = new InsertionSort();
        sorter.sort(array2);
        System.out.println("Données triées : " + 
            java.util.Arrays.stream(array2).boxed().collect(Collectors.toList()));
        System.out.println("Comparaisons : " + sorter.nbComparaisonCount());
        System.out.println("Échanges : " + sorter.nbEchangeCount());

        // Générer des données avec la stratégie InversionStrategy et répartition uniforme
        generator = new DataGenerator(
            new InversionStrategy(), new UniformDistributionStrategy());

        // Générer des données avec un niveau de désordre moyen
        List<Integer> data3 = generator.generate(10, DisorderLevel.MEDIUM);
        System.out.println("Données générées (Inversion avec répartition uniforme) : " + data3);

        // Trier les données générées avec InsertionSort
        int[] array3 = data3.stream().mapToInt(Integer::intValue).toArray();
        sorter = new InsertionSort();
        sorter.sort(array3);
        System.out.println("Données triées : " + 
            java.util.Arrays.stream(array3).boxed().collect(Collectors.toList()));
        System.out.println("Comparaisons : " + sorter.nbComparaisonCount());
        System.out.println("Échanges : " + sorter.nbEchangeCount());

        // Générer des données avec la stratégie InversionStrategy et répartition biaisée
        generator = new DataGenerator(
            new InversionStrategy(), new BiasedDistributionStrategy());

        // Générer des données avec un niveau de désordre faible
        List<Integer> data4 = generator.generate(10, DisorderLevel.LOW);
        System.out.println("Données générées (Inversion avec répartition biaisée) : " + data4);

        // Trier les données générées avec InsertionSort
        int[] array4 = data4.stream().mapToInt(Integer::intValue).toArray();
        sorter = new InsertionSort();
        sorter.sort(array4);
        System.out.println("Données triées : " + 
            java.util.Arrays.stream(array4).boxed().collect(Collectors.toList()));
        System.out.println("Comparaisons : " + sorter.nbComparaisonCount());
        System.out.println("Échanges : " + sorter.nbEchangeCount());
    }
}
