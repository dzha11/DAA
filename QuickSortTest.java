package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    void testQuickSort() {
        int[] arr = {5, 2, 9, 1, 3};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 9}, arr);
    }
}
