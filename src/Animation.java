
import biuoop.DrawSurface;

/**
 * this interface implemented by Animation object.
 * The AnimationRunner takes an Animation object and runs it.
 */
public interface Animation {
    /**
     * the logic of one frame when the animation playes.
     *
     * @param d - the DrawSurface we will make on the animation.
     */
    void doOneFrame(DrawSurface d);

    /**
     * the stopping logic for the animation.
     *
     * @return - boolean value that says to stop the animation.
     */
    boolean shouldStop();
}
