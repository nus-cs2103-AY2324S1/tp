package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public static final String MESSAGE_CONSTRAINTS = "Remark should be non-empty";
    public final String value;

    /**
     * Creates a Remark object with the specified remark value.
     *
     * @param remark The remark string to be associated with the person.
     * @throws NullPointerException if the provided remark is null.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
