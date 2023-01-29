package random_dungeon.Core;

import edu.princeton.cs.introcs.StdDraw;

public class KeyboardInput {
    private static final boolean PRINT_TYPED_KEYS = false;
    public KeyboardInput() {
        StdDraw.text(0.5, 0.7, "The Menu");
        StdDraw.text(0.5, 0.6, "N: Create a new world.");
        StdDraw.text(0.5, 0.4, "L: Load a pre-existing world.");
        StdDraw.text(0.5, 0.3, "Q: Quit.");
    }

    public char getNextKey() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());
                if (PRINT_TYPED_KEYS) {
                    System.out.print(c);
                }
                return c;
            }
        }
    }

    public boolean possibleNextInput() {
        return true;
    }
}
