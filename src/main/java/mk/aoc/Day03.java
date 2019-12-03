package mk.aoc;

import mk.aoc.model.IntersectionPoint;
import mk.aoc.model.Line;
import mk.aoc.model.Point;
import mk.aoc.util.FileUtil;
import mk.aoc.util.LineUtil;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Day03 {

    public static void main(String... args) {
        FileUtil file = new FileUtil();

        long startTime = System.nanoTime();
        long duration1 = -1;
        long duration2 = -1;

        BufferedReader reader;
        int minDistance = -1;
        int minSteps = -1;
        try {
            reader = new BufferedReader(new FileReader(file.getFileFromResources("Day03.txt")));
            String cableInput = reader.readLine();
            ArrayList<ArrayList<Line>> cables = new ArrayList<>();
            while (cableInput != null) {
                System.out.println("-> " + cableInput);
                ArrayList<Line> cable = new ArrayList<>();
                cables.add(cable);

                String[] moveInput = cableInput.split(",");

                Point start = new Point(0, 0);

                for (String move : moveInput) {
                    Point end = LineUtil.calculateEndPoint(start, move);
                    Line line = new Line(start, end);
                    cable.add(line);
                    start = end;
                }

                cableInput = reader.readLine();
            }

            ArrayList<IntersectionPoint> intersects = new ArrayList<>();

            for (int first = 0; first < cables.size() - 1; first++) {
                ArrayList<Line> firstCable = cables.get(first);

                for (int second = first + 1; second < cables.size(); second++) {
                    ArrayList<Line> secondCable = cables.get(second);
                    int stepsFromFirst = 0;
                    for (Line lineFromFirst : firstCable) {
                        stepsFromFirst += lineFromFirst.getSize();
                        int stepsFromSecond = 0;
                        for (Line lineFromSecond : secondCable) {
                            stepsFromSecond += lineFromSecond.getSize();
                            Point point = LineUtil.intersect(lineFromFirst, lineFromSecond);
                            if (point != null) {
                                int deltaFromFirst = LineUtil.distance(lineFromFirst.getEnd(), point);
                                int deltaFromSecond = LineUtil.distance(lineFromSecond.getEnd(), point);
                                int totalSteps = (stepsFromFirst - deltaFromFirst) + (stepsFromSecond - deltaFromSecond);
                                IntersectionPoint intersect = new IntersectionPoint(point, totalSteps);
                                intersects.add(intersect);
                            }
                        }
                    }
                }
            }

            minDistance = LineUtil.minDistance(intersects);
            long endTime1 =  System.nanoTime();
            minSteps = LineUtil.minSteps(intersects);
            long endTime2 = System.nanoTime();
            duration1 = (endTime1 - startTime) / 1_000_000;
            duration2 = (endTime2 - startTime) / 1_000_000;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("1:: " + minDistance + " / " + duration1 + "ms");
        System.out.println("2:: " + minSteps + " / " + duration2 + "ms");
    }


}

