package yandex;

public class TaskFTest {

    private static int counterCase;

    public static void main(String[] args) {
        int[] result;

        //CASE 1 --> M = 1000000000, N = 5000, K = 4999
        TaskF.lengthN = 5000;
        TaskF.elemK = 4999;
        TaskF.numM = 1000000000;
        TaskF.numbers =getNumbers();
        result = new int[TaskF.elemK];
        result = TaskF.findIndexes(result);
        printResult(result);
    }

    private static void printResult(int[] result) {
        counterCase++;
        System.out.println(counterCase + ". Print indexes");
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            if (i <= result.length - 2) {
                System.out.print(result[i] + ", ");
            }
            else {
                System.out.print(result[i] + "]");
            }
        }
        System.out.println();

        System.out.println(counterCase + ". Print data of \"numbers\"");
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            if (i <= result.length - 2) {
                System.out.print(TaskF.numbers[result[i] - 1] + ", ");
            }
            else {
                System.out.print(TaskF.numbers[result[i] - 1] + "]");
            }
        }
        System.out.println("\n");
    }

    private static int[] getNumbers() {
        int[] nums = new int[TaskF.lengthN];
        int counter = TaskF.elemK;
        nums[counter--] = TaskF.numM / 1000;
        nums[counter--] = 1000;


        while (counter >= 0) {
            nums[counter--] = 1;
        }
        return nums;
    }
}

