package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    void testQuickSort() {
        int[] arr = {7, 4, 1, 9, 2};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 4, 7, 9}, arr);
    }
}
