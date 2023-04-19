
/**
 * this interface implemented by Collidable object.
 * there is an ability to get Collision Rectangle and the new velocity after the collision.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return Rectangle shape.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     *
     * @param hitter - the ball
     * @param collisionPoint  - the point that the collation happened on.
     * @param currentVelocity - the velocity before the collision.
     * @return Velocity - the new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
