package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEHANDLE_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
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
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
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
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
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
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TELEHANDLE_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    /**
     * Test method for {@link LogicManager#getFilteredCourseList()} when there are no courses.
     * This method ensures that the observable course list is empty when no courses are associated with persons.
     */
    @Test
    public void getFilteredCourseList_noCourses() {
        Person person = new PersonBuilder().withCourses().build();
        model.addPerson(person);

        ObservableList<Pair<String, Integer>> courseList = logic.getFilteredCourseList();
        assertEquals(0, courseList.size());
    }

    /**
     * Test method for {@link LogicManager#getFilteredCourseList()} when there are courses
     * associated with persons.
     * This method ensures that the observable course list contains the correct course counts when
     * there are courses associated with persons.
     */
    @Test
    public void getFilteredCourseList_withCourses() {
        Person person1 = new PersonBuilder(AMY).withCourses("MA1521", "CS1101S").build();
        Person person2 = new PersonBuilder(BENSON).withCourses("CS1101S", "IS1128").build();
        model.addPerson(person1);
        model.addPerson(person2);

        ObservableList<Pair<String, Integer>> courseList = logic.getFilteredCourseList();
        assertEquals(3, courseList.size());

        Pair<String, Integer> ma1521Pair = new Pair<>("MA1521", 1);
        Pair<String, Integer> cs1101sPair = new Pair<>("CS1101S", 2);
        Pair<String, Integer> is1128Pair = new Pair<>("IS1128", 1);

        assertTrue(courseList.contains(ma1521Pair));
        assertTrue(courseList.contains(cs1101sPair));
        assertTrue(courseList.contains(is1128Pair));
    }

    /**
     * Test method for {@link LogicManager#getFilteredTagList()} when there are no tags.
     * This method ensures that the observable tag list contains only "0" counts for each
     * predefined tag when no tags are associated with persons.
     */
    @Test
    public void getFilteredTagList_noTags() {
        Person person = new PersonBuilder().withTags().build();
        model.addPerson(person);

        ObservableList<Pair<String, Integer>> tagList = logic.getFilteredTagList();
        assertEquals(3, tagList.size());

        Pair<String, Integer> friendPair = new Pair<>("Friend", 0);
        Pair<String, Integer> closeFriendPair = new Pair<>("Close Friend", 0);
        Pair<String, Integer> emergencyPair = new Pair<>("Emergency", 0);

        assertTrue(tagList.contains(friendPair));
        assertTrue(tagList.contains(closeFriendPair));
        assertTrue(tagList.contains(emergencyPair));
    }

    /**
     * Test method for {@link LogicManager#getFilteredTagList()} when there are tags associated
     * with persons.
     * This method ensures that the observable tag list contains the correct tag counts when
     * there are tags associated with persons.
     */
    @Test
    public void getFilteredTagList_withTags() {
        Person person1 = new PersonBuilder(AMY).withTags("Friend").build();
        Person person2 = new PersonBuilder(BENSON).withTags("Friend", "Emergency").build();
        model.addPerson(person1);
        model.addPerson(person2);

        ObservableList<Pair<String, Integer>> tagList = logic.getFilteredTagList();
        assertEquals(3, tagList.size());

        Pair<String, Integer> friendPair = new Pair<>("Friend", 2);
        Pair<String, Integer> closeFriendPair = new Pair<>("Close Friend", 0);
        Pair<String, Integer> emergencyPair = new Pair<>("Emergency", 1);

        assertTrue(tagList.contains(friendPair));
        assertTrue(tagList.contains(closeFriendPair));
        assertTrue(tagList.contains(emergencyPair));
    }

}
