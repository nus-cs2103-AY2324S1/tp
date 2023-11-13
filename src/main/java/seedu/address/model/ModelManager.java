package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.calendar.UniMateCalendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.task.ReadOnlyTaskManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UniMateCalendar calendar;
    private final TaskManager taskManager;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private ReadOnlyCalendar comparisonCalendar;

    /**
     * Initializes a ModelManager with the given addressBook, calendar and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyCalendar calendar, ReadOnlyTaskManager taskManager,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.calendar = new UniMateCalendar(calendar);
        this.taskManager = new TaskManager(taskManager);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        comparisonCalendar = new UniMateCalendar();
    }

    public ModelManager() {
        this(new AddressBook(), new UniMateCalendar(), new TaskManager(), new UserPrefs());
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

    @Override
    public Path getCalendarFilePath() {
        return userPrefs.getCalendarFilePath();
    }

    @Override
    public void setCalendarFilePath(Path calendarFilePath) {
        requireNonNull(calendarFilePath);
        userPrefs.setCalendarFilePath(calendarFilePath);
    }

    @Override
    public Path getTaskManagerFilePath() {
        return userPrefs.getTaskManagerFilePath();
    }

    @Override
    public void setTaskManagerFilePath(Path taskManagerFilePath) {
        requireNonNull(taskManagerFilePath);
        userPrefs.setTaskManagerFilePath(taskManagerFilePath);
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

    //=========== Sorted Person List Accessors ===============================================================
    @Override
    public void sortPersonList(Comparator<Person> personComparator) {
        addressBook.sortPersons(personComparator);
    }

    //=========== Calendar ===================================================================================

    @Override
    public void setCalendar(ReadOnlyCalendar calendar) {
        this.calendar.resetData(calendar);
    }

    @Override
    public ReadOnlyCalendar getCalendar() {
        return calendar;
    }
    @Override
    public ObservableList<Event> getEventList() {
        return calendar.getEventList();
    }

    @Override
    public ObservableList<Event> getCurrentWeekEventList() {
        return calendar.getCurrentWeekEventList();
    }

    @Override
    public boolean canAddEvent(Event event) {
        return calendar.canAddEvent(event);
    }

    @Override
    public void addEvent(Event event) {
        requireNonNull(event);

        calendar.addEvent(event);
    }

    @Override
    public void deleteEventAt(LocalDateTime dateTime) throws EventNotFoundException {
        requireNonNull(dateTime);

        calendar.deleteEventAt(dateTime);
    }

    @Override
    public Event findEventAt(LocalDateTime dateTime) throws EventNotFoundException {
        requireNonNull(dateTime);

        try {
            return calendar.findEventAt(dateTime).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EventNotFoundException();
        }
    }

    @Override
    public List<Event> eventsInRange(EventPeriod range) {
        requireNonNull(range);

        return calendar.getEventsInRange(range);
    }

    @Override
    public void deleteEventsInRange(EventPeriod range) {
        requireNonNull(range);

        calendar.deleteEventsInRange(range);
    }

    @Override
    public ReadOnlyCalendar getComparisonCalendar() {
        return comparisonCalendar;
    }

    @Override
    public void setComparisonCalendar(ReadOnlyCalendar comparisonCalendar) {
        requireNonNull(comparisonCalendar);

        this.comparisonCalendar = comparisonCalendar;
    }

    //=========== TaskManager ================================================================================

    @Override
    public TaskManager getTaskManager() {
        return taskManager;
    }

    @Override
    public void addTask(Task task) {
        requireNonNull(task);

        taskManager.addTask(task);
    }

    @Override
    public Task deleteTask(int index) {
        return taskManager.deleteTask(index);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return taskManager.getTaskList();
    }

    @Override
    public void sortTasksBy(String comparatorType) {
        taskManager.sortTasksBy(comparatorType);
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
                && calendar.equals(otherModelManager.calendar)
                && taskManager.equals(otherModelManager.taskManager)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }
}
