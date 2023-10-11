package seedu.address.model.event;

/**
 * The class for holding the "information" part for an Event
 */
public class EventInformation {
    private final String information;

    private EventInformation() {
        this.information = "";
    }
    private EventInformation(String information) {
        this.information = information;
    }

    /**
     * Construct an {@code EventInformation} object from {@code String}
     * @param information The information in {@code String}
     * @return The {@code EventInformation} object
     */
    public static EventInformation fromString(String information) {
        return new EventInformation(information);
    }

    /**
     * Get the String representation of this {@code EventInformation} object
     * @return The String representation of this {@code EventInformation} object
     */
    @Override
    public String toString() {
        return this.information;
    }
}
