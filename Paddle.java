// 207801887 Yuval Korenfeld

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * this class is a Paddle object. contains block and controls the movement by the keyboard.
 */
public class Paddle implements Collidable, Sprite {
    private biuoop.KeyboardSensor keyboard;
    private Block blockPaddle;
    private GameEnvironment environment;
    private Color color;
    private int sizeOfSideBars = 10; //the blocks in the side of the screen.
    private int maxScreenSizeX = 800; //the width of thr screen (the gui).
    private int scalarSizeOneClick = 15; // the size of one step made by the paddle.
    /**
     * if positive (value= 1) goes to right- with the axis x.
     * if negative (value= -1) goes to left - against the axis x.
     */
    private int nextMoveToDirection = 1;

    /**
     * constructor creating a new Paddle.
     *
     * @param keyboardSensor     -keyboardSensor controlled by the player - uses the gui.
     * @param block              - the block of the paddle.
     * @param scalarSizeOneClick - the size of one step made by the paddle.
     */
    public Paddle(KeyboardSensor keyboardSensor, Block block, int scalarSizeOneClick) {
        this.keyboard = keyboardSensor;
        this.blockPaddle = block;
        this.scalarSizeOneClick = scalarSizeOneClick;

    }

    /**
     * constructor creating a new Paddle.
     *
     * @param keyboardSensor -keyboardSensor controlled by the player - uses the gui.
     * @param block          - the block of the paddle.
     */
    public Paddle(KeyboardSensor keyboardSensor, Block block) {
        this.keyboard = keyboardSensor;
        this.blockPaddle = block;

    }

    /**
     * constructor creating a new Paddle.
     *
     * @param keyboardSensor -keyboardSensor controlled by the player - uses the gui.
     * @param upperLeft      - upper Left point of the block of the paddle.
     * @param height         - the height of the block of the paddle.
     * @param width          - the width of the block of the paddle.
     */
    public void createPaddle(Point upperLeft, double width, double height, KeyboardSensor keyboardSensor) {
        this.keyboard = keyboardSensor;
        this.blockPaddle = new Block(upperLeft, width, height);
    }

    /**
     * setter- sets the color value of this paddle.
     *
     * @param color the new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * setter- sets the size Of Side Bars value of this paddles game.
     *
     * @param sizeOfSideBars the new size.
     */
    public void setSizeOfSideBars(int sizeOfSideBars) {
        this.sizeOfSideBars = sizeOfSideBars;
    }

    /**
     * setter- sets the size of one step made by the paddle.
     *
     * @param scalarSizeOneClick the new size.
     */
    public void setScalarSizeOneClick(int scalarSizeOneClick) {
        this.scalarSizeOneClick = scalarSizeOneClick;
    }

    /**
     * setter- sets the size the width of thr screen (the gui) of this paddles game.
     *
     * @param maxScreenSizeX the new size.
     */
    public void setMaxScreenSizeX(int maxScreenSizeX) {
        this.maxScreenSizeX = maxScreenSizeX;
    }

    /**
     * setter- sets the direction of the paddle.
     * if positive (value= 1) goes to right- with the axis x.
     * if negative (value= -1) goes to left - against the axis x.
     *
     * @param nextMoveToDirection the new size.
     */
    public void setNextMoveToDirection(int nextMoveToDirection) {
        this.nextMoveToDirection = nextMoveToDirection;
    }

    /**
     * moves the paddle to the left, with checking the paddle stays on screen.
     */
    public void moveLeft() {
        int bound = sizeOfSideBars;
        if (blockPaddle.getRectangle().getUpperLeft().getX() <= bound) {
            //don't move you got to end of the screen.
            return;
        } else {
            Block nextStepBlock = new Block(new Point(blockPaddle.getRectangle().getUpperLeft()
                    .getX() - scalarSizeOneClick, blockPaddle.getRectangle().getUpperLeft().getY()),
                    blockPaddle.getRectangle().getWidth(), blockPaddle.getRectangle().getHeight());
            this.blockPaddle = nextStepBlock;
        }
    }

