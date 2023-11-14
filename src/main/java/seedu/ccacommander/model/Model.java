package seedu.ccacommander.model;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.ccacommander.commons.core.GuiSettings;
import seedu.ccacommander.model.enrolment.Enrolment;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;
import seedu.ccacommander.model.shared.Name;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Member> PREDICATE_SHOW_ALL_MEMBERS = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;
    Predicate<Enrolment> PREDICATE_SHOW_ALL_ENROLMENTS = unused -> true;

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
     * Returns the user prefs' CcaCommander file path.
     */
    Path getCcaCommanderFilePath();

    /**
     * Sets the user prefs' CcaCommander file path.
     */
    void setCcaCommanderFilePath(Path ccaCommanderFilePath);

    /**
     * Replaces CcaCommander data with the data in {@code ccaCommander}.
     */
    void setCcaCommander(ReadOnlyCcaCommander ccaCommander);

    /** Returns the CcaCommander */
    ReadOnlyCcaCommander getCcaCommander();

    /**
     * Returns true if a member with the same identity as {@code member} exists in CcaCommander.
     */
    boolean hasMember(Member member);

    /**
     * Deletes the given member.
     * The member must exist in CcaCommander.
     */
    void deleteMember(Member target);

    /**
     * Creates the given member.
     * {@code member} must not already exist in CcaCommander.
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

    /**
     * Deletes the given event.
     * The event must exist in CcaCommander.
     */
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

    /**
     * Returns true if an enrolment with the same identity as {@code enrolment} exists in CcaCommander.
     */
    boolean hasEnrolment(Enrolment enrolment);

    /**
     * Deletes the given enrolment.
     * The enrolment must exist in CcaCommander.
     */
    void deleteEnrolment(Enrolment target);

    /**
     * Deletes all enrolments with matching eventName.
     */
    void deleteEnrolmentsWithEventName(Name eventName);

    /**
     * Deletes all enrolments with matching memberName
     */
    void deleteEnrolmentsWithMemberName(Name memberName);

    /**
     * Creates the given event.
     * {@code enrolment} must not already exist in CcaCommander.
     */
    void createEnrolment(Enrolment enrolment);

    /**
     *  Edit the Enrolments from the enrolment list which
     *  contain an event that have the same name as {@param prevName}
     *  to match the {@param newName} of the event
     * @param prevName
     * @param newName
     */
    void editEnrolmentsWithEventName(Name prevName, Name newName);

    /**
     *  Edit the Enrolments from the enrolment list which
     *  contain a Member that have the same name as {@param prevName}
     *  to match the {@param newName} of the member
     * @param prevName
     * @param newName
     */
    void editEnrolmentsWithMemberName(Name prevName, Name newName);


    /**
     * Replaces the given member {@code target} with {@code editedEnrolment}.
     * {@code target} must exist in CcaCommander.
     */
    void setEnrolment(Enrolment target, Enrolment editedEnrolment);

    /** Returns an unmodifiable view of the filtered member list */
    ObservableList<Member> getFilteredMemberList();

    ObservableList<Event> getFilteredEventList();

    ObservableList<Enrolment> getFilteredEnrolmentList();
    Predicate<Member> getLastFilteredMemberPredicate();
    Predicate<Event> getLastFilteredEventPredicate();
    Predicate<Member> getUnenrolMemberPredicate(Name memberName);
    Predicate<Event> getUnenrolEventPredicate(Name eventName);

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

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEnrolmentList(Predicate<Enrolment> predicate);

    /**
     * Updates the members (enrolled in Event with {@param eventName})
     * with the hour and remark fields after a viewEventCommand is executed
     * @param eventName
     */
    Collection<Name> updateMemberHoursAndRemark(Name eventName);

    /**
     * Updates the events (that Member {@param memberName} is enrolled in)
     * with the hour and remark fields after a viewMemberCommand is executed
     * @param memberName
     */
    Collection<Name> updateEventHoursAndRemark(Name memberName);

    void commit(String commitMessage);

    /**
     * Returns true if there is a {@code Command} that can be undone.
     */
    boolean canUndo();

    /**
     * Undoes the most recent undoable {@code Command}
     */
    String undo();

    /**
     * Returns true if there is a {@code Command} that can be redone.
     */
    boolean canRedo();

    /**
     * Redoes the most recent redoable {@code Command}
     */
    String redo();

    /**
     * Returns a summary of all commands currently captured by this {@code Model}.
     */
    VersionCaptures viewVersionCaptures();
}
