package myCollections.myLinkedList;

public class MyLinkedListTest {

    public static void main(String[] args) {

        MyLinkedList<Integer> intList = new MyLinkedList<>();
        int sizeList = 20;
        System.out.println("size of MyLinkedList = " + sizeList + "\n");

        //1. add(element)
        for (int i = 0; i < sizeList; i++) {
            intList.add(i);
        }
        System.out.println("add(element)--> " + intList.toString());

        //2. remove(index)
        int[] removeElements = new int[intList.size()];
        for (int i = 0; i < intList.size(); i++) {

            if (i % 2 == 0){
                removeElements[i] = intList.remove(i);
            }
        }
        System.out.println("remove(index)--> " + intList.toString());

        //3. add(index,element)
        for (int i = 0; i < sizeList; i++) {

            if (i % 2 == 0){
                intList.add(i,removeElements[i]);
            }
        }
        System.out.println("add(index,element)--> " + intList.toString());

        //4. set(index, element)
        for (int i = 0; i < intList.size();) {
            intList.set(i++,i*10);
        }
        System.out.println("set(index,element)--> " + intList.toString());

        //5. get(index)
        System.out.print("get(index)--> ");
        for (int i = 0; i < intList.size();) {
            System.out.print(intList.get(i++) + " ");
        }
        System.out.println();

        //6.indexOf(element)
        int element = intList.get(intList.size() - 1);//element of last index
//        element = 300;// example for not found element

        if (intList.indexOf(element) == -1){
            System.out.println("indexOf(index)--> Element not found (" + intList.indexOf(element) + ")");
        }
        else {
            System.out.println("indexOf(index)--> Element was found (" + intList.indexOf(element) + ")");
        }

        //7.toArray()
        Object[] objs = intList.toArray();
        System.out.print("toArray()--> ");
        for (Object obj : objs) {
            System.out.print(obj + " ");
        }
        System.out.println();

        //8.clear()
        intList.clear();
        System.out.println("clear()--> " + intList.toString() + " : size = " + intList.size());
    }
}
