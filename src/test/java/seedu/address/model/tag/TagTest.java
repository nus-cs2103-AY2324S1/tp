package seedu.address.model.tag;

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

}
