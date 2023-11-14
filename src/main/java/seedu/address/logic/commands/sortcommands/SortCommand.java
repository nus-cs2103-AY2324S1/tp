package seedu.address.logic.commands.sortcommands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_RANGE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANGE_START;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Abstract class for SortCommand.
 */
public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Success message for the sort command!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the company list. \n"
        + "\n"
        + "Parameters: \n"
        + "c - sort the company list \n"
        + "[" + PREFIX_RANGE_START + "START_TIME_FOR_SORTING_RANGE (in dd-MM-yyyy HH:mm)] "
        + "[" + PREFIX_RANGE_END + "END_TIME_FOR_SORTING_RANGE (in dd-MM-yyyy HH:mm)] \n"
        + "Example: " + COMMAND_WORD + " c "
        + PREFIX_RANGE_START + "01-01-2020 00:00 "
        + PREFIX_RANGE_END + "01-01-2021 00:00 \n";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}

