package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

public class RedoCommandTest {

    // PATIENT COMMANDS
    private static final String SAMPLE_ADD_COMMAND_1 =
            "add name=John Doe gender=MALE birthdate=2000/10/20 phone=98765432 email=johnd@example.com address=311, "
                    + "Clementi Ave 2, #02-25 illness=fever";
    private static final String SAMPLE_ADD_COMMAND_2 =
            "add name=Jerry gender=MALE birthdate=2000/11/11 phone=88888888 email=jerry@example.com address=113, "
                    + "Clementi Ave 1999, #22-54 illness=flu";

    // APPOINTMENT COMMANDS
    private static final String SAMPLE_SCHEDULE_COMMAND_1 =
            "schedule patient=John Doe start=2023/10/20 12:00 end=2023/10/20 13:59 description=test";
    // OTHER COMMANDS
    private static final String SAMPLE_UNDO_COMMAND = "undo";
    private static final String SAMPLE_REDO_COMMAND = "redo";

    @TempDir
    public Path temporaryFolder;
    private Logic logic;
    private Model model = new ModelManager();
    private Storage storage;
    private AddressBookParser addressBookParser = new AddressBookParser();
    private Command redoCommand = new RedoCommand();

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_redoPatientCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_UNDO_COMMAND);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.redoHistory();
        String expectedMessage = RedoCommand.MESSAGE_REDO_COMMAND_SUCCESS;

        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoAppointmentCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_SCHEDULE_COMMAND_1);
        logic.execute(SAMPLE_UNDO_COMMAND);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.redoHistory();
        String expectedMessage = RedoCommand.MESSAGE_REDO_COMMAND_SUCCESS;

        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoAfterNoUndoCommand_throwsCommandException() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_NO_COMMAND_TO_REDO_ERROR);
    }

    @Test
    public void execute_multipleRedoCommands_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_ADD_COMMAND_2);
        logic.execute(SAMPLE_SCHEDULE_COMMAND_1);
        logic.execute(SAMPLE_UNDO_COMMAND);
        logic.execute(SAMPLE_UNDO_COMMAND);

        String expectedMessage = RedoCommand.MESSAGE_REDO_COMMAND_SUCCESS;
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.redoHistory();
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);

        expectedModel.redoHistory();
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moreRedoCommandsThanUndoCommands_throwsCommandException()
            throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_ADD_COMMAND_2);
        logic.execute(SAMPLE_SCHEDULE_COMMAND_1);
        logic.execute(SAMPLE_UNDO_COMMAND);
        logic.execute(SAMPLE_UNDO_COMMAND);
        logic.execute(SAMPLE_REDO_COMMAND);
        logic.execute(SAMPLE_REDO_COMMAND);

        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_NO_COMMAND_TO_REDO_ERROR);
    }


}
