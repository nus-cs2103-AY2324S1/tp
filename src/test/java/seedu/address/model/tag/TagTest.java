package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TestData;



public class TagTest {
    @Test
    public void isValidName_alphanumeric_true() {
        assertTrue(Tag.isValidName(TestData.Valid.Tag.ALPHANUMERIC));
    }

    @Test
    public void isValidName_alphanumericSpaces_true() {
        assertTrue(Tag.isValidName(TestData.Valid.Tag.ALPHANUMERIC_SPACES));
    }

    @Test
    public void isValidName_hashtag_false() {
        assertFalse(Tag.isValidName(TestData.Invalid.Tag.HASHTAG));
    }

    @Test
    public void isValidName_underscoreDash_false() {
        assertFalse(Tag.isValidName(TestData.Invalid.Tag.UNDERSCORE_DASH));
    }
}
