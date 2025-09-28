package algorithms;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {5, 2, 9, 1, 3};
        MergeSort.sort(arr1);
        System.out.println("MergeSort: " + Arrays.toString(arr1));

        int[] arr2 = {7, 4, 1, 9, 2};
        QuickSort.sort(arr2);
        System.out.println("QuickSort: " + Arrays.toString(arr2));

        int[] arr3 = {10, 4, 5, 8, 6, 11, 26};
        int k = 3;
        int kth = DeterministicSelect.select(arr3, k);
        System.out.println("k=" + k + " element = " + kth);

        Point[] points = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };
        double minDist = ClosestPair.closest(points);
        System.out.println("Closest Pair distance: " + minDist);
    }
}
