package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ConsumptionCountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConsumptionCount(null));
    }

    @Test
    public void constructor_invalidConsumptionCount_throwsIllegalArgumentException() {
        String invalidConsumptionCount = "";
        assertThrows(IllegalArgumentException.class, () ->
                new ConsumptionCount(invalidConsumptionCount));
    }

    @Test
    public void isValidConsumptionCount() {
        // null consumptionCount
        assertThrows(NullPointerException.class, () -> ConsumptionCount.isValidConsumptionCount(null));

        // invalid consumptionCount
        assertFalse(ConsumptionCount.isValidConsumptionCount("")); // empty string
        assertFalse(ConsumptionCount.isValidConsumptionCount(" ")); // spaces only
        assertFalse(ConsumptionCount.isValidConsumptionCount("^")); // non-alphanumeric characters
        assertFalse(ConsumptionCount.isValidConsumptionCount("a")); // alphabets

        // valid consumptionCount
        assertTrue(ConsumptionCount.isValidConsumptionCount("1")); // single digit
        assertTrue(ConsumptionCount.isValidConsumptionCount("12")); // double digit
        assertTrue(ConsumptionCount.isValidConsumptionCount("02")); // trailing zero
    }

    @Test
    public void incrementCount() {
        ConsumptionCount consumptionCount = new ConsumptionCount("0");

        // Test incrementing
        consumptionCount.incrementCount(1);
        assertTrue(consumptionCount.getConsumptionCount().equals("1"));

        // Test incrementing with a larger number
        consumptionCount.incrementCount(5);
        assertTrue(consumptionCount.getConsumptionCount().equals("6"));

    }

    @Test
    public void decrementCount() {
        ConsumptionCount consumptionCount = new ConsumptionCount("10");

        // Test decrementing
        consumptionCount.decrementCount(1);
        assertTrue(consumptionCount.getConsumptionCount().equals("9"));

        // Test decrementing with a larger number
        consumptionCount.decrementCount(5);
        assertTrue(consumptionCount.getConsumptionCount().equals("4"));

    }

    @Test
    public void setConsumptionCount() {
        ConsumptionCount consumptionCount = new ConsumptionCount("0");
        ConsumptionCount consumptionCount1 = new ConsumptionCount("1");
        // Test setIsCompleted
        consumptionCount.setConsumptionCount("1");
        assertEquals(consumptionCount.getConsumptionCount(), consumptionCount1.getConsumptionCount());
    }

    @Test
    public void equals() {
        ConsumptionCount consumptionCount = new ConsumptionCount("0");

        // same values -> returns true
        assertTrue(consumptionCount.equals(new ConsumptionCount("0")));

        // same object -> returns true
        assertTrue(consumptionCount.equals(consumptionCount));

        // null -> returns false
        assertFalse(consumptionCount.equals(null));

        // different types -> returns false
        assertFalse(consumptionCount.equals(5.0f));

        // different values -> returns false
        assertFalse(consumptionCount.equals(new ConsumptionCount("1")));

        // Add more test cases for equals as needed
    }
}
