package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;


public class JsonSerializableTeamBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTeamBookTest");
    private static final Path TYPICAL_TEAMS_FILE = TEST_DATA_FOLDER.resolve("typicalTeamsTeamBook.json");
    private static final Path INVALID_TEAM_FILE = TEST_DATA_FOLDER.resolve("invalidTeamTeamBook.json");
    private static final Path DUPLICATE_TEAM_FILE = TEST_DATA_FOLDER.resolve("duplicateTeamTeamBook.json");
    @Test
    public void toModelType_invalidTeamFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTeamBook dataFromFile = JsonUtil.readJsonFile(INVALID_TEAM_FILE,
                JsonSerializableTeamBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTeams_throwsIllegalValueException() throws Exception {
        JsonSerializableTeamBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TEAM_FILE,
                JsonSerializableTeamBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeamBook.MESSAGE_DUPLICATE_TEAM,
                dataFromFile::toModelType);
    }
}
