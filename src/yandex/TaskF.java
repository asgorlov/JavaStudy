package yandex;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        //case if M = 0
        if (numM == 0) {
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
        //case if M = 1
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
        //case if K = N
        else if (elemK == lengthN) {
            for (short i = 0; i < numbers.length; i++) {
                result[i] = i + 1;
            }
        }
        //case if K = 1
        else if (elemK == 1) {
            for (short i = 0; i < numbers.length; i++) {
                if (numbers[i] == numM) {
                    result[0] = i + 1;
                    break;
                }
            }
        }
        //case if K = 2
        else if (elemK == 2) {
            for (short i = 0; i < numbers.length; i++) {
                int firstMulti = numbers[i];
                if (firstMulti != 0 && numM % firstMulti == 0) {
                    result[counter++] = i + 1;
                    int secondMulti = numM / numbers[i];

                    for (int j = numbers.length - 1; j >= 0; j--) {
                        if (secondMulti == numbers[j] && j != i) {
                            result[counter++] = j + 1;
                            break;
                        }
                    }
                    if (counter == elemK) {
                        break;
                    }
                }
            }
        }
        //in all other cases
        else {
            findIndexes(result);
        }

        writeResult(result);
    }

    private static int[] findIndexes(int[] result) {

        int[][] data = createDataArray();
        int[] multipliers = findMultipliers(data);

        for (short i = 0; i < multipliers.length; i++) {
            for (short j = 0; j < numbers.length; j++) {
                if (multipliers[i] == numbers[j]) {
                    boolean isEqualElement = false;
                    for (short k = 0; k < i + 1; k++) {
                        if (j == result[k] - 1) {
                            isEqualElement = true;
                            break;
                        }
                    }
                    if (!isEqualElement) {
                        result[i] = j + 1;
                    }
                }
            }
        }
        return result;
    }

    private static int[][] createDataArray(){

        List<Integer> sortedArr = new ArrayList<>();
        for (int number : numbers) {
            if (numM % number == 0) {
                if (number > 0 && number <= numM) {
                    sortedArr.add(number);
                }
            }
        }
        Collections.sort(sortedArr);

        List<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < sortedArr.size(); i++) {
            int numCounter = 1;
            dataList.add(sortedArr.get(i));

            for (int j = i + 1; j < sortedArr.size(); j++){
                if (sortedArr.get(i).equals(sortedArr.get(j))) {
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

    private static int[] findMultipliers(int[][] data) {

        int[] multipliers = new int[elemK];
        byte coefficient = 1;
        if (data[data.length - 1][0] == numM) {
            coefficient = 2;
            if (data[0][0] == 1) {
                if (data[0][1] >= (elemK - 1)) {
                    multipliers[0] = numM;
                    for (short i = 1; i < multipliers.length; i++) {
                        multipliers[i] = data[0][0];
                    }
                    return multipliers;
                }
            }
        }

        for (int i = (data.length - coefficient); i >= 0; i--) {
            multipliers[counter++] = data[i][0];
            int tempNum = numM / data[i][0];

            for (short j = 1; j < data.length; j++) {
                if (data[j][0] == tempNum) {
                    multipliers[counter++] = tempNum;

                    if (elemK - counter <= data[0][1]) {
                        while (counter != elemK) {
                            multipliers[counter++] = data[0][0];
                        }
                        return multipliers;
                    }
                    multipliers[counter--] = 0;
                    break;
                }
                else if (data[j][0] > tempNum) {
                    break;
                }
            }
            multipliers[counter--] = 0;
        }
        return multipliers;
    }

    private static void getInitialData() {

        String[] initialData = new String[2];

        try {
            try (FileReader reader = new FileReader("src/yandex/TaskFIn.txt")) {
                Scanner input = new Scanner(reader);
                byte lineCounter = 0;

                while (input.hasNext()) {
                    initialData[lineCounter++] = input.nextLine();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String[] nums = initialData[0].split(" ");
            lengthN = Short.parseShort(nums[0]);
            numM = Integer.parseInt(nums[1]);
            elemK = Short.parseShort(nums[2]);
            numbers = new int[lengthN];
            String[] array = initialData[1].split(" ");

            for (short i = 0; i < array.length; i++) {
                numbers[i] = Integer.parseInt(array[i]);
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void writeResult(int[] result) {

        try {
            try (FileWriter writer = new FileWriter("src/yandex/TaskFOut.txt")) {
                for (int i1 : result) {
                    writer.write(i1 + " ");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
