// 207801887 Yuval Korenfeld

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Key Press Stoppable Animation generic class runs and stops given animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed = true;
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean end = false;

    /**
     * creates new Key Press Stoppable Animation.
     *
     * @param animation - the animation.
     * @param key       - the key that will be pressed.
     * @param sensor    - the sensor that will say that key was pressed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                end = true;
            }
        }
        if (!this.sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return end;
    }
}
