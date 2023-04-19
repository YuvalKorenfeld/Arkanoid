
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * This class will be in charge of creating the different levels,
 * and moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private int score;

    /**
     * constructor creates a new game flow.
     *
     * @param gui - the gui the game played on.
     * @param ar  - the animation runner - runs the game animation.
     * @param ks  - the Keyboard Sensor - checks for any presses in the Keyboard.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.score = 0;
    }

    /**
     * creates the different levels,
     * and moving from one level to the next.
     *
     * @param levels - the levels in the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(gui, levelInfo, score);
            level.initialize();
            while (level.gameStatus() == null) {
                level.run();
            }
            if (level.gameStatus() == 0) {
                score = level.getScoreEndOfLevel();
                KeyPressStoppableAnimation keyPressStoppableAnimation =
                        new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, new LoseScreen(this.ks, score));
                this.ar.run(keyPressStoppableAnimation);
                gui.close();
                break;
            }
            score = level.getScoreEndOfLevel();
        }
        KeyPressStoppableAnimation keyPressStoppableAnimation1 =
                new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, new WinScreen(this.ks, score));
        this.ar.run(keyPressStoppableAnimation1);
        gui.close();

    }


}
