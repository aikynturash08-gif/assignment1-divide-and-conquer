public class MergeSorter {

    private static long comparisons = 0;
    private static int maxRecursionDepth = 0;

    public static void sort(int[] arr) {
        reset();
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        sort(arr, temp, 0, arr.length - 1, 1);
    }

    private static void sort(int[] arr, int[] temp, int left, int right, int currentDepth) {
        if (currentDepth > maxRecursionDepth) {
            maxRecursionDepth = currentDepth;
        }

        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, temp, left, mid, currentDepth + 1);
            sort(arr, temp, mid + 1, right, currentDepth + 1);
            merge(arr, temp, left, mid, right);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            comparisons++;
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            arr[k] = temp[i];
            k++;
            i++;
        }
    }

    public static void reset() {
        comparisons = 0;
        maxRecursionDepth = 0;
    }

    public static long getComparisons() {
        return comparisons;
    }

    public static int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
}