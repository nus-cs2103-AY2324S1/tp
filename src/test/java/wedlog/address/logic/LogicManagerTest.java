package wedlog.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wedlog.address.logic.Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX;
import static wedlog.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static wedlog.address.logic.commands.CommandTestUtil.ADDRESS_DESC_GIA;
import static wedlog.address.logic.commands.CommandTestUtil.DIETARY_DESC_GIA;
import static wedlog.address.logic.commands.CommandTestUtil.EMAIL_DESC_GIA;
import static wedlog.address.logic.commands.CommandTestUtil.NAME_DESC_GIA;
import static wedlog.address.logic.commands.CommandTestUtil.PHONE_DESC_GIA;
import static wedlog.address.logic.commands.CommandTestUtil.RSVP_DESC_GIA;
import static wedlog.address.logic.commands.CommandTestUtil.TABLE_DESC_GIA;
import static wedlog.address.testutil.Assert.assertThrows;
import static wedlog.address.testutil.TypicalGuests.GABRIEL;
import static wedlog.address.testutil.TypicalGuests.GEORGE;
import static wedlog.address.testutil.TypicalGuests.GIA;
import static wedlog.address.testutil.TypicalGuests.GINA;
import static wedlog.address.testutil.TypicalGuests.GREG;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import wedlog.address.commons.core.GuiSettings;
import wedlog.address.logic.commands.CommandResult;
import wedlog.address.logic.commands.GuestAddCommand;
import wedlog.address.logic.commands.GuestListCommand;
import wedlog.address.logic.commands.exceptions.CommandException;
import wedlog.address.logic.parser.exceptions.ParseException;
import wedlog.address.model.DietaryRequirementStatistics;
import wedlog.address.model.Model;
import wedlog.address.model.ModelManager;
import wedlog.address.model.ReadOnlyAddressBook;
import wedlog.address.model.RsvpStatistics;
import wedlog.address.model.UserPrefs;
import wedlog.address.model.person.Guest;
import wedlog.address.storage.JsonAddressBookStorage;
import wedlog.address.storage.JsonUserPrefsStorage;
import wedlog.address.storage.StorageManager;
import wedlog.address.testutil.GuestBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String guestDeleteCommand = "guest delete 9";
        assertCommandException(guestDeleteCommand, MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String guestListCommand = "guest " + GuestListCommand.COMMAND_WORD;
        assertCommandSuccess(guestListCommand, GuestListCommand.MESSAGE_SUCCESS, model);
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
    public void getFilteredGuestList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredGuestList().remove(0));
    }

    @Test
    public void getFilteredVendorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredVendorList().remove(0));
    }

    @Test
    public void getRsvpStatisticsTest() {
        model.addGuest(GINA);
        model.addGuest(GREG);
        assertEquals(new RsvpStatistics(1, 1, 0), logic.getRsvpStatistics());
    }

    @Test
    public void getDietaryRequirementStatisticsTest() {
        HashMap<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("vegan", 1);
        expectedMap.put("no beef", 1);
        model.addGuest(GINA);
        model.addGuest(GREG); // rsvp no
        model.addGuest(GABRIEL); // rsvp unknown
        model.addGuest(GEORGE);
        assertEquals(new DietaryRequirementStatistics(expectedMap), logic.getDietaryRequirementStatistics());
    }

    @Test
    public void setGuiSettings() {
        // EPs: [null][non-null]

        // EP1: null
        assertThrows(NullPointerException.class, () -> logic.setGuiSettings(null));

        // EP2: non-null
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        logic.setGuiSettings(guiSettings);
        assertEquals(guiSettings, logic.getGuiSettings());
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
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
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
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

        // Triggers the saveAddressBook method by executing a guest add command
        String guestAddCommand = "guest " + GuestAddCommand.COMMAND_WORD + NAME_DESC_GIA + PHONE_DESC_GIA
                + EMAIL_DESC_GIA + ADDRESS_DESC_GIA + RSVP_DESC_GIA + DIETARY_DESC_GIA + TABLE_DESC_GIA;
        Guest expectedGuest = new GuestBuilder(GIA).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addGuest(expectedGuest);
        expectedModel.commitAddressBook();
        assertCommandFailure(guestAddCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
