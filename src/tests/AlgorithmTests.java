import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class AlgorithmTests {

    private final Random random = new Random(42); // Фиксированный seed для воспроизводимости

    // ==========================================
    // 1. Тесты для MergeSort
    // ==========================================
    @Test
    void testMergeSort() {
        int[] arr = {5, 2, 8, 1, 9, 3};
        int[] expected = {1, 2, 3, 5, 8, 9};

        // Вызов твоего метода MergeSort (измени название метода/класса под свой проект)
        MergeSort.sort(arr);

        assertArrayEquals(expected, arr, "MergeSort должен правильно сортировать неотсортированный массив");
    }

    @Test
    void testMergeSortAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr, "MergeSort должен корректно обрабатывать уже отсортированный массив");
    }

    // ==========================================
    // 2. Тесты для QuickSort
    // ==========================================
    @Test
    void testQuickSort() {
        int[] arr = {12, 4, 7, 3, 1, 15, 8};
        int[] expected = {1, 3, 4, 7, 8, 12, 15};

        QuickSort.sort(arr);

        assertArrayEquals(expected, arr, "QuickSort должен правильно сортировать массив");
    }

    @Test
    void testQuickSortWithDuplicates() {
        int[] arr = {4, 2, 4, 1, 2, 4, 3};
        int[] expected = {1, 2, 2, 3, 4, 4, 4};

        QuickSort.sort(arr);

        assertArrayEquals(expected, arr, "QuickSort должен корректно работать с дубликатами");
    }

    // ==========================================
    // 3. Тесты для Deterministic Select (Median-of-Medians)
    // ==========================================
    @Test
    void testDeterministicSelect() {
        int[] arr = {9, 1, 0, 2, 5, 7, 3, 4, 6, 8};

        // Поиск k-й порядковой статистики (0-indexed или 1-indexed в зависимости от твоей реализации)
        int k = 4; // 5-й наименьший элемент (индекс 4)
        int result = DeterministicSelect.select(arr, k);

        assertEquals(4, result, "Deterministic Select должен находить правильную k-ю статистику");
    }

    // ==========================================
    // 4. Тесты для Closest Pair of Points
    // ==========================================
    @Test
    void testClosestPair() {
        // Создание тестовых точек
        Point[] points = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };

        // Ближайшие точки: (2,3) и (3,4), расстояние = sqrt((3-2)^2 + (4-3)^2) = sqrt(2) ≈ 1.41421356
        double expectedDistance = Math.sqrt(2);
        double actualDistance = ClosestPair.findClosestPair(points);

        assertEquals(expectedDistance, actualDistance, 1e-6, "Closest Pair должен возвращать минимальное расстояние");
    }

    // ==========================================
    // 5. Стресс-тест на случайных данных
    // ==========================================
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

        Arrays.sort(arr1); // Эталонная сортировка из Java API
        QuickSort.sort(arr2);

        assertArrayEquals(arr1, arr2, "QuickSort должен совпадать с вердиктом Arrays.sort на случайном массиве");
    }
}