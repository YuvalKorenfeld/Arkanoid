
/**
 * this class is a Score Tracking Listener. he has Counter object
 * that have int value that represents the score in the game.
 * when hit happened, the score gets 5 more points.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor creating a new Score Tracking Listener.
     *
     * @param scoreCounter -Counter object that have int value that represents the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * setter- sets the Current Score.
     *
     * @param count the new value.
     */
    public void setCurrentScore(int count) {
        Counter counter = new Counter(count);
        this.currentScore = counter;
    }

    /**
     * getter -Returns the current Score.
     *
     * @return the current Score.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
