
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * this class is a Block object. Block is rectangle and color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new LinkedList<HitListener>();

    /**
     * Return the "collision shape" of the object.
     *
     * @return Rectangle shape.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * constructor creating a new block.
     *
     * @param upperLeft - the upper left point.
     * @param width     - width.
     * @param height    -height.
     */
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param currentVelocity - the velocity before the collision.
     * @param collisionPoint  - the point that the collation happened on.
     * @return Velocity - the new velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //upper left corner
        if (collisionPoint.equals(getCollisionRectangle().getUpperLeft())) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy() * -1);
        }
        //Upper Right corner
        if (collisionPoint.equals(getCollisionRectangle().getUpperRight())) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy() * -1);
        }
        //lower Right corner
        if (collisionPoint.equals(getCollisionRectangle().getLowerRight())) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy() * -1);
        }
        //lower left corner
        if (collisionPoint.equals(getCollisionRectangle().getLowerLeft())) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy() * -1);
        }

        //left wall
        if (Math.abs(collisionPoint.getX() - getCollisionRectangle().getUpperLeft().getX()) < 1E-10) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }
        //chilling
        if (Math.abs(collisionPoint.getY() - getCollisionRectangle().getUpperLeft().getY()) < 1E-10) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }
        //right wall
        if (Math.abs(collisionPoint.getX() - getCollisionRectangle().getLowerRight().getX()) < 1E-10) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }
        //floor
        if (Math.abs(collisionPoint.getY() - getCollisionRectangle().getLowerRight().getY()) < 1E-10) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }
        return currentVelocity;

    }

    /**
     * draw the sprite to the screen.
     *
     * @param d - the DrawSurface the sprite will be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        //empty in this stage.
    }

    /**
     * block is Sprite and Collidable - so we're adding to both - when needed.
     *
     * @param g - the game we're adding to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * block is Sprite and Collidable - so we're removing both- when needed.
     *
     * @param g - the game we're removing from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }


    /**
     * setter -sets the color value of this ball.
     *
     * @param color - the new given color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * getter -Returns the color value of this ball.
     *
     * @return the color value of this ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * getter -Returns the Rectangle of this block.
     *
     * @return the Rectangle of this block.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (!hitListeners.isEmpty()) {
            hitListeners.remove(hl);
        }
    }

    /**
     * Notify all listeners about a hit event. Make a copy of the hitListeners before iterating over them.
     *
     * @param hitter -the ball object that hits thus block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
