package seedu.address.model.person;

/**
 * The class for holding the event id for various commands
 */
public class ContactID {
    public static final String MESSAGE_NON_EMPTY = "Contact ID can not be empty!";

    private final int id;

    private ContactID(String id) {
        this.id = Integer.parseInt(id);
    }

    private ContactID(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Construct an {@code ContactID} object from {@code String}
     * @param id The id in {@code String}
     * @return The {@code ContactID} object
     */
    public static ContactID fromString(String id) {
        return new ContactID(id);
    }

    /**
     * Construct an {@code ContactID} object from {@code int}
     * @param id The id in {@code int}
     * @return The {@code ContactID} object
     */
    public static ContactID fromInt(int id) {
        return new ContactID(id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
