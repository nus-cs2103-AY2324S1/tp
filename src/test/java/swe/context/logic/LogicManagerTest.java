package swe.context.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static swe.context.testutil.Assert.assertThrows;
import static swe.context.testutil.TestData.Valid.EMAIL_DESC_AMY;
import static swe.context.testutil.TestData.Valid.NAME_DESC_AMY;
import static swe.context.testutil.TestData.Valid.NOTE_DESC_AMY;
import static swe.context.testutil.TestData.Valid.PHONE_DESC_AMY;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import swe.context.logic.commands.AddCommand;
import swe.context.logic.commands.CommandResult;
import swe.context.logic.commands.ListCommand;
import swe.context.logic.commands.exceptions.CommandException;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.ReadOnlyContacts;
import swe.context.model.Settings;
import swe.context.model.contact.Contact;
import swe.context.storage.JsonContactsStorage;
import swe.context.storage.JsonSettingsStorage;
import swe.context.storage.StorageManager;
import swe.context.testutil.ContactBuilder;
import swe.context.testutil.TestData;



public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonContactsStorage contactsStorage =
                new JsonContactsStorage(temporaryFolder.resolve("contacts.json"));
        JsonSettingsStorage settingsStorage = new JsonSettingsStorage(temporaryFolder.resolve("settings.json"));
        StorageManager storage = new StorageManager(contactsStorage, settingsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, Messages.COMMAND_UNKNOWN);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, Messages.INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, Messages.LIST_COMMAND_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                Messages.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                Messages.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredContactList().remove(0));
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
        Model expectedModel = new ModelManager(model.getContacts(), new Settings());
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
        // Inject LogicManager with an contactsStorage that throws the IOException e when saving
        JsonContactsStorage contactsStorage = new JsonContactsStorage(
            temporaryFolder.resolve("contacts.json")
        ) {
            @Override
            public void saveContacts(ReadOnlyContacts contactList) throws IOException {
                throw e;
            }
        };

        JsonSettingsStorage settingsStorage = new JsonSettingsStorage(
            temporaryFolder.resolve("settings.json")
        );
        StorageManager storage = new StorageManager(contactsStorage, settingsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveContacts method by executing an add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + NOTE_DESC_AMY;
        Contact expectedContact = new ContactBuilder(TestData.Valid.Contact.AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addContact(expectedContact);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
