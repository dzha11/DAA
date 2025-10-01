package algorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MainBenchmark {

    public static void main(String[] args) throws IOException {
        int[] sizes = {100, 500, 1000}; // размеры массивов
        int trials = 3; // сколько раз повторяем для каждого размера
        Random rnd = new Random(42);

        File outDir = new File("csv");
        if (!outDir.exists()) outDir.mkdirs();

        File csv = new File(outDir, "results.csv");
        try (FileWriter fw = new FileWriter(csv)) {
            fw.write("algorithm,n,trial,time_ns,comparisons,allocations,max_depth\n");

            // --- mergesort ---
            for (int n : sizes) {
                for (int t = 1; t <= trials; t++) {
                    int[] arr = rnd.ints(n, -1000, 1000).toArray();
                    Metrics m = new Metrics();
                    long t0 = System.nanoTime();
                    MergeSort.sort(arr, m); // твой mergesort
                    long t1 = System.nanoTime();
                    fw.write("mergesort," + n + "," + t + "," + (t1 - t0) + "," +
                            m.getComparisons() + "," + m.getAllocations() + "," + m.getMaxDepth() + "\n");
                }
            }

            // --- quicksort ---
            for (int n : sizes) {
                for (int t = 1; t <= trials; t++) {
                    int[] arr = rnd.ints(n, -1000, 1000).toArray();
                    Metrics m = new Metrics();
                    long t0 = System.nanoTime();
                    QuickSort.sort(arr, m); // твой quicksort
                    long t1 = System.nanoTime();
                    fw.write("quicksort," + n + "," + t + "," + (t1 - t0) + "," +
                            m.getComparisons() + "," + m.getAllocations() + "," + m.getMaxDepth() + "\n");
                }
            }

            // --- deterministic select ---
            for (int n : sizes) {
                for (int t = 1; t <= trials; t++) {
                    int[] arr = rnd.ints(n, 0, 1000).toArray();
                    int k = rnd.nextInt(n);
                    Metrics m = new Metrics();
                    long t0 = System.nanoTime();
                    DeterministicSelect.select(arr, k, m); // твой select
                    long t1 = System.nanoTime();
                    fw.write("select," + n + "," + t + "," + (t1 - t0) + "," +
                            m.getComparisons() + "," + m.getAllocations() + "," + m.getMaxDepth() + "\n");
                }
            }

            // --- closest pair ---
            for (int n : sizes) {
                for (int t = 1; t <= trials; t++) {
                    Point[] pts = new Point[n];
                    for (int i = 0; i < n; i++) {
                        pts[i] = new Point(rnd.nextDouble(), rnd.nextDouble());
                    }
                    Metrics m = new Metrics();
                    long t0 = System.nanoTime();
                    ClosestPair.closest(pts, m); // твой closest pair
                    long t1 = System.nanoTime();
                    fw.write("closestpair," + n + "," + t + "," + (t1 - t0) + "," +
                            m.getComparisons() + "," + m.getAllocations() + "," + m.getMaxDepth() + "\n");
                }
            }
        }

        System.out.println("Готово! Результаты в csv/results.csv");
    }
}
