package algorithms;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {

        int[] arr1 = {5, 2, 9, 1, 6};
        System.out.println("Before MergeSort: " + Arrays.toString(arr1));
        MergeSort.sort(arr1);
        System.out.println("After MergeSort: " + Arrays.toString(arr1));

        int[] arr2 = {10, 7, 8, 9, 1, 5};
        System.out.println("\nBefore QuickSort: " + Arrays.toString(arr2));
        QuickSort.sort(arr2);
        System.out.println("After QuickSort: " + Arrays.toString(arr2));

        int[] arr3 = {12, 3, 5, 7, 4, 19, 26};
        int k = 2;
        int kthSmallest = DeterministicSelect.select(arr3, k);
        System.out.println("\n" + k + "-th smallest element is: " + kthSmallest);

        ClosestPair.Point[] points = {
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(12, 30),
                new ClosestPair.Point(40, 50),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(12, 10),
                new ClosestPair.Point(3, 4)
        };
        double minDist = ClosestPair.findClosestPair(points);
        System.out.println("\nThe smallest distance is: " + minDist);
    }
}
