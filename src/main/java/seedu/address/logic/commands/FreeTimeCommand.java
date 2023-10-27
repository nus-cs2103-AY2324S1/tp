package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.Model;
import seedu.address.model.interval.Interval;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEGIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;

public class FreeTimeCommand extends Command {

    public static final String COMMAND_WORD = "freeTime";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": finds a free time.  "
            + "Parameters: "
            + PREFIX_DAY + "DAY "
            + PREFIX_DURATION + "HOURS "
            + PREFIX_BEGIN + "BEGIN "
            + PREFIX_END + "END "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DAY + "Monday "
            + PREFIX_DURATION+ "2 "
            + PREFIX_BEGIN + "0800 "
            + PREFIX_END + "2200 ";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "FreeTime command not implemented yet";
    public static final String MESSAGE_SUCCESS = "Available timings: ";
    private final Interval toFind;

    /**
     * Creates an FreeTimeCommand to check for the specified {@code Interval}
     */
    public FreeTimeCommand(Interval interval) {
        requireNonNull(interval);
        toFind = interval;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
        //return new CommandResult(MESSAGE_SUCCESS);
    }
}
