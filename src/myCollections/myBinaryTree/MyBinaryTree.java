package myCollections.myBinaryTree;

public class MyBinaryTree<E extends Number> {

    private NodeBTree root;

    public MyBinaryTree() {
        root = null;
    }

    public MyBinaryTree(NodeBTree root) {
        this.root = root;
    }

    public boolean search(int value) {
        if (isEmpty()) {
            return isEmpty();
        }

        NodeBTree<E> currentNode = root;

//        while (currentNode != null) {
//
//            if (currentNode.getValue() == value) {
//                return true;
//            }
//
//            if (currentNode.getValue() > value) {
//                currentNode = currentNode.getLeft();
//            }else {
//                currentNode = currentNode.getRigth();
//            }
//        }
        return false;
    }

    public boolean remove(int value) {
        if (isEmpty()){
            System.out.println("Tree is empty");
            return false;
        }else {
            NodeBTree currentNode = root;
            NodeBTree parentNode = root;
            boolean isRightChild = false;

//            while (currentNode.getValue() != value) {
//                parentNode = currentNode;
//
//                if (currentNode.getValue() > value) {
//                    isRightChild = false;
//                    currentNode = currentNode.getLeft();
//                }else if (currentNode.getValue() < value) {
//                    isRightChild = true;
//                    currentNode = currentNode.getRigth();
//                }
//
//                if (currentNode == null) {
//                    return false;
//                }
//            }

            //CASE 1: node without childs
            if (currentNode.getLeft() == null && currentNode.getRigth() == null) {
                if (currentNode == root) {
                    root = null;
                }

                if (isRightChild) {
                    parentNode.setRigth(null);
                }else {
                    parentNode.setLeft(null);
                }
                return true;
            }

            //CASE 2: node with two childs
            if (currentNode.getLeft() != null & currentNode.getRigth() != null) {
                NodeBTree successor = null;
                NodeBTree parentSuccessor = null;
                NodeBTree tempNode = currentNode.getRigth();

                while (tempNode != null) {
                    parentSuccessor = successor;
                    successor = tempNode;
                    tempNode = tempNode.getLeft();
                }

                if (successor.getRigth() != null) {
                    parentSuccessor.setLeft(successor.getRigth());
                }
                successor.setRigth(currentNode.getRigth());
                successor.setLeft(currentNode.getLeft());

                if (currentNode == root) {
                    root = successor;
                }

                if (isRightChild) {
                    parentNode.setRigth(successor);
                }else {
                    parentNode.setLeft(successor);
                }
                return true;
            }

            //CASE 3: node with only one child
            if (currentNode.getLeft() != null) {
                if (currentNode == root) {
                    root = currentNode.getLeft();
                }else if (isRightChild){
                    parentNode.setRigth(currentNode.getLeft());
                }else {
                    parentNode.setLeft(currentNode.getLeft());
                }

                return true;
            }else {
                if (currentNode == root) {
                    root = currentNode.getRigth();
                }else if (isRightChild) {
                    parentNode.setRigth(currentNode.getRigth());
                }else {
                    parentNode.setLeft(currentNode.getRigth());
                }
                return true;
            }
        }
    }

    @Override
    public void add(E value) {

        NodeBTree node = new NodeBTree(value);

        if (isEmpty()) {
            root = node;
        }
        else {
            NodeBTree current = root;
            NodeBTree parent;

//            while (true) {
////                parent = current;
////
////                if (current.getValue().equals(node.getValue())) {
////                    throw new UnsupportedOperationException("Duplicate nodes not allowed in Binary Search Tree");
////                }
////                if (current.getValue() > node.getValue()) {
////                    current = parent.getLeft();
////                    if (current == null) {
////                        parent.setLeft(node);
////                        break;
////                    }
////                } else {
////                    current = parent.getRigth();
////                    if (current == null) {
////                        parent.setRigth(node);
////                        break;
////                    }
////                }
////            }
        }
    }

    @Override
    public boolean search(E value) {
        return false;
    }

    @Override
    public boolean remove(E value) {
        return false;
    }

    public E findMax() {
        E value;
        return value;
    }

    public E findMin() {
        return E;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
