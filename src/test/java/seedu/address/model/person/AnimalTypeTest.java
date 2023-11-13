package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AnimalTypeTest {
    public static final Availability AVAILABLE = new Availability("Available");
    public static final Availability NOT_AVAILABLE = new Availability("NotAvailable");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AnimalType(null, AVAILABLE));
    }

    @Test
    public void constructor_invalidAnimalTypeWithAvailability_throwsIllegalArgumentException() {
        String invalidValue = "invalidCat";
        assertThrows(IllegalArgumentException.class, () -> new AnimalType(invalidValue, AVAILABLE));
    }

    @Test
    public void constructor_validAnimalTypeWithAvailableAvailability_success() {
        String validDog = "able.Dog";
        AnimalType dog = new AnimalType(validDog, AVAILABLE);
        assertEquals(validDog, dog.value);
        assertEquals(AVAILABLE, dog.availability);

        String validCat = "able.Cat";
        AnimalType cat = new AnimalType(validCat, AVAILABLE);
        assertEquals(validCat, cat.value);
        assertEquals(AVAILABLE, cat.availability);

        String validNil = "nil";
        AnimalType nil = new AnimalType(validNil, AVAILABLE);
        assertEquals(validNil, nil.value);
        assertEquals(AVAILABLE, nil.availability);
    }

    @Test
    public void constructor_validAnimalTypeWithNotAvailableAvailability_success() {
        String validDog = "current.Dog";
        AnimalType dog = new AnimalType(validDog, NOT_AVAILABLE);
        assertEquals(validDog, dog.value);
        assertEquals(NOT_AVAILABLE, dog.availability);

        String validCat = "current.Cat";
        AnimalType cat = new AnimalType(validCat, NOT_AVAILABLE);
        assertEquals(validCat, cat.value);
        assertEquals(NOT_AVAILABLE, cat.availability);

        String validNil = "nil";
        AnimalType nil = new AnimalType(validNil, AVAILABLE);
        assertEquals(validNil, nil.value);
        assertEquals(AVAILABLE, nil.availability);
    }

    @Test
    public void isValidAnimalType_validAvailableAnimalType_returnsTrue() {
        assertTrue(AnimalType.isValidAnimalType("able.Dog", AnimalType.VALIDATION_REGEX_AVAILABLE));
        assertTrue(AnimalType.isValidAnimalType("able.Cat", AnimalType.VALIDATION_REGEX_AVAILABLE));
        assertTrue(AnimalType.isValidAnimalType("nil", AnimalType.VALIDATION_REGEX_AVAILABLE));
    }

    @Test
    public void isValidAnimalType_validNotAvailableAnimalType_returnsTrue() {
        assertTrue(AnimalType.isValidAnimalType("current.Cat", AnimalType.VALIDATION_REGEX_NOT_AVAILABLE));
        assertTrue(AnimalType.isValidAnimalType("current.Dog", AnimalType.VALIDATION_REGEX_NOT_AVAILABLE));
        assertTrue(AnimalType.isValidAnimalType("nil", AnimalType.VALIDATION_REGEX_NOT_AVAILABLE));
    }

    @Test
    public void isValidAnimalType_invalidAnimalType_returnsFalse() {
        // invalid animal type, anything other than 'nil', 'able.Dog/Cat', 'current.Dog/Cat'
        assertFalse(AnimalType.isValidAnimalType("invalidDog", AnimalType.VALIDATION_REGEX_AVAILABLE));
        assertFalse(AnimalType.isValidAnimalType("invalidCat", AnimalType.VALIDATION_REGEX_NOT_AVAILABLE));

        // blank animal type
        assertFalse(AnimalType.isValidAnimalType("", AnimalType.VALIDATION_REGEX_NIL)); // empty string
        assertFalse(AnimalType.isValidAnimalType(" ", AnimalType.VALIDATION_REGEX_NIL)); // spaces only
    }

    @Test
    public void equalsForAvailable() {
        AnimalType animalType = new AnimalType("able.Dog", AVAILABLE);

        // same values -> returns true
        assertTrue(animalType.equals(new AnimalType("able.Dog", AVAILABLE)));

        // same object -> returns true
        assertTrue(animalType.equals(animalType));

        // null -> returns false
        assertFalse(animalType.equals(null));

        // different types -> returns false
        assertFalse(animalType.equals(5.0f));

        // different values -> returns false
        assertFalse(animalType.equals(new AnimalType("able.Cat", AVAILABLE)));
    }

    @Test
    public void equalsForNotAvailable() {
        AnimalType animalType = new AnimalType("current.Dog", NOT_AVAILABLE);

        // same values -> returns true
        assertTrue(animalType.equals(new AnimalType("current.Dog", NOT_AVAILABLE)));

        // same object -> returns true
        assertTrue(animalType.equals(animalType));

        // null -> returns false
        assertFalse(animalType.equals(null));

        // different types -> returns false
        assertFalse(animalType.equals(5.0f));

        // different values -> returns false
        assertFalse(animalType.equals(new AnimalType("current.Cat", NOT_AVAILABLE)));
    }

}
