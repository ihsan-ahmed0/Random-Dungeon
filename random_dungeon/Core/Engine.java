package random_dungeon.Core;

import random_dungeon.TileEngine.TERenderer;
import random_dungeon.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        KeyboardInput input = new KeyboardInput();
        while (input.possibleNextInput()) {
            char key_press = input.getNextKey();
            if (key_press == 'N') {
                String seed = "";
                double x = 0.3;
                while (input.possibleNextInput()) {
                    key_press = input.getNextKey();
                    if (key_press == 'S') {
                        Map new_map = new Map(Long.parseLong(seed));
                        new_map.spawnCharacter();
                        new_map.createWorld();
                        Game(input, new_map);
                    }
                    else {
                        seed += String.valueOf(key_press);
                        StdDraw.text(x, 0.5, String.valueOf(key_press));
                        x += 0.05;
                    }
                }
            }
            if (key_press == 'L') {
                Map new_map = Map.loadMap();
                new_map.createWorld();
                Game(input, new_map);
            }
            if (key_press == 'Q') {
                System.exit(0);
            }
        }
    }

    public void Game(KeyboardInput input, Map current_map) {
        while (input.possibleNextInput()) {
            char key_press = input.getNextKey();
            if (key_press == 'W') {
                current_map = Movement.move('W', current_map);
                current_map.createWorld();
                current_map.saveMap();
            }
            if (key_press == 'A') {
                current_map = Movement.move('A', current_map);
                current_map.createWorld();
                current_map.saveMap();
            }
            if (key_press == 'S') {
                current_map = Movement.move('S', current_map);
                current_map.createWorld();
                current_map.saveMap();
            }
            if (key_press == 'D') {
                current_map = Movement.move('D', current_map);
                current_map.createWorld();
                current_map.saveMap();
            }
            if (key_press == ':') {
                key_press = input.getNextKey();
                if (key_press == 'Q') {
                    current_map.saveMap();
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        Map new_map;
        if (!(input.charAt(0) == 'N' || input.charAt(0) == 'n')) {
            input = input.substring(1);
            new_map = Map.loadMap();
            //new_map.printMap();
            //System.out.println(new_map.getSeed());
        }
        else {
            if (!input.contains("S") && !input.contains("s")) throw new IllegalArgumentException("Please enter seed.");
            input = input.substring(1);
            String extracted_seed = "";
            while (input.charAt(0) != 'S' && input.charAt(0) != 's') {
                extracted_seed += input.substring(0, 1);
                input = input.substring(1);
            }
            input = input.substring(1);
            Long seed = Long.parseLong(extracted_seed);
            new_map = new Map(seed);
            new_map.spawnCharacter();
        }
        while (!input.equals("")) {
            new_map = Movement.move(input.charAt(0), new_map);
            input = input.substring(1);
        }
        TETile[][] world_grid = new_map.world;
        new_map.saveMap();
        //new_map.createWorld();
        return world_grid;
    }
}
