package seedu.address.model.person.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class ModuleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null, "monday 1200 1400"));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidName, "monday 1200 1400"));
    }

    @Test
    public void newModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Module.newModule(null));
    }

    @Test
    public void newModule_invalidInput_throwsIllegalArgumentException() {
        String invalidInput = "monday 1200 1400";
        assertThrows(IllegalArgumentException.class, () -> Module.newModule(invalidInput));
    }

    @Test
    public void newModule_invalidName_throwsIllegalValueException() {
        String invalidInput = "@ monday 1200 1400";
        assertThrows(IllegalValueException.class, () -> Module.newModule(invalidInput));
    }

    @Test
    public void newModule_validInput_success() throws IllegalValueException {
        String validInput = "CS2103 monday 1200 1400";
        assertEquals(new Module("CS2103", "monday 1200 1400"), Module.newModule(validInput));
    }

    @Test
    public void editName_success() {
        Module module = new Module("CS2103", "monday 1200 1400");
        assertEquals(new Module("CS2101", "monday 1200 1400"), module.editName("CS2101"));
    }

    @Test void editName_failure() {
        Module module = new Module("CS2103", "monday 1200 1400");
        assertThrows(IllegalArgumentException.class, () -> module.editName(""));
    }

    @Test
    public void isModule_success() {
        Module module = new Module("CS2103", "monday 1200 1400");
        assertTrue(module.isModule());
    }

    @Test
    public void getType_success() {
        Module module = new Module("CS2103", "monday 1200 1400");
        assertEquals("Module", module.getType());
    }

    @Test
    public void isValidModuleName() {
        // null name
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));

        // invalid name
        assertFalse(Module.isValidModuleName("")); // empty string
        assertFalse(Module.isValidModuleName(" ")); // spaces only
        assertFalse(Module.isValidModuleName("^")); // only non-alphanumeric characters
        assertFalse(Module.isValidModuleName("CS21*3")); // contains non-alphanumeric characters
        assertFalse(Module.isValidModuleName("CS2103 CS2103")); // contains spaces
        assertFalse(Module.isValidModuleName("CS21031")); // more numbers
        assertFalse(Module.isValidModuleName("CS")); // only alphbets

        // valid name
        assertTrue(Module.isValidModuleName("CS210")); // 3 numbers
        assertTrue(Module.isValidModuleName("CSS210")); // 3 starting alphabets
        assertTrue(Module.isValidModuleName("CS2101S")); // less numbers
        assertTrue(Module.isValidModuleName("CS2103")); // alphanumeric characters
        assertTrue(Module.isValidModuleName("cs2103")); // with small letters
    }

    @Test
    public void equals() {
        Module module = new Module("CS2100", "monday 1200 1400");

        // same values -> returns true
        assertTrue(module.equals(new Module("CS2100", "monday 1200 1400")));

        // same object -> returns true
        assertTrue(module.equals(module));

        // null -> returns false
        assertFalse(module.equals(null));

        // different types -> returns false
        assertFalse(module.equals(5.0f));

        // different values -> returns false
        assertFalse(module.equals(new Cca("CS2101", "monday 1200 1400")));

        assertFalse(module.equals(new Cca("CS2100", "tuesday 1200 1400")));
    }

    @Test
    public void testHashCode() {
        Module module = new Module("CS2103", "monday 1200 1400");
        assertEquals(module.hashCode(), new Module("CS2103", "monday 1200 1400").hashCode());
    }

}
