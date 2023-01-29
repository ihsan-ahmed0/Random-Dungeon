package random_dungeon.Core;

import random_dungeon.TileEngine.TERenderer;
import random_dungeon.TileEngine.TETile;
import random_dungeon.TileEngine.Tileset;
import java.io.*;
import java.util.Objects;
import java.util.Random;

public class Map implements Serializable {
    public static final int WIDTH = Engine.WIDTH; //75;
    public static final int HEIGHT = Engine.HEIGHT; //40;
    // Below I created a random seed generator just to test if the code works for various seeds.
    static Random seeder = new Random();
    public long SEED;  //seeder.nextInt(99999);45653;
    public Random rand;
    public final TERenderer ter = new TERenderer();
    public TETile[][] world = new TETile[WIDTH][HEIGHT];
    public Point character;

//    public static void main(String[] args) throws InterruptedException {
//        generateWorld();
//    }

    public Map(long seed_val) {
        SEED = seed_val;
        rand = new Random(SEED);
        generateWorld(rand, ter, world);
    }

    public static void generateWorld(Random rand, TERenderer ter, TETile[][] world) {
        blankWorld(world);

        Room new_room = new Room(rand, world);
        new_room.generate_room(world);

        int l_or_r = RandomUtils.uniform(rand, 0, 2);
        if (l_or_r == 0) {
            generateWorld(new_room.door_left, "left", rand, world);
            generateWorld(new_room.door_right, "right", rand, world);
        }
        else {
            generateWorld(new_room.door_right, "right", rand, world);
            generateWorld(new_room.door_left, "left", rand, world);
        }

        int u_or_d = RandomUtils.uniform(rand, 0, 2);
        if (u_or_d == 0) {
            generateWorld(new_room.door_up, "up", rand, world);
            generateWorld(new_room.door_down, "down", rand, world);
        }
        else {
            generateWorld(new_room.door_down, "down", rand, world);
            generateWorld(new_room.door_up, "up", rand, world);
        }
    }

