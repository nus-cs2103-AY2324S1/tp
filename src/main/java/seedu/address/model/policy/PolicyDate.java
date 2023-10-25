package seedu.address.model.policy;

import seedu.address.model.month.DeleteMonth;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a car insurance policy issue / expiry date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPolicyDate(String)}
 */
public class PolicyDate implements Comparable<PolicyDate> {

    public static final String MESSAGE_CONSTRAINTS = "Policy issue / expiry date should be in the format dd-mm-yyyy";

    /*
     * Should be in the format dd-mm-yyyy, checked with SimpleDateFormat
     */
    public static final String VALIDATION_DATE_FORMAT = "dd-MM-yyyy";
    public static final String DEFAULT_VALUE = "01-01-1000";

    public static final String DEFAULT_COMPARISON_VALUE = "1000-01-01";

    public final LocalDate date;

    /**
     * Constructs a {@code PolicyDate}.
     *
     * @param policyDate A valid policy expiry date.
     */
    public PolicyDate(String policyDate) {
        requireNonNull(policyDate);
        checkArgument(isValidPolicyDate(policyDate), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(policyDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Returns true if a given string is a valid date in the format dd-mm-yyyy.
     */
    public static boolean isValidPolicyDate(String test) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(VALIDATION_DATE_FORMAT);
            format.parse(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Returns true if this date is in the month and year.
     * @param deleteMonth DeleteMonth object.
     * @return True if the date is in the month and year.
     */
    public boolean isInMonth(DeleteMonth deleteMonth) {
        int year = date.getYear();
        int month = date.getMonthValue();
        return (year == deleteMonth.getYear()) && (month == deleteMonth.getMonth());
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyDate)) {
            return false;
        }

        PolicyDate otherPolicyDate = (PolicyDate) other;
        return date.equals(otherPolicyDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public int compareTo(PolicyDate other) {
        return date.compareTo(other.date);
    }

}
