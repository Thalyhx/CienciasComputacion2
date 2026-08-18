public class SimpleCase {

    public static void run() {

        System.out.println("\n > SIMPLE CASE:\n");

        Tree tree = new Tree(3);

        int[] insertions = {10, 20, 30, 40, 50, 60};

        for (int key : insertions) {
            tree.insert(key);
        }

        System.out.println("\n      -> Después de insertar:");
        tree.printTree();
        tree.printLeaves();

        int[] deletions = {
            20, 40, 60
        };

        for (int key : deletions) {

            System.out.println("\n      -> Eliminando " + key + ": ");

            tree.delete(key);

            tree.printTree();
            tree.printLeaves();
        }
    }
}