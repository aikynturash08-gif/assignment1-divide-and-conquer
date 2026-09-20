import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;

public class AlgorithmTests {

    private final Random random = new Random(42);

    @Test
    void testMergeSort() {
        int[] arr = {5, 2, 8, 1, 9, 3};
        int[] expected = {1, 2, 3, 5, 8, 9};

        MergeSorter sorter = new MergeSorter();
        sorter.sort(arr);

        assertArrayEquals(expected, arr, "MergeSorter должен правильно сортировать неотсортированный массив");
    }

    @Test
    void testMergeSortAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        MergeSorter sorter = new MergeSorter();
        sorter.sort(arr);

        assertArrayEquals(expected, arr, "MergeSorter должен корректно обрабатывать уже отсортированный массив");
    }

    @Test
    void testQuickSort() {
        int[] arr = {12, 4, 7, 3, 1, 15, 8};
        int[] expected = {1, 3, 4, 7, 8, 12, 15};

        QuickSorter sorter = new QuickSorter();
        sorter.sort(arr);

        assertArrayEquals(expected, arr, "QuickSorter должен правильно сортировать массив");
    }

    @Test
    void testQuickSortWithDuplicates() {
        int[] arr = {4, 2, 4, 1, 2, 4, 3};
        int[] expected = {1, 2, 2, 3, 4, 4, 4};

        QuickSorter sorter = new QuickSorter();
        sorter.sort(arr);

        assertArrayEquals(expected, arr, "QuickSorter должен корректно работать с дубликатами");
    }


    @Test
    void testDeterministicSelect() {
        int[] arr = {9, 1, 0, 2, 5, 7, 3, 4, 6, 8};

        int k = 4;
        DeterministicSelector selector = new DeterministicSelector();
        int result = selector.select(arr, k);

        assertEquals(4, result, "DeterministicSelector должен находить правильную k-ю статистику");
    }

    @Test
    void testClosestPair() {
        Point[] points = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };

        double expectedDistance = Math.sqrt(2);
        ClosestPairSolver solver = new ClosestPairSolver();
        double actualDistance = solver.findClosestPair(points);

        assertEquals(expectedDistance, actualDistance, 1e-6, "ClosestPairSolver должен возвращать минимальное расстояние");
    }


    @Test
    void testStressRandomData() {
        int size = 1000;
        int[] arr1 = new int[size];
        int[] arr2 = new int[size];

        for (int i = 0; i < size; i++) {
            int val = random.nextInt(10000);
            arr1[i] = val;
            arr2[i] = val;
        }

        Arrays.sort(arr1);
        QuickSorter sorter = new QuickSorter();
        sorter.sort(arr2);

        assertArrayEquals(arr1, arr2, "QuickSorter должен совпадать с вердиктом Arrays.sort на случайном массиве");
    }
}