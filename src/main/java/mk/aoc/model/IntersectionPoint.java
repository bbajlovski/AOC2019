package mk.aoc.model;

import java.util.ArrayList;

public class IntersectionPoint extends Point {

    private int totalSteps = -1;

    public IntersectionPoint(int x, int y, int totalSteps) {
        super(x, y);
        this.setTotalSteps(totalSteps);
    }

    public IntersectionPoint(Point point, int totalSteps) {
        this(point.getX(), point.getY(), totalSteps);
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(int totalSteps) {
        this.totalSteps = totalSteps;
    }
}
