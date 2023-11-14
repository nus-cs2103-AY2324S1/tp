package seedu.lovebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lovebook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.commons.util.JsonUtil;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.testutil.TypicalDatePrefs;

public class JsonSerializableDatePrefsTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableDatePrefsTest");
    private static final Path TYPICAL_DATE_PREFS_FILE = TEST_DATA_FOLDER.resolve("typicalDatePrefs.json");
    private static final Path INVALID_DATE_PREFS_FILE = TEST_DATA_FOLDER.resolve("invalidDatePrefs.json");

    @Test
    public void toModelType_typicalDatePrefsFile_success() throws Exception {
        JsonSerializableDatePrefs dataFromFile = JsonUtil.readJsonFile(TYPICAL_DATE_PREFS_FILE,
                JsonSerializableDatePrefs.class).get();
        DatePrefs datePrefsFromFile = dataFromFile.toModelType();
        DatePrefs typicalDatePrefs = TypicalDatePrefs.getTypicalDatePrefs();
        assertEquals(datePrefsFromFile, typicalDatePrefs);
    }

    @Test
    public void toModelType_invalidDatePrefsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDatePrefs dataFromFile = JsonUtil.readJsonFile(INVALID_DATE_PREFS_FILE,
                JsonSerializableDatePrefs.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
