package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSelect() {
        int[] arr = {7, 2, 9, 1, 5};
        int result = DeterministicSelect.select(arr, 2); // третий по возрастанию
        assertEquals(5, result);
    }
}
