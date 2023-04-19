

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor creating a new Velocity.
     *
     * @param dx - change in x axe.
     * @param dy - change in y axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor creating a new Velocity.
     *
     * @param angle - stands for the angle from top left corner clock wise.
     * @param speed -speed of the object.
     * @return new velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * getter -Returns the Dx value of this velocity.
     *
     * @return the Dx value of this velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getter -Returns the Dy value of this velocity.
     *
     * @return the Dy value of this velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * setter- sets the dX velocity.
     *
     * @param x1 the new value.
     */
    public void setDx(double x1) {
        this.dx = x1;
    }

    /**
     * setter- sets the dY velocity.
     *
     * @param y1 the new value.
     */
    public void setDy(double y1) {
        this.dy = y1;
    }

    /**
     * setter- sets the overall velocity.
     *
     * @param velocity the new value.
     */
    public void setVelocity(Velocity velocity) {
        this.dx = velocity.getDx();
        this.dy = velocity.getDy();
    }

    /**
     * Take a point with position (x,y) and return a new point, with position (x+dx, y+dy).
     *
     * @param p the given Point.
     * @return returns the new point.
     */
    public Point applyToPoint(Point p) {
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }
}
