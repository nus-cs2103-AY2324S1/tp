package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;
import seedu.address.model.schedule.Status;

/**
 * Edits the details of an existing schedule in the address book.
 */
public class EditScheduleCommand extends Command {
    public static final String COMMAND_WORD = "edit-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the schedule identified "
        + "by the index number used in the displayed schedule list. "
        + "Parameters: "
        + "SCHEDULE_INDEX (must be a positive integer)"
        + PREFIX_START_TIME + "START_TIME "
        + PREFIX_END_TIME + "END_TIME\n"
        + "Example: " + COMMAND_WORD + " "
        + "1 "
        + PREFIX_START_TIME + "2023-09-15T09:00:00 "
        + PREFIX_END_TIME + "2023-09-15T11:00:00";

    public static final String MESSAGE_EDIT_SCHEDULE_SUCCESS = "Edited Schedule: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This schedule already exists in the address book";
    public static final String MESSAGE_CLASHING_SCHEDULE = "This tutor has a clashing schedule in the address book";

    private final Index index;
    private final EditScheduleDescriptor editScheduleDescriptor;

    /**
     * @param index                of the schedule in the filtered schedule list to edit
     * @param editScheduleDescriptor details to edit the schedule with
     */
    public EditScheduleCommand(Index index, EditScheduleDescriptor editScheduleDescriptor) {
        requireNonNull(index);
        requireNonNull(editScheduleDescriptor);

        this.index = index;
        this.editScheduleDescriptor = new EditScheduleDescriptor(editScheduleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownList = model.getFilteredScheduleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }

        Schedule scheduleToEdit = lastShownList.get(index.getZeroBased());
        Schedule editedSchedule = createEditedSchedule(scheduleToEdit, editScheduleDescriptor);

        boolean hasScheduleClash =
            model.getAddressBook().getScheduleList().stream().filter(schedule -> !schedule.isDuplicate(scheduleToEdit))
                .anyMatch(schedule -> schedule.isClashing(editedSchedule));

        if (!scheduleToEdit.equals(editedSchedule) && model.hasSchedule(editedSchedule)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }


        if (hasScheduleClash) {
            throw new CommandException(MESSAGE_CLASHING_SCHEDULE);
        }

        model.setSchedule(scheduleToEdit, editedSchedule);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
        return new CommandResult(String.format(MESSAGE_EDIT_SCHEDULE_SUCCESS, Messages.format(editedSchedule)));
    }

    /**
     * Creates and returns a {@code Schedule} with the details of {@code scheduleToEdit}
     * edited with {@code editScheduleDescriptor}.
     */
    private static Schedule createEditedSchedule(Schedule scheduleToEdit,
        EditScheduleDescriptor editScheduleDescriptor) {
        StartTime updatedStartTime = editScheduleDescriptor.getStartTime().orElse(scheduleToEdit.getStartTime());
        EndTime updatedEndTime = editScheduleDescriptor.getEndTime().orElse(scheduleToEdit.getEndTime());
        Person tutor = editScheduleDescriptor.getTutor().orElse(scheduleToEdit.getTutor());
        Status status = scheduleToEdit.getStatus();

        return new Schedule(tutor, updatedStartTime, updatedEndTime, status);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditScheduleCommand)) {
            return false;
        }

        EditScheduleCommand otherEditScheduleCommand = (EditScheduleCommand) other;
        return index.equals(otherEditScheduleCommand.index)
            && editScheduleDescriptor.equals(otherEditScheduleCommand.editScheduleDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("index", index)
            .add("editScheduleDescriptor", editScheduleDescriptor)
            .toString();
    }

    /**
     * Stores the details to edit the schedule with. Each non-empty field value will replace the
     * corresponding field value of the schedule.
     */
    public static class EditScheduleDescriptor {
        private StartTime startTime;
        private EndTime endTime;
        private Person tutor;

        public EditScheduleDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditScheduleDescriptor(EditScheduleDescriptor toCopy) {
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
            setTutor(toCopy.tutor);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(startTime, endTime);
        }

        public void setStartTime(StartTime startTime) {
            this.startTime = startTime;
        }

        public Optional<StartTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(EndTime endTime) {
            this.endTime = endTime;
        }

        public Optional<EndTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setTutor(Person tutor) {
            this.tutor = tutor;
        }

        public Optional<Person> getTutor() {
            return Optional.ofNullable(tutor);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditScheduleDescriptor)) {
                return false;
            }

            EditScheduleDescriptor otherEditScheduleDescriptor = (EditScheduleDescriptor) other;
            return Objects.equals(startTime, otherEditScheduleDescriptor.startTime)
                && Objects.equals(endTime, otherEditScheduleDescriptor.endTime)
                && Objects.equals(tutor, otherEditScheduleDescriptor.tutor);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .add("tutor", tutor)
                .toString();
        }
    }
}

