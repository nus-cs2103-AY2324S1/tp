package seedu.address.model.priority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.priority.Level.HIGH;
import static seedu.address.model.priority.Level.LOW;
import static seedu.address.model.priority.Level.MEDIUM;
import static seedu.address.model.priority.Level.NONE;
import static seedu.address.model.priority.Priority.HIGH_PRIORITY_KEYWORD;
import static seedu.address.model.priority.Priority.LOW_PRIORITY_KEYWORD;
import static seedu.address.model.priority.Priority.isValidPriority;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Tag;

public class PriorityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsParseException() {
        assertThrows(IllegalArgumentException.class, () -> new Priority("blah"));
        assertThrows(IllegalArgumentException.class, () -> new Priority("high low"));
    }

    @Test
    public void assignPriorityLevel_validPriority_assignsCorrectPriorityLevel() {
        assertEquals(LOW, new Priority("low").getPriorityLevel());
        assertEquals(MEDIUM, new Priority("medium").getPriorityLevel());
        assertEquals(HIGH, new Priority("high").getPriorityLevel());
        assertEquals(NONE, new Priority("-").getPriorityLevel());
    }

    @Test
    public void isValidPriority_invalidPriority_returnsFalse() {
        assertFalse(isValidPriority(null));
        assertFalse(isValidPriority("high low"));
        assertFalse(isValidPriority("top"));
        assertFalse(isValidPriority(""));
        assertFalse(isValidPriority(" "));
    }

    @Test
    public void isValidPriority_validPriority_returnsTrue() {
        assertTrue(isValidPriority("high"));
        assertTrue(isValidPriority("medium"));
        assertTrue(isValidPriority("low"));
        assertTrue(isValidPriority("-"));
    }

    @Test
    public void equalsMethod() {
        Priority priority = new Priority(LOW_PRIORITY_KEYWORD);

        // same object -> return true
        assertTrue(priority.equals(priority));

        // different type -> false
        assertFalse(priority.equals(new Object()));
        assertFalse(priority.equals(null));
        assertFalse(priority.equals(new Tag(HIGH_PRIORITY_KEYWORD)));

        // different value -> false
        assertFalse(priority.equals(new Priority(HIGH_PRIORITY_KEYWORD)));

        // same value -> true
        assertTrue(priority.equals(new Priority(LOW_PRIORITY_KEYWORD)));
    }

    @Test
    public void hashCodeMethod() {
        Priority priority = new Priority(LOW_PRIORITY_KEYWORD);
        assertEquals(priority.hashCode(), LOW.hashCode());
    }
}
