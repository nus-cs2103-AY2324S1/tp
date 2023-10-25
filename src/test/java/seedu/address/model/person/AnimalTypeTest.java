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
    public void constructor_validAnimalTypeWithAvailability_success() {
        String validValue = "able.Dog";
        String validAvailability = "Available";
        AnimalType animalType = new AnimalType(validValue, new Availability(validAvailability));
        assertEquals(validValue, animalType.value);
        assertEquals(new Availability(validAvailability), animalType.availability);
    }

    @Test
    public void isValidAnimalType_validAvailableAnimalType_returnsTrue() {
        assertTrue(AnimalType.isValidAnimalType("able.Dog", AnimalType.VALIDATION_REGEX_AVAILABLE));
    }

    @Test
    public void isValidAnimalType_validNotAvailableAnimalType_returnsTrue() {
        assertTrue(AnimalType.isValidAnimalType("current.Cat", AnimalType.VALIDATION_REGEX_NOT_AVAILABLE));
    }

    @Test
    public void isValidAnimalType_invalidAnimalType_returnsFalse() {
        assertFalse(AnimalType.isValidAnimalType("invalidDog", AnimalType.VALIDATION_REGEX_AVAILABLE));
        assertFalse(AnimalType.isValidAnimalType("invalidCat", AnimalType.VALIDATION_REGEX_NOT_AVAILABLE));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        AnimalType animalType1 = new AnimalType("able.Dog", AVAILABLE);
        AnimalType animalType2 = new AnimalType("able.Dog", AVAILABLE);
        assertTrue(animalType1.equals(animalType2));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        AnimalType animalType1 = new AnimalType("current.Dog", NOT_AVAILABLE);
        AnimalType animalType2 = new AnimalType("current.Cat", NOT_AVAILABLE);
        assertFalse(animalType1.equals(animalType2));
    }

    @Test
    public void constructor_invalidAnimalTypeWithAvailability_throwsIllegalArgumentException() {
        String invalidValue = "invalidCat";
        assertThrows(IllegalArgumentException.class, () -> new AnimalType(invalidValue, AVAILABLE));
    }

}
