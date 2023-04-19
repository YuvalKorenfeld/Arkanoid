// 207801887 Yuval Korenfeld

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * the information about the level 4 of the game.
 */
public class Level4 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new LinkedList<Velocity>();
        initialBallVelocities.add(new Velocity(3, -4));
        initialBallVelocities.add(new Velocity(3, -3));
        initialBallVelocities.add(new Velocity(3, -3));
        return initialBallVelocities;
    }

    @Override
    public List<Point> initialBallStartPoints() {
        List<Point> initialBallStartPoints = new LinkedList<Point>();
        initialBallStartPoints.add(new Point(450, 500));
        initialBallStartPoints.add(new Point(400, 450));
        initialBallStartPoints.add(new Point(350, 500));
        return initialBallStartPoints;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "god eye";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(215, 241, 177));
                d.fillRectangle(0, 20, 800, 600);

                d.setColor(new Color(220, 164, 44));
                for (int i = 20; i < 780; i += 10) {
                    d.drawLine(100, 70, i, 150);
                }
                d.setColor(new Color(18, 62, 129));
                d.fillCircle(100, 70, 20);
                d.drawLine(100, 90, 170, 70);
                d.drawLine(100, 50, 170, 70);
                d.drawLine(100, 90, 30, 70);
                d.drawLine(100, 50, 30, 70);
                d.setColor(new Color(255, 255, 255));
                d.fillCircle(100, 70, 10);

            }

            @Override
            public void timePassed() {
            }
        };
    }


    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<Block>();

        Block[] blocksInArr = new Block[109];

        //the borders of the screen.
        blocksInArr[105] = new Block(new Point(0, 20), 800, 10);
        blocksInArr[105].setColor(Color.GRAY);
        blocksInArr[106] = new Block(new Point(790, 20), 10, 600);
        blocksInArr[106].setColor(Color.GRAY);
        blocksInArr[107] = new Block(new Point(0, 20), 10, 600);
        blocksInArr[107].setColor(Color.GRAY);
        //death-region block
        blocksInArr[108] = new Block(new Point(0, 599), 800, 10);
        blocksInArr[108].setColor(Color.blue);


        Color color;
        //block sizes.
        int blockWidth = 52;
        int blockHeight = 25;

        Point firstPoint = new Point(790 - blockWidth, 150); //the first top right block.
        int firstRowNum = 15; // block in the top row.
        int numOfRows = 7; //number of rows.
        int index = 0;
        for (int i = 0; i < numOfRows; i++) {
            switch (i) {
                case 0:
                    color = new Color(215, 55, 26);
                    break;
                case 1:
                    color = new Color(171, 85, 13);
                    break;
                case 2:
                    color = new Color(217, 125, 86);
                    break;
                case 3:
                    color = new Color(225, 189, 121);
                    break;
                case 4:
                    color = new Color(218, 205, 69);
                    break;
                case 5:
                    color = new Color(164, 211, 69);
                    break;
                case 6:
                    color = new Color(100, 148, 54);
                    break;
                default:
                    color = new Color(32, 96, 14, 71);
            }
            for (int j = 0; j < firstRowNum; j++) {
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
        return 105;
    }
}
