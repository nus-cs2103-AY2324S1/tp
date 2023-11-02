package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Filters the list of users by the given metric and value.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters users by categories (e.g. name, status, tags)"
            + " whose details match the given keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alex bernice st/interviewed t/software engineer";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
