package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Person's benefit of salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Benefit extends Payment {
    public static final String MESSAGE_CONSTRAINTS_INVALID_REASON = "The reason for a benefit "
        + "should only be one of the following:\n"
        + "1. Transport Allowance\n"
        + "2. Annual Bonus\n";

    private static final ArrayList<Reason> REASONS_FOR_BENEFITS = new ArrayList<>(
        List.of(
            Reason.TRANSPORT_ALLOWANCE,
            Reason.ANNUAL_BONUS
        )
    );

    private final Reason reason;

    /**
     * Constructs a {@code Benefit}.
     *
     * @param benefit A valid benefit.
     */
    public Benefit(String benefit, Reason reason) {
        super(benefit);
        requireNonNull(reason);
        checkArgument(isValidReason(reason), MESSAGE_CONSTRAINTS_INVALID_REASON);
        this.reason = reason;
    }

    /**
     * Returns true if a given reason is a valid reason for benefits.
     */
    public boolean isValidReason(Reason test) {
        return REASONS_FOR_BENEFITS.contains(test);
    }

    /**
     * Returns true if the object is a valid benefit.
     */
    public boolean isValid() {
        return Payment.isValid(value) && isValidReason(reason);
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
