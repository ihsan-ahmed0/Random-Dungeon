package random_dungeon.Core;

import random_dungeon.TileEngine.TETile;
import random_dungeon.TileEngine.Tileset;
import java.util.Random;

public class Hallway {

    public static Point StepRight(Point p, Random rand, TETile[][] world) {
        boolean colliding = false;
        int length = RandomUtils.uniform(rand, 0, 7);
        if (p.getX() + length + 1 > Map.WIDTH - 1) {
            colliding = true;
        }
        else {
            for (int i = p.getX() + 1; i <= p.getX() + length + 1; i++) {
                for (int j = p.getY() - 1; j <= p.getY() + 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        colliding = true;
                        break;
                    }
                }
                if (colliding) {
                    break;
                }
            }
        }
        while (colliding) {
            colliding = false;
            length = RandomUtils.uniform(rand, 0, 7);
            if (p.getX() + length + 1 > Map.WIDTH - 1) {
                colliding = true;
            }
            else {
                for (int i = p.getX() + 1; i <= p.getX() + length + 1; i++) {
                    for (int j = p.getY() - 1; j <= p.getY() + 1; j++) {
                        if (!world[i][j].equals(Tileset.WATER)) {
                            colliding = true;
                            break;
                        }
                    }
                    if (colliding) {
                        break;
                    }
                }
            }
        }
        Point new_point = new Point(p.getX(), p.getY());
        for (int i = 0; i <= length; i++) {
            new_point = new Point(new_point.getX() + 1, new_point.getY());
            Map.floor(new_point, world);
            Map.wall(new_point.getX(), new_point.getY() + 1, world);
            Map.wall(new_point.getX(), new_point.getY() - 1, world);
        }
        return new_point;
    }

    public static Point StepLeft(Point p, Random rand, TETile[][] world) {
        boolean colliding = false;
        int length = RandomUtils.uniform(rand, 0, 7);
        if (p.getX() - length - 1 < 0) {
            colliding = true;
        }
        else {
            for (int i = p.getX() - length - 1; i <= p.getX() - 1; i++) {
                for (int j = p.getY() - 1; j <= p.getY() + 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        colliding = true;
                        break;
                    }
                }
                if (colliding) {
                    break;
                }
            }
        }
        while (colliding) {
            colliding = false;
            length = RandomUtils.uniform(rand, 0, 7);
            if (p.getX() - length - 1 < 0) {
                colliding = true;
            }
            else {
                for (int i = p.getX() - length - 1; i <= p.getX() - 1; i++) {
                    for (int j = p.getY() - 1; j <= p.getY() + 1; j++) {
                        if (!world[i][j].equals(Tileset.WATER)) {
                            colliding = true;
                            break;
                        }
                    }
                    if (colliding) {
                        break;
                    }
                }
            }
        }
        Point new_point = new Point(p.getX(), p.getY());
        for (int i = 0; i <= length; i++) {
            new_point = new Point(new_point.getX() - 1, new_point.getY());
            Map.floor(new_point, world);
            Map.wall(new_point.getX(), new_point.getY() + 1, world);
            Map.wall(new_point.getX(), new_point.getY() - 1, world);
        }
        return new_point;
    }

    public static Point StepUp(Point p, Random rand, TETile[][] world) {
        boolean colliding = false;
        int length = RandomUtils.uniform(rand, 0, 7);
        if (p.getY() + length + 1 > Map.HEIGHT - 1) {
            colliding = true;
        }
        else {
            for (int i = p.getX() - 1; i <= p.getX() + 1; i++) {
                for (int j = p.getY() + 1; j <= p.getY() + length + 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        colliding = true;
                        break;
                    }
                }
                if (colliding) {
                    break;
                }
            }
        }
        while (colliding) {
            colliding = false;
            length = RandomUtils.uniform(rand, 0, 7);
            if (p.getY() + length + 1 > Map.HEIGHT - 1) {
                colliding = true;
            }
            else {
                for (int i = p.getX() - 1; i <= p.getX() + 1; i++) {
                    for (int j = p.getY() + 1; j <= p.getY() + length + 1; j++) {
                        if (!world[i][j].equals(Tileset.WATER)) {
                            colliding = true;
                            break;
                        }
                    }
                    if (colliding) {
                        break;
                    }
                }
            }
        }
        Point new_point = new Point(p.getX(), p.getY());
        for (int i = 0; i <= length; i++) {
            new_point = new Point(new_point.getX(), new_point.getY() + 1);
            Map.floor(new_point, world);
            Map.wall(new_point.getX() - 1, new_point.getY(), world);
            Map.wall(new_point.getX() + 1, new_point.getY(), world);
        }
        return new_point;
    }

    public static Point StepDown(Point p, Random rand, TETile[][] world) {
        boolean colliding = false;
        int length = RandomUtils.uniform(rand, 0, 7);
        if (p.getY() - length - 1 < 0) {
            colliding = true;
        }
        else {
            for (int i = p.getX() - 1; i <= p.getX() + 1; i++) {
                for (int j = p.getY() - length - 1; j <= p.getY() - 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        colliding = true;
                        break;
                    }
                }
                if (colliding) {
                    break;
                }
            }
        }
        while (colliding) {
            colliding = false;
            length = RandomUtils.uniform(rand, 0, 7);
            if (p.getY() - length - 1 < 0) {
                colliding = true;
            }
            else {
                for (int i = p.getX() - 1; i <= p.getX() + 1; i++) {
                    for (int j = p.getY() - length - 1; j <= p.getY() - 1; j++) {
                        if (!world[i][j].equals(Tileset.WATER)) {
                            colliding = true;
                            break;
                        }
                    }
                    if (colliding) {
                        break;
                    }
                }
            }
        }
        Point new_point = new Point(p.getX(), p.getY());
        for (int i = 0; i <= length; i++) {
            new_point = new Point(new_point.getX(), new_point.getY() - 1);
            Map.floor(new_point, world);
            Map.wall(new_point.getX() - 1, new_point.getY(), world);
            Map.wall(new_point.getX() + 1, new_point.getY(), world);
        }
        return new_point;
    }

    public boolean SafeToGenerateArea(Point left_bottom, Point right_top) {
        return false;
    }
}
