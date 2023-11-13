package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the user prefs' calendar file path.
     */
    Path getCalendarFilePath();

    /**
     * Sets the user prefs' calendar file path.
     */
    void setCalendarFilePath(Path calendarFilePath);

    /**
     * Replaces calendar data with the data in {@code calendar}.
     */
    void setCalendar(ReadOnlyCalendar calendar);

    /**
     * Returns the Calendar
     */
    ReadOnlyCalendar getCalendar();

    /**
     * Sets the user prefs' task manager file path.
     */
    void setTaskManagerFilePath(Path taskManagerFilePath);

    /**
     * Returns the user prefs' task manager file path.
     */
    Path getTaskManagerFilePath();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Check if calendar can add event
     *
     * @param event event to be added
     * @return true if possible, false otherwise
     */
    boolean canAddEvent(Event event);

    /**
     * Adds an event into the calendar
     *
     * @param event event to be added
     */
    void addEvent(Event event);

    /**
     * Deletes an event at the specified time.
     *
     * @param dateTime The specified time.
     * @throws EventNotFoundException if there is no event found at the specified time.
     */
    void deleteEventAt(LocalDateTime dateTime) throws EventNotFoundException;

    /**
     * Checks for an event at the specified time.
     *
     * @param dateTime the specified time.
     * @return the Event
     * @throws EventNotFoundException if there is no event at the specified time.
     */
    Event findEventAt(LocalDateTime dateTime) throws EventNotFoundException;

    /**
     * Looks for all events within a time range and returns them in a list.
     *
     * @param range an {@code EventPeriod} representing a time range.
     * @return A list that contains all events within the time range.
     */
    List<Event> eventsInRange(EventPeriod range);

    /**
     * Looks for all events within a time range and deletes them.
     *
     * @param range an {@code EventPeriod} representing a time range.
     */
    void deleteEventsInRange(EventPeriod range);


    /**
     * Get the calendar resulting from the calendar comparison operation.
     */
    ReadOnlyCalendar getComparisonCalendar();

    /**
     * Set the event list after comparison of calendars.
     *
     * @param eventList event list generated from comparison of calendars.
     */
    void setComparisonCalendar(ReadOnlyCalendar eventList);

    /** Returns a view of the event list */
    ObservableList<Event> getEventList();

    /** Returns a view of the task list */
    ObservableList<Task> getTaskList();

    /** Returns a view of the event list for the current week */
    ObservableList<Event> getCurrentWeekEventList();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Adds a task to the TaskManager.
     * @param task the task to be added.
     */
    void addTask(Task task);

    /**
     * Deletes a task from the TaskManager.
     * @param index the displayed index of the task to delete
     * @return the deleted task
     */
    Task deleteTask(int index);

    /**
     * Returns the TaskManager.
     */
    TaskManager getTaskManager();

    /**
     * Sorts the task list by the preset comparator type listed.
     */
    void sortTasksBy(String comparatorType);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Sorts the filtered person list by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortPersonList(Comparator<Person> personComparator);
}

