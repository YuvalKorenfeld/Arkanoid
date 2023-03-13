// 207801887 Yuval Korenfeld

/**
 * this class is a Collision Info object. Have the collision Point ant the collidable object.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * constructor creating a new Collision Info.
     *
     * @param collisionPoint1 - the collision Point.
     * @param collidable1     - the collidable object.
     */
    public CollisionInfo(Point collisionPoint1, Collidable collidable1) {
        collisionPoint = collisionPoint1;
        collidable = collidable1;
    }

    /**
     * getter - returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * getter - returns the collidable object involved in the collision.
     *
     * @return the the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collidable;
    }
}
