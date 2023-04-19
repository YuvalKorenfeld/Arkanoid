
import java.util.LinkedList;

/**
 * this class is a Rectangle object. Rectangle have upper left point , width, height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor Creating a new rectangle with location and width,height.
     *
     * @param upperLeft - the cornet upper left on the rectangle.
     * @param height    -the rectangle height.
     * @param width     -the rectangle width.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * getter -Returns the width value of this rectangle.
     *
     * @return width value of this rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter -Returns the height value of this rectangle.
     *
     * @return height value of this rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter -Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * getter -Returns the upper-Right point of the rectangle.
     *
     * @return the upper-Right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
    }

    /**
     * getter -Returns the Lower-Left point of the rectangle.
     *
     * @return the Lower-Left point of the rectangle.
     */
    public Point getLowerLeft() {
        return new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
    }

    /**
     * getter -Returns the Lower-Right point of the rectangle.
     *
     * @return the Lower-Right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line (with this triangle).
     *
     * @param line - the intersecting line.
     * @return linked list with the intersections with this triangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        LinkedList<Point> intersections = new LinkedList<>();

        Line ceiling = new Line(this.getUpperLeft(), this.getUpperRight());
        Line floor = new Line(this.getLowerRight(), this.getLowerLeft());
        Line wallLeft = new Line(this.getUpperLeft(), this.getLowerLeft());
        Line wallRight = new Line(this.getLowerRight(), this.getUpperRight());

        if (ceiling.intersectionWith(line) != null) {
            intersections.add(ceiling.intersectionWith(line));
        }
        if (floor.intersectionWith(line) != null) {
            intersections.add(floor.intersectionWith(line));
        }
        if (wallLeft.intersectionWith(line) != null) {
            intersections.add(wallLeft.intersectionWith(line));
        }
        if (wallRight.intersectionWith(line) != null) {
            intersections.add(wallRight.intersectionWith(line));
        }

        return intersections;
    }


}
