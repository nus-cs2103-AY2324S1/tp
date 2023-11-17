package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BreedTest {
    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Breed(null));
    }

    @Test
    void constructor_invalidBreed_throwsIllegalArgumentException() {
        String invalidBreed = "";
        assertThrows(IllegalArgumentException.class, () -> new Breed(invalidBreed));
    }

    @Test
    void testIsBreedValid_withNull() {
        assertThrows(NullPointerException.class, () -> Breed.isValidBreed(null));
    }

    @Test
    void testIsBreedValid_withInvalidBreed() {
        assertFalse(Breed.isValidBreed("")); // empty string
        assertFalse(Breed.isValidBreed(" ")); // spaces only
        assertFalse(Breed.isValidBreed("^")); // only non-alphabetic characters
        assertFalse(Breed.isValidBreed("corgi*")); // contains non-alphabetic characters
        assertFalse(Breed.isValidBreed("12345")); // numbers only
        assertFalse(Breed.isValidBreed("corgi the 2nd")); // alphabetic characters
        assertFalse(Breed.isValidBreed("corgi  tan")); // alphabetic characters
    }

    @Test
    void testIsBreedValid_withValidBreed() {
        assertTrue(Breed.isValidBreed("golden jack")); // alphabets only
        assertTrue(Breed.isValidBreed("Golden Tan")); // with capital letters
        assertTrue(Breed.isValidBreed("David Roger Jackson Ray Jr")); // long names
    }

    @Test
    void testEquals_withSameValues() {
        Breed breed = new Breed("Valid Breed");
        assertEquals(breed, new Breed("Valid Breed"));
    }

    @Test
    void testEquals_withSameObject() {
        Breed breed = new Breed("Valid Breed");
        assertEquals(breed, breed);
    }

    @Test
    void testEquals_withNull() {
        Breed breed = new Breed("Valid Breed");
        assertNotEquals(null, breed);
    }

    @Test
    void testEquals_withDifferentTypes() {
        Breed breed = new Breed("Valid Breed");
        assertFalse(breed.equals(5.0f));
    }

    @Test
    void testEquals_withDifferentValues() {
        Breed breed = new Breed("Valid Breed");
        assertNotEquals(breed, new Breed("Other Valid Breed"));
    }
}
