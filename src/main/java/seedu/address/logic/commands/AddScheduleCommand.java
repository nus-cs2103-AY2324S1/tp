package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_INDEX;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.Schedule;

/**
 * Adds a schedule to the address book.
 */
public class AddScheduleCommand extends Command {

    public static final String COMMAND_WORD = "add-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a schedule to the address book. "
            + "Parameters: "
            + PREFIX_TUTOR_INDEX + "TUTOR_INDEX "
            + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TUTOR_INDEX + "1 "
            + PREFIX_START_TIME + "2023-09-15T09:00:00 "
            + PREFIX_END_TIME + "2023-09-15T11:00:00";

    private final Schedule toAdd;

    /**
     * Creates an AddScheduleCommand to add the specified {@code Schedule}
     */
    public AddScheduleCommand(Schedule schedule) {
        requireNonNull(schedule);
        toAdd = schedule;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Temporary value for parser
        return new CommandResult("Temp");
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
        return toAdd.equals(otherAddScheduleCommand.toAdd);
    }

}
