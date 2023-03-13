// 207801887 Yuval Korenfeld

import biuoop.GUI;

import java.util.LinkedList;
import java.util.List;

/**
 * the class Ass6Game runs all the game. creates the levels in given order in the args,
 * or if args are empty in regular order,creates the gui and begins to run the game.
 */
public class Ass6Game {
    /**
     * creates the gui the animation runner the game flow and begin the game.
     *
     * @param args - the  order foe rhe levels (if empty , plays the trivial order).
     */
    public static void main(String[] args) {
        List<LevelInformation> levelInformations = new LinkedList<LevelInformation>();
        Level4 level4 = new Level4();
        Level3 level3 = new Level3();
        Level2 level2 = new Level2();
        Level1 level1 = new Level1();

        if (args.length == 0) {
            levelInformations.add(level1);
            levelInformations.add(level2);
            levelInformations.add(level3);
            levelInformations.add(level4);
        } else {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "1":
                        levelInformations.add(level1);
                        break;
                    case "2":
                        levelInformations.add(level2);
                        break;
                    case "3":
                        levelInformations.add(level3);
                        break;
                    case "4":
                        levelInformations.add(level4);
                        break;
                    default:
                }
            }
        }

        GUI gui = new GUI("Game", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), gui);
        gameFlow.runLevels(levelInformations);

    }
}
