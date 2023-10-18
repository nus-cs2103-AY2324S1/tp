package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AnimalTypeTest {

    @Test
    public void constructor_validAnimalTypeWithAvailability_success() {
        String validValue = "able.Dog";
        String validAvailability = "Available";
        AnimalType animalType = new AnimalType(validValue, validAvailability);
        assertEquals(validValue, animalType.value);
        assertEquals(validAvailability, animalType.availability);
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
        AnimalType animalType1 = new AnimalType("able.Dog", "Available");
        AnimalType animalType2 = new AnimalType("able.Dog", "Available");
        assertTrue(animalType1.equals(animalType2));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        AnimalType animalType1 = new AnimalType("able.Dog", "Available");
        AnimalType animalType2 = new AnimalType("able.Cat", "Available");
        assertFalse(animalType1.equals(animalType2));
    }

    @Test
    public void constructor_invalidAnimalTypeWithAvailability_throwsIllegalArgumentException() {
        String invalidValue = "invalidCat";
        String validAvailability = "Available";
        assertThrows(IllegalArgumentException.class, () -> new AnimalType(invalidValue, validAvailability));
    }

}
