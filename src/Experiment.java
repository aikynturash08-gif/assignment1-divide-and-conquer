import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Experiment {
    private static final Random random = new Random();

    public static void runExperiments() {
        int[] sizes = {100, 1000, 10000, 50000};

        try (PrintWriter writer = new PrintWriter(new FileWriter("results/results.csv"))) {
            writer.println("Algorithm,InputType,Size,TimeNs,MaxRecursionDepth,Comparisons");

            for (int size : sizes) {
                int[] randomArr = generateRandomArray(size);
                testSortingAlgorithms(writer, randomArr, "Random", size);

                int[] sortedArr = randomArr.clone();
                Arrays.sort(sortedArr);
                testSortingAlgorithms(writer, sortedArr, "Sorted", size);

                int[] reverseArr = generateReverseSortedArray(size);
                testSortingAlgorithms(writer, reverseArr, "Reverse-Sorted", size);

                int[] duplicateArr = generateDuplicateHeavyArray(size);
                testSortingAlgorithms(writer, duplicateArr, "Duplicate-Heavy", size);
            }
            System.out.println("Эксперименты завершены. Данные сохранены в results/results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testSortingAlgorithms(PrintWriter writer, int[] baseArr, String inputType, int size) {
        MergeSorter mergeSorter = new MergeSorter();
        int[] arrCopy1 = baseArr.clone();
        long start = System.nanoTime();
        mergeSorter.sort(arrCopy1);
        long end = System.nanoTime();
        writer.printf("MergeSort,%s,%d,%d,%d,%d\n", inputType, size, (end - start), 0, mergeSorter.getComparisons());

        QuickSorter quickSorter = new QuickSorter();
        int[] arrCopy2 = baseArr.clone();
        start = System.nanoTime();
        quickSorter.sort(arrCopy2);
        end = System.nanoTime();
        writer.printf("QuickSort,%s,%d,%d,%d,%d\n", inputType, size, (end - start), quickSorter.getMaxRecursionDepth(), quickSorter.getComparisons());
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = random.nextInt(100000);
        return arr;
    }

    private static int[] generateReverseSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        Arrays.sort(arr);
        for (int i = 0; i < size / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[size - 1 - i];
            arr[size - 1 - i] = temp;
        }
        return arr;
    }

    private static int[] generateDuplicateHeavyArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = random.nextInt(5);
        return arr;
    }
}