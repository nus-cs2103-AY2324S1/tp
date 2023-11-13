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
    private static final Path APPOINTMENT_OVERLAP_FILE = TEST_DATA_FOLDER
            .resolve("overlapAppointmentWellNus.json");
    private static final Path APPOINTMENT_STUDENT_NOT_FOUND_FILE = TEST_DATA_FOLDER
            .resolve("appointmentStudentNotFoundWellNus.json");


    // EP: Valid file
    @Test
    public void toModelType_typicalWellNusFile_success() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(TYPICAL_WELLNUS_FILE,
                JsonSerializableWellNus.class).get();
        WellNus wellNusFromFile = dataFromFile.toModelType();
        WellNus typicalStudentsWellNus = getTypicalWellNus();
        assertEquals(wellNusFromFile, typicalStudentsWellNus);
    }

    // EP: Invalid student in json file
    @Test
    public void toModelType_invalidStudentWellNusFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    // EP: Duplicate student in json file
    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWellNus.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    // EP: Invalid appointments in json file
    @Test
    public void toModelType_invalidAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    // EP: Duplicate appointments in json file
    @Test
    public void toModelType_duplicateAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPOINTMENT_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWellNus.MESSAGE_DUPLICATE_APPOINTMENT,
                dataFromFile::toModelType);
    }

    // EP: Overlapping appointments in json file
    @Test
    public void toModelType_overlappingAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(APPOINTMENT_OVERLAP_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWellNus.MESSAGE_OVERLAPPING_APPOINTMENT,
                dataFromFile::toModelType);
    }

    // Case where appointment in storage does not correspond to a student in the json file
    @Test
    public void toModelType_appointmentStudentNotFound_throwsIllegalValueException() throws Exception {
        JsonSerializableWellNus dataFromFile = JsonUtil.readJsonFile(APPOINTMENT_STUDENT_NOT_FOUND_FILE,
                JsonSerializableWellNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWellNus.MESSAGE_STUDENT_NOT_FOUND,
                dataFromFile::toModelType);
    }
}
