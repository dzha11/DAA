package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class DeterministicSelectTest {
    @Test
    void smallExample() {
        int[] arr = {7, 2, 9, 1, 5};
        int res = DeterministicSelect.select(arr.clone(), 2, new Metrics()); // 3-й элемент (0-based)
        assertEquals(5, res);
    }

    @Test
    void compareWithSortManyRandoms() {
        Random rnd = new Random(42);
        for (int t = 0; t < 50; t++) {
            int n = 100;
            int[] a = rnd.ints(n, 0, 1000).toArray();
            int k = rnd.nextInt(n);
            int[] expectedArr = a.clone();
            Arrays.sort(expectedArr);
            int expected = expectedArr[k];
            int got = DeterministicSelect.select(a.clone(), k, new Metrics());
            assertEquals(expected, got);
        }
    }
}
