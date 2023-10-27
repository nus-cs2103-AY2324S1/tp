package seedu.address.model.event;

/**
 * The class for holding the "information" part for an Event
 */
public class EventInformation {

    public static final String MESSAGE_CONSTRAINTS = "Event information can not be empty.";

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

    /**
     * Returns whether the given information in string is valid event information
     * @param str The information in string
     * @return True if the name is valid event information, false otherwise
     */
    public static boolean isValidEventInformation(String str) {
        return !str.isEmpty();
    }
}
