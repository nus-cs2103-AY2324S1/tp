package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription("3dUxHctmS8CrcahkimCRb5o33qMXhpMAoyheGmi9BLF8BJnTouR2KsH34as"
                        + "RqUKDppYnEBHmRq54p5LkBjRsGyGcjWFU6m6pRU2SGQDiAOQMD4ZGqsMwTO3SAxVDWaCwfiQdqBlopNXqyu9cIcg"
                        + "Djdh\n")); // more than 150 characters

        // valid description
        assertTrue(Description.isValidDescription("test description"));
    }

    @Test
    public void equals() {
        Description description = new Description("test");

        // same values -> returns true
        assertTrue(description.equals(new Description("test")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals("diff"));
    }

    @Test
    public void hashCode_returnsExpectedHashCode() {
        Description description = new Description("test");
        int expectedHashCode = "test".hashCode();
        int actualHashCode = description.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }
}
