package mk.aoc.util;

public class Tools {

    public static int[] convertStringArrayToIntArray(String[] stringArray) {
        int size = stringArray.length;
        if (size > 0) {
            int[] intArry = new int[size];
            for (int i = 0; i < size; i++) {
                intArry[i] = Integer.parseInt(stringArray[i]);
            }
            return intArry;
        } else {
            return null;
        }
    }

    public static void printIntArray(int[] array) {
        if (array.length > 0) {
            for (int i : array) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}
