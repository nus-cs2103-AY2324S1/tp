package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's deduction of salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Deduction extends Payment {
    private final Reason reason;

    /**
     * Constructs a {@code Deduction}.
     *
     * @param value A valid deduction.
     * @param reason A valid reason.
     */
    public Deduction(String value, Reason reason) {
        super(value);
        requireNonNull(reason);
        this.reason = reason;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Deduction)) {
            return false;
        }

        Deduction otherDeduction = (Deduction) other;
        return value.equals(otherDeduction.value)
                && reason.equals(otherDeduction.reason);
    }

    @Override
    public String toString() {
        return "Deduction: $" + value + " (" + reason + ")";
    }
}
