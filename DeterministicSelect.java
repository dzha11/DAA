package algorithms;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) throw new IllegalArgumentException("wrong k");
        return selectHelper(arr, 0, arr.length - 1, k);
    }

    private static int selectHelper(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return selectHelper(arr, left, pivotIndex - 1, k);
        else return selectHelper(arr, pivotIndex + 1, right, k);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, store, i);
                store++;
            }
        }
        swap(arr, store, right);
        return store;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        if (right - left < 5) {
            insertionSort(arr, left, right);
            return (left + right) / 2;
        }
        int subRight = left;
        for (int i = left; i <= right; i += 5) {
            int subEnd = Math.min(i + 4, right);
            insertionSort(arr, i, subEnd);
            int median = (i + subEnd) / 2;
            swap(arr, median, subRight);
            subRight++;
        }
        return medianOfMedians(arr, left, subRight - 1);
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
