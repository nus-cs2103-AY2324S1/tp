package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.schedule.Schedule;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Schedule> PREDICATE_SHOW_ALL_SCHEDULES = unused -> true;

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

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a schedule with the same fields as {@code schedule} exists in the address book.
     */
    boolean hasSchedule(Schedule schedule);

    /**
     * Deletes the given schedule.
     * The schedule must exist in the address book.
     */
    void deleteSchedule(Schedule target);

    /**
     * Deletes the given list of schedules.
     * The schedules must exist in the address book.
     * @targets the list of schedules to delete.
     */
    void deleteSchedules(ObservableList<Schedule> targets);

    /**
     * Adds the given schedule.
     * {@code schedule} must not already exist in the address book.
     */
    void addSchedule(Schedule schedule);

    /**
     * Replaces the given schedule {@code target} with {@code editedSchedule}.
     * {@code target} must exist in the address book.
     * The schedule fields of {@code editedSchedule} must not be the same as another existing schedule in the address
     * book.
     */
    void setSchedule(Schedule target, Schedule editedSchedule);

    /** Returns an unmodifiable view of the filtered schedule list */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered schedule list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Schedule> predicate);

    /**
     * Returns the list of schedules associated with given tutor.
     * @param tutor the given tutor to find associated schedules.
     * @throws PersonNotFoundException if {@code tutor} is null.
     */
    ObservableList<Schedule> getSchedulesFromTutor(Person tutor);
}
