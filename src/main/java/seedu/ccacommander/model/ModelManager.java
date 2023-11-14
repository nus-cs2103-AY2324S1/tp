package seedu.ccacommander.model;

import static java.util.Objects.requireNonNull;
import static seedu.ccacommander.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.ccacommander.commons.core.GuiSettings;
import seedu.ccacommander.commons.core.LogsCenter;
import seedu.ccacommander.model.enrolment.Enrolment;
import seedu.ccacommander.model.enrolment.exceptions.EnrolmentNotFoundException;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.event.EventInNameCollectionPredicate;
import seedu.ccacommander.model.event.SameEventPredicate;
import seedu.ccacommander.model.member.Member;
import seedu.ccacommander.model.member.MemberInNameCollectionPredicate;
import seedu.ccacommander.model.member.SameMemberPredicate;
import seedu.ccacommander.model.shared.Name;

/**
 * Represents the in-memory model of the CcaCommander data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final VersionedCcaCommander versionedCcaCommander;
    private final UserPrefs userPrefs;
    private final FilteredList<Member> filteredMembers;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Enrolment> filteredEnrolments;

    private Predicate<Event> lastEventPredicate;
    private Predicate<Member> lastMemberPredicate;
    /**
     * Initializes a ModelManager with the given CcaCommander and userPrefs.
     */
    public ModelManager(ReadOnlyCcaCommander ccaCommander, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(ccaCommander, userPrefs);

        logger.fine("Initializing with CCACommander: " + ccaCommander + " and user prefs " + userPrefs);

        this.versionedCcaCommander = new VersionedCcaCommander(ccaCommander);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMembers = new FilteredList<>(this.versionedCcaCommander.getMemberList());
        filteredEvents = new FilteredList<>(this.versionedCcaCommander.getEventList());
        filteredEnrolments = new FilteredList<>(this.versionedCcaCommander.getEnrolmentList());
        lastMemberPredicate = PREDICATE_SHOW_ALL_MEMBERS;
        lastEventPredicate = PREDICATE_SHOW_ALL_EVENTS;
    }

    public ModelManager() {
        this(new CcaCommander(), new UserPrefs());
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
    public Path getCcaCommanderFilePath() {
        return userPrefs.getCcaCommanderFilePath();
    }

    @Override
    public void setCcaCommanderFilePath(Path ccaCommanderFilePath) {
        requireNonNull(ccaCommanderFilePath);
        userPrefs.setCcaCommanderFilePath(ccaCommanderFilePath);
    }

    //=========== CcaCommander ================================================================================

    @Override
    public void setCcaCommander(ReadOnlyCcaCommander ccaCommander) {
        versionedCcaCommander.resetData(ccaCommander);
    }

    @Override
    public ReadOnlyCcaCommander getCcaCommander() {
        return versionedCcaCommander;
    }

    @Override
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return versionedCcaCommander.hasMember(member);
    }

    @Override
    public void deleteMember(Member target) {
        versionedCcaCommander.removeMember(target);
    }

    @Override
    public void createMember(Member member) {
        versionedCcaCommander.createMember(member);
        updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
    }

    @Override
    public void setMember(Member target, Member editedMember) {
        requireAllNonNull(target, editedMember);

        versionedCcaCommander.setMember(target, editedMember);
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return versionedCcaCommander.hasEvent(event);
    }
    @Override
    public void deleteEvent(Event target) {
        versionedCcaCommander.removeEvent(target);
    }

    @Override
    public void createEvent(Event event) {
        versionedCcaCommander.createEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        versionedCcaCommander.setEvent(target, editedEvent);
    }

    @Override
    public boolean hasEnrolment(Enrolment enrolment) {
        requireNonNull(enrolment);
        return versionedCcaCommander.hasEnrolment(enrolment);
    }

    @Override
    public void deleteEnrolment(Enrolment target) {
        versionedCcaCommander.removeEnrolment(target);
    }

    @Override
    public void deleteEnrolmentsWithEventName(Name eventName) {
        List<Enrolment> enrolmentsToBeDeletedList = new ArrayList<>();
        for (Enrolment enrolment: this.filteredEnrolments) {
            if (enrolment.getEventName().equals(eventName)) {
                // The enrolments cannot directly be deleted as it will affect the iteration of enrolments here.
                enrolmentsToBeDeletedList.add(enrolment);
            }
        }

        for (Enrolment enrolment: enrolmentsToBeDeletedList) {
            this.deleteEnrolment(enrolment);
        }
    }

    @Override
    public void deleteEnrolmentsWithMemberName(Name memberName) {
        List<Enrolment> enrolmentsToBeDeletedList = new ArrayList<>();
        for (Enrolment enrolment: this.filteredEnrolments) {
            if (enrolment.getMemberName().equals(memberName)) {
                // The enrolments cannot directly be deleted as it will affect the iteration of enrolments here.
                enrolmentsToBeDeletedList.add(enrolment);
            }
        }

        for (Enrolment enrolment: enrolmentsToBeDeletedList) {
            this.deleteEnrolment(enrolment);
        }
    }
    @Override
    public void createEnrolment(Enrolment enrolment) {
        versionedCcaCommander.createEnrolment(enrolment);
        updateFilteredEnrolmentList(PREDICATE_SHOW_ALL_ENROLMENTS);
    }

    /**
     *  Checks if the {@code lastShownEnrolmentList} contains a specific Enrolment object that has a Member and an
     *  Event that matches the given {@param memberName} and {@param eventName} respectively, and returns that
     *  specific Enrolment.
     * @param lastShownEnrolmentList
     * @param memberName
     * @param eventName
     * @throws EnrolmentNotFoundException if the enrolment cannot be found from the {@code lastShownEnrolmentList}
     */
    public static Enrolment findEnrolmentFromList(List<Enrolment> lastShownEnrolmentList,
                                                  Name memberName, Name eventName) throws EnrolmentNotFoundException {
        Enrolment selectedEnrolment = null;
        int enrolmentListPointer = 0;

        for (int i = 0; i < lastShownEnrolmentList.size(); i++) {
            selectedEnrolment = lastShownEnrolmentList.get(i);
            Name selectedMemberName = selectedEnrolment.getMemberName();
            Name selectedEventName = selectedEnrolment.getEventName();

            if (memberName.equals(selectedMemberName) && eventName.equals(selectedEventName)) {
                break;
            }
            enrolmentListPointer++;
        }

        if (enrolmentListPointer == lastShownEnrolmentList.size()) {
            throw new EnrolmentNotFoundException();
        } else {
            return selectedEnrolment;
        }
    }

    /**
     *  Edit the Enrolments from the enrolment list which
     *  contain an event that have the same name as {@param prevName}
     *  to match the {@param newName} of the event
     * @param prevName
     * @param newName
     */
    @Override
    public void editEnrolmentsWithEventName(Name prevName, Name newName) {
        updateFilteredEnrolmentList(PREDICATE_SHOW_ALL_ENROLMENTS);
        // get the enrolments list then loop through to edit matching enrolments
        List<Enrolment> enrolmentList = getFilteredEnrolmentList();
        for (Enrolment enrolment: enrolmentList) {
            if (enrolment.getEventName().equals(prevName)) {
                Enrolment editedEnrolment = new Enrolment(enrolment.getMemberName(), newName,
                        Optional.of(enrolment.getHours()), Optional.of(enrolment.getRemark()));
                setEnrolment(enrolment, editedEnrolment);
            }
        }
    }

    /**
     *  Edit the Enrolments from the enrolment list which
     *  contain a Member that have the same name as {@param prevName}
     *  to match the {@param newName} of the member
     * @param prevName
     * @param newName
     */
    @Override
    public void editEnrolmentsWithMemberName(Name prevName, Name newName) {
        updateFilteredEnrolmentList(PREDICATE_SHOW_ALL_ENROLMENTS);
        // get the enrolments list then loop through to edit matching enrolments
        List<Enrolment> enrolmentList = getFilteredEnrolmentList();
        for (Enrolment enrolment: enrolmentList) {
            if (enrolment.getMemberName().equals(prevName)) {
                Enrolment editedEnrolment = new Enrolment(newName, enrolment.getEventName(),
                        Optional.of(enrolment.getHours()), Optional.of(enrolment.getRemark()));
                setEnrolment(enrolment, editedEnrolment);
            }
        }
    }

    @Override
    public void setEnrolment(Enrolment target, Enrolment editedEnrolment) {
        requireAllNonNull(target, editedEnrolment);

        versionedCcaCommander.setEnrolment(target, editedEnrolment);
    }


    @Override
    public void commit(String commitMessage) {
        versionedCcaCommander.commit(commitMessage);
    }

    @Override
    public boolean canUndo() {
        return versionedCcaCommander.canUndo();
    }

    @Override
    public String undo() {
        return versionedCcaCommander.undo();
    }

    @Override
    public boolean canRedo() {
        return versionedCcaCommander.canRedo();
    }

    @Override
    public String redo() {
        return versionedCcaCommander.redo();
    }

    @Override
    public VersionCaptures viewVersionCaptures() {
        return versionedCcaCommander.viewVersionCaptures();
    }

    //=========== Filtered Member List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Member} backed by the internal list of
     * {@code versionedCcaCommander}
     */
    @Override
    public ObservableList<Member> getFilteredMemberList() {
        return filteredMembers;
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public ObservableList<Enrolment> getFilteredEnrolmentList() {
        return filteredEnrolments;
    }

    @Override
    public Predicate<Member> getLastFilteredMemberPredicate() {
        return this.lastMemberPredicate;
    }
    @Override
    public Predicate<Event> getLastFilteredEventPredicate() {
        return this.lastEventPredicate;
    }

    @Override
    public Predicate<Member> getUnenrolMemberPredicate(Name eventName) {
        if (lastEventPredicate instanceof SameEventPredicate) {
            Collection<Name> memberNamesCollection = this.updateMemberHoursAndRemark(eventName);
            return new MemberInNameCollectionPredicate(memberNamesCollection);
        } else {
            return lastMemberPredicate;
        }
    }
    @Override
    public Predicate<Event> getUnenrolEventPredicate(Name memberName) {
        if (lastMemberPredicate instanceof SameMemberPredicate) {
            Collection<Name> eventNamesCollection = this.updateEventHoursAndRemark(memberName);
            return new EventInNameCollectionPredicate(eventNamesCollection);
        } else {
            return lastEventPredicate;
        }
    }
    @Override
    public void updateFilteredMemberList(Predicate<Member> predicate) {
        requireNonNull(predicate);
        filteredMembers.setPredicate(predicate);
        this.lastMemberPredicate = predicate;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
        this.lastEventPredicate = predicate;
    }

    @Override
    public void updateFilteredEnrolmentList(Predicate<Enrolment> predicate) {
        requireNonNull(predicate);
        filteredEnrolments.setPredicate(predicate);
    }

    @Override
    public Collection<Name> updateMemberHoursAndRemark(Name eventName) {
        List<Member> lastShownMemberList = getFilteredMemberList();

        updateFilteredEnrolmentList(PREDICATE_SHOW_ALL_ENROLMENTS);
        List<Enrolment> enrolmentList = getFilteredEnrolmentList();
        Collection<Name> memberNameCollection = new HashSet<>();
        for (Enrolment enrolment: enrolmentList) {
            if (enrolment.getEventName().equals(eventName)) {
                Name memName = enrolment.getMemberName();
                memberNameCollection.add(memName);
                for (Member member: lastShownMemberList) {
                    if (member.getName().equals(memName)) {
                        member.setHours(Optional.of(enrolment.getHours()));
                        member.setRemark(Optional.of(enrolment.getRemark()));
                    }
                }
            }
        }
        return memberNameCollection;
    }
    @Override
    public Collection<Name> updateEventHoursAndRemark(Name memberName) {
        // View all events of member
        // loop through enrolment list, check if each enrolment.getMemberName() = member.getName()
        // then add enrolment.event.getName() to
        // Collection<Name> eventNames
        List<Event> lastShownEventList = getFilteredEventList();

        updateFilteredEnrolmentList(PREDICATE_SHOW_ALL_ENROLMENTS);
        List<Enrolment> enrolmentList = getFilteredEnrolmentList();
        Collection<Name> eventNameCollection = new HashSet<>();
        for (Enrolment enrolment: enrolmentList) {
            if (enrolment.getMemberName().equals(memberName)) {
                Name eventName = enrolment.getEventName();
                eventNameCollection.add(eventName);
                for (Event event: lastShownEventList) {
                    if (event.getName().equals(eventName)) {
                        event.setHours(Optional.of(enrolment.getHours()));
                        event.setRemark(Optional.of(enrolment.getRemark()));
                    }
                }
            }
        }
        return eventNameCollection;
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
        return versionedCcaCommander.equals(otherModelManager.versionedCcaCommander)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredMembers.equals(otherModelManager.filteredMembers)
                && filteredEvents.equals(otherModelManager.filteredEvents)
                && filteredEnrolments.equals(otherModelManager
                .filteredEnrolments);
    }
}
