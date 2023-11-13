package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid name
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("/")); // only /
        assertFalse(Description.isValidDescription("InvalidDescription//")); // description contains /

        // valid name
        assertTrue(Description.isValidDescription("Valid Description")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("Task 2")); // alphanumeric characters
        assertTrue(Description.isValidDescription("Capital Task")); // with capital letters
        assertTrue(Description.isValidDescription("This will be a looong description 111")); // long names
        assertTrue(Description.isValidDescription("This has other characters too !@#$....,,,*"));
    }

    @Test
    public void equals() {
        Description description = new Description("Valid Description");

        // same values -> returns true
        assertTrue(description.equals(new Description("Valid Description")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new Description("Other Valid Name")));
    }
}
