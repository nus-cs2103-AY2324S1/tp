package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_BOB_SECOND_JAN;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.schedule.Date;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleIsOnDatePredicate;
import seedu.address.model.schedule.Status;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ScheduleBuilder;

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
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4, "/view/DarkTheme.css"));
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
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4, "/view/DarkTheme.css");
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
    public void deletePerson_personWithSchedulesDeleted_success() {
        modelManager.addPerson(ALICE);
        modelManager.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertTrue(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
        modelManager.deletePerson(ALICE);
        assertFalse(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void hasSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSchedule(null));
    }

    @Test
    public void hasSchedule_scheduleNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void hasSchedule_scheduleInAddressBook_returnsTrue() {
        modelManager.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertTrue(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void setSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSchedule(null, null));
    }

    @Test
    public void setSchedule_success() {
        modelManager.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        modelManager.setSchedule(SCHEDULE_ALICE_FIRST_JAN, SCHEDULE_BOB_SECOND_JAN);
        assertTrue(modelManager.hasSchedule(SCHEDULE_BOB_SECOND_JAN));
    }

    @Test
    public void removeSchedule_success() {
        modelManager.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertTrue(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
        modelManager.deleteSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertFalse(modelManager.hasSchedule(SCHEDULE_BOB_SECOND_JAN));
    }

    @Test
    public void removeSchedules_success() {
        modelManager.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        modelManager.addSchedule(SCHEDULE_BOB_SECOND_JAN);
        assertTrue(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
        assertTrue(modelManager.hasSchedule(SCHEDULE_BOB_SECOND_JAN));
        modelManager.deleteSchedules(modelManager.getAddressBook().getScheduleList());
        assertFalse(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
        assertFalse(modelManager.hasSchedule(SCHEDULE_BOB_SECOND_JAN));
    }

    @Test
    public void getFilteredScheduleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredScheduleList().remove(0));
    }

    @Test
    public void getFilteredCalendarScheduleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            modelManager.getFilteredCalendarScheduleList().remove(0));
    }

    @Test
    public void getSchedulesFromTutor_success() {
        modelManager.addPerson(ALICE);
        modelManager.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertTrue(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
        ObservableList<Schedule> foundSchedules = modelManager.getSchedulesFromTutor(ALICE);
        assertTrue(foundSchedules.contains(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void getSchedulesFromTutor_throwsPersonNotFoundException() {
        modelManager.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertTrue(modelManager.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
        assertThrows(PersonNotFoundException.class, () -> modelManager.getSchedulesFromTutor(ALICE));
    }

    @Test
    public void updateTutorSchedules_success() {
        ModelManager model = new ModelManager();
        model.addPerson(ALICE);
        Schedule schedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).withStatus(Status.COMPLETED).build();
        model.addSchedule(schedule);

        Person editedPerson = new PersonBuilder().withName("John Doe").build();
        Schedule editedSchedule = new Schedule(editedPerson, schedule.getStartTime(),
            schedule.getEndTime(), schedule.getStatus());

        model.updateTutorSchedules(ALICE, editedPerson);

        // Check if the schedule is updated correctly
        assertTrue(model.getFilteredScheduleList().contains(editedSchedule));
        assertFalse(model.getFilteredScheduleList().contains(schedule));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder()
                .withPerson(ALICE)
                .withPerson(BOB)
                .withSchedule(SCHEDULE_ALICE_FIRST_JAN)
                .withSchedule(SCHEDULE_BOB_SECOND_JAN)
                .build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredPersonList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));

        // different filtered Schedule List -> returns false
        modelManager.updateFilteredScheduleList(p -> false);
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming test
        modelManager.updateFilteredScheduleList(Model.PREDICATE_SHOW_ALL_SCHEDULES);

        // different filtered Calendar Schedule List -> return false
        modelManager.updateFilteredCalendarScheduleList(
            new ScheduleIsOnDatePredicate(new Date(LocalDate.of(2023, 9, 15))));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
    }
}
