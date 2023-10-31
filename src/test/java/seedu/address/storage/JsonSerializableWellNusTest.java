package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWellNus.getTypicalWellNus;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.WellNus;

public class JsonSerializableWellNusTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableWellNusTest");
    private static final Path TYPICAL_WELLNUS_FILE = TEST_DATA_FOLDER.resolve("typicalWellNus.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentWellNus.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentWellNus.json");
    private static final Path INVALID_APPOINTMENT_FILE = TEST_DATA_FOLDER.resolve("invalidAppointmentWellNus.json");
    private static final Path DUPLICATE_APPOINTMENT_FILE = TEST_DATA_FOLDER.resolve("duplicateAppointmentWellNus.json");


    @Test
    public void toModelType_typicalWellNusFile_success() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(TYPICAL_WELLNUS_FILE,
                JsonSerializableWellNus.class).get();
        WellNus wellNusFromFile = dataFromFile.toModelType();
        WellNus typicalStudentsWellNus = getTypicalWellNus();
        assertEquals(wellNusFromFile, typicalStudentsWellNus);
    }

    @Test
    public void toModelType_invalidStudentWellNusFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWellNus.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPOINTMENT_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWellNus.MESSAGE_DUPLICATE_APPOINTMENT,
                dataFromFile::toModelType);
    }
}
