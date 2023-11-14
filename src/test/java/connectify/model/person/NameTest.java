package connectify.model.person;

import static connectify.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> PersonName.isValidName(null));

        // invalid name
        assertFalse(PersonName.isValidName("")); // empty string
        assertFalse(PersonName.isValidName(" ")); // spaces only
        assertFalse(PersonName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(PersonName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(PersonName.isValidName("peter jack")); // alphabets only
        assertTrue(PersonName.isValidName("12345")); // numbers only
        assertTrue(PersonName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(PersonName.isValidName("Capital Tan")); // with capital letters
        assertTrue(PersonName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        PersonName name = new PersonName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new PersonName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new PersonName("Other Valid Name")));
    }

    @Test
    public void compareTo() {
        PersonName name1 = new PersonName("Alice");
        PersonName name2 = new PersonName("Bob");

        assertEquals(name1.compareTo(name2), -1);
        assertEquals(name1.compareTo(name1), 0);
        assertEquals(name2.compareTo(name1), 1);
    }
}
