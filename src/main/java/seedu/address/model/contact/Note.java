package seedu.address.model.contact;



/**
 * Immutably represents a {@link Contact}'s note.
 */
public class Note {
    public final String value;

    public Note(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof also handles nulls
        if (!(other instanceof Note)) {
            return false;
        }
        Note otherNote = (Note)other;

        return this.value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
