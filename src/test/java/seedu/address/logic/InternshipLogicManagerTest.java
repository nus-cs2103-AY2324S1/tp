package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_JANESTREET;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.JANESTREET;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CreateCommand;
//import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.internship.Internship;
import seedu.address.storage.InternshipStorageManager;
import seedu.address.storage.JsonInternshipBookStorage;
import seedu.address.storage.JsonInternshipUserPrefsStorage;
import seedu.address.testutil.InternshipBuilder;

public class InternshipLogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private InternshipModel model = new InternshipModelManager();
    private InternshipLogic logic;

    @BeforeEach
    public void setUp() {
        JsonInternshipBookStorage internshipBookStorage =
                new JsonInternshipBookStorage(temporaryFolder.resolve("internshipBook.json"));
        JsonInternshipUserPrefsStorage userPrefsStorage = new JsonInternshipUserPrefsStorage(
                temporaryFolder.resolve("userPrefs.json"));
        InternshipStorageManager storage = new InternshipStorageManager(internshipBookStorage, userPrefsStorage);
        logic = new InternshipLogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String createCommand = CreateCommand.COMMAND_WORD + COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET
                + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET + START_DATE_DESC_JANESTREET
                + DURATION_DESC_JANESTREET;
        Internship createdInternship = new InternshipBuilder().withCompanyName(VALID_COMPANY_NAME_JANESTREET)
                        .withRole(VALID_ROLE_JANESTREET).withApplicationStatus(VALID_APPLICATIONSTATUS_JANESTREET)
                        .withDeadline(VALID_DEADLINE_JANESTREET, VALID_STARTDATE_JANESTREET)
                        .withStartDate(VALID_STARTDATE_JANESTREET)
                        .withDuration(VALID_DURATION_JANESTREET).build();
        assertCommandSuccess(createCommand, String.format(
                CreateCommand.MESSAGE_SUCCESS,
                Messages.format(createdInternship)),
                model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                InternshipLogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                InternshipLogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredInternshipList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, InternshipModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            InternshipModel expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String,  InternshipModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, InternshipModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, InternshipModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        InternshipModel expectedModel = new InternshipModelManager(
                model.getInternshipBook(),
                new InternshipUserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, InternshipModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, InternshipModel expectedModel) {
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

        // Inject LogicManager with an InternshipBookStorage that throws the IOException e when saving
        JsonInternshipBookStorage internshipBookStorage = new JsonInternshipBookStorage(prefPath) {
            @Override
            public void saveInternshipBook(ReadOnlyInternshipBook internshipBook, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonInternshipUserPrefsStorage userPrefsStorage =
                new JsonInternshipUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        InternshipStorageManager storage = new InternshipStorageManager(internshipBookStorage, userPrefsStorage);

        logic = new InternshipLogicManager(model, storage);

        // Triggers the saveInternshipBook method by executing a create command
        String createCommand = CreateCommand.COMMAND_WORD + COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET
                + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET
                + START_DATE_DESC_JANESTREET + DURATION_DESC_JANESTREET;
        Internship expectedInternship = new InternshipBuilder(JANESTREET).withRequirements().build();
        InternshipModelManager expectedModel = new InternshipModelManager();
        expectedModel.createInternship(expectedInternship);
        assertCommandFailure(createCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
