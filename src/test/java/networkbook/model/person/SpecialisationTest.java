package networkbook.model.person;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SpecialisationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Specialisation(null));
    }

    @Test
    public void constructor_invalidSpecialisation_throwsIllegalArgumentException() {
        String invalidSpecialisation = "";
        assertThrows(IllegalArgumentException.class, () -> new Specialisation(invalidSpecialisation));
    }

    @Test
    public void isValidSpecialisation() {
        // null specialisation
        assertThrows(NullPointerException.class, () -> Specialisation.isValidSpecialisation(null));

        // invalid specialisations
        assertFalse(Specialisation.isValidSpecialisation("")); // empty string
        assertFalse(Specialisation.isValidSpecialisation(" ")); // spaces only

        // valid specialisations
        assertTrue(Specialisation.isValidSpecialisation("Software Engineering"));
        assertTrue(Specialisation.isValidSpecialisation("-")); // one character
        assertTrue(Specialisation.isValidSpecialisation("Networking and Distributed Systems")); // long address
    }

    @Test
    public void equals() {
        Specialisation specialisation = new Specialisation("Valid specialisation");

        // same values -> returns true
        assertTrue(specialisation.equals(new Specialisation("Valid specialisation")));

        // same object -> returns true
        assertTrue(specialisation.equals(specialisation));

        // null -> returns false
        assertFalse(specialisation.equals(null));

        // different types -> returns false
        assertFalse(specialisation.equals(5.0f));

        // different values -> returns false
        assertFalse(specialisation.equals(new Specialisation("Other valid specialisation")));
    }
}
