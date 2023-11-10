package seedu.address.model;

/**
 * A class returning the duration of time
 */
public class Duration {

    private final int duration;

    public Duration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns the length of duration
     * @return duration in minutes
     */
    public int getDurationInMin() {
        return this.duration;
    }

}
