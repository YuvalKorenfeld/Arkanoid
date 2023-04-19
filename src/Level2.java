
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * the information about the level 2 of the game.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new LinkedList<Velocity>();
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        initialBallVelocities.add(new Velocity(5, -5));
        return initialBallVelocities;
    }

    @Override
    public List<Point> initialBallStartPoints() {
        List<Point> initialBallStartPoints = new LinkedList<Point>();
        initialBallStartPoints.add(new Point(650, 380));
        initialBallStartPoints.add(new Point(600, 360));
        initialBallStartPoints.add(new Point(550, 340));
        initialBallStartPoints.add(new Point(500, 320));
        initialBallStartPoints.add(new Point(450, 300));
        initialBallStartPoints.add(new Point(350, 300));
        initialBallStartPoints.add(new Point(300, 320));
        initialBallStartPoints.add(new Point(250, 340));
        initialBallStartPoints.add(new Point(200, 360));
        initialBallStartPoints.add(new Point(150, 380));


        return initialBallStartPoints;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "math notebook";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(239, 236, 165));
                d.fillRectangle(0, 20, 800, 600);
                d.setColor(new Color(222, 36, 43));
                d.drawLine(70, 20, 70, 800);
                d.drawLine(72, 20, 72, 800);
                d.setColor(new Color(55, 114, 180));
                for (int i = 100; i < 820; i += 20) {
                    d.drawLine(10, i, 790, i);
                }
                d.setColor(new Color(255, 255, 255));
                d.fillCircle(40, 200, 10);
                d.fillCircle(40, 400, 10);
                d.setColor(new Color(0, 0, 0));
                d.drawCircle(40, 400, 10);
                d.drawCircle(40, 200, 10);
                d.drawText(100, 100, "infi 1 tirgul - with doron perelman", 15);

            }

            @Override
            public void timePassed() {
            }
        };
    }


    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<Block>();

        Block[] blocksInArr = new Block[19];

        //the borders of the screen.
        blocksInArr[15] = new Block(new Point(0, 20), 800, 10);
        blocksInArr[15].setColor(Color.GRAY);
        blocksInArr[16] = new Block(new Point(790, 20), 10, 600);
        blocksInArr[16].setColor(Color.GRAY);
        blocksInArr[17] = new Block(new Point(0, 20), 10, 600);
        blocksInArr[17].setColor(Color.GRAY);
        //death-region block
        blocksInArr[18] = new Block(new Point(0, 599), 800, 10);
        blocksInArr[18].setColor(Color.blue);


        Color color;
        //block sizes.
        int blockWidth = 52;
        int blockHeight = 25;

        Point firstPoint = new Point(790 - blockWidth, 150); //the first top right block.
        int firstRowNum = 15; // block in the top row.
        int numOfRows = 1; //number of rows.
        int index = 0;
        for (int i = 0; i < numOfRows; i++) {

            for (int j = 0; j < firstRowNum; j++) {
                switch (j) {
                    case (0):
                        color = new Color(136, 43, 189);
                        break;
                    case 1:
                        color = new Color(175, 81, 197);
                        break;
                    case 2:
                        color = new Color(170, 123, 215);
                        break;
                    case 3:
                        color = new Color(193, 167, 220);
                        break;
                    case 4:
                        color = new Color(149, 222, 236);
                        break;
                    case 5:
                        color = new Color(107, 206, 224);
                        break;
                    case 6:
                        color = new Color(52, 161, 182);
                        break;
                    case 7:
                        color = new Color(31, 120, 136);
                        break;
                    case 8:
                        color = new Color(23, 102, 117);
                        break;
                    case 9:
                        color = new Color(19, 93, 107);
                        break;
                    case 10:
                        color = new Color(31, 86, 96);
                        break;
                    case 11:
                        color = new Color(24, 60, 107);
                        break;
                    case 12:
                        color = new Color(24, 53, 117);
                        break;
                    case 13:
                        color = new Color(44, 54, 117);
                        break;
                    case 14:
                        color = new Color(46, 73, 108);
                        break;
                    default:
                        color = new Color(56, 80, 117);
                }
                Point cerrentUperLeft = new Point((int) firstPoint.getX() - (j * blockWidth),
                        (int) firstPoint.getY() + (i * blockHeight));
                blocksInArr[index] = new Block(cerrentUperLeft, blockWidth, blockHeight);
                blocksInArr[index].setColor(color);
                index++;
            }
        }


        for (int i = 0; i < blocksInArr.length; i++) {
            blocks.add(blocksInArr[i]);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
