package connectify.model.person;

import static connectify.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PersonPriorityTest {

    @Test
    public void constructor_integerValue_success() {
        PersonPriority priority = new PersonPriority(1);
        assertTrue(priority.equals(new PersonPriority("1")));
    }

    @Test
    public void constructor_stringValue_success() {
        PersonPriority priority = new PersonPriority("1");
        assertTrue(priority.equals(new PersonPriority(1)));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "abc";
        assertThrows(IllegalArgumentException.class, () -> new PersonPriority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> PersonPriority.isValidPriority(null));

        // invalid priority
        assertFalse(PersonPriority.isValidPriority("")); // empty string
        assertFalse(PersonPriority.isValidPriority(" ")); // spaces only
        assertFalse(PersonPriority.isValidPriority("abc")); // alphabets only
        assertFalse(PersonPriority.isValidPriority("123abc")); // numbers and alphabets
        assertFalse(PersonPriority.isValidPriority("abc123")); // alphabets and numbers
        assertFalse(PersonPriority.isValidPriority("123 abc")); // numbers and alphabets with spaces
        assertFalse(PersonPriority.isValidPriority("abc 123")); // alphabets and numbers with spaces
        assertFalse(PersonPriority.isValidPriority("123 abc 123")); // numbers and alphabets with spaces

        // valid priority
        assertTrue(PersonPriority.isValidPriority("1")); // single digit
        assertTrue(PersonPriority.isValidPriority("10")); // double digit
        assertTrue(PersonPriority.isValidPriority("100")); // triple digit
        assertTrue(PersonPriority.isValidPriority("1000")); // quadruple digit
    }

    @Test
    public void toStringTest() {
        PersonPriority priority = new PersonPriority("1");
        assertTrue(priority.toString().equals("1"));
    }

    @Test
    public void equals() {
        PersonPriority priority = new PersonPriority("1");

        // same values -> returns true
        assertTrue(priority.equals(new PersonPriority("1")));

        // same object -> returns true
        assertTrue(priority.equals(priority));

        // null -> returns false
        assertFalse(priority.equals(null));

        // different types -> returns false
        assertFalse(priority.equals(5.0f));

        // different values -> returns false
        assertFalse(priority.equals(new PersonPriority("2")));
    }



}
