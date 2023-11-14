package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Show the linked Tree to the user.
 */
public class TreeCommand extends Command {

    public static final String COMMAND_WORD = "tree";

    public static final String MESSAGE_SUCCESS = "Showing LinkTree !!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS,
                false, false, false, false, true, false, false, false);
    }
}