    /**
     * moves the paddle to the right, with checking the paddle stays on screen.
     */
    public void moveRight() {
        int bound = maxScreenSizeX - sizeOfSideBars;
        if (blockPaddle.getRectangle().getUpperLeft().getX() + blockPaddle.getRectangle().getWidth() >= bound) {
            //don't move you got to end of the screen.
            return;
        } else {
            Block nextStepBlock = new Block(new Point(blockPaddle.getRectangle().getUpperLeft()
                    .getX() + scalarSizeOneClick, blockPaddle.getRectangle().getUpperLeft().getY()),
                    blockPaddle.getRectangle().getWidth(), blockPaddle.getRectangle().getHeight());
            this.blockPaddle = nextStepBlock;
        }
    }

    /**
     * listening to the user action and moving the paddle as he wishes.
     * (Sprite function).
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            setNextMoveToDirection(-1);
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            setNextMoveToDirection(1);
            this.moveRight();
        }
    }

    /**
     * draws the paddle on the screen - fill and borders.
     * (Sprite function).
     *
     * @param d - the given DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.blockPaddle.getRectangle().getUpperLeft().getX(),
                (int) this.blockPaddle.getRectangle().getUpperLeft().getY(),
                (int) this.blockPaddle.getRectangle().getWidth(),
                (int) this.blockPaddle.getRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.blockPaddle.getRectangle().getUpperLeft().getX(),
                (int) this.blockPaddle.getRectangle().getUpperLeft().getY(),
                (int) this.blockPaddle.getRectangle().getWidth(),
                (int) this.blockPaddle.getRectangle().getHeight());
    }

    /**
     * returns the rectangle of the paddle.
     * (Collidable function).
     *
     * @return the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.blockPaddle.getRectangle();

    }

    /**
     * calculates the new velocity after the ball hits the paddle.
     * The behavior of the ball's bounce depends on where it hits the paddle.
     * If the ball hits the middle region (region 3),
     * it will keep its horizontal direction and only change its vertical one
     * If we hit region 1, the ball will bounce back with an angle of 300 degrees (-60),
     * regardless of where it came from.Similarly, for region 2 is will bounce back 330 degrees,
     * for region 4 it will bounce in 30 degrees, and for region 5 in 60 degrees.
     * (Collidable function).
     *
     * @param hitter
     * @param collisionPoint  - the point rhe ball meets the paddle.
     * @param currentVelocity - tve velocity before the meeting.
     * @return the new velocity after the meeting.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity velocityDirection = this.blockPaddle.hit(null, collisionPoint, currentVelocity);

        double paddleSize = this.blockPaddle.getRectangle().getWidth();
        double hittingX = collisionPoint.getX();
        double distanceFromUpperLeft = this.blockPaddle.getRectangle().getUpperLeft().
                distance(new Point(hittingX, this.blockPaddle.getRectangle().getUpperLeft().getY()));

        if (Math.abs(collisionPoint.getY() - this.blockPaddle.getRectangle().getUpperLeft().getY()) < 1E-10) {
            if (distanceFromUpperLeft < paddleSize / 5 * 1) {
                return Velocity.fromAngleAndSpeed(60,
                        -1 * Math.hypot(currentVelocity.getDx(), currentVelocity.getDy()));
            }
            if (distanceFromUpperLeft < paddleSize / 5 * 2) {
                return Velocity.fromAngleAndSpeed(30,
                        -1 * Math.hypot(currentVelocity.getDx(), currentVelocity.getDy()));
            }
            if (distanceFromUpperLeft < paddleSize / 5 * 3) {
                return velocityDirection;
            }
            if (distanceFromUpperLeft < paddleSize / 5 * 4) {
                return Velocity.fromAngleAndSpeed(330,
                        -1 * Math.hypot(currentVelocity.getDx(), currentVelocity.getDy()));
            }
            if (distanceFromUpperLeft < paddleSize / 5 * 5) {
                return Velocity.fromAngleAndSpeed(300,
                        -1 * Math.hypot(currentVelocity.getDx(), currentVelocity.getDy()));
            }
        }
        return velocityDirection;

    }

    /**
     * Add this paddle to the game. the paddle is a sprite and also a collidable.
     *
     * @param g - the given Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.environment = g.getEnvironment();
    }


}
