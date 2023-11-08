package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;

    /**
     * Creates a copy of the model.
     */
    Model copy();

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
     * Returns true if a meeting with the same identity as {@code meeting} exists in the address book.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given meeting.
     * The meeting must exist in the address book.
     */
    void deleteMeeting(Meeting target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given meeting.
     * {@code meeting} must not already exist in the address book.
     */
    void addMeeting(Meeting meeting);

    /**
     * Returns the person with the given name.
     * A person with the given name must exist in the address book.
     */
    Person getPerson(String name);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Updates the given attendee {@code targetAttendee} with {@code editedAttendee} for all meetings.
     * @param targetAttendee The Attendee name to be replaced from all meetings.
     * @param editedAttendee The Attendee name to replace the {@code targetAttendee}.
     */
    void updateAttendee(String targetAttendee, String editedAttendee);

    /**
     * Deletes the given attendee {@code targetAttendee} from all meetings.
     * @param targetAttendee The attendee name to be deleted.
     */
    void deleteAttendee(String targetAttendee);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting identity of {@code editedMeeting} must be different than an existing meeting in the address book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered Meeting list */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    boolean hasName(String attendeeName);

    /**
     * Set current viewed person index based on viewc command.
     */
    void setViewedPersonIndex(Index personIndex);

    /**
     * Set current viewed meeting index based on viewm command.
     */
    void setViewedMeetingIndex(Index meetingIndex);

    /**
     * Get current viewed person and meeting as a pair based on both view commands.
     */
    Pair<Person, Meeting> getViewedItems();


}
