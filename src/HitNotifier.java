
/**
 * creates the Punctuality to add and remove Hit Listeners.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl -the Hit Listener we want to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl -the Hit Listener we want to Remove.
     */
    void removeHitListener(HitListener hl);

}
