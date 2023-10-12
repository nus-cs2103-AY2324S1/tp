package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_DERMATOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_ORTHOPAEDIC;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SpecialtyTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Specialty(null));
    }

    @Test
    public void constructor_invalidSpecialty_throwsIllegalArgumentException() {
        String invalidSpecialty = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidSpecialty));
    }

    @Test
    public void isValidSpecialty() {
        // null specialty
        assertThrows(NullPointerException.class, () -> Specialty.isValidSpecialty(null));

        // invalid specialties
        assertFalse(Specialty.isValidSpecialty("")); // empty string
        assertFalse(Specialty.isValidSpecialty(" ")); // spaces only
        // valid specialties
        assertTrue(Specialty.isValidSpecialty(VALID_SPECIALTY_DERMATOLOGY));
    }

    @Test
    public void equals() {
        Specialty specialty = new Specialty(VALID_SPECIALTY_ORTHOPAEDIC);

        // same values -> returns true
        assertTrue(specialty.equals(new Specialty(VALID_SPECIALTY_ORTHOPAEDIC)));

        // same object -> returns true
        assertTrue(specialty.equals(specialty));

        // null -> returns false
        assertFalse(specialty.equals(null));

        // different types -> returns false
        assertFalse(specialty.equals(VALID_SPECIALTY_ORTHOPAEDIC));

        // different values -> returns false
        assertFalse(specialty.equals(new Specialty(VALID_SPECIALTY_DERMATOLOGY)));
    }
}
