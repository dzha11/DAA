package algorithms;

import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] arr, int k, Metrics m) {
        Arrays.sort(arr); // для упрощения: реальный алгоритм median-of-medians можно вставить позже
        return arr[k];
    }
}
