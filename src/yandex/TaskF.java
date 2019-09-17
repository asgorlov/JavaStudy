package yandex;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskF {

   private static short lengthN;
   private static int numM;
   private static short elemK;
   private static int[] numbers;
   private static short counter;

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
                }
            }
        }
        else if (numM == 0) {

            for (short i = 0; i < numbers.length; i++) {

                if (numbers[i] == 0) {
                    result[0] = i + 1;

                    for (short j = 1; j < result.length; j++) {
                        if (j != i) {
                            result[j] = j + 1;
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
                }
                if (counter == elemK) {
                    break;
                }
            }
        }
        else {
            result = findIndexes(result);
        }
        writeResult(result);
    }

    private static int[] findIndexes(int[] result) {

        for (short i = 0; i < numbers.length; i++) {

            if (numbers[i] == 0) {
                continue;
            }

            if (numM % numbers[i] == 0) {
                long tempNum = numbers[i];
                counter = 0;
                result[counter++] = i + 1;
                result = searchIndex(result,tempNum);

                if (counter == elemK) {
                    break;
                }
            }
        }
        return result;
    }

    private static int[] searchIndex(int[] result, long tempNum) {

        for (short i = 0; i < numbers.length; i++) {

            if (counter == elemK) {
                return result;
            }

            if (numbers[i] == 0) {
                continue;
            }

            if (numM % numbers[i] != 0) {
                continue;
            }
            boolean isEqualIndex = false;

            for (short j = 0; j < counter; j++) {

                if (result[j] == (i + 1)) {
                    isEqualIndex = true;
                    break;
                }
            }

            if (isEqualIndex) {
                continue;
            }

            if (tempNum * numbers[i] < numM) {
                tempNum *= numbers[i];
                result[counter++] = i + 1;

                if (counter == elemK) {
                    result[--counter] = 0;
                }
                else {
                    short tempCounter = counter;
                    result = searchIndex(result,tempNum);

                    if (tempCounter == counter) {
                        result[--counter] = 0;
                    }
                }
            }
            else if (tempNum * numbers[i] == numM) {
                tempNum *= numbers[i];
                result[counter++] = i + 1;

                if (counter == elemK) {
                    return result;
                }
                else {
                    result = searchIndex(result,tempNum);
                }
            }
        }
        return result;
    }

    private static void getInitialData() {

        String[] inicialData = new String[2];

        try {
            FileReader reader = new FileReader("src/yandex/TaskFIn.txt");

            try {
                Scanner input = new Scanner(reader);
                byte lineCounter = 0;

                while (input.hasNext()) {
                    inicialData[lineCounter++] = input.nextLine();
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
            String[] nums = inicialData[0].split(" ");
            lengthN = Short.valueOf(nums[0]);
            numM = Integer.valueOf(nums[1]);
            elemK = Short.valueOf(nums[2]);
            numbers = new int[lengthN];
            String[] array = inicialData[1].split(" ");

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
