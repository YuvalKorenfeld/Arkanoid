// 207801887 Yuval Korenfeld

/**
 * Counter is a simple class that is used for counting things.
 * it can add and subtract number to or from current count.
 */
public class Counter {
    //the real int value.
    private int count;

    /**
     * constructor creating a new Counter.
     *
     * @param count - the value in the beginning.
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * add number to current count.
     *
     * @param number - the value we want to add.
     */
    void increase(int number) {
        count = count + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number - the value we want to subtract.
     */
    void decrease(int number) {
        count = count - number;
    }

    /**
     * get current count.
     *
     * @return the current value.
     */
    int getValue() {
        return count;
    }

    /**
     * set current count.
     *
     * @param count -the new value.
     */
    void setValue(int count) {
        this.count = count;
    }
}
