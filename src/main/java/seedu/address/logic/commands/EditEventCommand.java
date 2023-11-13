package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_GROUPS;
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
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;


/**
 * Command to edit a meeting in the address book.
 */
public class EditEventCommand extends Command {
    public static final String COMMAND_WORD = "edit_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the index number used in the displayed event list.\n"
            + "Existing values will be overwritten by the input values, except for "
            + "the list of assigned persons and the list of assigned groups \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EVENT_NAME + "EVENT_DETAILS] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_START_TIME + "START_TIME] "
            + "[" + PREFIX_END_TIME + "END_TIME] "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_UNASSIGN_PERSONS + "NAME]... "
            + "[" + PREFIX_GROUP + "GROUP]... "
            + "[" + PREFIX_UNASSIGN_GROUPS + "GROUP]... \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_NAME + "FumbleLog Meeting "
            + PREFIX_DATE + "2023-10-13 "
            + PREFIX_NAME + "Ken "
            + PREFIX_GROUP + "Team2 ";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_EDIT_SUCCESS = "Edited event: %1$s";

    public final Index index;
    public final EditEventDescriptor editEventDescriptor;

    /**
     * Takes in the index of the meeting to edit and its descriptor.
     * @param index of the meeting to edit
     * @param editEventDescriptor details to edit the meeting with
     */
    public EditEventCommand(Index index, EditEventDescriptor editEventDescriptor) {
        requireNonNull(index);
        requireNonNull(editEventDescriptor);
        this.index = index;
        this.editEventDescriptor = editEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Event> lastShownList = model.getFilteredEventList();


        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event meetingToEdit = lastShownList.get(index.getZeroBased());
        Event editedMeeting = createEditedMeeting(meetingToEdit, this.editEventDescriptor, model);

        // ensure that the user is not editing a valid time into an invalid time
        if (this.editEventDescriptor.getDate().isPresent()
                || this.editEventDescriptor.getStartTime().isPresent()
                || this.editEventDescriptor.getEndTime().isPresent()) {
            CommandUtil.verifyEventTimes(editedMeeting);
        }

        model.setEvent(meetingToEdit, editedMeeting);

        return new CommandResult(generateSuccessMessage(editedMeeting));
    }

    private static Event createEditedMeeting(Event meetingToEdit,
                                             EditEventDescriptor editEventDescriptor,
                                             Model model) throws CommandException {
        assert meetingToEdit != null;

        // All attributes are optional, so if they are not present, use the original values
        EventName updatedName = editEventDescriptor.getName().orElse(meetingToEdit.getName());
        EventDate updatedDate = editEventDescriptor.getDate().orElse(meetingToEdit.getStartDate());
        EventTime updatedStartTime = editEventDescriptor.getStartTime().orElse(meetingToEdit.getStartTime());
        EventTime updatedEndTime = editEventDescriptor.getEndTime().orElse(meetingToEdit.getEndTime());

        // Edit persons
        Set<Name> updatedPersonNames;
        updatedPersonNames = handleEditAssignPersons(meetingToEdit, editEventDescriptor, model);
        handleEditUnassignPersons(meetingToEdit, editEventDescriptor, model, updatedPersonNames);

        // Editing groups
        Set<Group> updatedGroups;
        updatedGroups = handleEditAssignGroups(meetingToEdit, editEventDescriptor, model);
        handleEditUnassignGroups(meetingToEdit, editEventDescriptor);

        return new Meeting(updatedName, updatedDate,
                Optional.of(updatedStartTime), Optional.of(updatedEndTime), updatedPersonNames, updatedGroups);
    }

    private static void handleEditUnassignGroups(Event meetingToEdit, EditEventDescriptor editEventDescriptor)
                throws CommandException {
        if (editEventDescriptor.getUnassignGroups().isPresent()) {
            if (!meetingToEdit.getGroups().containsAll(editEventDescriptor.getUnassignGroups().get())) {

                Set<Group> invalidUnassignGroups = findInvalidUnassignGroups(meetingToEdit,
                        editEventDescriptor.getUnassignGroups().get());

                //case where the groups to be unassigned have not even been previously assigned
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_UNASSIGN_GROUP,
                        listInvalidGroups(invalidUnassignGroups)));
            }
            meetingToEdit.getGroups().removeAll(editEventDescriptor.getUnassignGroups().get());
        }
    }

    private static Set<Group> handleEditAssignGroups(Event meetingToEdit, EditEventDescriptor editEventDescriptor,
                Model model) throws CommandException {
        Set<Group> updatedGroups;
        if (editEventDescriptor.getGroups().isPresent()) {
            Set<Group> invalidGroups = model.findInvalidGroups(editEventDescriptor.getGroups().get());
            if (!invalidGroups.isEmpty()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_GROUP,
                        listInvalidGroups(invalidGroups)));
            }
            meetingToEdit.getGroups().addAll(editEventDescriptor.getGroups().get());
        }
        updatedGroups = meetingToEdit.getGroups();
        return updatedGroups;
    }

    private static void handleEditUnassignPersons(Event meetingToEdit, EditEventDescriptor editEventDescriptor,
              Model model, Set<Name> updatedPersonNames) throws CommandException {
        if (editEventDescriptor.getUnassignedPersons().isPresent()) {
            Set<Name> invalidNames = model.findInvalidNames(editEventDescriptor.getUnassignedPersons().get());
            if (!invalidNames.isEmpty()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_PERSON,
                        listInvalidNames(invalidNames)));
            } else if (!meetingToEdit.getNames().containsAll(editEventDescriptor.getUnassignedPersons().get())) {
                //case where the persons to be unassigned have not even been previously assigned
                Set <Name> invalidUnassignNames = findInvalidUnassignNames(meetingToEdit,
                        editEventDescriptor.getUnassignedPersons().get());
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_UNASSIGN_PERSON,
                        listInvalidNames(invalidUnassignNames)));
            }
            //remove the persons from the new list of persons
            updatedPersonNames.removeAll(editEventDescriptor.getUnassignedPersons().get());
        } // no persons to be unassigned, do nothing
    }

    private static Set<Name> handleEditAssignPersons(Event meetingToEdit, EditEventDescriptor editEventDescriptor,
                Model model) throws CommandException {
        Set<Name> updatedPersonNames;
        if (editEventDescriptor.getAssignedPersons().isPresent()) {
            Set<Name> invalidNames = model.findInvalidNames(editEventDescriptor.getAssignedPersons().get());
            if (!invalidNames.isEmpty()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_PERSON,
                        listInvalidNames(invalidNames)));
            }
            //add the new persons to the existing list of persons
            meetingToEdit.getNames().addAll(editEventDescriptor.getAssignedPersons().get());
        }
        updatedPersonNames = meetingToEdit.getNames();
        return updatedPersonNames;
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

    private static Set<Group> findInvalidUnassignGroups(Event meetingToEdit, Set<Group> unassignGroups) {
        Set<Group> invalidUnassignGroups = new HashSet<>();
        for (Group group : unassignGroups) {
            if (!meetingToEdit.getGroups().contains(group)) {
                invalidUnassignGroups.add(group);
            }
        }
        return invalidUnassignGroups;
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

    private static String listInvalidGroups(Set<Group> invalidGroups) {
        StringBuilder builder = new StringBuilder();
        for (Group group : invalidGroups) {
            builder.append(group.toString());
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
        if (!(other instanceof EditEventCommand)) {
            return false;
        }

        EditEventCommand e = (EditEventCommand) other;
        return index.equals(e.index)
                && this.editEventDescriptor.equals(e.editEventDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditEventDescriptor {

        private EventName name;
        private EventDate date;
        private EventTime startTime;
        private EventTime endTime;

        private Set<Name> assignPersons;
        private Set<Name> unassignPersons;

        private Set<Group> assignGroups;
        private Set<Group> unassignGroups;

        public EditEventDescriptor() {
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
                    this.endTime, this.assignPersons, this.unassignPersons, this.assignGroups, this.unassignGroups);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", this.name)
                    .add("date", this.date.toString())
                    .add("start time", this.startTime.toString())
                    .add("end time", this.endTime.toString())
                    .add("assign persons", this.assignPersons)
                    .add("unassign persons", this.unassignPersons)
                    .add("assign groups", this.assignGroups)
                    .add("unassign groups", this.unassignGroups)
                    .toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEventDescriptor)) {
                return false;
            }

            EditEventDescriptor e = (EditEventDescriptor) other;
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

        public void setGroups(Set<Group> groups) {
            this.assignGroups = (groups != null) ? new HashSet<>(groups) : null;
        }

        public Optional<Set<Group>> getGroups() {
            return (this.assignGroups != null)
                    ? Optional.of(Collections.unmodifiableSet(this.assignGroups))
                    : Optional.empty();
        }

        public void setUnassignGroups(Set<Group> groups) {
            this.unassignGroups = (groups != null) ? new HashSet<>(groups) : null;
        }

        public Optional<Set<Group>> getUnassignGroups() {
            return (this.unassignGroups != null)
                    ? Optional.of(Collections.unmodifiableSet(this.unassignGroups))
                    : Optional.empty();
        }
    }
}
