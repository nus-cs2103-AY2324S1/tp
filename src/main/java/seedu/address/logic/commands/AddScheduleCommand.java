package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.model.schedule.Schedule.MESSAGE_CONSTRAINTS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;

/**
 * Adds a schedule to the address book.
 */
public class AddScheduleCommand extends Command {

    public static final String COMMAND_WORD = "add-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a schedule to the address book for a tutor "
            + "by the index number used in the displayed tutor list.\n"
            + "Parameters: "
            + "TUTOR_INDEX (must be a positive integer)"
            + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_START_TIME + "2023-09-15T09:00:00 "
            + PREFIX_END_TIME + "2023-09-15T11:00:00";

    public static final String MESSAGE_SUCCESS = "New schedule %1$s has been added.";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This schedule already exists in the address book";
    public static final String MESSAGE_CLASHING_SCHEDULE = "This tutor has a clashing schedule in the address book";

    private final Index index;
    private final StartTime startTime;
    private final EndTime endTime;

    /**
     * Creates an AddScheduleCommand to add the specified {@code Schedule}
     */
    public AddScheduleCommand(Index index, StartTime startTime, EndTime endTime) {
        requireAllNonNull(index, startTime, endTime);

        this.index = index;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Schedule toAdd;
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person tutor = lastShownList.get(index.getZeroBased());

        try {
            toAdd = new Schedule(tutor, startTime, endTime);
        } catch (IllegalArgumentException e) {
            throw new CommandException(MESSAGE_CONSTRAINTS);
        }

        boolean hasScheduleClash =
                model.getAddressBook().getScheduleList().stream().anyMatch(schedule -> schedule.isClashing(toAdd));

        if (model.hasSchedule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }

        if (hasScheduleClash) {
            throw new CommandException(MESSAGE_CLASHING_SCHEDULE);
        }

        model.addSchedule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddScheduleCommand)) {
            return false;
        }

        AddScheduleCommand otherAddScheduleCommand = (AddScheduleCommand) other;
        return index.equals(otherAddScheduleCommand.index)
                && startTime.equals(otherAddScheduleCommand.startTime)
                && endTime.equals(otherAddScheduleCommand.endTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .toString();
    }
}
