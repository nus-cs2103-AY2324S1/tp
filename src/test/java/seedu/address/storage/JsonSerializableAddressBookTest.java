package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalAppointments;
import seedu.address.testutil.TypicalDentists;
import seedu.address.testutil.TypicalPatients;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTreatments;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsAddressBook.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientAddressBook.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientAddressBook.json");
    private static final Path TYPICAL_DENTISTS_FILE = TEST_DATA_FOLDER.resolve("typicalDentistsAddressBook.json");
    private static final Path INVALID_DENTIST_FILE = TEST_DATA_FOLDER.resolve("invalidDentistAddressBook.json");
    private static final Path DUPLICATE_DENTIST_FILE = TEST_DATA_FOLDER.resolve("duplicateDentistAddressBook.json");
    private static final Path TYPICAL_TREATMENTS_FILE = TEST_DATA_FOLDER.resolve("typicalTreatmentsAddressBook.json");
    private static final Path DUPLICATE_TREATMENT_FILE = TEST_DATA_FOLDER.resolve("duplicateTreatmentAddressBook.json");
    private static final Path TYPICAL_APPOINTMENTS_FILE =
        TEST_DATA_FOLDER.resolve("typicalAppointmentsAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PERSON,
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
            JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPatientsAddressBook = TypicalPatients.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPatientsAddressBook);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PATIENT,
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalDentistFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_DENTISTS_FILE,
            JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalDentistsAddressBook = TypicalDentists.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalDentistsAddressBook);
    }

    @Test
    public void toModelType_invalidDentistFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_DENTIST_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateDentists_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DENTIST_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_DENTIST,
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalTreatmentsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TREATMENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTreatmentsAddressBook = TypicalTreatments.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalTreatmentsAddressBook);
    }

    @Test
    public void toModelType_duplicateTreatments_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TREATMENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_TREATMENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalAppointmentsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPOINTMENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalAppointmentsAddressBook = TypicalAppointments.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalAppointmentsAddressBook);
    }

}
