package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {
    @Test
    void testSelect() {
        int[] arr = {10, 4, 5, 8, 6, 11, 26};
        int result = DeterministicSelect.select(arr, 3);
        assertEquals(8, result);
    }
}
