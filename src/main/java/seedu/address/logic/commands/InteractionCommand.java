package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Represents a command that creates an interaction with the client.
 */
public class InteractionCommand extends Command {
    public static final String COMMAND_WORD = "interaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an interaction with the client "
            + "specified by clientID and a note and a outcome. "
            + "Parameters: "
            + "clientID "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Created client interaction";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
