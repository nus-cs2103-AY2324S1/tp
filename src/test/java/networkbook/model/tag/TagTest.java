package networkbook.model.tag;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void isSame_sameTagNames_returnsTrue() {
        assertTrue(new Tag("hello").isSame(new Tag("hello")));
        assertTrue(new Tag("dog").isSame(new Tag("dog")));
    }

    @Test
    public void isSame_differentTagNames_returnsFalse() {
        assertFalse(new Tag("hello").isSame(new Tag("hi")));
        assertFalse(new Tag("dif").isSame(new Tag("diff")));
    }

}
