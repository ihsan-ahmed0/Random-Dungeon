package random_dungeon.Core;

import java.io.Serializable;
import java.util.List;

public class Point implements Serializable {

    private int x;
    private int y;

    public Point(int i, int i1) {
        this.x = i;
        this.y = i1;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public static int distance(Point p1, Point p2) {
        int a = Math.abs(p1.getX() - p2.getX());
        int b = Math.abs(p1.getY() - p2.getY());
        return (int) Math.sqrt(a-b);
    }

    public static void swap(Point p1, Point p2) {
        int temp_x = p1.x;
        int temp_y = p1.y;
        p1.x = p2.x;
        p1.y = p2.y;
        p2.x = temp_x;
        p2.y = temp_y;
    }

    public void addX(int i) {
        this.x += i;
    }

    public void addY(int i) {
        this.y += i;
    }

    public static Point closest_point(Point p1, List<Point> points) {
        Point closest_point = points.get(0);
        int closest_distance = distance(p1, closest_point);
        for (int i = 1; i< points.size(); i++) {
            if (distance(p1, points.get(i)) < closest_distance) {
                closest_point = points.get(i);
                closest_distance = distance(p1, points.get(i));
            }
        }
        return closest_point;
    }

}
