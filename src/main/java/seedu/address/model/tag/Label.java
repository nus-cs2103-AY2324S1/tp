package seedu.address.model.tag;

/**
 * Represents a Label in the address book.
 * Guarantees: immutable;
 */
public abstract class Label {
    public final String name;

    public Label(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Label)) {
            return false;
        }

        Label otherLabel = (Label) other;
        return name.equals(otherLabel.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + name + ']';
    }
}
