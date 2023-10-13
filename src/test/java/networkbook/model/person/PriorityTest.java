package networkbook.model.person;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    public static final String VALID_PRIORITY_STRING_1 = "h";
    public static final String VALID_PRIORITY_STRING_2 = "M";
    public static final String VALID_PRIORITY_STRING_3 = "l";
    public static final String VALID_PRIORITY_STRING_4 = "high";
    public static final String VALID_PRIORITY_STRING_5 = "mEDiUm";
    public static final String VALID_PRIORITY_STRING_6 = "lOW";
    public static final String INVALID_PRIORITY_STRING_1 = "hi";
    public static final String INVALID_PRIORITY_STRING_2 = "1";
    public static final String INVALID_PRIORITY_STRING_3 = "highmedium";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_validPriority_success() {
        Priority p1 = new Priority(VALID_PRIORITY_STRING_1);
        Priority p2 = new Priority(VALID_PRIORITY_STRING_4);
        assertEquals(p1, p2);
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Priority(INVALID_PRIORITY_STRING_1));
        assertThrows(IllegalArgumentException.class, () -> new Priority(INVALID_PRIORITY_STRING_2));
        assertThrows(IllegalArgumentException.class, () -> new Priority(INVALID_PRIORITY_STRING_3));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priority
        assertFalse(Priority.isValidPriority(Priority.PriorityLevel.INVALID));

        // valid priorities
        assertTrue(Priority.isValidPriority(Priority.PriorityLevel.HIGH));
        assertTrue(Priority.isValidPriority(Priority.PriorityLevel.MEDIUM));
        assertTrue(Priority.isValidPriority(Priority.PriorityLevel.LOW));
    }

    @Test
    public void parsePriorityLevel_validPriorityString_validPriorityLevel() {
        assertTrue(Priority.isValidPriority(Priority.parsePriorityLevel(VALID_PRIORITY_STRING_1)));
        assertTrue(Priority.isValidPriority(Priority.parsePriorityLevel(VALID_PRIORITY_STRING_2)));
        assertTrue(Priority.isValidPriority(Priority.parsePriorityLevel(VALID_PRIORITY_STRING_3)));
        assertTrue(Priority.isValidPriority(Priority.parsePriorityLevel(VALID_PRIORITY_STRING_4)));
        assertTrue(Priority.isValidPriority(Priority.parsePriorityLevel(VALID_PRIORITY_STRING_5)));
        assertTrue(Priority.isValidPriority(Priority.parsePriorityLevel(VALID_PRIORITY_STRING_6)));
    }

    @Test
    public void parsePriorityLevel_invalidPriorityString_invalidPriorityLevel() {
        assertFalse(Priority.isValidPriority(Priority.parsePriorityLevel(INVALID_PRIORITY_STRING_1)));
        assertFalse(Priority.isValidPriority(Priority.parsePriorityLevel(INVALID_PRIORITY_STRING_2)));
        assertFalse(Priority.isValidPriority(Priority.parsePriorityLevel(INVALID_PRIORITY_STRING_3)));
    }

    @Test
    public void equals() {
        Priority priority = new Priority(VALID_PRIORITY_STRING_1);

        // same object -> returns true
        assertTrue(priority.equals(priority));

        // null -> returns false
        assertFalse(priority.equals(null));

        // different types -> returns false
        assertFalse(priority.equals(5.0f));

        // different values -> returns false
        assertFalse(priority.equals(new Priority(VALID_PRIORITY_STRING_2)));
    }
}
