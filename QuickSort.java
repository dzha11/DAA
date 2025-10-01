package algorithms;

public class QuickSort {
    public static void sort(int[] arr, Metrics m) {
        quickSort(arr, 0, arr.length - 1, m);
    }

    private static void quickSort(int[] arr, int low, int high, Metrics m) {
        if (low < high) {
            m.enterRecursion();
            int pi = partition(arr, low, high, m);
            quickSort(arr, low, pi - 1, m);
            quickSort(arr, pi + 1, high, m);
            m.exitRecursion();
        }
    }

    private static int partition(int[] arr, int low, int high, Metrics m) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            m.incComparisons();
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = tmp;
        return i + 1;
    }
}