    public void createWorld() {
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world);
    }

    public static void blankWorld(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WATER;
            }
        }
    }

    public static void generateWorld(Point p, String direction, Random rand, TETile[][] world) {
        if (direction.equals("left")) {
            if (p.getX() - 1 < 0 || !SafeToGenerateHall(p, "left", world)) {
                wall(p, world);
            }
            else {
                Point left = Hallway.StepLeft(p, rand, world);
                if (left.getX() - 5 < 0 || !SafeToGenerate(left, "left", world)) {
                    wall(left, world);
                }
                else {
                    createRoom(left, "left", rand, world);
                }
            }
        }
        else if (direction.equals("right")) {
            if (p.getX() + 1 > WIDTH - 1 || !SafeToGenerateHall(p, "right", world)) {
                wall(p, world);
            }
            else {
                Point right = Hallway.StepRight(p, rand, world);
                if (right.getX() + 5 > WIDTH - 1 || !SafeToGenerate(right, "right", world)) {
                    wall(right, world);
                }
                else {
                    createRoom(right, "right", rand, world);
                }
            }
        }
        else if (direction.equals("up")) {
            if (p.getY() + 1 > HEIGHT - 1 || !SafeToGenerateHall(p, "up", world)) {
                wall(p, world);
            }
            else {
                Point up = Hallway.StepUp(p, rand, world);
                if (up.getY() + 5 > HEIGHT - 1 || !SafeToGenerate(up, "up", world)) {
                    wall(up, world);
                }
                else {
                    createRoom(up, "up", rand, world);
                }
            }
        }
        else if (direction.equals("down")) {
            if (p.getY() - 1 < 0 || !SafeToGenerateHall(p, "down", world)) {
                wall(p, world);
            }
            else {
                Point down = Hallway.StepDown(p, rand, world);
                if (down.getY() - 5 < 0 || !SafeToGenerate(down, "down", world)) {
                    wall(down, world);
                }
                else {
                    createRoom(down, "down", rand, world);
                }
            }
        }
    }

    public static void createRoom(Point reference, String direction, Random rand, TETile[][] world) {
        if (direction.equals("left")) {
            boolean collide;
            Room new_room = new Room(reference, "left", rand, world);
            collide = new_room.colliding;
            while (collide) {
                new_room = new Room(reference, "left", rand, world);
                collide = new_room.colliding;
            }
            new_room.generate_room(world);

            int order = RandomUtils.uniform(rand, 0, 5);
            if (order == 0) {
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
            }
            else if (order == 1) {
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
            }
            else if (order == 2) {
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
            }
            else if (order == 3) {
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
            }
            else if (order == 4) {
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
            }
            else if (order == 5) {
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
            }
        }
        else if (direction.equals("right")) {
            boolean collide;
            Room new_room = new Room(reference, "right", rand, world);
            collide = new_room.colliding;
            while (collide) {
                new_room = new Room(reference, "right", rand, world);
                collide = new_room.colliding;
            }
            new_room.generate_room(world);

            int order = RandomUtils.uniform(rand, 0, 5);
            if (order == 0) {
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
            }
            else if (order == 1) {
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
            }
            else if (order == 2) {
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
            }
            else if (order == 3) {
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
            }
            else if (order == 4) {
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
            }
            else if (order == 5) {
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
            }
        }
        else if (direction.equals("up")) {
            boolean collide;
            Room new_room = new Room(reference, "up", rand, world);
            collide = new_room.colliding;
            while (collide) {
                new_room = new Room(reference, "up", rand, world);
                collide = new_room.colliding;
            }
            new_room.generate_room(world);

            int order = RandomUtils.uniform(rand, 0, 5);
            if (order == 0) {
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
            }
            else if (order == 1) {
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
            }
            else if (order == 2) {
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
            }
            else if (order == 3) {
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
            }
            else if (order == 4) {
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
            }
            else if (order == 5) {
                generateWorld(new_room.door_up, "up", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
            }
        }
        else if (direction.equals("down")) {
            boolean collide;
            Room new_room = new Room(reference, "down", rand, world);
            collide = new_room.colliding;
            while (collide) {
                new_room = new Room(reference, "down", rand, world);
                collide = new_room.colliding;
            }
            new_room.generate_room(world);

            int order = RandomUtils.uniform(rand, 0, 5);
            if (order == 0) {
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
            }
            else if (order == 1) {
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
            }
            else if (order == 2) {
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
            }
            else if (order == 3) {
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
            }
            else if (order == 4) {
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
            }
            else if (order == 5) {
                generateWorld(new_room.door_down, "down", rand, world);
                generateWorld(new_room.door_right, "right", rand, world);
                generateWorld(new_room.door_left, "left", rand, world);
            }
        }
    }

    private static boolean SafeToGenerateHall(Point reference, String direction, TETile[][] world) {
        if (direction.equals("left")) {
            for (int i = reference.getX() - 1; i <= reference.getX() - 1; i++) {
                for (int j = reference.getY() - 1; j <= reference.getY() + 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        return false;
                    }
                }
            }
        }
        else if (direction.equals("right")) {
            for (int i = reference.getX() + 1; i <= reference.getX() + 1; i++) {
                for (int j = reference.getY() - 1; j <= reference.getY() + 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        return false;
                    }
                }
            }
        }
        else if (direction.equals("up")) {
            for (int i = reference.getX() - 1; i <= reference.getX() + 1; i++) {
                for (int j = reference.getY() + 1; j <= reference.getY() + 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        return false;
                    }
                }
            }
        }
        else if (direction.equals("down")) {
            for (int i = reference.getX() - 1; i <= reference.getX() + 1; i++) {
                for (int j = reference.getY() - 1; j <= reference.getY() - 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean SafeToGenerate(Point reference, String direction, TETile[][] world) {
        if (direction.equals("left")) {
            for (int i = reference.getX() - 5; i <= reference.getX() - 1; i++) {
                for (int j = reference.getY() - 2; j <= reference.getY() + 2; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        return false;
                    }
                }
            }
        }
        else if (direction.equals("right")) {
            for (int i = reference.getX() + 1; i <= reference.getX() + 5; i++) {
                for (int j = reference.getY() - 2; j <= reference.getY() + 2; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        return false;
                    }
                }
            }
        }
        else if (direction.equals("up")) {
            for (int i = reference.getX() - 2; i <= reference.getX() + 2; i++) {
                for (int j = reference.getY() + 1; j <= reference.getY() + 5; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        return false;
                    }
                }
            }
        }
        else if (direction.equals("down")) {
            for (int i = reference.getX() - 2; i <= reference.getX() + 2; i++) {
                for (int j = reference.getY() - 5; j <= reference.getY() - 1; j++) {
                    if (!world[i][j].equals(Tileset.WATER)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void spawnCharacter() {
        boolean colliding = false;
        int x = RandomUtils.uniform(rand, 0, WIDTH);
        int y = RandomUtils.uniform(rand, 0, HEIGHT);
        if (world[x][y] != Tileset.FLOOR) {
            colliding = true;
        }
        while(colliding) {
            colliding = false;
            x = RandomUtils.uniform(rand, 0, WIDTH);
            y = RandomUtils.uniform(rand, 0, HEIGHT);
            if (world[x][y] != Tileset.FLOOR) {
                colliding = true;
            }
        }
        character = new Point(x, y);
        spawn(character, world);
    }

    public void saveMap() {
        File map_file = new File("save_state.txt");
        try {
            if (map_file.exists()) {
                map_file.delete();
                map_file.createNewFile();
            }
            else {
                map_file.createNewFile();
            }
            ObjectOutputStream map_output = new ObjectOutputStream(new FileOutputStream(map_file));
            map_output.writeObject(this);
            map_output.close();
        } catch (IOException ignored) {
        }
    }

    public static Map loadMap() {
        Map new_map;
        File map_file = new File("save_state.txt");
        try {
            ObjectInputStream map_input = new ObjectInputStream(new FileInputStream(map_file));
            new_map = (Map) map_input.readObject();
            map_input.close();
        } catch (IOException | ClassCastException | ClassNotFoundException FileNotFoundException) {
            System.out.println("Map not found!");
            new_map = null;
            System.exit(0);
        }
        return new_map;
    }

    public void printMap() {
        for (int y = 0; y < HEIGHT; y += 1) {
            for (int x = 0; x < WIDTH; x += 1) {
                if (Objects.equals(world[x][y].description(), "water")) {
                    if (x == WIDTH - 1) {
                        System.out.println("≈");
                    }
                    else {
                        System.out.print("≈");
                    }
                }
                else if (Objects.equals(world[x][y].description(), "wall")) {
                    if (x == WIDTH - 1) {
                        System.out.println("#");
                    }
                    else {
                        System.out.print("#");
                    }
                }
                else if (Objects.equals(world[x][y].description(), "floor")) {
                    if (x == WIDTH - 1) {
                        System.out.println("·");
                    }
                    else {
                        System.out.print("·");
                    }
                }
                else if (Objects.equals(world[x][y].description(), "you")) {
                    if (x == WIDTH - 1) {
                        System.out.println("@");
                    }
                    else {
                        System.out.print("@");
                    }
                }
            }
        }
    }

    public static void spawn(Point p, TETile[][] world) {  world[p.getX()][p.getY()] = Tileset.AVATAR; }

    public static void floor(int x, int y, TETile[][] world) {  world[x][y] = Tileset.FLOOR; }

    public static void floor(Point p, TETile[][] world) {  world[p.getX()][p.getY()] = Tileset.FLOOR; }

    public static void wall(int x, int y, TETile[][] world) {  world[x][y] = Tileset.WALL; }

    public static void wall(Point p, TETile[][] world) {  world[p.getX()][p.getY()] = Tileset.WALL; }

    public static int getWidth() {return WIDTH;}

    public static int getHeight() {return HEIGHT;}

    public Random getRandom() {return rand;}

    public long getSeed() {return SEED;}

}
