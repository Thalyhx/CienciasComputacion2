import java.util.List;

/**
 *
 * @author natha, luna
 */

public class Tree {
    
    private Node root;
    private int keyLimit;
    private int minKeys;

    public Tree(int order){
        this.root = new Node(true);
        this.keyLimit = order - 1;
        this.minKeys = (int) Math.ceil((double) (order - 1) / 2);
    }

    public Node searchLeaf(int key){
        Node actual = root;

        while (!actual.getIsLeaf()){
            int i = 0;
            while (i < actual.getKeys().size() && key >= actual.getKeys().get(i)){
                i++;
            }
            actual = actual.getChilds().get(i);
        }
        return actual;
    }

    public int searchPosition(List<Integer> keys, int key){
        int i = 0;
        while(i < keys.size() && keys.get(i) < key){
            i++;
        }
        return i;
    }

    // Insert:
    public void insert(int key){
        // If the node is root:
        if (root.getKeys().isEmpty()){
            root.getKeys().add(key);
        } else {
            Node leaf = searchLeaf(key);
            int position = searchPosition(leaf.getKeys(), key);

            leaf.getKeys().add(position, key);

            if (leaf.getKeys().size() > keyLimit){
                split(leaf);
            }
        }
    }

    public void split(Node node){

        int siz = node.getKeys().size();
        int mid = siz / 2;
        int rises = node.getKeys().get(mid);

        // Split
        Node rightNode = new Node(node.getIsLeaf());
        if (node.getIsLeaf()){
            rightNode.getKeys().addAll(node.getKeys().subList(mid, siz));
        } else {
            rightNode.getKeys().addAll(node.getKeys().subList(mid + 1, siz));
        }

        node.getKeys().subList(mid, siz).clear();

        // References
        if (!node.getIsLeaf()){
            int sizChilds = node.getChilds().size();
            rightNode.getChilds().addAll(node.getChilds().subList(mid + 1, sizChilds));
            node.getChilds().subList(mid + 1, sizChilds).clear();

            for (Node child : rightNode.getChilds()){
                child.setFather(rightNode);
            }
        }

        if (node.getIsLeaf()){
            rightNode.setPointer(node.getPointer());
            node.setPointer(rightNode);
        }

        // Root case
        if (node == root){
            Node newRoot = new Node(false);
            newRoot.getKeys().add(rises);
            newRoot.getChilds().add(node);
            newRoot.getChilds().add(rightNode);

            node.setFather(newRoot);
            rightNode.setFather(newRoot);
            root = newRoot;
        // Intermediate case
        } else {
            Node father = node.getFather();
            rightNode.setFather(father);

            int position = searchPosition(father.getKeys(), rises);
            father.getKeys().add(position, rises);
            father.getChilds().add(position + 1, rightNode);

            if (father.getKeys().size() > keyLimit){
                split(father);
            }
        }
    }
    
    private void updateCopy(Node current, int oldKey, int newKey) {
        
        //if is root
        if (current == null) {
            return;
        }   
        boolean updated = false;
        
        //Search the old key
        for (int i = 0; i < current.getKeys().size(); i++) {
            if (current.getKeys().get(i) == oldKey) {
                current.getKeys().set(i, newKey); // set the new key
                updated = true;
                break;
            }
        }
        
        if (!updated) {
            updateCopy(current.getFather(), oldKey, newKey);
        }
    }

    // Delete:
    public void delete(int key){
        Node leaf = searchLeaf(key);
        int position = searchPosition(leaf.getKeys(), key);
        boolean hasCopy = (position == 0);

        if (position >= leaf.getKeys().size() || leaf.getKeys().get(position) != key){
            return;
        }
        
        Node leftmost = root;
        while (!leftmost.getIsLeaf()) {
            leftmost = leftmost.getChilds().get(0);
        }
        boolean isFirstNode = (leaf == leftmost);

        leaf.getKeys().remove(position);

        if (leaf == root){
            return;
        }

        if (leaf.getKeys().size() < minKeys){
            handleUnderflow(leaf);
        }
        
        if (hasCopy && !isFirstNode) {
            if (!leaf.getKeys().isEmpty()) {
                int newSuccessor = leaf.getKeys().get(0);
                updateCopy(leaf.getFather(), key, newSuccessor);
            }
        }
    }

