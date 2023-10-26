package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Person's deduction of salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Deduction extends Payment {
    public static final String MESSAGE_CONSTRAINTS_INVALID_REASON = "The reason for a deduction "
        + "should only be one of the following:\n"
        + "1. No Pay Leave\n"
        + "2. Absence\n"
        + "3. Employee CPF Deduction\n";

    private static final ArrayList<Reason> REASONS_FOR_DEDUCTIONS = new ArrayList<>(
        List.of(
            Reason.NO_PAY_LEAVE,
            Reason.ABSENCE,
            Reason.EMPLOYEE_CPF_DEDUCTION
        )
    );

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
        checkArgument(isValidReason(reason), MESSAGE_CONSTRAINTS_INVALID_REASON);
        this.reason = reason;
    }

    /**
     * Returns true if a given reason is a valid reason for deductions.
     */
    public boolean isValidReason(Reason test) {
        return REASONS_FOR_DEDUCTIONS.contains(test);
    }

    /**
     * Returns true if the object is a valid deduction.
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
