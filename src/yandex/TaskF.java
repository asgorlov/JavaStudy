package yandex;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TaskF {

   public static short lengthN;
   public static int numM;
   public static short elemK;
   public static int[] numbers;

   public static short counter;
   public static List<Integer> numList;
   public static long multiNum;

    public static void main(String[] args) {

        getInitialData();
        int[] result = new int[elemK];

        if (elemK == lengthN) {
            for (short i = 0; i < numbers.length; i++) {
                result[i] = i + 1;
            }
        }
        else if (elemK == 1) {
            for (short i = 0; i < numbers.length; i++) {
                if (numbers[i] == numM) {
                    result[0] = i + 1;
                    break;
                }
            }
        }
        else if (numM == 0) {
            for (short i = 0; i < numbers.length; i++) {
                if (numbers[i] == 0) {
                    result[counter++] = i + 1;
                    for (short j = 0; j < numbers.length; j++) {
                        if (counter == elemK) {
                            break;
                        }
                        if (j != i) {
                            result[counter++] = j + 1;
                        }
                    }
                    break;
                }
            }
        }
        else if (numM == 1) {
            for (short i = 0; i < numbers.length; i++) {
                if (numbers[i] == numM) {
                    result[counter++] = i + 1;
                    if (counter == elemK) {
                        break;
                    }
                }
            }
        }
        else {
            result = getIndexes(result);
        }

        writeResult(result);
    }

    public static int[] getIndexes(int[] result) {

        int[][] data = getData();

        return result;
    }

    private static int[][] getData(){

        int[] sortedArr = new int[numbers.length];
        for (short i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0 && numbers[i] <= numM) {
                if (numM % numbers[i] == 0) {
                    sortedArr[i] = numbers[i];
                }
            }
        }
        Arrays.sort(sortedArr);

        ArrayList<Integer> dataList = new ArrayList<>();
        for (short i = 0; i < sortedArr.length; i++) {
            int numCounter = 0;
            dataList.add(sortedArr[i]);

            for (short j = i; j < sortedArr.length; j++){
                if (sortedArr[i] == sortedArr[j]) {
                    numCounter++;
                }
                else {
                    i = --j;
                    break;
                }
            }
            dataList.add(numCounter);
        }

        int[][] data = new int[dataList.size() / 2][2];
        short indexOdd = 0;
        short indexEven = 0;
        for (short i = 0; i < dataList.size(); i++) {
            if (i % 2 == 0) {
                data[indexEven++][0] = dataList.get(i);
            }
            else {
                data[indexOdd++][1] = dataList.get(i);
            }
        }
        return data;
    }

//    public static int[] getIndexes(int[] result) {
//
//        int[] indexes = new int[result.length];
//        numList = new ArrayList<>();
//
//        for (int element : numbers) {
//            if (element <= numM && element > 0) {
//                if (numM % element == 0) {
//                    numList.add(element);
//                }
//            }
//        }
//        Collections.sort(numList);
//
//        for (int i = (numList.size() - 1); i >= 0; i--) {
//            counter = 0;
//            multiNum = numbers[i];
//            indexes[counter++] = i;
//            indexes = findIndex(indexes);
//
//            if (counter == elemK) {
//                break;
//            }
//        }
//
//        System.out.println(numList.toString());
//        for (int i : indexes) {
//            System.out.println(i);
//        }
//
//        counter = 1;
//        for (short i = 0; i < indexes.length; i++) {
//
//            for (short j = 0; j < numbers.length; j++) {
//                if (numList.get(indexes[i]) == numbers[j]) {
//
//                    boolean isEqualIndex = false;
//                    for (short k = 0; k < result.length; k++) {
//                        if (result[k] == (j + 1)) {
//                            isEqualIndex = true;
//                            break;
//                        }
//                    }
//                    if (!isEqualIndex){
//                        result[i] = j + 1;
//                        counter++;
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    public static int[] findIndex(int[] indexes) {
//
//        for (short i = 0; i < numList.size(); i++) {
//            if (counter == elemK) {
//                return indexes;
//            }
//
//            boolean isEqualIndex = false;
//            for (short j = 0; j < counter; j++) {
//                if (indexes[j] == i) {
//                    isEqualIndex = true;
//                    break;
//                }
//            }
//            if (isEqualIndex) {
//                continue;
//            }
//
//            long tempNum = multiNum;
//            multiNum *= numList.get(i);
//            if (multiNum <= numM) {
//                indexes[counter++] = i;
//                if (counter == elemK) {
//                    if (multiNum == numM){
//                        return indexes;
//                    }else {
//                        indexes[--counter] = -1;
//                        multiNum = tempNum;
//                    }
//                }
//                else {
//                    short tempCounter = counter;
//                    indexes = findIndex(indexes);
//                    if (tempCounter == counter) {
//                        indexes[--counter] = -1;
//                        multiNum = tempNum;
//                    }
//                }
//            }
//            else {
//                multiNum = tempNum;
//            }
//        }
//        return indexes;
//    }

    private static void getInitialData() {

        String[] initialData = new String[2];

        try {
            FileReader reader = new FileReader("src/yandex/TaskFIn.txt");
            try {
                Scanner input = new Scanner(reader);
                byte lineCounter = 0;

                while (input.hasNext()) {
                    initialData[lineCounter++] = input.nextLine();
                }
            }
            finally {
                reader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String[] nums = initialData[0].split(" ");
            lengthN = Short.valueOf(nums[0]);
            numM = Integer.valueOf(nums[1]);
            elemK = Short.valueOf(nums[2]);
            numbers = new int[lengthN];
            String[] array = initialData[1].split(" ");

            for (short i = 0; i < array.length; i++) {
                numbers[i] = Integer.valueOf(array[i]);
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void writeResult(int[] result) {

        try {
            FileWriter writer = new FileWriter("src/yandex/TaskFOut.txt");
            try {
                for (int i1 : result) {
                    writer.write(i1 + " ");
                }
            }
            finally {
                writer.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
