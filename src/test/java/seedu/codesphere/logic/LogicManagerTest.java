package seedu.codesphere.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX;
import static seedu.codesphere.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalCourses.CS2100;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.codesphere.logic.commands.AddCourseCommand;
import seedu.codesphere.logic.commands.CommandResult;
import seedu.codesphere.logic.commands.ExitCommand;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.storage.InputHistory;
import seedu.codesphere.storage.InputStorage;
import seedu.codesphere.storage.JsonCourseListStorage;
import seedu.codesphere.storage.JsonUserPrefsStorage;
import seedu.codesphere.storage.StorageManager;
import seedu.codesphere.testutil.CourseBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonCourseListStorage courseListStorage =
                new JsonCourseListStorage(temporaryFolder.resolve("courseList.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        InputStorage inputStorage = new InputHistory();
        StorageManager storage = new StorageManager(courseListStorage, userPrefsStorage, inputStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String exitCommand = ExitCommand.COMMAND_WORD;
        assertCommandSuccess(exitCommand, ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                LogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredCourseList().remove(0));
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
        Model expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
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
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an AddressBookStorage that throws the IOException e when saving
        JsonCourseListStorage courseListStorage = new JsonCourseListStorage(prefPath) {
            @Override
            public void saveCourseList(ReadOnlyCourseList courseList, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        InputStorage inputStorage = new InputHistory();
        StorageManager storage = new StorageManager(courseListStorage, userPrefsStorage, inputStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveCourseList method by executing an add course command
        String addCourseCommand = AddCourseCommand.COMMAND_WORD + " c/CS2100";
        Course expectedCourse = new CourseBuilder(CS2100).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addCourse(expectedCourse);
        assertCommandFailure(addCourseCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
