package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.*;

import java.util.List;
import java.util.Optional;

import static seedu.address.logic.parser.CliSyntax.*;

public class EditMeetingCommand extends Command {
    public static final String COMMAND_WORD = "edit_meeting";

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

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "edit_meeting command not implemented yet";

    public static final String MESSAGE_EDIT_SUCCESS = "Edited meeting: %1$s";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    public final Index index;
    public final EditMeetingDescriptor editMeetingDescriptor;

    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        this.index = index;
        this.editMeetingDescriptor = editMeetingDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Event> lastShownList = model.getFilteredEventList();

        System.out.println("got filtered event list");
        System.out.println(lastShownList.size());

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Event meetingToEdit = lastShownList.get(index.getZeroBased());
        Event editedMeeting = createEditedMeeting(meetingToEdit, this.editMeetingDescriptor);

        model.setEvent(meetingToEdit, editedMeeting);

        return new CommandResult(generateSuccessMessage(editedMeeting));
    }

    private static Meeting createEditedMeeting(Event meetingToEdit,
                                               EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        EventName updatedName = editMeetingDescriptor.getName().orElse(meetingToEdit.getName());
        EventDate updatedDate = editMeetingDescriptor.getDate().orElse(meetingToEdit.getStartDate());
        EventTime updatedStartTime = editMeetingDescriptor.getStartTime().orElse(meetingToEdit.getStartTime());
        EventTime updatedEndTime = editMeetingDescriptor.getEndTime().orElse(meetingToEdit.getEndTime());

        return new Meeting(updatedName, updatedDate, updatedStartTime, updatedEndTime);
    }

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

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(this.name, this.date, this.startTime, this.endTime);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", this.name)
                    .add("phone", this.date.toString())
                    .add("email", this.startTime.toString())
                    .add("address", this.endTime.toString())
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
