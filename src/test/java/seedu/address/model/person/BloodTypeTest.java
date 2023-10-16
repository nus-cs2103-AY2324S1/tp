package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BloodTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BloodType(null));
    }

    @Test
    public void constructor_invalidBloodType_throwsIllegalArgumentException() {
        String invalidBloodType1 = "";
        assertThrows(IllegalArgumentException.class, () -> new BloodType(invalidBloodType1));
    }

    @Test
    public void isValidBloodType() {
        // null BloodType
        assertThrows(NullPointerException.class, () -> BloodType.isValidBloodType(null));

        // invalid BloodType
        assertFalse(BloodType.isValidBloodType("")); // empty string
        assertFalse(BloodType.isValidBloodType(" ")); // spaces only
        assertFalse(BloodType.isValidBloodType("91")); // anything other than A/B/AB/O
        assertFalse(BloodType.isValidBloodType("D+")); // anything other than A/B/AB/O
        assertFalse(BloodType.isValidBloodType("A")); // no +/- after blood type

        // valid BloodTyp
        assertTrue(BloodType.isValidBloodType("A+"));
        assertTrue(BloodType.isValidBloodType("AB-"));
        assertTrue(BloodType.isValidBloodType("O+"));
        assertTrue(BloodType.isValidBloodType("B-"));
    }

    @Test
    public void equals() {
        BloodType bloodType = new BloodType("A+");

        // same values -> returns true
        assertTrue(bloodType.equals(new BloodType("A+")));

        // same object -> returns true
        assertTrue(bloodType.equals(bloodType));

        // null -> returns false
        assertFalse(bloodType.equals(null));

        // different types -> returns false
        assertFalse(bloodType.equals(5.0f));

        // different values -> returns false
        assertFalse(bloodType.equals(new BloodType("AB+")));
    }
}
