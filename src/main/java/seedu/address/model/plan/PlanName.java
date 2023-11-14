package seedu.address.model.plan;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Plan's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPlanName(String)}
 */
public class PlanName {

    public static final String MESSAGE_CONSTRAINTS =
            "Plan name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String planName;

    /**
     * Constructs a {@code PlanName}.
     *
     * @param name A valid name for a Plan.
     */
    public PlanName(String name) {
        requireNonNull(name);
        checkArgument(isValidPlanName(name), MESSAGE_CONSTRAINTS);
        planName = name;
    }

    /**
     * Returns true if a given string is a valid Plan name.
     */
    public static boolean isValidPlanName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return planName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.plan.PlanName)) {
            return false;
        }

        seedu.address.model.plan.PlanName otherName = (seedu.address.model.plan.PlanName) other;
        return planName.equalsIgnoreCase(otherName.planName);
    }

    @Override
    public int hashCode() {
        return planName.hashCode();
    }

}
