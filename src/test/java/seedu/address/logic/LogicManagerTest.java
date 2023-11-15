package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ANIMAL_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ANIMAL_TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.AVAILABILITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.HOUSING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SaveCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewExitCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Logic logic;
    private Model backupModel;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
        backupModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }
    @Test
    public void testFinalConfirmationOnClearCommand() {

        try {
            logic.execute("reset");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(logic.getFinalConfirmation());
    }

    @Test
    public void equalsAddressBookPath() {
        assertTrue(logic.getAddressBookFilePath().equals(Paths.get("data", "addressbook.json")));
    }

    @Test
    public void execute_nonClearCommand_shouldSetFinalConfirmationToFalse() throws Exception {
        try {
            logic.execute("random command");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assert
        assertFalse(logic.getFinalConfirmation());
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_addressBookParserCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertAddressBookCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void executeInView_viewExitCommand_success() throws Exception {
        String viewExitCommand = ViewExitCommand.COMMAND_WORD;
        assertViewModeCommandSuccess(viewExitCommand, ViewExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, model);
    }


    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                LogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void executeInView_storageThrowsIoException_throwsCommandException() {
        assertViewModeCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                LogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void executeInView_storageThrowsAdException_throwsCommandException() {
        assertViewModeCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    @Test
    public void execute_undoCommand_success() throws CommandException, ParseException, DataLoadingException,
            IOException {
        String deleteCommand = "delete 1";
        logic.execute(deleteCommand);

        String undoCommand = "undo";
        CommandResult undoResult = logic.execute(undoCommand);

        assertEquals(undoResult.getFeedbackToUser(), UndoCommand.MESSAGE_SUCCESS);
        assertEquals(model, backupModel);
    }

    @Test
    public void execute_nonUndoCommand_success() throws CommandException, ParseException, DataLoadingException,
            IOException {
        // Execute a command that modifies the model
        String deleteCommand = "delete 1";
        logic.execute(deleteCommand);

        String nonUndoCommand = "list";
        CommandResult nonUndoResult = logic.execute(nonUndoCommand);
        Model expectedModel = new ModelManager(logic.getAddressBook(), new UserPrefs());
        assertNotEquals(nonUndoResult.getFeedbackToUser(), UndoCommand.MESSAGE_SUCCESS);
        assertEquals(model.getAddressBook(), expectedModel.getAddressBook());
    }

    @Test
    public void execute_undoCommandWithNullModel_success() throws CommandException, ParseException,
            DataLoadingException, IOException {
        String undoCommand = "undo";
        CommandResult undoResult = logic.execute(undoCommand);

        assertEquals(UndoCommand.MESSAGE_UNDO_DONE, undoResult.getFeedbackToUser());
        assertEquals(backupModel, model);
    }

    @Test
    public void setGuiSettings_validGuiSettings_success() {
        GuiSettings guiSettings = new GuiSettings(800, 600, 2, 2);

        logic.setGuiSettings(guiSettings);

        GuiSettings updatedGuiSettings = logic.getGuiSettings();

        assertEquals(guiSettings, updatedGuiSettings);
    }


    private void assertAddressBookCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException, DataLoadingException, IOException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Sets the boolean value isInViewMode to true to represent being in a profile view page after View command,
     * then executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     */
    private void assertViewModeCommandSuccess(String inputCommand, String expectedMessage,
                                                 Model expectedModel) throws CommandException, ParseException,
            DataLoadingException, IOException {
        String viewCommand = "view 1";
        CommandResult viewCommandResult = logic.execute(viewCommand);
        CommandResult result = logic.executeInView(
                inputCommand,
                viewCommandResult.getPersonToView(),
                viewCommandResult.getTargetIndex()
        );
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException, DataLoadingException, IOException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command in profile page and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     */
    private void assertViewModeCommandFailure(String inputCommand, Person p, Index index,
                                              Class<? extends Throwable> expectedException,
                                              String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.executeInView(inputCommand, p, index));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an AddressBookStorage that throws the IOException e when saving
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(prefPath) {
            @Override
            public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveAddressBook method by executing an add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + ANIMAL_NAME_DESC_AMY
                + AVAILABILITY_DESC_AMY
                + ANIMAL_TYPE_DESC_AMY
                + HOUSING_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedPerson);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertViewModeCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an AddressBookStorage that throws the IOException e when saving
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(prefPath) {
            @Override
            public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveAddressBook method by executing a save command
        String saveCommand = SaveCommand.SAVE_COMMAND_WORD;

        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPerson);
        assertViewModeCommandFailure(saveCommand, expectedPerson,
                Index.fromZeroBased(0), CommandException.class, expectedMessage, expectedModel);
    }
}
