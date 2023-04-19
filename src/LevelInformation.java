
import java.util.List;

/**
 * all the data for specific level.
 */
public interface LevelInformation {
    /**
     * how many balls in the game.
     *
     * @return - numbers of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the balls Velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The initial starting points of each ball.
     *
     * @return - the beginning points of the balls.
     */
    List<Point> initialBallStartPoints();

    /**
     * The speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * The Width of the paddle.
     *
     * @return the Width of the paddle.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background sprite.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the blocks for this level.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
