package seedu.address.model.event;

/**
 * The class for holding the event id for DeleteEventCommand
 */
public class EventID {
    public static final String MESSAGE_NON_EMPTY = "Event ID can not be empty!";

    private final int id;
    private EventID(String id) {
        this.id = Integer.parseInt(id);
    }
    private EventID(int id) {
        this.id = id;
    }

    /**
     * Get the numerical id
     * @return The numerical id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Construct an {@code EventID} object from {@code String}
     * @param id The id in {@code String}
     * @return The {@code EventID} object
     */
    public static EventID fromString(String id) {
        return new EventID(id);
    }

    /**
     * Construct an {@code EventID} object from {@code int}
     * @param id The id in {@code int}
     * @return The {@code EventID} object
     */
    public static EventID fromInt(int id) {
        return new EventID(id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
