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
    public void checkIsValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // Valid tag names - case insensitive
        assertTrue(Tag.isValidTagName("friend"));
        assertTrue(Tag.isValidTagName("FRIEND"));
        assertTrue(Tag.isValidTagName("FrIeNd"));

        // Invalid tag name
        assertFalse(Tag.isValidTagName("classmates"));
    }

    @Test
    public void checkIsEmergencyTag() {
        // not emergency tag
        Tag notEmergency = new Tag("friend");
        assertFalse(notEmergency.isEmergencyTag());

        // emergency tag - case insensitive
        Tag emergency = new Tag("EMERGENCY");
        assertTrue(emergency.isEmergencyTag());
    }

    @Test
    public void createEmergencyTag_allLowerCase_convertsToUpperCase() {
        // Arrange
        String inputTagName = "emergency";
        String expectedTagName = "[Emergency]";

        // Act
        Tag tag = new Tag(inputTagName);

        // Assert
        assertEquals(expectedTagName, tag.toString());
    }

    @Test
    public void createCloseFriendTag_allowAnyCase() {
        String expectedTagName = "[Close Friend]";

        // All upper case
        String upperCaseTagName = "CF";
        Tag firstTag = new Tag(upperCaseTagName);
        assertEquals(expectedTagName, firstTag.toString());

        // Mixed case
        String mixedCaseTagName = "cF";
        Tag secondTag = new Tag(mixedCaseTagName);
        assertEquals(expectedTagName, secondTag.toString());
    }

    @Test
    public void createFriendTag_allowAnyCase() {
        String expectedTagName = "[Friend]";

        // All upper case
        String upperCaseTagName = "FRIEND";
        Tag firstTag = new Tag(upperCaseTagName);
        assertEquals(expectedTagName, firstTag.toString());

        // Mixed case
        String mixedCaseTagName = "FrIeND";
        Tag secondTag = new Tag(mixedCaseTagName);
        assertEquals(expectedTagName, secondTag.toString());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Tag tag = new Tag("Friend");
        assertTrue(tag.equals(tag));
    }

    @Test
    public void equals_sameTagName_returnsTrue() {
        Tag firstTag = new Tag("Friend");
        Tag secondTag = new Tag("Friend");
        assertTrue(firstTag.equals(secondTag));
    }

    @Test
    public void equals_differentTagType_returnsFalse() {
        Tag firstTag = new Tag("Friend");
        Tag secondTag = new Tag("Close Friend");
        assertFalse(firstTag.equals(secondTag));
    }

    @Test
    public void equals_differentObjectType_returnsFalse() {
        Tag tag = new Tag("Friend");
        assertFalse(tag.equals(new Object()));
    }
}
