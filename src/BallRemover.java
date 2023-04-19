// 207801887 Yuval Korenfeld

/**
 * this class is a HitListener. Charge of removing balls, and updating an available-balls counter.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor creating a new Ball Remover.
     *
     * @param gameLevel           - the game.
     * @param remainingBalls - number of remaining balls - Counter object.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * setter- sets the number of remaining balls.
     *
     * @param count the new value.
     */
    public void setRemainingBalls(int count) {
        Counter counter = new Counter(count);
        this.remainingBalls = counter;
    }

    /**
     * getter -Returns the number of remaining balls.
     *
     * @return the number of remaining balls.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}






