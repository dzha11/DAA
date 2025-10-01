package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {
    @Test
    void simpleSort() {
        int[] arr = {7, 4, 6, 2, 8, 1};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{1, 2, 4, 6, 7, 8}, arr);
    }

    @Test
    void randomVsArraysSort() {
        Random rnd = new Random(99);
        for (int t = 0; t < 30; t++) {
            int n = 150;
            int[] a = rnd.ints(n, -1000, 1000).toArray();
            int[] expected = a.clone();
            Arrays.sort(expected);
            QuickSort.sort(a, new Metrics());
            assertArrayEquals(expected, a);
        }
    }
}
