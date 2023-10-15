package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.EventType;
import seedu.address.model.event.Meeting;


/**
 * Command to edit a meeting in the address book.
 */
public class EditMeetingCommand extends Command {
    public static final String COMMAND_WORD = "edit_meeting";

    public static final String EVENT_TYPE = "meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MEETING_NAME + "MEETING_DETAILS] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_START_TIME + "START_TIME] "
            + "[" + PREFIX_END_TIME + "END_TIME] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEETING_NAME + "TP WEEK 8 MEETING"
            + PREFIX_DATE + "2023-10-13 ";

    public static final String MESSAGE_EDIT_SUCCESS = "Edited meeting: %1$s";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    public final Index index;
    public final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * Takes in the index of the meeting to edit and its descriptor.
     * @param index of the meeting to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        this.index = index;
        this.editMeetingDescriptor = editMeetingDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Event> lastShownList = model.getEventList();


        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event meetingToEdit = lastShownList.get(index.getZeroBased());
        Event editedMeeting = createEditedMeeting(meetingToEdit, this.editMeetingDescriptor);

        model.setEvent(meetingToEdit, editedMeeting);

        return new CommandResult(generateSuccessMessage(editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * @param meetingToEdit meeting to edit
     * @param editMeetingDescriptor details to edit the meeting with
     * @return meeting with the appropriate details edited
     */
    private static Meeting createEditedMeeting(Event meetingToEdit,
                                               EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        EventName updatedName = editMeetingDescriptor.getName().orElse(meetingToEdit.getName());
        EventDate updatedDate = editMeetingDescriptor.getDate().orElse(meetingToEdit.getStartDate());
        EventTime updatedStartTime = editMeetingDescriptor.getStartTime().orElse(meetingToEdit.getStartTime());
        EventTime updatedEndTime = editMeetingDescriptor.getEndTime().orElse(meetingToEdit.getEndTime());

        return new Meeting(new EventType(EVENT_TYPE), updatedName, updatedDate, updatedStartTime, updatedEndTime);
    }

    /**
     * Generates a command execution success message with the meeting edited.
     * @param meetingToEdit meeting that was edited
     * @return String containing the success message
     */
    private String generateSuccessMessage(Event meetingToEdit) {
        return String.format(MESSAGE_EDIT_SUCCESS, meetingToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && this.editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {

        private EventName name;
        private EventDate date;
        private EventTime startTime;
        private EventTime endTime;

        public EditMeetingDescriptor() {
        }

        public Optional<EventName> getName() {
            return Optional.ofNullable(this.name);
        }

        public Optional<EventDate> getDate() {
            return Optional.ofNullable(this.date);
        }

        public Optional<EventTime> getStartTime() {
            return Optional.ofNullable(this.startTime);
        }

        public Optional<EventTime> getEndTime() {
            return Optional.ofNullable(this.endTime);
        }

        public void setName(EventName name) {
            this.name = name;
        }

        public void setDate(EventDate date) {
            this.date = date;
        }

        public void setStartTime(EventTime startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(EventTime endTime) {
            this.endTime = endTime;
        }

        /**
         * Checks if fields are edited.
         * @return true if at least one field is edited
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(this.name, this.date, this.startTime, this.endTime);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", this.name)
                    .add("date", this.date.toString())
                    .add("start time", this.startTime.toString())
                    .add("end time", this.endTime.toString())
                    .toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            EditMeetingDescriptor e = (EditMeetingDescriptor) other;
            return this.name.equals(e.name)
                    && this.date.equals(e.date)
                    && this.startTime.equals(e.startTime)
                    && this.endTime.equals(e.endTime);
        }
    }
}
