package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's car insurance policy expiry date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPolicyExpiryDate(String)}
 */
public class PolicyExpiryDate {

    public static final String MESSAGE_CONSTRAINTS
            = "Policy expiry date should be in the format dd-mm-yyyy";

    /*
     * Should be in the format dd-mm-yyyy, checked with SimpleDateFormat
     */
    public static final String VALIDATION_DATE_FORMAT = "dd-MM-yyyy";

    public final LocalDate date;

    /**
     * Constructs a {@code PolicyExpiryDate}.
     *
     * @param policyExpiryDate A valid policy expiry date.
     */
    public PolicyExpiryDate(String policyExpiryDate) {
        requireNonNull(policyExpiryDate);
        checkArgument(isValidPolicyExpiryDate(policyExpiryDate), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(policyExpiryDate);
    }

    /**
     * Returns true if a given string is a valid policy expiry date.
     */
    public static boolean isValidPolicyExpiryDate(String test) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(VALIDATION_DATE_FORMAT);
            format.parse(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyExpiryDate)) {
            return false;
        }

        PolicyExpiryDate otherPolicyExpiryDate = (PolicyExpiryDate) other;
        return date.equals(otherPolicyExpiryDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
