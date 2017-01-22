package Codewars;

import java.util.Arrays;

public class BrokenSequence {
    public static void main(String[] args) {
        System.out.println(findMissingNumber("1 2 4 5"));
    }

    public static int findMissingNumber(String sequence) {
        int missing = 0;
        String[] split = sequence.split(" ");
        int[] numbers = new int[split.length];
        int[] exp = new int[numbers.length - 1];
        for (int i = 0; i < exp.length; i++) {
            exp[i] = 1;
        }

        for (int i = 0; i < split.length; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }
        Arrays.sort(numbers);

        int[] dif = new int[numbers.length - 1];
        for (int i = 0; i < numbers.length - 1; i++) {
            dif[i] = numbers[i++] - numbers[i];
        }

        if (Arrays.equals(dif, exp)) {
            return 0;
        } else {
            int y = 0;
            for (int i = 0; i < dif.length; i++) {
                if (dif[i] > 1) {
                    y = dif[i];
                }
            }

        }

        return missing;
    }
}
