package seedu.ccacommander.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalAddressBook.getTypicalEventAddressBook;
import static seedu.ccacommander.testutil.TypicalAddressBook.getTypicalMemberAddressBook;

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

    @Test
    public void toModelType_typicalMembersFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEMBERS_FILE,
                JsonSerializableAddressBook.class).get();
        CcaCommander ccaCommanderFromFile = dataFromFile.toModelType();
        CcaCommander typicalMembersCcaCommander = getTypicalMemberAddressBook();
        assertEquals(ccaCommanderFromFile, typicalMembersCcaCommander);
    }

    @Test
    public void toModelType_invalidMemberFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_MEMBER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMembers_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEMBER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_MEMBER,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalEventFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENT_FILE,
                JsonSerializableAddressBook.class).get();
        CcaCommander ccaCommanderFromFile = dataFromFile.toModelType();
        CcaCommander typicalEventsCcaCommander = getTypicalEventAddressBook();
        assertEquals(ccaCommanderFromFile, typicalEventsCcaCommander);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }
}
