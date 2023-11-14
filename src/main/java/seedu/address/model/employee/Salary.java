package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary implements Comparable<Salary> {

    public static final String MESSAGE_CONSTRAINTS = "Salary should be a non-empty positive integer.";

    public static final String VALIDATION_REGEX = "\\d{1,}";

    public final String value;

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        value = salary;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public double getOvertimeHourlyRate() {
        return Double.parseDouble(value) * 1.5 / (52 * 44);
    }

    @Override
    public String toString() {
        return "$" + value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Salary)) {
            return false;
        }

        Salary otherSalary = (Salary) other;
        return value.equals(otherSalary.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Salary other) {
        return Integer.parseInt(value) - Integer.parseInt(other.value);
    }
}
