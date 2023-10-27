package seedu.ccacommander.logic.commands;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.commons.util.CollectionUtil;
import seedu.ccacommander.commons.util.ToStringBuilder;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.logic.commands.exceptions.CommandException;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.attendance.Attendance;
import seedu.ccacommander.model.attendance.EnrolmentExistsPredicate;
import seedu.ccacommander.model.attendance.Hours;
import seedu.ccacommander.model.attendance.Remark;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.event.EventDate;
import seedu.ccacommander.model.event.Location;
import seedu.ccacommander.model.member.Member;
import seedu.ccacommander.model.shared.Name;
import seedu.ccacommander.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_HOURS;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.ccacommander.model.Model.PREDICATE_SHOW_ALL_ATTENDANCES;
import static seedu.ccacommander.model.Model.PREDICATE_SHOW_ALL_EVENTS;

/**
 * Edits the details of an existing enrolment in CcaCommander.
 */
public class EditEnrolmentCommand extends Command {

    public static final String COMMAND_WORD = "editEnrolment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the enrolment details of the member identified "
            + "by the index number used in the displayed member list at the event identified by the index number used"
            + "in the displayed event list."
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_MEMBER + "MEMBER_INDEX "
            + PREFIX_EVENT + "EVENT_INDEX "
            + "[" + PREFIX_HOURS + "HOURS] "
            + "[" + PREFIX_REMARK + "REMARK] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEMBER + "1 "
            + PREFIX_EVENT + "1 "
            + PREFIX_HOURS + "3 "
            + PREFIX_REMARK + "Organised catering";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Enrolment: %1$s";
    public static final String MESSAGE_COMMIT = "Edited Enrolment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    private final Index memberIndex;
    private final Index eventIndex;
    private final EditEnrolmentDescriptor editEnrolmentDescriptor;

    /**
     * @param memberIndex of the desired member in the filtered member list
     * @param eventIndex of the desired event in the filtered event list
     * @param editEnrolmentDescriptor details to edit the event with
     */
    public EditEnrolmentCommand(Index memberIndex, Index eventIndex, EditEnrolmentDescriptor editEnrolmentDescriptor) {
        requireNonNull(memberIndex);
        requireNonNull(eventIndex);
        requireNonNull(editEnrolmentDescriptor);

        this.memberIndex = memberIndex;
        this.eventIndex = eventIndex;
        this.editEnrolmentDescriptor = new EditEnrolmentDescriptor(editEnrolmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownMemberList = model.getFilteredMemberList();
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (memberIndex.getZeroBased() >= lastShownMemberList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }
        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Member targetMember = lastShownMemberList.get(memberIndex.getZeroBased());
        Event targetEvent = lastShownEventList.get(eventIndex.getZeroBased());
        Attendance enrolmentToCheck =
                new Attendance(targetMember.getName(), targetEvent.getName(), new Hours("0"), new Remark("HOLDER"));

        model.updateFilteredAttendanceList(new EnrolmentExistsPredicate(enrolmentToCheck));
        List<Attendance> enrolmentToEditList = model.getFilteredAttendanceList();
        if (enrolmentToEditList.size() == 0) {
            throw new CommandException(String.format(Messages.MESSAGE_ENROLMENT_DOES_NOT_EXIST,
                    memberIndex.getOneBased(), eventIndex.getOneBased()));
        }
        assert enrolmentToEditList.size() == 1: "There should not be duplicate enrolments";

        Attendance enrolmentToEdit = enrolmentToEditList.get(0);
        Attendance editedEnrolment = createEditedEnrolment(enrolmentToEdit, editEnrolmentDescriptor);

        model.setEnrolment(enrolmentToEdit, editedEnrolment);
        model.updateFilteredAttendanceList(PREDICATE_SHOW_ALL_ATTENDANCES);
        model.commit(String.format(MESSAGE_COMMIT, editedEnrolment.getMemberAndEventAttendance()));
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, Messages.format(editedEnrolment)));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editEventDescriptor}.
     */
    private static Attendance createEditedEnrolment(Attendance enrolmentToEdit,
                                                    EditEnrolmentDescriptor editEnrolmentDescriptor) {
        assert enrolmentToEdit != null;

        Name memberName = enrolmentToEdit.getMemberName();
        Name eventName = enrolmentToEdit.getEventName();
        Hours updatedHours = editEnrolmentDescriptor.getHours().orElse(enrolmentToEdit.getHours());
        Remark updatedRemark = editEnrolmentDescriptor.getRemark().orElse(enrolmentToEdit.getRemark());

        return new Attendance(memberName, eventName, updatedHours, updatedRemark);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditEnrolmentCommand)) {
            return false;
        }

        EditEnrolmentCommand otherEditEnrolmentCommand = (EditEnrolmentCommand) other;
        return memberIndex.equals(otherEditEnrolmentCommand.memberIndex)
                && eventIndex.equals(otherEditEnrolmentCommand.eventIndex)
                && editEnrolmentDescriptor.equals(otherEditEnrolmentCommand.editEnrolmentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("memberIndex", memberIndex)
                .add("eventIndex", eventIndex)
                .add("editEnrolmentDescriptor", editEnrolmentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the enrolment with. Each non-empty field value will replace the
     * corresponding field value of the event.
     */
    public static class EditEnrolmentDescriptor {
        private Hours hours;
        private Remark remark;

        public EditEnrolmentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditEnrolmentDescriptor(EditEnrolmentDescriptor toCopy) {
            setHours(toCopy.hours);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least either hours or remark is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(hours, remark);
        }

        public void setHours(Hours hours) {
            this.hours = hours;
        }

        public Optional<Hours> getHours() {
            return Optional.ofNullable(hours);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEnrolmentDescriptor)) {
                return false;
            }

            EditEnrolmentDescriptor otherEditEnrolmentDescriptor = (EditEnrolmentDescriptor) other;
            return Objects.equals(hours, otherEditEnrolmentDescriptor.hours)
                    && Objects.equals(remark, otherEditEnrolmentDescriptor.remark);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("hours", hours)
                    .add("remark", remark)
                    .toString();
        }
    }
}
