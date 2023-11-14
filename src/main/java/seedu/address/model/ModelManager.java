package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.schedule.Schedule;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Schedule> filteredSchedules;
    private final FilteredList<Schedule> filteredCalendarSchedules;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredSchedules = new FilteredList<>(this.addressBook.getScheduleList());
        filteredCalendarSchedules = new FilteredList<>(filteredSchedules);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        ObservableList<Schedule> associatedSchedules = this.getSchedulesFromTutor(target);
        this.deleteSchedules(associatedSchedules);
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return addressBook.hasSchedule(schedule);
    }

    @Override
    public void deleteSchedule(Schedule target) {
        addressBook.removeSchedule(target);
    }

    @Override
    public void deleteSchedules(ObservableList<Schedule> targets) {
        List<Schedule> copyOfTargets = new ArrayList<>(targets);
        for (Schedule target : copyOfTargets) {
            this.deleteSchedule(target);
        }
    }

    @Override
    public void addSchedule(Schedule schedule) {
        addressBook.addSchedule(schedule);
        updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
    }

    @Override
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireAllNonNull(target, editedSchedule);

        addressBook.setSchedule(target, editedSchedule);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Schedule List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Schedule} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return filteredSchedules;
    }

    @Override
    public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
        requireNonNull(predicate);
        filteredSchedules.setPredicate(predicate);
    }

    @Override
    public ObservableList<Schedule> getFilteredCalendarScheduleList() {
        return filteredCalendarSchedules;
    }

    @Override
    public void updateFilteredCalendarScheduleList(Predicate<Schedule> predicate) {
        requireNonNull(predicate);
        filteredCalendarSchedules.setPredicate(predicate);
    }

    @Override
    public ObservableList<Schedule> getSchedulesFromTutor(Person tutor) throws PersonNotFoundException {
        if (!hasPerson(tutor)) {
            throw new PersonNotFoundException();
        }
        ObservableList<Schedule> schedules = this.addressBook.getScheduleList();
        return schedules.stream().filter(schedule -> schedule.getTutor().equals(tutor))
            .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public void updateTutorSchedules(Person oldPerson, Person editedPerson) {
        ObservableList<Schedule> schedules = getSchedulesFromTutor(oldPerson);
        schedules.forEach(schedule -> {
            Schedule newSchedule = new Schedule(
                editedPerson,
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getStatus()
            );
            setSchedule(schedule, newSchedule);
        });
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredSchedules.equals(otherModelManager.filteredSchedules)
                && filteredCalendarSchedules.equals(otherModelManager.filteredCalendarSchedules);
    }

}
