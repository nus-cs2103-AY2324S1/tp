package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassNumber(null));
    }

    @Test
    public void constructor_invalidClassNumber_throwsIllegalArgumentException() {
        String invalidClassNumber = "11";
        assertThrows(IllegalArgumentException.class, () -> new ClassNumber(invalidClassNumber));
    }

    @Test
    public void isValidClassNumber() {
        // null class number
        assertThrows(NullPointerException.class, () -> ClassNumber.isValidClassNumber(null));

        // invalid class number
        assertFalse(ClassNumber.isValidClassNumber("")); // empty string
        assertFalse(ClassNumber.isValidClassNumber("11")); // doesnt start with T

        // valid class numbers
        assertTrue(ClassNumber.isValidClassNumber("T01"));
        assertTrue(ClassNumber.isValidClassNumber("T11"));
        assertTrue(ClassNumber.isValidClassNumber("T02"));
    }

    @Test
    public void equals() {
        ClassNumber classNumber = new ClassNumber("T11");

        // same values -> returns true
        assertTrue(classNumber.equals(new ClassNumber("T11")));

        // same object -> returns true
        assertTrue(classNumber.equals(classNumber));

        // null -> returns false
        assertFalse(classNumber.equals(null));

        // different types -> returns false
        assertFalse(classNumber.equals(5.0f));

        // different values -> returns false
        assertFalse(classNumber.equals(new ClassNumber("T12")));
    }
}
