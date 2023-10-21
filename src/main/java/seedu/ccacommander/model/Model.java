package seedu.ccacommander.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.ccacommander.commons.core.GuiSettings;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Member> PREDICATE_SHOW_ALL_MEMBERS = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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
     * Returns the user prefs' ccacommander book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' ccacommander book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces ccacommander book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyCcaCommander addressBook);

    /** Returns the CcaCommander */
    ReadOnlyCcaCommander getAddressBook();

    /**
     * Returns true if a member with the same identity as {@code member} exists in the ccacommander book.
     */
    boolean hasMember(Member member);

    /**
     * Deletes the given member.
     * The member must exist in the ccacommander book.
     */
    void deleteMember(Member target);

    /**
     * Creates the given member.
     * {@code member} must not already exist in the ccacommander book.
     */
    void createMember(Member member);

    /**
     * Replaces the given member {@code target} with {@code editedMember}.
     * {@code target} must exist in CcaCommander.
     * The member identity of {@code editedMember} must not be the same as another existing member in CcaCommander.
     */
    void setMember(Member target, Member editedMember);

    /**
     * Returns true if a event with the same identity as {@code event} exists in CcaCommander.
     */
    boolean hasEvent(Event event);

    void deleteEvent(Event target);

    /**
     * Creates the given event.
     * {@code event} must not already exist in CcaCommander.
     */
    void createEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in CcaCommander.
     * The member identity of {@code editedEvent} must not be the same as another existing event in CcaCommander.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered member list */
    ObservableList<Member> getFilteredMemberList();

    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered member list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMemberList(Predicate<Member> predicate);

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);
}
