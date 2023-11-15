package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equals() {
        Tag tag1 = new Tag("Friends");
        Tag tag2 = new Tag("Friends");
        Tag tag3 = new Tag("Family");

        // Test with the same tags
        assertTrue(tag1.equals(tag1));

        // Test with different instances but the same name
        assertTrue(tag1.equals(tag2));

        // Test with different names
        assertFalse(tag1.equals(tag3));

        // Test with null
        assertFalse(tag1.equals(null));

        // Test with a different class
        assertFalse(tag1.equals("Friends"));
    }

    @Test
    public void testHashCode() {
        Tag tag1 = new Tag("Friends");
        Tag tag2 = new Tag("Friends");

        // Hash code should be the same for equal objects
        assertEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    public void testToString() {
        Tag tag = new Tag("Friends");

        // Check the string representation
        assertEquals("[Friends]", tag.toString());
    }

}
