import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPairSolver {

    public double findClosestPair(Point[] points) {
        Point[] pointsSortedByX = points.clone();
        Arrays.sort(pointsSortedByX, Comparator.comparingDouble(p -> p.x));

        Point[] pointsSortedByY = points.clone();
        Arrays.sort(pointsSortedByY, Comparator.comparingDouble(p -> p.y));

        return closest(pointsSortedByX, pointsSortedByY, 0, points.length - 1);
    }

    private double closest(Point[] px, Point[] py, int low, int high) {
        int n = high - low + 1;
        if (n <= 3) {
            return bruteForce(px, low, high);
        }

        int mid = low + (high - low) / 2;
        Point midPoint = px[mid];

        Point[] pyl = new Point[mid - low + 1];
        Point[] pyr = new Point[high - mid];
        int li = 0, ri = 0;

        for (Point p : py) {
            if (p.x <= midPoint.x && li < pyl.length) {
                pyl[li++] = p;
            } else {
                pyr[ri++] = p;
            }
        }

        double d1 = closest(px, pyl, low, mid);
        double d2 = closest(px, pyr, mid + 1, high);
        double d = Math.min(d1, d2);

        List<Point> strip = new ArrayList<>();
        for (Point p : py) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                double dist = strip.get(i).distanceTo(strip.get(j));
                if (dist < d) {
                    d = dist;
                }
            }
        }

        return d;
    }

    public double bruteForce(Point[] points, int low, int high) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = low; i <= high; i++) {
            for (int j = i + 1; j <= high; j++) {
                double dist = points[i].distanceTo(points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }
}