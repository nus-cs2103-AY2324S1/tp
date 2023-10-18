package seedu.address.model.person;

/**
 * Represents a Note in the address book.
 */
public class Note {
    private final String text;

    public Note() {
        this.text = "";
    }

    public Note(String note) {
        this.text = note;
    }

    @Override
    public String toString() {
        return this.text;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && text.equals(((Note) other).text)); // state check
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
