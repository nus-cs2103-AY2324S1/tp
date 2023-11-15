package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTypeTest {

    @Test
    public void fromString_validName_correctEnumReturned() {
        assertEquals(TagType.FRIEND, TagType.fromString("Friend"));
        assertEquals(TagType.CLOSE_FRIEND, TagType.fromString("Close Friend"));
        assertEquals(TagType.CLOSE_FRIEND, TagType.fromString("cf"));
        assertEquals(TagType.EMERGENCY, TagType.fromString("Emergency"));
    }

    @Test
    public void fromString_invalidName_throwsIllegalArgumentException() {
        String invalidName = "Invalid";
        assertThrows(IllegalArgumentException.class, () -> TagType.fromString(invalidName));
    }

    @Test
    public void fromString_caseInsensitiveName_correctEnumReturned() {
        assertEquals(TagType.FRIEND, TagType.fromString("fRiEnD"));
        assertEquals(TagType.CLOSE_FRIEND, TagType.fromString("ClOsE FrIeNd"));
        assertEquals(TagType.CLOSE_FRIEND, TagType.fromString("CF"));
        assertEquals(TagType.EMERGENCY, TagType.fromString("eMeRgEnCy"));
    }

    @Test
    public void getDisplayName_validEnum_correctDisplayNameReturned() {
        assertEquals("Friend", TagType.FRIEND.getDisplayName());
        assertEquals("Close Friend", TagType.CLOSE_FRIEND.getDisplayName());
        assertEquals("Emergency", TagType.EMERGENCY.getDisplayName());
    }

}

