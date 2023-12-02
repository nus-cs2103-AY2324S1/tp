package seedu.address.model.delivery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class DeliveryNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeliveryName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new DeliveryName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> DeliveryName.isValidName(null));

        // invalid name
        assertFalse(DeliveryName.isValidName("")); // empty string
        assertFalse(DeliveryName.isValidName(" ")); // spaces only
        assertFalse(DeliveryName.isValidName("\n\r\t")); // escape sequences
        assertFalse(DeliveryName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(DeliveryName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(DeliveryName.isValidName("peter jack")); // alphabets only
        assertTrue(DeliveryName.isValidName("12345")); // numbers only
        assertTrue(DeliveryName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(DeliveryName.isValidName("Capital Tan")); // with capital letters
        assertTrue(DeliveryName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void compareTo() {
        assertTrue(new DeliveryName("A").compareTo(new DeliveryName("B")) < 0);
        assertTrue(new DeliveryName("B").compareTo(new DeliveryName("A")) > 0);
        assertTrue(new DeliveryName("A").compareTo(new DeliveryName("A")) == 0);
    }

    @Test
    public void equals() {
        DeliveryName name = new DeliveryName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new DeliveryName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new DeliveryName("Other Valid Name")));
    }
}
