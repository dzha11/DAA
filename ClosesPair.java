package algorithms;

public class ClosestPair {
    public static double closest(Point[] pts, Metrics m) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                m.incComparisons();
                double d = Math.hypot(pts[i].x - pts[j].x, pts[i].y - pts[j].y);
                if (d < best) best = d;
            }
        }
        return best;
    }
}
