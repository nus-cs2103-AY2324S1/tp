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
    public void isEmergencyTag() {
        for (Tag.EmergencyTags tag: Tag.EmergencyTags.values()) {
            assertTrue(Tag.EmergencyTags.isEmergencyTag(tag.name()));
        }

        assertFalse(Tag.EmergencyTags.isEmergencyTag("Friend"));
        assertFalse(Tag.EmergencyTags.isEmergencyTag("Buddy"));
        assertFalse(Tag.EmergencyTags.isEmergencyTag(""));
    }

    @Test
    public void equals() {
        Tag tag = new Tag("friends");

        // same values (same case) -> returns true
        assertTrue(tag.equals(new Tag("friends")));

        // same values (i.e., when string is read, they are the same) -> returns true
        assertTrue(tag.equals(new Tag("FRIENDS")));

        // same object -> returns true
        assertTrue(tag.equals(tag));

        // null -> returns false
        assertFalse(tag.equals(null));

        // different types -> returns false
        assertFalse(tag.equals(5.0f));

        // different values -> returns false
        assertFalse(tag.equals(new Tag("colleagues")));
    }

    @Test
    public void testHashCode() {
        Tag tag1 = new Tag("friends");
        Tag tag2 = new Tag("friends");
        Tag tag3 = new Tag("FRIENDS");
        assertEquals(tag1.hashCode(), tag2.hashCode());
        assertEquals(tag1.hashCode(), tag3.hashCode());
    }

}
