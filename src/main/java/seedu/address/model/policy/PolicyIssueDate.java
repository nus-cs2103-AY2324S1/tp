package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's car insurance policy issue date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPolicyIssueDate(String)}
 */
public class PolicyIssueDate {

    public static final String MESSAGE_CONSTRAINTS
            = "Policy issue date should be in the format dd-mm-yyyy";

    /*
     * Should be in the format dd-mm-yyyy, checked with SimpleDateFormat
     */
    public static final String VALIDATION_DATE_FORMAT = "dd-MM-yyyy";

    public final LocalDate date;

    /**
     * Constructs a {@code PolicyIssueDate}.
     *
     * @param policyIssueDate A valid policy issue date.
     */
    public PolicyIssueDate(String policyIssueDate) {
        requireNonNull(policyIssueDate);
        checkArgument(isValidPolicyIssueDate(policyIssueDate), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(policyIssueDate);
    }

    /**
     * Returns true if a given string is a valid policy issue date.
     */
    public static boolean isValidPolicyIssueDate(String test) {
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
        if (!(other instanceof PolicyIssueDate)) {
            return false;
        }

        PolicyIssueDate otherPolicyIssueDate = (PolicyIssueDate) other;
        return date.equals(otherPolicyIssueDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
