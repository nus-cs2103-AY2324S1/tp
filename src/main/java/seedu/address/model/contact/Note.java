package seedu.address.model.contact;



/**
 * Represents an arbitrary note string.
 */
public class Note {
    public final String text;

    /**
     * Constructs a Note.
     *
     * @param _text Note string.
     */
    public Note(String _text) {
        this.text = _text;
    }

    @Override
    public boolean equals(Object other) {
        // instanceof also handles nulls
        if (!(other instanceof Note)) {
            return false;
        }
        Note otherNote = (Note)other;

        if (otherNote == this) {
            return true;
        }

        return this.text.equals(otherNote.text);
    }

    @Override
    public int hashCode() {
        return this.text.hashCode();
    }

    @Override
    public String toString() {
        return this.text;
    }
}
