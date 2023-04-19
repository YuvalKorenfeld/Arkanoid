
import biuoop.DrawSurface;

import java.util.LinkedList;

/**
 * this class is a SpriteCollection object. collects all the sprites in the game.
 */
public class SpriteCollection {
    private LinkedList<Sprite> sprites = new LinkedList<Sprite>();

    /**
     * constructor creating a new linked list to hold the sprites.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * getter -Returns the linked list to holds the sprites.
     *
     * @return the linked list to holds the sprites.
     */
    public LinkedList<Sprite> getSpriteList() {
        return new LinkedList<>(this.sprites);
    }

    /**
     * adds new sprite to the sprites linked list.
     *
     * @param s - the new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }


    /**
     * removes  sprite from the sprites linked list.
     *
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * calls drawOn(d) on all sprites.
     *
     * @param d - the DrawSurface the sprites will be drawn on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}
