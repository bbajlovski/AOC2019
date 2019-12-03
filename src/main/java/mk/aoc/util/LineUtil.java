package mk.aoc.util;

import mk.aoc.model.IntersectionPoint;
import mk.aoc.model.Line;
import mk.aoc.model.Point;

import java.util.ArrayList;

public class LineUtil {

    public static Point intersect(Line firstLine, Line secondLine) {
        Point intersection = null;

        // Horizontal - 2 - Vertical
        if (firstLine.getType() == 0 && secondLine.getType() == 1) {

            int yMax = Math.max(secondLine.getStart().getY(), secondLine.getEnd().getY());
            int yMin = Math.min(secondLine.getStart().getY(), secondLine.getEnd().getY());
            int yIntersect = firstLine.getStart().getY();

            if (yIntersect >= yMin && yIntersect <= yMax) {

                int xMax = Math.max(firstLine.getStart().getX(), firstLine.getEnd().getX());
                int xMin = Math.min(firstLine.getStart().getX(), firstLine.getEnd().getX());
                int xIntersect = secondLine.getStart().getX();

                if (xIntersect >= xMin && xIntersect <= xMax) {
                    intersection = new Point(xIntersect, yIntersect);
                }

            }
        }

        // Vertical - 2 - Horizontal
        if (firstLine.getType() == 1 && secondLine.getType() == 0) {

            int yMax = Math.max(firstLine.getStart().getY(), firstLine.getEnd().getY());
            int yMin = Math.min(firstLine.getStart().getY(), firstLine.getEnd().getY());
            int yIntersect = secondLine.getStart().getY();

            if (yIntersect >= yMin && yIntersect <= yMax) {

                int xMax = Math.max(secondLine.getStart().getX(), secondLine.getEnd().getX());
                int xMin = Math.min(secondLine.getStart().getX(), secondLine.getEnd().getX());
                int xIntersect = firstLine.getStart().getX();

                if (xIntersect >= xMin && xIntersect <= xMax) {
                    intersection = new Point(xIntersect, yIntersect);
                }

            }
        }

        return intersection;
    }

    public static Point calculateEndPoint (Point start, String move) {
        Point end = null;

        if (start != null && move != null && move.length() > 1) {
            char direction = move.toUpperCase().charAt(0);
            int distance = Integer.parseInt(move.substring(1));
            switch (direction) {
                case 'R': {
                    end = new Point(start.getX() + distance, start.getY());
                    break;
                }
                case 'L': {
                    end = new Point(start.getX() - distance, start.getY());
                    break;
                }
                case 'D': {
                    end = new Point(start.getX(), start.getY() - distance);
                    break;
                }
                case 'U': {
                    end = new Point(start.getX(), start.getY() + distance);
                    break;
                }
            }

        }

        return end;
    }

    public static int minDistance(ArrayList<IntersectionPoint> points) {
        int min = -1;

        for (IntersectionPoint point : points) {
            int distance = Math.abs(point.getX()) + Math.abs(point.getY()); // Manhattan distance
            min = min == -1 || distance < min ? distance : min;
        }

        return min;
    }

    public static int distance(Point firstPoint, Point secondPoint) {

        return Math.abs(firstPoint.getX() - secondPoint.getX()) +
                Math.abs(firstPoint.getY() - secondPoint.getY());
    }


    public static int minSteps(ArrayList<IntersectionPoint> points) {
        int min = -1;

        for (IntersectionPoint point : points) {
            min = min == -1 || point.getTotalSteps() < min ? point.getTotalSteps() : min;
        }

        return min;
    }
}
