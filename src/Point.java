
/**
 * this class is a Point object. Point is x,y coordinates.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor creating a new Point.
     *
     * @param x - x line coordinate.
     * @param y - y line coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other - the second point.
     * @return double- the distance between the points.
     */
    public double distance(Point other) {
        double distanceSquared = ((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y));
        return Math.sqrt(distanceSquared);
    }

    /**
     * Equals cekcs if the tho points is the same point. taking in account small error epsilon.
     *
     * @param other - the second point.
     * @return true - if equals, false - if not.
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.x) < 1E-10) && (Math.abs(this.y - other.y) < 1E-10);
    }

    /**
     * getter -Returns the  x value of this point.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * getter- Returns the  y value of this point.
     *
     * @return the y value of this point.
     */
    public double getY() {
        return y;
    }

    /**
     * setter- sets the x value of this point.
     *
     * @param x1 the new value.
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * setter- sets the y value of this point.
     *
     * @param y1 the new value.
     */
    public void setY(double y1) {
        this.y = y1;
    }
}
