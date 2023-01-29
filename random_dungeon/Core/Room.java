package random_dungeon.Core;

import random_dungeon.TileEngine.TETile;
import random_dungeon.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private final int map_width = Map.WIDTH;
    private final int map_height = Map.HEIGHT;
//    private final static Random rand = Map.rand;

    private int width;
    private int height;
    private Point left_bot;
    private Point right_top;
    Point door_left;
    Point door_right;
    Point door_up;
    Point door_down;
    public Point center;
    public List<Point> room_dim = new ArrayList<Point>();
    public static List<Point> hallways = new ArrayList<Point>();

    // value is set to false by default, but will be set to true if a collision is found.
    public boolean colliding = false;


    public Room(Random rand, TETile[][] world) {
        this.width = RandomUtils.uniform(rand, 4, 10);
        this.height = RandomUtils.uniform(rand, 4, 10);

        // getting dimensions of the room
        this.left_bot = new Point(RandomUtils.uniform(rand, 0, map_width - 10), RandomUtils.uniform(rand, 0, map_height - 10));
        this.right_top = new Point(left_bot.getX() + width, left_bot.getY() + height);

        this.room_dim.add(left_bot);
        this.room_dim.add(right_top);

         int center_x = (left_bot.getX() + right_top.getX()) / 2;
         int center_y = (left_bot.getY() + right_top.getY()) / 2;
         center = new Point(center_x, center_y);
         door_left = new Point(left_bot.getX(), center_y);
         door_right = new Point(right_top.getX(), center_y);
         door_up = new Point(center_x, right_top.getY());
         door_down = new Point(center_x, left_bot.getY());


        /*
         * Here, we will iterate over every tile that this room will occupy. We will check if each
         * tile is a floor, wall, or if it has neighbors that are floors/walls. If any of these are
         * true for any tile, then the colliding boolean value will be set back to true, and we will
         * break out of the iteration. This will cause the parameters of the room to be reset.
         *
         * Obviously, checking for the status of neighboring tiles isn't completely necessary, but
         * I decided to do so in order to have at least a single tile of space between each room.
         *
         */
        for (int i = left_bot.getX(); i <= right_top.getX(); i++) {
            for (int j = left_bot.getY(); j <= right_top.getY(); j++) {
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

    public Room(Point p, String direction, Random rand, TETile[][] world) {
        this.width = RandomUtils.uniform(rand, 4, 10);
        this.height = RandomUtils.uniform(rand, 4, 10);
        if (direction.equals("left")) {
            this.left_bot = new Point(p.getX() - width - 1, p.getY() - (height / 2));
            this.right_top = new Point(left_bot.getX() + width, left_bot.getY() + height);

            int center_x = (left_bot.getX() + right_top.getX()) / 2;
            int center_y = (left_bot.getY() + right_top.getY()) / 2;

            center = new Point(center_x, center_y);

            door_left = new Point(left_bot.getX(), center_y);
            door_right = new Point(right_top.getX(), center_y);
            door_up = new Point(center_x, right_top.getY());
            door_down = new Point(center_x, left_bot.getY());

            if (left_bot.getX() < 0 || right_top.getY() > map_height - 1 || left_bot.getY() < 0) {
                colliding = true;
            }
            else {
                for (int i = left_bot.getX(); i <= right_top.getX(); i++) {
                    for (int j = left_bot.getY(); j <= right_top.getY(); j++) {
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
        else if (direction.equals("right")) {
            this.left_bot = new Point(p.getX() + 1, p.getY() - (height / 2));
            this.right_top = new Point(left_bot.getX() + width, left_bot.getY() + height);

            int center_x = (left_bot.getX() + right_top.getX()) / 2;
            int center_y = (left_bot.getY() + right_top.getY()) / 2;

            center = new Point(center_x, center_y);

            door_left = new Point(left_bot.getX(), center_y);
            door_right = new Point(right_top.getX(), center_y);
            door_up = new Point(center_x, right_top.getY());
            door_down = new Point(center_x, left_bot.getY());

            if (right_top.getX() > map_width - 1 || right_top.getY() > map_height - 1 || left_bot.getY() < 0) {
                colliding = true;
            }
            else {
                for (int i = left_bot.getX(); i <= right_top.getX(); i++) {
                    for (int j = left_bot.getY(); j <= right_top.getY(); j++) {
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
        else if (direction.equals("up")) {
            this.left_bot = new Point(p.getX() - (width / 2), p.getY() + 1);
            this.right_top = new Point(left_bot.getX() + width, left_bot.getY() + height);

            int center_x = (left_bot.getX() + right_top.getX()) / 2;
            int center_y = (left_bot.getY() + right_top.getY()) / 2;

            center = new Point(center_x, center_y);

            door_left = new Point(left_bot.getX(), center_y);
            door_right = new Point(right_top.getX(), center_y);
            door_up = new Point(center_x, right_top.getY());
            door_down = new Point(center_x, left_bot.getY());

            if (left_bot.getX() < 0 || right_top.getY() > map_height - 1 || right_top.getX() > map_width - 1) {
                colliding = true;
            }
            else {
                for (int i = left_bot.getX(); i <= right_top.getX(); i++) {
                    for (int j = left_bot.getY(); j <= right_top.getY(); j++) {
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
        else if (direction.equals("down")) {
            this.left_bot = new Point(p.getX() - (width / 2), p.getY() - height - 1);
            this.right_top = new Point(left_bot.getX() + width, left_bot.getY() + height);

            int center_x = (left_bot.getX() + right_top.getX()) / 2;
            int center_y = (left_bot.getY() + right_top.getY()) / 2;

            center = new Point(center_x, center_y);

            door_left = new Point(left_bot.getX(), center_y);
            door_right = new Point(right_top.getX(), center_y);
            door_up = new Point(center_x, right_top.getY());
            door_down = new Point(center_x, left_bot.getY());

            if (left_bot.getX() < 0 || left_bot.getY() < 0 || right_top.getX() > map_width - 1) {
                colliding = true;
            }
            else {
                for (int i = left_bot.getX(); i <= right_top.getX(); i++) {
                    for (int j = left_bot.getY(); j <= right_top.getY(); j++) {
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
    }

    public void generate_room(TETile[][] world) {

        for (int x = left_bot.getX(); x <= right_top.getX(); x++) {
            world[x][left_bot.getY()] = Tileset.WALL;
            world[x][right_top.getY()] = Tileset.WALL;
        }
        for (int y = left_bot.getY(); y <= right_top.getY(); y++) {
            world[left_bot.getX()][y] = Tileset.WALL;
            world[right_top.getX()][y] = Tileset.WALL;
        }
        for (int x = left_bot.getX() + 1; x <= right_top.getX() - 1; x++) {
            for (int y = left_bot.getY() + 1; y <= right_top.getY() - 1; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
        //world[center.getX()][center.getY()] = Tileset.FLOWER;
        Map.floor(door_left, world);
        Map.floor(door_right, world);
        Map.floor(door_up, world);
        Map.floor(door_down, world);
    }

}
