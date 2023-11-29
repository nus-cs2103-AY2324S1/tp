package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.dentist.Specialization;

public class SpecializationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Specialization(null));
    }

    @Test
    public void constructor_invalidSpecialization_throwsIllegalArgumentException() {
        String invalidSpec = "";
        assertThrows(IllegalArgumentException.class, () -> new Specialization(invalidSpec));
    }

    @Test
    public void isValidSpecialization() {
        // null spec
        assertThrows(NullPointerException.class, () -> Specialization.isValidSpecialization(null));

        // invalid spec
        assertFalse(Specialization.isValidSpecialization("")); // empty string
        assertFalse(Specialization.isValidSpecialization(" ")); // spaces only
        assertFalse(Specialization.isValidSpecialization("^")); // only non-alphanumeric characters
        assertFalse(
            Specialization.isValidSpecialization("peter*")); // contains non-alphanumeric characters

        // valid spec
        assertTrue(Specialization.isValidSpecialization("orthodontics")); // alphabets only
        assertTrue(Specialization.isValidSpecialization("ORTHODONTICS")); // alphabets only
        assertTrue(Specialization.isValidSpecialization(
            "DENTAL_PUBLIC_HEALTH")); // alphabets and underscore
        assertTrue(
            Specialization.isValidSpecialization("Dental Public Health")); // alphabets and space
        assertTrue(Specialization.isValidSpecialization("PROSTHODONTICS")); // alphabets and space
        assertTrue(Specialization.isValidSpecialization("prosthodontics"));

    }

    @Test
    public void equals() {
        Specialization specialization = new Specialization("orthodontics");

        // same values -> returns true
        assertTrue(specialization.equals(new Specialization("orthodontics")));

        // same object -> returns true
        assertTrue(specialization.equals(specialization));

        // null -> returns false
        assertFalse(specialization.equals(null));

        // different types -> returns false
        assertFalse(specialization.equals(5.0f));

        // different values -> returns false
        assertFalse(specialization.equals(new Specialization("prosthodontics")));
    }
}
