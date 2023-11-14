package seedu.address.model.policy;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;


/**
 * Represents a Policy in the address book.
 *  * Guarantees: immutable; name is valid as declared in {@link #isValidPolicyName(String)}
 */
public class Policy {

    public static final String POLICY_NAME_MESSAGE_CONSTRAINTS = "Policy names should be alphanumeric with spaces";
    public static final String ALPHANUMERIC_VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public static final String DESCRIPTION_MESSAGE_CONSTRAINTS = "Description should be alphanumeric with spaces";

    public static final String POLICY_VALUE_VALIDATION_REGEX = "^\\d+(\\.\\d+)?$";
    public static final String POLICY_VALUE_MESSAGE_CONSTRAINTS = "Policy value should be a valid decimal number";

    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should be in the format 'YYYY-MM-DD'";





    public final String policyName;
    public final String description;
    public final double policyValue;
    public final LocalDate startDate;
    public final LocalDate endDate;

    /**
     * Constructs a {@code Policy}.
     *
     * @param policyName A valid tag name.
     */

    public Policy(String policyName, String description, double policyValue, LocalDate startDate, LocalDate endDate) {
        requireNonNull(policyName);
        requireNonNull(description);
        requireNonNull(startDate);
        requireNonNull(endDate);
        checkArgument(isValidPolicyName(policyName), POLICY_NAME_MESSAGE_CONSTRAINTS);
        checkArgument(isValidDescription(description), DESCRIPTION_MESSAGE_CONSTRAINTS);
        checkArgument(isValidPolicyValue(Double.toString(policyValue)), POLICY_VALUE_MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(startDate), DATE_MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(endDate), DATE_MESSAGE_CONSTRAINTS);
        this.policyName = policyName;
        this.description = description;
        this.policyValue = policyValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a {@code Policy}.
     *
     * @param policyString A valid policy toString.
     */
    public static Policy fromString(String policyString) {
        String[] parts = policyString.split("\n");
        if (parts.length != 5) {
            // Handle invalid input string here, e.g., throw an exception or return null
            return null;
        }

        String policyName = parts[0].replace("Policy name: ", "");
        String description = parts[1].replace("Policy description: ", "");
        double policyValue = Double.parseDouble(parts[2].replace("Value: ", ""));
        LocalDate startDate = LocalDate.parse(parts[3].replace("Start date: ", ""));
        LocalDate endDate = LocalDate.parse(parts[4].replace("End date: ", ""));
        return new Policy(policyName, description, policyValue, startDate, endDate);
    }
    /**
     * Returns true if a given string is a valid policy name.
     */
    public static boolean isValidPolicyName(String test) {
        return test.matches(ALPHANUMERIC_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(ALPHANUMERIC_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid policy value.
     */
    public static boolean isValidPolicyValue(String test) {
        return test.matches(POLICY_VALUE_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given LocalDate object is a valid date.
     */
    public static boolean isValidDate(LocalDate date) {
        if (date == null) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date.format(formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public String getPolicyName() {
        return policyName;
    }

    public String getDescription() {
        return description;
    }

    public double getPolicyValue() {
        return policyValue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Policy policy = (Policy) o;
        return Double.compare(policy.policyValue, policyValue) == 0
                && Objects.equals(policyName, policy.policyName)
                && Objects.equals(description, policy.description)
                && Objects.equals(startDate, policy.startDate)
                && Objects.equals(endDate, policy.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyName, description, policyValue, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Policy name: " + policyName + "\n"
                + "Policy description: " + description + '\n'
                + "Value: " + policyValue + '\n'
                + "Start date: " + startDate + '\n'
                + "End date: " + endDate + '\n';

    }
}
