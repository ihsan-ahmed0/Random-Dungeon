package random_dungeon.Core;

public class MappingTest {
    public static void main(String[] args) {
        String[] test_seed = {"-s", "N1234S"};
        Main.main(test_seed);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main.main(test_seed);
    }
}
