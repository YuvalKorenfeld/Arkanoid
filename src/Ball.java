
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class is a Ball object. Ball is center Point, radius and color.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private int ceiling;
    private int floor;
    private int rightWall;
    private int leftWall;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * constructor creating a new Ball.
     * with 200*200 screen size.
     *
     * @param r      - radius.
     * @param center - center Point.
     * @param color  -color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.floor = 200;
        this.leftWall = 0;
        this.rightWall = 200;
        this.ceiling = 0;

    }

    /**
     * constructor creating a new Ball.
     * with 200*200 screen size.
     *
     * @param r     - radius.
     * @param x     - center Point coordinates.
     * @param y     - center Point coordinates.
     * @param color -color.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.floor = 200;
        this.leftWall = 0;
        this.rightWall = 200;
        this.ceiling = 0;
    }

    /**
     * setter- sets the floor value of this Ball.
     *
     * @param floor the new value.
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * setter- sets the rightWall value of this Ball.
     *
     * @param rightWall the new value.
     */
    public void setRightWall(int rightWall) {
        this.rightWall = rightWall;
    }

    /**
     * setter- sets the ceiling value of this Ball.
     *
     * @param ceiling the new value.
     */
    public void setCeiling(int ceiling) {
        this.ceiling = ceiling;
    }

    /**
     * setter- sets the leftWall value of this Ball.
     *
     * @param leftWall the new value.
     */
    public void setLeftWall(int leftWall) {
        this.leftWall = leftWall;
    }

    /**
     * getter -Returns the  x value of this ball center point.
     *
     * @return the x value of this Ball center point.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * getter -Returns the  y value of this ball center point.
     *
     * @return the y value of this Ball center point.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * getter -Returns the  radius value of this ball.
     *
     * @return the radius value of this ball.
     */
    public int getSize() {
        return r;
    }

    /**
     * getter -Returns the  color value of this ball.
     *
     * @return the color value of this ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface given DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());

    }

    /**
     * notify the sprite that time has passed- moving to the next step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * setter- sets the velocity value of this Ball.
     *
     * @param v the new value.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setter- sets the velocity value of this Ball with coordinates.
     *
     * @param dx the new x line value.
     * @param dy the new y line value
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getter -Returns the velocity of this ball.
     *
     * @return the velocity of this ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * moving the ball to the next step on screen considering the size of the ball and size of the screen
     * to obtain the ball fully in view all time.
     */
    public void moveOneStepOld() {
        this.center = this.getVelocity().applyToPoint(this.center);
        //right bound
        if (this.center.getX() + r + this.velocity.getDx() >= rightWall) {
            this.center.setX(rightWall - r);
            this.velocity.setDx(velocity.getDx() * (-1));
        }
        //left bound
        if (this.center.getX() - r + this.velocity.getDx() <= leftWall) {
            this.center.setX(leftWall + r);
            this.velocity.setDx(velocity.getDx() * (-1));
        }
        //top bound
        if (this.center.getY() - r + this.velocity.getDy() <= ceiling) {
            this.center.setY(ceiling + r);
            this.velocity.setDy(velocity.getDy() * (-1));
        }
        //bottom bound
        if (this.center.getY() + r + this.velocity.getDy() >= floor) {
            this.center.setY(floor - r);
            this.velocity.setDy(velocity.getDy() * (-1));
        }
    }

    /**
     * moving the ball to the next step on screen considering the center of the ball
     * and the velocity of the ball all time.
     */
    public void moveOneStep() {
        Line trajectory = this.makeTrajectory();
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        Point newCenter = this.center;
        boolean cornerFlag = false;
        if (info != null) {

            //Upper Left
            if (this.center.equals(info.collisionObject().getCollisionRectangle().getUpperLeft())) {
                newCenter = new Point(this.center.getX() + 1, this.center.getY() - 1);
                cornerFlag = true;
            }

            //Upper Right
            if (this.center.equals(info.collisionObject().getCollisionRectangle().getUpperRight())) {
                newCenter = new Point(this.center.getX() - 1, this.center.getY() - 1);
                cornerFlag = true;
            }
            //Lower Left
            if (this.center.equals(info.collisionObject().getCollisionRectangle().getLowerLeft())) {
                newCenter = new Point(this.center.getX() + 1, this.center.getY() + 1);
                cornerFlag = true;
            }
            //Lower Right
            if (this.center.equals(info.collisionObject().getCollisionRectangle().getLowerRight())) {
                newCenter = new Point(this.center.getX() - 1, this.center.getY() + 1);
                cornerFlag = true;
            }

            //left wall
            if (Math.abs(this.center.getX() - info.collisionObject().getCollisionRectangle().getUpperLeft().getX())
                    < 1E-10 && !cornerFlag) {
                newCenter = new Point(this.center.getX() + 1, this.center.getY());
            }
            //chilling
            if (Math.abs(this.center.getY() - info.collisionObject().getCollisionRectangle().getUpperLeft().getY())
                    < 1E-10 && !cornerFlag) {
                newCenter = new Point(this.center.getX(), this.center.getY() - 1);
            }
            //right wall
            if (Math.abs(this.center.getX() - info.collisionObject().getCollisionRectangle().getLowerRight().getX())
                    < 1E-10 && !cornerFlag) {
                newCenter = new Point(this.center.getX() - 1, this.center.getY());
            }
            //floor
            if (Math.abs(this.center.getY() - info.collisionObject().getCollisionRectangle().getLowerRight().getY())
                    < 1E-10 && !cornerFlag) {
                newCenter = new Point(this.center.getX(), this.center.getY() + 1);
            }

            this.center.setX(newCenter.getX());
            this.center.setY(newCenter.getY());

            this.velocity.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
        } else {
            this.center.setX(velocity.getDx() + this.center.getX());
            this.center.setY(velocity.getDy() + this.center.getY());
        }
    }

    /**
     * building line representing the ball movement on screen.
     *
     * @return the line.
     */
    private Line makeTrajectory() {
        return new Line(this.center, new Point(this.center.getX() + velocity.getDx(),
                this.center.getY() + velocity.getDy()));
    }

    /**
     * setter -  sets the game Environment.
     *
     * @param gameEnvironment -the new game Environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * getter -  returns the game Environment.
     *
     * @return gameEnvironment -this game Environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * adding the ball to a game. ball is a sprite.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.setGameEnvironment(g.getEnvironment());
    }

    /**
     * removing the ball from a game. ball is a sprite.
     *
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}


