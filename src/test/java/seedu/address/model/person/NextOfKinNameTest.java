package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NextOfKinNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NextOfKinName(null));
    }

    @Test
    public void constructor_invalidNextOfKinName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new NextOfKinName(invalidName));
    }

    @Test
    public void isValidNextOfKinName() {
        // null name
        assertThrows(NullPointerException.class, () -> NextOfKinName.isValidName(null));

        // invalid name
        assertFalse(NextOfKinName.isValidName("")); // empty string
        assertFalse(NextOfKinName.isValidName(" ")); // spaces only
        assertFalse(NextOfKinName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(NextOfKinName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(NextOfKinName.isValidName("peter jack")); // alphabets only
        assertTrue(NextOfKinName.isValidName("12345")); // numbers only
        assertTrue(NextOfKinName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(NextOfKinName.isValidName("Capital Tan")); // with capital letters
        assertTrue(NextOfKinName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        NextOfKinName name = new NextOfKinName("Valid NextOfKinName");

        // same values -> returns true
        assertTrue(name.equals(new NextOfKinName("Valid NextOfKinName")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new NextOfKinName("Other Valid NextOfKinName")));
    }
}
