package algorithms;

public class MergeSort {
    public static void sort(int[] arr, Metrics m) {
        m.enterRecursion();
        mergeSort(arr, 0, arr.length - 1, m);
        m.exitRecursion();
    }

    private static void mergeSort(int[] arr, int l, int r, Metrics m) {
        if (l >= r) return;
        int mid = (l + r) / 2;
        m.enterRecursion();
        mergeSort(arr, l, mid, m);
        mergeSort(arr, mid + 1, r, m);
        merge(arr, l, mid, r, m);
        m.exitRecursion();
    }

    private static void merge(int[] arr, int l, int mid, int r, Metrics m) {
        int n1 = mid - l + 1;
        int n2 = r - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        m.incAllocations(); m.incAllocations();

        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            m.incComparisons();
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}
