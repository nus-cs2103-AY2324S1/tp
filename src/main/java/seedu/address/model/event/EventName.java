package seedu.address.model.event;

/**
 * The class for holding the "name" part for an Event
 */
public class EventName {

    public static final String MESSAGE_CONSTRAINTS = "Event name can not be empty.";

    private final String name;

    private EventName(String name) {
        this.name = name;
    }

    /**
     * Construct an {@code EventName} object from {@code String}
     *
     * @param name The name in String
     * @return The {@code EventName} object
     */
    public static EventName fromString(String name) {
        return new EventName(name);
    }

    /**
     * Get the String representation of this {@code EventName} object
     *
     * @return The String representation of this {@code EventName} object
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns whether the given name in string is a valid event name
     * @param str The name in string
     * @return True if the name is a valid event name, false otherwise
     */
    public static boolean isValidEventName(String str) {
        return !str.isEmpty();
    }
}
