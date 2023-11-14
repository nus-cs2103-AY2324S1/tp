package networkbook.model.person;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
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

    /**
     * The constructor should automatically remove leading and trailing spaces when constructing a specialisation
     */
    @Test
    public void constructor_removalOfSpaces_success() {
        try {
            Specialisation spec = new Specialisation("  Computer Science   ");
        } catch (IllegalArgumentException e) {
            Assertions.fail();
        }
    }

    @Test
    public void isValidSpecialisation() {
        // null specialisation
        assertThrows(NullPointerException.class, () -> Specialisation.isValidSpecialisation(null));

        // invalid specialisations
        assertFalse(Specialisation.isValidSpecialisation("")); // empty string
        assertFalse(Specialisation.isValidSpecialisation(" ")); // spaces only
        assertFalse(Specialisation
                .isValidSpecialisation("I once saw a  balloon")); // more than one space between words
        assertFalse(Specialisation.isValidSpecialisation(" I once went to Kentucky")); // spaces in the beginning

        // valid specialisations
        assertTrue(Specialisation.isValidSpecialisation("Software Engineering"));
        assertTrue(Specialisation.isValidSpecialisation("AAA Gaming")); // all uppercase words (acronyms)
        assertTrue(Specialisation
                .isValidSpecialisation("Leader of the Multinational Party of Czechoslovakia and the "
                        + "Associations Within the Umbrella of the Multinational Party.")); // very long specialisation
        assertTrue(Specialisation
                .isValidSpecialisation("Industry 4.0 and AI planning, "
                        + "decision-making")); // commas, periods, dashes
        assertTrue(Specialisation.isValidSpecialisation("developed moon.exe"));
        assertTrue(Specialisation.isValidSpecialisation("AIa")); // uppercase letters beyond the first character
        assertTrue(Specialisation.isValidSpecialisation(", but")); // starting a word with comma, period, dash
        assertTrue(Specialisation.isValidSpecialisation(".exe"));
        assertTrue(Specialisation.isValidSpecialisation("-ish"));
    }

    @Test
    public void equals() {
        Specialisation specialisation = new Specialisation("Valid specialisation");

        // same values -> returns true
        assertEquals(specialisation, new Specialisation("Valid specialisation"));

        // same object -> returns true
        assertEquals(specialisation, specialisation);

        // null -> returns false
        assertFalse(specialisation.equals(null));

        // different types -> returns false
        assertNotEquals(5.0f, specialisation);

        // different values -> returns false
        assertNotEquals(specialisation, new Specialisation("Other valid specialisation"));

        // same specialisation but with leading/trailing spaces -> returns true
        assertEquals(specialisation, new Specialisation("  Valid specialisation    "));
    }

    @Test
    public void hashcode() {
        // roughly equivalent to equals
        Specialisation specialisation = new Specialisation("Valid specialisation");

        assertTrue(specialisation.hashCode() == new Specialisation("Valid specialisation").hashCode());
        assertTrue(specialisation.hashCode() == specialisation.hashCode());

        assertFalse(specialisation.hashCode() == new Specialisation("Other valid specialisation").hashCode());
        assertFalse(specialisation.hashCode() == 5);
        assertFalse(new Integer(specialisation.hashCode()).equals(null));
    }

    @Test
    public void getSpecialisation() {
        Specialisation specialisation = new Specialisation("Valid Specialisation");

        // Should behave like the string that it was constructed from
        assertTrue(specialisation.getSpecialisation().equals("Valid Specialisation"));
        assertTrue(specialisation.getSpecialisation()
                .equals(new Specialisation("Valid Specialisation").getSpecialisation()));
        assertTrue(specialisation.getSpecialisation()
                .equals(new Specialisation(specialisation.getSpecialisation()).getSpecialisation()));

        assertFalse(specialisation.getSpecialisation().equals(null));
        assertFalse(specialisation.getSpecialisation().equals("Some other text"));
        assertFalse(specialisation.getSpecialisation()
                .equals(new Specialisation("Other Valid Specialisation").getSpecialisation()));

        // leading and trailing spaces removed
        assertEquals(new Specialisation("   Valid specialisation    ").getSpecialisation(),
                "Valid specialisation");
    }
}
