package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    private Tag tag1 = new Tag("Test");
    private Tag tag1Duplicate = new Tag("Test");
    private Tag tag2 = new Tag("Test2");

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
        assert(tag1.equals(tag1));

        // same values -> returns true
        assert(tag1.equals(tag1Duplicate));

        // different values -> returns false
        assert(!tag1.equals(tag2));

        // different types -> returns false
        assert(!tag1.equals(1));

        // null -> returns false
        assert(!tag1.equals(null));
    }

}
