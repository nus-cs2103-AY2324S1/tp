package seedu.address.model.employee;

/**
 * Represents an Employee's available overtime hours left in the address book.
 * Guarantees: always not less than zero
 */
public class OvertimeHours {

    public final int value;

    /**
     * Constructs a {@code OvertimeSalary}.
     */
    public OvertimeHours(int hours) {
        value = hours;
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
