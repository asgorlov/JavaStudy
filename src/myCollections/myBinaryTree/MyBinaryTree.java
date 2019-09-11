package myCollections.myBinaryTree;

import java.util.NoSuchElementException;

public class MyBinaryTree<E extends Comparable<E>> implements IBinaryTree<E> {

    private NodeBTree root;

    public MyBinaryTree() {
        root = null;
    }

    public MyBinaryTree(NodeBTree root) {
        this.root = root;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E value) {

        NodeBTree<E> node = new NodeBTree(value);

        if (isEmpty()) {
            root = node;
            return true;
        }
        else {
            NodeBTree<E> current = root;
            NodeBTree<E> parent;

            while (true) {
                parent = current;

                if (compare(current.getValue(), node.getValue()) == 0) {
                    return false;
                }

                if (compare(current.getValue(), node.getValue()) > 0) {
                    current = parent.getLeft();

                    if (current == null) {
                        parent.setLeft(node);
                        return true;
                    }
                }
                else {
                    current = parent.getRigth();

                    if (current == null) {
                        parent.setRigth(node);
                        return true;
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public NodeBTree<E> search(E value) {

        NodeBTree<E> node = root;

        while (node != null) {

            if (compare(node.getValue(), value) == 0) {
                return node;
            }

            if (compare(node.getValue(), value) > 0) {
                node = node.getLeft();
            }
            else {
                node = node.getRigth();
            }
        }
        return node;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(E value) {

        if (isEmpty()){
            throw new NoSuchElementException();
        }
        else {
            NodeBTree<E> current = root;
            NodeBTree<E> parent = root;
            boolean isRightChild = false;

            while (compare(current.getValue(), value) != 0) {
                parent = current;

                if (compare(current.getValue(), value) > 0) {
                    isRightChild = false;
                    current = current.getLeft();
                }
                else if (compare(current.getValue(), value) < 0) {
                    isRightChild = true;
                    current = current.getRigth();
                }

                if (current == null) {
                    return false;
                }
            }

            //CASE 1: node without childs
            if (current.getLeft() == null && current.getRigth() == null) {

                if (current == root) {
                    root = null;
                }

                if (isRightChild) {
                    parent.setRigth(null);
                }
                else {
                    parent.setLeft(null);
                }
                return true;
            }

            //CASE 2: node with two childs
            if (current.getLeft() != null & current.getRigth() != null) {
                NodeBTree<E> successor = current.getRigth();
                NodeBTree<E> parentSuccessor = current.getRigth();
                NodeBTree<E> tempNode = current.getRigth();

                while (tempNode != null) {
                    parentSuccessor = successor;
                    successor = tempNode;
                    tempNode = tempNode.getLeft();
                }

                if (successor.getRigth() != null) {
                    parentSuccessor.setLeft(successor.getRigth());
                }

                successor.setRigth(current.getRigth());
                successor.setLeft(current.getLeft());

                if (current == root) {
                    root = successor;
                }
                else if (isRightChild) {
                    parent.setRigth(successor);
                }else {
                    parent.setLeft(successor);
                }
                return true;
            }

            //CASE 3: node with only one child
            if (current.getLeft() != null) {
                if (current == root) {
                    root = current.getLeft();
                }
                else if (isRightChild){
                    parent.setRigth(current.getLeft());
                }
                else {
                    parent.setLeft(current.getLeft());
                }

                return true;
            }else {
                if (current == root) {
                    root = current.getRigth();
                }
                else if (isRightChild) {
                    parent.setRigth(current.getRigth());
                }
                else {
                    parent.setLeft(current.getRigth());
                }
                return true;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E findMax() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        NodeBTree<E> current = root;

        while (current.getRigth() != null) {
            current = current.getRigth();
        }

        return current.getValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E findMin() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        NodeBTree<E> current = root;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current.getValue();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {

        if (!isEmpty()) {
            deleteNode(root);
        }

        root = null;
    }

    @Override
    public int compare(E o1, E o2) {
        return o1.compareTo(o2);
    }

    @SuppressWarnings("unchecked")
    private void deleteNode(NodeBTree<E> node) {

        if (node.getLeft()!= null) {
            deleteNode(node.getLeft());
        }

        if (node.getRigth() != null) {
            deleteNode(node.getRigth());
        }

        node.setLeft(null);
        node.setRigth(null);
        node.setValue(null);
    }
}
