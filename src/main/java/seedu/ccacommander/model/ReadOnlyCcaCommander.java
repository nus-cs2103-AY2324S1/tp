package seedu.ccacommander.model;

import javafx.collections.ObservableList;
import seedu.ccacommander.model.enrolment.Enrolment;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;

/**
 * Unmodifiable view of CcaCommander
 */
public interface ReadOnlyCcaCommander {

    /**
     * Returns an unmodifiable view of the members list.
     * This list will not contain any duplicate members.
     */
    ObservableList<Member> getMemberList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

    /**
     * Returns an unmodifiable view of the enrolment list.
     * This list will not contain any duplicate enrolments.
     */
    ObservableList<Enrolment> getEnrolmentList();

}
