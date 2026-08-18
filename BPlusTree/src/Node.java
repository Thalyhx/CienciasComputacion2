import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author natha, luna
 */

public class Node {
    private Boolean isLeaf;
    private List<Integer> keys;
    private List<Node> childs;
    private Node pointer;
    private Node father;

    public Node(Boolean isLeaf){
        this.isLeaf = isLeaf;
        this.childs = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.pointer = null;
        this.father = null;
    }

    // Getters
    public Boolean getIsLeaf(){
        return isLeaf;
    }

    public List<Integer> getKeys(){
        return keys;
    }

    public List<Node> getChilds(){
        return childs;
    }

    public Node getPointer(){
        return pointer;
    }

    public Node getFather(){
        return father;
    }

    // Setters
    public void setIsLeaf(Boolean isLeaf){
        this.isLeaf = isLeaf;
    }

    public void setPointer(Node pointer) {
        this.pointer = pointer;
    }

    public void setFather(Node father) {
        this.father = father;
    }
}