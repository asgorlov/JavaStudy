package myCollections.myBinaryTree;

interface IBinaryTree<E extends Number> {

    void add (E value);

    boolean search (E value);

    boolean remove (E value);

    E findMax();

    E findMin();

    boolean isEmpty();
}

