package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void getTagName() {
        String validTagName = "CS2103T T02";
        Tag tag = new Tag(validTagName);
        assertEquals(validTagName, tag.getTagName());
    }

    @Test
    public void getCourseCode() {
        String validTagName = "CS2103T T02";
        Tag tag = new Tag(validTagName);
        assertEquals("CS2103T", tag.getCourseCode());
    }

    @Test
    public void getTutorialGroup() {
        String validTagName = "CS2103T T02";
        Tag tag = new Tag(validTagName);
        assertEquals("T02", tag.getTutorialGroup());
    }

    @Test
    public void create() {
        String validTagName = "tagName";
        Tag tag = Tag.create(validTagName);

        Tag expected = new Tag(validTagName);
        assertEquals(expected, tag);
    }
}
