package seedu.address.commons.core.increment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Salary;

public class IncrementTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Increment(null));
    }

    @Test
    public void constructor_invalidIncrement_throwsIllegalArgumentException() {
        String invalidIncrement = "";
        assertThrows(IllegalArgumentException.class, () -> new Increment(invalidIncrement));
    }

    @Test
    public void constructor_incrementExceedsMaximumLong() {
        Increment increment = new Increment("10000000000000000000");
        long expectedLongValue = Salary.MAXIMUM_SALARY_LONG + 1;
        String expectedString = "10000000000000000000.00";
        assertEquals(expectedLongValue, increment.getLongValue());
        assertEquals(expectedString, increment.toString());
    }

    @Test
    public void isValidIncrement() {
        // null increment
        assertThrows(NullPointerException.class, () -> Increment.isValidIncrement(null));

        // invalid increment
        assertFalse(Increment.isValidIncrement("")); // empty string
        assertFalse(Increment.isValidIncrement(" ")); // spaces only
        assertFalse(Increment.isValidIncrement("^")); // non-number
        assertFalse(Increment.isValidIncrement("10@")); // non-number with numbers
        assertFalse(Increment.isValidIncrement("1000.999")); // more than 2 decimal places

        // valid increment
        assertTrue(Increment.isValidIncrement("0.0")); // zero
        assertTrue(Increment.isValidIncrement("1000.99")); // non-negative number
        assertTrue(Increment.isValidIncrement("-150.1")); // negative number
    }

    @Test
    public void getLongValue() {
        long incrementAmount = 10000;
        String incrementAmountString = "100.00";
        Increment increment = new Increment(incrementAmountString);
        assertEquals(incrementAmount, increment.getLongValue());
    }

    @Test
    public void toStringMethod() {
        // no decimals
        String incrementAmount = "100";
        String expected = "100.00";
        Increment increment = new Increment(incrementAmount);
        assertEquals(expected, increment.toString());

        // one decimal
        incrementAmount = "100.0";
        expected = "100.00";
        increment = new Increment(incrementAmount);
        assertEquals(expected, increment.toString());

        // two decimals
        incrementAmount = "100.00";
        expected = "100.00";
        increment = new Increment(incrementAmount);
        assertEquals(expected, increment.toString());
    }

    @Test
    public void equals() {
        Increment increment = new Increment("100");

        // same values -> returns true
        assertTrue(increment.equals(new Increment("100")));

        // same object -> returns true
        assertTrue(increment.equals(increment));

        // null -> returns false
        assertFalse(increment.equals(null));

        // different types -> returns false
        assertFalse(increment.equals(5.0f));

        // different values -> returns false
        assertFalse(increment.equals(new Increment("10")));

        // different stored strings -> return false
        increment = new Increment(Long.toString(Salary.MAXIMUM_SALARY_LONG + 1));
        Increment otherIncrement = new Increment(Long.MAX_VALUE + "0");
        assertFalse(increment.equals(otherIncrement));
    }
}
