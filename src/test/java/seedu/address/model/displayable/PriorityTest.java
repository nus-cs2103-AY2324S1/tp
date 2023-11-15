package seedu.address.model.displayable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPriorities.DEFAULT_PRIORITY;
import static seedu.address.testutil.TypicalPriorities.HIGH_PRIORITY;
import static seedu.address.testutil.TypicalPriorities.LOW_PRIORITY;
import static seedu.address.testutil.TypicalPriorities.MEDIUM_PRIORITY;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "1";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void getPrioLvl_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Priority.getPrioLvl(null));
    }

    @Test
    public void getPrioLvl_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "1";
        assertThrows(IllegalArgumentException.class, () -> Priority.getPrioLvl(invalidPriority));
    }

    @Test
    public void getPrioLvl_validPriority_returnsCorrectEnumVal() {
        assertEquals(Priority.PrioLvl.HIGH, Priority.getPrioLvl("high"));
        assertEquals(Priority.PrioLvl.MEDIUM, Priority.getPrioLvl("medium"));
        assertEquals(Priority.PrioLvl.MEDIUM, Priority.getPrioLvl("med"));
        assertEquals(Priority.PrioLvl.LOW, Priority.getPrioLvl("low"));
        assertEquals(Priority.PrioLvl.NIL, Priority.getPrioLvl("nil"));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // valid priority levels
        assertTrue(Priority.isValidPriority("High"));
        assertTrue(Priority.isValidPriority("high"));
        assertTrue(Priority.isValidPriority("medium"));
        assertTrue(Priority.isValidPriority("med"));
        assertTrue(Priority.isValidPriority("medim"));
        assertTrue(Priority.isValidPriority("low"));
        assertTrue(Priority.isValidPriority("nil"));
        assertTrue(Priority.isValidPriority("NIL"));
        assertTrue(Priority.isValidPriority("   nil"));

        // invalid priority levels
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority("testing")); // invalid arguments
        assertFalse(Priority.isValidPriority("0")); // non-alphabetical
        assertFalse(Priority.isValidPriority("123")); // non-alphabetical
        assertFalse(Priority.isValidPriority("   ")); // all spaces

        // valid but inappropriate priority levels
        assertTrue(Priority.isValidPriority("h"));
        assertTrue(Priority.isValidPriority("m"));
        assertTrue(Priority.isValidPriority("l"));
        assertTrue(Priority.isValidPriority("hig"));
        assertTrue(Priority.isValidPriority("h9gh"));
        assertTrue(Priority.isValidPriority("med ium")); // note: counted as extra argument, addressed in DG
        assertTrue(Priority.isValidPriority("lo"));
    }
    @Test
    public void isAppropriatePriority() {
        // Invalid priority lvl should imply inappropriate as well
        assertFalse(Priority.isAppropriatePriority("")); // empty string
        assertFalse(Priority.isAppropriatePriority("   ")); // spaces only
        assertFalse(Priority.isAppropriatePriority("testing")); // invalid arguments
        assertFalse(Priority.isAppropriatePriority("0")); // non-alphabetical
        assertFalse(Priority.isAppropriatePriority("123")); // non-alphabetical

        // Valid but inappropriate priority levels
        assertFalse(Priority.isAppropriatePriority("h9gh"));
        assertFalse(Priority.isAppropriatePriority("hsidfsidf"));
        assertFalse(Priority.isAppropriatePriority("med ium"));
        assertFalse(Priority.isAppropriatePriority("lefds"));
        assertFalse(Priority.isAppropriatePriority("noawde"));

        // Valid and appropriate priority levels (shortform or typos)
        assertTrue(Priority.isAppropriatePriority("high"));
        assertTrue(Priority.isAppropriatePriority("hgih"));
        assertTrue(Priority.isAppropriatePriority("hhhh"));
        assertTrue(Priority.isAppropriatePriority("hig"));
        assertTrue(Priority.isAppropriatePriority("hii"));
        assertTrue(Priority.isAppropriatePriority("hg"));
        assertTrue(Priority.isAppropriatePriority("h"));
        assertTrue(Priority.isAppropriatePriority("HH"));

        assertTrue(Priority.isAppropriatePriority("medium"));
        assertTrue(Priority.isAppropriatePriority("meidum"));
        assertTrue(Priority.isAppropriatePriority("mdium"));
        assertTrue(Priority.isAppropriatePriority("medim"));
        assertTrue(Priority.isAppropriatePriority("md"));
        assertTrue(Priority.isAppropriatePriority("m"));
        assertTrue(Priority.isAppropriatePriority("MM"));

        assertTrue(Priority.isAppropriatePriority("low"));
        assertTrue(Priority.isAppropriatePriority("lww"));
        assertTrue(Priority.isAppropriatePriority("lo"));
        assertTrue(Priority.isAppropriatePriority("l"));
        assertTrue(Priority.isAppropriatePriority("LW"));

        assertTrue(Priority.isAppropriatePriority("nll"));
        assertTrue(Priority.isAppropriatePriority("nil"));
        assertTrue(Priority.isAppropriatePriority("NL"));
    }

    @Test
    public void toString_returnsCorrectOutput() {
        assertEquals("high", HIGH_PRIORITY.toString());
        assertEquals("med", MEDIUM_PRIORITY.toString());
        assertEquals("low", LOW_PRIORITY.toString());
        assertEquals("nil", DEFAULT_PRIORITY.toString());
    }

    @Test
    public void getBackgroundColor_returnsCorrectOutput() {
        assertEquals("red", HIGH_PRIORITY.getBackgroundColor());
        assertEquals("orange", MEDIUM_PRIORITY.getBackgroundColor());
        assertEquals("green", LOW_PRIORITY.getBackgroundColor());
        assertEquals("purple", DEFAULT_PRIORITY.getBackgroundColor());
    }

    @Test
    public void isPriorityNil_checksCorrectly() {
        assertTrue(DEFAULT_PRIORITY.isPriorityNil());
        assertFalse(LOW_PRIORITY.isPriorityNil());
        assertFalse(MEDIUM_PRIORITY.isPriorityNil());
        assertFalse(HIGH_PRIORITY.isPriorityNil());
    }

    @Test
    public void equals() {
        Priority mediumPriorityToTest = new Priority("medium");

        // same values -> returns true
        assertTrue(mediumPriorityToTest.equals(MEDIUM_PRIORITY));
        assertTrue(MEDIUM_PRIORITY.equals(mediumPriorityToTest));

        // same object -> returns true
        assertTrue(mediumPriorityToTest.equals(mediumPriorityToTest));
        assertTrue(MEDIUM_PRIORITY.equals(MEDIUM_PRIORITY));

        // null -> returns false
        assertFalse(mediumPriorityToTest.equals(null));
        assertFalse(MEDIUM_PRIORITY.equals(null));

        // different types -> returns false
        assertFalse(mediumPriorityToTest.equals("medium priority"));
        assertFalse(MEDIUM_PRIORITY.equals("medium priority"));
        assertFalse(mediumPriorityToTest.equals(mediumPriorityToTest.toString()));
        assertFalse(MEDIUM_PRIORITY.equals(MEDIUM_PRIORITY.toString()));
        assertFalse(mediumPriorityToTest.equals(Priority.PrioLvl.MEDIUM));
        assertFalse(MEDIUM_PRIORITY.equals(Priority.PrioLvl.MEDIUM));

        // different values -> returns false
        assertFalse(mediumPriorityToTest.equals(HIGH_PRIORITY));
        assertFalse(mediumPriorityToTest.equals(LOW_PRIORITY));
        assertFalse(mediumPriorityToTest.equals(DEFAULT_PRIORITY));
        assertFalse(MEDIUM_PRIORITY.equals(HIGH_PRIORITY));
        assertFalse(MEDIUM_PRIORITY.equals(LOW_PRIORITY));
        assertFalse(MEDIUM_PRIORITY.equals(DEFAULT_PRIORITY));
    }
}
