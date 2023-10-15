package seedu.staffsnap.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.commons.exceptions.IllegalValueException;
import seedu.staffsnap.commons.util.JsonUtil;
import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.testutil.TypicalApplicants;

public class JsonSerializableApplicantBookTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableApplicantBookTest");
    private static final Path TYPICAL_APPLICANTS_FILE = TEST_DATA_FOLDER
            .resolve("typicalApplicantsApplicantBook.json");
    private static final Path INVALID_APPLICANT_FILE = TEST_DATA_FOLDER
            .resolve("invalidApplicantApplicantBook.json");
    private static final Path DUPLICATE_APPLICANT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateApplicantApplicantBook.json");

    @Test
    public void toModelType_typicalApplicantsFile_success() throws Exception {
        JsonSerializableApplicantBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICANTS_FILE,
                JsonSerializableApplicantBook.class).get();
        ApplicantBook applicantBookFromFile = dataFromFile.toModelType();
        ApplicantBook typicalApplicantsApplicantBook = TypicalApplicants.getTypicalApplicantBook();
        assertEquals(applicantBookFromFile, typicalApplicantsApplicantBook);
    }

    @Test
    public void toModelType_invalidApplicantFile_throwsIllegalValueException() throws Exception {
        JsonSerializableApplicantBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICANT_FILE,
                JsonSerializableApplicantBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplicants_throwsIllegalValueException() throws Exception {
        JsonSerializableApplicantBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICANT_FILE,
                JsonSerializableApplicantBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableApplicantBook.MESSAGE_DUPLICATE_APPLICANT,
                dataFromFile::toModelType);
    }

}
