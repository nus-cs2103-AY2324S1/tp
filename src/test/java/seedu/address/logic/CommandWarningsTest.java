package seedu.address.logic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class CommandWarningsTest {

    private static final String TEST1 = "Testwithoutspaces";
    private static final String TEST2 = "   whitespaced   ";
    private static final String TEST3 = "Sting with spaces";
    private static final String TEST4 = "String with unusual characters 中文 (*9328^%92 *~/- :F:f;";
    private static final String BLANK = "";
    private static final String BLANK2 = "    ";
    @Test
    public void warnings_addTest() {
        CommandWarnings warnings = new CommandWarnings();
        assertFalse(warnings.containsWarningString(null));
        warnings.addWarning(null);
        assertFalse(warnings.containsWarningString(null));
        warnings.addWarning(TEST1);
        assertTrue(warnings.containsWarningString(TEST1));
        warnings.addWarning(TEST2);
        assertTrue(warnings.containsWarningString(TEST2));
        warnings.addWarning(TEST3);
        assertTrue(warnings.containsWarningString(TEST3));
        warnings.addWarning(TEST4);
        assertTrue(warnings.containsWarningString(TEST4));
        warnings.addWarning(BLANK);
        warnings.addWarning(BLANK2);
        assertTrue(warnings.containsWarningString(TEST1));
        assertTrue(warnings.containsWarningString(TEST2));
        assertTrue(warnings.containsWarningString(TEST3));
        assertTrue(warnings.containsWarningString(TEST4));
        assertTrue(warnings.containsWarningString(BLANK));
        assertTrue(warnings.containsWarningString(BLANK2));
    }

    @Test
    public void warnings_getWarningMessage() {
        CommandWarnings warnings = new CommandWarnings();
        assertEquals(warnings.getWarningMessage(), "");
        warnings.addWarning(TEST1);
        assertEquals(warnings.getWarningMessage(),
                "Warning!; [" + TEST1 + "]\nPlease ignore if this is expected.");
        warnings.addWarning(TEST4);
        assertEquals(warnings.getWarningMessage(),
                "Warning!; [" + TEST1 + ", " + TEST4 + "]\nPlease ignore if this is expected.");
    }
    @Test
    public void warnings_toString() {
        CommandWarnings warnings = new CommandWarnings();
        assertEquals(warnings.toString(), "[]");
        warnings.addWarning(TEST1);
        assertEquals(warnings.toString(),
                "[" + TEST1 + "]");
        warnings.addWarning(TEST4);
        assertEquals(warnings.toString(),
                "[" + TEST1 + ", " + TEST4 + "]");
    }

    @Test
    public void warnings_contains() {
        CommandWarnings warnings = new CommandWarnings();
        assertFalse(warnings.containsWarnings());
        assertFalse(warnings.containsWarningString(""));
        assertFalse(warnings.containsWarningString("   "));

        warnings.addWarning(TEST4);
        assertTrue(warnings.containsWarnings());
        assertTrue(warnings.containsWarningString(TEST4));
        assertFalse(warnings.containsWarningString(TEST4.substring(4)));

        warnings.addWarning(TEST2);
        assertTrue(warnings.containsWarningString(TEST2));
        assertFalse(warnings.containsWarningString(TEST2.trim()));
    }

    @Test
    public void warnings_equality() {
        CommandWarnings warnings = new CommandWarnings();
        warnings.addWarning("Test");
        warnings.addWarning("Test2");

        CommandWarnings warningsCopy = new CommandWarnings();
        warningsCopy.addWarning("Test");
        warningsCopy.addWarning("Test2");

        CommandWarnings warningsInvertedOrder = new CommandWarnings();
        warningsInvertedOrder.addWarning("Test2");
        warningsInvertedOrder.addWarning("Test");

        CommandWarnings warningsDuplicated = new CommandWarnings();
        warningsDuplicated.addWarning("Test");
        warningsDuplicated.addWarning("Test2");
        warningsDuplicated.addWarning("Test2");
        warningsDuplicated.addWarning("Test");

        CommandWarnings other = new CommandWarnings();
        warningsCopy.addWarning("Test2");

        CommandWarnings blank = new CommandWarnings();

        CommandWarnings empty = new CommandWarnings();
        empty.addWarning("");

        CommandWarnings nullWarnings = null;

        assertEquals(warnings, warnings);
        assertNotEquals(warnings, other);
        assertEquals(warnings, warningsCopy);
        assertEquals(warnings, (Object) warningsCopy);
        assertEquals(blank, new CommandWarnings());
        assertNotEquals(empty, blank);
        assertNotEquals(empty, null);
        assertNull(nullWarnings);
        assertEquals(warningsInvertedOrder, warningsCopy);
        assertEquals(warnings, warningsInvertedOrder);
        assertEquals(warnings, warningsDuplicated);
    }
}
