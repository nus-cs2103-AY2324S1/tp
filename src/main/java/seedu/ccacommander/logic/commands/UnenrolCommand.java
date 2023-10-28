package seedu.ccacommander.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.commons.util.ToStringBuilder;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.logic.commands.exceptions.CommandException;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.enrolment.Enrolment;
import seedu.ccacommander.model.enrolment.exceptions.EnrolmentNotFoundException;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;
import seedu.ccacommander.model.shared.Name;

/**
 * Deletes an enrolment identified using the displayed indexes of the member and event from CcaCommander.
 */
public class UnenrolCommand extends Command {
    public static final String COMMAND_WORD = "unenrol";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the enrolment identified by the member's index number used in the displayed member list "
            + "and the event's index number used in the displayed event list.\n"
            + "Parameters: MEMBER_INDEX (must be a positive integer), EVENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " m/1" + " e/1";
    public static final String MESSAGE_DELETE_ENROLMENT_SUCCESS = "Deleted Enrolment: %1$s";
    public static final String MESSAGE_COMMIT = "Deleted Enrolment: %1$s.";
    private final Index memberIndex;
    private final Index eventIndex;

    /**
     * Creates an UnenrolCommand to remove the specified {@code Enrolment}
     */
    public UnenrolCommand(Index memberIndex, Index eventIndex) {
        this.memberIndex = memberIndex;
        this.eventIndex = eventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Enrolment> lastShownEnrolmentList = model.getFilteredEnrolmentList();
        List<Member> lastShownMemberList = model.getFilteredMemberList();
        List<Event> lastShownEventList = model.getFilteredEventList();

        int zeroBasedMemberIndex = memberIndex.getZeroBased();
        int zeroBasedEventIndex = eventIndex.getZeroBased();

        if (zeroBasedEventIndex >= lastShownMemberList.size()
                && zeroBasedEventIndex >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_AND_EVENT_DISPLAYED_INDEX);
        }

        if (zeroBasedMemberIndex >= lastShownMemberList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        if (zeroBasedEventIndex >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Member member = lastShownMemberList.get(zeroBasedMemberIndex);
        Event event = lastShownEventList.get(zeroBasedEventIndex);

        Name memberName = member.getName();
        Name eventName = event.getName();

        Enrolment enrolmentToDelete = findEnrolmentFromList(lastShownEnrolmentList, memberName, eventName);
        model.deleteEnrolment(enrolmentToDelete);
        model.commit(String.format(MESSAGE_COMMIT, enrolmentToDelete.getMemberAndEventEnrolment()));
        return new CommandResult(String.format(MESSAGE_DELETE_ENROLMENT_SUCCESS, Messages.format(enrolmentToDelete)));
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

        for (int i = 0; i < lastShownEnrolmentList.size(); i++) {
            selectedEnrolment = lastShownEnrolmentList.get(i);
            Name selectedMemberName = selectedEnrolment.getMemberName();
            Name selectedEventName = selectedEnrolment.getEventName();

            if (memberName.equals(selectedMemberName) && eventName.equals(selectedEventName)) {
                break;
            }
        }

        if (selectedEnrolment == null) {
            throw new EnrolmentNotFoundException();
        } else {
            return selectedEnrolment;
        }
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnenrolCommand)) {
            return false;
        }

        UnenrolCommand otherUnenrolCommand = (UnenrolCommand) other;
        return memberIndex.equals(otherUnenrolCommand.memberIndex)
                && eventIndex.equals(otherUnenrolCommand.eventIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("memberIndex", memberIndex)
                .add("eventIndex", eventIndex)
                .toString();
    }
}
