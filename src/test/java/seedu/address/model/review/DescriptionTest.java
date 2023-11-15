package seedu.address.model.review;

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
    public void equals() {
        Description description = new Description("very good");
        Description descriptionCopy = new Description("very good");
        Description description2 = new Description("very nice");

        // same object -> returns true
        assertTrue(description.equals(description));

        // same values -> returns true
        assertTrue(description.equals(descriptionCopy));

        // different types -> returns false
        assertFalse(description.equals(1));

        // null -> returns false
        assertFalse(description.equals(null));

        // different description -> returns false
        assertFalse(description.equals(description2));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Description.isValidDescription("chicken rice")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("chicken rice 2")); // alphanumeric characters
        assertTrue(Description.isValidDescription("Chicken Rice")); // with capital letters
        assertTrue(Description.isValidDescription("Chicken noodle soup with a soda on the side")); // long names
        assertTrue(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertTrue(Description.isValidDescription("chicken rice*")); // contains non-alphanumeric characters
    }

}
