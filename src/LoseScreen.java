// 207801887 Yuval Korenfeld

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * animation displaying the lose screen.
 */
public class LoseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * constructor creating a new animation for lose screen.
     *
     * @param score - the score to display.
     * @param k     - the sensor for closing the animation.
     */
    public LoseScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
