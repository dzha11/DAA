package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.Arrays;

public class DeterministicSelectTest {

    @Test
    void smallExample() {
        int[] a = {7,2,9,1,5};
        int res = DeterministicSelect.select(a.clone(), 2); // 3rd smallest (0-based)
        assertEquals(5, res);
    }

    @Test
    void compareWithSortManyRandoms() {
        Random rnd = new Random(42);
        for (int t = 0; t < 100; t++) {
            int n = 100;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1000);
            int k = rnd.nextInt(n);
            int[] sorted = a.clone();
            Arrays.sort(sorted);
            int expected = sorted[k];
            int got = DeterministicSelect.select(a.clone(), k);
            assertEquals(expected, got);
        }
    }
}
