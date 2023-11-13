package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        String invalidTagName2 = "CS!";

        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName2));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // Tag Names
        String invalidTagName = "CS2100S!";
        String validTagName = "CS1231s";

        assertTrue(Tag.isValidTagName(validTagName));
        assertFalse(Tag.isValidTagName(invalidTagName));
    }

    @Test
    public void equals() {
        Tag tag = new Tag("Valid Tag");

        // same values -> returns true
        assertEquals(tag, new Tag("Valid Tag"));

        // same object -> returns true
        assertEquals(tag, tag);

        // null -> returns false
        assertNotEquals(null, tag);

        // different types -> returns false
        assertNotEquals("clown", tag);

        // different values -> returns false
        assertNotEquals(tag, new Tag("Other Valid Tag"));
    }

    @Test
    public void hashcode() {
        Tag tag = new Tag("Valid Tag");

        // same values -> returns true cos same hash
        assertEquals(tag.hashCode(), new Tag("Valid Tag").hashCode());

        // null -> returns false since different hash
        assertFalse(tag.equals(null));

        // different types -> returns false since different hash
        assertNotEquals(tag.hashCode(), "clown".hashCode());

        // different values -> returns false since different hash
        assertNotEquals(tag.hashCode(), new Tag("Other Valid Tag").hashCode());
    }

}
