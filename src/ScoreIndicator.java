
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class is a Sprite that only in charge of displaying the score on the screen.
 * the score comes from the score Tracking Listener.
 */
public class ScoreIndicator implements Sprite {
    private ScoreTrackingListener scoreTrackingListener;

    /**
     * constructor creating a new Score Indicator.
     *
     * @param scoreTrackingListener -the listener that contain the real value of the score.
     */
    public ScoreIndicator(ScoreTrackingListener scoreTrackingListener) {
        this.scoreTrackingListener = scoreTrackingListener;
    }

    /**
     * getter -Returns the score Tracking Listener.
     *
     * @return the score Tracking Listener.
     */
    public ScoreTrackingListener getScoreTrackingListener() {
        return this.scoreTrackingListener;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, 800, 20);
        d.drawText(200, 17, "Score: " + scoreTrackingListener.getCurrentScore().getValue(), 15);
    }

    @Override
    public void timePassed() {
        //nothing.
    }
}
