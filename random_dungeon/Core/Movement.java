package random_dungeon.Core;

import java.util.Objects;

public class Movement {
    public static Map move(char key, Map map) {
        if (key == 'W' || key == 'w') {
            if (Objects.equals(map.world[map.character.getX()][map.character.getY() + 1].description(), "floor")) {
                map.character = new Point(map.character.getX(), map.character.getY() + 1);
                map.spawn(map.character, map.world);
                map.floor(map.character.getX(), map.character.getY() - 1, map.world);
            }
        }
        else if (key == 'A' || key == 'a') {
            if (Objects.equals(map.world[map.character.getX() - 1][map.character.getY()].description(), "floor")) {
                map.character = new Point(map.character.getX() - 1, map.character.getY());
                map.spawn(map.character, map.world);
                map.floor(map.character.getX() + 1, map.character.getY(), map.world);
            }
        }
        else if (key == 'S' || key == 's') {
            if (Objects.equals(map.world[map.character.getX()][map.character.getY() - 1].description(), "floor")) {
                map.character = new Point(map.character.getX(), map.character.getY() - 1);
                map.spawn(map.character, map.world);
                map.floor(map.character.getX(), map.character.getY() + 1, map.world);
            }
        }
        else if (key == 'D' || key == 'd') {
            if (Objects.equals(map.world[map.character.getX() + 1][map.character.getY()].description(), "floor")) {
                map.character = new Point(map.character.getX() + 1, map.character.getY());
                map.spawn(map.character, map.world);
                map.floor(map.character.getX() - 1, map.character.getY(), map.world);
            }
        }
        return map;
    }
}
