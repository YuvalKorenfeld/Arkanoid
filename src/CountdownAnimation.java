
import biuoop.DrawSurface;
import biuoop.Sleeper;


/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long timeToSleep;
    private Sleeper sleeper = new Sleeper();
    private int firstItration;

    /**
     * constructor creating a new Count down Animation.
     *
     * @param countFrom    - began to count from this number.
     * @param numOfSeconds - the all count session will last this amount of seconds.
     * @param gameScreen   - the screen behind dte count down.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.timeToSleep = (long) (numOfSeconds / countFrom * 1000);
        this.firstItration = countFrom;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 50);
        if (countFrom <= 0) {
            this.shouldStop();
        }
        if (countFrom != firstItration) {
            sleeper.sleepFor(timeToSleep);
        }
        countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return !(this.countFrom >= 0);
    }
}
