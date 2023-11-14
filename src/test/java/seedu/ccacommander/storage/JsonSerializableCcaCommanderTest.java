package seedu.ccacommander.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalEnrolmentCcaCommander;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalEventCcaCommander;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalMemberCcaCommander;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.exceptions.IllegalValueException;
import seedu.ccacommander.commons.util.JsonUtil;
import seedu.ccacommander.model.CcaCommander;

public class JsonSerializableCcaCommanderTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCcaCommanderTest");
    private static final Path TYPICAL_MEMBERS_FILE = TEST_DATA_FOLDER.resolve("typicalMembersCcaCommander.json");
    private static final Path INVALID_MEMBER_FILE = TEST_DATA_FOLDER.resolve("invalidMemberCcaCommander.json");
    private static final Path DUPLICATE_MEMBER_FILE = TEST_DATA_FOLDER.resolve("duplicateMemberCcaCommander.json");
    private static final Path TYPICAL_EVENT_FILE = TEST_DATA_FOLDER.resolve("typicalEventsCcaCommander.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventCcaCommander.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventCcaCommander.json");
    private static final Path TYPICAL_ENROLMENT_FILE = TEST_DATA_FOLDER.resolve("typicalEnrolmentCcaCommander.json");
    private static final Path INVALID_ENROLMENT_FILE = TEST_DATA_FOLDER.resolve("invalidEnrolmentCcaCommander.json");
    private static final Path DUPLICATE_ENROLMENT_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateEnrolmentCcaCommander.json");

    @Test
    public void toModelType_typicalMembersFile_success() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEMBERS_FILE,
                JsonSerializableCcaCommander.class).get();
        CcaCommander ccaCommanderFromFile = dataFromFile.toModelType();
        CcaCommander typicalMembersCcaCommander = getTypicalMemberCcaCommander();
        assertEquals(ccaCommanderFromFile, typicalMembersCcaCommander);
    }

    @Test
    public void toModelType_invalidMemberFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(INVALID_MEMBER_FILE,
                JsonSerializableCcaCommander.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMembers_throwsIllegalValueException() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEMBER_FILE,
                JsonSerializableCcaCommander.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCcaCommander.MESSAGE_DUPLICATE_MEMBER,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalEventFile_success() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENT_FILE,
                JsonSerializableCcaCommander.class).get();
        CcaCommander ccaCommanderFromFile = dataFromFile.toModelType();
        CcaCommander typicalEventsCcaCommander = getTypicalEventCcaCommander();
        assertEquals(ccaCommanderFromFile, typicalEventsCcaCommander);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableCcaCommander.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableCcaCommander.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCcaCommander.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalEnrolmentFile_success() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(TYPICAL_ENROLMENT_FILE,
                JsonSerializableCcaCommander.class).get();
        CcaCommander ccaCommanderFromFile = dataFromFile.toModelType();
        CcaCommander typicalEnrolmentCcaCommander = getTypicalEnrolmentCcaCommander();
        assertEquals(ccaCommanderFromFile, typicalEnrolmentCcaCommander);
    }

    @Test
    public void toModelType_invalidEnrolmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(INVALID_ENROLMENT_FILE,
                JsonSerializableCcaCommander.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEnrolment_throwsIllegalValueException() throws Exception {
        JsonSerializableCcaCommander dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ENROLMENT_FILE,
                JsonSerializableCcaCommander.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCcaCommander.MESSAGE_DUPLICATE_ENROLMENT,
                dataFromFile::toModelType);
    }
}
