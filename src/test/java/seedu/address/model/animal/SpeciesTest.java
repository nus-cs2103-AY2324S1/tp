package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SpeciesTest {
    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Species(null));
    }

    @Test
    void constructor_invalidSpecies_throwsIllegalArgumentException() {
        String invalidSpecies = "";
        assertThrows(IllegalArgumentException.class, () -> new Species(invalidSpecies));
    }

    @Test
    void testIsSpeciesValid_withNull() {
        assertThrows(NullPointerException.class, () -> Species.isValidSpecies(null));
    }

    @Test
    void testIsSpeciesValid_withInvalidSpecies() {
        assertFalse(Species.isValidSpecies("")); // empty string
        assertFalse(Species.isValidSpecies(" ")); // spaces only
        assertFalse(Species.isValidSpecies("^")); // only non-alphabetic characters
        assertFalse(Species.isValidSpecies("cat*")); // contains non-alphabetic characters
        assertFalse(Species.isValidSpecies("12345")); // numbers only
        assertFalse(Species.isValidSpecies("cat the 2nd")); // alphabetic characters
        assertFalse(Species.isValidSpecies("cat  tan")); // alphabetic characters
    }

    @Test
    void testIsSpeciesValid_withValidSpecies() {
        assertTrue(Species.isValidSpecies("dog jack")); // alphabets only
        assertTrue(Species.isValidSpecies("Dog Tan")); // with capital letters
        assertTrue(Species.isValidSpecies("Dog Roger Jackson Ray Jr")); // long names
    }

    @Test
    void testEquals_withSameValues() {
        Species species = new Species("Valid Species");
        assertEquals(species, new Species("Valid Species"));
    }

    @Test
    void testEquals_withSameObject() {
        Species species = new Species("Valid Species");
        assertEquals(species, species);
    }

    @Test
    void testEquals_withNull() {
        Species species = new Species("Valid Species");
        assertNotEquals(null, species);
    }

    @Test
    void testEquals_withDifferentTypes() {
        Species species = new Species("Valid Species");
        assertFalse(species.equals(5.0f));
    }

    @Test
    void testEquals_withDifferentValues() {
        Species species = new Species("Valid Species");
        assertNotEquals(species, new Species("Other Valid Species"));
    }
}
