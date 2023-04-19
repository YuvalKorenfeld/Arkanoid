// 207801887 Yuval Korenfeld

import biuoop.DrawSurface;

/**
 * this interface implemented by Sprite object.
 * there is an ability to draw sprite on DrawSurface and notify them that time passed.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d - the DrawSurface the sprite will be drawn on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
