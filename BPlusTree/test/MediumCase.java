public class MediumCase {

    public static void run() {

        System.out.println("\n > MEDIUM CASE:\n");

        Tree tree = new Tree(4);

        int[] insertions = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};

        for (int key : insertions) {
            tree.insert(key);
        }

        System.out.println("\n      -> Después de insertar: ");
        tree.printTree();
        tree.printLeaves();

        int[] deletions = {40, 90, 20, 70, 100, 50};

        for (int key : deletions) {

            System.out.println("\n      -> Eliminando " + key + ": ");

            tree.delete(key);

            tree.printTree();
            tree.printLeaves();
        }
    }
}