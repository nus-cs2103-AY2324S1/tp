package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.Model;

/**
 * Finds the best match based on Date Preferences of the user.
 */
public class BestMatchCommand extends Command {
    public static final String COMMAND_WORD = "bestMatch";

    public static final String MESSAGE_SUCCESS = "Here's your best match!";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.getBestDate();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
