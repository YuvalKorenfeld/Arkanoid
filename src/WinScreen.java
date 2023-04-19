
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * animation displaying the win screen.
 */
public class WinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * constructor creating a new animation for win screen.
     *
     * @param score - the score to display.
     * @param k     - the sensor for closing the animation.
     */
    public WinScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
