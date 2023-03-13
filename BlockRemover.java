// 207801887 Yuval Korenfeld

/**
 * Block Remover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor creating a new Block Remover.
     *
     * @param gameLevel          - the game.
     * @param removedBlocks - number of removed Blocks - Counter object.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * setter- sets the number of remaining Blocks.
     *
     * @param count the new value.
     */
    public void setRemainingBlocks(int count) {
        Counter counter = new Counter(count);
        this.remainingBlocks = counter;
    }

    /**
     * getter -Returns the number of remaining Blocks.
     *
     * @return the number of remaining Blocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * removing the listener from the block.
     *
     * @param hitter   - the ball object that hits the block.
     * @param beingHit -the block object that was hits by the ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
