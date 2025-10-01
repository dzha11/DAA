package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {
    @Test
    void simpleSort() {
        int[] arr = {9, 7, 5, 3, 1};
        MergeSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{1, 3, 5, 7, 9}, arr);
    }

    @Test
    void randomLots() {
        Random rnd = new Random(123);
        for (int t = 0; t < 30; t++) {
            int n = 100;
            int[] a = rnd.ints(n, -500, 500).toArray();
            int[] expected = a.clone();
            Arrays.sort(expected);
            MergeSort.sort(a, new Metrics());
            assertArrayEquals(expected, a);
        }
    }
}
