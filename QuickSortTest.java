package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.Arrays;

public class QuickSortTest {

    @Test
    void simpleSort() {
        int[] arr = {7, 4, 6, 2, 8, 1};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1,2,4,6,7,8}, arr);
    }

    @Test
    void randomVsArraysSort() {
        Random rnd = new Random(99);
        for (int t = 0; t < 50; t++) {
            int n = 150;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1000) - 500;
            int[] exp = a.clone(); Arrays.sort(exp);
            QuickSort.sort(a);
            assertArrayEquals(exp, a);
        }
    }

    @Test
    void adversarialSortedInput() {
        int n = 200;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i; // already sorted
        QuickSort.sort(a); // should not blow stack or fail
        int[] exp = a.clone(); Arrays.sort(exp);
        assertArrayEquals(exp, a);
    }
}
