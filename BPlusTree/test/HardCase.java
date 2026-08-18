public class HardCase {

    public static void run() {

        System.out.println("\n > HARD CASE:\n");

        Tree tree = new Tree(5);

        int[] insertions = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125};

        for (int key : insertions) {
            tree.insert(key);
        }

        System.out.println("\n      -> Después de insertar: ");
        tree.printTree();
        tree.printLeaves();

        int[] deletions = {20, 85, 10, 60, 115, 35, 100, 5, 75, 45, 110, 25 };

        for (int key : deletions) {

            System.out.println("\n      -> Eliminando " + key + ": ");

            tree.delete(key);
            tree.printTree();
            tree.printLeaves();
        }
    }
}