package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class PriceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "5.5";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid price
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice("12")); // not a double
        assertFalse(Price.isValidPrice("phone")); // non-numeric
        assertFalse(Price.isValidPrice("12.3")); // less than 2 decimal places
        assertFalse(Price.isValidPrice("12.345")); // more than 2 decimal places
        assertFalse(Price.isValidPrice("012.345")); // leading zeros not removed for numbers >= 1.00
        assertFalse(Price.isValidPrice("00.34")); // more than 1 leading zeros for numbers < 1.00
        assertFalse(Price.isValidPrice("-5.34")); // negative double

        // valid price
        assertTrue(Price.isValidPrice("0.34")); // exactly 2 decimal places
        assertTrue(Price.isValidPrice("12.34")); // exactly 2 decimal places
        assertTrue(Price.isValidPrice("124293842033123.81")); // long prices
    }

    @Test
    public void equals() {
        Price price = new Price("12.34");

        // same values -> returns true
        assertTrue(price.equals(new Price("12.34")));

        // same object -> returns true
        assertTrue(price.equals(price));

        // null -> returns false
        assertFalse(price.equals(null));

        // different types -> returns false
        assertFalse(price.equals(5.0f));

        // different values -> returns false
        assertFalse(price.equals(new Price("12.35")));
    }
}
