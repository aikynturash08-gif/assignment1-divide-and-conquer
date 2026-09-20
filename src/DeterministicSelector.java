import java.util.Arrays;

public class DeterministicSelector {

    public int select(int[] a, int k) {
        return select(a, 0, a.length - 1, k);
    }

    private int select(int[] a, int low, int high, int k) {
        if (low == high) {
            return a[low];
        }

        int pivot = medianOfMedians(a, low, high);
        int pivotIndex = partitionAroundPivot(a, low, high, pivot);

        if (k == pivotIndex) {
            return a[k];
        } else if (k < pivotIndex) {
            return select(a, low, pivotIndex - 1, k);
        } else {
            return select(a, pivotIndex + 1, high, k);
        }
    }

    private int medianOfMedians(int[] a, int low, int high) {
        int n = high - low + 1;
        if (n <= 5) {
            Arrays.sort(a, low, high + 1);
            return a[low + n / 2];
        }

        int numGroups = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int groupLow = low + i * 5;
            int groupHigh = Math.min(groupLow + 4, high);
            Arrays.sort(a, groupLow, groupHigh + 1);
            medians[i] = a[groupLow + (groupHigh - groupLow) / 2];
        }

        return select(medians, 0, numGroups - 1, numGroups / 2);
    }

    private int partitionAroundPivot(int[] a, int low, int high, int pivot) {
        for (int i = low; i <= high; i++) {
            if (a[i] == pivot) {
                swap(a, i, high);
                break;
            }
        }

        int i = low - 1;
        for (int j = low; j < high; j++) {
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
}