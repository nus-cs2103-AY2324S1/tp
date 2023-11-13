package profplan.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static profplan.logic.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static profplan.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import profplan.logic.commands.AddCommand;
import profplan.logic.commands.CommandResult;
import profplan.logic.commands.CommandTestUtil;
import profplan.logic.commands.ListCommand;
import profplan.logic.commands.exceptions.CommandException;
import profplan.logic.parser.exceptions.ParseException;
import profplan.model.Model;
import profplan.model.ModelManager;
import profplan.model.ReadOnlyProfPlan;
import profplan.model.UserConfigs;
import profplan.model.UserPrefs;
import profplan.model.task.Task;
import profplan.storage.JsonProfPlanStorage;
import profplan.storage.JsonUserConfigsStorage;
import profplan.storage.JsonUserPrefsStorage;
import profplan.storage.StorageManager;
import profplan.testutil.Assert;
import profplan.testutil.TaskBuilder;
import profplan.testutil.TypicalTasks;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonProfPlanStorage profPlanStorage =
                new JsonProfPlanStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonUserConfigsStorage userConfigsStorage = new JsonUserConfigsStorage(
                temporaryFolder.resolve("userConfigs.json"));
        StorageManager storage = new StorageManager(profPlanStorage, userPrefsStorage, userConfigsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTaskList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
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
        Model expectedModel = new ModelManager(model.getProfPlan(), new UserPrefs(), new UserConfigs());
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
        Assert.assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
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

        // Inject LogicManager with an ProfPlanStorage that throws the IOException e when saving
        JsonProfPlanStorage profPlanStorage = new JsonProfPlanStorage(prefPath) {
            @Override
            public void saveProfPlan(ReadOnlyProfPlan addressBook, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        JsonUserConfigsStorage userConfigsStorage =
                new JsonUserConfigsStorage(temporaryFolder.resolve("ExceptionUserConfigs.json"));
        StorageManager storage = new StorageManager(profPlanStorage, userPrefsStorage, userConfigsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveAddressBook method by executing an add command
        String addCommand = AddCommand.COMMAND_WORD + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.PRIORITY_DESC_AMY;
        Task expectedTask = new TaskBuilder(TypicalTasks.AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addTask(expectedTask);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
