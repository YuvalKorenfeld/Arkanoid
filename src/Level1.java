
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * the information about the level 1 of the game.
 */
public class Level1 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new LinkedList<Velocity>();
        initialBallVelocities.add(new Velocity(0, -5));
        return initialBallVelocities;
    }

    @Override
    public List<Point> initialBallStartPoints() {
        List<Point> initialBallStartPoints = new LinkedList<Point>();
        initialBallStartPoints.add(new Point(400, 450));
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
        return "falling stars";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(227, 130, 156));
                d.fillRectangle(0, 20, 800, 600);
                d.setColor(new Color(255, 255, 255));
                d.fillCircle(100, 200, 60);
                d.setColor(new Color(227, 130, 156));
                d.fillCircle(120, 180, 60);

                d.setColor(new Color(255, 255, 255));

                d.fillCircle(500, 400, 5);
                d.drawLine(500, 400, 750, 50);
                d.drawLine(500, 385, 500, 415);
                d.drawLine(485, 400, 515, 400);

                d.fillCircle(550, 100, 5);
                d.drawLine(550, 100, 600, 20);
                d.drawLine(550, 85, 550, 115);
                d.drawLine(535, 100, 565, 100);

                d.fillCircle(200, 450, 5);
                d.drawLine(200, 450, 500, 100);
                d.drawLine(200, 465, 200, 435);
                d.drawLine(185, 450, 215, 450);

                d.fillCircle(650, 100, 5);
                d.drawLine(650, 100, 720, 30);
                d.drawLine(650, 85, 650, 115);
                d.drawLine(635, 100, 665, 100);

                d.fillCircle(680, 200, 5);
                d.drawLine(680, 200, 800, 50);
                d.drawLine(680, 185, 680, 215);
                d.drawLine(665, 200, 695, 200);


            }

            @Override
            public void timePassed() {
            }
        };
    }


    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<Block>();

        Block[] blocksInArr = new Block[5];

        //the borders of the screen.
        blocksInArr[1] = new Block(new Point(0, 20), 800, 10);
        blocksInArr[1].setColor(Color.GRAY);
        blocksInArr[2] = new Block(new Point(790, 20), 10, 600);
        blocksInArr[2].setColor(Color.GRAY);
        blocksInArr[3] = new Block(new Point(0, 20), 10, 600);
        blocksInArr[3].setColor(Color.GRAY);
        //death-region block
        blocksInArr[4] = new Block(new Point(0, 599), 800, 10);
        blocksInArr[4].setColor(Color.blue);


        Color color;
        //block sizes.
        int blockWidth = 50;
        int blockHeight = 50;

        Point firstPoint = new Point(400 - (int) (blockWidth / 2), 150); //the first top right block.
        int firstRowNum = 1; // block in the top row.
        int numOfRows = 1; //number of rows.
        int index = 0;
        for (int i = 0; i < numOfRows; i++) {

            for (int j = 0; j < firstRowNum; j++) {
                switch (j) {
                    case (0):
                        color = Color.darkGray;
                        break;
                    case 1:
                        color = Color.red;
                        break;
                    case 2:
                        color = Color.gray;
                        break;
                    case 3:
                        color = Color.gray;
                        break;
                    case 4:
                        color = Color.blue;
                        break;
                    case 5:
                        color = Color.blue;
                        break;
                    case 6:
                        color = Color.CYAN;
                        break;
                    case 7:
                        color = Color.CYAN;
                        break;
                    case 8:
                        color = Color.cyan;
                        break;
                    case 9:
                        color = Color.red;
                        break;
                    case 10:
                        color = Color.red;
                        break;
                    case 11:
                        color = Color.green;
                        break;
                    case 12:
                        color = Color.green;
                        break;
                    case 13:
                        color = Color.pink;
                        break;
                    case 14:
                        color = Color.pink;
                        break;
                    default:
                        color = Color.black;
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
        return 1;
    }
}
