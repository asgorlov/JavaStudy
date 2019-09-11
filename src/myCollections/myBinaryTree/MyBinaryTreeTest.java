package myCollections.myBinaryTree;

public class MyBinaryTreeTest {

    public static void main(String[] args) {

        MyBinaryTree<Integer> tree = new MyBinaryTree<>();

        tree.add(19);
        tree.add(21);
        tree.add(23);
        tree.add(20);
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

        System.out.println(tree.search(9));
        System.out.println("remove \"3\"--> " + tree.remove(9));
        System.out.println("\"3\" is " + tree.search(9));
        System.out.println(tree.search(22));
        System.out.println("remove \"22\"--> " + tree.remove(22));
        System.out.println("\"22\" is " + tree.search(22));
        System.out.println(tree.search(18));
        System.out.println("remove \"18\"--> " + tree.remove(18));
        System.out.println("\"18\" is " + tree.search(18));
        System.out.println();

        System.out.println("Max value = " + tree.findMax());
        System.out.println("Mix value = " + tree.findMin());
        System.out.println();

        System.out.println("before clear()--> " + tree.isEmpty());
        tree.clear();
        System.out.println("after clear()--> " + tree.isEmpty());


    }
}
