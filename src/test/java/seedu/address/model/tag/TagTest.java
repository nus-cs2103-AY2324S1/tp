package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    public static final Tag VALID_TAG = new Tag("tag");
    public static final Tag VALID_TAG_COPY = new Tag("tag");

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
        // same object -> returns true
        assertEquals(VALID_TAG, VALID_TAG);

        // same values -> returns true
        assertEquals(VALID_TAG, VALID_TAG_COPY);

        // null -> returns false
        assertNotEquals(VALID_TAG, null);

        // different types -> returns false
        assertNotEquals(VALID_TAG, 1);

        // different values -> returns false
        Tag differentTag = new Tag("differentTag");
        assertNotEquals(VALID_TAG, differentTag);
    }

    @Test
    public void hashCodeTest() {
        assertEquals(VALID_TAG.hashCode(), VALID_TAG_COPY.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("[tag]", VALID_TAG.toString());
    }

    @Test
    public void compareTo() {
        Tag smallerTag = new Tag("a");
        Tag biggerTag = new Tag("z");

        assertEquals(0, VALID_TAG.compareTo(VALID_TAG_COPY));
        assertTrue(VALID_TAG.compareTo(biggerTag) < 0);
        assertTrue(VALID_TAG.compareTo(smallerTag) > 0);
    }
}
