package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.EventBuilder.DEFAULT_START_TIME_STRING;
import static seedu.address.testutil.TypicalEvents.CONFERENCE;
import static seedu.address.testutil.TypicalEvents.TEST_EVENT_A;
import static seedu.address.testutil.TypicalEvents.TEST_EVENT_B;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.calendar.UniMateCalendar;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.task.TaskManager;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.CalendarBuilder;
import seedu.address.testutil.EventPeriodBuilder;
import seedu.address.testutil.TaskManagerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void setCalendarFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setCalendarFilePath(null));
    }

    @Test
    public void setCalendarFilePath_validPath_setsCalendarFilePath() {
        Path path = Paths.get("calendar/file/path");
        modelManager.setCalendarFilePath(path);
        assertEquals(path, modelManager.getCalendarFilePath());
    }

    @Test
    public void setTaskManagerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskManagerFilePath(null));
    }

    @Test
    public void setTaskManagerFilePath_validPath_setsTaskManagerFilePath() {
        Path path = Paths.get("task/manager/file/path");
        modelManager.setTaskManagerFilePath(path);
        assertEquals(path, modelManager.getTaskManagerFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void addEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addEvent(null));
    }

    @Test
    public void deleteEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteEventAt(null));
    }

    @Test
    public void findEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.findEventAt(null));
    }

    @Test
    public void findEvent_throwsEventNotFoundException() {
        LocalDateTime eventTime = LocalDateTime.parse(DEFAULT_START_TIME_STRING, DATE_TIME_STRING_FORMATTER);
        assertThrows(EventNotFoundException.class, () -> modelManager.findEventAt(eventTime));
    }

    @Test
    public void eventsInRange_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.eventsInRange(null));
    }

    @Test
    public void eventsInRange_returnsSuccessful() {
        modelManager.addEvent(CONFERENCE);
        EventPeriodBuilder builder = new EventPeriodBuilder();
        builder.changeStartAndEnd("2023-11-15 08:30", "2023-11-15 17:00");
        assertEquals(List.of(CONFERENCE), modelManager.eventsInRange(builder.build()));
    }

    @Test
    public void deleteEventsInRange_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteEventsInRange(null));
    }

    @Test
    public void deleteEventsInRange_successful() {
        EventPeriodBuilder builder = new EventPeriodBuilder();
        builder.changeStartAndEnd("2023-11-15 08:30", "2023-11-15 17:00");
        modelManager.deleteEventsInRange(builder.build());
        LocalDateTime localDateTime = LocalDateTime.parse("2023-11-15 08:30", DATE_TIME_STRING_FORMATTER);
        assertThrows(EventNotFoundException.class, () -> modelManager.findEventAt(localDateTime));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UniMateCalendar calendar = new CalendarBuilder().withEvent(TEST_EVENT_A).withEvent(TEST_EVENT_B).build();
        UniMateCalendar differentCalendar = new UniMateCalendar();
        TaskManager taskManager = new TaskManagerBuilder().withTask(ASSIGNMENT).build();
        TaskManager differentTaskManager = new TaskManager();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, calendar, taskManager, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, calendar, taskManager, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook, same calendar, same taskManager -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, calendar, taskManager, userPrefs)));

        // same addressBook, different calendar, same taskManager -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentCalendar, taskManager, userPrefs)));

        // same addressBook, same calendar, different taskManager -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook, calendar, differentTaskManager, userPrefs)));

        // different addressBook, different calendar, different taskManager -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, differentCalendar,
                differentTaskManager, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, calendar, taskManager, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, calendar, taskManager, differentUserPrefs)));
    }
}
