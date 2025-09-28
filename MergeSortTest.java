package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    void testMergeSort() {
        int[] arr = {9, 7, 5, 3, 1};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 3, 5, 7, 9}, arr);
    }
}
