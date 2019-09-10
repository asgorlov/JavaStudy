package myCollections.myBinaryTree;

public class MyBinaryTreeTest {

    public static void main(String[] args) {

        MyBinaryTree tree = new MyBinaryTree();

        tree.add(19);
        tree.add(21);
        tree.add(22);
        tree.add(9);
        tree.add(16);
        tree.add(18);
        tree.add(17);
        tree.add(10);
        tree.add(15);
        tree.add(7);
        tree.add(3);
        tree.add(2);

        System.out.println(tree.search(19));
        System.out.println(tree.remove(19));
        System.out.println(tree.search(19));
    }
}
