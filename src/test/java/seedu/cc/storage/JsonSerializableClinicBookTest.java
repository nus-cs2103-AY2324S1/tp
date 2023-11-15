package seedu.cc.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.commons.util.JsonUtil;
import seedu.cc.model.ClinicBook;
import seedu.cc.testutil.TypicalPatients;

public class JsonSerializableClinicBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableClinicBookTest");
    private static final Path TYPICAL_PATIENT_FILE = TEST_DATA_FOLDER.resolve("typicalPatientClinicBook.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientClinicBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonClinicBook.json");

    @Test
    public void toModelType_typicalPatientFile_success() throws Exception {
        JsonSerializableClinicBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENT_FILE,
                JsonSerializableClinicBook.class).get();
        ClinicBook addressBookFromFile = dataFromFile.toModelType();
        ClinicBook typicalPersonsClinicBook = TypicalPatients.getTypicalClinicBook();
        assertEquals(addressBookFromFile, typicalPersonsClinicBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableClinicBook dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializableClinicBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableClinicBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableClinicBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableClinicBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
