package myCollections.myLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements ILinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void add(E element) {

        Node<E> node = new Node<>(element);

        if (size == 0) {
            first = last = node;
        }
        else {
            last.setNext(node);
            last = node;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {

        checkExeptions(index);

        if (index > size) {
            add(element);
            return;
        }

        Node<E> node = new Node<>(element);

        if (index <= 0){
            node.setNext(first);
            first = node;
        }
        else {
            Node<E> current = first;

            for (int i = 0; i < (index - 1); i++) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.setNext(node);
        }
        size++;
    }

    @Override
    public void clear() {

        if (size != 0) {
            Node<E> node;

            for(Node<E> current = first; current.getNext() != null; current = node) {
                node = current.getNext();
                node.setNext(null);
                node.setElement(null);
            }

            first = last = null;
            size = 0;
        }
    }

    @Override
    public E get(int index) {

        checkExeptions(index);

        Node<E> node = first;
        int counter = 0;
        while (counter < index) {
            node = node.getNext();
            counter++;
        }
        return node.getElement();
    }

    @Override
    public int indexOf(E element) {

        if (size != 0) {
            int index = 0;
            Node<E> node;

            if (element == null) {
                for(node = first; node != null; node = node.getNext()) {
                    if (node.getElement() == null) {
                        return index;
                    }
                    index++;
                }
            }
            else {
                for(node = first; node != null; node = node.getNext()) {
                    if (element.equals(node.getElement())) {
                        return index;
                    }
                    index++;
                }
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {

        checkExeptions(index);

        Node<E> node = null;
        E element;

        if (index == 0) {
            node = first;
            element = node.getElement();
            first = node.getNext();
            node.setNext(null);
            node.setElement(null);
        }
        else {
            Node<E> removable = first;

            for (int i = 0; i < index; i++) {
                node = removable;
                removable = removable.getNext();
            }

            element = removable.getElement();
            node.setNext(removable.getNext());
            removable.setNext(null);
            removable.setElement(null);

            if (index == (size - 1)) {
                last = node;
            }
        }

        size--;
        return element;
    }

    @Override
    public void set(int index, E element) {

        checkExeptions(index);

        Node<E> node = first;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        node.setElement(element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {

        if (size == 0) {
            return null;
        }

        Object[] objects = new Object[size];
        int index = 0;

        for (Node<E> node = first; node != null; node = node.getNext()) {
            objects[index++] = node.getElement();
        }
        return objects;
    }

    @Override
    public String toString() {

        Iterator<E> iterator = iterator();
        if (!iterator.hasNext()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        result.append("[");
        while (true) {
            E element = iterator.next();
            result.append(element);
            if (!iterator.hasNext()) {
                return result.append("]").toString();
            }
            else {
                result.append(",").append(" ");
            }
        }
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {

            Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {

                 if (hasNext()) {
                     E element = current.getElement();
                     current = current.getNext();
                     return element;
                 }
                 else {
                     return null;
                 }
            }
        };
    }

    private void checkExeptions(int index) {

        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
