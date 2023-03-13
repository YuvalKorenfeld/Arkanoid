// 207801887 Yuval Korenfeld

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;

/**
 * this class is a Game Environment. contains all the collidables on the game.
 */
public class GameEnvironment {
    private LinkedList<Collidable> collidables = new LinkedList<Collidable>();

    /**
     * add the given collidable to the environment.
     *
     * @param c - the new Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * removes Collidable from the Collidable linked list.
     *
     * @param c - the Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }


    /**
     * getter -Returns the linked list to holds the Collidables.
     *
     * @return the linked list to holds the Collidables.
     */
    public LinkedList<Collidable> getCollidableList() {
        return new LinkedList<>(this.collidables);
    }


    /**
     * get The Closest Collision function- Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables- return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory - the trajectory line.
     * @return CollisionInfo - of the closest coalition, or null.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        Point[] collPoints = new Point[this.collidables.size()];
        for (int i = 0; i < collPoints.length; i++) {
            Rectangle rectangle1 = new Rectangle(collidables.get(i).getCollisionRectangle().getUpperLeft(),
                    collidables.get(i).getCollisionRectangle().getWidth(),
                    collidables.get(i).getCollisionRectangle().getHeight());
            collPoints[i] = trajectory.closestIntersectionToStartOfLine(rectangle1);
        }
        boolean nullFlag = true; // no collide point.
        boolean firstIteration = true; //flag for the first iteration.
        double minDisstance = 0;
        int indexsSaver = 0; // the index for the min distance collation point.
        for (int i = 0; i < collPoints.length; i++) {
            if (collPoints[i] != null) {
                nullFlag = false;
                if (firstIteration) {
                    minDisstance = trajectory.start().distance(collPoints[i]);
                    indexsSaver = i;
                    firstIteration = false;
                } else {
                    if (trajectory.start().distance(collPoints[i]) < minDisstance) {
                        minDisstance = trajectory.start().distance(collPoints[i]);
                        indexsSaver = i;
                    }
                }
            }
        }
        if (nullFlag) {
            return null;
        }
        return new CollisionInfo(collPoints[indexsSaver], this.collidables.get(indexsSaver));
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface given DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.GRAY);
        for (int i = 0; i < collidables.size(); i++) {
            surface.fillRectangle((int) this.collidables.get(i).getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.collidables.get(i).getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.collidables.get(i).getCollisionRectangle().getWidth(),
                    (int) this.collidables.get(i).getCollisionRectangle().getHeight());
        }

    }

}
