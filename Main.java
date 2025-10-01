package algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Metrics metrics = new Metrics();
        Random rnd = new Random();

        try (FileWriter out = new FileWriter("results.csv")) {
            out.write("algo,n,size,comparisons,allocations,maxDepth\n");

            // MergeSort
            for (int n = 100; n <= 1000; n += 100) {
                int[] arr = rnd.ints(n, -1000, 1000).toArray();
                metrics.reset();
                MergeSort.sort(arr, metrics);
                out.write("mergesort," + n + "," + metrics.getComparisons() + "," +
                        metrics.getAllocations() + "," + metrics.getMaxDepth() + "\n");
            }

            // QuickSort
            for (int n = 100; n <= 1000; n += 100) {
                int[] arr = rnd.ints(n, -1000, 1000).toArray();
                metrics.reset();
                QuickSort.sort(arr, metrics);
                out.write("quicksort," + n + "," + metrics.getComparisons() + "," +
                        metrics.getAllocations() + "," + metrics.getMaxDepth() + "\n");
            }

            // DeterministicSelect
            for (int n = 100; n <= 1000; n += 100) {
                int[] arr = rnd.ints(n, -1000, 1000).toArray();
                int k = rnd.nextInt(n);
                metrics.reset();
                DeterministicSelect.select(arr, k, metrics);
                out.write("select," + n + "," + metrics.getComparisons() + "," +
                        metrics.getAllocations() + "," + metrics.getMaxDepth() + "\n");
            }

            // ClosestPair
            for (int n = 100; n <= 1000; n += 100) {
                Point[] pts = new Point[n];
                for (int i = 0; i < n; i++)
                    pts[i] = new Point(rnd.nextDouble()*1000, rnd.nextDouble()*1000);
                metrics.reset();
                ClosestPair.closest(pts, metrics);
                out.write("closestpair," + n + "," + metrics.getComparisons() + "," +
                        metrics.getAllocations() + "," + metrics.getMaxDepth() + "\n");
            }
        }

        System.out.println("done! results written to results.csv");
    }
}
