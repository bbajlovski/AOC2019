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

    public static void printIntArray(String[] array) {
        if (array.length > 0) {
            for (String s : array) {
                System.out.print(s + ",");
            }
            System.out.println();
        }
    }

    public static boolean hasSameAdjacent(int number) {
        boolean has = false;

        String num = Integer.toString(number);

        for (int i=0; i<num.length()-1; i++) {
            if (has) {
                break;
            }
            if (num.charAt(i)==num.charAt(i+1)) {
                has = true;
                break;
            }
        }
        return has;
    }

    public static boolean hasDecreasingOrder(int number) {
        boolean has = false;

        String num = Integer.toString(number);

        for (int i=0; i<num.length()-1; i++) {
            if (has) {
                break;
            }
            if (num.charAt(i)>num.charAt(i+1)) {
                has = true;
                break;
            }
        }
        return has;
    }

    public static boolean hasOnlyTwoInAdjacency(int number) {
        boolean has = false;

        String num = Integer.toString(number);

        for (int i=0; i<num.length()-1; i++) {
            if (has) {
                break;
            }
            if (num.charAt(i)==num.charAt(i+1)) {
                if (i>0) {
                    if (num.charAt(i-1)==num.charAt(i)) {
                        continue;
                    }
                }
                if (i<num.length()-2) {
                    if (num.charAt(i)==num.charAt(i+2)) {
                        continue;
                    }
                }
                has = true;
                break;
            }
        }

        return has;
    }
}
