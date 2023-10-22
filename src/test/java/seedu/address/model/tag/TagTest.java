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
}
