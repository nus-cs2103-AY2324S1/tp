package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    public void equalityTest() {
        Tag tag = new Tag("test");
        Tag tagCopy = new Tag("test");
        Tag otherTag = new Tag("test2");
        Tag weirdTag = new Tag("中文 non-alphanumeric");
        Object objectTag = (Object) tagCopy;
        Tag nullTag = null;
        assertEquals(tag, tag); //reflexive

        assertEquals(tag, tagCopy); //other copy
        assertEquals(tagCopy, tag); //symmetric

        assertEquals(tag, objectTag); //casting
        assertEquals(objectTag, tagCopy); //transitive

        assertNotEquals(weirdTag, tag);
        assertNull(nullTag);
        assertNotEquals(tag, otherTag);
    }

}
