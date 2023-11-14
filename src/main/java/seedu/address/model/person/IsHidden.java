package seedu.address.model.person;

/**
 * Represents whether a Person is hidden in the address book.
 */
public class IsHidden {
    public final boolean value;

    /**
     * Constructs a {@code IsHidden}.
     */
    public IsHidden(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value ? "true" : "false";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IsHidden)) {
            return false;
        }

        IsHidden otherIsHidden = (IsHidden) other;
        return value == otherIsHidden.value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }

}
