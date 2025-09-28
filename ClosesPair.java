package algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double closest(Point[] points) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        return closestRec(sortedByX);
    }

    private static double closestRec(Point[] points) {
        int n = points.length;
        if (n <= 3) return bruteForce(points);

        int mid = n / 2;
        Point midPoint = points[mid];

        double d1 = closestRec(Arrays.copyOfRange(points, 0, mid));
        double d2 = closestRec(Arrays.copyOfRange(points, mid, n));
        double d = Math.min(d1, d2);

        Point[] strip = Arrays.stream(points)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .sorted(Comparator.comparingDouble(p -> p.y))
                .toArray(Point[]::new);

        return Math.min(d, stripClosest(strip, d));
    }

    private static double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                double dist = distance(strip[i], strip[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y));
    }
}
