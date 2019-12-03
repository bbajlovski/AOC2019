package mk.aoc.model;

public class Line {

    private Point start;
    private Point end;
    private int type = -1; // -1 - NOT SET, 0 - Horizontal, 1 - Vertical

    public Line(Point start, Point end) {
        this.setStart(start);
        this.setEnd(end);
        if (start.getY() == end.getY()) {
            type = 0;
        } else if (start.getX() == end.getX()) {
            type = 1;
        }
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public int getType() {
        return type;
    }

    public int getSize() {
        return type == 0 ?
                Math.abs(end.getX() - start.getX()) :
                type == 1 ?
                        Math.abs(end.getY() - start.getY()) :
                        -1;
    }
}
