package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LoadCommand.MESSAGE_FILE_CANNOT_LOAD;
import static seedu.address.logic.commands.LoadCommand.MESSAGE_FILE_NOT_FOUND;
import static seedu.address.logic.commands.LoadCommand.MESSAGE_LOAD_SUCCESS;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

public class LoadCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLoadCommandTest");
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void load_validFile_success() throws CommandException {
        String validFileName = "validAddressBook";
        Path validFilePath = TEST_DATA_FOLDER.resolve(validFileName + ".json");
        LoadCommand loadCommand = new LoadCommand(validFileName, validFilePath);
        Model expectedModel = new ModelManager();
        loadAddressBook(expectedModel, validFileName, validFilePath);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_LOAD_SUCCESS, validFileName), false, false, true);
        assertCommandSuccess(loadCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void load_missingFile_throwsCommandException() {
        String missingFileName = "missingAddressBook";
        Path missingFilePath = TEST_DATA_FOLDER.resolve(missingFileName + ".json");
        LoadCommand loadCommand = new LoadCommand(missingFileName, missingFilePath);
        assertCommandFailure(loadCommand, model, String.format(MESSAGE_FILE_NOT_FOUND, missingFileName));
    }

    @Test
    public void load_corruptFile_throwsCommandException() {
        String corruptFileName = "corruptAddressBook";
        Path corruptFilePath = TEST_DATA_FOLDER.resolve(corruptFileName + ".json");
        LoadCommand loadCommand = new LoadCommand(corruptFileName, corruptFilePath);
        assertCommandFailure(loadCommand, model, String.format(MESSAGE_FILE_CANNOT_LOAD, corruptFileName));
    }

    public void loadAddressBook(Model expectedModel, String fileName, Path filePath) throws CommandException {
        AddressBookStorage tempAddressBookStorage = new JsonAddressBookStorage(filePath);
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook tempData;
        try {
            addressBookOptional = tempAddressBookStorage.readAddressBook();
            tempData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataLoadingException e) {
            throw new CommandException(String.format(MESSAGE_FILE_CANNOT_LOAD, fileName));
        }
        expectedModel.setAddressBookFilePath(filePath);
        expectedModel.setAddressBook(tempData);
    }

    @Test
    public void equals() {
        String firstFileName = "validAddressBook";
        Path firstFilePath = TEST_DATA_FOLDER.resolve(firstFileName + ".json");

        String secondFileName = "addressBook";
        Path secondFilePath = TEST_DATA_FOLDER.resolve(secondFileName + ".json");

        LoadCommand loadFirstCommand = new LoadCommand(firstFileName, firstFilePath);
        LoadCommand loadSecondCommand = new LoadCommand(secondFileName, secondFilePath);

        // same object -> returns true
        assertTrue(loadFirstCommand.equals(loadFirstCommand));

        // same values -> returns true
        LoadCommand loadFirstCommandCopy = new LoadCommand(firstFileName, firstFilePath);
        assertTrue(loadFirstCommand.equals(loadFirstCommandCopy));

        // different types -> returns false
        assertFalse(loadFirstCommand.equals(1));

        // null -> returns false
        assertFalse(loadFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(loadFirstCommand.equals(loadSecondCommand));
    }
}
