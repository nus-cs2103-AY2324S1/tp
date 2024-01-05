package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TaskName.isValidName(null));

        // invalid name
        assertFalse(TaskName.isValidName("")); // empty string
        assertFalse(TaskName.isValidName(" ")); // spaces only
        assertFalse(TaskName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TaskName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TaskName.isValidName("do work")); // alphabets only
        assertTrue(TaskName.isValidName("2103")); // numbers only
        assertTrue(TaskName.isValidName("do the 2nd paper")); // alphanumeric characters
        assertTrue(TaskName.isValidName("Read Script")); // with capital letters
        assertTrue(TaskName.isValidName("Read the script and memorise everything fully")); // long names
    }

    @Test
    public void equals() {
        TaskName name = new TaskName("Borrow Book");

        // same values -> returns true
        assertTrue(name.equals(new TaskName("Borrow Book")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new TaskName("Other Borrow Book")));
    }
}
