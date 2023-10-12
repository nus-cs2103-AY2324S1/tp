package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a car insurance policy number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPolicyNumber(String)}
 */
public class PolicyNumber {

    public static final String MESSAGE_CONSTRAINTS
            = "Policy numbers should be an alphanumeric string with 1-8 characters";

    /*
     * Should be an alphanumeric string with 1-8 characters
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]{1,8}$";
    public static final String DEFAULT_VALUE = "NOPOLICY";

    public final String value;

    /**
     * Constructs a {@code PolicyNumber}.
     *
     * @param policyNumber A valid policy number.
     */
    public PolicyNumber(String policyNumber) {
        requireNonNull(policyNumber);
        checkArgument(isValidPolicyNumber(policyNumber), MESSAGE_CONSTRAINTS);
        value = policyNumber;
    }

    /**
     * Returns true if a given string is a valid policy number.
     */
    public static boolean isValidPolicyNumber(String test) {
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
        if (!(other instanceof PolicyNumber)) {
            return false;
        }

        PolicyNumber otherPolicyNumber = (PolicyNumber) other;
        return value.equals(otherPolicyNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
