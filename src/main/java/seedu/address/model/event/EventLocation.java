package seedu.address.model.event;

/**
 * The class for holding the "location" part for an Event
 */
public class EventLocation {
    private final String location;

    private EventLocation() {
        this.location = "unknown";
    }

    private EventLocation(String location) {
        this.location = location;
    }

    /**
     * Construct an {@code EventLocation} object from {@code String}
     * 
     * @param location The location in String
     * @return The {@code EventLocation} object
     */
    public static EventLocation fromString(String location) {
        return new EventLocation(location);
    }

    /**
     * Get the String representation of this {@code EventLocation} object
     * 
     * @return The String representation of this {@code EventLocation} object
     */
    @Override
    public String toString() {
        return this.location;
    }
}
