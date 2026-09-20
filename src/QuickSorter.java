import java.util.Random;

public class QuickSorter {
    private static final Random random = new Random();
    private int maxRecursionDepth = 0;
    private long comparisons = 0;

    public void sort(int[] a) {
        maxRecursionDepth = 0;
        comparisons = 0;
        sort(a, 0, a.length - 1, 1);
    }

    private void sort(int[] a, int low, int high, int currentDepth) {
        maxRecursionDepth = Math.max(maxRecursionDepth, currentDepth);

        while (low < high) {
            int pivotIndex = low + random.nextInt(high - low + 1);
            swap(a, pivotIndex, high);

            int p = partition(a, low, high);

            if (p - low < high - p) {
                sort(a, low, p - 1, currentDepth + 1);
                low = p + 1;
            } else {
                sort(a, p + 1, high, currentDepth + 1);
                high = p - 1;
            }
        }
    }

    private int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons++;
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public long getComparisons() {
        return comparisons;
    }
}