package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAnimal.INVALID_FIELDS_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedAnimal.MISSING_FIELDS_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.TOFU;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;






public class JsonAdaptedAnimalTest {
    private static final String INVALID_NAME = "Pookie Be@r";
    private static final String INVALID_PET_ID = "abcd";
    private static final String INVALID_SEX = "JuiceBox";
    private static final String INVALID_SPECIES = "5 doggies";
    private static final String INVALID_BREED = "1 Loyboy";
    private static final String INVALID_DATE_OF_BIRTH = "50 September 1960";
    private static final String INVALID_ADMISSION_DATE = "2023-12-00";

    private static final String VALID_NAME = TOFU.getNameForSerialization();
    private static final String VALID_PET_ID = TOFU.getPetIdForSerialization();
    private static final String VALID_SEX = TOFU.getSexForSerialization();
    private static final String VALID_SPECIES = TOFU.getSpeciesForSerialization();
    private static final String VALID_BREED = TOFU.getBreedForSerialization();
    private static final String VALID_DATE_OF_BIRTH = TOFU.getDateOfBirthForSerialization();
    private static final String VALID_ADMISSION_DATE = TOFU.getAdmissionDateForSerialization();

    private static final List<JsonAdaptedTask> VALID_TASK_LIST = TOFU.getTaskListForSerialization();

    @Test
    public void toAnimalModel_validAnimalDetails_returnsAnimal() throws Exception {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(TOFU);
        assertEquals(TOFU, animal.toAnimalModel());
    }

    @Test
    public void toAnimalModel_invalidName_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(INVALID_NAME, VALID_PET_ID, VALID_SEX, VALID_SPECIES, VALID_BREED,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(INVALID_FIELDS_MESSAGE_FORMAT, Name.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_nullName_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(null, VALID_PET_ID, VALID_SEX, VALID_SPECIES, VALID_BREED,
            VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELDS_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_invalidPetId_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, INVALID_PET_ID, VALID_SEX, VALID_SPECIES, VALID_BREED,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(INVALID_FIELDS_MESSAGE_FORMAT, PetId.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_nullPetId_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, null, VALID_SEX, VALID_SPECIES, VALID_BREED,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELDS_MESSAGE_FORMAT, PetId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_invalidSex_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, INVALID_SEX, VALID_SPECIES, VALID_BREED,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(INVALID_FIELDS_MESSAGE_FORMAT, Sex.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_nullSex_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, null, VALID_SPECIES, VALID_BREED,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELDS_MESSAGE_FORMAT, Sex.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_invalidSpecies_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, VALID_SEX, INVALID_SPECIES, VALID_BREED,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(INVALID_FIELDS_MESSAGE_FORMAT, Species.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_nullSpecies_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, VALID_SEX, null, VALID_BREED,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELDS_MESSAGE_FORMAT, Species.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_invalidBreed_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, VALID_SEX, VALID_SPECIES, INVALID_BREED,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(INVALID_FIELDS_MESSAGE_FORMAT, Breed.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_nullBreed_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, VALID_SEX, VALID_SPECIES, null,
                VALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELDS_MESSAGE_FORMAT, Breed.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, VALID_SEX, VALID_SPECIES, VALID_BREED,
                INVALID_DATE_OF_BIRTH, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(INVALID_FIELDS_MESSAGE_FORMAT, DateOfBirth.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_nullDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, VALID_SEX, VALID_SPECIES, VALID_BREED,
                null, VALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELDS_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_invalidAdmissionDate_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, VALID_SEX, VALID_SPECIES, VALID_BREED,
                VALID_DATE_OF_BIRTH, INVALID_ADMISSION_DATE, VALID_TASK_LIST);
        String expectedMessage = String.format(INVALID_FIELDS_MESSAGE_FORMAT, AdmissionDate.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }

    @Test
    public void toAnimalModel_nullAdmissionDate_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
            new JsonAdaptedAnimal(VALID_NAME, VALID_PET_ID, VALID_SEX, VALID_SPECIES, VALID_BREED,
                VALID_DATE_OF_BIRTH, null, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELDS_MESSAGE_FORMAT, AdmissionDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toAnimalModel);
    }
}
