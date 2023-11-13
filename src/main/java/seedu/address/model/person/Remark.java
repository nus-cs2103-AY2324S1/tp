package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remarks can take any values, and it can be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final Remark NULL_REMARK;

    static {
        try {
            NULL_REMARK = new Remark("");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        value = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        if (test.trim().equals("")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return value.equals(otherRemark.value);
    }

    /**
     * Factory method to create a Remark object.
     * @param remark
     * @return Remark object
     * @throws IllegalArgumentException
     */
    public static Remark of(String remark) throws IllegalArgumentException {
        if (!isValidRemark(remark)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        } else if (remark.isBlank()) {
            return Remark.NULL_REMARK;
        } else {
            return new Remark(remark);
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
