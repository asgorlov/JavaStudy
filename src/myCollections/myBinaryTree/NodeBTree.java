package myCollections.myBinaryTree;

public class NodeBTree<E extends Number> {

    private NodeBTree left;
    private NodeBTree rigth;
    private E value;

    public NodeBTree(E value) {
        this.value = value;
        left = null;
        rigth = null;
    }

    @Override
    public String toString() {
        return "NodeBTree{value=" + value + '}';
    }

    public NodeBTree getLeft() {
        return left;
    }

    public void setLeft(NodeBTree left) {
        this.left = left;
    }

    public NodeBTree getRigth() {
        return rigth;
    }

    public void setRigth(NodeBTree right) {
        this.rigth = right;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
