
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * the Animation Runner class runs the main animation of the game.
 * "the big playing loop".
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * constructor creates a new Animation Runner.
     *
     * @param framesPerSecond - the amount of frames fer second in the game loop.
     * @param gui             - the gui that the animation created in.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }

    /**
     * this the main loop that runs the game.
     *
     * @param animation - the given animation to run every frame.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
