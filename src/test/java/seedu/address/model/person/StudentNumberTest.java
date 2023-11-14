package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentNumber(null));
    }

    @Test
    public void constructor_invalidStudentNumber_throwsIllegalArgumentException() {
        String invalidStudentNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentNumber(invalidStudentNumber));
    }

    @Test
    public void isValidStudentNumber() {
        // null student number
        assertThrows(NullPointerException.class, () -> StudentNumber.isValidStudentNumber(null));

        // invalid student numbers
        assertFalse(StudentNumber.isValidStudentNumber("")); // empty string
        assertFalse(StudentNumber.isValidStudentNumber(" ")); // spaces only
        assertFalse(StudentNumber.isValidStudentNumber("A0324A"));
        assertFalse(StudentNumber.isValidStudentNumber("studentNo"));
        assertFalse(StudentNumber.isValidStudentNumber("3284933")); // only digits
        assertFalse(StudentNumber.isValidStudentNumber("A838 3877C")); // spaces within

        // valid student numbers
        assertTrue(StudentNumber.isValidStudentNumber("A0837473D"));
        assertTrue(StudentNumber.isValidStudentNumber("A1111111G"));
        assertTrue(StudentNumber.isValidStudentNumber("A8478373Z"));
    }

    @Test
    public void equals() {
        StudentNumber studentNo = new StudentNumber("A1111111G");

        // same values -> returns true
        assertTrue(studentNo.equals(new StudentNumber("A1111111G")));

        // same object -> returns true
        assertTrue(studentNo.equals(studentNo));

        // null -> returns false
        assertFalse(studentNo.equals(null));

        // different types -> returns false
        assertFalse(studentNo.equals(5.0f));

        // different values -> returns false
        assertFalse(studentNo.equals(new StudentNumber("A1111112G")));
    }
}
