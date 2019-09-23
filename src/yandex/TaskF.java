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
            result = findIndexes(result);
        }

        writeResult(result);
    }

    public static int[] findIndexes(int[] result) {

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

    public static int[] findMultipliers(int[][] data) {

        int[] multipliers = new int[elemK];

        if (data[data.length - 1][0] == numM) {
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
        else if (data[0][0] == 1) {
            for (int i = (data.length - 1); i >= 0; i--) {
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
        }

        return multipliers;
    }

    public static int[][] createDataArray(){

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

    public static void getInitialData() {

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
