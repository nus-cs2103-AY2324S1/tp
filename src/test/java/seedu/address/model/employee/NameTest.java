package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.name.EmployeeName;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmployeeName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EmployeeName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> EmployeeName.isValidName(null));

        // invalid name
        assertFalse(EmployeeName.isValidName("")); // empty string
        assertFalse(EmployeeName.isValidName(" ")); // spaces only
        assertFalse(EmployeeName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(EmployeeName.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(EmployeeName.isValidName("12345")); // numbers only
        assertFalse(EmployeeName.isValidName("peter the 2nd")); // alphanumeric characters
        assertFalse(EmployeeName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names

        // valid name
        assertTrue(EmployeeName.isValidName("peter jack")); // alphabets only
        assertTrue(EmployeeName.isValidName("Capital Tan")); // with capital letters
        assertTrue(EmployeeName.isValidName("Frank Sinatra Jr.")); // with a full stop
        assertTrue(EmployeeName.isValidName("Elizabeth II")); // roman numerals

    }

    @Test
    public void equals() {
        EmployeeName name = new EmployeeName("Valid EmployeeName");

        // same values -> returns true
        assertTrue(name.equals(new EmployeeName("Valid EmployeeName")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new EmployeeName("Other Valid EmployeeName")));
    }
}
