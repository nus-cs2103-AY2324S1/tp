package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_USER_PREFS_CANNOT_LOAD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ConfigCommand.MESSAGE_CONFIG_SUCCESS;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.UserPrefsStorage;

public class ConfigCommandTest {

    @Test
    public void configure_validArg_success() throws CommandException {
        int tutorialCount = 3;
        int assignmentCount = 2;
        ConfigCommand configCommand = new ConfigCommand(tutorialCount, assignmentCount);

        Path notConfiguredUserPrefsPath = Paths.get(
                "src", "test", "data", "JsonUserPrefsStorageTest", "NotConfiguredUserPref.json");
        Model model = new ModelManager(getTypicalAddressBook(), getUserPrefs(notConfiguredUserPrefsPath));

        Path validUserPrefsPath = Paths.get(
                "src", "test", "data", "JsonUserPrefsStorageTest", "TypicalUserPref.json");
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getUserPrefs(validUserPrefsPath));

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_CONFIG_SUCCESS, tutorialCount, assignmentCount));

        assertCommandSuccess(configCommand, model, expectedCommandResult, expectedModel);
    }

    public UserPrefs getUserPrefs(Path userPrefsPath) throws CommandException {
        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(userPrefsPath);
        Optional<UserPrefs> userPrefsOptional;
        try {
            userPrefsOptional = userPrefsStorage.readUserPrefs();
            return userPrefsOptional.orElse(new UserPrefs());
        } catch (DataLoadingException e) {
            throw new CommandException(MESSAGE_USER_PREFS_CANNOT_LOAD);
        }
    }
}
