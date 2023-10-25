package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's benefit of salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Benefit extends Payment {
    private final Reason reason;

    /**
     * Constructs a {@code Benefit}.
     *
     * @param benefit A valid benefit.
     */
    public Benefit(String benefit, Reason reason) {
        super(benefit);
        requireNonNull(reason);
        this.reason = reason;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Benefit)) {
            return false;
        }

        Benefit otherBenefit = (Benefit) other;
        return value.equals(otherBenefit.value)
                && reason.equals(otherBenefit.reason);
    }

    @Override
    public String toString() {
        return "Benefit: $" + value + " (" + reason + ")";
    }
}
