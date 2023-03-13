// 207801887 Yuval Korenfeld

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * this class is a Game object. creating new game
 * with all the parameters given in the constructor.
 */
public class GameLevel implements Animation {
    private boolean running;
    private LevelInformation levelInformation;
    private int score;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private AnimationRunner runner;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter counterPointScore = new Counter(0);
    private ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(counterPointScore);
    private ScoreIndicator scoreIndicator = new ScoreIndicator(scoreTrackingListener);
    private KeyboardSensor keyboard;

    /**
     * constructor creats a new game level.
     *
     * @param gui              - the gui the level will played on.
     * @param levelInformation - contains all the specific level information - for that exact level.
     * @param score            - the score the level begins with.(cam be 0 or different number from previous levels).
     */
    public GameLevel(GUI gui, LevelInformation levelInformation, int score) {
        this.gui = gui;
        this.runner = new AnimationRunner(this.gui, 60);
        this.keyboard = gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
        this.score = score;

    }


    /**
     * adding new Collidable to the Collidable list.
     *
     * @param c - the new Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adding new Sprite to the Sprite list.
     *
     * @param s - the new Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * getter- returns the environment.
     *
     * @return GameEnvironment the environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * gets all the blocks from the level information in specific pattern
     * add the listeners to them,and adds them to the game.
     *
     * @param ballRemover  - the ball remover listener.
     * @param blockRemover - the block remover listener.
     */
    public void generateBlock(BlockRemover blockRemover, BallRemover ballRemover) {
        List<Block> blocks = levelInformation.blocks();
        blocks.get(levelInformation.blocks().size() - 1).addHitListener(ballRemover);

        for (int i = 0; i < levelInformation.blocks().size(); i++) {
            //do for all the blocks beside the borders
            if (i < levelInformation.blocks().size() - 4) {
                blocks.get(i).addHitListener(blockRemover);
                blocks.get(i).addHitListener(scoreTrackingListener);
            }
            blocks.get(i).addToGame(this);
        }

    }

    /**
     * gets all the balls from the level information in specific pattern
     * creates them and adds them to the game.
     */
    public void generateBalls() {
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball1 = new Ball((int) levelInformation.initialBallStartPoints().get(i).getX(),
                    (int) levelInformation.initialBallStartPoints().get(i).getY(), 7, Color.black);
            ball1.setVelocity(levelInformation.initialBallVelocities().get(i).getDx(),
                    levelInformation.initialBallVelocities().get(i).getDy());
            ball1.addToGame(this);
        }

    }

    /**
     * gets pedal from the level information in specific size and speed
     * creates it and adds it to the game.
     */
    public void generatePaddle() {
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                new Block(new Point(800 / 2, 590 - 15), levelInformation.paddleWidth(), 8),
                levelInformation.paddleSpeed());
        paddle.setColor(new Color(56, 76, 86, 255));
        paddle.addToGame(this);
    }

    /**
     * Initialize a new game: with all the needed components.
     */
    public void initialize() {
        sprites.addSprite(scoreIndicator);
        sprites.addSprite(new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.drawText(500, 17, "level name: " + levelInformation.levelName(), 15);
            }

            @Override
            public void timePassed() {
                //nothing
            }
        });
        sprites.addSprite(levelInformation.getBackground());
        Counter counterBlock = new Counter(levelInformation.numberOfBlocksToRemove());
        Counter counterBall = new Counter(levelInformation.numberOfBalls());
        blockRemover = new BlockRemover(this, counterBlock);
        ballRemover = new BallRemover(this, counterBall);
        ballRemover.setRemainingBalls(levelInformation.numberOfBalls());
        blockRemover.setRemainingBlocks(levelInformation.numberOfBlocksToRemove());
        generateBalls();
        generatePaddle();
        generateBlock(blockRemover, ballRemover);
        scoreIndicator.getScoreTrackingListener().setCurrentScore(score);
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * removing given Collidable from Collidable list.
     *
     * @param c - the given Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removing given Sprite from Sprite list.
     *
     * @param s - the given Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blockRemover.getRemainingBlocks().getValue() == 0
                || ballRemover.getRemainingBalls().getValue() == 0) {
            if (blockRemover.getRemainingBlocks().getValue() == 0) {
                scoreIndicator.getScoreTrackingListener().setCurrentScore(scoreIndicator
                        .getScoreTrackingListener().getCurrentScore().getValue() + 100);
            }
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation keyPressStoppableAnimation =
                    new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard));
            this.runner.run(keyPressStoppableAnimation);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * the game status tell the program if the ame ends in on way or another.
     *
     * @return - player removes all the blocks return 1.
     * player lose all the balls return 0. if the player not won or lose return null.
     */
    public Integer gameStatus() {
        if (blockRemover.getRemainingBlocks().getValue() == 0
                || ballRemover.getRemainingBalls().getValue() == 0) {
            if (blockRemover.getRemainingBlocks().getValue() == 0) {
                return 1;
            }
            return 0;
        }
        return null;
    }

    /**
     * returns the score in the end of the level
     * to continue the counting in the next level from the same number.
     *
     * @return the score in the end.
     */
    public int getScoreEndOfLevel() {
        return scoreIndicator.getScoreTrackingListener().getCurrentScore().getValue();
    }
}
