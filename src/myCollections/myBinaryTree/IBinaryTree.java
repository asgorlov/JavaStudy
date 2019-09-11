package myCollections.myBinaryTree;

import java.util.Comparator;

interface IBinaryTree<E> extends Comparator<E> {

    boolean add (E value);

    NodeBTree<E> search (E value);

    boolean remove (E value);

    E findMax();

    E findMin();

    boolean isEmpty();

    void clear();
}

