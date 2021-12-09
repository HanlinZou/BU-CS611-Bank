/**
 * Observer Pattern: Objects that require time (for example, objects which
 * handle interest) should implement TimeObserver interface to receive
 * notice when time changes.
 */
public interface TimerObserver {
    void timeChange();
}
