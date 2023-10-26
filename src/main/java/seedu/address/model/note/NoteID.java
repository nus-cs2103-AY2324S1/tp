package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

/**
 * Represents the note id for DeleteEventCommand
 */
public class NoteID {
    public static final String MESSAGE_NON_EMPTY = "Note ID should not be blank";

    private final int id;

    private NoteID(String id) {
        requireNonNull(id);
        this.id = Integer.parseInt(id);
    }

    private NoteID(int id) {
        requireNonNull(id);
        this.id = id;
    }

    /**
     * Gets the numerical id
     * @return The numerical id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Constructs an {@code NoteID} object from {@code String}
     * @param id The id in {@code String}
     * @return The {@code NoteID} object
     */
    public static NoteID fromString(String id) {
        return new NoteID(id);
    }

    /**
     * Constructs an {@code NoteID} object from {@code int}
     * @param id The id in {@code int}
     * @return The {@code NoteID} object
     */
    public static NoteID fromInt(int id) {
        return new NoteID(id);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteID)) {
            return false;
        }

        NoteID otherNoteID = (NoteID) other;
        return id == otherNoteID.id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
