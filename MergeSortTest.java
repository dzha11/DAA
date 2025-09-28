package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.Arrays;

public class MergeSortTest {

    @Test
    void simpleSort() {
        int[] arr = {9, 7, 5, 3, 1};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1,3,5,7,9}, arr);
    }

    @Test
    void randomLots() {
        Random rnd = new Random(123);
        for (int t = 0; t < 50; t++) {
            int n = 100;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(500);
            int[] expected = a.clone();
            Arrays.sort(expected);
            MergeSort.sort(a);
            assertArrayEquals(expected, a);
        }
    }

    @Test
    void duplicatesAndSmall() {
        int[] arr = {2,2,2,1,1,3,2};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1,1,2,2,2,2,3}, arr);
    }
}
