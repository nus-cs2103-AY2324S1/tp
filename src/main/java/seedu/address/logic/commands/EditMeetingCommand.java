package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import seedu.address.model.event.Meeting;
import seedu.address.model.person.Name;



/**
 * Command to edit a meeting in the address book.
 */
public class EditMeetingCommand extends Command {
    public static final String COMMAND_WORD = "edit_meeting";

    public static final String EVENT_TYPE = "meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list.\n"
            + "Existing values will be overwritten by the input values, except for "
            + "the list of assigned persons, which will be appended to the existing list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MEETING_NAME + "MEETING_DETAILS] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_START_TIME + "START_TIME] "
            + "[" + PREFIX_END_TIME + "END_TIME] "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_UNASSIGN_PERSONS + "NAME]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEETING_NAME + "TP WEEK 8 MEETING "
            + PREFIX_DATE + "2023-10-13 "
            + PREFIX_NAME + "Alice "
            + PREFIX_UNASSIGN_PERSONS + "Bob "
            + PREFIX_UNASSIGN_PERSONS + "Charlie ";

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
        Event editedMeeting = createEditedMeeting(meetingToEdit, this.editMeetingDescriptor, model);

        model.setEvent(meetingToEdit, editedMeeting);

        return new CommandResult(generateSuccessMessage(editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * @param meetingToEdit meeting to edit
     * @param editMeetingDescriptor details to edit the meeting with
     * @return meeting with the appropriate details edited
     */
    private static Event createEditedMeeting(Event meetingToEdit,
                                             EditMeetingDescriptor editMeetingDescriptor,
                                             Model model) throws CommandException {
        assert meetingToEdit != null;

        EventName updatedName = editMeetingDescriptor.getName().orElse(meetingToEdit.getName());
        EventDate updatedDate = editMeetingDescriptor.getDate().orElse(meetingToEdit.getStartDate());
        EventTime updatedStartTime = editMeetingDescriptor.getStartTime().orElse(meetingToEdit.getStartTime());
        EventTime updatedEndTime = editMeetingDescriptor.getEndTime().orElse(meetingToEdit.getEndTime());


        Set<Name> updatedPersonNames;

        if (editMeetingDescriptor.getAssignedPersons().isPresent()) {

            Set<Name> invalidNames = model.findInvalidNames(editMeetingDescriptor.getAssignedPersons().get());

            if (!invalidNames.isEmpty()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_PERSON,
                        listInvalidNames(invalidNames)));
            }

            //add the new persons to the existing list of persons
            meetingToEdit.getNames().addAll(editMeetingDescriptor.getAssignedPersons().get());
            updatedPersonNames = meetingToEdit.getNames();
        } else {
            updatedPersonNames = meetingToEdit.getNames();
        }

        if (editMeetingDescriptor.getUnassignedPersons().isPresent()) {
            Set<Name> invalidNames = model.findInvalidNames(editMeetingDescriptor.getUnassignedPersons().get());

            if (!invalidNames.isEmpty()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_PERSON,
                        listInvalidNames(invalidNames)));
            } else if (!meetingToEdit.getNames().containsAll(editMeetingDescriptor.getUnassignedPersons().get())) {
                //case where the persons to be unassigned have not even been previously assigned

                Set <Name> invalidUnassignNames = findInvalidUnassignNames(meetingToEdit,
                        editMeetingDescriptor.getUnassignedPersons().get());

                throw new CommandException(String.format(Messages.MESSAGE_INVALID_UNASSIGN_PERSON,
                        listInvalidNames(invalidUnassignNames)));
            }

            //remove the persons from the new list of persons
            updatedPersonNames.removeAll(editMeetingDescriptor.getUnassignedPersons().get());
        } else {
            // no persons to be unassigned, do nothing
        }
        return new Meeting(updatedName, updatedDate,
                Optional.of(updatedStartTime), Optional.of(updatedEndTime), updatedPersonNames);
    }

    private static Set<Name> findInvalidUnassignNames(Event meetingToEdit, Set<Name> unassignNames) {
        Set<Name> invalidUnassignNames = new HashSet<>();

        for (Name name : unassignNames) {
            if (!meetingToEdit.getNames().contains(name)) {
                invalidUnassignNames.add(name);
            }
        }
        return invalidUnassignNames;
    }


    /**
     * generates a string of invalid names for display.
     */
    private static String listInvalidNames(Set<Name> invalidNames) {
        StringBuilder builder = new StringBuilder();
        for (Name name : invalidNames) {
            builder.append(name.toString());
            builder.append(", ");
        }

        builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        return builder.toString();
    }

    /**
     * Generates a command execution success message with the meeting edited.
     * @param meetingToEdit meeting that was edited
     * @return String containing the success message
     */
    private String generateSuccessMessage(Event meetingToEdit) {
        return String.format(MESSAGE_EDIT_SUCCESS, Messages.formatEvent(meetingToEdit));
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

        private Set<Name> assignPersons;
        private Set<Name> unassignPersons;

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
            return CollectionUtil.isAnyNonNull(this.name, this.date, this.startTime,
                    this.endTime, this.assignPersons, this.unassignPersons);
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

        public void setPersonNames(Set<Name> names) {
            this.assignPersons = (names != null) ? new HashSet<>(names) : null;
        }

        public void setUnassignPersons(Set<Name> names) {
            this.unassignPersons = (names != null) ? new HashSet<>(names) : null;
        }

        public Optional<Set<Name>> getAssignedPersons() {
            return (this.assignPersons != null)
                    ? Optional.of(Collections.unmodifiableSet(this.assignPersons))
                    : Optional.empty();
        }

        public Optional<Set<Name>> getUnassignedPersons() {
            return (this.unassignPersons != null)
                    ? Optional.of(Collections.unmodifiableSet(this.unassignPersons))
                    : Optional.empty();
        }
    }
}
