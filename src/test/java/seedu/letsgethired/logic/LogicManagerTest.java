package seedu.letsgethired.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX;
import static seedu.letsgethired.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.letsgethired.logic.commands.CommandTestUtil.COMPANY_DESC_JANE_STREET;
import static seedu.letsgethired.logic.commands.CommandTestUtil.CYCLE_DESC_SUMMER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.ROLE_DESC_FULL_STACK;
import static seedu.letsgethired.logic.commands.CommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.A;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.letsgethired.logic.commands.AddCommand;
import seedu.letsgethired.logic.commands.CommandResult;
import seedu.letsgethired.logic.commands.ListCommand;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.storage.JsonInternTrackerStorage;
import seedu.letsgethired.storage.JsonUserPrefsStorage;
import seedu.letsgethired.storage.StorageManager;
import seedu.letsgethired.testutil.InternApplicationBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonInternTrackerStorage internTrackerStorage =
                new JsonInternTrackerStorage(temporaryFolder.resolve("letsgethired.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(internTrackerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION,
                String.format(LogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION,
                String.format(LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredInternApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredInternApplicationList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage, Model expectedModel)
            throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(logic.getInternTracker(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e               the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an InternTrackerStorage that throws the IOException e when saving
        JsonInternTrackerStorage internBookStorage = new JsonInternTrackerStorage(prefPath) {
            @Override
            public void saveInternTracker(ReadOnlyInternTracker internTracker, Path filePath) throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(internBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveInternTracker method by executing an add command
        String addCommand = AddCommand.COMMAND_WORD
                + COMPANY_DESC_JANE_STREET
                + ROLE_DESC_FULL_STACK
                + CYCLE_DESC_SUMMER
                + STATUS_DESC_ACCEPTED;
        InternApplication expectedInternApplication = new InternApplicationBuilder(A).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addInternApplication(expectedInternApplication);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
