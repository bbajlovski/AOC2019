package mk.aoc;

import mk.aoc.model.IntersectionPoint;
import mk.aoc.model.Line;
import mk.aoc.model.Point;
import mk.aoc.util.FileUtil;
import mk.aoc.util.LineUtil;
import mk.aoc.util.Tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day04 {

    public static void main(String... args) {
        FileUtil file = new FileUtil();

        long startTime = System.nanoTime();
        long duration1 = -1;
        long duration2 = -1;

        BufferedReader reader;
        int numberOfPasswords = 0;
        int numberOfPasswords2 = 0;

        for (int i=158126; i<=624574; i++) {
            if (Tools.hasSameAdjacent(i)) {
                if (!Tools.hasDecreasingOrder(i)) {
                    numberOfPasswords++;
                    if (Tools.hasOnlyTwoInAdjacency(i)){
                        numberOfPasswords2++;
                    }
                }
            }
        }

        long endTime1 =  System.nanoTime();
        long endTime2 = System.nanoTime();
        duration1 = (endTime1 - startTime) / 1_000_000;
        duration2 = (endTime2 - startTime) / 1_000_000;

        System.out.println("1:: " + numberOfPasswords + " / " + duration1 + "ms");
        System.out.println("2:: " + numberOfPasswords2 + " / " + duration2 + "ms");
    }


}

