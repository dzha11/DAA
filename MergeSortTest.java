package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    void testMergeSort() {
        int[] arr = {5, 2, 9, 1, 3};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 9}, arr);
    }
}
