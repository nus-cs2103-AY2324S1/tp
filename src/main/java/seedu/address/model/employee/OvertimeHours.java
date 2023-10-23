package seedu.address.model.employee;

/**
 * Represents an Employee's available overtime hours left in the address book.
 * Guarantees: always not less than zero
 */
public class OvertimeHours {

    public static final String MESSAGE_CONSTRAINTS = "Number of overtime hours left"
            + " should not exceed above 72 or below 0";

    public final int value;

    /**
     * Constructs a {@code OvertimeSalary}.
     */
    public OvertimeHours(int hours) {
        value = hours;
    }

    public static boolean isValidOvertimeHours(int hours) {
        return hours >= 0 && hours <= 72;
    }

    @Override
    public String toString() {
        return value + " hours";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OvertimeHours)) {
            return false;
        }

        OvertimeHours otherOvertimeHours = (OvertimeHours) other;
        return value == otherOvertimeHours.value;
    }

    @Override
    public int hashCode() {
        Integer thisVal = value;
        return thisVal.hashCode();
    }
}
