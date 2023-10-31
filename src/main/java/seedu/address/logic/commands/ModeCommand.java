package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Toggles between light and dark mode for the application.
 */
public class ModeCommand extends Command {

    public static final String COMMAND_WORD = "mode";

    public static final String MESSAGE_SUCCESS = "Toggled light/dark mode";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

}
