// 207801887 Yuval Korenfeld

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * the information about the level 3 of the game.
 */
public class Level3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new LinkedList<Velocity>();
        initialBallVelocities.add(new Velocity(-5, -6));
        initialBallVelocities.add(new Velocity(-6, -5));
        return initialBallVelocities;
    }

    @Override
    public List<Point> initialBallStartPoints() {
        List<Point> initialBallStartPoints = new LinkedList<Point>();
        initialBallStartPoints.add(new Point(350, 550));
        initialBallStartPoints.add(new Point(450, 550));
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
        return "swimming pool";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(110, 180, 193));
                d.fillRectangle(0, 20, 800, 600);
                d.setColor(new Color(34, 147, 168));
                d.fillRectangle(0, 20, 800, 400);
                d.setColor(new Color(34, 147, 168));
                for (int i = 0; i < 800; i += 30) {
                    d.fillCircle(i, 420, 17);
                }
                d.setColor(new Color(218, 40, 40));
                d.drawLine(0, 520, 800, 520);
                for (int i = 0; i < 7; i++) {
                    d.drawLine(0, 520 + i, 800, 520 + i);
                }
                d.drawText(40, 510, "maim redudim", 15);

            }


            @Override
            public void timePassed() {
            }
        };
    }


    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<Block>();

        Block[] blocksInArr = new Block[61];

        //the borders of the screen.
        blocksInArr[57] = new Block(new Point(0, 20), 800, 10);
        blocksInArr[57].setColor(Color.GRAY);
        blocksInArr[58] = new Block(new Point(790, 20), 10, 600);
        blocksInArr[58].setColor(Color.GRAY);
        blocksInArr[59] = new Block(new Point(0, 20), 10, 600);
        blocksInArr[59].setColor(Color.GRAY);
        //death-region block
        blocksInArr[60] = new Block(new Point(0, 599), 800, 10);
        blocksInArr[60].setColor(Color.blue);


        Color color;
        //block sizes.
        int blockWidth = 50;
        int blockHeight = 25;

        Point firstPoint = new Point(790 - blockWidth, 150); //the first top right block.
        int firstRowNum = 12; // block in the top row.
        int numOfRows = 6; //number of rows.
        int index = 0;
        for (int i = 0; i < numOfRows; i++) {
            switch (i) {
                case 0:
                    color = new Color(9, 38, 80);
                    break;
                case 1:
                    color = new Color(25, 63, 122);
                    break;
                case 2:
                    color = new Color(69, 96, 138);
                    break;
                case 3:
                    color = new Color(80, 164, 180);
                    break;
                case 4:
                    color = new Color(127, 217, 234);
                    break;
                case 5:
                    color = new Color(95, 124, 129);
                    break;
                default:
                    color = new Color(89, 96, 98);
            }
            for (int j = 0; j < firstRowNum; j++) {
                Point cerrentUperLeft = new Point((int) firstPoint.getX() - (j * blockWidth),
                        (int) firstPoint.getY() + (i * blockHeight));
                blocksInArr[index] = new Block(cerrentUperLeft, blockWidth, blockHeight);
                blocksInArr[index].setColor(color);
                index++;
            }
            firstRowNum--;
        }


        for (int i = 0; i < blocksInArr.length; i++) {
            blocks.add(blocksInArr[i]);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 57;
    }
}
