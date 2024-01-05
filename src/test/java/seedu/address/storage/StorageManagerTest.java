package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalConsultations.getTypicalConsultationListBook;
import static seedu.address.testutil.TypicalGradedTest.getTypicalGradedTestList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.ConsultationListBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyConsultationList;
import seedu.address.model.ReadOnlyGradedTestList;
import seedu.address.model.ReadOnlySessionList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.SessionListBook;
import seedu.address.model.TaskListBook;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonTaskListStorage taskListStorage = new JsonTaskListStorage(getTempFilePath("tasks"));
        JsonSessionListStorage sessionListStorage = new JsonSessionListStorage(getTempFilePath("sessions"));
        JsonConsultationListStorage consultationListStorage = new JsonConsultationListStorage(
                getTempFilePath("consultations"));
        JsonGradedTestListStorage gradedTestListStorage = new JsonGradedTestListStorage(
                getTempFilePath("gradedTests"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage,
                taskListStorage, sessionListStorage, consultationListStorage, gradedTestListStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        AddressBook original = getTypicalAddressBook();
        storageManager.saveAddressBook(original);
        ReadOnlyAddressBook retrieved = storageManager.readAddressBook().get();
        assertEquals(original, new AddressBook(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

    @Test
    public void sessionListReadSave() throws Exception {
        SessionListBook original = getTypicalSessionList();
        storageManager.saveSessionList(original);
        ReadOnlySessionList retrieved = storageManager.readSessionList().get();
        assertEquals(original, new SessionListBook(retrieved));
    }

    @Test
    public void getSessionListFilePath() {
        assertNotNull(storageManager.getSessionListFilePath());
    }

    @Test
    public void getConsultationListFilePath() {
        assertNotNull(storageManager.getConsultationListFilePath());
    }

    @Test
    public void consultationListReadSave() throws Exception {
        ConsultationListBook original = getTypicalConsultationListBook();
        storageManager.saveConsultationList(original);
        ReadOnlyConsultationList retrieved = storageManager.readConsultationList().get();
        assertEquals(original, new ConsultationListBook(retrieved));
    }

    @Test
    public void taskListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        TaskListBook original = getTypicalTaskList();
        storageManager.saveTaskList(original);
        ReadOnlyTaskList retrieved = storageManager.readTaskList().get();
        assertEquals(original, new TaskListBook(retrieved));
    }

    @Test
    public void getTaskListFilePath() {
        assertNotNull(storageManager.getTaskListFilePath());
    }

    @Test
    public void getGradedTaskListFilePath() {
        assertNotNull(storageManager.getGradedTestListFilePath());
    }

    @Test
    public void gradedTestListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        GradedTestListBook original = getTypicalGradedTestList();
        storageManager.saveGradedTestList(original);
        ReadOnlyGradedTestList retrieved = storageManager.readGradedTestList().get();
        // assertTrue(original.equals(new GradedTestListBook(retrieved)));
        assertEquals(original, new GradedTestListBook(retrieved));
    }
}
