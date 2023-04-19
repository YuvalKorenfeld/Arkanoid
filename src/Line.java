
import java.util.LinkedList;

/**
 * this class is a Line object. contains two Point objects - start point and end point.
 */
public class Line {
    private Point start;
    private Point end;
    private double m, n;
    private boolean isVertical;

    /**
     * constructor creating a new Line.
     *
     * @param start -start point of the line.
     * @param end   - end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.createLineByMandN();
    }

    /**
     * constructor creating a new Line.
     *
     * @param x1 -start point of the line - in coordinates.
     * @param y1 -start point of the line - in coordinates.
     * @param x2 -end point of the line - in coordinates.
     * @param y2 -end point of the line - in coordinates.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return double- the distance between the points.
     */
    public double length() {
        return start().distance(end());
    }

    /**
     * Returns the middle point of the line.
     *
     * @return double- the middle point of the line.
     */
    public Point middle() {
        return new Point((this.start().getX() + this.end().getX()) / 2,
                (this.start().getY() + this.end().getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return double- the start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return double- the end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * Returns true if the line is Vertical, false otherwise.
     *
     * @param line - the line.
     * @return boolean- Returns true if the line is Vertical, false otherwise.
     */
    public boolean isVertical(Line line) {
        if (line.start().getX() == line.end().getX()) {
            return true;
        }
        return false;
    }

    /**
     * creating new line by M and N parameters.
     * m- is the incline.
     * n- is the free number.
     */
    private void createLineByMandN() {
        this.calcM();
        this.calcN();
    }

    /**
     * calculating the incline.
     */
    private void calcM() {
        if (!(Math.abs(start.getX() - end.getX()) < 1E-10)) {
            m = (start.getY() - end.getY()) / (start.getX() - end.getX());
        } else {
            m = 0;
            isVertical = true;
        }
    }

    /**
     * calculating the free number.
     */
    private void calcN() {
        if (!isVertical) {
            n = start.getY() - (start.getX() * m);
        } else {
            n = 0;
        }
    }

    /**
     * Returns true if the lines intersect in one point, false otherwise.
     *
     * @param other - the second line.
     * @return boolean- Returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     *
     * @param other - the second line.
     * @return Point- intersection point/null.
     */
    public Point intersectionWithV1(Line other) {
        //same line
        if (this.equals(other)) {
            return null;
        }
        //one of the lines or both parallel to Y line.
        if ((this.start().getX() == this.end().getX()) || (other.start().getX() == other.end().getX())) {
            return intersectionParallelToYAxisV1(other);
        }

        //building the equation of a line.
        double m1 = (this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
        double m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
        double n1 = this.start().getY() - (m1 * this.start().getX());
        double n2 = other.start().getY() - (m2 * other.start().getX());

        // when the incline are the same.
        if (m1 == m2) {
            if (n1 != n2) {
                return null;
            }
            double distance1 = this.start().distance(other.start());
            double distance2 = this.end().distance(other.end());
            if ((distance1 + distance2) != (this.length() + other.length())) {
                return null;
            } else {

                //the lines continue each other.
                if (this.start().equals(other.end())) {
                    return this.start();
                }
                //the lines continue each other.
                if (this.end().equals(other.start())) {
                    return this.end();
                }
                //the lines continue each other.
                if (this.start().equals(other.start())) {
                    return this.start();
                }
                //the lines continue each other.
                if (this.end().equals(other.end())) {
                    return this.end();
                }
            }
        }

        //when the incline is not rhe same.
        double xOfIntersection = (n2 - n1) / (m1 - m2);
        double yOfIntersection = m1 * xOfIntersection + n1;
        Point inter = new Point(xOfIntersection, yOfIntersection);

        //checking if the intersection is between the infinite equation or the limits lines.
        boolean line1BetweenXOneSide = inter.getX() > Math.max(this.start().getX(), this.end().getX()) + 1E-10;
        boolean line1BetweenXOtherSide = inter.getX() < Math.min(this.start().getX(), this.end().getX()) - 1E-10;
        boolean line1BetweenYOneSide = inter.getY() > Math.max(this.start().getY(), this.end().getY()) + 1E-10;
        boolean line1BetweenYOtherSide = inter.getY() < Math.min(this.start().getY(), this.end().getY()) - 1E-10;
        //checking if the intersection is between the infinite equation or the limits lines.
        boolean line2BetweenXOneSide = inter.getX() > Math.max(other.start().getX(), other.end().getX()) + 1E-10;
        boolean line2BetweenXOtherSide = inter.getX() < Math.min(other.start().getX(), other.end().getX()) - 1E-10;
        boolean line2BetweenYOneSide = inter.getY() > Math.max(other.start().getY(), other.end().getY()) + 1E-10;
        boolean line2BetweenYOtherSide = inter.getY() < Math.min(other.start().getY(), other.end().getY()) - 1E-10;

        //checking if the intersection is between the infinite equation or the limits lines.
        if (!line1BetweenXOneSide && !line1BetweenXOtherSide
                && !line1BetweenYOneSide && !line1BetweenYOtherSide
                && !line2BetweenXOneSide && !line2BetweenXOtherSide
                && !line2BetweenYOneSide && !line2BetweenYOtherSide) {
            return inter;
        }
        return null;
    }

    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     *
     * @param other - the second line.
     * @return Point- intersection point/null.
     */
    public Point intersectionWith(Line other) {
        Point interPoint;
        if (isVertical(this) && isVertical(other)) {
            return null;
        } else if (isVertical(this)) {
            interPoint = new Point(this.start.getX(), other.m * this.start.getX() + other.n);
        } else if (isVertical(other)) {
            interPoint = new Point(other.start.getX(), this.m * other.start.getX() + this.n);
        } else {
            interPoint = new Point((this.n - other.n) / (other.m - this.m),
                    (this.m * ((this.n - other.n) / (other.m - this.m))) + this.n);
        }
        //checking if the intersections is between the infinite equations or the limit lines.
        if (this.pointOnLine(interPoint) && other.pointOnLine(interPoint)) {
            return interPoint;
        } else {
            return null;
        }
    }

    /**
     * Returns the intersection point if the lines intersect,and null otherwise -
     * one of the lines or both parallel to Y line.
     *
     * @param other - the second line.
     * @return Point- intersection point/null.
     */
    public Point intersectionParallelToYAxisV1(Line other) {
        //both parallel to Y line.
        if ((this.start().getX() == this.end().getX()) && (other.start().getX() == other.end().getX())) {
            //both parallel to Y line. and dose no touching each other.
            if (this.start().getX() != other.start().getX()) {
                return null;
            }
            //touching in more than one point or those not touch at all.
            double distance1 = this.start().distance(other.start());
            double distance2 = this.end().distance(other.end());
            if ((distance1 + distance2) != (this.length() + other.length())) {
                return null;
            }
            //the lines continue each other.
            if (this.start().equals(other.end())) {
                return this.start();
            }
            //the lines continue each other.
            if (this.end().equals(other.start())) {
                return this.end();
            }
            //the lines continue each other.
            if (this.start().equals(other.start())) {
                return this.start();
            }
            //the lines continue each other.
            if (this.end().equals(other.end())) {
                return this.end();
            }

        }

        //this line parallel to Y line.
        if (this.start().getX() == this.end().getX()) {
            double m1 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
            double n1 = other.start().getY() - (m1 * other.start().getX());
            double xOfIntersection = this.start().getX();
            double yOfIntersection = m1 * xOfIntersection + n1;
            Point inter = new Point(xOfIntersection, yOfIntersection);
            //checking if the intersection is between the infinite equation or the limits lines.
            boolean betweenFlag1 = (inter.getY() <= Math.max(this.start().getY(), this.end().getY()) + 1E-10)
                    && (inter.getY() >= Math.min(this.start().getY(), this.end().getY()) - 1E-10);
            boolean betweenFlag2 = (inter.getX() <= Math.max(other.start().getX(), other.end().getX()) + 1E-10)
                    && (inter.getY() >= Math.min(other.start().getX(), other.end().getX()) - 1E-10);
            //checking if the intersection is between the infinite equation or the limits lines.
            if (betweenFlag1 && betweenFlag2) {
                return inter;
            } else {
                return null;
            }
        }
        //other line parallel to Y line.
        if (other.start().getX() == other.end().getX()) {
            double m2 = (this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
            double n2 = this.start().getY() - (m2 * this.start().getX());
            double xOfIntersection = other.start().getX();
            double yOfIntersection = m2 * xOfIntersection + n2;
            Point inter = new Point(xOfIntersection, yOfIntersection);
            //checking if the intersection is between the infinite equation or the limits lines.
            boolean betweenFlag1 = (inter.getY() <= Math.max(other.start().getY(), other.end().getY()) + 1E-10)
                    && (inter.getY() >= Math.min(other.start().getY(), other.end().getY()) - 1E-10);
            boolean betweenFlag2 = (inter.getX() <= Math.max(this.start().getX(), this.end().getX()) + 1E-10)
                    && (inter.getY() >= Math.min(this.start().getX(), this.end().getX()) - 1E-10);
            //checking if the intersection is between the infinite equation or the limits lines.
            if (betweenFlag1 && betweenFlag2) {
                return inter;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * checks if the point is on the line start to end ( not only on the lin equation).
     *
     * @param p - the point.
     * @return true - on the line, false - not on the line (can be on the infinite equation).
     */
    public boolean pointOnLine(Point p) {
        double length = this.start.distance(p) + p.distance(this.end);
        if (Math.abs(length - this.length()) < 1E-10) {
            return true;
        }
        return false;
    }

    /**
     * equals - return true is the lines are equal, false otherwise.
     *
     * @param other - the second line.
     * @return boolean- true/false.
     */
    public boolean equals(Line other) {
        boolean flag1 = this.start().equals(other.start()) && this.end().equals(other.end());
        boolean flag2 = this.start().equals(other.end()) && this.end().equals(other.start());
        return flag1 || flag2;
    }

    /**
     * return closest Intersection with this rectangle To Start Of the Line.
     *
     * @param rect - the rectangle which the line intersecting with.
     * @return the intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        LinkedList<Point> intersections;
        intersections = (LinkedList<Point>) rect.intersectionPoints(this);
        //no intersections.
        if (intersections.size() == 0) {
            return null;
        }
        //only one intersection - so it the closest.
        if (intersections.size() == 1) {
            return intersections.get(0);
        }
        //two intersections.
        double diss1 = this.start().distance(intersections.get(0));
        double diss2 = this.start().distance(intersections.get(1));
        if (diss1 < diss2) {
            return intersections.get(0);
        } else {
            return intersections.get(1);
        }
    }


}
