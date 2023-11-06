package seedu.address.model.person;

/**
 * Represents a Person's income in the address book.
 */
public class Income {

    public static final String MESSAGE_CONSTRAINTS =
            "Income should only contain positive integers less than 2147483648 and it should not be blank";

    public final Integer value;

    /**
    * Constructs a {@code Income}.
    *
    * @param income A valid income.
    */
    public Income(Integer income) {
        value = income;
    }

    /**
    * Returns true if a given string is a valid income.
    */
    public static boolean isValidIncome(String test) {
        try {
            int income = Integer.parseInt(test);
            return income >= 0;
        } catch (NumberFormatException e) {
            // If integer is too big, the exception will be thrown
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
