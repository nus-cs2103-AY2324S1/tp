package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Dummy Command, to display all help.
 */
public class DummyCommand extends Command {

    public static final String MESSAGE_SUCCESS = "This command is not supposed to be run!";

    public static final String MESSAGE_USAGE = "This command is not supposed to be displayed!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
