package seedu.address.model.person;

/**
 * Represents a Person's income in the address book.
 */
public class Income {

    public static final String MESSAGE_CONSTRAINTS = "Income should only contain numbers "
            + "and it should not be blank";

    public final Number value;

    /**
    * Constructs a {@code Income}.
    *
    * @param income A valid income.
    */
    public Income(Number income) {
        value = income;
    }

    /**
    * Returns true if a given string is a valid income.
    */
    public static boolean isValidIncome(String test) {
        try {
            Double.parseDouble(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Income // instanceof handles nulls
                && value.equals(((Income) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