    private void handleUnderflow(Node node){

        Node father = node.getFather();

        int position = father.getChilds().indexOf(node);

        Node leftSibling = null;
        Node rightSibling = null;

        if (position > 0){
            leftSibling = father.getChilds().get(position - 1);
        }

        if (position < father.getChilds().size() - 1){
            rightSibling = father.getChilds().get(position + 1);
        }

        if (leftSibling != null && leftSibling.getKeys().size() > minKeys){

        if (node.getIsLeaf()){
            borrowFromLeft(node, leftSibling, father, position);
            } else {
                borrowFromLeftInternal(node, leftSibling, father, position);
            }

            return;
        }

        if (rightSibling != null && rightSibling.getKeys().size() > minKeys){

            if (node.getIsLeaf()){
                borrowFromRight(node, rightSibling, father, position);
            } else {
                borrowFromRightInternal(node, rightSibling, father, position);
            }

            return;
        }

        if (leftSibling != null){
            merge(leftSibling, node, father, position);
        } else {
            merge(node, rightSibling, father, position + 1);
        }
    }

    private void borrowFromLeft(Node node, Node leftSibling, Node father, int position){

        int borrowedKey = leftSibling.getKeys().remove(leftSibling.getKeys().size() - 1);

        node.getKeys().add(0, borrowedKey);

        father.getKeys().set(position - 1, borrowedKey);
    }

    private void borrowFromRight(Node node, Node rightSibling, Node father, int position){

        int borrowedKey = rightSibling.getKeys().remove(0);

        node.getKeys().add(borrowedKey);

        father.getKeys().set(position, rightSibling.getKeys().get(0));
    }

    private void borrowFromRightInternal(Node node, Node rightSibling, Node father, int position){

        int separator = father.getKeys().get(position);

        node.getKeys().add(separator);

        Node borrowedChild = rightSibling.getChilds().remove(0);

        node.getChilds().add(borrowedChild);
        borrowedChild.setFather(node);

        int newSeparator = rightSibling.getKeys().remove(0);

        father.getKeys().set(position, newSeparator);
    }

    private void borrowFromLeftInternal(Node node, Node leftSibling, Node father, int position){

        int separator = father.getKeys().get(position - 1);

        node.getKeys().add(0, separator);

        Node borrowedChild = leftSibling.getChilds().remove(leftSibling.getChilds().size() - 1);

        node.getChilds().add(0, borrowedChild);
        borrowedChild.setFather(node);

        int newSeparator = leftSibling.getKeys().remove(leftSibling.getKeys().size() - 1);

        father.getKeys().set(position - 1, newSeparator);
    }

    private void merge(Node leftNode, Node rightNode, Node father, int position){
        
        if (leftNode.getIsLeaf()){

            leftNode.getKeys().addAll(rightNode.getKeys());

            leftNode.setPointer(rightNode.getPointer());

        } else {

            int separator = father.getKeys().get(position - 1);

            leftNode.getKeys().add(separator);
            leftNode.getKeys().addAll(rightNode.getKeys());

            leftNode.getChilds().addAll(rightNode.getChilds());

            for (Node child : rightNode.getChilds()){
                child.setFather(leftNode);
            }
        }

        father.getKeys().remove(position - 1);
        father.getChilds().remove(position);

        if (father == root && father.getKeys().isEmpty()){
            root = leftNode;
            leftNode.setFather(null);
            return;
        }

        if (father != root && father.getKeys().size() < minKeys){
            handleUnderflow(father);
        }
    }


    // Print Tree:
    public void printTree() {
        System.out.println("B+ TREE:\n");
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isLast) {

        System.out.println(
            prefix +
            (isLast ? "|-- " : "|--- ") +
            node.getKeys()
        );

        if (!node.getIsLeaf()) {

            List<Node> children = node.getChilds();

            for (int i = 0; i < children.size(); i++) {

                boolean lastChild = (i == children.size() - 1);

                String newPrefix = prefix + (isLast ? "    " : "|   ");

                printTree(
                    children.get(i),
                    newPrefix,
                    lastChild
                );
            }
        }
    }

    public void printLeaves() {
        Node actual = root;

        while (!actual.getIsLeaf()) {
            actual = actual.getChilds().get(0);
        }

        while (actual != null) {
            System.out.print(actual.getKeys() + " -> ");
            actual = actual.getPointer();
        }

        System.out.println("null");
    }
}
