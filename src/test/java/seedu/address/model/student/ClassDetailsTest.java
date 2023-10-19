package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassDetails(null));
    }

    @Test
    public void constructor_invalidClassDetails_throwsIllegalArgumentException() {
        String invalidClassDetails = "11";
        assertThrows(IllegalArgumentException.class, () -> new ClassDetails(invalidClassDetails));
    }

    @Test
    public void isValidClassDetails() {
        // null class number
        assertThrows(NullPointerException.class, () -> ClassDetails.isValidClassDetails(null));

        // invalid class number
        assertFalse(ClassDetails.isValidClassDetails("")); // empty string
        assertFalse(ClassDetails.isValidClassDetails("11")); // doesnt start with T

        // valid class numbers
        assertTrue(ClassDetails.isValidClassDetails("T01"));
        assertTrue(ClassDetails.isValidClassDetails("T11"));
        assertTrue(ClassDetails.isValidClassDetails("T02"));
    }

    @Test
    public void equals() {
        ClassDetails classDetails = new ClassDetails("T11");

        // same values -> returns true
        assertTrue(classDetails.equals(new ClassDetails("T11")));

        // same object -> returns true
        assertTrue(classDetails.equals(classDetails));

        // null -> returns false
        assertFalse(classDetails.equals(null));

        // different types -> returns false
        assertFalse(classDetails.equals(5.0f));

        // different values -> returns false
        assertFalse(classDetails.equals(new ClassDetails("T12")));
    }

    @Test
    public void test_hashCode() {
        ClassDetails classDetails = new ClassDetails("T11");

        // same values -> returns true
        assertTrue(classDetails.hashCode() == (new ClassDetails("T11")).hashCode());

        // same object -> returns true
        assertTrue(classDetails.hashCode() == classDetails.hashCode());

        // null -> returns false
        assertFalse(classDetails.hashCode() == 0);

        // different types -> returns false
        assertFalse(classDetails.hashCode() == (5.0f));

        // different values -> returns false
        assertFalse(classDetails.hashCode() == (new ClassDetails("T12")).hashCode());
    }
}
