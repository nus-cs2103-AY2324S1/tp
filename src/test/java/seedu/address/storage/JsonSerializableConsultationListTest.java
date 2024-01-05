package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ConsultationListBook;
import seedu.address.testutil.TypicalConsultations;

public class JsonSerializableConsultationListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableConsultationListTest");
    private static final Path TYPICAL_CONSULTATION_FILE = TEST_DATA_FOLDER
            .resolve("typicalConsultationList.json");
    private static final Path INVALID_CONSULTATION_FILE = TEST_DATA_FOLDER
            .resolve("invalidConsultationList.json");
    private static final Path DUPLICATE_CONSULTATION_FILE = TEST_DATA_FOLDER
            .resolve("duplicateConsultationList.json");

    @Test
    public void toModelType_typicalConsultationsFile_success() throws Exception {
        JsonSerializableConsultationList dataFromFile = JsonUtil.readJsonFile(TYPICAL_CONSULTATION_FILE,
                JsonSerializableConsultationList.class).get();
        ConsultationListBook consultationList = dataFromFile.toModelType();
        ConsultationListBook typicalConsultationList = TypicalConsultations.getTypicalConsultationListBook();
        assertEquals(consultationList, typicalConsultationList);
    }

    @Test
    public void toModelType_invalidConsultationsFile_throwsDateTimeParseException() throws Exception {
        JsonSerializableConsultationList dataFromFile = JsonUtil.readJsonFile(INVALID_CONSULTATION_FILE,
                JsonSerializableConsultationList.class).get();
        assertThrows(DateTimeParseException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableConsultationList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONSULTATION_FILE,
                JsonSerializableConsultationList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableConsultationList.MESSAGE_DUPLICATE_CONSULTATION,
                dataFromFile::toModelType);
    }
}
