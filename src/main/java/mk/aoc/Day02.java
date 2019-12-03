package mk.aoc;

import mk.aoc.util.FileUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day02 {

    public static void main(String... args) {
        FileUtil file = new FileUtil();

        long startTime = System.nanoTime();
        long duration1 = -1;
        long duration2 = -1;

        BufferedReader reader;
        int mostLeft = -1;
        int outputNounVerb = -1;
        try {
            reader = new BufferedReader(new FileReader(file.getFileFromResources("Day02.txt")));
            String line = reader.readLine();
            while (line != null) {
                System.out.println("-> " + line);
                String[] input = line.split(",");

                line = reader.readLine();
                mostLeft = mostLeft(input, 12, 2);
                long endTime1 =  System.nanoTime();

                int noun = 0;
                int verb = 0;
                boolean found = false;

                while (!found && noun < 100) {
                    verb = 0;
                    while (!found && verb < 100) {
                        found = 19690720 == mostLeft(input, noun, verb) ? true : false;
                        verb = !found ? verb + 1 : verb + 0;
                    }
                    noun = !found ? noun + 1 : noun + 0;
                }

                outputNounVerb = 100 * noun + verb;
                long endTime2 =  System.nanoTime();

                duration1 = (endTime1 - startTime) / 1_000_000;
                duration2 = (endTime2 - startTime) / 1_000_000;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("1:: " + mostLeft + " / " + duration1 + "ms");
        System.out.println("2:: " + outputNounVerb + " / " + duration2 + "ms");
    }

    public static int calculateFuel(int mass) {
        return mass / 3 - 2;
    }

    public static int calculateTotalFuel(int mass) {
        int fuel = calculateFuel(mass);
        fuel = fuel < 0 ? 0 : fuel;
        int sum = fuel;
        while (fuel > 0) {
            fuel = calculateFuel(fuel);
            fuel = fuel < 0 ? 0 : fuel;
            sum += fuel;
        }

        return sum;
    }

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

    public static int mostLeft(String[] input, int noun, int verb) {
        System.out.println("========================== noun=" + noun + ", verb=" + verb + " =========================================================");
        int[] opInput = convertStringArrayToIntArray(input);
        opInput[1] = noun;
        opInput[2] = verb;

        int pos = 0;
        boolean halt = pos >= opInput.length;
        while (!halt) {
            int opCode = opInput[pos];
            switch (opCode) {
                case 1: {
                    int dest = opInput[pos+3];
                    int src1 = opInput[pos+1];
                    int src2 = opInput[pos+2];
                    int val1 = opInput[src1];
                    int val2 = opInput[src2];
                    System.out.println(":: [" + pos + "] :: ADD :: [" + dest + "] = [" + src1 + "] + [" + src2 + "]");
                    opInput[dest] = val1 + val2;
                    System.out.println(":: [" + pos + "] :: ADD :: [" + dest + "] = " + val1 + " + " + val2);
                    System.out.println(":: [" + pos + "] :: ADD :: [" + dest + "] = " + opInput[dest]);
                    printIntArray(opInput);
                    pos+=4;
                    break;
                }
                case 2: {
                    int dest = opInput[pos+3];
                    int src1 = opInput[pos+1];
                    int src2 = opInput[pos+2];
                    int val1 = opInput[src1];
                    int val2 = opInput[src2];
                    System.out.println(":: [" + pos + "] :: MULTIPLICATION :: [" + dest + "] = [" + src1 + "] * [" + src2 + "]");
                    opInput[dest] = val1 * val2;
                    System.out.println(":: [" + pos + "] :: MULTIPLICATION :: [" + dest + "] = " + val1 + " * " + val2);
                    System.out.println(":: [" + pos + "] :: MULTIPLICATION :: [" + dest + "] = " + opInput[dest]);
                    printIntArray(opInput);
                    pos+=4;
                    break;
                }
                case 99: {
                    System.out.println(":: [" + pos + "] :: HALT");
                    halt = true;
                    break;
                }
                default: {
                    System.out.println(":: Something went wrong at [" + pos + "]=" + opCode);
                    halt = true;
                    break;
                }
            }
        }

        return opInput != null && opInput.length > 0 ? opInput[0] : -1;
    }
}
