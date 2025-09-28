package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class ClosestPairTest {

    @Test
    void tinyExample() {
        Point[] pts = { new Point(0,0), new Point(1,1), new Point(3,4) };
        double res = ClosestPair.closest(pts);
        assertEquals(Math.sqrt(2), res, 1e-6);
    }

    @Test
    void compareBruteForceOnRandomSmall() {
        Random rnd = new Random(7);
        for (int t = 0; t < 30; t++) {
            int n = 200; // small enough for brute
            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) pts[i] = new Point(rnd.nextDouble()*1000, rnd.nextDouble()*1000);
            double fast = ClosestPair.closest(pts);
            double brute = bruteForce(pts);
            assertEquals(brute, fast, 1e-6);
        }
    }

    private double bruteForce(Point[] p) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < p.length; i++) for (int j = i+1; j < p.length; j++)
            best = Math.min(best, Math.hypot(p[i].x - p[j].x, p[i].y - p[j].y));
        return best;
    }
}
