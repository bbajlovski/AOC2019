package mk.aoc;

import mk.aoc.util.FileUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day01 {

    public static void main(String... args) {
        FileUtil file = new FileUtil();

        long startTime = System.nanoTime();
        long duration = -1;

        BufferedReader reader;
        int sum = 0;
        int sum2 = 0;
        try {
            reader = new BufferedReader(new FileReader(file.getFileFromResources("Day01.txt")));
            String line = reader.readLine();
            while (line != null) {
                System.out.println("-> " + line);
                int mass = Integer.parseInt(line);
                sum += calculateFuel(mass);
                sum2 += calculateTotalFuel(mass);
                line = reader.readLine();
            }
            long endTime =  System.nanoTime();
            duration = (endTime - startTime) / 1_000_000;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("1:: " + sum);
        System.out.println("2:: " + sum2);
        System.out.println("time:: " + duration + "ms");
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
}
